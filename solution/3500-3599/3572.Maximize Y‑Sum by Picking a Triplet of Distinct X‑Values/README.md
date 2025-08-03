---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3572.Maximize%20Y%E2%80%91Sum%20by%20Picking%20a%20Triplet%20of%20Distinct%20X%E2%80%91Values/README.md
rating: 1319
source: 第 158 场双周赛 Q1
tags:
    - 贪心
    - 数组
    - 哈希表
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [3572. 选择不同 X 值三元组使 Y 值之和最大](https://leetcode.cn/problems/maximize-ysum-by-picking-a-triplet-of-distinct-xvalues)

[English Version](/solution/3500-3599/3572.Maximize%20Y%E2%80%91Sum%20by%20Picking%20a%20Triplet%20of%20Distinct%20X%E2%80%91Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>x</code> 和 <code>y</code>，长度均为 <code>n</code>。你必须选择三个&nbsp;<strong>不同&nbsp;</strong>的下标&nbsp;<code>i</code>&nbsp;，<code>j</code> 和 <code>k</code>，满足以下条件：</p>

<ul>
	<li><code>x[i] != x[j]</code></li>
	<li><code>x[j] != x[k]</code></li>
	<li><code>x[k] != x[i]</code></li>
</ul>

<p>你的目标是在满足这些条件下&nbsp;<strong>最大化</strong> <code>y[i] + y[j] + y[k]</code> 的值。返回通过选择这样一组三元组下标所能获得的&nbsp;<strong>最大&nbsp;</strong>可能和。</p>

<p>如果不存在这样的三元组，返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">x = [1,2,1,3,2], y = [5,3,4,6,2]</span></p>

<p><strong>输出：</strong><span class="example-io">14</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择 <code>i = 0</code>（<code>x[i] = 1</code>，<code>y[i] = 5</code>），<code>j = 1</code>（<code>x[j] = 2</code>，<code>y[j] = 3</code>），<code>k = 3</code>（<code>x[k] = 3</code>，<code>y[k] = 6</code>）。</li>
	<li>选出的三个 <code>x</code> 中的值互不相同。<code>5 + 3 + 6 = 14</code> 是我们能获得的最大值。因此输出为 14。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">x = [1,2,1,2], y = [4,5,6,7]</span></p>

<p><strong>输出：</strong><span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>x</code> 中只有两个不同的值。因此输出为 -1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == x.length == y.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x[i], y[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心 + 哈希表

我们将数组 $x$ 和 $y$ 中的元素配对成一个二维数组 $\textit{arr}$，然后按照 $y$ 的值从大到小对 $\textit{arr}$ 进行排序。接下来，我们使用一个哈希表来记录已经选择的 $x$ 值，并遍历 $\textit{arr}$，每次选择一个未被选择的 $x$ 值和对应的 $y$ 值，直到选择了三个不同的 $x$ 值为止。

如果在遍历过程中选择了三个不同的 $x$ 值，则返回这三个 $y$ 值的和；如果遍历结束后仍未选择三个不同的 $x$ 值，则返回 -1。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{x}$ 和 $\textit{y}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSumDistinctTriplet(self, x: List[int], y: List[int]) -> int:
        arr = [(a, b) for a, b in zip(x, y)]
        arr.sort(key=lambda x: -x[1])
        vis = set()
        ans = 0
        for a, b in arr:
            if a in vis:
                continue
            vis.add(a)
            ans += b
            if len(vis) == 3:
                return ans
        return -1
```

#### Java

```java
class Solution {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {x[i], y[i]};
        }
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        int ans = 0;
        Set<Integer> vis = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            int a = arr[i][0], b = arr[i][1];
            if (vis.add(a)) {
                ans += b;
                if (vis.size() == 3) {
                    return ans;
                }
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSumDistinctTriplet(vector<int>& x, vector<int>& y) {
        int n = x.size();
        vector<array<int, 2>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {x[i], y[i]};
        }
        ranges::sort(arr, [](auto& a, auto& b) {
            return b[1] < a[1];
        });
        int ans = 0;
        unordered_set<int> vis;
        for (int i = 0; i < n; ++i) {
            int a = arr[i][0], b = arr[i][1];
            if (vis.insert(a).second) {
                ans += b;
                if (vis.size() == 3) {
                    return ans;
                }
            }
        }
        return -1;
    }
};
```

#### Go

```go
func maxSumDistinctTriplet(x []int, y []int) int {
	n := len(x)
	arr := make([][2]int, n)
	for i := 0; i < n; i++ {
		arr[i] = [2]int{x[i], y[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][1] > arr[j][1]
	})
	ans := 0
	vis := make(map[int]bool)
	for i := 0; i < n; i++ {
		a, b := arr[i][0], arr[i][1]
		if !vis[a] {
			vis[a] = true
			ans += b
			if len(vis) == 3 {
				return ans
			}
		}
	}
	return -1
}
```

#### TypeScript

```ts
function maxSumDistinctTriplet(x: number[], y: number[]): number {
    const n = x.length;
    const arr: [number, number][] = [];
    for (let i = 0; i < n; i++) {
        arr.push([x[i], y[i]]);
    }
    arr.sort((a, b) => b[1] - a[1]);
    const vis = new Set<number>();
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const [a, b] = arr[i];
        if (!vis.has(a)) {
            vis.add(a);
            ans += b;
            if (vis.size === 3) {
                return ans;
            }
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_sum_distinct_triplet(x: Vec<i32>, y: Vec<i32>) -> i32 {
        let n = x.len();
        let mut arr: Vec<(i32, i32)> = (0..n).map(|i| (x[i], y[i])).collect();
        arr.sort_by(|a, b| b.1.cmp(&a.1));
        let mut vis = std::collections::HashSet::new();
        let mut ans = 0;
        for (a, b) in arr {
            if vis.insert(a) {
                ans += b;
                if vis.len() == 3 {
                    return ans;
                }
            }
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
