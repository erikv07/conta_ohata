import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Conta {
	private int saldo; // Atributo inteiro que armazena o saldo da conta.
	private boolean isEspecial; // Atributo booleano que indica se o usuário é especial.

	private boolean isSaqueRealizado = false; // Atributo booleano para identificar se o saque foi realizado ou não.

	/**
	 * Método que inicializa o saldo da conta do cliente e define seu tipo.
	 *
	 * @param saldo Valor inicial do saldo da conta.
	 */
	@Given("Um cliente especial com saldo atual de {int} reais")
	public void um_cliente_especial_com_saldo_atual_de_reais(Integer saldo) {
	    this.saldo = saldo;
	    this.isEspecial = true;
	}

	/**
	 * Método que realiza um saque através do método realizarSaque.
	 *
	 * @param saque Valor a ser sacado.
	 */
	@When("for solicitado um saque no valor de {int} reais")
	public void for_solicitado_um_saque_no_valor_de_reais(int saque) {
	    if (saque > 0 && (this.isEspecial || this.saldo >= saque)) {
	        this.saldo -= saque;
	        this.isSaqueRealizado = true;
	    } else {
	        System.out.println("Saldo Insuficiente.");
	    }
	}

	/**
	 * Método que verifica o saldo da conta após um saque.
	 *
	 * @param novoSaldo Novo valor do saldo da conta.
	 */
	@Then("deve efetuar o saque e atualizar o saldo da conta para {int} reais")
	public void deve_efetuar_o_saque_e_atualizar_o_saldo_da_conta_para_reais(int novoSaldo) {
	    assert this.saldo == novoSaldo;
	}

	/**
	 * Método que exibe mensagens caso o cliente seja especial ou o saldo seja negativo.
	 */
	@Then("check more outcomes")
	public void check_more_outcomes() {
	    if (this.isEspecial) {
	        System.out.println("Cliente especial.");
	    }
	    if (this.saldo < 0) {
	        System.out.println("Saldo negativo.");
	    }
	}

	/**
	 * Método que define o saldo inicial da conta do cliente comum.
	 *
	 * @param saldoInicial Saldo inicial da conta.
	 */
	@Given("Um cliente comum com saldo atual de {int} reais")
	public void um_cliente_comum_com_saldo_atual_de_reais(Integer saldoInicial) {
	    this.saldo = saldoInicial;
	    this.isSaqueRealizado = false;
	}

	/**
	 * Método que realiza um saque.
	 *
	 * @param saque Valor a ser sacado.
	 */
	@When("solicitar um saque de {int} reais")
	public void solicitar_um_saque_de_reais(int saque) {
	    if (saque > 0 && (this.isEspecial || this.saldo >= saque)) {
	        this.saldo -= saque;
	        this.isSaqueRealizado = true;
	    } else {
	        System.out.println("Saldo Insuficiente.");
	    }
	}

	/**
	 * Método que garante que um saque não foi efetuado.
	 */
	@Then("não deve efetuar o saque e deve retornar a mensagem Saldo Insuficiente")
	public void não_deve_efetuar_o_saque_e_deve_retornar_a_mensagem_saldo_insuficiente() {
	    assert !this.isSaqueRealizado;
	}

	

}
