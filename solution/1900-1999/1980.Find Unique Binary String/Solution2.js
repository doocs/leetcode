/**
 * @param {string[]} nums
 * @return {string}
 */
var findDifferentBinaryString = function (nums) {
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; i++) {
        ans[i] = nums[i][i] === '0' ? '1' : '0';
    }
    return ans.join('');
};
