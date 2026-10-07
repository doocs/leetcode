function minimumDiameterAfterMerge(edges1: number[][], edges2: number[][]): number {
    const d1 = treeDiameter(edges1);
    const d2 = treeDiameter(edges2);
    return Math.max(d1, d2, Math.ceil(d1 / 2) + Math.ceil(d2 / 2) + 1);
}

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
        const stk: [number, number, number][] = [[start, -1, 0]];
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
        return [ans, node];
    };
    const [, a] = farthest(0);
    const [ans] = farthest(a);
    return ans;
}
