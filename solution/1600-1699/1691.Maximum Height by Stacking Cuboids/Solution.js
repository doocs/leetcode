/**
 * @param {number[][]} cuboids
 * @return {number}
 */
var maxHeight = function (cuboids) {
    for (const c of cuboids) {
        c.sort((a, b) => a - b);
    }
    cuboids.sort((a, b) => {
        if (a[0] != b[0]) return a[0] - b[0];
        if (a[1] != b[1]) return a[1] - b[1];
        return a[2] - b[2];
    });
    const n = cuboids.length;
    const f = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            const ok =
                cuboids[j][1] <= cuboids[i][1] &&
                cuboids[j][2] <= cuboids[i][2];
            if (ok) f[i] = Math.max(f[i], f[j]);
        }
        f[i] += cuboids[i][2];
    }
    return Math.max(...f);
};
