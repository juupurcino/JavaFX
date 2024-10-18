import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

        static double porcentagem(long total, long valor){
        
                double porcentagem = (float) valor / (total / 100);   
                return porcentagem;
        }
        public static void main(String[] args) throws IOException {

                // ArrayList<Integer> list = new ArrayList<>();
                // for (int i = 1; i < 6; i++)
                // list.add(i);

                // List<Integer> result = list.stream()
                // .map(n -> n * n)
                // .filter(n -> n % 2 == 1)
                // .sorted((n, m) -> m - n)
                // .limit(2)
                // .collect(Collectors.toList());

                // for (Integer value : result)
                // System.out.println(value); // 25 9

                // Integer sum = Stream.of(1, 2, 3, 4, 5)
                // .map(n -> n * n)
                // .filter(n -> n % 2 == 1)
                // .sorted()
                // .skip(1)
                // .reduce(0, (acc, crr) -> acc + crr);

                // System.out.println(sum); // 34

                // boolean allPrimes = Stream.of(2, 3, 5, 7, 11)
                // .allMatch(n -> {
                // Integer a = n / 2;
                // Integer r = ((int) Math.pow(a, n - 1) % n);
                // return r == 1;
                // });

                // System.out.println(allPrimes); // true

                // Stream.of(3, 5, 7, 8, 11)
                // .filter(n -> n % 2 == 0)
                // .findAny()
                // .ifPresent(n -> System.out.println(n)); // 8

                // long odds = IntStream.range(1, 300)
                // .filter(n -> n % 2 == 1)
                // .count();

                // System.out.println(odds); // 150

                // Lendo de Arquivos
                long vacinados = Files
                                .lines(Paths.get("C:\\Users\\disrct\\Desktop\\JavaFX\\Stream\\INFLUD21-01-05-2023.csv"),
                                                Charset.defaultCharset())
                                .skip(1)
                                .map(line -> line.split(";"))
                                .filter(line -> line[154].contains("1") && line[109].contains("2") && line[106].contains("5")) // Recebeu vacina, morto e covid
                                .count();

                long naoVacinados = Files
                                .lines(Paths.get("C:\\Users\\disrct\\Desktop\\JavaFX\\Stream\\INFLUD21-01-05-2023.csv"),
                                                Charset.defaultCharset())
                                .skip(1)
                                .map(line -> line.split(";"))
                                .filter(line -> line[154].contains("2") && line[109].contains("2") && line[106].contains("5")) // Não recebeu vacina, morto e covid
                                .count();

                long total = Files
                                .lines(Paths.get("C:\\Users\\disrct\\Desktop\\JavaFX\\Stream\\INFLUD21-01-05-2023.csv"),
                                                Charset.defaultCharset())
                                .skip(1)
                                .map(line -> line.split(";"))
                                .filter(line -> line[109].contains("2") && line[106].contains("5")) // Morto e covid
                                .filter(line -> line[154].contains("1") || line[154].contains("2")) //recebeu vacina e não recebeu
                                .count();


                System.err.println("Vacinados: " + vacinados);
                System.err.printf("Vacinados: %.0f", porcentagem(total, vacinados));
                System.err.println("%");
                System.err.println("Nao vacinados: " + naoVacinados);
                System.err.printf("Nao vacinados: %.0f", porcentagem(total, naoVacinados));
                System.err.println("%");
                System.err.println("Total: " + total);

        }


}

