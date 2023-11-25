/**
 * @param {string} s
 * @return {boolean}
 */
var checkZeroOnes = function (s) {
    const f = x => {
        let [mx, cnt] = [0, 0];
        for (const c of s) {
            if (c === x) {
                mx = Math.max(mx, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return mx;
    };
    return f('1') > f('0');
};
