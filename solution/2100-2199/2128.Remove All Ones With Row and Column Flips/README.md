---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/README.md
tags:
    - 位运算
    - 数组
    - 数学
    - 矩阵
---

<!-- problem:start -->

# [2128. 通过翻转行或列来去除所有的 1 🔒](https://leetcode.cn/problems/remove-all-ones-with-row-and-column-flips)

[English Version](/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为&nbsp;<code>m x n</code> 的二进制矩阵&nbsp;<code>grid</code>。</p>

<p>每次操作，你可以选择 <strong>任意</strong> 一行 或者 一列，然后将其中的所有值翻转（<code>0</code> 变成 <code>1</code>， <code>1</code>变成 <code>0</code>）。</p>

<p>如果经过 <strong>任意次</strong> 操作，你能将矩阵中所有的 <code>1</code> 去除，那么返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/images/image-20220103191300-1.png" style="width: 756px; height: 225px;">
<pre><strong>输入:</strong> grid = [[0,1,0],[1,0,1],[0,1,0]]
<strong>输出:</strong> true
<strong>解释:</strong> 一种去除所有 1 的可行方法是:
- 翻转矩阵的中间的行
- 翻转矩阵的中间的列
</pre>

<p><strong>示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/images/image-20220103181204-7.png" style="width: 237px; height: 225px;">
<pre><strong>输入:</strong> grid = [[1,1,0],[0,0,0],[0,0,0]]
<strong>输出:</strong> false
<strong>解释:</strong> 不可能去除矩阵中所有的 1。
</pre>

<p><strong>示例 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/images/image-20220103181224-8.png" style="width: 114px; height: 100px;">
<pre><strong>输入:</strong> grid = [[0]]
<strong>输出:</strong> true
<strong>解释:</strong> 矩阵中不存在 1，已经符合要求。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>grid[i][j]</code> 是&nbsp;<code>0</code>&nbsp;或者&nbsp;<code>1</code>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们观察发现，如果矩阵中的两行满足以下条件之一，则它们可以通过翻转某些列的方式得到相等的行：

1. 两行的对应位置元素相等，即如果其中一行元素为 $1,0,0,1$，则另一行元素也为 $1,0,0,1$；
1. 两行的对应位置元素相反，即如果其中一行元素为 $1,0,0,1$，则另一行元素为 $0,1,1,0$。

我们称满足以上条件之一的两行元素为“等价行”，那么题目所求的答案即为矩阵中最多包含等价行的行数。

因此，我们可以遍历矩阵的每一行，将每一行转换成第一个元素为 $0$ 的“等价行”。具体做法如下：

-   如果当前行的第一个元素为 $0$，那么当前行的元素保持不变；
-   如果当前行的第一个元素为 $1$，那么我们将当前行的每个元素进行翻转，即 $0$ 变成 $1$, $1$ 变成 $0$。也就是说，我们将以 $1$ 开头的行翻转成以 $0$ 开头的“等价行”。

这样一来，我们只需要用一个哈希表来统计转换后的每一行，如果最后哈希表只有一个元素，那么说明我们可以通过翻转行或列，将矩阵中所有的 $1$ 去除。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

相似题目：

-   [1072. 按列翻转得到最大值等行数](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1072.Flip%20Columns%20For%20Maximum%20Number%20of%20Equal%20Rows/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeOnes(self, grid: List[List[int]]) -> bool:
        s = set()
        for row in grid:
            t = tuple(row) if row[0] == grid[0][0] else tuple(x ^ 1 for x in row)
            s.add(t)
        return len(s) == 1
```

#### Java

```java
class Solution {
    public boolean removeOnes(int[][] grid) {
        Set<String> s = new HashSet<>();
        int n = grid[0].length;
        for (var row : grid) {
            var cs = new char[n];
            for (int i = 0; i < n; ++i) {
                cs[i] = (char) (row[0] ^ row[i]);
            }
            s.add(String.valueOf(cs));
        }
        return s.size() == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool removeOnes(vector<vector<int>>& grid) {
        unordered_set<string> s;
        for (auto& row : grid) {
            string t;
            for (int x : row) {
                t.push_back('0' + (row[0] == 0 ? x : x ^ 1));
            }
            s.insert(t);
        }
        return s.size() == 1;
    }
};
```

#### Go

```go
func removeOnes(grid [][]int) bool {
	s := map[string]bool{}
	for _, row := range grid {
		t := []byte{}
		for _, x := range row {
			if row[0] == 1 {
				x ^= 1
			}
			t = append(t, byte(x)+'0')
		}
		s[string(t)] = true
	}
	return len(s) == 1
}
```

#### TypeScript

```ts
function removeOnes(grid: number[][]): boolean {
    const s = new Set<string>();
    for (const row of grid) {
        if (row[0] === 1) {
            for (let i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }
        const t = row.join('');
        s.add(t);
    }
    return s.size === 1;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn remove_ones(grid: Vec<Vec<i32>>) -> bool {
        let n = grid[0].len();
        let mut set = HashSet::new();

        for row in grid.iter() {
            let mut pattern = String::with_capacity(n);
            for &x in row.iter() {
                pattern.push(((row[0] ^ x) as u8 + b'0') as char);
            }
            set.insert(pattern);
        }

        set.len() == 1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
