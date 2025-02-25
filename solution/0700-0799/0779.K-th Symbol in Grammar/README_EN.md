---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0779.K-th%20Symbol%20in%20Grammar/README_EN.md
tags:
    - Bit Manipulation
    - Recursion
    - Math
---

<!-- problem:start -->

# [779. K-th Symbol in Grammar](https://leetcode.com/problems/k-th-symbol-in-grammar)

[中文文档](/solution/0700-0799/0779.K-th%20Symbol%20in%20Grammar/README.md)

## Description

<!-- description:start -->

<p>We build a table of <code>n</code> rows (<strong>1-indexed</strong>). We start by writing <code>0</code> in the <code>1<sup>st</sup></code> row. Now in every subsequent row, we look at the previous row and replace each occurrence of <code>0</code> with <code>01</code>, and each occurrence of <code>1</code> with <code>10</code>.</p>

<ul>
	<li>For example, for <code>n = 3</code>, the <code>1<sup>st</sup></code> row is <code>0</code>, the <code>2<sup>nd</sup></code> row is <code>01</code>, and the <code>3<sup>rd</sup></code> row is <code>0110</code>.</li>
</ul>

<p>Given two integer <code>n</code> and <code>k</code>, return the <code>k<sup>th</sup></code> (<strong>1-indexed</strong>) symbol in the <code>n<sup>th</sup></code> row of a table of <code>n</code> rows.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> row 1: <u>0</u>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
row 1: 0
row 2: <u>0</u>1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
row 1: 0
row 2: 0<u>1</u>
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>n - 1</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

Let's first observe the pattern of the first few rows:

```
n = 1: 0
n = 2: 0 1
n = 3: 0 1 1 0
n = 4: 0 1 1 0 1 0 0 1
n = 5: 0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
...
```

We can see that the first half of each row is exactly the same as the previous row, and the second half is the inversion of the previous row. Note that "inversion" here means changing $0$ to $1$ and $1$ to $0$.

If $k$ is in the first half, then the $k$-th character is the same as the $k$-th character of the previous row, so we can directly recurse with $kthGrammar(n - 1, k)$.

If $k$ is in the second half, then the $k$-th character is the inversion of the $(k - 2^{n - 2})$-th character of the previous row, i.e., $kthGrammar(n - 1, k - 2^{n - 2}) \oplus 1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthGrammar(self, n: int, k: int) -> int:
        if n == 1:
            return 0
        if k <= (1 << (n - 2)):
            return self.kthGrammar(n - 1, k)
        return self.kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1
```

#### Java

```java
class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        if (k <= (1 << (n - 2))) {
            return kthGrammar(n - 1, k);
        }
        return kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        if (k <= (1 << (n - 2))) return kthGrammar(n - 1, k);
        return kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1;
    }
};
```

#### Go

```go
func kthGrammar(n int, k int) int {
	if n == 1 {
		return 0
	}
	if k <= (1 << (n - 2)) {
		return kthGrammar(n-1, k)
	}
	return kthGrammar(n-1, k-(1<<(n-2))) ^ 1
}
```

#### TypeScript

```ts
function kthGrammar(n: number, k: number): number {
    if (n == 1) {
        return 0;
    }
    if (k <= 1 << (n - 2)) {
        return kthGrammar(n - 1, k);
    }
    return kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Bit Manipulation + Brain Teaser

In the problem, the index starts from $1$. We will change $k$ to $k-1$, converting the index to start from $0$. In the following discussion, all indices start from $0$.

Upon closer observation, the $i$-th character in a row generates two characters at positions $2i$ and $2i+1$ in the next row.

```
0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
```

If the $i$-th character is $0$, then the characters generated at positions $2i$ and $2i+1$ are $0$ and $1$, respectively. If the $i$-th character is $1$, the generated characters are $1$ and $0$.

```
0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
      ^     * *
```

```
0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
        ^       * *
```

We can see that the character at position $2i$ (even index) is always the same as the character at position $i$, while the character at position $2i+1$ (odd index) is the inversion of the character at position $i$. In other words, characters at odd indices are always the result of one inversion. If the number of inversions is even, the character remains unchanged; if the number of inversions is odd, it is equivalent to one inversion.

Therefore, we only need to check whether $k$ is odd. If it is, we accumulate one inversion. Then, we divide $k$ by $2$ and continue to check, accumulating the number of inversions until $k$ becomes $0$.

Finally, we determine whether the number of inversions is odd. If it is, the answer is $1$; otherwise, it is $0$.

The process of accumulating the number of inversions is essentially equivalent to counting the number of $1$s in the binary representation of $k$.

The time complexity is $O(\log k)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthGrammar(self, n: int, k: int) -> int:
        return (k - 1).bit_count() & 1
```

#### Java

```java
class Solution {
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k - 1) & 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kthGrammar(int n, int k) {
        return __builtin_popcount(k - 1) & 1;
    }
};
```

#### Go

```go
func kthGrammar(n int, k int) int {
	return bits.OnesCount(uint(k-1)) & 1
}
```

#### TypeScript

```ts
function kthGrammar(n: number, k: number): number {
    return bitCount(k - 1) & 1;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
