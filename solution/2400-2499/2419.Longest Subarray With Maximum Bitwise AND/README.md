# [2419. 按位与最大的最长子数组](https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and)

[English Version](/solution/2400-2499/2419.Longest%20Subarray%20With%20Maximum%20Bitwise%20AND/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> 。</p>

<p>考虑 <code>nums</code> 中进行 <strong>按位与（bitwise AND）</strong>运算得到的值 <strong>最大</strong> 的 <strong>非空</strong> 子数组。</p>

<ul>
	<li>换句话说，令 <code>k</code> 是 <code>nums</code> <strong>任意</strong> 子数组执行按位与运算所能得到的最大值。那么，只需要考虑那些执行一次按位与运算后等于 <code>k</code> 的子数组。</li>
</ul>

<p>返回满足要求的 <strong>最长</strong> 子数组的长度。</p>

<p>数组的按位与就是对数组中的所有数字进行按位与运算。</p>

<p><strong>子数组</strong> 是数组中的一个连续元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,3,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>
子数组按位与运算的最大值是 3 。
能得到此结果的最长子数组是 [3,3]，所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>1
<strong>解释：</strong>
子数组按位与运算的最大值是 4 。 
能得到此结果的最长子数组是 [4]，所以返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

由于按位与的操作，不会使得数字变大，因此最大值就是数组中的最大值。

题目可以转换为求最大值在数组中最多连续出现的次数。

先遍历一遍数组，求出最大值，然后再遍历一遍数组，求出最大值连续出现的次数，最后返回这个次数即可。

时间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        mx = max(nums)
        ans = cnt = 0
        for v in nums:
            if v == mx:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 0
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int mx = 0;
        for (int v : nums) {
            mx = Math.max(mx, v);
        }
        int ans = 0, cnt = 0;
        for (int v : nums) {
            if (v == mx) {
                ++cnt;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0;
            }
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
        int mx = *max_element(nums.begin(), nums.end());
        int ans = 0, cnt = 0;
        for (int v : nums) {
            if (v == mx) {
                ++cnt;
                ans = max(ans, cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestSubarray(nums []int) int {
	mx := 0
	for _, v := range nums {
		mx = max(mx, v)
	}
	ans, cnt := 0, 0
	for _, v := range nums {
		if v == mx {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 0
		}
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
