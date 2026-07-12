---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3537.Fill%20a%20Special%20Grid/README.md
rating: 1541
source: 第 448 场周赛 Q2
tags:
    - 数组
    - 分治
    - 矩阵
---

<!-- problem:start -->

# [3537. 填充特殊网格](https://leetcode.cn/problems/fill-a-special-grid)

[English Version](/solution/3500-3599/3537.Fill%20a%20Special%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个非负整数 <code><font face="monospace">N</font></code>，表示一个 <code>2<sup>N</sup> x 2<sup>N</sup></code> 的网格。你需要用从 0 到 <code>2<sup>2N</sup> - 1</code> 的整数填充网格，使其成为一个&nbsp;<strong>特殊&nbsp;</strong>网格。一个网格当且仅当满足以下&nbsp;<strong>所有&nbsp;</strong>条件时，才能称之为 <strong>特殊</strong> 网格：</p>

<ul>
	<li>右上角象限中的所有数字都小于右下角象限中的所有数字。</li>
	<li>右下角象限中的所有数字都小于左下角象限中的所有数字。</li>
	<li>左下角象限中的所有数字都小于左上角象限中的所有数字。</li>
	<li>每个象限也都是一个特殊网格。</li>
</ul>

<p>返回一个&nbsp;<code>2<sup>N</sup> x 2<sup>N</sup></code>&nbsp;的特殊网格。</p>

<p><strong>注意：</strong>任何 1x1 的网格都是特殊网格。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">N = 0</span></p>

<p><strong>输出：</strong> <span class="example-io">[[0]]</span></p>

<p><strong>解释：</strong></p>

<p>唯一可以放置的数字是 0，并且网格中只有一个位置。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">N = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">[[3,0],[2,1]]</span></p>

<p><strong>解释：</strong></p>

<p>每个象限的数字如下：</p>

<ul>
	<li>右上角：0</li>
	<li>右下角：1</li>
	<li>左下角：2</li>
	<li>左上角：3</li>
</ul>

<p>由于 <code>0 &lt; 1 &lt; 2 &lt; 3</code>，该网格满足给定的约束条件。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">N = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[[15,12,3,0],[14,13,2,1],[11,8,7,4],[10,9,6,5]]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3537.Fill%20a%20Special%20Grid/images/1746289512-jpANZH-4123example3p1drawio.png" style="width: 161px; height: 161px;" /></p>

<p>每个象限的数字如下：</p>

<ul>
	<li>右上角：3, 0, 2, 1</li>
	<li>右下角：7, 4, 6, 5</li>
	<li>左下角：11, 8, 10, 9</li>
	<li>左上角：15, 12, 14, 13</li>
	<li><code>max(3, 0, 2, 1) &lt; min(7, 4, 6, 5)</code></li>
	<li><code>max(7, 4, 6, 5) &lt; min(11, 8, 10, 9)</code></li>
	<li><code>max(11, 8, 10, 9) &lt; min(15, 12, 14, 13)</code></li>
</ul>

<p>这满足前三个要求。此外，每个象限也是一个特殊网格。因此，这是一个特殊网格。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= N &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归分治

特殊网格要求每个象限内的数字满足：右上角 < 右下角 < 左下角 < 左上角，且每个象限也是特殊网格。我们可以用递归分治的方法构造：对于一个边长为 $k$ 的子网格，按照「右上 → 右下 → 左下 → 左上」的顺序依次递归填充，保证较小数字先填入右上角象限，较大数字后填入左上角象限，从而满足约束条件。

我们从整个网格的右上角 $(0, m - 1)$ 开始，其中 $m = 2^n$，边长为 $m$。当 $k = 1$ 时，直接将当前值填入并递增；否则将子网格分为四个象限，递归处理。

时间复杂度 $O(4^n)$，空间复杂度 $O(4^n)$。其中 $n$ 是输入参数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def specialGrid(self, n: int) -> List[List[int]]:
        def dfs(x: int, y: int, k: int):
            if k == 1:
                nonlocal val
                ans[x][y] = val
                val += 1
                return

            dfs(x, y, k // 2)
            dfs(x + k // 2, y, k // 2)
            dfs(x + k // 2, y - k // 2, k // 2)
            dfs(x, y - k // 2, k // 2)

        m = 1 << n
        ans = [[0] * m for _ in range(m)]
        val = 0
        dfs(0, m - 1, m)
        return ans
```

#### Java

```java
class Solution {
    private int[][] ans;
    private int val;

    public int[][] specialGrid(int n) {
        int m = 1 << n;
        ans = new int[m][m];
        dfs(0, m - 1, m);
        return ans;
    }

    private void dfs(int x, int y, int k) {
        if (k == 1) {
            ans[x][y] = val++;
            return;
        }

        int h = k / 2;
        dfs(x, y, h);
        dfs(x + h, y, h);
        dfs(x + h, y - h, h);
        dfs(x, y - h, h);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> specialGrid(int n) {
        int m = 1 << n;
        vector<vector<int>> ans(m, vector<int>(m));
        int val = 0;

        auto dfs = [&](this auto&& dfs, int x, int y, int k) -> void {
            if (k == 1) {
                ans[x][y] = val++;
                return;
            }

            int h = k / 2;
            dfs(x, y, h);
            dfs(x + h, y, h);
            dfs(x + h, y - h, h);
            dfs(x, y - h, h);
        };

        dfs(0, m - 1, m);
        return ans;
    }
};
```

#### Go

```go
func specialGrid(n int) [][]int {
	m := 1 << n
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, m)
	}
	val := 0

	var dfs func(int, int, int)
	dfs = func(x, y, k int) {
		if k == 1 {
			ans[x][y] = val
			val++
			return
		}

		h := k / 2
		dfs(x, y, h)
		dfs(x+h, y, h)
		dfs(x+h, y-h, h)
		dfs(x, y-h, h)
	}

	dfs(0, m-1, m)
	return ans
}
```

#### TypeScript

```ts
function specialGrid(n: number): number[][] {
    const m = 1 << n;
    const ans = Array.from({ length: m }, () => Array(m).fill(0));
    let val = 0;

    const dfs = (x: number, y: number, k: number): void => {
        if (k === 1) {
            ans[x][y] = val++;
            return;
        }

        const h = k >> 1;
        dfs(x, y, h);
        dfs(x + h, y, h);
        dfs(x + h, y - h, h);
        dfs(x, y - h, h);
    };

    dfs(0, m - 1, m);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn special_grid(n: i32) -> Vec<Vec<i32>> {
        fn dfs(x: usize, y: usize, k: usize, ans: &mut Vec<Vec<i32>>, val: &mut i32) {
            if k == 1 {
                ans[x][y] = *val;
                *val += 1;
                return;
            }

            let h = k / 2;
            dfs(x, y, h, ans, val);
            dfs(x + h, y, h, ans, val);
            dfs(x + h, y - h, h, ans, val);
            dfs(x, y - h, h, ans, val);
        }

        let m = 1usize << n;
        let mut ans = vec![vec![0; m]; m];
        let mut val = 0;
        dfs(0, m - 1, m, &mut ans, &mut val);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
