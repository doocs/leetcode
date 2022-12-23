/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const s = new Set(nums);
    let res = 0;
    for (const num of nums) {
        if (!s.has(num - 1)) {
            let t = 1;
            let next = num + 1;
            while (s.has(next++)) {
                t++;
            }
            res = Math.max(res, t);
        }
    }
    return res;
};
