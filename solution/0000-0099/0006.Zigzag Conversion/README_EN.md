---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0006.Zigzag%20Conversion/README_EN.md
tags:
    - String
---

# [6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion)

[中文文档](/solution/0000-0099/0006.Zigzag%20Conversion/README.md)

## Description

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

## Solutions

### Solution 1: Simulation

We use a two-dimensional array $g$ to simulate the process of the $Z$-shape arrangement, where $g[i][j]$ represents the character at the $i$-th row and the $j$-th column. Initially, $i=0$, and we define a direction variable $k$, initially $k=-1$, indicating moving upwards.

We traverse the string $s$ from left to right. Each time we traverse to a character $c$, we append it to $g[i]$. If $i=0$ or $i=numRows-1$ at this time, it means that the current character is at the turning point of the $Z$-shape arrangement, and we reverse the value of $k$, i.e., $k=-k$. Next, we update the value of $i$ to $i+k$, i.e., move up or down one row. Continue to traverse the next character until we have traversed the string $s$, and we return the string concatenated by all rows in $g$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

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

```rust
impl Solution {
    pub fn convert(s: String, num_rows: i32) -> String {
        let num_rows = num_rows as usize;
        if num_rows == 1 {
            return s;
        }
        let mut ss = vec![String::new(); num_rows];
        let mut i = 0;
        let mut to_down = true;
        for c in s.chars() {
            ss[i].push(c);
            if to_down {
                i += 1;
            } else {
                i -= 1;
            }
            if i == 0 || i == num_rows - 1 {
                to_down = !to_down;
            }
        }
        let mut res = String::new();
        for i in 0..num_rows {
            res += &ss[i];
        }
        res
    }
}
```

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

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1:
            return s
        group = 2 * numRows - 2
        ans = []
        for i in range(1, numRows + 1):
            interval = group if i == numRows else 2 * numRows - 2 * i
            idx = i - 1
            while idx < len(s):
                ans.append(s[idx])
                idx += interval
                interval = group - interval
                if interval == 0:
                    interval = group
        return ''.join(ans)
```

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder ans = new StringBuilder();
        int group = 2 * numRows - 2;
        for (int i = 1; i <= numRows; i++) {
            int interval = i == numRows ? group : 2 * numRows - 2 * i;
            int idx = i - 1;
            while (idx < s.length()) {
                ans.append(s.charAt(idx));
                idx += interval;
                interval = group - interval;
                if (interval == 0) {
                    interval = group;
                }
            }
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1) return s;
        string ans;
        int group = 2 * numRows - 2;
        for (int i = 1; i <= numRows; ++i) {
            int interval = i == numRows ? group : 2 * numRows - 2 * i;
            int idx = i - 1;
            while (idx < s.length()) {
                ans.push_back(s[idx]);
                idx += interval;
                interval = group - interval;
                if (interval == 0) interval = group;
            }
        }
        return ans;
    }
};
```

```go
func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	n := len(s)
	ans := make([]byte, n)
	step := 2*numRows - 2
	count := 0
	for i := 0; i < numRows; i++ {
		for j := 0; j+i < n; j += step {
			ans[count] = s[i+j]
			count++
			if i != 0 && i != numRows-1 && j+step-i < n {
				ans[count] = s[j+step-i]
				count++
			}
		}
	}
	return string(ans)
}
```

```ts
function convert(s: string, numRows: number): string {
    if (numRows === 1) {
        return s;
    }
    const ss = new Array(numRows).fill('');
    let i = 0;
    let toDown = true;
    for (const c of s) {
        ss[i] += c;
        if (toDown) {
            i++;
        } else {
            i--;
        }
        if (i === 0 || i === numRows - 1) {
            toDown = !toDown;
        }
    }
    return ss.reduce((r, s) => r + s);
}
```

```rust
impl Solution {
    pub fn convert(s: String, num_rows: i32) -> String {
        let num_rows = num_rows as usize;
        let mut rows = vec![String::new(); num_rows];
        let iter = (0..num_rows).chain((1..num_rows - 1).rev()).cycle();
        iter.zip(s.chars()).for_each(|(i, c)| rows[i].push(c));
        rows.into_iter().collect()
    }
}
```

```js
/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function (s, numRows) {
    if (numRows == 1) return s;
    const arr = new Array(numRows);
    for (let i = 0; i < numRows; i++) arr[i] = [];
    let mi = 0,
        isDown = true;
    for (const c of s) {
        arr[mi].push(c);

        if (mi >= numRows - 1) isDown = false;
        else if (mi <= 0) isDown = true;

        if (isDown) mi++;
        else mi--;
    }
    let ans = [];
    for (const item of arr) {
        ans = ans.concat(item);
    }
    return ans.join('');
};
```

```php
class Solution {
    /**
     * @param string $s
     * @param int $numRows
     * @return string
     */

    function convert($s, $numRows) {
        if ($numRows == 1 || strlen($s) <= $numRows) {
            return $s;
        }

        $result = '';
        $cycleLength = 2 * $numRows - 2;
        $n = strlen($s);

        for ($i = 0; $i < $numRows; $i++) {
            for ($j = 0; $j + $i < $n; $j += $cycleLength) {
                $result .= $s[$j + $i];

                if ($i != 0 && $i != $numRows - 1 && $j + $cycleLength - $i < $n) {
                    $result .= $s[$j + $cycleLength - $i];
                }
            }
        }

        return $result;
    }
}
```

<!-- tabs:end -->

<!-- end -->
