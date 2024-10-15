package P1;

public class Parabola {
    private int a,b,c;
    public Parabola(int a, int b, int c)
    {
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public void varf_parabola()
    {
        System.out.println("varful este "+String.format("%.2f",varf_x())+","+String.format("%.2f",varf_y()));
    }
    public float varf_x()
    {
        return (float)(-b/(a*2.0));
    }
    public float varf_y()
    {
        return (float)(-(b*b)+(4.0*a*c)/(4.0*a));
    }
    public String ToString()
    {
        return ("fx="+a+"x^2"+b+"x"+" "+c);
    }
    public void mij(Parabola p)
    {
        float x1=0,x2=0;
        x1=(varf_x()+p.varf_x())/2;
        x2=(varf_y()+p.varf_y())/2;
        System.out.println("Mijlocul este"+x1+","+x2);
    }

    public static void mij2(Parabola p, Parabola p1)
    {
        float x1=0,x2=0;
        x1=(p.varf_x()+p1.varf_x())/2;
        x2=(p.varf_y()+p1.varf_y())/2;
        System.out.println("Mij este:"+x1+","+x2);
    }

    public void lungimea(Parabola p)
    {
        double x1=(p.varf_x()-varf_x());
        double x2=(p.varf_y()-varf_y());
        double x;
        x=Math.hypot(x1,x2);
        System.out.println("Lungimea:"+x);

    }
     public static void lungimea2(Parabola p, Parabola p1)
     {
         double x1=(p1.varf_x()-p.varf_x());
         double x2=(p1.varf_y()-p.varf_y());
         double x;
         x=Math.hypot(x1,x2);
         System.out.println("Lungimea:"+x);
     }

}
