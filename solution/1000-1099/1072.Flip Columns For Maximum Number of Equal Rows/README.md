---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1072.Flip%20Columns%20For%20Maximum%20Number%20of%20Equal%20Rows/README.md
rating: 1797
source: 第 139 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 矩阵
---

<!-- problem:start -->

# [1072. 按列翻转得到最大值等行数](https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows)

[English Version](/solution/1000-1099/1072.Flip%20Columns%20For%20Maximum%20Number%20of%20Equal%20Rows/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定&nbsp;<code>m x n</code>&nbsp;矩阵&nbsp;<code>matrix</code>&nbsp;。</p>

<p>你可以从中选出任意数量的列并翻转其上的&nbsp;<strong>每个&nbsp;</strong>单元格。（即翻转后，单元格的值从 <code>0</code> 变成 <code>1</code>，或者从 <code>1</code> 变为 <code>0</code> 。）</p>

<p>返回 <em>经过一些翻转后，行内所有值都相等的最大行数</em>&nbsp;。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[0,1],[1,1]]
<strong>输出：</strong>1
<strong>解释：</strong>不进行翻转，有 1 行所有值都相等。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[0,1],[1,0]]
<strong>输出：</strong>2
<strong>解释：</strong>翻转第一列的值之后，这两行都由相等的值组成。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[0,0,0],[0,0,1],[1,1,0]]
<strong>输出：</strong>2
<strong>解释：</strong>翻转前两列的值之后，后两行由相等的值组成。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>matrix[i][j] == 0</code> 或&nbsp;<code>1</code></li>
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

这样一来，我们只需要用一个哈希表来统计转换后的每一行的出现次数，其中键为转换后的行（可以将所有数字拼接成一个字符串），值为该行出现的次数。最后，哈希表中值的最大值即为矩阵中最多包含等价行的行数。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

相似题目：

-   [2128. 通过翻转行或列来去除所有的 1](https://github.com/doocs/leetcode/blob/main/solution/2100-2199/2128.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips/README.md)

<!-- tabs:start -->

```python
class Solution:
    def maxEqualRowsAfterFlips(self, matrix: List[List[int]]) -> int:
        cnt = Counter()
        for row in matrix:
            t = tuple(row) if row[0] == 0 else tuple(x ^ 1 for x in row)
            cnt[t] += 1
        return max(cnt.values())
```

```java
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> cnt = new HashMap<>();
        int ans = 0, n = matrix[0].length;
        for (var row : matrix) {
            char[] cs = new char[n];
            for (int i = 0; i < n; ++i) {
                cs[i] = (char) (row[0] ^ row[i]);
            }
            ans = Math.max(ans, cnt.merge(String.valueOf(cs), 1, Integer::sum));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxEqualRowsAfterFlips(vector<vector<int>>& matrix) {
        unordered_map<string, int> cnt;
        int ans = 0;
        for (auto& row : matrix) {
            string s;
            for (int x : row) {
                s.push_back('0' + (row[0] == 0 ? x : x ^ 1));
            }
            ans = max(ans, ++cnt[s]);
        }
        return ans;
    }
};
```

```go
func maxEqualRowsAfterFlips(matrix [][]int) (ans int) {
	cnt := map[string]int{}
	for _, row := range matrix {
		s := []byte{}
		for _, x := range row {
			if row[0] == 1 {
				x ^= 1
			}
			s = append(s, byte(x)+'0')
		}
		t := string(s)
		cnt[t]++
		ans = max(ans, cnt[t])
	}
	return
}
```

```ts
function maxEqualRowsAfterFlips(matrix: number[][]): number {
    const cnt = new Map<string, number>();
    let ans = 0;
    for (const row of matrix) {
        if (row[0] === 1) {
            for (let i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }
        const s = row.join('');
        cnt.set(s, (cnt.get(s) || 0) + 1);
        ans = Math.max(ans, cnt.get(s)!);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
