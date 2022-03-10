/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxLength = function (nums) {
    const mp = new Map();
    mp.set(0, -1);
    let s = 0;
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i] == 0 ? -1 : 1;
        if (mp.has(s)) ans = Math.max(ans, i - mp.get(s));
        else mp.set(s, i);
    }
    return ans;
};
