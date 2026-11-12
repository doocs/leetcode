---
comments: true
difficulty: Medium
rating: 1376
source: Weekly Contest 315 Q3
tags:
    - Math
    - Enumeration
---

<!-- problem:start -->

# [2443. Sum of Number and Its Reverse](https://leetcode.com/problems/sum-of-number-and-its-reverse)

[中文文档](/solution/2400-2499/2443.Sum%20of%20Number%20and%20Its%20Reverse/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>non-negative</strong> integer <code>num</code>, return <code>true</code><em> if </em><code>num</code><em> can be expressed as the sum of any <strong>non-negative</strong> integer and its reverse, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 443</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>172 + 271 = 443, where 271 is the reverse of 172. Therefore, we return true.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 63</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>63 cannot be expressed as the sum of a non-negative integer and its reverse. Therefore, we return false.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 181</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>140 + 041 = 181, where 041 represents 41, the reverse of 140. Reversed numbers may contain leading zeros. Therefore, we return true.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brute Force Enumeration

<!-- thinking:start -->

> **Thinking**
>
> With $\textit{num}\le 10^5$, try every $k\in[0,\textit{num}]$ and test $k+\textit{reverse}(k)=\textit{num}$. The range is small enough; reverse via the decimal string.

<!-- thinking:end -->

Enumerate $k$ in the range $[0,.., \textit{num}]$, and check whether $k + \textit{reverse}(k)$ equals $\textit{num}$.

The time complexity is $O(n \log n)$, where $n$ is the size of $\textit{num}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfNumberAndReverse(self, num: int) -> bool:
        return any(k + int(str(k)[::-1]) == num for k in range(num + 1))
```

#### Java

```java
class Solution {
    public boolean sumOfNumberAndReverse(int num) {
        for (int x = 0; x <= num; ++x) {
            int k = x;
            int y = 0;
            while (k > 0) {
                y = y * 10 + k % 10;
                k /= 10;
            }
            if (x + y == num) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool sumOfNumberAndReverse(int num) {
        for (int x = 0; x <= num; ++x) {
            int k = x;
            int y = 0;
            while (k > 0) {
                y = y * 10 + k % 10;
                k /= 10;
            }
            if (x + y == num) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func sumOfNumberAndReverse(num int) bool {
	for x := 0; x <= num; x++ {
		k, y := x, 0
		for k > 0 {
			y = y*10 + k%10
			k /= 10
		}
		if x+y == num {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function sumOfNumberAndReverse(num: number): boolean {
    for (let i = 0; i <= num; i++) {
        if (i + Number([...(i + '')].reverse().join('')) === num) {
            return true;
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn sum_of_number_and_reverse(num: i32) -> bool {
        for i in 0..=num {
            if i + ({
                let mut t = i;
                let mut j = 0;
                while t > 0 {
                    j = j * 10 + (t % 10);
                    t /= 10;
                }
                j
            }) == num
            {
                return true;
            }
        }
        false
    }
}
```

#### C

```c
bool sumOfNumberAndReverse(int num) {
    for (int i = 0; i <= num; i++) {
        int t = i;
        int j = 0;
        while (t > 0) {
            j = j * 10 + t % 10;
            t /= 10;
        }
        if (i + j == num) {
            return 1;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
