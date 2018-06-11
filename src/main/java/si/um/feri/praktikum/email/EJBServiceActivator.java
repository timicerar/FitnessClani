package si.um.feri.praktikum.email;

public interface EJBServiceActivator {

    void posljiMailNovemuClanu(String ime, String priimek, String email);

    void posljiMailZaPrijavoNaProgram(String ime, String priimek, String email, String program);

    void posljiMailZaKoncanProgram(String ime, String priimek, String email, String program);

}
