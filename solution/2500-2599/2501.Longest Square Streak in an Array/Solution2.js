/**
 * @param {number[]} nums
 * @return {number}
 */
var longestSquareStreak = function (nums) {
    const s = new Set(nums);
    const f = new Map();
    const dfs = x => {
        if (f.has(x)) {
            return f.get(x);
        }
        if (!s.has(x)) {
            return 0;
        }
        f.set(x, 1 + dfs(x ** 2));
        return f.get(x);
    };

    for (const x of s) {
        dfs(x);
    }
    const ans = Math.max(...f.values());
    return ans > 1 ? ans : -1;
};
