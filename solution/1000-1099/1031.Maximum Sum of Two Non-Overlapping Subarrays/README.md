# [1031. 两个非重叠子数组的最大和](https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays)

[English Version](/solution/1000-1099/1031.Maximum%20Sum%20of%20Two%20Non-Overlapping%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出非负整数数组 <code>A</code> ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 <code>L</code> 和 <code>M</code>。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）</p>

<p>从形式上看，返回最大的 <code>V</code>，而 <code>V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1])</code> 并满足下列条件之一：</p>

<p>&nbsp;</p>

<ul>
	<li><code>0 &lt;= i &lt; i + L - 1 &lt; j &lt; j + M - 1 &lt; A.length</code>, <strong>或</strong></li>
	<li><code>0 &lt;= j &lt; j + M - 1 &lt; i &lt; i + L - 1 &lt; A.length</code>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
<strong>输出：</strong>20
<strong>解释：</strong>子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
<strong>输出：</strong>29
<strong>解释：</strong>子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
<strong>输出：</strong>31
<strong>解释：</strong>子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>L &gt;= 1</code></li>
	<li><code>M &gt;= 1</code></li>
	<li><code>L + M &lt;= A.length &lt;= 1000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 枚举**

我们先预处理得到数组 `nums` 的前缀和数组 $s$，其中 $s[i]$ 表示 $nums$ 中前 $i$ 个元素的和。

接下来，我们分两种情况枚举：

假设 $firstLen$ 个元素的子数组在 $secondLen$ 个元素的子数组的左边，那么我们可以枚举 $secondLen$ 个元素的子数组的左端点 $i$，用变量 $t$ 维护左边 $firstLen$ 个元素的子数组的最大和，那么答案就是 $t + s[i + secondLen] - s[i]$。枚举完所有的 $i$，就可以得到候选答案。

假设 $secondLen$ 个元素的子数组在 $firstLen$ 个元素的子数组的左边，那么我们可以枚举 $firstLen$ 个元素的子数组的左端点 $i$，用变量 $t$ 维护左边 $secondLen$ 个元素的子数组的最大和，那么答案就是 $t + s[i + firstLen] - s[i]$。枚举完所有的 $i$，就可以得到候选答案。

最后，我们取两种情况下的候选答案的最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        ans = t = 0
        i = firstLen
        while i + secondLen - 1 < n:
            t = max(t, s[i] - s[i - firstLen])
            ans = max(ans, t + s[i + secondLen] - s[i])
            i += 1
        t = 0
        i = secondLen
        while i + firstLen - 1 < n:
            t = max(t, s[i] - s[i - secondLen])
            ans = max(ans, t + s[i + firstLen] - s[i])
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = 0;
        for (int i = firstLen, t = 0; i + secondLen - 1 < n; ++i) {
            t = Math.max(t, s[i] - s[i - firstLen]);
            ans = Math.max(ans, t + s[i + secondLen] - s[i]);
        }
        for (int i = secondLen, t = 0; i + firstLen - 1 < n; ++i) {
            t = Math.max(t, s[i] - s[i - secondLen]);
            ans = Math.max(ans, t + s[i + firstLen] - s[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumTwoNoOverlap(vector<int>& nums, int firstLen, int secondLen) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = 0;
        for (int i = firstLen, t = 0; i + secondLen - 1 < n; ++i) {
            t = max(t, s[i] - s[i - firstLen]);
            ans = max(ans, t + s[i + secondLen] - s[i]);
        }
        for (int i = secondLen, t = 0; i + firstLen - 1 < n; ++i) {
            t = max(t, s[i] - s[i - secondLen]);
            ans = max(ans, t + s[i + firstLen] - s[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSumTwoNoOverlap(nums []int, firstLen int, secondLen int) (ans int) {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for i, t := firstLen, 0; i+secondLen-1 < n; i++ {
		t = max(t, s[i]-s[i-firstLen])
		ans = max(ans, t+s[i+secondLen]-s[i])
	}
	for i, t := secondLen, 0; i+firstLen-1 < n; i++ {
		t = max(t, s[i]-s[i-secondLen])
		ans = max(ans, t+s[i+firstLen]-s[i])
	}
	return
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
