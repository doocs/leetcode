export function validPath(
    n: number,
    edges: number[][],
    source: number,
    destination: number,
): boolean {
    const graph: number[][] = Array.from({ length: n }, () => []);

    for (const [a, b] of edges) {
        graph[a].push(b);
        graph[b].push(a);
    }

    const seen = new Set<number>();
    const dfs = (i: number) => {
        if (i === destination) return true;
        if (seen.has(i)) return false;

        seen.add(i);
        return graph[i].some(dfs);
    };

    return dfs(source);
}
