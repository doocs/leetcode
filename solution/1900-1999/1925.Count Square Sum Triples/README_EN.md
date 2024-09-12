---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1925.Count%20Square%20Sum%20Triples/README_EN.md
rating: 1323
source: Biweekly Contest 56 Q1
tags:
    - Math
    - Enumeration
---

<!-- problem:start -->

# [1925. Count Square Sum Triples](https://leetcode.com/problems/count-square-sum-triples)

[中文文档](/solution/1900-1999/1925.Count%20Square%20Sum%20Triples/README.md)

## Description

<!-- description:start -->

<p>A <strong>square triple</strong> <code>(a,b,c)</code> is a triple where <code>a</code>, <code>b</code>, and <code>c</code> are <strong>integers</strong> and <code>a<sup>2</sup> + b<sup>2</sup> = c<sup>2</sup></code>.</p>

<p>Given an integer <code>n</code>, return <em>the number of <strong>square triples</strong> such that </em><code>1 &lt;= a, b, c &lt;= n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 2
<strong>Explanation</strong>: The square triples are (3,4,5) and (4,3,5).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 4
<strong>Explanation</strong>: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 250</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We enumerate $a$ and $b$ in the range $[1, n)$, then calculate $c = \sqrt{a^2 + b^2}$. If $c$ is an integer and $c \leq n$, then we have found a Pythagorean triplet, and we increment the answer by one.

After the enumeration is complete, return the answer.

The time complexity is $O(n^2)$, where $n$ is the given integer. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countTriples(self, n: int) -> int:
        ans = 0
        for a in range(1, n):
            for b in range(1, n):
                x = a * a + b * b
                c = int(sqrt(x))
                if c <= n and c * c == x:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countTriples(int n) {
        int ans = 0;
        for (int a = 1; a < n; a++) {
            for (int b = 1; b < n; b++) {
                int x = a * a + b * b;
                int c = (int) Math.sqrt(x);
                if (c <= n && c * c == x) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countTriples(int n) {
        int ans = 0;
        for (int a = 1; a < n; ++a) {
            for (int b = 1; b < n; ++b) {
                int x = a * a + b * b;
                int c = static_cast<int>(sqrt(x));
                if (c <= n && c * c == x) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countTriples(n int) (ans int) {
	for a := 1; a < n; a++ {
		for b := 1; b < n; b++ {
			x := a*a + b*b
			c := int(math.Sqrt(float64(x)))
			if c <= n && c*c == x {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countTriples(n: number): number {
    let ans = 0;
    for (let a = 1; a < n; a++) {
        for (let b = 1; b < n; b++) {
            const x = a * a + b * b;
            const c = Math.floor(Math.sqrt(x));
            if (c <= n && c * c === x) {
                ans++;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
