---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1121.Divide%20Array%20Into%20Increasing%20Sequences/README.md
rating: 1664
source: 第 4 场双周赛 Q4
tags:
    - 数组
    - 计数
---

<!-- problem:start -->

# [1121. 将数组分成几个递增序列 🔒](https://leetcode.cn/problems/divide-array-into-increasing-sequences)

[English Version](/solution/1100-1199/1121.Divide%20Array%20Into%20Increasing%20Sequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>非递减</strong> 的正整数数组&nbsp;<code>nums</code>&nbsp;和整数&nbsp;<code>K</code>，判断该数组是否可以被分成一个或几个&nbsp;<strong>长度至少&nbsp;为 </strong><code>K</code><strong> 的 不相交的递增子序列</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,2,3,3,4,4], K = 3
<strong>输出：</strong>true
<strong>解释：</strong>
该数组可以分成两个子序列 [1,2,3,4] 和 [2,3,4]，每个子序列的长度都至少是 3。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,6,6,7,8], K = 3
<strong>输出：</strong>false
<strong>解释：</strong>
没有办法根据条件来划分数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= nums.length&nbsp;&lt;= 10^5</code></li>
	<li><code>1 &lt;= K &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^5</code></li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

<!-- thinking:start -->

> **思考**
>
> 数组已非递减，严格递增子序列不能含重复值，故出现次数最多的值必须分到不同子序列。若最多出现 $cnt$ 次，则至少需要 $cnt$ 条长度不小于 $k$ 的子序列，因而 $cnt\times k\le n$ 是充要条件。
>
> 非递减数组中相同值连续，用 `groupby` 即可得到最大段长，不必再开哈希表。

<!-- thinking:end -->

我们假设可以将数组分成 $m$ 个长度至少为 $k$ 的严格递增子序列，如果数组中出现次数最多的数字的个数为 $cnt$，那么这 $cnt$ 个数字必须在不同的子序列中，所以 $m \geq cnt$，又因为 $m$ 个子序列的长度至少为 $k$，因此，子序列的个数越少越好，所以 $m = cnt$。那么 $cnt \times k \leq n$，才能满足题意。因此，我们只需要统计数组中出现次数最多的数字的个数 $cnt$，然后判断 $cnt \times k \leq n$ 即可。如果是，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canDivideIntoSubsequences(self, nums: List[int], k: int) -> bool:
        mx = max(len(list(x)) for _, x in groupby(nums))
        return mx * k <= len(nums)
```

#### Java

```java
class Solution {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, cnt.merge(x, 1, Integer::sum));
        }
        return mx * k <= nums.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canDivideIntoSubsequences(vector<int>& nums, int k) {
        int cnt = 0;
        int a = 0;
        for (int& b : nums) {
            cnt = a == b ? cnt + 1 : 1;
            if (cnt * k > nums.size()) {
                return false;
            }
            a = b;
        }
        return true;
    }
};
```

#### Go

```go
func canDivideIntoSubsequences(nums []int, k int) bool {
	cnt, a := 0, 0
	for _, b := range nums {
		cnt++
		if a != b {
			cnt = 1
		}
		if cnt*k > len(nums) {
			return false
		}
		a = b
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一先求全局最大频次再与 $n$ 比较。方法二扫描时维护当前相同值的连续长度，一旦 $cnt\times k>n$ 即可提前返回假。充要条件不变，实现改为一次线性扫描。

<!-- thinking:end -->

<!-- tabs:start -->

#### Java

```java
class Solution {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        int cnt = 0;
        int a = 0;
        for (int b : nums) {
            cnt = a == b ? cnt + 1 : 1;
            if (cnt * k > nums.length) {
                return false;
            }
            a = b;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
