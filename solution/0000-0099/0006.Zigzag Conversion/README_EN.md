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
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;PAYPALISHIRING&quot;, numRows = 3
<strong>Output:</strong> &quot;PAHNAPLSIIGYIR&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;PAYPALISHIRING&quot;, numRows = 4
<strong>Output:</strong> &quot;PINALSIGYAHRPI&quot;
<strong>Explanation:</strong>
P     I    N
A   L S  I G
Y A   H R
P     I
</pre>

<p><strong>Example 3:</strong></p>

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **C#**

```cs
using System.Collections.Generic;
using System.Linq;

public class Solution {
    public string Convert(string s, int numRows) {
        if (numRows == 1) return s;
        if (numRows > s.Length) numRows = s.Length;
        var rows = new List<char>[numRows];
        var i = 0;
        var j = 0;
        var down = true;
        while (i < s.Length)
        {
            if (rows[j] == null)
            {
                rows[j] = new List<char>();
            }
            rows[j].Add(s[i]);
            j = j + (down ? 1 : -1);
            if (j == numRows || j < 0)
            {
                down = !down;
                j = j + (down ? 2 : -2);
            }
            ++i;
        }
        return new string(rows.SelectMany(row => row).ToArray());
    }
}
```

### **Go**

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

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function (s, numRows) {
    if (numRows == 1) return s;
    let arr = new Array(numRows);
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
    for (let item of arr) {
        ans = ans.concat(item);
    }
    return ans.join('');
};
```

### **TypeScript**

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

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
