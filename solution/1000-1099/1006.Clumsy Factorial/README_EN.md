---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1006.Clumsy%20Factorial/README_EN.md
rating: 1407
source: Weekly Contest 127 Q2
tags:
    - Stack
    - Math
    - Simulation
---

<!-- problem:start -->

# [1006. Clumsy Factorial](https://leetcode.com/problems/clumsy-factorial)

[中文文档](/solution/1000-1099/1006.Clumsy%20Factorial/README.md)

## Description

<!-- description:start -->

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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 7
<strong>Explanation:</strong> 7 = 4 * 3 / 2 + 1
</pre>

<p><strong class="example">Example 2:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack + Simulation

The calculation process of clumsy factorial can be seen as a simulation of a stack.

We define a stack `stk`, initially we push $n$ into the stack, and define a variable $k$ to represent the current operator, initially $k = 0$.

Then we start from $n-1$, enumerate $x$, and decide how to handle $x$ based on the current value of $k$:

- When $k = 0$, it represents a multiplication operation, we pop the top element of the stack, multiply it by $x$, and then push it back into the stack;
- When $k = 1$, it represents a division operation, we pop the top element of the stack, divide it by $x$, take the integer part, and then push it back into the stack;
- When $k = 2$, it represents an addition operation, we directly push $x$ into the stack;
- When $k = 3$, it represents a subtraction operation, we push $-x$ into the stack.

Next, we update $k = (k + 1) \mod 4$.

Finally, the sum of the elements in the stack is the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the integer $N$ given in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def clumsy(self, n: int) -> int:
        k = 0
        stk = [n]
        for x in range(n - 1, 0, -1):
            if k == 0:
                stk.append(stk.pop() * x)
            elif k == 1:
                stk.append(int(stk.pop() / x))
            elif k == 2:
                stk.append(x)
            else:
                stk.append(-x)
            k = (k + 1) % 4
        return sum(stk)
```

#### Java

```java
class Solution {
    public int clumsy(int n) {
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(n);
        int k = 0;
        for (int x = n - 1; x > 0; --x) {
            if (k == 0) {
                stk.push(stk.pop() * x);
            } else if (k == 1) {
                stk.push(stk.pop() / x);
            } else if (k == 2) {
                stk.push(x);
            } else {
                stk.push(-x);
            }
            k = (k + 1) % 4;
        }
        return stk.stream().mapToInt(Integer::intValue).sum();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int clumsy(int n) {
        stack<int> stk;
        stk.push(n);
        int k = 0;
        for (int x = n - 1; x; --x) {
            if (k == 0) {
                stk.top() *= x;
            } else if (k == 1) {
                stk.top() /= x;
            } else if (k == 2) {
                stk.push(x);
            } else {
                stk.push(-x);
            }
            k = (k + 1) % 4;
        }
        int ans = 0;
        while (!stk.empty()) {
            ans += stk.top();
            stk.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func clumsy(n int) (ans int) {
	stk := []int{n}
	k := 0
	for x := n - 1; x > 0; x-- {
		switch k {
		case 0:
			stk[len(stk)-1] *= x
		case 1:
			stk[len(stk)-1] /= x
		case 2:
			stk = append(stk, x)
		case 3:
			stk = append(stk, -x)
		}
		k = (k + 1) % 4
	}
	for _, x := range stk {
		ans += x
	}
	return
}
```

#### TypeScript

```ts
function clumsy(n: number): number {
    const stk: number[] = [n];
    let k = 0;
    for (let x = n - 1; x; --x) {
        if (k === 0) {
            stk.push(stk.pop()! * x);
        } else if (k === 1) {
            stk.push((stk.pop()! / x) | 0);
        } else if (k === 2) {
            stk.push(x);
        } else {
            stk.push(-x);
        }
        k = (k + 1) % 4;
    }
    return stk.reduce((acc, cur) => acc + cur, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
