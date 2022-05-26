function findSmallestSetOfVertices(n: number, edges: number[][]): number[] {
    const arr = new Array(n).fill(true);
    for (const [_, i] of edges) {
        arr[i] = false;
    }
    const res = [];
    arr.forEach((v, i) => {
        if (v) {
            res.push(i);
        }
    });
    return res;
}
