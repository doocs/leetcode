/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function(nums) {
    var flags = []  
    for(var i = 0; i <= nums.length; i++) {
        flags[i] = 0
    }   
    for(var i = 0; i<nums.length; i++) {
        var num = nums[i]
        flags[ num ] = 1
    }    
    for(var i = 0; i < flags.length; i++) {
        if (flags[i] == 0) {
            return i
        }
    }    
};
