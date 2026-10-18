---
comments: true
difficulty: Easy
rating: 1134
source: Weekly Contest 523 Q1
---

<!-- problem:start -->

# [4078. Three Fibonacci Sum](https://leetcode.com/problems/three-fibonacci-sum)

[中文文档](/solution/4000-4099/4078.Three%20Fibonacci%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>The <strong>Fibonacci sequence</strong> starts with 0 and 1, and each subsequent number is the sum of the previous two numbers. Its first few terms are <code>0, 1, 1, 2, 3, 5, 8, 13, ...</code>.</p>

<p>Return <code>true</code> if <code>n</code> can be expressed as the sum of <strong>three consecutive terms</strong> in the Fibonacci sequence, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 16</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The three consecutive terms 3, 5, and 8 have a sum of <code>3 + 5 + 8 = 16</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>Although <code>1 + 2 + 5 = 8</code>, these terms are not consecutive in the Fibonacci sequence. No three consecutive terms have a sum of 8.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The first three terms have a sum of <code>0 + 1 + 1 = 2</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Fibonacci Membership

<!-- thinking:start -->

> **Thinking**
>
> $n$ can be as large as $10^9$, so counting up from $1$ to $n$ is not usable. Only a few dozen Fibonacci numbers stay within that bound, and checking every block of three consecutive terms is already enough.
>
> Those three terms are tied together more tightly than a plain sum. The recurrence gives $F_{i+2}=F_i+F_{i+1}$, so the three-term sum is exactly twice the last term.
>
> The question is then whether $n$ is even and whether $n/2$ is a Fibonacci number. Those numbers can be stored in a hash set in advance, and each query is one membership test.

<!-- thinking:end -->

The Fibonacci sequence is defined by $F_0=0$, $F_1=1$, and $F_{i+2}=F_i+F_{i+1}$. The sum of three consecutive terms is

$$
F_i+F_{i+1}+F_{i+2}=2F_{i+2}.
$$

For $i\ge 0$, $F_{i+2}$ runs through every Fibonacci number that is at least $1$. Generate every Fibonacci number up to $10^9$ in advance and store it in a hash set $f$. Return $\text{true}$ when $n$ is even and $n/2\in f$, and return $\text{false}$ otherwise.

Preprocessing takes $O(\log M)$ time and $O(\log M)$ space, and each query takes $O(1)$ time, where $M=10^9$.

<!-- tabs:start -->

#### Python3

```python
a, b = 0, 1
mx = 10**9 + 1
f = set()
while b < mx:
    a, b = b, a + b
    f.add(b)


class Solution:
    def threeFibonacciSum(self, n: int) -> bool:
        return n % 2 == 0 and n // 2 in f
```

#### Java

```java
class Solution {
    static Set<Integer> f = new HashSet<>();

    static {
        int a = 0, b = 1;
        while (b < 1000000001) {
            int c = a + b;
            a = b;
            b = c;
            f.add(b);
        }
    }

    public boolean threeFibonacciSum(int n) {
        return n % 2 == 0 && f.contains(n / 2);
    }
}
```

#### C++

```cpp
class Solution {
    static unordered_set<int> f;

public:
    bool threeFibonacciSum(int n) {
        return n % 2 == 0 && f.count(n / 2);
    }
};

unordered_set<int> Solution::f = [] {
    unordered_set<int> f;
    int a = 0, b = 1;
    while (b < 1000000001) {
        int c = a + b;
        a = b;
        b = c;
        f.insert(b);
    }
    return f;
}();
```

#### Go

```go
var f = func() map[int]bool {
	f := map[int]bool{}
	a, b := 0, 1
	for b < 1000000001 {
		a, b = b, a+b
		f[b] = true
	}
	return f
}()

func threeFibonacciSum(n int) bool {
	return n%2 == 0 && f[n/2]
}
```

#### TypeScript

```ts
const f = new Set<number>();

for (let a = 0, b = 1; b < 1000000001;) {
    [a, b] = [b, a + b];
    f.add(b);
}

function threeFibonacciSum(n: number): boolean {
    return n % 2 === 0 && f.has(n / 2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
