# [1262. 可被三整除的最大和](https://leetcode.cn/problems/greatest-sum-divisible-by-three)

[English Version](/solution/1200-1299/1262.Greatest%20Sum%20Divisible%20by%20Three/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，请你找出并返回能被三整除的元素最大和。</p>

<ol>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,6,5,1,8]
<strong>输出：</strong>18
<strong>解释：</strong>选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [4]
<strong>输出：</strong>0
<strong>解释：</strong>4 不能被 3 整除，所以无法选出数字，返回 0。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,4]
<strong>输出：</strong>12
<strong>解释：</strong>选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 4 * 10^4</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[j]$ 表示前 $i-1$ 个数中，余数为 $j$ 的最大和。那么对于当前的数 $x$，我们可以将其加入到前面的数中，得到的和为 $f[j] + x$，其中 $j$ 为 $(f[j] + x) \bmod 3$。

最后，我们返回 $f[0]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        f = [0] * 3
        for x in nums:
            a, b, c = f[0] + x, f[1] + x, f[2] + x
            f[a % 3] = max(f[a % 3], a)
            f[b % 3] = max(f[b % 3], b)
            f[c % 3] = max(f[c % 3], c)
        return f[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] f = new int[3];
        for (int x : nums) {
            int a = f[0] + x, b = f[1] + x, c = f[2] + x;
            f[a % 3] = Math.max(f[a % 3], a);
            f[b % 3] = Math.max(f[b % 3], b);
            f[c % 3] = Math.max(f[c % 3], c);
        }
        return f[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumDivThree(vector<int>& nums) {
        int f[3]{};
        for (int x : nums) {
            int a = f[0] + x, b = f[1] + x, c = f[2] + x;
            f[a % 3] = max(f[a % 3], a);
            f[b % 3] = max(f[b % 3], b);
            f[c % 3] = max(f[c % 3], c);
        }
        return f[0];
    }
};
```

### **Go**

```go
func maxSumDivThree(nums []int) int {
	f := [3]int{}
	for _, x := range nums {
		a, b, c := f[0]+x, f[1]+x, f[2]+x
		f[a%3] = max(f[a%3], a)
		f[b%3] = max(f[b%3], b)
		f[c%3] = max(f[c%3], c)
	}
	return f[0]
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
