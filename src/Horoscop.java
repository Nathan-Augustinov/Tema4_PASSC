import java.util.ArrayList;
import java.time.LocalDate;


public class Horoscop {
    private static ArrayList<Student>students=new ArrayList<>();
    private static Horoscop instance=null;
    private Horoscop (){}
    public static Horoscop getInstance(){
        if(instance==null){
            instance=new Horoscop();
        }
        return instance;
    }
    public static void readStudentsDetails(StudentsStorer st){
        students=st.readStudents();
    }

    public static void preziceMedie(int nr_matricol){
        String nume_student=null;
        String string_afisare=null;
        for(Student student:students){
            if(student.getNr_matricol()==nr_matricol){
                nume_student=student.getNume();
                break;
            }
        }
        if(Horoscop.getInstance().algoritmPredictie(nr_matricol,nume_student)){
            string_afisare=String.format("Media studentului %s va creste in viitor!",nume_student);
            System.out.println(string_afisare);
        }
        else{
            string_afisare=String.format("Media studentului %s va scadea in viitor!",nume_student);
            System.out.println(string_afisare);
        }

    }
    //daca media creste metoda returneaza true, altfel false
    private boolean algoritmPredictie(int nr_matricol, String nume){
        int nr_matricol_aux=nr_matricol;
        int suma=0;
        while(nr_matricol_aux > 0){
            suma = suma + nr_matricol_aux % 10;
            nr_matricol_aux /= 10;
        }
        for(int i=0;i<nume.length();i++){
            suma=suma+nume.charAt(i);
        }
        return (suma % 2 == 1);
    }

    public int preziceStudentiPeste8MedieScazuta(){
        int counter=0;
        for(Student student:students){
            if(!algoritmPredictie(student.getNr_matricol(),student.getNume()) && student.getMedie() > 8){
                counter++;
            }
        }
        return counter;
    }
    public static void afisareNumarStudentiMediePeste8CuMedieScazuta(){
        String afisare = String.format("Numarul de studenti cu medie peste 8 carora le va scadea media este %d.",Horoscop.getInstance().preziceStudentiPeste8MedieScazuta());
        System.out.println(afisare);
    }

    public static void studentZiBuna(int nr_matricol){
        String nume_student=null;
        String string_afisare=null;
        for(Student student:students){
            if(student.getNr_matricol()==nr_matricol){
                nume_student=student.getNume();
                break;
            }
        }
        if(Horoscop.getInstance().algoritmZiBuna(nume_student)){
            string_afisare=String.format("Studentul %s va avea azi o zi buna!",nume_student);
            System.out.println(string_afisare);
        }
        else{
            string_afisare=String.format("Studentul %s nu va avea azi o zi buna!",nume_student);
            System.out.println(string_afisare);
        }
    }
    private boolean algoritmZiBuna(String nume){
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        int suma = currentDay + nume.charAt(0);
        return (suma % 2 == 1);
    }
}
