function shortestAlternatingPaths(
    n: number,
    redEdges: number[][],
    blueEdges: number[][],
): number[] {
    const g: [Graph, Graph] = [{}, {}];
    const ans = Array(n).fill(-1);
    const vis = Array.from({ length: n }, () => Array.from({ length: 2 }, () => false));
    let q: Vertex[] = [
        [0, 0],
        [0, 1],
    ];
    vis[0][0] = vis[0][1] = true;
    let d = 0;
    for (const [i, j] of redEdges) {
        (g[0][i] ??= []).push(j);
    }
    for (const [i, j] of blueEdges) {
        (g[1][i] ??= []).push(j);
    }
    while (q.length) {
        const qNext: Vertex[] = [];
        for (let [i, color] of q) {
            if (ans[i] === -1) {
                ans[i] = d;
            }
            color ^= 1;
            for (const j of g[color][i] ?? []) {
                if (!vis[j][color]) {
                    vis[j][color] = true;
                    qNext.push([j, color as Color]);
                }
            }
        }
        q = qNext;
        d++;
    }
    return ans;
}

type Graph = Record<number, number[]>;
type Color = 0 | 1;
type Vertex = [number, Color];
