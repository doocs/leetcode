function countSubTrees(n: number, edges: number[][], labels: string): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[] = Array(n).fill(0);
    const cnt: number[] = Array(26).fill(0);
    const stk: number[][] = [[0, -1, 0]];
    while (stk.length) {
        const [i, fa, state] = stk.pop()!;
        const k = labels.charCodeAt(i) - 97;
        if (state === 0) {
            ans[i] -= cnt[k];
            cnt[k]++;
            stk.push([i, fa, 1]);
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, 0]);
                }
            }
        } else {
            ans[i] += cnt[k];
        }
    }
    return ans;
}
