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

**哈希表**

遍历数组，查看哈希表中是否存在对应的差值（`target` - 遍历元素）：
- 存在，即 `return` 返回。
- 不存在，记录元素，继续遍历。

*复杂度*：

- 时间 ***O(N)***
- 空间 ***O(N)***

**双指针**

1. 声明头尾指针（数组的左右两端）。
2. 将头尾指针所指向的元素相加，与 `target` 比较：
    - 大于：尾指针前移。
    - 小于：头指针后移。
    - 等于：返回两个元素即可。
3. 重复步骤 2，直到等于为止。

> 因为数组是有序的，指针变动对值的影响可预测。

*复杂度*：

- 时间 ***O(N)***
- 空间 ***O(1)***

```txt
TWO-SUM(A,t)
    l = 0
    r = A.length - 1
    while A[l] + A[r] != t
        if A[l] + A[r] < t
            l = l + 1
        else r = r - 1
    return [A[l], A[r]]
```

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

### **TypeScript**

哈希表：

```ts
function twoSum(nums: number[], target: number): number[] {
    const set = new Set();
    for (const num of nums) {
        if (set.has(target - num)) {
            return [target - num, num]
        }
        set.add(num)
    }
    return null
};
```

双指针：

```ts
function twoSum(nums: number[], target: number): number[] {
    let l = 0;
    let r = nums.length - 1;
    while (nums[l] + nums[r] !== target) {
        if (nums[l] + nums[r] < target) {
            l++;
        } else {
            r--;
        }
    }
    return [nums[l], nums[r]];
}
```

### **Rust**

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut l = 0;
        let mut r = nums.len() - 1;
        loop {
            match target.cmp(&(nums[l] + nums[r])) {
                Ordering::Less => r -= 1,
                Ordering::Greater => l += 1,
                Ordering::Equal => break vec![nums[l], nums[r]],
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
