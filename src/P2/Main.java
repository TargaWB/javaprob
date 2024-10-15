package P2;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Produs> produse = new ArrayList<>();
        citesteProduseDinFisier(produse, "D:\\lucru_java_intellij\\Lab3\\src\\P2\\produse.csv");
        Scanner scanner = new Scanner(System.in);
        int optiune;
        do {
            System.out.println("Meniu:");
            System.out.println("1. Afișează toate produsele");
            System.out.println("2. Afișează produsele expirate");
            System.out.println("3. Vinde un produs");
            System.out.println("4. Afișează produsele cu prețul minim");
            System.out.println("5. Salvează produsele cu cantitatea mai mică de un număr dat într-un fișier");
            System.out.println("6. Ieșire");
            System.out.print("Alegeți o opțiune: ");
            optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    afiseazaProduse(produse);
                    break;
                case 2:
                    afiseazaProduseExpirate(produse);
                    break;
                case 3:
                    vindeProdus(produse, scanner);
                    break;
                case 4:
                    afiseazaProduseCuPretMinim(produse);
                    break;
                case 5:
                    salveazaProduseCuCantitateRedusa(produse, scanner);
                    break;
                case 6:
                    System.out.println("Ieșire din program.");
                    break;
                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        } while (optiune != 6);

        scanner.close();
    }

    private static void citesteProduseDinFisier(List<Produs> produse, String fisier) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] detalii = linie.split(",");
                String denumire = detalii[0];
                float pret = Float.parseFloat(detalii[1]);
                int cantitate = Integer.parseInt(detalii[2]);
                LocalDate dataExpirarii = LocalDate.parse(detalii[3]);

                produse.add(new Produs(denumire, pret, cantitate, dataExpirarii));
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }

    private static void afiseazaProduse(List<Produs> produse) {
        System.out.println("Produse:");
        for (Produs p : produse) {
            System.out.println(p);
        }
    }

    private static void afiseazaProduseExpirate(List<Produs> produse) {
        System.out.println("Produse expirate:");
        for (Produs p : produse) {
            if (p.esteExpirat()) {
                System.out.println(p);
            }
        }
    }

    private static void vindeProdus(List<Produs> produse, Scanner scanner) {
        System.out.print("Introduceți denumirea produsului: ");
        String denumire = scanner.nextLine();
        System.out.print("Introduceți cantitatea dorită: ");
        int cantitate = scanner.nextInt();

        Produs produsVandut = null;
        for (Produs p : produse) {
            if (p.getDenumire().equalsIgnoreCase(denumire)) {
                produsVandut = p;
                break;
            }
        }

        if (produsVandut != null && produsVandut.vinde(cantitate)) {
            System.out.println("Produs vândut cu succes!");
            if (produsVandut.getCantitatea() == 0) {
                produse.remove(produsVandut);
            }
        } else {
            System.out.println("Nu există suficient stoc sau produsul nu a fost găsit.");
        }

        System.out.println("Încasări totale: " + Produs.incasari);
    }

    private static void afiseazaProduseCuPretMinim(List<Produs> produse) {
        if (produse.isEmpty()) {
            System.out.println("Nu există produse.");
            return;
        }

        float pretMinim = Float.MAX_VALUE;
        for (Produs p : produse) {
            if (p.getPretul() < pretMinim) {
                pretMinim = p.getPretul();
            }
        }

        System.out.println("Produse cu prețul minim:");
        for (Produs p : produse) {
            if (p.getPretul() == pretMinim) {
                System.out.println(p);
            }
        }
    }

    private static void salveazaProduseCuCantitateRedusa(List<Produs> produse, Scanner scanner) {
        System.out.print("Introduceți cantitatea minimă: ");
        int cantitateMinima = scanner.nextInt();

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("produse_cantitate_redusa.csv"))) {
            for (Produs p : produse) {
                if (p.getCantitatea() < cantitateMinima) {
                    writer.write(p.toString());
                    writer.newLine();
                }
            }
            System.out.println("Produsele cu cantitatea redusă au fost salvate în produse_cantitate_redusa.csv.");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea fișierului: " + e.getMessage());
        }
    }
}