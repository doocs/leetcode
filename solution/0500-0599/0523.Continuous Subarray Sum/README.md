---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0523.Continuous%20Subarray%20Sum/README.md
tags:
    - 数组
    - 哈希表
    - 数学
    - 前缀和
---

<!-- problem:start -->

# [523. 连续的子数组和](https://leetcode.cn/problems/continuous-subarray-sum)

[English Version](/solution/0500-0599/0523.Continuous%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数&nbsp;<code>k</code> ，如果&nbsp;<code>nums</code>&nbsp;有一个 <strong>好的子数组</strong>&nbsp;返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>：</p>

<p>一个&nbsp;<strong>好的子数组</strong>&nbsp;是：</p>

<ul>
	<li>长度&nbsp;<strong>至少为 2</strong> ，且</li>
	<li>子数组元素总和为 <code>k</code> 的倍数。</li>
</ul>

<p><strong>注意</strong>：</p>

<ul>
	<li><strong>子数组</strong> 是数组中 <strong>连续</strong> 的部分。</li>
	<li>如果存在一个整数 <code>n</code> ，令整数 <code>x</code> 符合 <code>x = n * k</code> ，则称 <code>x</code> 是 <code>k</code> 的一个倍数。<code>0</code> <strong>始终</strong> 视为 <code>k</code> 的一个倍数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [23<u>,2,4</u>,6,7], k = 6
<strong>输出：</strong>true
<strong>解释：</strong>[2,4] 是一个大小为 2 的子数组，并且和为 6 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [<u>23,2,6,4,7</u>], k = 6
<strong>输出：</strong>true
<strong>解释：</strong>[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [23,2,6,4,7], k = 13
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= sum(nums[i]) &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 哈希表

根据题目描述，如果存在两个前缀和模 $k$ 的余数相同的位置 $i$ 和 $j$（不妨设 $j < i$），那么 $\text{nums}[j+1..i]$ 这个子数组的和是 $k$ 的倍数。

因此，我们可以使用哈希表存储每个前缀和模 $k$ 的余数第一次出现的位置。初始时，我们在哈希表中存入一对键值对 $(0, -1)$，表示前缀和为 $0$ 的余数 $0$ 出现在位置 $-1$。

遍历数组时，我们计算当前前缀和的模 $k$ 的余数，如果当前前缀和的模 $k$ 的余数没有在哈希表中出现过，我们就将当前前缀和的模 $k$ 的余数和对应的位置存入哈希表中。否则，如果当前前缀和的模 $k$ 的余数在哈希表中已经出现过，位置为 $j$，那么我们就找到了一个满足条件的子数组 $\text{nums}[j+1..i]$，因此返回 $\text{True}$。

遍历结束后，如果没有找到满足条件的子数组，我们返回 $\text{False}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\text{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        d = {0: -1}
        s = 0
        for i, x in enumerate(nums):
            s = (s + x) % k
            if s not in d:
                d[s] = i
            elif i - d[s] > 1:
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s = (s + nums[i]) % k;
            if (!d.containsKey(s)) {
                d.put(s, i);
            } else if (i - d.get(s) > 1) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> d{{0, -1}};
        int s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s = (s + nums[i]) % k;
            if (!d.contains(s)) {
                d[s] = i;
            } else if (i - d[s] > 1) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func checkSubarraySum(nums []int, k int) bool {
	d := map[int]int{0: -1}
	s := 0
	for i, x := range nums {
		s = (s + x) % k
		if _, ok := d[s]; !ok {
			d[s] = i
		} else if i-d[s] > 1 {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function checkSubarraySum(nums: number[], k: number): boolean {
    const d: Record<number, number> = { 0: -1 };
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s = (s + nums[i]) % k;
        if (!d.hasOwnProperty(s)) {
            d[s] = i;
        } else if (i - d[s] > 1) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
