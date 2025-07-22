---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0006.Zigzag%20Conversion/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion)

[中文文档](/solution/0000-0099/0006.Zigzag%20Conversion/README.md)

## Description

<!-- description:start -->

<p>The string <code>&quot;PAYPALISHIRING&quot;</code> is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)</p>

<pre>
P   A   H   N
A P L S I I G
Y   I   R
</pre>

<p>And then read line by line: <code>&quot;PAHNAPLSIIGYIR&quot;</code></p>

<p>Write the code that will take a string and make this conversion given a number of rows:</p>

<pre>
string convert(string s, int numRows);
</pre>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;PAYPALISHIRING&quot;, numRows = 3
<strong>Output:</strong> &quot;PAHNAPLSIIGYIR&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;PAYPALISHIRING&quot;, numRows = 4
<strong>Output:</strong> &quot;PINALSIGYAHRPI&quot;
<strong>Explanation:</strong>
P     I    N
A   L S  I G
Y A   H R
P     I
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;A&quot;, numRows = 1
<strong>Output:</strong> &quot;A&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of English letters (lower-case and upper-case), <code>&#39;,&#39;</code> and <code>&#39;.&#39;</code>.</li>
	<li><code>1 &lt;= numRows &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a 2D array $g$ to simulate the process of arranging the string in a zigzag pattern, where $g[i][j]$ represents the character at row $i$ and column $j$. Initially, $i = 0$. We also define a direction variable $k$, initially $k = -1$, which means moving upwards.

We traverse the string $s$ from left to right. For each character $c$, we append it to $g[i]$. If $i = 0$ or $i = \textit{numRows} - 1$, it means the current character is at a turning point in the zigzag pattern, so we reverse the value of $k$, i.e., $k = -k$. Then, we update $i$ to $i + k$, which means moving up or down one row. Continue traversing the next character until the end of the string $s$. Finally, we return the concatenation of all rows in $g$ as the result.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

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
