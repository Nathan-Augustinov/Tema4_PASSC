public class Student {
    private int nr_matricol;
    private double medie;
    private String nume;

    public Student(){}

    public Student(int nr_matricol, double medie, String nume) {
        this.nr_matricol = nr_matricol;
        this.medie = medie;
        this.nume = nume;
    }

    public int getNr_matricol() {
        return nr_matricol;
    }

    public double getMedie() {
        return medie;
    }

    public String getNume() {
        return nume;
    }
}
