public class Main {

    public static void main(String[] args) {
        StudentsStorer st = new Database();
        Horoscop.readStudentsDetails(st);
        //StudentsStorer st1 = new XMLFile();
        //Horoscop.readStudentsDetails(st1);
        Horoscop.preziceMedie(2);
        Horoscop.afisareNumarStudentiMediePeste8CuMedieScazuta();
        Horoscop.studentZiBuna(1);
    }
}
