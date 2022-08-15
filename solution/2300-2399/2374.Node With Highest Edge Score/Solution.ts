function edgeScore(edges: number[]): number {
    const n = edges.length;
    const sum = new Array(n).fill(0);
    for (let i = 0; i < n; i++) {
        sum[edges[i]] += i;
    }
    let res = 0;
    for (let i = 0; i < n; i++) {
        if (sum[res] < sum[i]) {
            res = i;
        }
    }
    return res;
}
