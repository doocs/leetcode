---
comments: true
difficulty: Easy
rating: 1248
source: Weekly Contest 246 Q1
tags:
    - Greedy
    - Math
    - String
---

<!-- problem:start -->

# [1903. Largest Odd Number in String](https://leetcode.com/problems/largest-odd-number-in-string)

[中文文档](/solution/1900-1999/1903.Largest%20Odd%20Number%20in%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>num</code>, representing a large integer. Return <em>the <strong>largest-valued odd</strong> integer (as a string) that is a <strong>non-empty substring</strong> of </em><code>num</code><em>, or an empty string </em><code>&quot;&quot;</code><em> if no odd integer exists</em>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;52&quot;
<strong>Output:</strong> &quot;5&quot;
<strong>Explanation:</strong> The only non-empty substrings are &quot;5&quot;, &quot;2&quot;, and &quot;52&quot;. &quot;5&quot; is the only odd number.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;4206&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are no odd numbers in &quot;4206&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Traversal

<!-- thinking:start -->

> **Thinking**
>
> The largest odd number as a substring must be a prefix of $num$ that ends on an odd digit. Converting every prefix to an integer is unnecessary and $n\le 10^5$ forbids heavy arithmetic.
>
> A right-to-left scan finds the first odd digit; the prefix ending there is both odd and longest, hence largest.
>
> If no odd digit exists the answer is empty. One pass and constant extra space suffice.

<!-- thinking:end -->

We can traverse the string from the end to the beginning, find the first odd number, and then return the substring from the beginning to this odd number. If there is no odd number, return an empty string.

The time complexity is $O(n)$, where $n$ is the length of the string $num$. Ignoring the space consumption of the answer string, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestOddNumber(self, num: str) -> str:
        for i in range(len(num) - 1, -1, -1):
            if (int(num[i]) & 1) == 1:
                return num[: i + 1]
        return ''
```

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

```ts
function largestOddNumber(num: string): string {
    for (let i = num.length - 1; ~i; --i) {
        if (Number(num[i]) & 1) {
            return num.slice(0, i + 1);
        }
    }
    return '';
}
```

#### JavaScript

```js
/**
 * @param {string} num
 * @return {string}
 */
var largestOddNumber = function (num) {
    for (let i = num.length - 1; ~i; --i) {
        if (Number(num[i]) & 1) {
            return num.slice(0, i + 1);
        }
    }
    return '';
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
