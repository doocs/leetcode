/**
 * @param {number[]} nums
 * @return {number[]}
 */
var getSumAbsoluteDifferences = function (nums) {
    let s = 0;
    for (const v of nums) {
        s += v;
    }
    let t = 0;
    const n = nums.length;
    const ans = [];
    for (let i = 0; i < n; ++i) {
        const v = nums[i];
        const x = s - t - (n - i) * v + v * i - t;
        t += v;
        ans.push(x);
    }
    return ans;
};
