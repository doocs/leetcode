/**
 * @param {number[]} nums
 * @return {number}
 */
var largestUniqueNumber = function (nums) {
    const cnt = new Array(1001).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let x = 1000; x >= 0; --x) {
        if (cnt[x] == 1) {
            return x;
        }
    }
    return -1;
};
