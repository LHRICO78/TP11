package lml.snir.gestionpersonne.metier.sort;

import java.util.Comparator;

public class InsertionOptimisedSort extends Sort {

    @Override
    public long  sort(Comparable[] values) {
        this.start();
        for (int passage = 1; passage < values.length; passage++) {
            for (int i = passage - 1; i >= 0; i--) {
                if (values[i].compareTo(values[i+1]) > 0) {
                    Comparable tmp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = tmp;
                } else {
                    break;
                }
            }
        }
        return this.stop();
    }

    @Override
    public long  sort(Object[] values, Comparator comparator) {
        this.start();
        for (int passage = 1; passage < values.length; passage++) {
            for (int i = passage - 1; i >= 0; i--) {
                if (comparator.compare(values[i], values[i+1]) > 0) {
                    Object tmp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = tmp;
                }  else {
                    break;
                }
            }
        }
        return this.stop();
    }
}
