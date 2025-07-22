function largestPathValue(colors: string, edges: number[][]): number {
    const n = colors.length;
    const indeg = Array(n).fill(0);
    const g: Map<number, number[]> = new Map();
    for (const [a, b] of edges) {
        if (!g.has(a)) g.set(a, []);
        g.get(a)!.push(b);
        indeg[b]++;
    }
    const q: number[] = [];
    const dp: number[][] = Array.from({ length: n }, () => Array(26).fill(0));
    for (let i = 0; i < n; i++) {
        if (indeg[i] === 0) {
            q.push(i);
            const c = colors.charCodeAt(i) - 97;
            dp[i][c]++;
        }
    }
    let cnt = 0;
    let ans = 1;
    while (q.length) {
        const i = q.pop()!;
        cnt++;
        if (g.has(i)) {
            for (const j of g.get(i)!) {
                indeg[j]--;
                if (indeg[j] === 0) q.push(j);
                const c = colors.charCodeAt(j) - 97;
                for (let k = 0; k < 26; k++) {
                    dp[j][k] = Math.max(dp[j][k], dp[i][k] + (c === k ? 1 : 0));
                    ans = Math.max(ans, dp[j][k]);
                }
            }
        }
    }
    return cnt < n ? -1 : ans;
}
