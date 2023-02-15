/**
 * @param {number[]} nums
 * @return {number[]}
 */
var numberOfPairs = function (nums) {
    const cnt = new Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    const s = cnt.reduce((a, b) => a + (b >> 1), 0);
    return [s, nums.length - s * 2];
};
