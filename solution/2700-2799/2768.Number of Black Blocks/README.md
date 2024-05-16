---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2768.Number%20of%20Black%20Blocks/README.md
rating: 2175
source: 第 108 场双周赛 Q4
tags:
    - 数组
    - 哈希表
    - 枚举
---

# [2768. 黑格子的数目](https://leetcode.cn/problems/number-of-black-blocks)

[English Version](/solution/2700-2799/2768.Number%20of%20Black%20Blocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数&nbsp;<code>m</code> 和&nbsp;<code>n</code>&nbsp;，表示一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;的网格图。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数矩阵&nbsp;<code>coordinates</code>&nbsp;，其中&nbsp;<code>coordinates[i] = [x, y]</code>&nbsp;表示坐标为&nbsp;<code>[x, y]</code>&nbsp;的格子是 <strong>黑色的</strong>&nbsp;，所有没出现在&nbsp;<code>coordinates</code>&nbsp;中的格子都是 <strong>白色的</strong>。</p>

<p>一个块定义为网格图中&nbsp;<code>2 x 2</code>&nbsp;的一个子矩阵。更正式的，对于左上角格子为 <code>[x, y]</code> 的块，其中 <code>0 &lt;= x &lt; m - 1</code> 且&nbsp;<code>0 &lt;= y &lt; n - 1</code> ，包含坐标为&nbsp;<code>[x, y]</code>&nbsp;，<code>[x + 1, y]</code>&nbsp;，<code>[x, y + 1]</code>&nbsp;和&nbsp;<code>[x + 1, y + 1]</code>&nbsp;的格子。</p>

<p>请你返回一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>5</code>&nbsp;的整数数组&nbsp;<code>arr</code>&nbsp;，<code>arr[i]</code>&nbsp;表示恰好包含&nbsp;<code>i</code>&nbsp;个&nbsp;<strong>黑色</strong>&nbsp;格子的块的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>m = 3, n = 3, coordinates = [[0,0]]
<b>输出：</b>[3,1,0,0,0]
<b>解释：</b>网格图如下：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2768.Number%20of%20Black%20Blocks/images/screen-shot-2023-06-18-at-44656-am.png" style="width: 150px; height: 128px;" />
只有 1 个块有一个黑色格子，这个块是左上角为 [0,0] 的块。
其他 3 个左上角分别为 [0,1] ，[1,0] 和 [1,1] 的块都有 0 个黑格子。
所以我们返回 [3,1,0,0,0] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>m = 3, n = 3, coordinates = [[0,0],[1,1],[0,2]]
<b>输出：</b>[0,2,2,0,0]
<b>解释：</b>网格图如下：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2768.Number%20of%20Black%20Blocks/images/screen-shot-2023-06-18-at-45018-am.png" style="width: 150px; height: 128px;" />
有 2 个块有 2 个黑色格子（左上角格子分别为 [0,0] 和 [0,1]）。
左上角为 [1,0] 和 [1,1] 的两个块，都有 1 个黑格子。
所以我们返回 [0,2,2,0,0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= coordinates.length &lt;= 10<sup>4</sup></code></li>
	<li><code>coordinates[i].length == 2</code></li>
	<li><code>0 &lt;= coordinates[i][0] &lt; m</code></li>
	<li><code>0 &lt;= coordinates[i][1] &lt; n</code></li>
	<li><code>coordinates</code>&nbsp;中的坐标对两两互不相同。</li>
</ul>

## 解法

### 方法一：哈希表计数

对于每个 $2 \times 2$ 的子矩阵，我们可以用其左上角的坐标 $(x, y)$ 来表示它。

而对于每个黑格子 $(x, y)$，它对 $4$ 个子矩阵的贡献为 $1$，即矩阵 $(x - 1, y - 1)$, $(x - 1, y)$, $(x, y - 1)$, $(x, y)$。

因此，我们遍历所有的黑格子，然后累计每个子矩阵中黑格子的个数，记录在哈希表 $cnt$ 中。

最后，我们遍历 $cnt$ 中的所有值（大于 $0$），统计其出现的次数，记录在答案数组 $ans$ 中，而 $ans[0]$ 则表示没有黑格子的子矩阵的个数，值为 $(m - 1) \times (n - 1) - \sum_{i = 1}^4 ans[i]$。

时间复杂度 $O(l)$，空间复杂度 $O(l)$，其中 $l$ 为 $coordinates$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def countBlackBlocks(
        self, m: int, n: int, coordinates: List[List[int]]
    ) -> List[int]:
        cnt = Counter()
        for x, y in coordinates:
            for a, b in pairwise((0, 0, -1, -1, 0)):
                i, j = x + a, y + b
                if 0 <= i < m - 1 and 0 <= j < n - 1:
                    cnt[(i, j)] += 1
        ans = [0] * 5
        for x in cnt.values():
            ans[x] += 1
        ans[0] = (m - 1) * (n - 1) - len(cnt.values())
        return ans
```

```java
class Solution {
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        Map<Long, Integer> cnt = new HashMap<>(coordinates.length);
        int[] dirs = {0, 0, -1, -1, 0};
        for (var e : coordinates) {
            int x = e[0], y = e[1];
            for (int k = 0; k < 4; ++k) {
                int i = x + dirs[k], j = y + dirs[k + 1];
                if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                    cnt.merge(1L * i * n + j, 1, Integer::sum);
                }
            }
        }
        long[] ans = new long[5];
        ans[0] = (m - 1L) * (n - 1);
        for (int x : cnt.values()) {
            ++ans[x];
            --ans[0];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<long long> countBlackBlocks(int m, int n, vector<vector<int>>& coordinates) {
        unordered_map<long long, int> cnt;
        int dirs[5] = {0, 0, -1, -1, 0};
        for (auto& e : coordinates) {
            int x = e[0], y = e[1];
            for (int k = 0; k < 4; ++k) {
                int i = x + dirs[k], j = y + dirs[k + 1];
                if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                    ++cnt[1LL * i * n + j];
                }
            }
        }
        vector<long long> ans(5);
        ans[0] = (m - 1LL) * (n - 1);
        for (auto& [_, x] : cnt) {
            ++ans[x];
            --ans[0];
        }
        return ans;
    }
};
```

```go
func countBlackBlocks(m int, n int, coordinates [][]int) []int64 {
	cnt := map[int64]int{}
	dirs := [5]int{0, 0, -1, -1, 0}
	for _, e := range coordinates {
		x, y := e[0], e[1]
		for k := 0; k < 4; k++ {
			i, j := x+dirs[k], y+dirs[k+1]
			if i >= 0 && i < m-1 && j >= 0 && j < n-1 {
				cnt[int64(i*n+j)]++
			}
		}
	}
	ans := make([]int64, 5)
	ans[0] = int64((m - 1) * (n - 1))
	for _, x := range cnt {
		ans[x]++
		ans[0]--
	}
	return ans
}
```

```ts
function countBlackBlocks(m: number, n: number, coordinates: number[][]): number[] {
    const cnt: Map<number, number> = new Map();
    const dirs: number[] = [0, 0, -1, -1, 0];
    for (const [x, y] of coordinates) {
        for (let k = 0; k < 4; ++k) {
            const [i, j] = [x + dirs[k], y + dirs[k + 1]];
            if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                const key = i * n + j;
                cnt.set(key, (cnt.get(key) || 0) + 1);
            }
        }
    }
    const ans: number[] = Array(5).fill(0);
    ans[0] = (m - 1) * (n - 1);
    for (const [_, x] of cnt) {
        ++ans[x];
        --ans[0];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
