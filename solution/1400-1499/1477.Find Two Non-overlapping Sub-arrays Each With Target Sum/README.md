---
comments: true
difficulty: 中等
rating: 1850
source: 第 28 场双周赛 Q3
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 动态规划
    - 滑动窗口
---

<!-- problem:start -->

# [1477. 找两个和为目标值且不重叠的子数组](https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum)

[English Version](/solution/1400-1499/1477.Find%20Two%20Non-overlapping%20Sub-arrays%20Each%20With%20Target%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>arr</code> 和一个整数值&nbsp;<code>target</code>&nbsp;。</p>

<p>请你在 <code>arr</code>&nbsp;中找 <strong>两个互不重叠的子数组</strong>&nbsp;且它们的和都等于&nbsp;<code>target</code>&nbsp;。可能会有多种方案，请你返回满足要求的两个子数组长度和的 <strong>最小值</strong> 。</p>

<p>请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 <strong>-1</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,2,2,4,3], target = 3
<strong>输出：</strong>2
<strong>解释：</strong>只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [7,3,4,7], target = 7
<strong>输出：</strong>2
<strong>解释：</strong>尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [4,3,2,6,2,3,4], target = 6
<strong>输出：</strong>-1
<strong>解释：</strong>我们只有一个和为 6 的子数组。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [5,5,4,4,5], target = 3
<strong>输出：</strong>-1
<strong>解释：</strong>我们无法找到和为 3 的子数组。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [3,1,1,1,5,1,2,1], target = 3
<strong>输出：</strong>3
<strong>解释：</strong>注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= target &lt;= 10^8</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 前缀和 + 动态规划

<!-- thinking:start -->

> **思考**
>
> 最直接的做法是枚举所有和为 $target$ 的子数组，再两两检查是否重叠并取长度和的最小值。$n \le 10^5$，子数组数量达到平方级，无法通过。
>
> 选定右段之后，左段必须完全落在它左侧的前缀里，而且我们只关心那段前缀中最短的合法子数组。这要求我们在扫描过程中随时能回答「前缀里的最短合法段」。
>
> 数组元素均为正整数，前缀和严格递增，每个前缀和只对应一个位置。因此用哈希表记下前缀和出现的下标后，就能在常数时间定位以当前位置结尾、和为 $target$ 的区间 $[j+1,i]$。
>
> 于是从左到右维护 $f[i]$：前 $i$ 个元素中和为 $target$ 的最短子数组长度。每当找到 $[j+1,i]$，就用 $f[j]$ 与当前长度相加更新答案，再令 $f[i]=\min(f[i-1], i-j)$。左段始终取自当前段之前，两段自然不重叠。

<!-- thinking:end -->

我们用哈希表 $d$ 记录每个前缀和出现的位置，初始时 $d[0]=0$。

定义 $f[i]$ 表示数组前 $i$ 个元素中，和为 $target$ 的最短子数组的长度。初始时 $f[0]=\infty$，答案 $ans=\infty$。下标从 $1$ 开始。

遍历数组 $\textit{arr}$。对于当前位置 $i$，先令 $f[i]=f[i-1]$，并累加得到前缀和 $s$。若 $s-\textit{target}$ 在哈希表中，记 $j=d[s-\textit{target}]$，则区间 $[j+1,i]$ 的和为 $target$，长度为 $i-j$。此时更新 $f[i]=\min(f[i], i-j)$，并用左侧最优长度更新答案 $ans=\min(ans, f[j]+i-j)$。然后将 $d[s]$ 记为 $i$。

最后，如果 $ans$ 大于数组长度，则返回 $-1$，否则返回 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSumOfLengths(self, arr: List[int], target: int) -> int:
        d = {0: 0}
        s, n = 0, len(arr)
        f = [inf] * (n + 1)
        ans = inf
        for i, v in enumerate(arr, 1):
            s += v
            f[i] = f[i - 1]
            if s - target in d:
                j = d[s - target]
                f[i] = min(f[i], i - j)
                ans = min(ans, f[j] + i - j)
            d[s] = i
        return -1 if ans > n else ans
```

#### Java

```java
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, 0);
        int n = arr.length;
        int[] f = new int[n + 1];
        final int inf = 1 << 30;
        f[0] = inf;
        int s = 0, ans = inf;
        for (int i = 1; i <= n; ++i) {
            int v = arr[i - 1];
            s += v;
            f[i] = f[i - 1];
            if (d.containsKey(s - target)) {
                int j = d.get(s - target);
                f[i] = Math.min(f[i], i - j);
                ans = Math.min(ans, f[j] + i - j);
            }
            d.put(s, i);
        }
        return ans > n ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSumOfLengths(vector<int>& arr, int target) {
        unordered_map<int, int> d;
        d[0] = 0;
        int s = 0, n = arr.size();
        int f[n + 1];
        const int inf = 1 << 30;
        f[0] = inf;
        int ans = inf;
        for (int i = 1; i <= n; ++i) {
            int v = arr[i - 1];
            s += v;
            f[i] = f[i - 1];
            if (d.count(s - target)) {
                int j = d[s - target];
                f[i] = min(f[i], i - j);
                ans = min(ans, f[j] + i - j);
            }
            d[s] = i;
        }
        return ans > n ? -1 : ans;
    }
};
```

#### Go

```go
func minSumOfLengths(arr []int, target int) int {
	d := map[int]int{0: 0}
	const inf = 1 << 30
	s, n := 0, len(arr)
	f := make([]int, n+1)
	f[0] = inf
	ans := inf
	for i, v := range arr {
		i++
		f[i] = f[i-1]
		s += v
		if j, ok := d[s-target]; ok {
			f[i] = min(f[i], i-j)
			ans = min(ans, f[j]+i-j)
		}
		d[s] = i
	}
	if ans > n {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minSumOfLengths(arr: number[], target: number): number {
    const d = new Map<number, number>();
    d.set(0, 0);
    let s = 0;
    const n = arr.length;
    const f: number[] = Array(n + 1);
    const inf = 1 << 30;
    f[0] = inf;
    let ans = inf;
    for (let i = 1; i <= n; ++i) {
        const v = arr[i - 1];
        s += v;
        f[i] = f[i - 1];
        if (d.has(s - target)) {
            const j = d.get(s - target)!;
            f[i] = Math.min(f[i], i - j);
            ans = Math.min(ans, f[j] + i - j);
        }
        d.set(s, i);
    }
    return ans > n ? -1 : ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_sum_of_lengths(arr: Vec<i32>, target: i32) -> i32 {
        let mut d = HashMap::new();
        d.insert(0, 0);
        let n = arr.len();
        let inf = 1 << 30;
        let mut f = vec![0; n + 1];
        f[0] = inf;
        let mut s = 0;
        let mut ans = inf;
        for i in 1..=n {
            s += arr[i - 1];
            f[i] = f[i - 1];
            if let Some(&j) = d.get(&(s - target)) {
                f[i] = f[i].min((i - j) as i32);
                ans = ans.min(f[j] + (i - j) as i32);
            }
            d.insert(s, i);
        }
        if ans > n as i32 {
            -1
        } else {
            ans
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
