const moveZeros = function(nums){
  for(let i = 0; i < nums.length; i++){
    if(nums[i] === 0){
      for(let j = i+1; j < nums.length; j++){
        if(nums[j] !== 0){
          let t = nums[i];
          nums[i] = nums[j];
          nums[j] = t;
          break;
        }
      }
    }
  }
}