public class Main
{
    public static void main(String[] args)
    {
        Collection<Pessoa> pessoas = new ArrayList<>();

        Pessoa pessoa = new Pessoa(
            "Robertin Bosque", Time.now()
        );
        pessoas.add(pessoa);

        System.out.println(pessoa.getNome());

    }
}
