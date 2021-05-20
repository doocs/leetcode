# [面试题 21. 调整数组顺序使奇数位于偶数前面](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

## 题目描述

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

**示例：**

```
输入：nums = [1,2,3,4]
输出：[1,3,2,4]
注：[3,1,2,4] 也是正确的答案之一。
```

**提示：**

- 1 <= nums.length <= 50000
- 1 <= nums[i] <= 10000

## 解法

双指针。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exchange(self, nums: List[int]) -> List[int]:
        p, q = 0, len(nums) - 1
        while p < q:
            if nums[p] & 1 == 1:
                p += 1
                continue
            if nums[q] & 1 == 0:
                q -= 1
                continue
            nums[p], nums[q] = nums[q], nums[p]
        return nums
```

### **Java**

```java
class Solution {
    public int[] exchange(int[] nums) {
        int p = 0, q = nums.length - 1;
        while (p < q) {
            if ((nums[p] & 1) == 1) {
                ++p;
                continue;
            }
            if ((nums[q] & 1) == 0) {
                --q;
                continue;
            }
            swap(nums, p, q);
        }
        return nums;
    }

    private void swap(int[] nums, int p, int q) {
        int t = nums[p];
        nums[p] = nums[q];
        nums[q] = t;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var exchange = function (nums) {
  let left = 0;
  let right = nums.length - 1;
  while (left < right) {
    let c = nums[left];
    nums[left] = nums[right];
    nums[right] = c;
    while (nums[left] % 2) {
      left++;
    }
    while (nums[right] % 2 === 0) {
      right--;
    }
  }
  return nums;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> exchange(vector<int>& nums) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) == 1) {
                ++left;
            }
            while (left < right && (nums[right] & 1) == 0) {
                --right;
            }
            swap(nums[left], nums[right]);
        }
        return nums;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
