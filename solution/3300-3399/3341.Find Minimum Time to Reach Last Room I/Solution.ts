function minTimeToReach(moveTime: number[][]): number {
    const n = moveTime.length;
    const m = moveTime[0].length;
    const dist = Array.from({ length: n }, () => Array(m).fill(Infinity));
    dist[0][0] = 0;
    type Node = [number, number, number];
    const pq = new PriorityQueue<Node>((a, b) => a[0] - b[0]);
    pq.enqueue([0, 0, 0]);
    const dirs = [-1, 0, 1, 0, -1];
    while (!pq.isEmpty()) {
        const [d, i, j] = pq.dequeue();
        if (d > dist[i][j]) continue;
        if (i === n - 1 && j === m - 1) return d;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < m) {
                const t = Math.max(moveTime[x][y], d) + 1;
                if (t < dist[x][y]) {
                    dist[x][y] = t;
                    pq.enqueue([t, x, y]);
                }
            }
        }
    }
    return -1;
}
