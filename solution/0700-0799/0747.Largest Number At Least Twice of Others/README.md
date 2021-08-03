# [747. 至少是其他数字两倍的最大数](https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others)

[English Version](/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个给定的数组<code>nums</code>中，总是存在一个最大元素 。</p>

<p>查找数组中的最大元素是否至少是数组中每个其他数字的两倍。</p>

<p>如果是，则返回最大元素的索引，否则返回-1。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [3, 6, 1, 0]
<strong>输出:</strong> 1
<strong>解释:</strong> 6是最大的整数, 对于数组中的其他整数,
6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
</pre>

<p>&nbsp;</p>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums = [1, 2, 3, 4]
<strong>输出:</strong> -1
<strong>解释:</strong> 4没有超过3的两倍大, 所以我们返回 -1.
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ol>
	<li><code>nums</code>&nbsp;的长度范围在<code>[1, 50]</code>.</li>
	<li>每个&nbsp;<code>nums[i]</code>&nbsp;的整数范围在&nbsp;<code>[0, 100]</code>.</li>
</ol>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        max_idx, n = 0, len(nums)
        for i in range(1, n):
            if nums[i] > nums[max_idx]:
                max_idx = i
        for i in range(n):
            if i != max_idx and nums[i] * 2 > nums[max_idx]:
                return -1
        return max_idx
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
  public int dominantIndex(int[] nums) {
    int maxIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > nums[maxIndex])
        maxIndex = i;
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] * 2 > nums[maxIndex] && i != maxIndex)
        return -1;
    }
    return maxIndex;
  }
}
```

### **C++**

```cpp
class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int maxIdx = 0, n = nums.size();
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        for (int i = 0; i < n; ++i) {
            if (i != maxIdx && nums[i] * 2 > nums[maxIdx]) return -1;
        }
        return maxIdx;
    }
};
```

### **Go**

```go
func dominantIndex(nums []int) int {
	maxIndex, n := 0, len(nums)
	for i := 1; i < n; i++ {
		if nums[i] > nums[maxIndex] {
			maxIndex = i
		}
	}
	for i := 0; i < n; i++ {
		if i != maxIndex && nums[i]*2 > nums[maxIndex] {
			return -1
		}
	}
	return maxIndex
}
```

### **...**

```

```

<!-- tabs:end -->
