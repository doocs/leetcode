---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0470.Implement%20Rand10%28%29%20Using%20Rand7%28%29/README_EN.md
tags:
    - Math
    - Rejection Sampling
    - Probability and Statistics
    - Randomized
---

<!-- problem:start -->

# [470. Implement Rand10() Using Rand7()](https://leetcode.com/problems/implement-rand10-using-rand7)

[中文文档](/solution/0400-0499/0470.Implement%20Rand10%28%29%20Using%20Rand7%28%29/README.md)

## Description

<!-- description:start -->

<p>Given the <strong>API</strong> <code>rand7()</code> that generates a uniform random integer in the range <code>[1, 7]</code>, write a function <code>rand10()</code> that generates a uniform random integer in the range <code>[1, 10]</code>. You can only call the API <code>rand7()</code>, and you shouldn&#39;t call any other API. Please <strong>do not</strong> use a language&#39;s built-in random API.</p>

<p>Each test case will have one <strong>internal</strong> argument <code>n</code>, the number of times that your implemented function <code>rand10()</code> will be called while testing. Note that this is <strong>not an argument</strong> passed to <code>rand10()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 1
<strong>Output:</strong> [2]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 2
<strong>Output:</strong> [2,8]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> n = 3
<strong>Output:</strong> [3,8,10]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>What is the <a href="https://en.wikipedia.org/wiki/Expected_value" target="_blank">expected value</a> for the number of calls to <code>rand7()</code> function?</li>
	<li>Could you minimize the number of calls to <code>rand7()</code>?</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
# The rand7() API is already defined for you.
# def rand7():
# @return a random integer in the range 1 to 7


class Solution:
    def rand10(self):
        """
        :rtype: int
        """
        while 1:
            i = rand7() - 1
            j = rand7()
            x = i * 7 + j
            if x <= 40:
                return x % 10 + 1
```

#### Java

```java
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int i = rand7() - 1;
            int j = rand7();
            int x = i * 7 + j;
            if (x <= 40) {
                return x % 10 + 1;
            }
        }
    }
}
```

#### C++

```cpp
// The rand7() API is already defined for you.
// int rand7();
// @return a random integer in the range 1 to 7

class Solution {
public:
    int rand10() {
        while (1) {
            int i = rand7() - 1;
            int j = rand7();
            int x = i * 7 + j;
            if (x <= 40) {
                return x % 10 + 1;
            }
        }
    }
};
```

#### Go

```go
func rand10() int {
	for {
		i := rand7() - 1
		j := rand7()
		x := i*7 + j
		if x <= 40 {
			return x%10 + 1
		}
	}
}
```

#### TypeScript

```ts
/**
 * The rand7() API is already defined for you.
 * function rand7(): number {}
 * @return a random integer in the range 1 to 7
 */

function rand10(): number {
    while (true) {
        const i = rand7() - 1;
        const j = rand7();
        const x = i * 7 + j;
        if (x <= 40) {
            return (x % 10) + 1;
        }
    }
}
```

#### Rust

```rust
/**
 * The rand7() API is already defined for you.
 * @return a random integer in the range 1 to 7
 * fn rand7() -> i32;
 */

impl Solution {
    pub fn rand10() -> i32 {
        loop {
            let i = rand7() - 1;
            let j = rand7();
            let x = i * 7 + j;
            if x <= 40 {
                return (x % 10) + 1;
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
