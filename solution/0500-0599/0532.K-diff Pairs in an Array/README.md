---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0532.K-diff%20Pairs%20in%20an%20Array/README.md
tags:
    - 数组
    - 哈希表
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [532. 数组中的 k-diff 数对](https://leetcode.cn/problems/k-diff-pairs-in-an-array)

[English Version](/solution/0500-0599/0532.K-diff%20Pairs%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code> 和一个整数&nbsp;<code>k</code>，请你在数组中找出<strong> 不同的&nbsp;</strong>k-diff 数对，并返回不同的 <strong>k-diff 数对</strong> 的数目。</p>

<p><strong>k-diff</strong>&nbsp;数对定义为一个整数对 <code>(nums[i], nums[j])</code><strong> </strong>，并满足下述全部条件：</p>

<ul>
	<li><code>0 &lt;= i, j &lt; nums.length</code></li>
	<li><code>i != j</code></li>
	<li><code>|nums[i] - nums[j]| == k</code></li>
</ul>

<p><strong>注意</strong>，<code>|val|</code> 表示 <code>val</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3, 1, 4, 1, 5], k = 2
<strong>输出：</strong>2
<strong>解释：</strong>数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 2, 3, 4, 5], k = 1
<strong>输出：</strong>4
<strong>解释：</strong>数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 3, 1, 5, 4], k = 0
<strong>输出：</strong>1
<strong>解释：</strong>数组中只有一个 0-diff 数对，(1, 1) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>7</sup> &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

由于 $k$ 是一个定值，我们可以用一个哈希表 $\textit{ans}$ 记录数对的较小值，就能够确定较大的值。最后返回 $\textit{ans}$ 的大小作为答案。

遍历数组 $\textit{nums}$，当前遍历到的数 $x$，我们用哈希表 $\textit{vis}$ 记录此前遍历到的所有数字。若 $x-k$ 在 $\textit{vis}$ 中，则将 $x-k$ 添加至 $\textit{ans}$；若 $x+k$ 在 $\textit{vis}$ 中，则将 $x$ 添加至 $\textit{ans}$。然后我们将 $x$ 添加至 $\textit{vis}$。继续遍历数组 $\textit{nums}$ 直至遍历结束。

最后返回 $\textit{ans}$ 的大小作为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPairs(self, nums: List[int], k: int) -> int:
        ans = set()
        vis = set()
        for x in nums:
            if x - k in vis:
                ans.add(x - k)
            if x + k in vis:
                ans.add(x)
            vis.add(x)
        return len(ans)
```

#### Java

```java
class Solution {
    public int findPairs(int[] nums, int k) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> vis = new HashSet<>();
        for (int x : nums) {
            if (vis.contains(x - k)) {
                ans.add(x - k);
            }
            if (vis.contains(x + k)) {
                ans.add(x);
            }
            vis.add(x);
        }
        return ans.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        unordered_set<int> ans, vis;
        for (int x : nums) {
            if (vis.count(x - k)) {
                ans.insert(x - k);
            }
            if (vis.count(x + k)) {
                ans.insert(x);
            }
            vis.insert(x);
        }
        return ans.size();
    }
};
```

#### Go

```go
func findPairs(nums []int, k int) int {
	ans := make(map[int]struct{})
	vis := make(map[int]struct{})

	for _, x := range nums {
		if _, ok := vis[x-k]; ok {
			ans[x-k] = struct{}{}
		}
		if _, ok := vis[x+k]; ok {
			ans[x] = struct{}{}
		}
		vis[x] = struct{}{}
	}
	return len(ans)
}
```

#### TypeScript

```ts
function findPairs(nums: number[], k: number): number {
    const ans = new Set<number>();
    const vis = new Set<number>();
    for (const x of nums) {
        if (vis.has(x - k)) {
            ans.add(x - k);
        }
        if (vis.has(x + k)) {
            ans.add(x);
        }
        vis.add(x);
    }
    return ans.size;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn find_pairs(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = HashSet::new();
        let mut vis = HashSet::new();

        for &x in &nums {
            if vis.contains(&(x - k)) {
                ans.insert(x - k);
            }
            if vis.contains(&(x + k)) {
                ans.insert(x);
            }
            vis.insert(x);
        }
        ans.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
