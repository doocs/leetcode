/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function (nums) {
    let a = 0;
    let b = 0;
    for (let num of nums) {
        a = (a ^ num) & ~b;
        b = (b ^ num) & ~a;
    }
    return a;
};
