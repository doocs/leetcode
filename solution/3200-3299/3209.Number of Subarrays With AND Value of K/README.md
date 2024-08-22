---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3209.Number%20of%20Subarrays%20With%20AND%20Value%20of%20K/README.md
rating: 2050
source: 第 134 场双周赛 Q4
tags:
    - 位运算
    - 线段树
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [3209. 子数组按位与值为 K 的数目](https://leetcode.cn/problems/number-of-subarrays-with-and-value-of-k)

[English Version](/solution/3200-3299/3209.Number%20of%20Subarrays%20With%20AND%20Value%20of%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回&nbsp;<code>nums</code>&nbsp;中有多少个<span data-keyword="subarray-nonempty">子数组</span>满足：子数组中所有元素按位&nbsp;<code>AND</code>&nbsp;的结果为 <code>k</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,1,1], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>所有子数组都只含有元素 1 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,1,2], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><b>解释：</b></p>

<p>按位&nbsp;<code>AND</code>&nbsp;值为 1 的子数组包括：<code>[<u><strong>1</strong></u>,1,2]</code>, <code>[1,<u><strong>1</strong></u>,2]</code>, <code>[<u><strong>1,1</strong></u>,2]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>按位&nbsp;<code>AND</code>&nbsp;值为 2 的子数组包括：<code>[1,<b><u>2</u></b>,3]</code>, <code>[1,<u><strong>2,3</strong></u>]</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

根据题目描述，我们需要求出数组 $\textit{nums}$ 下标 $l$ 到 $r$ 的元素的按位与运算的结果，即 $\textit{nums}[l] \land \textit{nums}[l + 1] \land \cdots \land \textit{nums}[r]$。其中 $\land$ 表示按位与运算。

如果我们每次固定右端点 $r$，那么左端点 $l$ 的范围是 $[0, r]$。由于按位与之和随着 $l$ 的减小而单调递减，并且 $nums[i]$ 的值不超过 $10^9$，因此区间 $[0, r]$ 最多只有 $30$ 种不同的值。因此，我们可以用一个集合来维护所有的 $\textit{nums}[l] \land \textit{nums}[l + 1] \land \cdots \land \textit{nums}[r]$ 的值，以及这些值出现的次数。

当我们从 $r$ 遍历到 $r+1$ 时，以 $r+1$ 为右端点的值，就是集合中每个值与 $nums[r + 1]$ 进行按位与运算得到的值，再加上 $\textit{nums}[r + 1]$ 本身。

因此，我们只需要枚举集合中的每个值，与 $\textit{nums[r]}$ 进行按位与运算，就可以得到以 $r$ 为右端点的所有值及其出现的次数。然后，我们还需要将 $\textit{nums[r]}$ 的出现次数加上去。此时，我们将值为 $k$ 的出现次数累加到答案中。继续遍历 $r$，直到遍历完整个数组。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(\log M)$。其中 $n$ 和 $M$ 分别是数组 $\textit{nums}$ 的长度和数组 $\textit{nums}$ 中元素的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        ans = 0
        pre = Counter()
        for x in nums:
            cur = Counter()
            for y, v in pre.items():
                cur[x & y] += v
            cur[x] += 1
            ans += cur[k]
            pre = cur
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> pre = new HashMap<>();
        for (int x : nums) {
            Map<Integer, Integer> cur = new HashMap<>();
            for (var e : pre.entrySet()) {
                int y = e.getKey(), v = e.getValue();
                cur.merge(x & y, v, Integer::sum);
            }
            cur.merge(x, 1, Integer::sum);
            ans += cur.getOrDefault(k, 0);
            pre = cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, int k) {
        long long ans = 0;
        unordered_map<int, int> pre;
        for (int x : nums) {
            unordered_map<int, int> cur;
            for (auto& [y, v] : pre) {
                cur[x & y] += v;
            }
            cur[x]++;
            ans += cur[k];
            pre = cur;
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int, k int) (ans int64) {
	pre := map[int]int{}
	for _, x := range nums {
		cur := map[int]int{}
		for y, v := range pre {
			cur[x&y] += v
		}
		cur[x]++
		ans += int64(cur[k])
		pre = cur
	}
	return
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[], k: number): number {
    let ans = 0;
    let pre = new Map<number, number>();
    for (const x of nums) {
        const cur = new Map<number, number>();
        for (const [y, v] of pre) {
            const z = x & y;
            cur.set(z, (cur.get(z) || 0) + v);
        }
        cur.set(x, (cur.get(x) || 0) + 1);
        ans += cur.get(k) || 0;
        pre = cur;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
