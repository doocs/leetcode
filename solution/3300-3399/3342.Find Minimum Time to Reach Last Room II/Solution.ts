function minTimeToReach(moveTime: number[][]): number {
    const [n, m] = [moveTime.length, moveTime[0].length];
    const dist: number[][] = Array.from({ length: n }, () => Array(m).fill(Infinity));
    dist[0][0] = 0;
    const pq = new PriorityQueue({ compare: (a, b) => a[0] - b[0] });
    pq.enqueue([0, 0, 0]);
    const dirs = [-1, 0, 1, 0, -1];
    while (1) {
        const [d, i, j] = pq.dequeue();
        if (i === n - 1 && j === m - 1) {
            return d;
        }
        if (d > dist[i][j]) {
            continue;
        }
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < n && y >= 0 && y < m) {
                const t = Math.max(moveTime[x][y], dist[i][j]) + ((i + j) % 2) + 1;
                if (dist[x][y] > t) {
                    dist[x][y] = t;
                    pq.enqueue([t, x, y]);
                }
            }
        }
    }
}
