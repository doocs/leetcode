function maximumScoreAfterOperations(edges: number[][], values: number[]): number {
    const g: number[][] = Array.from({ length: values.length }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = (i: number, fa: number): [number, number] => {
        let [a, b] = [0, 0];
        let leaf = true;
        for (const j of g[i]) {
            if (j !== fa) {
                const [aa, bb] = dfs(j, i);
                a += aa;
                b += bb;
                leaf = false;
            }
        }
        if (leaf) {
            return [values[i], 0];
        }
        return [values[i] + a, Math.max(values[i] + b, a)];
    };
    return dfs(0, -1)[1];
}
