function minCost(m: number, n: number, penalty: number[][]): number {
    const dist = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => [Infinity, Infinity]),
    );
    dist[0][0][1] = 1;

    const pq = new MinPriorityQueue<number[]>(x => x[0]);
    pq.enqueue([1, 0, 0, 1]);

    const dirs = [
        [-1, 0],
        [0, 1],
        [0, -1],
        [1, 0],
    ];

    while (!pq.isEmpty()) {
        const [d, i, j, k] = pq.dequeue();

        if (i === m - 1 && j === n - 1) {
            return d;
        }
        if (d > dist[i][j][k]) {
            continue;
        }

        const p = penalty[i][j];

        let nd = d + p;
        if (nd < dist[i][j][k ^ 1]) {
            dist[i][j][k ^ 1] = nd;
            pq.enqueue([nd, i, j, k ^ 1]);
        }

        for (let idx = 0; idx < 4; idx++) {
            const [dx, dy] = dirs[idx];
            const x = i + dx;
            const y = j + dy;
            if (0 <= x && x < m && 0 <= y && y < n) {
                nd = d + (x + 1) * (y + 1) + ((idx & 1) ^ k) * p;
                if (nd < dist[x][y][k ^ 1]) {
                    dist[x][y][k ^ 1] = nd;
                    pq.enqueue([nd, x, y, k ^ 1]);
                }
            }
        }
    }

    return -1;
}
