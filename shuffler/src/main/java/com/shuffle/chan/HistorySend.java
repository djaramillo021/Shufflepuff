package com.shuffle.chan;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel Krawisz on 4/25/16.
 */
public class HistorySend<X> implements Send<X> {
    private final Send<X> chan;
    private final LinkedList<X> history = new LinkedList<>();

    public HistorySend(Send<X> chan) {
        this.chan = chan;
    }

    @Override
    public boolean send(X x) throws InterruptedException, IOException {
        boolean sent = chan.send(x);

        if (sent) {
            history.addLast(x);
        }

        return sent;
    }

    @Override
    public void close() {
        chan.close();
    }

    public List<X> history() {
        List<X> h = new LinkedList<X>();
        h.addAll(history);
        return h;
    }

    public X last() {
        return history.getLast();
    }
}
