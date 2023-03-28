function shortestCommonSupersequence(str1: string, str2: string): string {
    const m = str1.length;
    const n = str2.length;
    const f = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (str1[i - 1] == str2[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
            } else {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }
    }
    let ans: string[] = [];
    let i = m;
    let j = n;
    while (i > 0 || j > 0) {
        if (i === 0) {
            ans.push(str2[--j]);
        } else if (j === 0) {
            ans.push(str1[--i]);
        } else {
            if (f[i][j] === f[i - 1][j]) {
                ans.push(str1[--i]);
            } else if (f[i][j] === f[i][j - 1]) {
                ans.push(str2[--j]);
            } else {
                ans.push(str1[--i]);
                --j;
            }
        }
    }
    return ans.reverse().join('');
}
