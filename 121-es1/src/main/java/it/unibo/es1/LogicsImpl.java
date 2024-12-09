package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics {

    private final List<Integer> values;

    public LogicsImpl(final int size) {
        checkSize(size);

        this.values = new ArrayList<>(IntStream.range(0, size - 1).boxed().toList());
        // this.values = new ArrayList<>(Collections.nCopies(size, 0));
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
        return this.values.stream()
                .map(v -> v != this.values.size())
                .toList();
    }

    @Override
    public int hit(final int elem) {
        checkIndex(elem);

        if (this.values.get(elem) < this.values.size()) {
            this.values.set(elem, this.values.get(elem) + 1);
        }

        return this.values.get(elem);
    }

    @Override
    public String result() {
        return "<<"
                .concat(this.values.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("|")))
                .concat(">>");
    }

    @Override
    public boolean toQuit() {
        // Returns true if all elements are equal to the first.
        // The list is guaranteed to have at least 1 element.
        return this.values.stream()
                .allMatch(v -> v == this.values.getFirst());
    }

    private void checkSize(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size " + size + " is not valid.");
        }
    }

    private void checkIndex(final int elem) {
        if (elem >= this.values.size()) {
            throw new IllegalArgumentException("There is no button in the " + elem + " position.");
        }
    }
}
