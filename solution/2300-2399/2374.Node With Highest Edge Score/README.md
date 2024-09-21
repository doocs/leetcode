---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/README.md
rating: 1418
source: 第 306 场周赛 Q2
tags:
    - 图
    - 哈希表
---

<!-- problem:start -->

# [2374. 边积分最高的节点](https://leetcode.cn/problems/node-with-highest-edge-score)

[English Version](/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个有向图，图中有 <code>n</code> 个节点，节点编号从 <code>0</code> 到 <code>n - 1</code> ，其中每个节点都 <strong>恰有一条</strong> 出边。</p>

<p>图由一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组 <code>edges</code> 表示，其中 <code>edges[i]</code> 表示存在一条从节点 <code>i</code> 到节点 <code>edges[i]</code> 的 <strong>有向</strong> 边。</p>

<p>节点 <code>i</code> 的 <strong>边积分</strong> 定义为：所有存在一条指向节点 <code>i</code> 的边的节点的 <strong>编号</strong> 总和。</p>

<p>返回 <strong>边积分</strong> 最高的节点。如果多个节点的 <strong>边积分</strong> 相同，返回编号 <strong>最小</strong> 的那个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/images/image-20220620195403-1.png" style="width: 450px; height: 260px;">
<pre><strong>输入：</strong>edges = [1,0,0,0,0,7,7,5]
<strong>输出：</strong>7
<strong>解释：</strong>
- 节点 1、2、3 和 4 都有指向节点 0 的边，节点 0 的边积分等于 1 + 2 + 3 + 4 = 10 。
- 节点 0 有一条指向节点 1 的边，节点 1 的边积分等于 0 。
- 节点 7 有一条指向节点 5 的边，节点 5 的边积分等于 7 。
- 节点 5 和 6 都有指向节点 7 的边，节点 7 的边积分等于 5 + 6 = 11 。
节点 7 的边积分最高，所以返回 7 。
</pre>

<p><strong>示例 2：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2374.Node%20With%20Highest%20Edge%20Score/images/image-20220620200212-3.png" style="width: 150px; height: 155px;">
<pre><strong>输入：</strong>edges = [2,0,0,2]
<strong>输出：</strong>0
<strong>解释：
</strong>- 节点 1 和 2 都有指向节点 0 的边，节点 0 的边积分等于 1 + 2 = 3 。
- 节点 0 和 3 都有指向节点 2 的边，节点 2 的边积分等于 0 + 3 = 3 。
节点 0 和 2 的边积分都是 3 。由于节点 0 的编号更小，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges[i] &lt; n</code></li>
	<li><code>edges[i] != i</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们定义一个长度为 $n$ 的数组 $\textit{cnt}$，其中 $\textit{cnt}[i]$ 表示节点 $i$ 的边积分，初始时所有元素均为 $0$。定义一个答案变量 $\textit{ans}$，初始时为 $0$。

接下来，我们遍历数组 $\textit{edges}$，对于每个节点 $i$，以及它的出边节点 $j$，我们更新 $\textit{cnt}[j]$ 为 $\textit{cnt}[j] + i$。如果 $\textit{cnt}[\textit{ans}] < \textit{cnt}[j]$ 或者 $\textit{cnt}[\textit{ans}] = \textit{cnt}[j]$ 且 $j < \textit{ans}$，我们更新 $\textit{ans}$ 为 $j$。

最后，返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{edges}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def edgeScore(self, edges: List[int]) -> int:
        ans = 0
        cnt = [0] * len(edges)
        for i, j in enumerate(edges):
            cnt[j] += i
            if cnt[ans] < cnt[j] or (cnt[ans] == cnt[j] and j < ans):
                ans = j
        return ans
```

#### Java

```java
class Solution {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] cnt = new long[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = edges[i];
            cnt[j] += i;
            if (cnt[ans] < cnt[j] || (cnt[ans] == cnt[j] && j < ans)) {
                ans = j;
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
    int edgeScore(vector<int>& edges) {
        int n = edges.size();
        vector<long long> cnt(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = edges[i];
            cnt[j] += i;
            if (cnt[ans] < cnt[j] || (cnt[ans] == cnt[j] && j < ans)) {
                ans = j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func edgeScore(edges []int) (ans int) {
	cnt := make([]int, len(edges))
	for i, j := range edges {
		cnt[j] += i
		if cnt[ans] < cnt[j] || (cnt[ans] == cnt[j] && j < ans) {
			ans = j
		}
	}
	return
}
```

#### TypeScript

```ts
function edgeScore(edges: number[]): number {
    const n = edges.length;
    const cnt: number[] = Array(n).fill(0);
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        const j = edges[i];
        cnt[j] += i;
        if (cnt[ans] < cnt[j] || (cnt[ans] === cnt[j] && j < ans)) {
            ans = j;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn edge_score(edges: Vec<i32>) -> i32 {
        let n = edges.len();
        let mut cnt = vec![0_i64; n];
        let mut ans = 0;

        for (i, &j) in edges.iter().enumerate() {
            let j = j as usize;
            cnt[j] += i as i64;
            if cnt[ans] < cnt[j] || (cnt[ans] == cnt[j] && j < ans) {
                ans = j;
            }
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
