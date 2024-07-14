/**
 * @param {number[]} nums
 * @return {number}
 */
var findMagicIndex = function (nums) {
    const dfs = (i, j) => {
        if (i > j) {
            return -1;
        }
        const mid = (i + j) >> 1;
        const l = dfs(i, mid - 1);
        if (l !== -1) {
            return l;
        }
        if (nums[mid] === mid) {
            return mid;
        }
        return dfs(mid + 1, j);
    };
    return dfs(0, nums.length - 1);
};
