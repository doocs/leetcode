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
    const q = [source];

    while (q.length) {
        const i = q.pop()!;

        if (i === destination) return true;
        if (seen.has(i)) continue;

        seen.add(i);
        q.push(...graph[i]);
    }

    return false;
}
