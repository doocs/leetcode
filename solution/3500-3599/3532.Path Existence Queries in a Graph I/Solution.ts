function pathExistenceQueries(
    n: number,
    nums: number[],
    maxDiff: number,
    queries: number[][],
): boolean[] {
    const g: number[] = Array(n).fill(0);
    let cnt = 0;

    for (let i = 1; i < n; ++i) {
        if (nums[i] - nums[i - 1] > maxDiff) {
            ++cnt;
        }
        g[i] = cnt;
    }

    return queries.map(([u, v]) => g[u] === g[v]);
}
