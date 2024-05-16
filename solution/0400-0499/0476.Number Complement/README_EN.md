---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0476.Number%20Complement/README_EN.md
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [476. Number Complement](https://leetcode.com/problems/number-complement)

[中文文档](/solution/0400-0499/0476.Number%20Complement/README.md)

## Description

<p>The <strong>complement</strong> of an integer is the integer you get when you flip all the <code>0</code>&#39;s to <code>1</code>&#39;s and all the <code>1</code>&#39;s to <code>0</code>&#39;s in its binary representation.</p>

<ul>
	<li>For example, The integer <code>5</code> is <code>&quot;101&quot;</code> in binary and its <strong>complement</strong> is <code>&quot;010&quot;</code> which is the integer <code>2</code>.</li>
</ul>

<p>Given an integer <code>num</code>, return <em>its complement</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt; 2<sup>31</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as 1009: <a href="https://leetcode.com/problems/complement-of-base-10-integer/" target="_blank">https://leetcode.com/problems/complement-of-base-10-integer/</a></p>

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

According to the problem description, we can use XOR operation to implement the flipping operation, the steps are as follows:

First, we find the highest bit of $1$ in the binary representation of $\text{num}$, and the position is denoted as $k$.

Then, we construct a binary number, where the $k$-th bit is $0$ and the rest of the lower bits are $1$, which is $2^k - 1$;

Finally, we perform XOR operation on $\text{num}$ and the constructed binary number to get the answer.

The time complexity is $O(\log \text{num})$, where $\text{num}$ is the input integer. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findComplement(self, num: int) -> int:
        return num ^ ((1 << num.bit_length()) - 1)
```

```java
class Solution {
    public int findComplement(int num) {
        return num ^ ((1 << (32 - Integer.numberOfLeadingZeros(num))) - 1);
    }
}
```

```cpp
class Solution {
public:
    int findComplement(int num) {
        return num ^ ((1LL << (64 - __builtin_clzll(num))) - 1);
    }
};
```

```go
func findComplement(num int) int {
	return num ^ ((1 << bits.Len(uint(num))) - 1)
}
```

```ts
function findComplement(num: number): number {
    return num ^ (2 ** num.toString(2).length - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
