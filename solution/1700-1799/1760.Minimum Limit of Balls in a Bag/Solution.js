/**
 * @param {number[]} nums
 * @param {number} maxOperations
 * @return {number}
 */
var minimumSize = function (nums, maxOperations) {
    let left = 1;
    let right = 1e9;
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (const v of nums) {
            s += Math.floor((v - 1) / mid);
        }
        if (s <= maxOperations) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
