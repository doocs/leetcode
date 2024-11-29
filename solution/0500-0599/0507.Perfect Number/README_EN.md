---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0507.Perfect%20Number/README_EN.md
tags:
    - Math
---

<!-- problem:start -->

# [507. Perfect Number](https://leetcode.com/problems/perfect-number)

[中文文档](/solution/0500-0599/0507.Perfect%20Number/README.md)

## Description

<!-- description:start -->

<p>A <a href="https://en.wikipedia.org/wiki/Perfect_number" target="_blank"><strong>perfect number</strong></a> is a <strong>positive integer</strong> that is equal to the sum of its <strong>positive divisors</strong>, excluding the number itself. A <strong>divisor</strong> of an integer <code>x</code> is an integer that can divide <code>x</code> evenly.</p>

<p>Given an integer <code>n</code>, return <code>true</code><em> if </em><code>n</code><em> is a perfect number, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 28
<strong>Output:</strong> true
<strong>Explanation:</strong> 28 = 1 + 2 + 4 + 7 + 14
1, 2, 4, 7, and 14 are all divisors of 28.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 7
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

First, we check if $\textit{num}$ is 1. If it is, then $\textit{num}$ is not a perfect number, and we return $\text{false}$.

Next, we enumerate all positive divisors of $\textit{num}$ starting from 2. If $\textit{num}$ is divisible by a positive divisor $i$, we add $i$ to the sum $\textit{s}$. If the quotient of $\textit{num}$ divided by $i$ is not equal to $i$, we also add the quotient to the sum $\textit{s}$.

Finally, we check if $\textit{s}$ is equal to $\textit{num}$.

The time complexity is $O(\sqrt{n})$, where $n$ is the value of $\textit{num}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkPerfectNumber(self, num: int) -> bool:
        if num == 1:
            return False
        s, i = 1, 2
        while i <= num // i:
            if num % i == 0:
                s += i
                if i != num // i:
                    s += num // i
            i += 1
        return s == num
```

#### Java

```java
class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int s = 1;
        for (int i = 2; i <= num / i; ++i) {
            if (num % i == 0) {
                s += i;
                if (i != num / i) {
                    s += num / i;
                }
            }
        }
        return s == num;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int s = 1;
        for (int i = 2; i <= num / i; ++i) {
            if (num % i == 0) {
                s += i;
                if (i != num / i) {
                    s += num / i;
                }
            }
        }
        return s == num;
    }
};
```

#### Go

```go
func checkPerfectNumber(num int) bool {
	if num == 1 {
		return false
	}
	s := 1
	for i := 2; i <= num/i; i++ {
		if num%i == 0 {
			s += i
			if j := num / i; i != j {
				s += j
			}
		}
	}
	return s == num
}
```

#### TypeScript

```ts
function checkPerfectNumber(num: number): boolean {
    if (num <= 1) {
        return false;
    }
    let s = 1;
    for (let i = 2; i <= num / i; ++i) {
        if (num % i === 0) {
            s += i;
            if (i * i !== num) {
                s += num / i;
            }
        }
    }
    return s === num;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
