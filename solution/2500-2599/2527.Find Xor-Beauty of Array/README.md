# [2527. 查询数组 Xor 美丽值](https://leetcode.cn/problems/find-xor-beauty-of-array)

[English Version](/solution/2500-2599/2527.Find%20Xor-Beauty%20of%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>三个下标&nbsp;<code>i</code>&nbsp;，<code>j</code>&nbsp;和&nbsp;<code>k</code>&nbsp;的 <strong>有效值</strong>&nbsp;定义为&nbsp;<code>((nums[i] | nums[j]) &amp; nums[k])</code>&nbsp;。</p>

<p>一个数组的 <strong>xor 美丽值</strong>&nbsp;是数组中所有满足&nbsp;<code>0 &lt;= i, j, k &lt; n</code>&nbsp;&nbsp;<strong>的三元组</strong>&nbsp;<code>(i, j, k)</code>&nbsp;的 <strong>有效值</strong>&nbsp;的异或结果。</p>

<p>请你返回&nbsp;<code>nums</code>&nbsp;的 xor 美丽值。</p>

<p><b>注意：</b></p>

<ul>
	<li><code>val1 | val2</code>&nbsp;是&nbsp;<code>val1</code> 和&nbsp;<code>val2</code>&nbsp;的按位或。</li>
	<li><code>val1 &amp; val2</code>&nbsp;是&nbsp;<code>val1</code> 和&nbsp;<code>val2</code>&nbsp;的按位与。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,4]
<b>输出：</b>5
<b>解释：</b>
三元组和它们对应的有效值如下：
- (0,0,0) 有效值为 ((1 | 1) &amp; 1) = 1
- (0,0,1) 有效值为 ((1 | 1) &amp; 4) = 0
- (0,1,0) 有效值为 ((1 | 4) &amp; 1) = 1
- (0,1,1) 有效值为 ((1 | 4) &amp; 4) = 4
- (1,0,0) 有效值为 ((4 | 1) &amp; 1) = 1
- (1,0,1) 有效值为 ((4 | 1) &amp; 4) = 4
- (1,1,0) 有效值为 ((4 | 4) &amp; 1) = 0
- (1,1,1) 有效值为 ((4 | 4) &amp; 4) = 4 
数组的 xor 美丽值为所有有效值的按位异或 1 ^ 0 ^ 1 ^ 4 ^ 1 ^ 4 ^ 0 ^ 4 = 5 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [15,45,20,2,34,35,5,44,32,30]
<b>输出：</b>34
<code><span style=""><b>解释：</b>数组的 xor 美丽值为</span> 34 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

我们首先考虑 $i$ 与 $j$ 不相等的情况，此时 $(nums[i] | nums[j]) \& nums[k]$ 与 $(nums[j] | nums[i]) \& nums[k]$ 的结果是相同的，两者的异或结果为 $0$。

因此，我们只需要考虑 $i$ 与 $j$ 相等的情况。此时 $(nums[i] | nums[j]) \& nums[k] = nums[i] \& nums[k]$，如果 $i \neq k$，那么与 $nums[k] \& nums[i]$ 的结果是相同的，这些值的异或结果为 $0$。

因此，我们最终只需要考虑 $i = j = k$ 的情况，那么答案就是所有 $nums[i]$ 的异或结果。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def xorBeauty(self, nums: List[int]) -> int:
        return reduce(xor, nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int xorBeauty(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int xorBeauty(vector<int>& nums) {
        int ans = 0;
        for (auto& x : nums) {
            ans ^= x;
        }
        return ans;
    }
};
```

### **Go**

```go
func xorBeauty(nums []int) (ans int) {
	for _, x := range nums {
		ans ^= x
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
