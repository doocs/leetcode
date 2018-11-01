const maxSubArray = function(nums){
  if(nums.length === 0) return 0;
  let ans = nums[0], tmp = nums[0];
  for(let i = 1; i < nums.length; i++){
    tmp = Math.max(tmp+nums[i], nums[i]);
    ans = Math.max(ans,tmp);
  }
  return ans;
}