---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0319.Bulb%20Switcher/README_EN.md
tags:
    - Brainteaser
    - Math
---

<!-- problem:start -->

# [319. Bulb Switcher](https://leetcode.com/problems/bulb-switcher)

[中文文档](/solution/0300-0399/0319.Bulb%20Switcher/README.md)

## Description

<p>There are <code>n</code> bulbs that are initially off. You first turn on all the bulbs, then&nbsp;you turn off every second bulb.</p>

<p>On the third round, you toggle every third bulb (turning on if it&#39;s off or turning off if it&#39;s on). For the <code>i<sup>th</sup></code> round, you toggle every <code>i</code> bulb. For the <code>n<sup>th</sup></code> round, you only toggle the last bulb.</p>

<p>Return <em>the number of bulbs that are on after <code>n</code> rounds</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0319.Bulb%20Switcher/images/bulb.jpg" style="width: 421px; height: 321px;" />
<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> At first, the three bulbs are [off, off, off].
After the first round, the three bulbs are [on, on, on].
After the second round, the three bulbs are [on, off, on].
After the third round, the three bulbs are [on, off, off]. 
So you should return 1 because there is only one bulb is on.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 0
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We can number the $n$ bulbs as $1, 2, 3, \cdots, n$. For the $i$-th bulb, it will be operated in the $d$-th round if and only if $d$ is a factor of $i$.

For a number $i$, the number of its factors is finite. If the number of factors is odd, the final state is on; otherwise, it is off.

Therefore, we only need to find the number of numbers from $1$ to $n$ with an odd number of factors.

For a number $i$, if it has a factor $d$, then it must have a factor $i/d$. Therefore, numbers with an odd number of factors must be perfect squares.

For example, the factors of the number $12$ are $1, 2, 3, 4, 6, 12$, and the number of factors is $6$, which is even. For the perfect square number $16$, the factors are $1, 2, 4, 8, 16$, and the number of factors is $5$, which is odd.

Therefore, we only need to find how many perfect squares there are from $1$ to $n$, which is $\lfloor \sqrt{n} \rfloor$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def bulbSwitch(self, n: int) -> int:
        return int(sqrt(n))
```

```java
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
```

```cpp
class Solution {
public:
    int bulbSwitch(int n) {
        return (int) sqrt(n);
    }
};
```

```go
func bulbSwitch(n int) int {
	return int(math.Sqrt(float64(n)))
}
```

```ts
function bulbSwitch(n: number): number {
    return Math.floor(Math.sqrt(n));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
