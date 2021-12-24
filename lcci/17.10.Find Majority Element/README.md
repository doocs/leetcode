# [面试题 17.10. 主要元素](https://leetcode-cn.com/problems/find-majority-element-lcci)

[English Version](/lcci/17.10.Find%20Majority%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>如果数组中多一半的数都是同一个，则称之为主要元素。给定一个<strong>整数</strong>数组，找到它的主要元素。若没有，返回-1。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[1,2,5,9,5,9,5,5,5]
<strong>输出：</strong>5</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[3,2]
<strong>输出：</strong>-1</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[2,2,1,1,1,2,2]
<strong>输出：</strong>2</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong><br>
你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

摩尔投票法。时间复杂度 O(n)，空间复杂度 O(1)。

一般而言，摩尔投票法需要对输入的列表进行**两次遍历**。在第一次遍历中，我们生成候选值 candidate，如果存在多数，那么该候选值就是多数值。在第二次遍历中，只需要简单地计算候选值的频率，以确认是否是多数值。

接下来我们详细看下**第一次遍历**：

我们需要两个变量：`cnt`, `candidate`，其中 `cnt` 初始化为 0，`candidate` 初始化可以是任何值，这里我们设置为 0。

对于列表中的每个元素 num，我们首先检查计数值 cnt，

- 若 `cnt == 0`，我们将候选值 candidate 设置为当前元素值，即 `candidate = num`。
- 若 `candidate == num`，将 cnt 加 1，否则减 1。

**第二次遍历**，则是扫描列表中 candidate 出现的次数，若大于 `n/2`，则该候选值就是多数值，否则返回 -1。

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
        return candidate if nums.count(candidate) > len(nums) / 2 else -1
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
        cnt = 0;
        for (int num : nums) {
            if (num == candidate) {
                ++cnt;
            }
        }
        return cnt > nums.length / 2 ? candidate : -1;
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
    cnt = 0;
    for (const num of nums) {
        if (candidate == num) {
            ++cnt;
        }
    }
    return cnt > nums.length / 2 ? candidate : -1;
};
```

### **C++**

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, candidate = 0;
        for (int num : nums)
        {
            if (cnt == 0) candidate = num;
            cnt += (candidate == num ? 1 : -1);
        }
        cnt = count(nums.begin(), nums.end(), candidate);
        return cnt > nums.size() / 2 ? candidate : -1;
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
	cnt = 0
	for _, num := range nums {
		if candidate == num {
			cnt++
		}
	}
	if cnt > len(nums)/2 {
		return candidate
	}
	return -1
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
        cnt = 0;
        foreach (int num in nums)
        {
            if (candidate == num)
            {
                ++cnt;
            }
        }
        return cnt > nums.Length / 2 ? candidate : -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
