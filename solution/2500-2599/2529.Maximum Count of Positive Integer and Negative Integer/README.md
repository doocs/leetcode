# [2529. 正整数和负整数的最大计数](https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer)

[English Version](/solution/2500-2599/2529.Maximum%20Count%20of%20Positive%20Integer%20and%20Negative%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个按 <strong>非递减顺序</strong> 排列的数组 <code>nums</code> ，返回正整数数目和负整数数目中的最大值。</p>

<ul>
	<li>换句话讲，如果 <code>nums</code> 中正整数的数目是 <code>pos</code> ，而负整数的数目是 <code>neg</code> ，返回 <code>pos</code> 和 <code>neg</code>二者中的最大值。</li>
</ul>

<p><strong>注意：</strong><code>0</code> 既不是正整数也不是负整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-2,-1,-1,1,2,3]
<strong>输出：</strong>3
<strong>解释：</strong>共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-3,-2,-1,0,0,1,2]
<strong>输出：</strong>3
<strong>解释：</strong>共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,20,66,1314]
<strong>输出：</strong>4
<strong>解释：</strong>共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-2000 &lt;= nums[i] &lt;= 2000</code></li>
	<li><code>nums</code> 按 <strong>非递减顺序</strong> 排列。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumCount(self, nums: List[int]) -> int:
        a = sum(v > 0 for v in nums)
        b = sum(v < 0 for v in nums)
        return max(a, b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumCount(int[] nums) {
        int a = 0, b = 0;
        for (int v : nums) {
            if (v > 0) {
                ++a;
            }
            if (v < 0) {
                ++b;
            }
        }
        return Math.max(a, b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int a = 0, b = 0;
        for (int& v : nums) {
            if (v > 0) {
                ++a;
            }
            if (v < 0) {
                ++b;
            }
        }
        return max(a, b);
    }
};
```

### **Go**

```go
func maximumCount(nums []int) int {
	a, b := 0, 0
	for _, v := range nums {
		if v > 0 {
			a++
		}
		if v < 0 {
			b++
		}
	}
	return max(a, b)
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
