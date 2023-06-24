function findSmallestSetOfVertices(n: number, edges: number[][]): number[] {
    const cnt: number[] = new Array(n).fill(0);
    for (const [_, t] of edges) {
        cnt[t]++;
    }
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (cnt[i] === 0) {
            ans.push(i);
        }
    }
    return ans;
}
