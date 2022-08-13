# [1558. 得到目标数组的最少函数调用次数](https://leetcode.cn/problems/minimum-numbers-of-function-calls-to-make-target-array)

[English Version](/solution/1500-1599/1558.Minimum%20Numbers%20of%20Function%20Calls%20to%20Make%20Target%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1558.Minimum%20Numbers%20of%20Function%20Calls%20to%20Make%20Target%20Array/images/sample_2_1887.png" style="height:294px; width:573px" /></p>

<p>给你一个与 <code>nums</code>&nbsp;大小相同且初始值全为 0 的数组 <code>arr</code> ，请你调用以上函数得到整数数组 <code>nums</code>&nbsp;。</p>

<p>请你返回将 <code>arr</code>&nbsp;变成 <code>nums</code>&nbsp;的最少函数调用次数。</p>

<p>答案保证在 32 位有符号整数以内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5]
<strong>输出：</strong>5
<strong>解释：</strong>给第二个数加 1 ：[0, 0] 变成 [0, 1] （1 次操作）。
将所有数字乘以 2 ：[0, 1] -&gt; [0, 2] -&gt; [0, 4] （2 次操作）。
给两个数字都加 1 ：[0, 4] -&gt; [1, 4] -&gt; <strong>[1, 5]</strong> （2 次操作）。
总操作次数为：1 + 2 + 2 = 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2]
<strong>输出：</strong>3
<strong>解释：</strong>给两个数字都加 1 ：[0, 0] -&gt; [0, 1] -&gt; [1, 1] （2 次操作）。
将所有数字乘以 2 ： [1, 1] -&gt; <strong>[2, 2]</strong> （1 次操作）。
总操作次数为： 2 + 1 = 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,2,5]
<strong>输出：</strong>6
<strong>解释：</strong>（初始）[0,0,0] -&gt; [1,0,0] -&gt; [1,0,1] -&gt; [2,0,2] -&gt; [2,1,2] -&gt; [4,2,4] -&gt; <strong>[4,2,5] </strong>（nums 数组）。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,2,4]
<strong>输出：</strong>7
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,8,16]
<strong>输出：</strong>8
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        return sum(v.bit_count() for v in nums) + max(0, max(nums).bit_length() - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        int mx = 0;
        for (int v : nums) {
            mx = Math.max(mx, v);
            ans += Integer.bitCount(v);
        }
        ans += Integer.toBinaryString(mx).length() - 1;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        int mx = 0;
        for (int v : nums) {
            mx = max(mx, v);
            ans += __builtin_popcount(v);
        }
        if (mx) ans += 31 - __builtin_clz(mx);
        return ans;
    }
};
```

### **Go**

```go
func minOperations(nums []int) int {
	ans, mx := 0, 0
	for _, v := range nums {
		mx = max(mx, v)
		for v > 0 {
			ans += v & 1
			v >>= 1
		}
	}
	if mx > 0 {
		for mx > 0 {
			ans++
			mx >>= 1
		}
		ans--
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
