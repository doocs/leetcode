function distributeCookies(cookies: number[], k: number): number {
    const cnt = new Array(k).fill(0);
    let ans = 1 << 30;
    const dfs = (i: number) => {
        if (i >= cookies.length) {
            ans = Math.max(...cnt);
            return;
        }
        for (let j = 0; j < k; ++j) {
            if (cnt[j] + cookies[i] >= ans || (j && cnt[j] == cnt[j - 1])) {
                continue;
            }
            cnt[j] += cookies[i];
            dfs(i + 1);
            cnt[j] -= cookies[i];
        }
    };
    dfs(0);
    return ans;
}
