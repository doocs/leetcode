/**
 * @param {number} x
 * @return {number}
 */
 var mySqrt = function(x) {
    if (x == 0) {
        return 0;
    }
    let left = 1;
    let right = x;
    while (left < right) {
        const mid = (left + right + 1) >>> 1;
        if (x / mid >= mid) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};