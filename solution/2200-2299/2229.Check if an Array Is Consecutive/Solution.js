/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isConsecutive = function (nums) {
    let [mi, mx] = [nums[0], 0];
    const s = new Set();
    for (const x of nums) {
        if (s.has(x)) {
            return false;
        }
        s.add(x);
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return mx - mi + 1 === nums.length;
};
