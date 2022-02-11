/**
 * @param {number[]} nums
 * @param {number} k
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var rotate = function (nums, k) {
    k %= nums.length;
    nums.splice(0, 0, ...nums.splice(-k, k));
};

/*
 * Author: KimYangOfCat
*/
 var rotate = function(nums, k) {
    // 数组翻转想法
    k%=nums.length;
    reverse(nums,0,nums.length-1);
    reverse(nums,0,k-1);
    reverse(nums,k,nums.length-1);

};
function reverse(nums,start,end){
    while(start<end){
        const temp = nums[start];
        nums[start]=nums[end];
        nums[end]=temp;
        start+=1;
        end-=1;
    }
}