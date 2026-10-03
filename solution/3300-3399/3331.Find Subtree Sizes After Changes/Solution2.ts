function findSubtreeSizes(parent: number[], s: string): number[] {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const d: number[][] = Array.from({ length: 26 }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    const ans: number[] = Array(n).fill(0);
    const stk: number[][] = [[0, -1, 0]];
    while (stk.length) {
        const [i, fa, state] = stk.pop()!;
        const idx = s.charCodeAt(i) - 97;
        if (state === 0) {
            ans[i] = 1;
            d[idx].push(i);
            stk.push([i, fa, 1]);
            for (const j of g[i]) {
                stk.push([j, i, 0]);
            }
        } else {
            const k = d[idx].length > 1 ? d[idx][d[idx].length - 2] : fa;
            if (k >= 0) {
                ans[k] += ans[i];
            }
            d[idx].pop();
        }
    }
    return ans;
}
