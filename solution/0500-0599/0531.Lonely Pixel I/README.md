---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0531.Lonely%20Pixel%20I/README.md
tags:
    - 数组
    - 哈希表
    - 矩阵
---

# [531. 孤独像素 I 🔒](https://leetcode.cn/problems/lonely-pixel-i)

[English Version](/solution/0500-0599/0531.Lonely%20Pixel%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的图像 <code>picture</code> ，图像由黑白像素组成，<code>'B'</code> 表示黑色像素，<code>'W'</code> 表示白色像素，请你统计并返回图像中 <strong>黑色</strong> 孤独像素的数量。</p>

<p><strong>黑色孤独像素</strong> 的定义为：如果黑色像素 <code>'B'</code> 所在的同一行和同一列不存在其他黑色像素，那么这个黑色像素就是黑色孤独像素。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0531.Lonely%20Pixel%20I/images/pixel1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
<strong>输出：</strong>3
<strong>解释：</strong>全部三个 'B' 都是黑色的孤独像素
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0531.Lonely%20Pixel%20I/images/pixel2.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m ==&nbsp;picture.length</code></li>
	<li><code>n ==&nbsp;picture[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>picture[i][j]</code> 为 <code>'W'</code> 或 <code>'B'</code></li>
</ul>

## 解法

### 方法一：计数 + 枚举

根据题目描述，我们需要统计每一行和每一列的黑色像素数量，分别记录在数组 $\textit{rows}$ 和 $\textit{cols}$ 中。然后我们遍历每一个黑色像素，检查其所在的行和列是否只有一个黑色像素，如果是则将答案加一。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def findLonelyPixel(self, picture: List[List[str]]) -> int:
        rows = [0] * len(picture)
        cols = [0] * len(picture[0])
        for i, row in enumerate(picture):
            for j, x in enumerate(row):
                if x == "B":
                    rows[i] += 1
                    cols[j] += 1
        ans = 0
        for i, row in enumerate(picture):
            for j, x in enumerate(row):
                if x == "B" and rows[i] == 1 and cols[j] == 1:
                    ans += 1
        return ans
```

```java
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    ++cols[j];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findLonelyPixel(vector<vector<char>>& picture) {
        int m = picture.size(), n = picture[0].size();
        vector<int> rows(m);
        vector<int> cols(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    ++cols[j];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func findLonelyPixel(picture [][]byte) (ans int) {
	rows := make([]int, len(picture))
	cols := make([]int, len(picture[0]))
	for i, row := range picture {
		for j, x := range row {
			if x == 'B' {
				rows[i]++
				cols[j]++
			}
		}
	}
	for i, row := range picture {
		for j, x := range row {
			if x == 'B' && rows[i] == 1 && cols[j] == 1 {
				ans++
			}
		}
	}
	return
}
```

```ts
function findLonelyPixel(picture: string[][]): number {
    const m = picture.length;
    const n = picture[0].length;
    const rows: number[] = Array(m).fill(0);
    const cols: number[] = Array(n).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (picture[i][j] === 'B') {
                ++rows[i];
                ++cols[j];
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (picture[i][j] === 'B' && rows[i] === 1 && cols[j] === 1) {
                ++ans;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
