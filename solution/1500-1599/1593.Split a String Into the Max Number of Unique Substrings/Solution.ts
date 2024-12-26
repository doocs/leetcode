function maxUniqueSplit(s: string): number {
    const n = s.length;
    const st = new Set<string>();
    let ans = 0;
    const dfs = (i: number): void => {
        if (st.size + n - i <= ans) {
            return;
        }
        if (i >= n) {
            ans = Math.max(ans, st.size);
            return;
        }
        for (let j = i + 1; j <= n; ++j) {
            const t = s.slice(i, j);
            if (!st.has(t)) {
                st.add(t);
                dfs(j);
                st.delete(t);
            }
        }
    };
    dfs(0);
    return ans;
}
