/**
 * @param {number[]} nums
 * @return {string}
 */
var minNumber = function (nums) {
    nums.sort((a, b) => {
        let s1 = a + '' + b;
        let s2 = b + '' + a;
        if (s1 < s2) {
            return -1;
        } else return 1;
    });
    return nums.join('');
};
