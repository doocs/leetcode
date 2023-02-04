/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function (nums) {
    const cnt = new Array(32).fill(0);
    for (let x of nums) {
        for (let i = 0; i < 32; ++i) {
            cnt[i] += x & 1;
            x >>= 1;
        }
    }
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        if (cnt[i] % 3) {
            ans |= 1 << i;
        }
    }
    return ans;
};
