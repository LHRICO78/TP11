package lml.snir.gestionpersonne.metier.sort;

import java.util.Comparator;

public class BubbleSort extends Sort {

    @Override
    public long  sort(Comparable[] values) {
        this.start();
        for (int i = 0; i < values.length; i++) {
            for (int j = 1; j < values.length; j++) {
                if (values[j - 1].compareTo(values[j]) > 0) {
                    Comparable tmp = values[j - 1];
                    values[j - 1] = values[j];
                    values[j] = tmp;
                }
            }
        }
        return this.stop();
    }

    @Override
    public long  sort(Object[] values, Comparator comparator) {
        this.start();
        for (int i = 0; i < values.length; i++) {
            for (int j = 1; j < values.length; j++) {
                if (comparator.compare(values[j - 1], values[j]) > 0) {
                    Object tmp = values[j - 1];
                    values[j - 1] = values[j];
                    values[j] = tmp;
                }
            }
        }
        return this.stop();
    }

}
