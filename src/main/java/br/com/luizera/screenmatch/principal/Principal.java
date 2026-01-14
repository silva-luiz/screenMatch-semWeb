package br.com.luizera.screenmatch.principal;

import static java.util.stream.Collectors.*;

import br.com.luizera.screenmatch.model.DadosEpisodio;
import br.com.luizera.screenmatch.model.DadosSerie;
import br.com.luizera.screenmatch.model.DadosTemporada;
import br.com.luizera.screenmatch.model.Episodio;
import br.com.luizera.screenmatch.service.ConsumoApi;
import br.com.luizera.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t="; // padrão de maiusculas para constantes
    private final String API_KEY = "&apikey=187f3dd5";

    public void showMenu(){
        System.out.println("Por favor, digite o nome da série desejada para busca: ");
        var serieName = scanner.nextLine();
        consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados(ENDERECO + serieName.replaceAll(" ","+") + API_KEY); // substitui o  espaço " " por "+" na URL
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);

        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++){ // laço 'for' para mostrar todos os episódios de todas as temporadas

            var jsonTemporada = consumoApi.obterDados(ENDERECO + serieName.replaceAll(" ","+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);

            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
            List<DadosEpisodio> episodiosTemporada  = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporada.size(); j ++){
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));


//        Exemplo de Stream
//        List<Integer> numbers = Arrays.asList(5, 10, 2, 40, 33, 9, 12);
//
//        numbers.stream().sorted().forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(
            toList());

        // Uso da função Peek para entender o que acontece em cada linha do stream()
        System.out.println("TOP 10 EPISODIOS DA SERIE " + serieName.toUpperCase());
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filto(N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenação: " + e))
                .limit(10)
                .peek(e -> System.out.println("Limitando em 10 itens " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Passando tudo para UPPER CASE" + e ))
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream().flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numero(), d))).collect(
            Collectors.toList());
//
//        episodios.forEach(System.out::println);
//        System.out.println("A partir de que ano você deseja ver os episódios?");
//
//        var ano = scanner.nextInt();
//        scanner.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
//
//        episodios.stream()
//            .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//            .forEach(e -> System.out.print("Temporada: " + e.getTemporada() +
//                " Episódio: " + e.getTitulo() +
//                "Data de lançamento: " + e.getDataLancamento().format(formatter)));


        // Avaliações por temporada - pegando apenas os episódios que foram avaliados (avaliacao != 0)
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
            .filter(e ->  e.getAvaliacao() > 0.0)
            .collect(groupingBy(Episodio::getTemporada,
                averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacoesPorTemporada);


    }
}
