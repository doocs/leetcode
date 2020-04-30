/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function(nums) {
    let cnt = 0
    let mode = -1
    for(let num of nums) {
        if(!cnt) {
            mode = num
            cnt++
        } else {
            if(mode === num) cnt++
            else cnt--
        }
    }
    return mode
    // return nums.sort((a,b)=>a-b)[~~(nums.length/2)]
};