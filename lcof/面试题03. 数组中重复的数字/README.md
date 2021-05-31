# [面试题 03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述

找出数组中重复的数字。

在一个长度为 n 的数组 nums 里的所有数字都在 0 ～ n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

**示例 1：**

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3
```

**限制：**

```
2 <= n <= 100000
```

## 解法

0 ～ n-1 范围内的数，分别还原到对应的位置上，如：数字 2 交换到下标为 2 的位置。

若交换过程中发现重复，则直接返回。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRepeatNumber(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            while i != num:
                if num == nums[num]:
                    return num
                nums[i], nums[num] = nums[num], nums[i]
                num = nums[i]
        return -1
```

### **Java**

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0, n = nums.length; i < n; ++i) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) return nums[i];
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **Kotlin**

```kotlin
class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        for (i in nums.indices) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        var t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findRepeatNumber = function (nums) {
  let m = {};
  for (let num of nums) {
    if (m[num]) return num;
    m[num] = 1;
  }
};
```

### **Go**

```go
func findRepeatNumber(nums []int) int {
    for i := 0; i < len(nums); i++ {
        for nums[i] != i {
            if nums[i] == nums[nums[i]] {
                return nums[i]
            }
            nums[i], nums[nums[i]] = nums[nums[i]], nums[i]
        }
    }
    return -1
}
```

### **C++**

```cpp
class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
        int len = nums.size();
        for (int i = 0; i < len; i++) {
            while (i != nums[i]) {
                // 这一位的值，不等于这一位的数字
                if (nums[i] == nums[nums[i]]) {
                    // 如果在交换的过程中，发现了相等的数字，直接返回
                    return nums[i];
                }

                swap(nums[i], nums[nums[i]]);
            }
        }

        return 0;
    }
};
```

### **TypeScript**

```ts
function findRepeatNumber(nums: number[]): number {
    let n: number = nums.length;
    for (let i: number = 0; i < n; i++) {
        while (nums[i] != i) {
            if (nums[i] == nums[nums[i]]) return nums[i];
            swap(nums, i, nums[i]);
        }
    }
    return -1;
};

function swap (nums: number[], i: number, j: number): void {
    [nums[i], nums[j]] = [nums[j], nums[i]];
}
```

### **...**

```

```

<!-- tabs:end -->
