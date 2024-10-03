public class Pessoa
{
    public Pessoa(String nome, Time birth) {
        this.nome = nome;
        this.birth = birth;
    }

    private Time birth;
    public Time getBirth() {
        return birth;
    }

    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}