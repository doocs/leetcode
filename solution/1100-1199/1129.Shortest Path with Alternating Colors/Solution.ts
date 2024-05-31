function shortestAlternatingPaths(
    n: number,
    redEdges: number[][],
    blueEdges: number[][],
): number[] {
    const g: [Graph, Graph] = [{}, {}];
    const ans = Array(n).fill(-1);
    const vis = Array.from({ length: n }, () =>
        Array.from({ length: n }, () => Array.from({ length: 2 }, () => false)),
    );
    // prettier-ignore
    let q: Vertex[] = [ [0, 0], [0, 1] ]
    let d = 0;

    for (const [i, j] of redEdges) (g[0][i] ??= []).push(j);
    for (const [i, j] of blueEdges) (g[1][i] ??= []).push(j);

    while (q.length) {
        const qNext: Vertex[] = [];

        for (const [v, color] of q) {
            if (!~ans[v]) ans[v] = d;

            for (const vNext of g[color][v] ?? []) {
                if (vis[v][vNext][color]) continue;

                vis[v][vNext][color] = true;
                qNext.push([vNext, +!color as Color]);
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
