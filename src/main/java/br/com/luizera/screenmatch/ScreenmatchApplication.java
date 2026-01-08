package br.com.luizera.screenmatch;

import br.com.luizera.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Principal principal = new Principal();
        principal.showMenu();


        // --TODO ESSE CONTEUDO FOI MIGRADO PARA A CLASSE 'PRINCIPAL' PARA QUE AQUI FIQUE MAIS LIMPO E CLARO
//        // instancia da classe ConsumoApi
//        var consumoApi = new ConsumoApi();
//        // API KEY = 187f3dd5
//        var json = consumoApi.obterDados("https://www.omdbapi.com/?t=peaky+blinders&apikey=187f3dd5");
//        var jsonSerie = consumoApi.obterDados("https://www.omdbapi.com/?t=peaky+blinders&apikey=187f3dd5&episode=2&season=1");
//
////        É importante separar a responsabiliade de consumir a API em uma classe separada
////        json = consumoApi.obterDados("consome qualquer url com json aqui");
//
//        System.out.println(json);
//
//        // instância da classe de converter dados
//        ConverteDados conversor = new ConverteDados();
//
//        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
//        DadosEpisodio dadosEpisodio = conversor.obterDados(jsonSerie, DadosEpisodio.class);

//        List<DadosTemporada> temporadas = new ArrayList<>();
//
//        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++){ // laço 'for' para mostrar todos os episódios de todas as temporadas
//
//            var jsonTemporada = consumoApi.obterDados("https://www.omdbapi.com/?t=peaky+blinders&apikey=187f3dd5&season=" + i);
//            DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
//
//            temporadas.add(dadosTemporada);
//        }
//            temporadas.forEach(System.out::println);



    }
}
