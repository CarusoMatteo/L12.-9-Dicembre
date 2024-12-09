package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LogicsImpl implements Logics {

    private final List<Integer> values; // = new ArrayList<>();

    public LogicsImpl(final int size) {
        this.values = new ArrayList<>(Collections.nCopies(size, 0));
        /*
         * for (int i = 0; i < size; i++) {
         * values.add(i);
         * }
         */
    }

    @Override
    public int size() {
        return this.values.size();
    }

    @Override
    public List<Integer> values() {
        return List.copyOf(this.values);
    }

    @Override
    public List<Boolean> enablings() {
        final List<Boolean> list = new ArrayList<>(this.values.size());

        for (int i = 0; i < this.values.size(); i++) {
            list.add(enabled(i));
        }
        return list;
    }

    @Override
    public int hit(final int elem) {
        checkInput(elem);

        if (this.values.get(elem) < this.values.size()) {
            this.values.set(elem, this.values.get(elem) + 1);
        }

        return this.values.get(elem);
    }

    @Override
    public String result() {
        String output = "<<";
        final String END = ">>";
        final String SEPARATOR = "|";

        final var it = this.values.iterator();
        while (it.hasNext()) {

            if (it.hasNext()) {
                output = output.concat(SEPARATOR);
            }
        }

        return output.concat(END);
    }

    @Override
    public boolean toQuit() {
        Optional<Integer> prec = Optional.empty();

        for (final Integer value : this.values) {
            if (prec.isEmpty()) {
                prec = Optional.of(value);
            } else if (!prec.get().equals(value)) {
                return false;
            }
        }

        return true;
    }

    private boolean enabled(final int elem) {
        checkInput(elem);

        if (this.values.get(elem) == this.values.size()) {
            return false;
        }
        return true;
    }

    private void checkInput(final int elem) {
        if (elem >= this.values.size()) {
            throw new IllegalArgumentException("There is no button in the " + elem + " position.");
        }
    }
}
