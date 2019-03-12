/**
 * @param {number[]} A
 * @return {number[]}
 */
/**
 * Author: Mcnwork2018
 */
// 第一种解法
var sortedSquares = function(A) {
  let results = A.map((item, index, array) => {
    return item *= item;
  });
  results.sort((v1, v2) => {
    return v1 -v2;       
  });
  return results;
};
// 第二种解法
var sortedSquares = function(A){
  let len = A.length;     // 数组长度
  let j = 0;              // j 正数开始
  while ( j < len && A[j] < 0 ) {
    j++;
  }
  let i = j - 1;          // i 负数开始
  let results = [];       // 存放最终结果
  let t = 0;              // results下标
  while ( i >= 0 && j < len ) {
    if ( A[i] * A[i] < A[j] * A[j] ) {
			results[t++] = A[i] * A[i];
			i--;
    } else {
			results[t++] = A[j] * A[j];
			j++;
    }
  }
  while	( i >= 0 )	{
		results[t++] = A[i] * A[i];
		i--;
  }
	while	( j < len )	{
		results[t++] = A[j] * A[j];
		j++;
  }
  return results;
}