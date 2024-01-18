function translateNum(num: number): number {
    const s = num.toString();
    const n = s.length;
    const f = new Array(n).fill(0);
    const dfs = (i: number): number => {
        if (i >= n - 1) {
            return 1;
        }
        if (f[i]) {
            return f[i];
        }
        let ans = dfs(i + 1);
        if (s[i] === '1' || (s[i] === '2' && s[i + 1] < '6')) {
            ans += dfs(i + 2);
        }
        f[i] = ans;
        return ans;
    };
    return dfs(0);
}
