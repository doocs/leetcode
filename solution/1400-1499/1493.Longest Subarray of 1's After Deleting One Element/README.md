# [1493. 删掉一个元素以后全为 1 的最长子数组](https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element)

[English Version](/solution/1400-1499/1493.Longest%20Subarray%20of%201%27s%20After%20Deleting%20One%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制数组&nbsp;<code>nums</code>&nbsp;，你需要从中删掉一个元素。</p>

<p>请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。</p>

<p>如果不存在这样的子数组，请返回 0 。</p>

<p>&nbsp;</p>

<p><strong>提示 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,0,1]
<strong>输出：</strong>3
<strong>解释：</strong>删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,1,1,0,1,1,0,1]
<strong>输出：</strong>5
<strong>解释：</strong>删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1]
<strong>输出：</strong>2
<strong>解释：</strong>你必须要删除一个元素。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

枚举删除的位置 $i$，那么每一个位置 $i$ 的最长子数组长度为 $i$ 左边连续的 $1$ 的个数加上 $i$ 右边连续的 $1$ 的个数。

因此，我们可以先遍历一遍数组，统计每个位置 $i$ 左边连续的 $1$ 的个数，记录在 `left` 数组；然后再从右向左遍历一遍数组，统计每个位置 $i$ 右边连续的 $1$ 的个数，记录在 `right` 数组，最后枚举删除的位置 $i$，求出最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        left = [0] * n
        right = [0] * n
        for i in range(1, n):
            if nums[i - 1] == 1:
                left[i] = left[i - 1] + 1
        for i in range(n - 2, -1, -1):
            if nums[i + 1] == 1:
                right[i] = right[i + 1] + 1
        return max(a + b for a, b in zip(left, right))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] == 1) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i + 1] == 1) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, left[i] + right[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n);
        vector<int> right(n);
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] == 1) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; ~i; --i) {
            if (nums[i + 1] == 1) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, left[i] + right[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestSubarray(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := 1; i < n; i++ {
		if nums[i-1] == 1 {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 2; i >= 0; i-- {
		if nums[i+1] == 1 {
			right[i] = right[i+1] + 1
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		ans = max(ans, left[i]+right[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
