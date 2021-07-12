/**
 * @param {number[]} nums
 * @return {number[]}
 */
 var getConcatenation = function(nums) {
    let ans = nums.slice();
    ans.splice(nums.length, 0, ...nums);
    return ans;
};