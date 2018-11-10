package Heap;

/**
 *
 * @author FlowOverStack
 */
public class Heap<K extends Comparable<K>, V> {

    private No<K, V> vetor[];
    private int capacidade;
    private int tamanho;
    private final TipoHeap tipo;

    public Heap(TipoHeap tipo) {
        this.capacidade = 2;
        this.tamanho = 0;
        this.vetor = (No<K, V>[]) new Object[capacidade];
        this.tipo = tipo;
    }

    void aumentarCapacidade() {
        if (tamanho == capacidade) {
            capacidade *= 2;
            No<K, V> novoVetor[] = (No<K, V>[]) new Object[capacidade];
            System.arraycopy(vetor, 0, novoVetor, 0, tamanho);
            vetor = novoVetor;
        }
    }

    public void inserir(K chave, V value) {
        aumentarCapacidade();
        No<K, V> no = new No<>(chave, value);
        vetor[tamanho] = no;
        borbulhamentoCima(tamanho);
        tamanho++;
    }

    public void borbulhamentoCima(int indice) {
        int indicePai = (indice + 1) / 2 - 1;
        No<K, V> noAtual = vetor[indice];

        if (tipo == TipoHeap.minHeap) {
            while ((indice > 0) && (vetor[indicePai].chave.compareTo(noAtual.chave) > 0)) {
                vetor[indice] = vetor[indicePai];
                indice = indicePai;
                indicePai = (indice + 1) / 2 - 1;
            }
            vetor[indice] = noAtual;
        } else {
            while ((indice > 0) && (vetor[indicePai].chave.compareTo(noAtual.chave) < 0)) {
                vetor[indice] = vetor[indicePai];
                indice = indicePai;
                indicePai = (indice + 1) / 2 - 1;
            }
            vetor[indice] = noAtual;
        }
    }

    public V removerDeCima() {
        V valor = vetor[0].valor;
        vetor[0] = vetor[tamanho - 1];
        vetor[tamanho - 1] = null;
        tamanho--;

        borbulhamentoBaixo(0);
        return valor;
    }

    private void borbulhamentoBaixo(int indice) {
        int menorFilho;
        int filhoDireito, filhoEsquerdo;
        No<K, V> infoTemp;
        infoTemp = vetor[indice];
        if (tipo == TipoHeap.minHeap) {   
            while ((indice + 1) * 2 <= tamanho) {
                filhoEsquerdo = 2 * (indice + 1) - 1;
                filhoDireito = filhoEsquerdo + 1;
                if ((filhoDireito <= tamanho - 1) && (vetor[filhoDireito].chave.compareTo(vetor[filhoEsquerdo].chave) < 0)) {
                    menorFilho = filhoDireito;
                } else {
                    menorFilho = filhoEsquerdo;
                }

                if (infoTemp.chave.compareTo(vetor[menorFilho].chave) < 0) {
                    break;
                }
                vetor[indice] = vetor[menorFilho];
                indice = menorFilho;
            }
            vetor[indice] = infoTemp;
        }else {
            while ((indice + 1) * 2 <= tamanho) {
                filhoEsquerdo = 2 * (indice + 1) - 1;
                filhoDireito = filhoEsquerdo + 1;
                if ((filhoDireito <= tamanho - 1) && (vetor[filhoDireito].chave.compareTo(vetor[filhoEsquerdo].chave) > 0)) {
                    menorFilho = filhoDireito;
                } else {
                    menorFilho = filhoEsquerdo;
                }

                if (infoTemp.chave.compareTo(vetor[menorFilho].chave) > 0) {
                    break;
                }
                vetor[indice] = vetor[menorFilho];
                indice = menorFilho;
            }
            vetor[indice] = infoTemp;
        }
    }
    
    public int tamanho() {
        return this.tamanho;
    }
}
