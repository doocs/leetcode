---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0594.Longest%20Harmonious%20Subsequence/README.md
tags:
    - 数组
    - 哈希表
    - 计数
    - 排序
    - 滑动窗口
---

<!-- problem:start -->

# [594. 最长和谐子序列](https://leetcode.cn/problems/longest-harmonious-subsequence)

[English Version](/solution/0500-0599/0594.Longest%20Harmonious%20Subsequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>和谐数组是指一个数组里元素的最大值和最小值之间的差别 <strong>正好是 <code>1</code></strong> 。</p>

<p>给你一个整数数组 <code>nums</code> ，请你在所有可能的 <span data-keyword="subsequence-array">子序列</span> 中找到最长的和谐子序列的长度。</p>

<p>数组的 <strong>子序列</strong> 是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,3,2,2,5,2,3,7]</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<p>最长和谐子序列是&nbsp;<code>[3,2,2,2,3]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>最长和谐子序列是&nbsp;<code>[1,2]</code>，<code>[2,3]</code>&nbsp;和&nbsp;<code>[3,4]</code>，长度都为 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>不存在和谐子序列。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{cnt}$ 记录数组 $\textit{nums}$ 中每个元素出现的次数，然后遍历哈希表中的每个键值对 $(x, c)$，如果哈希表中存在键 $x + 1$，那么 $\textit{nums}$ 中元素 $x$ 和 $x + 1$ 出现的次数之和 $c + \textit{cnt}[x + 1]$ 就是一个和谐子序列，我们只需要在所有和谐子序列中找到最大的长度即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLHS(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return max((c + cnt[x + 1] for x, c in cnt.items() if cnt[x + 1]), default=0)
```

#### Java

```java
class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), c = e.getValue();
            if (cnt.containsKey(x + 1)) {
                ans = Math.max(ans, c + cnt.get(x + 1));
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLHS(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (auto& [x, c] : cnt) {
            if (cnt.contains(x + 1)) {
                ans = max(ans, c + cnt[x + 1]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLHS(nums []int) (ans int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x, c := range cnt {
		if c1, ok := cnt[x+1]; ok {
			ans = max(ans, c+c1)
		}
	}
	return
}
```

#### TypeScript

```ts
function findLHS(nums: number[]): number {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    let ans = 0;
    for (const [x, c] of Object.entries(cnt)) {
        const y = +x + 1;
        if (cnt[y]) {
            ans = Math.max(ans, c + cnt[y]);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn find_lhs(nums: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut ans = 0;
        for (&x, &c) in &cnt {
            if let Some(&y) = cnt.get(&(x + 1)) {
                ans = ans.max(c + y);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
