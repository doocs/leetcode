/**
 * @param {number[]} nums
 * @return {number}
 */
var longestSquareStreak = function (nums) {
    const s = new Set(nums);
    let ans = -1;

    for (const num of nums) {
        let x = num;
        let t = 0;

        while (s.has(x)) {
            x *= x;
            t += 1;
        }

        if (t > 1) {
            ans = Math.max(ans, t);
        }
    }

    return ans;
};
