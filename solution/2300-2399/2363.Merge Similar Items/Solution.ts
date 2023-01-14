function mergeSimilarItems(items1: number[][], items2: number[][]): number[][] {
    const count = new Array(1001).fill(0);
    for (const [v, w] of items1) {
        count[v] += w;
    }
    for (const [v, w] of items2) {
        count[v] += w;
    }
    return [...count.entries()].filter(v => v[1] !== 0);
}
