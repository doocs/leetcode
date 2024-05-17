---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20008.%20%E5%92%8C%E5%A4%A7%E4%BA%8E%E7%AD%89%E4%BA%8E%20target%20%E7%9A%84%E6%9C%80%E7%9F%AD%E5%AD%90%E6%95%B0%E7%BB%84/README.md
---

<!-- problem:start -->

# [剑指 Offer II 008. 和大于等于 target 的最短子数组](https://leetcode.cn/problems/2VG8Kg)

## 题目描述

<!-- description:start -->

<p>给定一个含有&nbsp;<code>n</code><strong>&nbsp;</strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>

<p>找出该数组中满足其和<strong> </strong><code>&ge; target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong>&nbsp;<code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
<strong>输出：</strong>2
<strong>解释：</strong>子数组&nbsp;<code>[4,3]</code>&nbsp;是该条件下的长度最小的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 4, nums = [1,4,4]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p>提示：</p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p>进阶：</p>

<ul>
	<li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 209&nbsp;题相同：<a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">https://leetcode.cn/problems/minimum-size-subarray-sum/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们使用双指针维护一个和小于 $target$ 的连续子数组。每次右边界 $j$ 向右移动一位，如果和大于等于 $target$，则更新答案的最小值，同时左边界 $i$ 向右移动，直到和小于 $target$。

最后，如果答案没有被更新过，返回 $0$，否则返回答案。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        ans = inf
        s = i = 0
        for j, x in enumerate(nums):
            s += x
            while s >= target:
                ans = min(ans, j - i + 1)
                s -= nums[i]
                i += 1
        return 0 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        final int inf = 1 << 30;
        int ans = inf;
        int s = 0;
        for (int i = 0, j = 0; j < nums.length; ++j) {
            s += nums[j];
            while (s >= target) {
                ans = Math.min(ans, j - i + 1);
                s -= nums[i++];
            }
        }
        return ans == inf ? 0 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        const int inf = 1 << 30;
        int ans = inf;
        int n = nums.size();
        int s = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            s += nums[j];
            while (s >= target) {
                ans = min(ans, j - i + 1);
                s -= nums[i++];
            }
        }
        return ans == inf ? 0 : ans;
    }
};
```

#### Go

```go
func minSubArrayLen(target int, nums []int) int {
	const inf = 1 << 30
	ans := inf
	s, i := 0, 0
	for j, x := range nums {
		s += x
		for s >= target {
			ans = min(ans, j-i+1)
			s -= nums[i]
			i++
		}
	}
	if ans == inf {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    const inf = 1 << 30;
    let ans = inf;
    let s = 0;
    for (let i = 0, j = 0; j < n; ++j) {
        s += nums[j];
        while (s >= target) {
            ans = Math.min(ans, j - i + 1);
            s -= nums[i++];
        }
    }
    return ans === inf ? 0 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
