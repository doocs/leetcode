/**
 * @param {number[]} nums
 * @return {number[]}
 */
var singleNumber = function (nums) {
    let eor = 0;
    for (const x of nums) {
        eor ^= x;
    }
    const lowbit = eor & -eor;
    let ans = [0];
    for (const x of nums) {
        if ((x & lowbit) == 0) {
            ans[0] ^= x;
        }
    }
    ans.push(eor ^ ans[0]);
    return ans;
};
