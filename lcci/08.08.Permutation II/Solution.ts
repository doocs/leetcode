function permutation(S: string): string[] {
    const s: string[] = S.split('').sort();
    const n = s.length;
    const t = Array(n).fill('');
    const vis: boolean[] = Array(n).fill(false);
    const ans: string[] = [];
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (j === 0 || s[j] !== s[j - 1] || vis[j - 1])) {
                vis[j] = true;
                t[i] = s[j];
                dfs(i + 1);
                vis[j] = false;
            }
        }
    };
    dfs(0);
    return ans;
}
