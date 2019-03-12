# 数组拆分 I

### 题目描述

给定长度为 **2n** 的数组, 你的任务是将这些数分成 **n** 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。

**示例 1**

```
输入: [1,4,3,2]
输出: 4
解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
```

**提示**

1. **n** 是正整数,范围在 [1, 10000].
2. 数组中的元素范围在 [-10000, 10000].

**提示1**

Obviously, brute force won't help here. Think of something else, take some example like 1,2,3,4.

显然，蛮力在这里无济于事。想想其他的东西，比如1,2,3,4。

**提示2**

How will you make pairs to get the result? There must be some pattern.

你将如何成对获得结果？ 必须有一些模式。

**提示3**

Did you observe that- Minimum element gets add into the result in sacrifice of maximum element.

您是否观察到 - 最小元素会牺牲最大元素而添加到结果中。

**提示4**

Still won't able to find pairs? Sort the array and try to find the pattern.

仍然无法找到对？ 对数组进行排序并尝试查找模式。

### 解题思路

**思路**

先排序，在查找配对的两个数中最小值求和。这里使用的是归并排序和根据数组下标为奇数求和，有一些取巧，但有更快的算法（例如算法里使用C的解法，复杂度为O（n）），但还没想明白。

**算法**

**JavaScript**

```javascript
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
```

**C**

```c
nt arrayPairSum(vector<int>& nums) {
        if( nums.size() == 0)
            return 0;
        int min = nums[0], max= nums[0];
        
        for( int i = 0; i < nums.size(); i++)
            if( nums[i] < min)
                min = nums[i];
           else if( nums[i] > max)
               max = nums[i];
        
        int *record = (int *)malloc( sizeof( int) * ( max - min +1));
        
        for( int i = 0; i< max - min + 1; i++)
            record[i] = 0;
        
        for( int i = 0; i < nums.size(); i++)
            record[ nums[i] - min]++;
        
        int maxSum = 0, i = 0;
        while(i < max-min+1){
            if( record[i] == 0){
                i++;
                continue;
            }
            if( record[i] > 1){
                maxSum += i + min;
                record[i] -=2;
                continue;
            }
            if( record[i] == 1){
                for( int j = i+1; j < max-min+1; j++)
                    if( record[j] != 0){
                        record[j]--, record[i]--;
                        maxSum += i + min;
                        i = j;
                        break;
                    }
                continue;
            }
                
        }
        return maxSum;
    }
```
**复杂度分析**
暂无