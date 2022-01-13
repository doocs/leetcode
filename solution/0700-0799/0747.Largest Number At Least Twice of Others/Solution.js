/**
 * @param {number[]} nums
 * @return {number}
 */
var dominantIndex = function (nums) {
    let mx = 0,
        mid = 0;
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] > mx) {
            mid = mx;
            mx = nums[i];
            ans = i;
        } else if (nums[i] > mid) {
            mid = nums[i];
        }
    }
    return mx >= mid * 2 ? ans : -1;
};
