/**
 * @param {number[]} nums
 * @return {number}
 */
var numIdenticalPairs = function (nums) {
    const cnt = Array(101).fill(0);
    let ans = 0;
    for (const x of nums) {
        ans += cnt[x]++;
    }
    return ans;
};
