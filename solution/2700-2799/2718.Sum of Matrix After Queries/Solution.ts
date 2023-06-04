function matrixSumQueries(n: number, queries: number[][]): number {
    const row: Set<number> = new Set();
    const col: Set<number> = new Set();
    let ans = 0;
    queries.reverse();
    for (const [t, i, v] of queries) {
        if (t === 0) {
            if (!row.has(i)) {
                ans += v * (n - col.size);
                row.add(i);
            }
        } else {
            if (!col.has(i)) {
                ans += v * (n - row.size);
                col.add(i);
            }
        }
    }
    return ans;
}
