//beat 88%
const merge1 = function(nums1, m, nums2, n){
  const arr = nums1.slice(0,m);
  let i = 0, j = 0;
  let count = 0;
  while(i < m && j < n){
    if(arr[i] <= nums2[j]){
      nums1[count++] = arr[i++];
    }else{
      nums1[count++] = nums2[j++];
    }
  }
  while(i < m){
    nums1[count++] = arr[i++];
  }
  while(j < n){
    nums1[count++] = nums2[j++];
  }
};

//beat 30%....
const merge = function(nums1, m, nums2,n){
  let index = m + n - 1;
  let aindex = m - 1;
  let bindex = n - 1;
  while(aindex >= 0 && bindex >= 0){
    if(nums1[aindex] > nums2[bindex]){
      nums1[index--] = nums1[aindex--];
    }else{
      nums1[index--] = nums2[bindex--];
    }
  }
  while(aindex >= 0){
    nums1[index--] = nums1[aindex--];
  }
  while(bindex >= 0){
    nums1[index--] = nums2[bindex--];
  }
};