/**
 * @param {number[]} nums
 * @param {number} value
 * @return {number}
 */
var findSmallestInteger = function (nums, value) {
    const cnt = Array(value).fill(0);
    for (const x of nums) {
        ++cnt[((x % value) + value) % value];
    }
    for (let i = 0; ; ++i) {
        if (cnt[i % value]-- === 0) {
            return i;
        }
    }
};
