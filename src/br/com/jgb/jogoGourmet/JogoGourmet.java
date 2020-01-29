package br.com.jgb.jogoGourmet;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Inicio do jogo
 * 
 * @author Jandrei
 *
 */
public class JogoGourmet {

	private static int PRATO_NAO_ENCONTRADO_NO_TIPO = 99;
	private String nomeJogo = "Jogo Gourmet";
	private String[] botoesSimNao = { "Sim", "N�o" };
	private String[] botaoOk = { "OK" };

	private List<TipoPrato> tipos = new ArrayList<TipoPrato>();

	private TipoPrato ultimoTipo;
	private Prato ultimoPrato;

	public static void main(String[] args) {
		new JogoGourmet();
	}

	public JogoGourmet() {

		// pratos iniciais
		Prato lasanha = new Prato("Lasanha");
		Prato boloChocolate = new Prato("Bolo de Chocolate");

		// tipos iniciais
		tipos.add(new TipoPrato("massa", lasanha));
		tipos.add(new TipoPrato("bolo", boloChocolate));

		inicializacao();
	}

	/**
	 * Inc�o das perguntas do jogo
	 */
	public void inicializacao() {

		perguntarPensarPrato();
		perguntarPratoQuePensei();
		inicializacao();
	}

	/**
	 * Pede para pensar em um prato
	 */
	private void perguntarPensarPrato() {

		if (JOptionPane.showOptionDialog(null, "Pense em um prato que gosta", nomeJogo, JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, botaoOk, botaoOk[0]) == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Pergunta o nome do prato que pensei
	 * 
	 * @return
	 */
	private void perguntarPratoQuePensei() {

		// percorre tipos de pratos para descobrir qual o usu�rio pensou
		int resp = -1;
		boolean encontrou = false;
		for (TipoPrato tipo : tipos) {

			resp = perguntarPratoQuePensei(tipo);

			if (resp == JOptionPane.CLOSED_OPTION) {

				System.exit(0);

			} else if (resp == JOptionPane.NO_OPTION) {

				continue;

			} else if (resp == JOptionPane.YES_OPTION) {

				JOptionPane.showMessageDialog(null, "Acertei de novo!");
				encontrou = true;
				break;

			} else if (resp == PRATO_NAO_ENCONTRADO_NO_TIPO) {

				break;
			}
		}

		// terminou os platos e n�o acertei qual �, pergunta o nome
		if (encontrou == false) {
			novoPrato();
		}
	}

	/**
	 * Pergunta o nome do prato que pensei
	 * 
	 * @return
	 */
	private int perguntarPratoQuePensei(TipoPrato tipo) {

		ultimoTipo = tipo;

		// se o tipo � o correto precisa percorrer os pratos
		int resp = JOptionPane.showOptionDialog(null, "O prato que voc� pensou � " + tipo.getNome() + "?", "Confirme",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoesSimNao, botoesSimNao[0]);

		if (resp == JOptionPane.YES_OPTION) {

			// verifica os pratos do tipo, sen�o os tipos dele
			if (tipo.getPratos().size() > 0) {

				for (Prato prato : tipo.getPratos()) {

					ultimoPrato = prato;
					resp = perguntarPratoQuePensei(prato);

					if (resp == JOptionPane.YES_OPTION) {
						return resp;
					} else {
						continue;
					}
				}
			}

			// se tem subtipos, precisa verificar
			if (tipo.getTipoPrato() != null) {

				resp = perguntarPratoQuePensei(tipo.getTipoPrato());

				if (resp == JOptionPane.YES_OPTION) {
					return resp;
				}
			}

			// se � um tipo confirmado, mas n�o encontrou o prato, n�o pode pesquisar nos
			// outros tipos
			resp = PRATO_NAO_ENCONTRADO_NO_TIPO;
		}

		return resp;
	}

	/**
	 * Pergunta se � o prato que pensou
	 * 
	 * @param prato
	 * @return
	 */
	private int perguntarPratoQuePensei(Prato prato) {

		return JOptionPane.showOptionDialog(null, "O prato que voc� pensou � " + prato.getNome() + "?", "Confirme",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoesSimNao, botoesSimNao[0]);
	}

	/**
	 * Adi��o do novo prato
	 */
	private void novoPrato() {

		String nome = JOptionPane.showInputDialog(null, "Qual prato voc� pensou?");

		String tipo = JOptionPane.showInputDialog(null, nome + " � _____ mas " + ultimoPrato.getNome() + " n�o.",
				"Complete", JOptionPane.PLAIN_MESSAGE);

		Prato novoPrato = new Prato(nome);
		TipoPrato novoTipo = new TipoPrato(tipo, novoPrato);

		ultimoTipo.setTipoPrato(novoTipo);

		System.out.println(tipos);
	}
}