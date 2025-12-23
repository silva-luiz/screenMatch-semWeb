package br.com.luizera.screenmatch;

import br.com.luizera.screenmatch.model.DadosSerie;
import br.com.luizera.screenmatch.service.ConsumoApi;
import br.com.luizera.screenmatch.service.ConverteDados;
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
        // instancia da classe ConsumoApi
        var consumoApi = new ConsumoApi();
        // API KEY = 187f3dd5
        var json = consumoApi.obterDados("https://www.omdbapi.com/?t=peaky+blinders&apikey=187f3dd5");
//        É importante separar a responsabiliade de consumir a API em uma classe separada
//        json = consumoApi.obterDados("consome qualquer url com json aqui");

        System.out.println(json);

        ConverteDados conversor = new ConverteDados();

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

    }
}
