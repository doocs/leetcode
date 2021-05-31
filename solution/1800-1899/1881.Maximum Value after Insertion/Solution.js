/**
 * @param {string} n
 * @param {number} x
 * @return {string}
 */
 var maxValue = function(n, x) {
    let nums = [...n];
    let sign = 1, i = 0;
    if (nums[0] == '-') {
        sign = -1;
        i++;
    }
    while (i < n.length && (nums[i] - x) * sign >= 0) {
        i++;
    }
    nums.splice(i, 0, x);
    return nums.join('');
};