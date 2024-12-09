function minRunesToAdd(
    n: number,
    crystals: number[],
    flowFrom: number[],
    flowTo: number[],
): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < flowFrom.length; i++) {
        const a = flowFrom[i],
            b = flowTo[i];
        g[a].push(b);
    }

    const vis: number[] = Array(n).fill(0);
    for (const x of crystals) {
        vis[x] = 1;
    }

    const bfs = (q: number[]) => {
        while (q.length > 0) {
            const a = q.shift()!;
            for (const b of g[a]) {
                if (vis[b] === 1) continue;
                vis[b] = 1;
                q.push(b);
            }
        }
    };

    const seq: number[] = [];
    const dfs = (a: number) => {
        vis[a] = 2;
        for (const b of g[a]) {
            if (vis[b] > 0) continue;
            dfs(b);
        }
        seq.push(a);
    };

    bfs(crystals);

    for (let i = 0; i < n; i++) {
        if (vis[i] === 0) {
            dfs(i);
        }
    }

    seq.reverse();

    let ans = 0;
    for (const i of seq) {
        if (vis[i] === 2) {
            bfs([i]);
            vis[i] = 1;
            ans++;
        }
    }

    return ans;
}
