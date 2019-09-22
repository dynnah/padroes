package model;

import java.util.ArrayList;
import java.util.Collection;

public class Cliente {
    private String nome;
    private Collection<Aluguel> fitasAlugadas = new ArrayList<Aluguel>();

    public Cliente(String nome) {
        this.nome = nome;
    }
    
    public Cliente() {
	}

    public String getNome() {
        return nome;
    }
    
	public void setNome(String nome) {
		this.nome = nome;
	}

    public void adicionaAluguel(Aluguel aluguel) {
        fitasAlugadas.add(aluguel);
    }
    
	public Collection<Aluguel> getFitasAlugadas() {
		return fitasAlugadas;
	}

	public void setFitasAlugadas(Collection<Aluguel> fitasAlugadas) {
		this.fitasAlugadas = fitasAlugadas;
	}
	
	public String extrato() {
        final String fimDeLinha = System.getProperty("line.separator");
        double valorTotal = 0.0;
        int pontosDeAlugadorFrequente = 0;
        String resultado = "Registro de Alugueis de " + getNome() + fimDeLinha;

        for (Aluguel f : fitasAlugadas) {

            double valorCorrente = 0.0;
            Aluguel cada = f;

            // determina valores para cada linha
            // switch com enum
            switch (cada.getFita().getCodigoDePreco()) {
            case normal:
                valorCorrente += 2;
                if (cada.getDiasAlugada() > 2) {
                    valorCorrente += (cada.getDiasAlugada() - 2) * 1.5;
                }
                break;
            case lancamento:
                valorCorrente += cada.getDiasAlugada() * 3;
                break;
            case infantil:
                valorCorrente += 1.5;
                if (cada.getDiasAlugada() > 3) {
                    valorCorrente += (cada.getDiasAlugada() - 3) * 1.5;
                }
                break;
            } // switch
            // trata de pontos de alugador frequente
            pontosDeAlugadorFrequente++;
            // adiciona bonus para aluguel de um lançamento por pelo menos 2
            // dias
            if (cada.getFita().getCodigoDePreco() == Tipo.lancamento
                && cada.getDiasAlugada() > 1) {
                pontosDeAlugadorFrequente++;
            }

            // mostra valores para este aluguel
            resultado += "\t" + cada.getFita().getTitulo() + "\t"
                         + valorCorrente + fimDeLinha;
            valorTotal += valorCorrente;
        } // while
        // adiciona rodapé
        resultado += "Valor total devido: " + valorTotal + fimDeLinha;
        resultado += "Voce acumulou " + pontosDeAlugadorFrequente
                     + " pontos de alugador frequente";
        return resultado;
    }
}