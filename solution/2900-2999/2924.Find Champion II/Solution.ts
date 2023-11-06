function findChampion(n: number, edges: number[][]): number {
    const indeg: number[] = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++indeg[v];
    }
    let [ans, cnt] = [-1, 0];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            ++cnt;
            ans = i;
        }
    }
    return cnt === 1 ? ans : -1;
}
