function permute(n: number): number[][] {
    const ans: number[][] = [];
    const vis: boolean[] = Array(n).fill(false);
    const t: number[] = Array(n).fill(0);
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        for (let j = 1; j <= n; ++j) {
            if (!vis[j] && (i === 0 || t[i - 1] % 2 !== j % 2)) {
                vis[j] = true;
                t[i] = j;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    };
    dfs(0);
    return ans;
}
