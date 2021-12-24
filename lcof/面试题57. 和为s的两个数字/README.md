# [面试题 57. 和为 s 的两个数字](https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/)

## 题目描述

输入一个递增排序的数组和一个数字 s，在数组中查找两个数，使得它们的和正好是 s。如果有多对数字的和等于 s，则输出任意一对即可。

**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
```

**示例 2：**

```
输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]
```

**限制：**

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^6`

## 解法

哈希表或双指针实现。时间复杂度均为 `O(n)`。

哈希表空间复杂度 `O(n)`，双指针则是 `O(1)`。

<!-- tabs:start -->

### **Python3**

哈希表：

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        s = set()
        for num in nums:
            if target - num in s:
                return [num, target - num]
            s.add(num)
```

双指针：

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        p, q = 0, len(nums) - 1
        while p < q:
            s = nums[p] + nums[q]
            if s == target:
                return [nums[p], nums[q]]
            if s < target:
                p += 1
            else:
                q -= 1
```

### **Java**

哈希表：

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            if (s.contains(target - num)) {
                return new int[]{num, target - num};
            }
            s.add(num);
        }
        return null;
    }
}
```

双指针：

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int p = 0, q = nums.length - 1; p < q;) {
            int s = nums[p] + nums[q];
            if (s == target) {
                return new int[]{nums[p], nums[q]};
            }
            if (s < target) {
                ++p;
            } else {
                --q;
            }
        }
        return null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        for (int p = 0, q = nums.size() - 1; p < q;) {
            int s = nums[p] + nums[q];
            if (s == target) {
                return vector<int>{nums[p], nums[q]};
            }
            if (s < target) {
                ++p;
            } else {
                --q;
            }
        }
        return vector<int>{};
    }
};
```

### **Go**

```go
func twoSum(nums []int, target int) []int {
	for p, q := 0, len(nums)-1; p < q; {
		s := nums[p] + nums[q]
		if s == target {
			return []int{nums[p], nums[q]}
		}
		if s < target {
			p++
		} else {
			q--
		}
	}
	return nil
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    for (let p = 0, q = nums.length; p < q; ) {
        const s = nums[p] + nums[q];
        if (s == target) {
            return [nums[p], nums[q]];
        }
        if (s < target) {
            ++p;
        } else {
            --q;
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
