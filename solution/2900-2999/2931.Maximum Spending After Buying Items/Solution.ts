function maxSpending(values: number[][]): number {
    const m = values.length;
    const n = values[0].length;
    const pq = new PriorityQueue({ compare: (a, b) => a[0] - b[0] });
    for (let i = 0; i < m; ++i) {
        pq.enqueue([values[i][n - 1], i, n - 1]);
    }

    let ans = 0;
    for (let d = 1; !pq.isEmpty(); ++d) {
        const [v, i, j] = pq.dequeue()!;
        ans += v * d;
        if (j > 0) {
            pq.enqueue([values[i][j - 1], i, j - 1]);
        }
    }
    return ans;
}
