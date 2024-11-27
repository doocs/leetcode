function findChampion(n, edges) {
    const vertexes = new Set(Array.from({ length: n }, (_, i) => i));
    for (const [_, v] of edges) {
        vertexes.delete(v);
    }
    return vertexes.size === 1 ? vertexes[Symbol.iterator]().next().value : -1;
}
