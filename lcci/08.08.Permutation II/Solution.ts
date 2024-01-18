function permutation(S: string): string[] {
    const cs: string[] = S.split('').sort();
    const ans: string[] = [];
    const n = cs.length;
    const vis: boolean[] = Array(n).fill(false);
    const t: string[] = [];
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && !vis[j - 1] && cs[j] === cs[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.push(cs[j]);
            dfs(i + 1);
            t.pop();
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
}
