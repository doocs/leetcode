/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function(nums) {
    var max = 0
    var n = 0    
    for(var i = 0; i < nums.length; i++) {
        if (nums[i] == 0) {
            n = 0
        } else {
            n++
        }        
        max = n > max ? n : max        
        if (n > max) {
            max = n
        }
    }
        return max
};
