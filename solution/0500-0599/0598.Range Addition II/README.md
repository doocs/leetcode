---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0598.Range%20Addition%20II/README.md
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [598. 区间加法 II](https://leetcode.cn/problems/range-addition-ii)

[English Version](/solution/0500-0599/0598.Range%20Addition%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x&nbsp;n</code> 的矩阵&nbsp;<code>M</code><strong> </strong>和一个操作数组 <code>op</code> 。矩阵初始化时所有的单元格都为 <code>0</code> 。<code>ops[i] = [ai, bi]</code> 意味着当所有的 <code>0 &lt;= x &lt; ai</code> 和 <code>0 &lt;= y &lt; bi</code> 时， <code>M[x][y]</code> 应该加 1。</p>

<p>在&nbsp;<em>执行完所有操作后</em>&nbsp;，计算并返回&nbsp;<em>矩阵中最大整数的个数</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0598.Range%20Addition%20II/images/ex1.jpg" style="height: 176px; width: 750px;" /></p>

<pre>
<strong>输入:</strong> m = 3, n = 3，ops = [[2,2],[3,3]]
<strong>输出:</strong> 4
<strong>解释:</strong> M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> m = 3, n = 3, ops = [[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3]]
<strong>输出:</strong> 4
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> m = 3, n = 3, ops = []
<strong>输出:</strong> 9
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= ops.length &lt;= 10<sup>4</sup></code></li>
	<li><code>ops[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>&nbsp;&lt;= m</code></li>
	<li><code>1 &lt;= b<sub>i</sub>&nbsp;&lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

我们注意到，所有操作子矩阵的交集就是最终的最大整数所在的子矩阵，并且每个操作子矩阵都是从左上角 $(0, 0)$ 开始的，因此，我们遍历所有操作子矩阵，求出行数和列数的最小值，最后返回这两个值的乘积即可。

注意，如果操作数组为空，那么矩阵中的最大整数个数就是 $m \times n$。

时间复杂度 $O(k)$，其中 $k$ 是操作数组 $\textit{ops}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCount(self, m: int, n: int, ops: List[List[int]]) -> int:
        for a, b in ops:
            m = min(m, a)
            n = min(n, b)
        return m * n
```

#### Java

```java
class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCount(int m, int n, vector<vector<int>>& ops) {
        for (auto op : ops) {
            m = min(m, op[0]);
            n = min(n, op[1]);
        }
        return m * n;
    }
};
```

#### Go

```go
func maxCount(m int, n int, ops [][]int) int {
	for _, op := range ops {
		m = min(m, op[0])
		n = min(n, op[1])
	}
	return m * n
}
```

#### TypeScript

```ts
function maxCount(m: number, n: number, ops: number[][]): number {
    for (const [a, b] of ops) {
        m = Math.min(m, a);
        n = Math.min(n, b);
    }
    return m * n;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_count(mut m: i32, mut n: i32, ops: Vec<Vec<i32>>) -> i32 {
        for op in ops {
            m = m.min(op[0]);
            n = n.min(op[1]);
        }
        m * n
    }
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @param {number[][]} ops
 * @return {number}
 */
var maxCount = function (m, n, ops) {
    for (const [a, b] of ops) {
        m = Math.min(m, a);
        n = Math.min(n, b);
    }
    return m * n;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
