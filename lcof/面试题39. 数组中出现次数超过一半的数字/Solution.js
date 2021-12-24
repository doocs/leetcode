/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0;
    let candidate = 0;
    for (const num of nums) {
        if (cnt == 0) {
            candidate = num;
        }
        cnt += candidate == num ? 1 : -1;
    }
    return candidate;
};
