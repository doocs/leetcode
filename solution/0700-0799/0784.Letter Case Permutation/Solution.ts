function letterCasePermutation(s: string): string[] {
    const n = s.length;
    const cs = [...s];
    const res = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push(cs.join(''));
            return;
        }
        dfs(i + 1);
        if (cs[i] >= 'A') {
            cs[i] = String.fromCharCode(cs[i].charCodeAt(0) ^ 32);
            dfs(i + 1);
        }
    };
    dfs(0);
    return res;
}
