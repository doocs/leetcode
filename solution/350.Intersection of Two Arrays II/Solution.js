const intersect = function(nums1, nums2){
  let arr = [];
  while(nums2.length > 0){
    let tmp = nums2.pop();
    if(nums1.indexOf(tmp) > -1){
      let indexTmp = nums1.indexOf(tmp);
      nums1.splice(indexTmp,1);
      arr.push(tmp);
    }
  }
  return arr;
}