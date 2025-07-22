---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1009.Complement%20of%20Base%2010%20Integer/README_EN.md
rating: 1234
source: Weekly Contest 128 Q1
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [1009. Complement of Base 10 Integer](https://leetcode.com/problems/complement-of-base-10-integer)

[中文文档](/solution/1000-1099/1009.Complement%20of%20Base%2010%20Integer/README.md)

## Description

<!-- description:start -->

<p>The <strong>complement</strong> of an integer is the integer you get when you flip all the <code>0</code>&#39;s to <code>1</code>&#39;s and all the <code>1</code>&#39;s to <code>0</code>&#39;s in its binary representation.</p>

<ul>
	<li>For example, The integer <code>5</code> is <code>&quot;101&quot;</code> in binary and its <strong>complement</strong> is <code>&quot;010&quot;</code> which is the integer <code>2</code>.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>its complement</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> 5 is &quot;101&quot; in binary, with complement &quot;010&quot; in binary, which is 2 in base-10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> 0
<strong>Explanation:</strong> 7 is &quot;111&quot; in binary, with complement &quot;000&quot; in binary, which is 0 in base-10.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 5
<strong>Explanation:</strong> 10 is &quot;1010&quot; in binary, with complement &quot;0101&quot; in binary, which is 5 in base-10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt; 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as 476: <a href="https://leetcode.com/problems/number-complement/" target="_blank">https://leetcode.com/problems/number-complement/</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

First, we check if $n$ is $0$. If it is, we return $1$.

Next, we define two variables $\textit{ans}$ and $i$, both initialized to $0$. Then we iterate through $n$. In each iteration, we set the $i$-th bit of $\textit{ans}$ to the inverse of the $i$-th bit of $n$, increment $i$ by $1$, and right shift $n$ by $1$.

Finally, we return $\textit{ans}$.

The time complexity is $O(\log n)$, where $n$ is the given decimal number. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bitwiseComplement(self, n: int) -> int:
        if n == 0:
            return 1
        ans = i = 0
        while n:
            ans |= ((n & 1 ^ 1) << i)
            i += 1
            n >>= 1
        return ans
```

#### Java

```java
class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0, i = 0;
        while (n != 0) {
            ans |= (n & 1 ^ 1) << (i++);
            n >>= 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0, i = 0;
        while (n != 0) {
            ans |= (n & 1 ^ 1) << (i++);
            n >>= 1;
        }
        return ans;
    }
};
```

#### Go

```go
func bitwiseComplement(n int) (ans int) {
	if n == 0 {
		return 1
	}
	for i := 0; n != 0; n >>= 1 {
		ans |= (n&1 ^ 1) << i
		i++
	}
	return
}
```

#### TypeScript

```ts
function bitwiseComplement(n: number): number {
    if (n === 0) {
        return 1;
    }
    let ans = 0;
    for (let i = 0; n; n >>= 1) {
        ans |= ((n & 1) ^ 1) << i++;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn bitwise_complement(mut n: i32) -> i32 {
        if n == 0 {
            return 1;
        }
        let mut ans = 0;
        let mut i = 0;
        while n != 0 {
            ans |= ((n & 1) ^ 1) << i;
            n >>= 1;
            i += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
