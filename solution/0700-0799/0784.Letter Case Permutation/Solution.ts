function letterCasePermutation(s: string): string[] {
    const t = s.split('');
    const ans: string[] = [];
    const dfs = (i: number) => {
        if (i >= t.length) {
            ans.push(t.join(''));
            return;
        }
        dfs(i + 1);
        if (t[i].charCodeAt(0) >= 65) {
            t[i] = String.fromCharCode(t[i].charCodeAt(0) ^ 32);
            dfs(i + 1);
        }
    };
    dfs(0);
    return ans;
}
