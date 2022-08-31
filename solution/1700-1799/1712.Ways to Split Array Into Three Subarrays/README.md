# [1712. 将数组分成三个子数组的方案数](https://leetcode.cn/problems/ways-to-split-array-into-three-subarrays)

[English Version](/solution/1700-1799/1712.Ways%20to%20Split%20Array%20Into%20Three%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们称一个分割整数数组的方案是 <strong>好的</strong> ，当它满足：</p>

<ul>
	<li>数组被分成三个 <strong>非空</strong> 连续子数组，从左至右分别命名为 <code>left</code> ， <code>mid</code> ， <code>right</code> 。</li>
	<li><code>left</code> 中元素和小于等于 <code>mid</code> 中元素和，<code>mid</code> 中元素和小于等于 <code>right</code> 中元素和。</li>
</ul>

<p>给你一个 <strong>非负</strong> 整数数组 <code>nums</code> ，请你返回 <strong>好的</strong> 分割 <code>nums</code> 方案数目。由于答案可能会很大，请你将结果对 <code>10<sup>9 </sup>+ 7</code> 取余后返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1]
<b>输出：</b>1
<b>解释：</b>唯一一种好的分割方案是将 nums 分成 [1] [1] [1] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,2,2,5,0]
<b>输出：</b>3
<b>解释：</b>nums 总共有 3 种好的分割方案：
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,1]
<b>输出：</b>0
<b>解释：</b>没有好的分割方案。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 二分查找**

计算数组 `nums` 的前缀和 `s`。由于 `nums[i]` 是非负整数，可以得知 `s` 是一个单调递增数组。

我们枚举 `left` 子数组所能到达的下标，记为 `i`。然后二分查找 `mid` 子数组分割的合理范围，记为 `[j0, j1)`，累加方案数 `j1-j0`。注意答案取模操作。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$，其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def waysToSplit(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        s = list(accumulate(nums))
        ans, n = 0, len(nums)
        for i in range(n - 2):
            j0 = bisect_left(s, s[i] * 2, i + 1, n - 1)
            j1 = bisect_right(s, (s[-1] + s[i]) // 2, j0, n - 1)
            ans += j1 - j0
        return ans % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;
    
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            s[i] = s[i - 1] + nums[i];
        }
        int ans = 0;
        for (int i = 0; i < n - 2; ++i) {
            int j0 = lowerBound(s, s[i] * 2, i + 1, n - 1);
            int j1 = lowerBound(s, (s[i] + s[n - 1]) / 2 + 1, j0, n - 1);
            ans = (ans + j1 - j0) % MOD;
        }
        return ans;
    }

    private int lowerBound(int[] s, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int waysToSplit(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n, nums[0]);
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + nums[i];
        int ans = 0;
        int mod = 1e9 + 7;
        for (int i = 0; i < n - 2; ++i) {
            int j0 = lower_bound(s.begin() + i + 1, s.begin() + n - 1, s[i] * 2) - s.begin();
            int j1 = upper_bound(s.begin() + j0, s.begin() + n - 1, (s[i] + s[n - 1]) / 2) - s.begin();
            ans = (ans + j1 - j0) % mod;
        }
        return ans;
    }
};
```

### ****

```go
func waysToSplit(nums []int) int {
	search := func(s []int, x, left, right int) int {
		for left < right {
			mid := (left + right) >> 1
			if s[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}
	var mod int = 1e9 + 7
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + nums[i]
	}
	ans := 0
	for i := 0; i < n-2; i++ {
		j0 := search(s, s[i]*2, i+1, n-1)
		j1 := search(s, (s[i]+s[n-1])/2+1, j0, n-1)
		ans += j1 - j0
	}
	ans %= mod
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
