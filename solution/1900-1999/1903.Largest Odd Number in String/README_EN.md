# [1903. Largest Odd Number in String](https://leetcode.com/problems/largest-odd-number-in-string)

[中文文档](/solution/1900-1999/1903.Largest%20Odd%20Number%20in%20String/README.md)

## Description

<p>You are given a string <code>num</code>, representing a large integer. Return <em>the <strong>largest-valued odd</strong> integer (as a string) that is a <strong>non-empty substring</strong> of </em><code>num</code><em>, or an empty string </em><code>&quot;&quot;</code><em> if no odd integer exists</em>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;52&quot;
<strong>Output:</strong> &quot;5&quot;
<strong>Explanation:</strong> The only non-empty substrings are &quot;5&quot;, &quot;2&quot;, and &quot;52&quot;. &quot;5&quot; is the only odd number.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;4206&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are no odd numbers in &quot;4206&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;35427&quot;
<strong>Output:</strong> &quot;35427&quot;
<strong>Explanation:</strong> &quot;35427&quot; is already an odd number.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> only consists of digits and does not contain any leading zeros.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestOddNumber(self, num: str) -> str:
        for i in range(len(num) - 1, -1, -1):
            if (int(num[i]) & 1) == 1:
                return num[: i + 1]
        return ''
```

### **Java**

```java
class Solution {
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; --i) {
            int c = num.charAt(i) - '0';
            if ((c & 1) == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} num
 * @return {string}
 */
var largestOddNumber = function (num) {
    let n = num.length;
    for (let j = n - 1; j >= 0; j--) {
        if (num.charAt(j) & (1 == 1)) {
            return num.slice(0, j + 1);
        }
    }
    return '';
};
```

### **C++**

```cpp
class Solution {
public:
    string largestOddNumber(string num) {
        for (int i = num.size() - 1; i >= 0; --i) {
            int c = num[i] - '0';
            if ((c & 1) == 1) {
                return num.substr(0, i + 1);
            }
        }
        return "";
    }
};
```

### **Go**

```go
func largestOddNumber(num string) string {
	for i := len(num) - 1; i >= 0; i-- {
		c := num[i] - '0'
		if (c & 1) == 1 {
			return num[:i+1]
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
