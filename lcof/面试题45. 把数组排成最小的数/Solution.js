/**
 * @param {number[]} nums
 * @return {string}
 */
var minNumber = function (nums) {
    nums.sort((a, b) => {
        const x = a + '' + b;
        const y = b + '' + a;
        return x < y ? -1 : 1;
    });
    return nums.join('');
};
