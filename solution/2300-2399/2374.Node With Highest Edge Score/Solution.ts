function edgeScore(edges: number[]): number {
    const n = edges.length;
    const cnt: number[] = Array(n).fill(0);
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        const j = edges[i];
        cnt[j] += i;
        if (cnt[ans] < cnt[j] || (cnt[ans] === cnt[j] && j < ans)) {
            ans = j;
        }
    }
    return ans;
}
