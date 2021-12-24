/**
 * @param {number[]} nums
 * @return {number[]}
 */
var singleNumbers = function (nums) {
    let eor = 0;
    for (let num of nums) {
        eor ^= num;
    }
    const diff = eor & (~eor + 1);
    let a = 0;
    for (let num of nums) {
        if ((num & diff) == 0) {
            a ^= num;
        }
    }
    let b = eor ^ a;
    return [a, b];
};
