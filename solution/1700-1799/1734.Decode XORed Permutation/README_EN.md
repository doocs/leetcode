---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1734.Decode%20XORed%20Permutation/README_EN.md
rating: 2024
tags:
    - Bit Manipulation
    - Array
---

# [1734. Decode XORed Permutation](https://leetcode.com/problems/decode-xored-permutation)

[中文文档](/solution/1700-1799/1734.Decode%20XORed%20Permutation/README.md)

## Description

<p>There is an integer array <code>perm</code> that is a permutation of the first <code>n</code> positive integers, where <code>n</code> is always <strong>odd</strong>.</p>

<p>It was encoded into another integer array <code>encoded</code> of length <code>n - 1</code>, such that <code>encoded[i] = perm[i] XOR perm[i + 1]</code>. For example, if <code>perm = [1,3,2]</code>, then <code>encoded = [2,1]</code>.</p>

<p>Given the <code>encoded</code> array, return <em>the original array</em> <code>perm</code>. It is guaranteed that the answer exists and is unique.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> encoded = [3,1]
<strong>Output:</strong> [1,2,3]
<strong>Explanation:</strong> If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> encoded = [6,5,4,6]
<strong>Output:</strong> [2,4,1,5,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;&nbsp;10<sup>5</sup></code></li>
	<li><code>n</code>&nbsp;is odd.</li>
	<li><code>encoded.length == n - 1</code></li>
</ul>

## Solutions

### Solution 1: Bitwise Operation

We notice that the array $perm$ is a permutation of the first $n$ positive integers, so the XOR of all elements in $perm$ is $1 \oplus 2 \oplus \cdots \oplus n$, denoted as $a$. And $encode[i]=perm[i] \oplus perm[i+1]$, if we denote the XOR of all elements $encode[0],encode[2],\cdots,encode[n-3]$ as $b$, then $perm[n-1]=a \oplus b$. Knowing the last element of $perm$, we can find all elements of $perm$ by traversing the array $encode$ in reverse order.

The time complexity is $O(n)$, where $n$ is the length of the array $perm$. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def decode(self, encoded: List[int]) -> List[int]:
        n = len(encoded) + 1
        a = b = 0
        for i in range(0, n - 1, 2):
            a ^= encoded[i]
        for i in range(1, n + 1):
            b ^= i
        perm = [0] * n
        perm[-1] = a ^ b
        for i in range(n - 2, -1, -1):
            perm[i] = encoded[i] ^ perm[i + 1]
        return perm
```

```java
class Solution {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i += 2) {
            a ^= encoded[i];
        }
        for (int i = 1; i <= n; ++i) {
            b ^= i;
        }
        int[] perm = new int[n];
        perm[n - 1] = a ^ b;
        for (int i = n - 2; i >= 0; --i) {
            perm[i] = encoded[i] ^ perm[i + 1];
        }
        return perm;
    }
}
```

```cpp
class Solution {
public:
    vector<int> decode(vector<int>& encoded) {
        int n = encoded.size() + 1;
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i += 2) {
            a ^= encoded[i];
        }
        for (int i = 1; i <= n; ++i) {
            b ^= i;
        }
        vector<int> perm(n);
        perm[n - 1] = a ^ b;
        for (int i = n - 2; ~i; --i) {
            perm[i] = encoded[i] ^ perm[i + 1];
        }
        return perm;
    }
};
```

```go
func decode(encoded []int) []int {
	n := len(encoded) + 1
	a, b := 0, 0
	for i := 0; i < n-1; i += 2 {
		a ^= encoded[i]
	}
	for i := 1; i <= n; i++ {
		b ^= i
	}
	perm := make([]int, n)
	perm[n-1] = a ^ b
	for i := n - 2; i >= 0; i-- {
		perm[i] = encoded[i] ^ perm[i+1]
	}
	return perm
}
```

<!-- tabs:end -->

<!-- end -->
