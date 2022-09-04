# [2401. 最长优雅子数组](https://leetcode.cn/problems/longest-nice-subarray)

[English Version](/solution/2400-2499/2401.Longest%20Nice%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>正</strong> 整数组成的数组 <code>nums</code> 。</p>

<p>如果&nbsp;<code>nums</code> 的子数组中位于 <strong>不同</strong> 位置的每对元素按位 <strong>与（AND）</strong>运算的结果等于 <code>0</code> ，则称该子数组为 <strong>优雅</strong> 子数组。</p>

<p>返回 <strong>最长</strong> 的优雅子数组的长度。</p>

<p><strong>子数组</strong> 是数组中的一个 <strong>连续</strong> 部分。</p>

<p><strong>注意：</strong>长度为 <code>1</code> 的子数组始终视作优雅子数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,8,48,10]
<strong>输出：</strong>3
<strong>解释：</strong>最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：
- 3 AND 8 = 0
- 3 AND 48 = 0
- 8 AND 48 = 0
可以证明不存在更长的优雅子数组，所以返回 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [3,1,5,11,13]
<strong>输出：</strong>1
<strong>解释：</strong>最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：滑动窗口**

时间复杂度 $O(n)$，其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        ans = j = t = 0
        for i, v in enumerate(nums):
            while t & v:
                t ^= nums[j]
                j += 1
            t |= v
            ans = max(ans, i - j + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int j = 0, t = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            while ((t & nums[i]) != 0) {
                t ^= nums[j++];
            }
            t |= nums[i];
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestNiceSubarray(vector<int>& nums) {
        int t = 0, j = 0;
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            while (t & nums[i]) {
                t ^= nums[j++];
            }
            t |= nums[i];
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestNiceSubarray(nums []int) int {
	t, j := 0, 0
	ans := 0
	for i, v := range nums {
		for (t & v) != 0 {
			t ^= nums[j]
			j++
		}
		t |= v
		ans = max(ans, i-j+1)
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

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
