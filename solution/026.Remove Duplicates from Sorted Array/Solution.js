const removeDuplicates = function(nums){
  let i = 1;
  let t = 0;
  let t2 = nums[0];
  let res = 1;
  let length = nums.length;
  for(let k = 1; k < length; k++){
    if(nums[k] - nums[k-1] !== 0 && nums[k] !== t2){
      t2 = nums[k];
      t = nums[i];
      nums[i] = nums[k];
      nums[k] = t;
      i++;
      res++;
    }
  }
  return res;
}