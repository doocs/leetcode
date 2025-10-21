---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3347.Maximum%20Frequency%20of%20an%20Element%20After%20Performing%20Operations%20II/README.md
rating: 2155
source: 第 143 场双周赛 Q3
tags:
    - 数组
    - 二分查找
    - 前缀和
    - 排序
    - 滑动窗口
---

<!-- problem:start -->

# [3347. 执行操作后元素的最高频率 II](https://leetcode.cn/problems/maximum-frequency-of-an-element-after-performing-operations-ii)

[English Version](/solution/3300-3399/3347.Maximum%20Frequency%20of%20an%20Element%20After%20Performing%20Operations%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和两个整数&nbsp;<code>k</code> 和&nbsp;<code>numOperations</code>&nbsp;。</p>

<p>你必须对 <code>nums</code>&nbsp;执行 <strong>操作</strong>&nbsp; <code>numOperations</code>&nbsp;次。每次操作中，你可以：</p>

<ul>
	<li>选择一个下标&nbsp;<code>i</code>&nbsp;，它在之前的操作中 <strong>没有</strong>&nbsp;被选择过。</li>
	<li>将 <code>nums[i]</code>&nbsp;增加范围&nbsp;<code>[-k, k]</code>&nbsp;中的一个整数。</li>
</ul>

<p>在执行完所有操作以后，请你返回 <code>nums</code>&nbsp;中出现 <strong>频率最高</strong>&nbsp;元素的出现次数。</p>

<p>一个元素 <code>x</code>&nbsp;的 <strong>频率</strong>&nbsp;指的是它在数组中出现的次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,4,5], k = 1, numOperations = 2</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>通过以下操作得到最高频率 2 ：</p>

<ul>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 0 ，<code>nums</code> 变为&nbsp;<code>[1, 4, 5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[2]</code>&nbsp;增加 -1 ，<code>nums</code> 变为&nbsp;<code>[1, 4, 4]</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,11,20,20], k = 5, numOperations = 1</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>通过以下操作得到最高频率 2 ：</p>

<ul>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 0 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= numOperations &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分

根据题目描述，对于数组 $\textit{nums}$ 中的每个元素 $x$，我们可以将其变为 $[x-k, x+k]$ 范围内的任意整数。我们希望通过对 $\textit{nums}$ 中的若干元素进行操作，使得某个整数在数组中出现的次数最多。

题目可以转化为，将每个元素 $x$ 对应的区间 $[x-k, x+k]$ 的所有元素进行合并，找出合并后区间中包含最多原始元素的整数。这可以通过差分数组来实现。

我们使用一个字典 $d$ 来记录差分数组。对于每个元素 $x$，我们在差分数组中执行以下操作：

-   在位置 $x-k$ 处加 $1$，表示从这个位置开始，有一个新的区间开始。
-   在位置 $x+k+1$ 处减 $1$，表示从这个位置开始，有一个区间结束。
-   在位置 $x$ 处加 $0$，确保位置 $x$ 存在于差分数组中，方便后续计算。

同时，我们还需要记录每个元素在原始数组中出现的次数，使用字典 $cnt$ 来实现。

接下来，我们对差分数组进行前缀和计算，得到每个位置上有多少个区间覆盖。对于每个位置 $x$，我们计算其覆盖的区间数 $s$。接下来分类讨论：

-   如果 $x$ 在原始数组中出现过，对于 $x$ 自身的操作，没有意义，因此会有 $s - cnt[x]$ 个其他的元素可以通过操作变为 $x$，但最多只能操作 $\textit{numOperations}$ 次，所以该位置的最大频率为 $\textit{cnt}[x] + \min(s - \textit{cnt}[x], \textit{numOperations})$。
-   如果 $x$ 在原始数组中没有出现过，那么最多只能通过操作 $\textit{numOperations}$ 次将其他元素变为 $x$，因此该位置的最大频率为 $\min(s, \textit{numOperations})$。

综合以上两种情况，我们可以统一表示为 $\min(s, \textit{cnt}[x] + \textit{numOperations})$。

最后，我们遍历所有位置，计算出每个位置的最大频率，并取其中的最大值作为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int, numOperations: int) -> int:
        cnt = defaultdict(int)
        d = defaultdict(int)
        for x in nums:
            cnt[x] += 1
            d[x] += 0
            d[x - k] += 1
            d[x + k + 1] -= 1
        ans = s = 0
        for x, t in sorted(d.items()):
            s += t
            ans = max(ans, min(s, cnt[x] + numOperations))
        return ans
```

#### Java

```java
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
            d.putIfAbsent(x, 0);
            d.merge(x - k, 1, Integer::sum);
            d.merge(x + k + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0;
        for (var e : d.entrySet()) {
            int x = e.getKey(), t = e.getValue();
            s += t;
            ans = Math.max(ans, Math.min(s, cnt.getOrDefault(x, 0) + numOperations));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        unordered_map<int, int> cnt;
        map<int, int> d;

        for (int x : nums) {
            cnt[x]++;
            d[x];
            d[x - k]++;
            d[x + k + 1]--;
        }

        int ans = 0, s = 0;
        for (const auto& [x, t] : d) {
            s += t;
            ans = max(ans, min(s, cnt[x] + numOperations));
        }

        return ans;
    }
};
```

#### Go

```go
func maxFrequency(nums []int, k int, numOperations int) (ans int) {
	cnt := make(map[int]int)
	d := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
		d[x] = d[x]
		d[x-k]++
		d[x+k+1]--
	}

	s := 0
	keys := make([]int, 0, len(d))
	for key := range d {
		keys = append(keys, key)
	}
	sort.Ints(keys)
	for _, x := range keys {
		s += d[x]
		ans = max(ans, min(s, cnt[x]+numOperations))
	}

	return
}
```

#### TypeScript

```ts
function maxFrequency(nums: number[], k: number, numOperations: number): number {
    const cnt: Record<number, number> = {};
    const d: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
        d[x] = d[x] || 0;
        d[x - k] = (d[x - k] || 0) + 1;
        d[x + k + 1] = (d[x + k + 1] || 0) - 1;
    }
    let [ans, s] = [0, 0];
    const keys = Object.keys(d)
        .map(Number)
        .sort((a, b) => a - b);
    for (const x of keys) {
        s += d[x];
        ans = Math.max(ans, Math.min(s, (cnt[x] || 0) + numOperations));
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::{HashMap, BTreeMap};

impl Solution {
    pub fn max_frequency(nums: Vec<i32>, k: i32, num_operations: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut d = BTreeMap::new();

        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
            d.entry(x).or_insert(0);
            *d.entry(x - k).or_insert(0) += 1;
            *d.entry(x + k + 1).or_insert(0) -= 1;
        }

        let mut ans = 0;
        let mut s = 0;
        for (&x, &t) in d.iter() {
            s += t;
            let cur = s.min(cnt.get(&x).copied().unwrap_or(0) + num_operations);
            ans = ans.max(cur);
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MaxFrequency(int[] nums, int k, int numOperations) {
        var cnt = new Dictionary<int, int>();
        var d = new SortedDictionary<int, int>();

        foreach (var x in nums) {
            if (!cnt.ContainsKey(x)) {
                cnt[x] = 0;
            }
            cnt[x]++;

            if (!d.ContainsKey(x)) {
                d[x] = 0;
            }
            if (!d.ContainsKey(x - k)) {
                d[x - k] = 0;
            }
            if (!d.ContainsKey(x + k + 1)) {
                d[x + k + 1] = 0;
            }

            d[x - k] += 1;
            d[x + k + 1] -= 1;
        }

        int ans = 0, s = 0;
        foreach (var kvp in d) {
            int x = kvp.Key, t = kvp.Value;
            s += t;
            int cur = Math.Min(s, (cnt.ContainsKey(x) ? cnt[x] : 0) + numOperations);
            ans = Math.Max(ans, cur);
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
