function longestDiverseString(a: number, b: number, c: number): string {
    let res = '';
    let prev = { ch: '', c: 0 };
    let last = { ch: '', c: 0 };
    const pq = new MaxPriorityQueue({ priority: ({ c }) => c });

    pq.enqueue({ ch: 'a', c: a });
    pq.enqueue({ ch: 'b', c: b });
    pq.enqueue({ ch: 'c', c });

    while (pq.size()) {
        const item = pq.dequeue().element;
        let c = item.c < prev.c ? 1 : 2;

        if (prev.c) pq.enqueue(prev);
        if (last.ch !== item.ch && item.c) last = { ...item, c: 0 };

        while (c-- && item.c && last.c++ < 2) {
            item.c--;
            res += item.ch;
        }
        prev = item;
    }

    return res;
}
