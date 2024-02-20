# [2746. 字符串连接删减字母](https://leetcode.cn/problems/decremental-string-concatenation)

[English Version](/solution/2700-2799/2746.Decremental%20String%20Concatenation/README_EN.md)

<!-- tags:数组,字符串,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>words</code>&nbsp;，它包含 <code>n</code>&nbsp;个字符串。</p>

<p>定义 <strong>连接</strong>&nbsp;操作&nbsp;<code>join(x, y)</code>&nbsp;表示将字符串&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;连在一起，得到&nbsp;<code>xy</code>&nbsp;。如果&nbsp;<code>x</code>&nbsp;的最后一个字符与&nbsp;<code>y</code>&nbsp;的第一个字符相等，连接后两个字符中的一个会被&nbsp;<strong>删除</strong>&nbsp;。</p>

<p>比方说&nbsp;<code>join("ab", "ba") = "aba"</code>&nbsp;，&nbsp;<code>join("ab", "cde") = "abcde"</code>&nbsp;。</p>

<p>你需要执行&nbsp;<code>n - 1</code>&nbsp;次&nbsp;<strong>连接</strong>&nbsp;操作。令&nbsp;<code>str<sub>0</sub> = words[0]</code>&nbsp;，从&nbsp;<code>i = 1</code> 直到&nbsp;<code>i = n - 1</code>&nbsp;，对于第&nbsp;<code>i</code>&nbsp;个操作，你可以执行以下操作之一：</p>

<ul>
	<li>令&nbsp;<code>str<sub>i</sub> = join(str<sub>i - 1</sub>, words[i])</code></li>
	<li>令&nbsp;<code>str<sub>i</sub> = join(words[i], str<sub>i - 1</sub>)</code></li>
</ul>

<p>你的任务是使&nbsp;<code>str<sub>n - 1</sub></code>&nbsp;的长度<strong>&nbsp;最小&nbsp;</strong>。</p>

<p>请你返回一个整数，表示&nbsp;<code>str<sub>n - 1</sub></code>&nbsp;的最小长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>words = ["aa","ab","bc"]
<b>输出：</b>4
<strong>解释：</strong>这个例子中，我们按以下顺序执行连接操作，得到 <code>str<sub>2</sub></code> 的最小长度：
<code>str<sub>0</sub> = "aa"</code>
<code>str<sub>1</sub> = join(str<sub>0</sub>, "ab") = "aab"
</code><code>str<sub>2</sub> = join(str<sub>1</sub>, "bc") = "aabc"</code> 
<code>str<sub>2</sub></code> 的最小长度为 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>words = ["ab","b"]
<b>输出：</b>2
<b>解释：</b>这个例子中，str<sub>0</sub> = "ab"，可以得到两个不同的 str<sub>1</sub>：
join(str<sub>0</sub>, "b") = "ab" 或者 join("b", str<sub>0</sub>) = "bab" 。
第一个字符串 "ab" 的长度最短，所以答案为 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>words = ["aaa","c","aba"]
<b>输出：</b>6
<b>解释：</b>这个例子中，我们按以下顺序执行连接操作，得到 <code>str<sub>2</sub>&nbsp;的最小长度：</code>
<code>str<sub>0</sub> = "</code>aaa"
<code>str<sub>1</sub> = join(str<sub>0</sub>, "c") = "aaac"</code>
<code>str<sub>2</sub> = join("aba", str<sub>1</sub>) = "abaaac"</code>
<code>str<sub>2</sub></code> 的最小长度为 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>words[i]</code>&nbsp;中只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：记忆化搜索

我们注意到，字符串连接时，字符串的第一个字符和最后一个字符会影响到连接后字符串的长度。因此，我们设计一个函数 $dfs(i, a, b)$，表示从第 $i$ 个字符串开始连接，且此前已经连接的字符串的第一个字符为 $a$，最后一个字符为 $b$ 时，连接后字符串的最小长度。

函数 $dfs(i, a, b)$ 的执行过程如下：

-   如果 $i = n$，说明所有字符串已经连接完毕，返回 $0$；
-   否则，我们考虑将第 $i$ 个字符串连接到已经连接的字符串的末尾或者开头，得到连接后字符串的长度 $x$ 和 $y$，则 $dfs(i, a, b) = \min(x, y) + |words[i]|$。

为了避免重复计算，我们使用记忆化搜索的方法。具体地，我们使用一个三维数组 $f$ 存储所有的 $dfs(i, a, b)$ 的返回值。当我们需要计算 $dfs(i, a, b)$ 时，如果 $f[i][a][b]$ 已经被计算过，我们直接返回 $f[i][a][b]$；否则我们按照上述的递推关系计算 $dfs(i, a, b)$ 的值，并将其存入 $f[i][a][b]$ 中。

在主函数中，我们直接返回 $|words[0]| + dfs(1, words[0][0], words[0][|words[0]| - 1])$。

时间复杂度 $O(n \times C^2)$，空间复杂度 $O(n \times C^2)$，其中 $C$ 表示字符串的最大长度。

<!-- tabs:start -->

```python
class Solution:
    def minimizeConcatenatedLength(self, words: List[str]) -> int:
        @cache
        def dfs(i: int, a: str, b: str) -> int:
            if i >= len(words):
                return 0
            s = words[i]
            x = dfs(i + 1, a, s[-1]) - int(s[0] == b)
            y = dfs(i + 1, s[0], b) - int(s[-1] == a)
            return len(s) + min(x, y)

        return len(words[0]) + dfs(1, words[0][0], words[0][-1])
```

```java
class Solution {
    private Integer[][][] f;
    private String[] words;
    private int n;

    public int minimizeConcatenatedLength(String[] words) {
        n = words.length;
        this.words = words;
        f = new Integer[n][26][26];
        return words[0].length()
            + dfs(1, words[0].charAt(0) - 'a', words[0].charAt(words[0].length() - 1) - 'a');
    }

    private int dfs(int i, int a, int b) {
        if (i >= n) {
            return 0;
        }
        if (f[i][a][b] != null) {
            return f[i][a][b];
        }
        String s = words[i];
        int m = s.length();
        int x = dfs(i + 1, a, s.charAt(m - 1) - 'a') - (s.charAt(0) - 'a' == b ? 1 : 0);
        int y = dfs(i + 1, s.charAt(0) - 'a', b) - (s.charAt(m - 1) - 'a' == a ? 1 : 0);
        return f[i][a][b] = m + Math.min(x, y);
    }
}
```

```cpp
class Solution {
public:
    int minimizeConcatenatedLength(vector<string>& words) {
        int n = words.size();
        int f[n][26][26];
        memset(f, 0, sizeof(f));
        function<int(int, int, int)> dfs = [&](int i, int a, int b) {
            if (i >= n) {
                return 0;
            }
            if (f[i][a][b]) {
                return f[i][a][b];
            }
            auto s = words[i];
            int m = s.size();
            int x = dfs(i + 1, a, s[m - 1] - 'a') - (s[0] - 'a' == b);
            int y = dfs(i + 1, s[0] - 'a', b) - (s[m - 1] - 'a' == a);
            return f[i][a][b] = m + min(x, y);
        };
        return words[0].size() + dfs(1, words[0].front() - 'a', words[0].back() - 'a');
    }
};
```

```go
func minimizeConcatenatedLength(words []string) int {
	n := len(words)
	f := make([][26][26]int, n)
	var dfs func(i, a, b int) int
	dfs = func(i, a, b int) int {
		if i >= n {
			return 0
		}
		if f[i][a][b] > 0 {
			return f[i][a][b]
		}
		s := words[i]
		m := len(s)
		x := dfs(i+1, a, int(s[m-1]-'a'))
		y := dfs(i+1, int(s[0]-'a'), b)
		if int(s[0]-'a') == b {
			x--
		}
		if int(s[m-1]-'a') == a {
			y--
		}
		f[i][a][b] = m + min(x, y)
		return f[i][a][b]
	}
	return len(words[0]) + dfs(1, int(words[0][0]-'a'), int(words[0][len(words[0])-1]-'a'))
}
```

```ts
function minimizeConcatenatedLength(words: string[]): number {
    const n = words.length;
    const f: number[][][] = Array(n)
        .fill(0)
        .map(() =>
            Array(26)
                .fill(0)
                .map(() => Array(26).fill(0)),
        );
    const dfs = (i: number, a: number, b: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][a][b] > 0) {
            return f[i][a][b];
        }
        const s = words[i];
        const m = s.length;
        const x =
            dfs(i + 1, a, s[m - 1].charCodeAt(0) - 97) - (s[0].charCodeAt(0) - 97 === b ? 1 : 0);
        const y =
            dfs(i + 1, s[0].charCodeAt(0) - 97, b) - (s[m - 1].charCodeAt(0) - 97 === a ? 1 : 0);
        return (f[i][a][b] = Math.min(x + m, y + m));
    };
    return (
        words[0].length +
        dfs(1, words[0][0].charCodeAt(0) - 97, words[0][words[0].length - 1].charCodeAt(0) - 97)
    );
}
```

<!-- tabs:end -->

<!-- end -->
