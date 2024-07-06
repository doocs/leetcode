/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
var smallestDivisor = function (nums, threshold) {
    let l = 1;
    let r = Math.max(...nums);
    while (l < r) {
        const mid = (l + r) >> 1;
        const s = nums.reduce((acc, x) => acc + Math.ceil(x / mid), 0);
        if (s <= threshold) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};
