/**
 * @param {string} s
 * @return {number}
 */
var minSwaps = function (s) {
    const n0 = (s.match(/0/g) || []).length;
    const n1 = s.length - n0;
    if (Math.abs(n0 - n1) > 1) {
        return -1;
    }
    const calc = c => {
        let cnt = 0;
        for (let i = 0; i < s.length; i++) {
            const x = +s[i];
            if (((i & 1) ^ c) !== x) {
                cnt++;
            }
        }
        return Math.floor(cnt / 2);
    };
    if (n0 === n1) {
        return Math.min(calc(0), calc(1));
    }
    return calc(n0 > n1 ? 0 : 1);
};
