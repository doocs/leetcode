function leadsToDestination(
    n: number,
    edges: number[][],
    source: number,
    destination: number,
): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
    }
    if (g[destination].length > 0) {
        return false;
    }

    const st: number[] = Array(n).fill(0);

    const dfs = (i: number): boolean => {
        if (st[i] !== 0) {
            return st[i] === 2;
        }
        if (g[i].length === 0) {
            return i === destination;
        }
        st[i] = 1;
        for (const j of g[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        st[i] = 2;
        return true;
    };

    return dfs(source);
}
