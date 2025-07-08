function maxEvents(events: number[][]): number {
    const g: Map<number, number[]> = new Map();
    let l = Infinity,
        r = 0;
    for (const [s, e] of events) {
        if (!g.has(s)) g.set(s, []);
        g.get(s)!.push(e);
        l = Math.min(l, s);
        r = Math.max(r, e);
    }

    const pq = new MinPriorityQueue<number>();
    let ans = 0;
    for (let s = l; s <= r; s++) {
        while (!pq.isEmpty() && pq.front() < s) {
            pq.dequeue();
        }
        for (const e of g.get(s) || []) {
            pq.enqueue(e);
        }
        if (!pq.isEmpty()) {
            pq.dequeue();
            ans++;
        }
    }
    return ans;
}
