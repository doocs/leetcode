function restoreIpAddresses(s: string): string[] {
    const n = s.length;
    const ans: string[] = [];
    const t: string[] = [];
    const dfs = (i: number): void => {
        if (i >= n && t.length === 4) {
            ans.push(t.join('.'));
            return;
        }
        if (i >= n || t.length === 4) {
            return;
        }
        let x = 0;
        for (let j = i; j < i + 3 && j < n; ++j) {
            x = x * 10 + s[j].charCodeAt(0) - '0'.charCodeAt(0);
            if (x > 255 || (j > i && s[i] === '0')) {
                break;
            }
            t.push(x.toString());
            dfs(j + 1);
            t.pop();
        }
    };
    dfs(0);
    return ans;
}
