# [1006. Clumsy Factorial](https://leetcode.com/problems/clumsy-factorial)

[中文文档](/solution/1000-1099/1006.Clumsy%20Factorial/README.md)

## Description

<p>The <strong>factorial</strong> of a positive integer <code>n</code> is the product of all positive integers less than or equal to <code>n</code>.</p>

<ul>
	<li>For example, <code>factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1</code>.</li>
</ul>

<p>We make a <strong>clumsy factorial</strong> using the integers in decreasing order by swapping out the multiply operations for a fixed rotation of operations with multiply <code>&#39;*&#39;</code>, divide <code>&#39;/&#39;</code>, add <code>&#39;+&#39;</code>, and subtract <code>&#39;-&#39;</code> in this order.</p>

<ul>
	<li>For example, <code>clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1</code>.</li>
</ul>

<p>However, these operations are still applied using the usual order of operations of arithmetic. We do all multiplication and division steps before any addition or subtraction steps, and multiplication and division steps are processed left to right.</p>

<p>Additionally, the division that we use is floor division such that <code>10 * 9 / 8 = 90 / 8 = 11</code>.</p>

<p>Given an integer <code>n</code>, return <em>the clumsy factorial of </em><code>n</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 7
<strong>Explanation:</strong> 7 = 4 * 3 / 2 + 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 12
<strong>Explanation:</strong> 12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def clumsy(self, N: int) -> int:
        op = 0
        s = [N]
        for i in range(N - 1, 0, -1):
            if op == 0:
                s.append(s.pop() * i)
            elif op == 1:
                s.append(int(s.pop() / i))
            elif op == 2:
                s.append(i)
            else:
                s.append(-i)
            op = (op + 1) % 4
        return sum(s)
```

### **Java**

```java
class Solution {
    public int clumsy(int N) {
        Deque<Integer> s = new ArrayDeque<>();
        s.offerLast(N);
        int op = 0;
        for (int i = N - 1; i > 0; --i) {
            if (op == 0) {
                s.offerLast(s.pollLast() * i);
            } else if (op == 1) {
                s.offerLast(s.pollLast() / i);
            } else if (op == 2) {
                s.offerLast(i);
            } else {
                s.offerLast(-i);
            }
            op = (op + 1) % 4;
        }
        int res = 0;
        while (!s.isEmpty()) {
            res += s.pollLast();
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
