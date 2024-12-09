package it.unibo.es2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LogicsImpl implements Logics {

    // In this implementation, " " is false, "*" is true.
    private final boolean[][] labels;

    public LogicsImpl(final int size) {
        labels = new boolean[size][size];
    }

    @Override
    public String hit(final Pair<Integer, Integer> buttonPosition) {

        this.labels[buttonPosition.getX()][buttonPosition
                .getY()] = !labels[buttonPosition.getX()][buttonPosition.getY()];
        return this.labels[buttonPosition.getX()][buttonPosition.getY()] ? "*" : " ";
    }

    @Override
    public boolean shouldExit() {
        if (checkLines(labels) || checkLines(getColumns())) {
            return true;
        }
        return false;
    }

    private boolean checkLines(boolean[][] lines) {
        for (boolean[] line : lines) {
            if (checkLine(line)) {
                return true;
            }
        }
        return false;
    }

    private boolean[][] getColumns() {
        boolean[][] columns = new boolean[size()][size()];

        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                columns[j][i] = labels[i][j];
            }
        }
        return columns;
    }

    private boolean checkLine(boolean[] line) {
        return booleanArrayStream(line)
                .allMatch(b -> b == true);
    }

    /**
     * <p>
     * Returns a stream from a boolean (primitive type) array.
     * </p>
     * <p>
     * This is needed because there is no <code>Arrays.stream(boolean[] bs)</code>
     * </p>
     * <p>
     * and <code>List.of(boolean[] bs)</code> returns a
     * <code>List of boolean[]</code> instead of
     * <code>List of boolean</code>.
     * </p>
     * 
     * @param line
     * @return
     */
    private Stream<Boolean> booleanArrayStream(boolean[] line) {
        return IntStream.range(0, line.length)
                .mapToObj(x -> line[x]);
    }

    private int size() {
        return labels.length;
    }
}
