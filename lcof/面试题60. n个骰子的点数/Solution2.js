/**
 * @param {number} num
 * @return {number[]}
 */
var statisticsProbability = function (n) {
    let f = Array(7).fill(1);
    f[0] = 0;
    for (let i = 2; i <= n; ++i) {
        let g = Array(6 * i + 1).fill(0);
        for (let j = i; j <= 6 * i; ++j) {
            for (let k = 1; k <= 6; ++k) {
                if (j - k >= 0 && j - k < f.length) {
                    g[j] += f[j - k];
                }
            }
        }
        f = g;
    }

    const ans = [];
    const m = Math.pow(6, n);
    for (let j = n; j <= 6 * n; ++j) {
        ans.push(f[j] / m);
    }
    return ans;
};
