# [634. Find the Derangement of An Array 🔒](https://leetcode.com/problems/find-the-derangement-of-an-array)

[中文文档](/solution/0600-0699/0634.Find%20the%20Derangement%20of%20An%20Array/README.md)

<!-- tags:Math,Dynamic Programming -->

## Description

<p>In combinatorial mathematics, a <strong>derangement</strong> is a permutation of the elements of a set, such that no element appears in its original position.</p>

<p>You are given an integer <code>n</code>. There is originally an array consisting of <code>n</code> integers from <code>1</code> to <code>n</code> in ascending order, return <em>the number of <strong>derangements</strong> it can generate</em>. Since the answer may be huge, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i]$ as the number of derangement of an array of length $i$. Initially, $f[0] = 1$, $f[1] = 0$. The answer is $f[n]$.

For an array of length $i$, we consider where to place the number $1$. Suppose it is placed in the $j$-th position, where there are $i-1$ choices. Then, the number $j$ has two choices:

-   Placed in the first position, then the remaining $i - 2$ positions have $f[i - 2]$ derangements, so there are a total of $(i - 1) \times f[i - 2]$ derangements;
-   Not placed in the first position, which is equivalent to the derangement of an array of length $i - 1$, so there are a total of $(i - 1) \times f[i - 1]$ derangements.

In summary, we have the following state transition equation:

$$
f[i] = (i - 1) \times (f[i - 1] + f[i - 2])
$$

The final answer is $f[n]$. Note the modulo operation in the answer.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findDerangement(self, n: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * n
        for i in range(2, n + 1):
            f[i] = (i - 1) * (f[i - 1] + f[i - 2]) % mod
        return f[n]
```

```java
class Solution {
    public int findDerangement(int n) {
        long[] f = new long[n + 1];
        f[0] = 1;
        final int mod = (int) 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            f[i] = (i - 1) * (f[i - 1] + f[i - 2]) % mod;
        }
        return (int) f[n];
    }
}
```

```cpp
class Solution {
public:
    int findDerangement(int n) {
        long long f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        const int mod = 1e9 + 7;
        for (int i = 2; i <= n; i++) {
            f[i] = (i - 1LL) * (f[i - 1] + f[i - 2]) % mod;
        }
        return f[n];
    }
};
```

```go
func findDerangement(n int) int {
	f := make([]int, n+1)
	f[0] = 1
	const mod = 1e9 + 7
	for i := 2; i <= n; i++ {
		f[i] = (i - 1) * (f[i-1] + f[i-2]) % mod
	}
	return f[n]
}
```

<!-- tabs:end -->

### Solution 2: Dynamic Programming (Space Optimization)

We notice that the state transition equation only relates to $f[i - 1]$ and $f[i - 2]$. Therefore, we can use two variables $a$ and $b$ to represent $f[i - 1]$ and $f[i - 2]$ respectively, thereby reducing the space complexity to $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findDerangement(self, n: int) -> int:
        mod = 10**9 + 7
        a, b = 1, 0
        for i in range(2, n + 1):
            a, b = b, ((i - 1) * (a + b)) % mod
        return b
```

```java
class Solution {
    public int findDerangement(int n) {
        final int mod = (int) 1e9 + 7;
        long a = 1, b = 0;
        for (int i = 2; i <= n; ++i) {
            long c = (i - 1) * (a + b) % mod;
            a = b;
            b = c;
        }
        return (int) b;
    }
}
```

```cpp
class Solution {
public:
    int findDerangement(int n) {
        long long a = 1, b = 0;
        const int mod = 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            long long c = (i - 1) * (a + b) % mod;
            a = b;
            b = c;
        }
        return b;
    }
};
```

```go
func findDerangement(n int) int {
	a, b := 1, 0
	const mod = 1e9 + 7
	for i := 2; i <= n; i++ {
		a, b = b, (i-1)*(a+b)%mod
	}
	return b
}
```

<!-- tabs:end -->

<!-- end -->
