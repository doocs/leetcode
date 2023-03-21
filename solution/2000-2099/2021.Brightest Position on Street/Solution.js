/**
 * @param {number[][]} lights
 * @return {number}
 */
var brightestPosition = function (lights) {
    const d = new Map();
    for (const [i, j] of lights) {
        const l = i - j;
        const r = i + j;
        d.set(l, (d.get(l) ?? 0) + 1);
        d.set(r + 1, (d.get(r + 1) ?? 0) - 1);
    }
    const keys = [];
    for (const k of d.keys()) {
        keys.push(k);
    }
    keys.sort((a, b) => a - b);
    let ans = 0;
    let s = 0;
    let mx = 0;
    for (const i of keys) {
        s += d.get(i);
        if (mx < s) {
            mx = s;
            ans = i;
        }
    }
    return ans;
};
