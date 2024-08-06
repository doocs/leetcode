function minimumPushes(word) {
    const pq = new MaxPriorityQueue();
    const cnt = new Map();
    let [i, res] = [0, 0];

    for (const x of word) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    for (const [x, c] of cnt) {
        pq.enqueue(x, c);
    }

    while (!pq.isEmpty()) {
        const c = pq.dequeue().priority;
        res += c * (((i++ / 8) | 0) + 1);
    }

    return res;
}
