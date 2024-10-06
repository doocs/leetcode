function remainingMethods(n: number, k: number, invocations: number[][]): number[] {
    const suspicious: boolean[] = Array(n).fill(false);
    const vis: boolean[] = Array(n).fill(false);
    const f: number[][] = Array.from({ length: n }, () => []);
    const g: number[][] = Array.from({ length: n }, () => []);

    for (const [a, b] of invocations) {
        f[a].push(b);
        f[b].push(a);
        g[a].push(b);
    }

    const dfs = (i: number) => {
        suspicious[i] = true;
        for (const j of g[i]) {
            if (!suspicious[j]) {
                dfs(j);
            }
        }
    };

    dfs(k);

    const dfs2 = (i: number) => {
        vis[i] = true;
        for (const j of f[i]) {
            if (!vis[j]) {
                suspicious[j] = false;
                dfs2(j);
            }
        }
    };

    for (let i = 0; i < n; i++) {
        if (!suspicious[i] && !vis[i]) {
            dfs2(i);
        }
    }

    return Array.from({ length: n }, (_, i) => i).filter(i => !suspicious[i]);
}
