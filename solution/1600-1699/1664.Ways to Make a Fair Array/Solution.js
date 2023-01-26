/**
 * @param {number[]} nums
 * @return {number}
 */
var waysToMakeFair = function (nums) {
    let [s1, s2, t1, t2] = [0, 0, 0, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (i % 2 == 0) {
            s1 += nums[i];
        } else {
            s2 += nums[i];
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const v = nums[i];
        ans += i % 2 == 0 && t2 + s1 - t1 - v == t1 + s2 - t2;
        ans += i % 2 == 1 && t2 + s1 - t1 == t1 + s2 - t2 - v;
        t1 += i % 2 == 0 ? v : 0;
        t2 += i % 2 == 1 ? v : 0;
    }
    return ans;
};
