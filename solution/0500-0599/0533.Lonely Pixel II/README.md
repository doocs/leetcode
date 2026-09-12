---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0533.Lonely%20Pixel%20II/README.md
tags:
    - 数组
    - 哈希表
    - 矩阵
---

<!-- problem:start -->

# [533. 孤独像素 II 🔒](https://leetcode.cn/problems/lonely-pixel-ii)

[English Version](/solution/0500-0599/0533.Lonely%20Pixel%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>m x n</code> 的二维字符数组 <code>picture</code> ，表示一张黑白图像，数组中的 <code>'B'</code> 表示黑色像素，<code>'W'</code> 表示白色像素。另给你一个整数 <code>target</code> ，请你找出并返回符合规则的 <strong>黑色</strong> 孤独像素的数量。</p>

<p>黑色孤独像素是指位于某一特定位置 <code>(r, c)</code> 的字符 <code>'B'</code> ，其中：</p>

<ul>
	<li>行 <code>r</code> 和列 <code>c</code> 中的黑色像素恰好有 <code>target</code> 个。</li>
	<li>列 <code>c</code> 中所有黑色像素所在的行必须和行 <code>r</code> 完全相同。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/1694957797-UWXAxl-image.png" style="width: 493px; height: 333px;" />
<pre>
<strong>输入：</strong>picture = [["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","W","B","W","B","W"]], target = 3
<strong>输出：</strong>6
<strong>解释：</strong>所有绿色的 'B' 都是我们所求的像素(第 1 列和第 3 列的所有 'B' )
以行 r = 0 和列 c = 1 的 'B' 为例：
- 规则 1 ，行 r = 0 和列 c = 1 都恰好有 target = 3 个黑色像素 
- 规则 2 ，列 c = 1 的黑色像素分别位于行 0，行 1 和行 2。和行 r = 0 完全相同。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/1694957806-FyCCMF-image.png" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>picture = [["W","W","B"],["W","W","B"],["W","W","B"]], target = 1
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m ==&nbsp;picture.length</code></li>
	<li><code>n ==&nbsp;picture[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>picture[i][j]</code> 为 <code>'W'</code> 或 <code>'B'</code></li>
	<li><code>1 &lt;= target &lt;= min(m, n)</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

<!-- thinking:start -->

> **思考**
>
> 除行、列恰有 $target$ 个 `B` 外，还要求该列上每个 `B` 所在行的图案完全相同。逐列再比所有行会重复扫描。
>
> 先统计每行黑点数，并按列记录出现 `B` 的行号。对一列，取其中一个行模板：若该列的行数等于模板的黑点数 $target$，且这些行与模板逐格相同，则这一列贡献 $target$ 个像素。行相等用整行比较，避免逐格重写。

<!-- thinking:end -->

题目中条件二相当于要求对于每一列中所有包含黑色像素的行，这些行完全相同。

因此，我们可以用一个邻接表 $g$ 来存储每一列中所有包含黑色像素的行，即 $g[j]$ 表示第 $j$ 列中所有包含黑色像素的行的集合。另外，用一个数组 $rows$ 来存储每一行中黑色像素的数量。

接下来，我们遍历每一列，对于每一列，我们找到第一个包含黑色像素的行 $i_1$，如果这一行中黑色像素的数量不等于 $target$，那么这一列不可能包含孤独像素，直接跳过。否则，我们检查这一列中所有包含黑色像素的行是否和第 $i_1$ 行完全相同，如果是，则这一列中所有的黑色像素都是孤独像素，答案加上 $target$。

遍历结束后，返回答案即可。

时间复杂度 $O(m \times n^2)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findBlackPixel(self, picture: List[List[str]], target: int) -> int:
        rows = [0] * len(picture)
        g = defaultdict(list)
        for i, row in enumerate(picture):
            for j, x in enumerate(row):
                if x == "B":
                    rows[i] += 1
                    g[j].append(i)
        ans = 0
        for j in g:
            i1 = g[j][0]
            if rows[i1] != target:
                continue
            if len(g[j]) == rows[i1] and all(picture[i2] == picture[i1] for i2 in g[j]):
                ans += target
        return ans
```

#### Java

```java
class Solution {
    public int findBlackPixel(char[][] picture, int target) {
        int m = picture.length;
        int n = picture[0].length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] rows = new int[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    g[j].add(i);
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            if (g[j].isEmpty() || (rows[g[j].get(0)] != target)) {
                continue;
            }
            int i1 = g[j].get(0);
            int ok = 0;
            if (g[j].size() == rows[i1]) {
                ok = target;
                for (int i2 : g[j]) {
                    if (!Arrays.equals(picture[i1], picture[i2])) {
                        ok = 0;
                        break;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findBlackPixel(vector<vector<char>>& picture, int target) {
        int m = picture.size();
        int n = picture[0].size();
        vector<int> g[n];
        vector<int> rows(m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    g[j].push_back(i);
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < n; ++j) {
            if (g[j].empty() || (rows[g[j][0]] != target)) {
                continue;
            }
            int i1 = g[j][0];
            int ok = 0;
            if (g[j].size() == rows[i1]) {
                ok = target;
                for (int i2 : g[j]) {
                    if (picture[i1] != picture[i2]) {
                        ok = 0;
                        break;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
};
```

#### Go

```go
func findBlackPixel(picture [][]byte, target int) (ans int) {
	m := len(picture)
	n := len(picture[0])
	g := make([][]int, n)
	rows := make([]int, m)
	for i, row := range picture {
		for j, x := range row {
			if x == 'B' {
				rows[i]++
				g[j] = append(g[j], i)
			}
		}
	}
	for j := 0; j < n; j++ {
		if len(g[j]) == 0 || rows[g[j][0]] != target {
			continue
		}
		i1 := g[j][0]
		ok := 0
		if len(g[j]) == rows[i1] {
			ok = target
			for _, i2 := range g[j] {
				if !bytes.Equal(picture[i1], picture[i2]) {
					ok = 0
					break
				}
			}
		}
		ans += ok
	}
	return
}
```

#### TypeScript

```ts
function findBlackPixel(picture: string[][], target: number): number {
    const m: number = picture.length;
    const n: number = picture[0].length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const rows: number[] = Array(m).fill(0);

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (picture[i][j] === 'B') {
                ++rows[i];
                g[j].push(i);
            }
        }
    }

    let ans: number = 0;
    for (let j = 0; j < n; ++j) {
        if (g[j].length === 0 || rows[g[j][0]] !== target) {
            continue;
        }
        const i1: number = g[j][0];
        let ok: number = 0;
        if (g[j].length === rows[i1]) {
            ok = target;
            for (const i2 of g[j]) {
                if (picture[i1].join('') !== picture[i2].join('')) {
                    ok = 0;
                    break;
                }
            }
        }
        ans += ok;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
