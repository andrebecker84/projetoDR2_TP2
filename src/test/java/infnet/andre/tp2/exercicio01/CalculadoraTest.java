package infnet.andre.tp2.exercicio01;

import net.jqwik.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe CalculadoraDepois.
 *
 * Exercita o princípio de nomeação clara de variáveis e métodos,
 * verificando que ambas as implementações (verbosa e concisa)
 * produzem resultados corretos.
 *
 * @author André Becker
 * @version 1.0.0
 */
class CalculadoraTest {

    @Test
    @DisplayName("Deve calcular dobro da soma quando valores são positivos")
    void deveCalcularDobroDaSomaQuandoValoresSaoPositivos() {
        // Arrange
        int primeiroNumero = 3;
        int segundoNumero = 5;
        int resultadoEsperado = 16; // (3 + 5) * 2 = 16

        // Act
        int resultado = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultado, is(equalTo(resultadoEsperado)));
    }

    @Test
    @DisplayName("Deve calcular dobro da soma quando valores são negativos")
    void deveCalcularDobroDaSomaQuandoValoresSaoNegativos() {
        // Arrange
        int primeiroNumero = -10;
        int segundoNumero = -5;
        int resultadoEsperado = -30; // (-10 + -5) * 2 = -30

        // Act
        int resultado = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultado, is(equalTo(resultadoEsperado)));
    }

    @Test
    @DisplayName("Deve retornar zero quando ambos os valores são zero")
    void deveRetornarZeroQuandoAmbosValoresSaoZero() {
        // Arrange
        int primeiroNumero = 0;
        int segundoNumero = 0;
        int resultadoEsperado = 0;

        // Act
        int resultado = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultado, is(equalTo(resultadoEsperado)));
    }

    @Test
    @DisplayName("Deve calcular dobro da soma quando valores são mistos (positivo e negativo)")
    void deveCalcularDobroDaSomaQuandoValoresSaoMistos() {
        // Arrange
        int primeiroNumero = 10;
        int segundoNumero = -3;
        int resultadoEsperado = 14; // (10 + -3) * 2 = 14

        // Act
        int resultado = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultado, is(equalTo(resultadoEsperado)));
    }

    @Test
    @DisplayName("Deve calcular dobro da soma concisa quando valores são positivos")
    void deveCalcularDobroDaSomaConcisaQuandoValoresSaoPositivos() {
        // Arrange
        int primeiroNumero = 10;
        int segundoNumero = 20;
        int resultadoEsperado = 60; // (10 + 20) * 2 = 60

        // Act
        int resultado = CalculadoraDepois.calcularDobroDaSomaConcisa(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultado, is(equalTo(resultadoEsperado)));
    }

    @Test
    @DisplayName("Deve retornar mesmo resultado em ambas as implementações")
    void deveRetornarMesmoResultadoEmAmbasImplementacoes() {
        // Arrange
        int primeiroNumero = 7;
        int segundoNumero = 13;

        // Act
        int resultadoVerboso = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);
        int resultadoConciso = CalculadoraDepois.calcularDobroDaSomaConcisa(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultadoVerboso, is(equalTo(resultadoConciso)));
    }

    @Test
    @DisplayName("Deve calcular corretamente com valores grandes")
    void deveCalcularCorretamenteComValoresGrandes() {
        // Arrange
        int primeiroNumero = 1000000;
        int segundoNumero = 2000000;
        int resultadoEsperado = 6000000; // (1000000 + 2000000) * 2

        // Act
        int resultado = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultado, is(equalTo(resultadoEsperado)));
    }

    @Property
    void propriedadeDobroDaSomaDeveSerIgualASomaMultiplicadaPorDois(
            @ForAll int primeiroNumero,
            @ForAll int segundoNumero) {

        // Arrange & Act
        int resultado = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);
        int esperado = (primeiroNumero + segundoNumero) * 2;

        // Assert
        assertThat(resultado, is(equalTo(esperado)));
    }

    @Property
    void propriedadeAmbasImplementacoesDevemRetornarResultadoIdentico(
            @ForAll int primeiroNumero,
            @ForAll int segundoNumero) {

        // Act
        int resultadoVerboso = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);
        int resultadoConciso = CalculadoraDepois.calcularDobroDaSomaConcisa(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultadoVerboso, is(equalTo(resultadoConciso)));
    }

    @Property
    void propriedadeResultadoDeveSerParParaQuaisquerValores(
            @ForAll int primeiroNumero,
            @ForAll int segundoNumero) {

        // Act
        int resultado = CalculadoraDepois.calcularDobroDaSoma(primeiroNumero, segundoNumero);

        // Assert
        assertThat(resultado % 2, is(equalTo(0)));
    }
}
