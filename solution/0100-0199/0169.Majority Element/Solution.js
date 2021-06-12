/**
 * @param {number[]} nums
 * @return {number}
 */
 var majorityElement = function(nums) {
    let cnt = 0;
    let major = 0;
    for (const num of nums) {
        if (cnt == 0) {
            major = num;
            cnt = 1;
        } else {
            cnt += (major == num ? 1 : -1);
        }
    }
    return major;
};