function minimumTime(n: number, relations: number[][], time: number[]): number {
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => []);
    const indeg: number[] = Array(n).fill(0);
    for (const [a, b] of relations) {
        g[a - 1].push(b - 1);
        ++indeg[b - 1];
    }
    const q: number[] = [];
    const f: number[] = Array(n).fill(0);
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
            f[i] = time[i];
            ans = Math.max(ans, f[i]);
        }
    }
    while (q.length > 0) {
        const i = q.shift()!;
        for (const j of g[i]) {
            f[j] = Math.max(f[j], f[i] + time[j]);
            ans = Math.max(ans, f[j]);
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return ans;
}
