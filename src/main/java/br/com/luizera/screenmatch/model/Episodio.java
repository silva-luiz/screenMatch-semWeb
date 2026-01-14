package br.com.luizera.screenmatch.model;

import java.time.LocalDate;

public class Episodio {
  private Integer temporada;
  private String titulo;
  private Integer numeroEpisodio;
  private Double  avaliacao;
  private LocalDate dataLancamento;

  public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
    this.temporada = numeroTemporada;
    this.titulo = dadosEpisodio.titulo();
    this.numeroEpisodio = dadosEpisodio.numero();
    this.avaliacao = dadosEpisodio.avaliacao().equals("N/A")
        ? 0
        : Double.parseDouble(dadosEpisodio.avaliacao());

    this.dataLancamento = dadosEpisodio.dataLancamento().equals("N/A")
        ? null
        : LocalDate.parse(dadosEpisodio.dataLancamento());
  }


  public Integer getTemporada() {
    return temporada;
  }

  public String getTitulo() {
    return titulo;
  }

  public Integer getNumeroEpisodio() {
    return numeroEpisodio;
  }

  public Double getAvaliacao() {
    return avaliacao;
  }

  public LocalDate getDataLancamento() {
    return dataLancamento;
  }

  public void setTemporada(Integer temporada) {
    this.temporada = temporada;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setNumeroEpisodio(Integer numeroEpisodio) {
    this.numeroEpisodio = numeroEpisodio;
  }

  public void setAvaliacao(Double avaliacao) {
    this.avaliacao = avaliacao;
  }

  public void setDataLancamento(LocalDate dataLancamento) {
    this.dataLancamento = dataLancamento;
  }

  @Override
  public String toString() {
    return "temporada=" + temporada +
        ", titulo='" + titulo + '\'' +
        ", numeroEpisodio=" + numeroEpisodio +
        ", avaliacao=" + avaliacao +
        ", dataLancamento=" + dataLancamento;
  }
}
