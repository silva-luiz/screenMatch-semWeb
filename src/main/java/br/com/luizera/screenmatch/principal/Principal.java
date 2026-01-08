package br.com.luizera.screenmatch.principal;

import br.com.luizera.screenmatch.model.DadosEpisodio;
import br.com.luizera.screenmatch.model.DadosSerie;
import br.com.luizera.screenmatch.model.DadosTemporada;
import br.com.luizera.screenmatch.service.ConsumoApi;
import br.com.luizera.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
//
//        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodiosTemporada  = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j ++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
