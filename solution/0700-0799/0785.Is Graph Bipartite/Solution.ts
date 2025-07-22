function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    const color: number[] = Array(n).fill(0);
    const dfs = (a: number, c: number): boolean => {
        color[a] = c;
        for (const b of graph[a]) {
            if (color[b] === c || (color[b] === 0 && !dfs(b, -c))) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0; i < n; i++) {
        if (color[i] === 0 && !dfs(i, 1)) {
            return false;
        }
    }
    return true;
}
