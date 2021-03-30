# [面试题 51. 数组中的逆序对](https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

**示例 1:**

```
输入: [7,5,6,4]
输出: 5
```

**限制：**

- `0 <= 数组长度 <= 50000`

## 解法

<!-- 这里可写通用的实现逻辑 -->

在归并中统计逆序对。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        self.res = 0

        def merge(part1, part2, nums):
            len1, len2 = len(part1) - 1, len(part2) - 1
            t = len(nums) - 1
            while len1 >= 0 and len2 >= 0:
                if part1[len1] > part2[len2]:
                    self.res += (len2 + 1)
                    nums[t] = part1[len1]
                    len1 -= 1
                else:
                    nums[t] = part2[len2]
                    len2 -= 1
                t -= 1
            while len1 >= 0:
                nums[t] = part1[len1]
                t -= 1
                len1 -= 1
            while len2 >= 0:
                nums[t] = part2[len2]
                t -= 1
                len2 -= 1

        def merge_sort(nums):
            if len(nums) < 2:
                return
            mid = len(nums) // 2
            s1, s2 = nums[:mid], nums[mid:]
            merge_sort(s1)
            merge_sort(s2)
            merge(s1, s2, nums)

        merge_sort(nums)
        return self.res

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int res = 0;
    public int reversePairs(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        mergeSort(nums, 0, n - 1);
        return res;
    }

    private void mergeSort(int[] nums, int s, int e) {
        if (s == e) {
            return;
        }
        int mid = (s + e) >>> 1;
        mergeSort(nums, s, mid);
        mergeSort(nums, mid + 1, e);
        merge(nums, s, mid, e);
    }

    private void merge(int[] nums, int s, int mid, int e) {
        int n = e - s + 1;
        int[] help = new int[n];
        int i = s, j = mid + 1, idx = 0;
        while (i <= mid && j <= e) {
            if (nums[i] > nums[j]) {
                res += (mid - i + 1);
                help[idx++] = nums[j++];
            } else {
                help[idx++] = nums[i++];
            }
        }
        while (i <= mid) {
            help[idx++] = nums[i++];
        }
        while (j <= e) {
            help[idx++] = nums[j++];
        }
        for (int t = 0; t < n; ++t) {
            nums[s + t] = help[t];
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function (nums) {
  if (!nums || nums.length < 2) return 0;
  let res = 0;
  function mergeSort(arr) {
    if (arr.length === 1) {
      return arr;
    }
    let mid = ~~(arr.length / 2);
    return merge(mergeSort(arr.slice(0, mid)), mergeSort(arr.slice(mid)));
  }
  function merge(a, b) {
    let r = [];
    let cnt = 0;
    while (a && b && a.length && b.length) {
      if (a[0] <= b[0]) {
        res += cnt;
        r.push(a.shift());
      } else {
        r.push(b.shift());
        cnt++;
      }
    }
    res += a.length * cnt;
    return r.concat(a, b);
  }
  mergeSort(nums);
  return res;
};
```

### **...**

```

```

<!-- tabs:end -->
