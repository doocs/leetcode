function findCircleNum(isConnected: number[][]): number {
    const n = isConnected.length;
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number) => {
        vis[i] = true;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && isConnected[i][j]) {
                dfs(j);
            }
        }
    };
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (!vis[i]) {
            dfs(i);
            ++ans;
        }
    }
    return ans;
}
