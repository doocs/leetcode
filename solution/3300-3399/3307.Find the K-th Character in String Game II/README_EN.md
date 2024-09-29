---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3307.Find%20the%20K-th%20Character%20in%20String%20Game%20II/README_EN.md
---

<!-- problem:start -->

# [3307. Find the K-th Character in String Game II](https://leetcode.com/problems/find-the-k-th-character-in-string-game-ii)

[中文文档](/solution/3300-3399/3307.Find%20the%20K-th%20Character%20in%20String%20Game%20II/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are playing a game. Initially, Alice has a string <code>word = &quot;a&quot;</code>.</p>

<p>You are given a <strong>positive</strong> integer <code>k</code>. You are also given an integer array <code>operations</code>, where <code>operations[i]</code> represents the <strong>type</strong> of the <code>i<sup>th</sup></code> operation.</p>

<p>Now Bob will ask Alice to perform <strong>all</strong> operations in sequence:</p>

<ul>
	<li>If <code>operations[i] == 0</code>, <strong>append</strong> a copy of <code>word</code> to itself.</li>
	<li>If <code>operations[i] == 1</code>, generate a new string by <strong>changing</strong> each character in <code>word</code> to its <strong>next</strong> character in the English alphabet, and <strong>append</strong> it to the <em>original</em> <code>word</code>. For example, performing the operation on <code>&quot;c&quot;</code> generates <code>&quot;cd&quot;</code> and performing the operation on <code>&quot;zb&quot;</code> generates <code>&quot;zbac&quot;</code>.</li>
</ul>

<p>Return the value of the <code>k<sup>th</sup></code> character in <code>word</code> after performing all the operations.</p>

<p><strong>Note</strong> that the character <code>&#39;z&#39;</code> can be changed to <code>&#39;a&#39;</code> in the second type of operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 5, operations = [0,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;a&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>word == &quot;a&quot;</code>. Alice performs the three operations as follows:</p>

<ul>
	<li>Appends <code>&quot;a&quot;</code> to <code>&quot;a&quot;</code>, <code>word</code> becomes <code>&quot;aa&quot;</code>.</li>
	<li>Appends <code>&quot;aa&quot;</code> to <code>&quot;aa&quot;</code>, <code>word</code> becomes <code>&quot;aaaa&quot;</code>.</li>
	<li>Appends <code>&quot;aaaa&quot;</code> to <code>&quot;aaaa&quot;</code>, <code>word</code> becomes <code>&quot;aaaaaaaa&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 10, operations = [0,1,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;b&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>word == &quot;a&quot;</code>. Alice performs the four operations as follows:</p>

<ul>
	<li>Appends <code>&quot;a&quot;</code> to <code>&quot;a&quot;</code>, <code>word</code> becomes <code>&quot;aa&quot;</code>.</li>
	<li>Appends <code>&quot;bb&quot;</code> to <code>&quot;aa&quot;</code>, <code>word</code> becomes <code>&quot;aabb&quot;</code>.</li>
	<li>Appends <code>&quot;aabb&quot;</code> to <code>&quot;aabb&quot;</code>, <code>word</code> becomes <code>&quot;aabbaabb&quot;</code>.</li>
	<li>Appends <code>&quot;bbccbbcc&quot;</code> to <code>&quot;aabbaabb&quot;</code>, <code>word</code> becomes <code>&quot;aabbaabbbbccbbcc&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>14</sup></code></li>
	<li><code>1 &lt;= operations.length &lt;= 100</code></li>
	<li><code>operations[i]</code> is either 0 or 1.</li>
	<li>The input is generated such that <code>word</code> has <strong>at least</strong> <code>k</code> characters after all operations.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recurrence

Since the length of the string doubles after each operation, if we perform $i$ operations, the length of the string will be $2^i$.

We can simulate this process to find the first string length $n$ that is greater than or equal to $k$.

Next, we backtrack and discuss the following cases:

-   If $k \gt n / 2$, it means $k$ is in the second half. If $\textit{operations}[i - 1] = 1$, it means the character at position $k$ is obtained by adding $1$ to the character in the first half. We add $1$ to it. Then we update $k$ to $k - n / 2$.
-   If $k \le n / 2$, it means $k$ is in the first half and is not affected by $\textit{operations}[i - 1]$.
-   Next, we update $n$ to $n / 2$ and continue backtracking until $n = 1$.

Finally, we take the resulting number modulo $26$ and add the ASCII code of `'a'` to get the answer.

The time complexity is $O(\log k)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthCharacter(self, k: int, operations: List[int]) -> str:
        n, i = 1, 0
        while n < k:
            n *= 2
            i += 1
        d = 0
        while n > 1:
            if k > n // 2:
                k -= n // 2
                d += operations[i - 1]
            n //= 2
            i -= 1
        return chr(d % 26 + ord("a"))
```

#### Java

```java
class Solution {
    public char kthCharacter(long k, int[] operations) {
        long n = 1;
        int i = 0;
        while (n < k) {
            n *= 2;
            ++i;
        }
        int d = 0;
        while (n > 1) {
            if (k > n / 2) {
                k -= n / 2;
                d += operations[i - 1];
            }
            n /= 2;
            --i;
        }
        return (char) ('a' + (d % 26));
    }
}
```

#### C++

```cpp
class Solution {
public:
    char kthCharacter(long long k, vector<int>& operations) {
        long long n = 1;
        int i = 0;
        while (n < k) {
            n *= 2;
            ++i;
        }
        int d = 0;
        while (n > 1) {
            if (k > n / 2) {
                k -= n / 2;
                d += operations[i - 1];
            }
            n /= 2;
            --i;
        }
        return 'a' + (d % 26);
    }
};
```

#### Go

```go
func kthCharacter(k int64, operations []int) byte {
	n := int64(1)
	i := 0
	for n < k {
		n *= 2
		i++
	}
	d := 0
	for n > 1 {
		if k > n/2 {
			k -= n / 2
			d += operations[i-1]
		}
		n /= 2
		i--
	}
	return byte('a' + (d % 26))
}
```

#### TypeScript

```ts
function kthCharacter(k: number, operations: number[]): string {
    let n = 1;
    let i = 0;
    while (n < k) {
        n *= 2;
        i++;
    }
    let d = 0;
    while (n > 1) {
        if (k > n / 2) {
            k -= n / 2;
            d += operations[i - 1];
        }
        n /= 2;
        i--;
    }
    return String.fromCharCode('a'.charCodeAt(0) + (d % 26));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
