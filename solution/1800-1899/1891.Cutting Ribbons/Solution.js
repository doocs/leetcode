/**
 * @param {number[]} ribbons
 * @param {number} k
 * @return {number}
 */
 var maxLength = function(ribbons, k) {
    let low = 0;
    let high = 100000;
    while (low < high) {
        const mid = (low + high + 1) >> 1;
        let cnt = 0;
        for (let ribbon of ribbons) {
            cnt += Math.floor(ribbon / mid);
        }
        if (cnt < k) {
            high = mid - 1;
        } else {
            low = mid;
        }
    }
    return low;
};