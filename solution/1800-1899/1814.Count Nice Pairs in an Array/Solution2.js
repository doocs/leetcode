/**
 * @param {number[]} nums
 * @return {number}
 */
var countNicePairs = function (nums) {
    const rev = x => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    let ans = 0;
    const mod = 1e9 + 7;
    const cnt = new Map();
    for (const x of nums) {
        const y = x - rev(x);
        const v = cnt.get(y) | 0;
        ans = (ans + v) % mod;
        cnt.set(y, v + 1);
    }
    return ans;
};
