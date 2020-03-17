# 按奇偶排序数组

### 题目描述

给定一个非负整数数组 `A`，返回一个由 `A` 的所有偶数元素组成的数组，后面跟 `A` 的所有奇数元素。

你可以返回满足此条件的任何数组作为答案。

**示例**

```
输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
```

**提示**

1. `1 <= A.length <= 5000`
2. `0 <= A[i] <= 5000`

### 解法

官方题解缺失

**第一种解法**

**思路**

将数组`A`中的奇数和偶数都分别的提取到一个数组中，最后进行合并数组操作。

**算法**

```javascript
var sortArrayByParity = function (A) {
  const len = A.length;
  if (len === 1) return A;
  let evenNumber = [], oddNumber = [];
  for (let i = 0; i < len; i++) {
    if (A[i] % 2 === 0) {
      evenNumber.push(A[i]);
    } else {
      oddNumber.push(A[i]);
    }
  }
  return evenNumber.concat(oddNumber);
};
```

**复杂度分析**

暂无

**第二种解法**

**思路**

利用数组实现一个特殊的队列，队列两头都可以进，前面进偶数，后面进奇数。

**算法**

```javascript
var sortArrayByParity = function (A) {
  const len = A.length;
  if (len === 1) return A;
  let eoNum = [];
  A.forEach((item, index, array) => {
    if (item % 2 === 1) {
      eoNum.push(item);
    } else {
      eoNum.unshift(item);
    }
  });
  return eoNum;
};
```

**复杂度分析**

暂无

**第三种解法**

**思路**

利用双指针`i`、`j`，`i`指向数组的开始，`j`指向数组的末尾，当`i` <`j`时，比较`A[i]`和`A[j]`,若`i`指向的为奇数，`j`指向的为偶数，交换`A[i]`和`A[j]`，`i`加一，`j`减一。若`A[i]`为偶数，`i`加一，不进行交换。若`A[j]`为奇数，`j`减一，不进行交换。

**算法**

```javascript
// 第一种思路
var sortArrayByParity = function (A) {
  const len = A.length;
  if (len === 1) return A;
  let i = 0, j = len - 1;
  while (i < j) {
    if ((A[i] % 2 === 1) && (A[j] % 2 === 0)) {
      let temp = A[j];
      A[j--] = A[i];
      A[i++] = temp;
    }
    if (A[i] % 2 === 0) i++;
    if (A[j] % 2 === 1) j--;
  }
  return A;
};
// 第二种思路
var sortArrayByParity = function (A) {
  const len = A.length;
  if (len === 1) return A;
  let i = 0, j = len - 1;
  while (i < j) {
    while ( A[i] % 2 === 0 ) i++;
    while ( A[j] % 2 === 1 ) j--;
    while( i < j && (A[i] % 2 === 1) && (A[j] % 2 === 0) ){
      let temp = A[j];
      A[j--] = A[i];
      A[i++] = temp;
    }
  }
  return A;
};
```

**复杂度分析**

暂无