/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
var smallestDivisor = function (nums, threshold) {
    let left = 1,
        right = 1000000;
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (let v of nums) {
            s += Math.ceil(v / mid);
        }
        if (s <= threshold) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
