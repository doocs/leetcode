function numberOfComponents(properties: number[][], k: number): number {
    const n = properties.length;
    const ss: Set<number>[] = Array.from({ length: n }, () => new Set());
    const g: number[][] = Array.from({ length: n }, () => []);

    for (let i = 0; i < n; i++) {
        for (const x of properties[i]) {
            ss[i].add(x);
        }
    }

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < i; j++) {
            let cnt = 0;
            for (const x of ss[i]) {
                if (ss[j].has(x)) {
                    cnt++;
                }
            }
            if (cnt >= k) {
                g[i].push(j);
                g[j].push(i);
            }
        }
    }

    let ans = 0;
    const vis: boolean[] = Array(n).fill(false);

    const dfs = (i: number) => {
        vis[i] = true;
        for (const j of g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    };

    for (let i = 0; i < n; i++) {
        if (!vis[i]) {
            dfs(i);
            ans++;
        }
    }
    return ans;
}
