/**
 * @param {number[]} nums
 * @return {number}
 */

/** 
 * Author: Mcnwork2018
 */

var arrayPairSum = function(nums) {
  let result = merge_sort(nums);
  let sum = 0;
  for ( let i = 0; i < result.length; i += 2 ) {
    sum += result[i];
  }
  return sum;
};
function merge_sort(A){
  let len = A.length;
  if ( len === 1 ) return A;
  let mid = ~~( len / 2 );
  let leftArray =  A.slice(0, mid);
  let rightArray = A.slice(mid, len);
  return merge(merge_sort(leftArray), merge_sort(rightArray));
}
function merge(left, right){
  let i = 0, j = 0, k = 0, tmp = [];
  while ( i < left.length && j < right.length ) {
    if ( left[i] <= right[j] ) {
      tmp[k++] = left[i++];
    } else {
      tmp[k++] = right[j++];
    }
  }
  while ( i < left.length ) {
    tmp[k++] = left[i++];   
  }
  while ( j < right.length ) {
    tmp[k++] = right[j++];
  }
  return tmp;
}