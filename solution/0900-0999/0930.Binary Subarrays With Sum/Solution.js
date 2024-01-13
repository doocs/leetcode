/**
 * @param {number[]} nums
 * @param {number} goal
 * @return {number}
 */
var numSubarraysWithSum = function (nums, goal) {
    const cnt = new Array(nums.length + 1).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let s = 0;
    for (const v of nums) {
        s += v;
        if (s >= goal) {
            ans += cnt[s - goal];
        }
        ++cnt[s];
    }
    return ans;
};
