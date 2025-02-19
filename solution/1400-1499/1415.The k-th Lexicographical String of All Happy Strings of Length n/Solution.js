function getHappyString(n, k) {
    const ans = [];

    const dfs = (s = '') => {
        if (s.length === n) {
            ans.push(s);
            return;
        }

        for (const ch of 'abc') {
            if (s.at(-1) === ch) continue;
            dfs(s + ch);
        }
    };

    dfs();

    return ans[k - 1] ?? '';
}
