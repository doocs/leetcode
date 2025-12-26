function longestDiverseString(a: number, b: number, c: number): string {
    const pq = new PriorityQueue<number[]>((a, b) => b[1] - a[1]);

    if (a > 0) pq.enqueue(['a'.charCodeAt(0), a]);
    if (b > 0) pq.enqueue(['b'.charCodeAt(0), b]);
    if (c > 0) pq.enqueue(['c'.charCodeAt(0), c]);

    const sb: number[] = [];

    while (!pq.isEmpty()) {
        const cur = pq.dequeue();
        const n = sb.length;

        if (n >= 2 && sb[n - 1] === cur[0] && sb[n - 2] === cur[0]) {
            if (pq.isEmpty()) break;

            const next = pq.dequeue();
            sb.push(next[0]);

            if (next[1] > 1) {
                next[1]--;
                pq.enqueue(next);
            }
            pq.enqueue(cur);
        } else {
            sb.push(cur[0]);
            if (cur[1] > 1) {
                cur[1]--;
                pq.enqueue(cur);
            }
        }
    }

    return String.fromCharCode(...sb);
}
