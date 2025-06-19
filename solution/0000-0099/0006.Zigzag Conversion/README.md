---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0006.Zigzag%20Conversion/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [6. Z 字形变换](https://leetcode.cn/problems/zigzag-conversion)

[English Version](/solution/0000-0099/0006.Zigzag%20Conversion/README_EN.md)

## 题目描述

<!-- description:start -->

<p>将一个给定字符串 <code>s</code> 根据给定的行数 <code>numRows</code> ，以从上往下、从左到右进行 Z 字形排列。</p>

<p>比如输入字符串为 <code>"PAYPALISHIRING"</code> 行数为 <code>3</code> 时，排列如下：</p>

<pre>
P   A   H   N
A P L S I I G
Y   I   R</pre>

<p>之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如：<code>"PAHNAPLSIIGYIR"</code>。</p>

<p>请你实现这个将字符串进行指定行数变换的函数：</p>

<pre>
string convert(string s, int numRows);</pre>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "PAYPALISHIRING", numRows = 3
<strong>输出：</strong>"PAHNAPLSIIGYIR"
</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入：</strong>s = "PAYPALISHIRING", numRows = 4
<strong>输出：</strong>"PINALSIGYAHRPI"
<strong>解释：</strong>
P     I    N
A   L S  I G
Y A   H R
P     I
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "A", numRows = 1
<strong>输出：</strong>"A"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>s</code> 由英文字母（小写和大写）、<code>','</code> 和 <code>'.'</code> 组成</li>
	<li><code>1 <= numRows <= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个二维数组 $g$ 来模拟 Z 字形排列的过程，其中 $g[i][j]$ 表示第 $i$ 行第 $j$ 列的字符。初始时 $i = 0$，另外我们定义一个方向变量 $k$，初始时 $k = -1$，表示向上走。

我们从左到右遍历字符串 $s$，每次遍历到一个字符 $c$，将其追加到 $g[i]$ 中。如果此时 $i = 0$ 或者 $i = \textit{numRows} - 1$，说明当前字符位于 Z 字形排列的拐点，我们将 $k$ 的值反转，即 $k = -k$。接下来，我们将 $i$ 的值更新为 $i + k$，即向上或向下移动一行。继续遍历下一个字符，直到遍历完字符串 $s$，我们返回 $g$ 中所有行拼接后的字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1:
            return s
        g = [[] for _ in range(numRows)]
        i, k = 0, -1
        for c in s:
            g[i].append(c)
            if i == 0 or i == numRows - 1:
                k = -k
            i += k
        return ''.join(chain(*g))
```

#### Java

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] g = new StringBuilder[numRows];
        Arrays.setAll(g, k -> new StringBuilder());
        int i = 0, k = -1;
        for (char c : s.toCharArray()) {
            g[i].append(c);
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        return String.join("", g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        vector<string> g(numRows);
        int i = 0, k = -1;
        for (char c : s) {
            g[i] += c;
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        string ans;
        for (auto& t : g) {
            ans += t;
        }
        return ans;
    }
};
```

#### Go

```go
func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	g := make([][]byte, numRows)
	i, k := 0, -1
	for _, c := range s {
		g[i] = append(g[i], byte(c))
		if i == 0 || i == numRows-1 {
			k = -k
		}
		i += k
	}
	return string(bytes.Join(g, nil))
}
```

#### TypeScript

```ts
function convert(s: string, numRows: number): string {
    if (numRows === 1) {
        return s;
    }
    const g: string[][] = new Array(numRows).fill(0).map(() => []);
    let i = 0;
    let k = -1;
    for (const c of s) {
        g[i].push(c);
        if (i === numRows - 1 || i === 0) {
            k = -k;
        }
        i += k;
    }
    return g.flat().join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn convert(s: String, num_rows: i32) -> String {
        if num_rows == 1 {
            return s;
        }

        let num_rows = num_rows as usize;
        let mut g = vec![String::new(); num_rows];
        let mut i = 0;
        let mut k = -1;

        for c in s.chars() {
            g[i].push(c);
            if i == 0 || i == num_rows - 1 {
                k = -k;
            }
            i = (i as isize + k) as usize;
        }

        g.concat()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function (s, numRows) {
    if (numRows === 1) {
        return s;
    }
    const g = new Array(numRows).fill(_).map(() => []);
    let i = 0;
    let k = -1;
    for (const c of s) {
        g[i].push(c);
        if (i === 0 || i === numRows - 1) {
            k = -k;
        }
        i += k;
    }
    return g.flat().join('');
};
```

#### C#

```cs
public class Solution {
    public string Convert(string s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.Length;
        StringBuilder[] g = new StringBuilder[numRows];
        for (int j = 0; j < numRows; ++j) {
            g[j] = new StringBuilder();
        }
        int i = 0, k = -1;
        foreach (char c in s.ToCharArray()) {
            g[i].Append(c);
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        StringBuilder ans = new StringBuilder();
        foreach (StringBuilder t in g) {
            ans.Append(t);
        }
        return ans.ToString();
    }
}
```

#### C

```c
char* convert(char* s, int numRows) {
    if (numRows == 1) {
        return strdup(s);
    }

    int len = strlen(s);
    char** g = (char**) malloc(numRows * sizeof(char*));
    int* idx = (int*) malloc(numRows * sizeof(int));
    for (int i = 0; i < numRows; ++i) {
        g[i] = (char*) malloc((len + 1) * sizeof(char));
        idx[i] = 0;
    }

    int i = 0, k = -1;
    for (int p = 0; p < len; ++p) {
        g[i][idx[i]++] = s[p];
        if (i == 0 || i == numRows - 1) {
            k = -k;
        }
        i += k;
    }

    char* ans = (char*) malloc((len + 1) * sizeof(char));
    int pos = 0;
    for (int r = 0; r < numRows; ++r) {
        for (int j = 0; j < idx[r]; ++j) {
            ans[pos++] = g[r][j];
        }
        free(g[r]);
    }
    ans[pos] = '\0';

    free(g);
    free(idx);
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @param Integer $numRows
     * @return String
     */
    function convert($s, $numRows) {
        if ($numRows == 1) {
            return $s;
        }

        $g = array_fill(0, $numRows, '');
        $i = 0;
        $k = -1;

        $length = strlen($s);
        for ($j = 0; $j < $length; $j++) {
            $c = $s[$j];
            $g[$i] .= $c;

            if ($i == 0 || $i == $numRows - 1) {
                $k = -$k;
            }

            $i += $k;
        }
        return implode('', $g);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
