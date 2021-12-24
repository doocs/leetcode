# [面试题 39. 数组中出现次数超过一半的数字](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/)

## 题目描述

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

**示例  1:**

```
输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2
```

**限制：**

- `1 <= 数组长度 <= 50000`

## 解法

摩尔投票法。时间复杂度 O(n)，空间复杂度 O(1)。

一般而言，摩尔投票法需要对输入的列表进行**两次遍历**。在第一次遍历中，我们生成候选值 candidate，如果存在多数，那么该候选值就是多数值。在第二次遍历中，只需要简单地计算候选值的频率，以确认是否是多数值。

接下来我们详细看下**第一次遍历**：

我们需要两个变量：`cnt`, `candidate`，其中 `cnt` 初始化为 0，`candidate` 初始化可以是任何值，这里我们设置为 0。

对于列表中的每个元素 num，我们首先检查计数值 cnt，

- 若 `cnt == 0`，我们将候选值 candidate 设置为当前元素值，即 `candidate = num`。
- 若 `candidate == num`，将 cnt 加 1，否则减 1。

**第二次遍历**，则是扫描列表中 candidate 出现的次数，若大于 `n/2`，则该候选值就是多数值，否则返回 -1。

注意：本题已经明确说明存在多数值，所以第一次遍历结束后，直接返回 candidate 即可，无需二次遍历确认是否是多数值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = candidate = 0
        for num in nums:
            if cnt == 0:
                candidate = num
            cnt += (1 if candidate == num else -1)
        return candidate
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, candidate = 0;
        for (int num : nums) {
            if (cnt == 0) {
                candidate = num;
            }
            cnt += (num == candidate ? 1 : -1);
        }
        return candidate;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0;
    let candidate = 0;
    for (const num of nums) {
        if (cnt == 0) {
            candidate = num;
        }
        cnt += candidate == num ? 1 : -1;
    }
    return candidate;
};
```

### **C++**

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int votes = 0, x = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += x == num ? 1 : -1;
        }
        return x;
    }
};
```

### **Go**

```go
func majorityElement(nums []int) int {
	var cnt, candidate int
	for _, num := range nums {
		if cnt == 0 {
			candidate = num
		}
		if candidate == num {
			cnt++
		} else {
			cnt--
		}
	}
	return candidate
}
```

### **C#**

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, candidate = 0;
        foreach (int num in nums)
        {
            if (cnt == 0)
            {
                candidate = num;
            }
            cnt += (candidate == num ? 1 : -1);
        }
        return candidate;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
