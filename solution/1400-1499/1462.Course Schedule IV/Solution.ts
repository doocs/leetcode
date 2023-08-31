function checkIfPrerequisite(
    n: number,
    prerequisites: number[][],
    queries: number[][],
): boolean[] {
    const f = Array.from({ length: n }, () => Array(n).fill(false));
    const g: number[][] = Array.from({ length: n }, () => []);
    const indeg: number[] = Array(n).fill(0);
    for (const [a, b] of prerequisites) {
        g[a].push(b);
        ++indeg[b];
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.shift()!;
        for (const j of g[i]) {
            f[i][j] = true;
            for (let h = 0; h < n; ++h) {
                f[h][j] ||= f[h][i];
            }
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return queries.map(([a, b]) => f[a][b]);
}
