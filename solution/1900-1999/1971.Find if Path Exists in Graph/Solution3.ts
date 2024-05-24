function validPath(n: number, edges: number[][], source: number, destination: number): boolean {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (const [a, b] of edges) {
        p[find(a)] = find(b);
    }
    return find(source) === find(destination);
}
