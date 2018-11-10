package Main;

import Heap.Heap;
import Heap.TipoHeap;

/**
 *
 * @author rt
 */
public class Main {
    public static void main(String[] args) {
        Heap<Integer, Integer> a = new Heap<>(TipoHeap.minHeap);
        a.inserir(1, 1);
        System.out.println(a.removerDeCima());
    }
}
