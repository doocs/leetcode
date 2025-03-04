---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1881.Maximum%20Value%20after%20Insertion/README_EN.md
rating: 1381
source: Weekly Contest 243 Q2
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [1881. Maximum Value after Insertion](https://leetcode.com/problems/maximum-value-after-insertion)

[中文文档](/solution/1800-1899/1881.Maximum%20Value%20after%20Insertion/README.md)

## Description

<!-- description:start -->

<p>You are given a very large integer <code>n</code>, represented as a string,​​​​​​ and an integer digit <code>x</code>. The digits in <code>n</code> and the digit <code>x</code> are in the <strong>inclusive</strong> range <code>[1, 9]</code>, and <code>n</code> may represent a <b>negative</b> number.</p>

<p>You want to <strong>maximize </strong><code>n</code><strong>&#39;s numerical value</strong> by inserting <code>x</code> anywhere in the decimal representation of <code>n</code>​​​​​​. You <strong>cannot</strong> insert <code>x</code> to the left of the negative sign.</p>

<ul>
	<li>For example, if <code>n = 73</code> and <code>x = 6</code>, it would be best to insert it between <code>7</code> and <code>3</code>, making <code>n = 763</code>.</li>
	<li>If <code>n = -55</code> and <code>x = 2</code>, it would be best to insert it before the first <code>5</code>, making <code>n = -255</code>.</li>
</ul>

<p>Return <em>a string representing the <strong>maximum</strong> value of </em><code>n</code><em>​​​​​​ after the insertion</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;99&quot;, x = 9
<strong>Output:</strong> &quot;999&quot;
<strong>Explanation:</strong> The result is the same regardless of where you insert 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = &quot;-13&quot;, x = 2
<strong>Output:</strong> &quot;-123&quot;
<strong>Explanation:</strong> You can make n one of {-213, -123, -132}, and the largest of those three is -123.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x &lt;= 9</code></li>
	<li>The digits in <code>n</code>​​​ are in the range <code>[1, 9]</code>.</li>
	<li><code>n</code> is a valid representation of an integer.</li>
	<li>In the case of a negative <code>n</code>,​​​​​​ it will begin with <code>&#39;-&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

If $n$ is negative, we need to find the first position greater than $x$ and insert $x$ at that position. If $n$ is positive, we need to find the first position less than $x$ and insert $x$ at that position.

The time complexity is $O(m)$, where $m$ is the length of $n$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, n: str, x: int) -> str:
        i = 0
        if n[0] == "-":
            i += 1
            while i < len(n) and int(n[i]) <= x:
                i += 1
        else:
            while i < len(n) and int(n[i]) >= x:
                i += 1
        return n[:i] + str(x) + n[i:]
```

#### Java

```java
class Solution {
    public String maxValue(String n, int x) {
        int i = 0;
        if (n.charAt(0) == '-') {
            ++i;
            while (i < n.length() && n.charAt(i) - '0' <= x) {
                ++i;
            }
        } else {
            while (i < n.length() && n.charAt(i) - '0' >= x) {
                ++i;
            }
        }
        return n.substring(0, i) + x + n.substring(i);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string maxValue(string n, int x) {
        int i = 0;
        if (n[0] == '-') {
            ++i;
            while (i < n.size() && n[i] - '0' <= x) {
                ++i;
            }
        } else {
            while (i < n.size() && n[i] - '0' >= x) {
                ++i;
            }
        }
        n.insert(i, 1, x + '0');
        return n;
    }
};
```

#### Go

```go
func maxValue(n string, x int) string {
	i := 0
	y := byte('0' + x)
	if n[0] == '-' {
		i++
		for i < len(n) && n[i] <= y {
			i++
		}
	} else {
		for i < len(n) && n[i] >= y {
			i++
		}
	}
	return n[:i] + string(y) + n[i:]
}
```

#### TypeScript

```ts
function maxValue(n: string, x: number): string {
    let i = 0;
    if (n[0] === '-') {
        i++;
        while (i < n.length && +n[i] <= x) {
            i++;
        }
    } else {
        while (i < n.length && +n[i] >= x) {
            i++;
        }
    }
    return n.slice(0, i) + x + n.slice(i);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_value(n: String, x: i32) -> String {
        let s = n.as_bytes();
        let mut i = 0;
        if n.starts_with('-') {
            i += 1;
            while i < s.len() && (s[i] - b'0') as i32 <= x {
                i += 1;
            }
        } else {
            while i < s.len() && (s[i] - b'0') as i32 >= x {
                i += 1;
            }
        }
        let mut ans = String::new();
        ans.push_str(&n[0..i]);
        ans.push_str(&x.to_string());
        ans.push_str(&n[i..]);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} n
 * @param {number} x
 * @return {string}
 */
var maxValue = function (n, x) {
    let i = 0;
    if (n[0] === '-') {
        i++;
        while (i < n.length && +n[i] <= x) {
            i++;
        }
    } else {
        while (i < n.length && +n[i] >= x) {
            i++;
        }
    }
    return n.slice(0, i) + x + n.slice(i);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
