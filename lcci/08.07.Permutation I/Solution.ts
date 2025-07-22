function permutation(S: string): string[] {
    const n = S.length;
    const vis: boolean[] = Array(n).fill(false);
    const ans: string[] = [];
    const t: string[] = Array(n).fill('');
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j]) {
                continue;
            }
            vis[j] = true;
            t[i] = S[j];
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
}
