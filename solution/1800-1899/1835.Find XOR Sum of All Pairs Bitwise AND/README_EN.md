---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1835.Find%20XOR%20Sum%20of%20All%20Pairs%20Bitwise%20AND/README_EN.md
rating: 1825
source: Weekly Contest 237 Q4
tags:
    - Bit Manipulation
    - Array
    - Math
---

<!-- problem:start -->

# [1835. Find XOR Sum of All Pairs Bitwise AND](https://leetcode.com/problems/find-xor-sum-of-all-pairs-bitwise-and)

[中文文档](/solution/1800-1899/1835.Find%20XOR%20Sum%20of%20All%20Pairs%20Bitwise%20AND/README.md)

## Description

<!-- description:start -->

<p>The <strong>XOR sum</strong> of a list is the bitwise <code>XOR</code> of all its elements. If the list only contains one element, then its <strong>XOR sum</strong> will be equal to this element.</p>

<ul>
	<li>For example, the <strong>XOR sum</strong> of <code>[1,2,3,4]</code> is equal to <code>1 XOR 2 XOR 3 XOR 4 = 4</code>, and the <strong>XOR sum</strong> of <code>[3]</code> is equal to <code>3</code>.</li>
</ul>

<p>You are given two <strong>0-indexed</strong> arrays <code>arr1</code> and <code>arr2</code> that consist only of non-negative integers.</p>

<p>Consider the list containing the result of <code>arr1[i] AND arr2[j]</code> (bitwise <code>AND</code>) for every <code>(i, j)</code> pair where <code>0 &lt;= i &lt; arr1.length</code> and <code>0 &lt;= j &lt; arr2.length</code>.</p>

<p>Return <em>the <strong>XOR sum</strong> of the aforementioned list</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,2,3], arr2 = [6,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The list = [1 AND 6, 1 AND 5, 2 AND 6, 2 AND 5, 3 AND 6, 3 AND 5] = [0,1,2,0,2,1].
The XOR sum = 0 XOR 1 XOR 2 XOR 0 XOR 2 XOR 1 = 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [12], arr2 = [4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The list = [12 AND 4] = [4]. The XOR sum = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr1[i], arr2[j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bitwise Operation

Assume that the elements of array $arr1$ are $a_1, a_2, ..., a_n$, and the elements of array $arr2$ are $b_1, b_2, ..., b_m$. Then, the answer to the problem is:

$$
\begin{aligned}
\textit{ans} &= (a_1 \wedge b_1) \oplus (a_1 \wedge b_2) ... (a_1 \wedge b_m) \\
&\quad \oplus (a_2 \wedge b_1) \oplus (a_2 \wedge b_2) ... (a_2 \wedge b_m) \\
&\quad \oplus \cdots \\
&\quad \oplus (a_n \wedge b_1) \oplus (a_n \wedge b_2) ... (a_n \wedge b_m) \\
\end{aligned}
$$

Since in Boolean algebra, the XOR operation is addition without carry, and the AND operation is multiplication, the above formula can be simplified as:

$$
\textit{ans} = (a_1 \oplus a_2 \oplus \cdots \oplus a_n) \wedge (b_1 \oplus b_2 \oplus \cdots \oplus b_m)
$$

That is, the bitwise AND of the XOR sum of array $arr1$ and the XOR sum of array $arr2$.

The time complexity is $O(n + m)$, where $n$ and $m$ are the lengths of arrays $arr1$ and $arr2$, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getXORSum(self, arr1: List[int], arr2: List[int]) -> int:
        a = reduce(xor, arr1)
        b = reduce(xor, arr2)
        return a & b
```

#### Java

```java
class Solution {
    public int getXORSum(int[] arr1, int[] arr2) {
        int a = 0, b = 0;
        for (int v : arr1) {
            a ^= v;
        }
        for (int v : arr2) {
            b ^= v;
        }
        return a & b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getXORSum(vector<int>& arr1, vector<int>& arr2) {
        int a = accumulate(arr1.begin(), arr1.end(), 0, bit_xor<int>());
        int b = accumulate(arr2.begin(), arr2.end(), 0, bit_xor<int>());
        return a & b;
    }
};
```

#### Go

```go
func getXORSum(arr1 []int, arr2 []int) int {
	var a, b int
	for _, v := range arr1 {
		a ^= v
	}
	for _, v := range arr2 {
		b ^= v
	}
	return a & b
}
```

#### TypeScript

```ts
function getXORSum(arr1: number[], arr2: number[]): number {
    const a = arr1.reduce((acc, x) => acc ^ x);
    const b = arr2.reduce((acc, x) => acc ^ x);
    return a & b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
