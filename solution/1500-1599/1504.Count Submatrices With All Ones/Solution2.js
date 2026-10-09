/**
 * @param {number[][]} mat
 * @return {number}
 */
var numSubmat = function (mat) {
    const m = mat.length;
    const n = mat[0].length;
    const g = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }
    let ans = 0;
    for (let j = 0; j < n; j++) {
        const stk = [];
        for (let i = 0; i < m; i++) {
            const cur = g[i][j];
            while (stk.length > 0 && stk[stk.length - 1][0] >= cur) {
                stk.pop();
            }
            let cnt = cur * (i + 1);
            if (stk.length > 0) {
                const t = stk[stk.length - 1];
                cnt = t[2] + cur * (i - t[1]);
            }
            ans += cnt;
            stk.push([cur, i, cnt]);
        }
    }
    return ans;
};
