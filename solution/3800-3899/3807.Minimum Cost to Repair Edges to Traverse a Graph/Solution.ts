function minCost(n: number, edges: number[][], k: number): number {
    edges.sort((a, b) => a[2] - b[2]);

    const check = (idx: number): boolean => {
        const g: number[][] = Array.from({ length: n }, () => []);
        for (let i = 0; i <= idx; i++) {
            const [u, v] = edges[i];
            g[u].push(v);
            g[v].push(u);
        }

        let q: number[] = [0];
        const vis: boolean[] = Array(n).fill(false);
        vis[0] = true;

        let dist = 0;
        while (q.length > 0) {
            const nq: number[] = [];
            for (const u of q) {
                if (u === n - 1) {
                    return dist <= k;
                }
                for (const v of g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nq.push(v);
                    }
                }
            }
            q = nq;
            dist++;
        }
        return false;
    };

    let [l, r] = [0, edges.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return check(l) ? edges[l][2] : -1;
}
