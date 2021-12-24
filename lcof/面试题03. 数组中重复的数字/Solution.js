/**
 * @param {number[]} nums
 * @return {number}
 */
var findRepeatNumber = function (nums) {
    let m = {};
    for (let num of nums) {
        if (m[num]) return num;
        m[num] = 1;
    }
};
