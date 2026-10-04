function treeDiameter(edges: number[][]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const farthest = (start: number): [number, number] => {
        let ans = 0;
        let node = start;
        const stk: number[][] = [[start, -1, 0]];
        while (stk.length) {
            const [i, fa, t] = stk.pop()!;
            if (ans < t) {
                ans = t;
                node = i;
            }
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, t + 1]);
                }
            }
        }
        return [node, ans];
    };
    const [node] = farthest(0);
    return farthest(node)[1];
}
