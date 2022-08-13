# [1508. 子数组和排序后的区间和](https://leetcode.cn/problems/range-sum-of-sorted-subarray-sums)

[English Version](/solution/1500-1599/1508.Range%20Sum%20of%20Sorted%20Subarray%20Sums/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;，它包含&nbsp;<code>n</code>&nbsp;个正整数。你需要计算所有非空连续子数组的和，并将它们按升序排序，得到一个新的包含&nbsp;<code>n * (n + 1) / 2</code>&nbsp;个数字的数组。</p>

<p>请你返回在新数组中下标为<em>&nbsp;</em><code>left</code>&nbsp;到&nbsp;<code>right</code> <strong>（下标从 1 开始）</strong>的所有数字和（包括左右端点）。由于答案可能很大，请你将它对 10^9 + 7 取模后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], n = 4, left = 1, right = 5
<strong>输出：</strong>13 
<strong>解释：</strong>所有的子数组和为 1, 3, 6, 10, 2, 5, 9, 3, 7, 4 。将它们升序排序后，我们得到新的数组 [1, 2, 3, 3, 4, 5, 6, 7, 9, 10] 。下标从 le = 1 到 ri = 5 的和为 1 + 2 + 3 + 3 + 4 = 13 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], n = 4, left = 3, right = 4
<strong>输出：</strong>6
<strong>解释：</strong>给定数组与示例 1 一样，所以新数组为 [1, 2, 3, 3, 4, 5, 6, 7, 9, 10] 。下标从 le = 3 到 ri = 4 的和为 3 + 3 = 6 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], n = 4, left = 1, right = 10
<strong>输出：</strong>50
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^3</code></li>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= left &lt;= right&nbsp;&lt;= n * (n + 1) / 2</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

按照题意生成 arr 数组，排序后，对 `[left-1, right-1]` 范围的所有元素求和，得到结果。

时间复杂度 O(n²logn)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rangeSum(self, nums: List[int], n: int, left: int, right: int) -> int:
        arr = []
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += nums[j]
                arr.append(s)
        arr.sort()
        MOD = 10**9 + 7
        return sum(arr[left - 1 : right]) % MOD
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] arr = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums[j];
                arr[idx++] = s;
            }
        }
        Arrays.sort(arr);
        int ans = 0;
        for (int i = left - 1; i < right; ++i) {
            ans = (ans + arr[i]) % MOD;
        }
        return ans;
    }
}
```

### **Go**

```go
func rangeSum(nums []int, n int, left int, right int) int {
	var arr []int
	for i := 0; i < n; i++ {
		s := 0
		for j := i; j < n; j++ {
			s += nums[j]
			arr = append(arr, s)
		}
	}
	sort.Ints(arr)
	mod := int(1e9) + 7
	ans := 0
	for i := left - 1; i < right; i++ {
		ans = (ans + arr[i]) % mod
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
