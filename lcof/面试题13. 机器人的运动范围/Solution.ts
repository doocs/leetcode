function movingCount(m: number, n: number, k: number): number {
    const vis: boolean[] = Array(m * n).fill(false);
    const f = (x: number): number => {
        return ((x / 10) | 0) + (x % 10);
    };
    const dfs = (i: number, j: number): number => {
        if (i >= m || j >= n || vis[i * n + j] || f(i) + f(j) > k) {
            return 0;
        }
        vis[i * n + j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    };
    return dfs(0, 0);
}
