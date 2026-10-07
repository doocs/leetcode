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

    suspicious[k] = true;
    let stk: number[] = [k];
    while (stk.length) {
        const i = stk.pop()!;
        for (const j of g[i]) {
            if (!suspicious[j]) {
                suspicious[j] = true;
                stk.push(j);
            }
        }
    }

    for (let i = 0; i < n; i++) {
        if (suspicious[i] || vis[i]) {
            continue;
        }
        vis[i] = true;
        stk = [i];
        while (stk.length) {
            const u = stk.pop()!;
            for (const j of f[u]) {
                if (!vis[j]) {
                    suspicious[j] = false;
                    vis[j] = true;
                    stk.push(j);
                }
            }
        }
    }

    return Array.from({ length: n }, (_, i) => i).filter(i => !suspicious[i]);
}
