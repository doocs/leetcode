function combinationSum3(k, n) {
    const ans = [];
    const t = [];
    const dfs = (i, s) => {
        if (s === 0) {
            if (t.length === k) {
                ans.push(t.slice());
            }
            return;
        }
        if (i > 9 || i > s || t.length >= k) {
            return;
        }
        for (let j = i; j <= 9; ++j) {
            t.push(j);
            dfs(j + 1, s - j);
            t.pop();
        }
    };
    dfs(1, n);
    return ans;
}
