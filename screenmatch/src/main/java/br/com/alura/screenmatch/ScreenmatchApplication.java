package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite a série que deseja fazer a busca:");
		var nomeSerie = leitura.nextLine();
		var enderenco = "https://www.omdbapi.com/?t=" + nomeSerie + "&apikey=6585022c";
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados(enderenco);

		ConverteDados conversor = new ConverteDados();
		DadosSeries dados = conversor.obterdados(json, DadosSeries.class);
		System.out.println(dados);

		System.out.println("Temporada: ");
		var temporada = leitura.nextLine();
		System.out.println("Episodio: ");
		var episodio = leitura.nextLine();
		enderenco = "https://www.omdbapi.com/?t=" + nomeSerie + "&season=" + temporada + "&episode=" + episodio + "&apikey=6585022c";
		json = consumoApi.obterDados(enderenco);
		DadosEpisodio dadosEpisodio = conversor.obterdados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			enderenco = "https://www.omdbapi.com/?t=" + nomeSerie.replace(" ", "+") + "&season=" + i + "&apikey=6585022c";
			json = consumoApi.obterDados(enderenco);
			DadosTemporada dadosTemporada = conversor.obterdados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}
}
