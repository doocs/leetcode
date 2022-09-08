function countArrangement(n: number): number {
    const vis = new Array(n + 1).fill(0);
    const match = Array.from({ length: n + 1 }, () => []);
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= n; j++) {
            if (i % j === 0 || j % i === 0) {
                match[i].push(j);
            }
        }
    }

    let res = 0;
    const dfs = (i: number, n: number) => {
        if (i === n + 1) {
            res++;
            return;
        }
        for (const j of match[i]) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, n);
                vis[j] = false;
            }
        }
    };
    dfs(1, n);
    return res;
}
