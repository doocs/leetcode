/**
 * @param {number[]} nums
 * @return {number}
 */
 var majorityElement = function(nums) {
    let candidate = 0, count = 0;
    for (let num of nums) {
        if (count == 0) candidate = num;
        if (candidate == num) {
            count++;
        } else {
            count--;
        }
    }
    let n = 0;
    for (let num of nums) {
        if (candidate == num) n++;
    }
    return n > (nums.length / 2) ? candidate : -1;
};