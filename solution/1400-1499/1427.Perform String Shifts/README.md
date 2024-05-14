# [1427. 字符串的左右移 🔒](https://leetcode.cn/problems/perform-string-shifts)

[English Version](/solution/1400-1499/1427.Perform%20String%20Shifts/README_EN.md)

<!-- tags:数组,数学,字符串 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含小写英文字母的字符串&nbsp;<code>s</code>&nbsp;以及一个矩阵&nbsp;<code>shift</code>，其中&nbsp;<code>shift[i] = [direction, amount]</code>：</p>

<ul>
	<li><code>direction</code>&nbsp;可以为&nbsp;<code>0</code>&nbsp;（表示左移）或&nbsp;<code>1</code>&nbsp;（表示右移）。</li>
	<li><code>amount</code>&nbsp;表示&nbsp;<code>s</code>&nbsp;左右移的位数。</li>
	<li>左移 1 位表示移除&nbsp;<code>s</code>&nbsp;的第一个字符，并将该字符插入到 <code>s</code> 的结尾。</li>
	<li>类似地，右移 1 位表示移除&nbsp;<code>s</code>&nbsp;的最后一个字符，并将该字符插入到 <code>s</code> 的开头。</li>
</ul>

<p>对这个字符串进行所有操作后，返回最终结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;abc&quot;, shift = [[0,1],[1,2]]
<strong>输出：</strong>&quot;cab&quot;
<strong>解释：</strong>
[0,1] 表示左移 1 位。 &quot;abc&quot; -&gt; &quot;bca&quot;
[1,2] 表示右移 2 位。 &quot;bca&quot; -&gt; &quot;cab&quot;</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;abcdefg&quot;, shift = [[1,1],[1,1],[0,2],[1,3]]
<strong>输出：</strong>&quot;efgabcd&quot;
<strong>解释：</strong> 
[1,1] 表示右移 1 位。 &quot;abcdefg&quot; -&gt; &quot;gabcdef&quot;
[1,1] 表示右移 1 位。 &quot;gabcdef&quot; -&gt; &quot;fgabcde&quot;
[0,2] 表示左移 2 位。 &quot;fgabcde&quot; -&gt; &quot;abcdefg&quot;
[1,3] 表示右移 3 位。 &quot;abcdefg&quot; -&gt; &quot;efgabcd&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母</li>
	<li><code>1 &lt;= shift.length &lt;= 100</code></li>
	<li><code>shift[i].length == 2</code></li>
	<li><code>0 &lt;= shift[i][0] &lt;= 1</code></li>
	<li><code>0 &lt;= shift[i][1] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：模拟

我们不妨记字符串 $s$ 的长度为 $n$。接下来遍历数组 $shift$，累加得到最终的偏移量 $x$，然后将 $x$ 对 $n$ 取模，最终结果就是将 $s$ 的前 $n - x$ 个字符移动到末尾。

时间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别是字符串 $s$ 的长度和数组 $shift$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def stringShift(self, s: str, shift: List[List[int]]) -> str:
        x = sum((b if a else -b) for a, b in shift)
        x %= len(s)
        return s[-x:] + s[:-x]
```

```java
class Solution {
    public String stringShift(String s, int[][] shift) {
        int x = 0;
        for (var e : shift) {
            if (e[0] == 0) {
                e[1] *= -1;
            }
            x += e[1];
        }
        int n = s.length();
        x = (x % n + n) % n;
        return s.substring(n - x) + s.substring(0, n - x);
    }
}
```

```cpp
class Solution {
public:
    string stringShift(string s, vector<vector<int>>& shift) {
        int x = 0;
        for (auto& e : shift) {
            if (e[0] == 0) {
                e[1] = -e[1];
            }
            x += e[1];
        }
        int n = s.size();
        x = (x % n + n) % n;
        return s.substr(n - x, x) + s.substr(0, n - x);
    }
};
```

```go
func stringShift(s string, shift [][]int) string {
	x := 0
	for _, e := range shift {
		if e[0] == 0 {
			e[1] = -e[1]
		}
		x += e[1]
	}
	n := len(s)
	x = (x%n + n) % n
	return s[n-x:] + s[:n-x]
}
```

```ts
function stringShift(s: string, shift: number[][]): string {
    let x = 0;
    for (const [a, b] of shift) {
        x += a === 0 ? -b : b;
    }
    x %= s.length;
    if (x < 0) {
        x += s.length;
    }
    return s.slice(-x) + s.slice(0, -x);
}
```

<!-- tabs:end -->

<!-- end -->
