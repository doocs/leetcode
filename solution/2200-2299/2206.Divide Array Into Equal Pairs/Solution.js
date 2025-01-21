/**
 * @param {number[]} nums
 * @return {boolean}
 */
var divideArray = function (nums) {
    const cnt = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    return Object.values(cnt).every(x => x % 2 === 0);
};
