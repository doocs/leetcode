---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1414.Find%20the%20Minimum%20Number%20of%20Fibonacci%20Numbers%20Whose%20Sum%20Is%20K/README_EN.md
rating: 1465
source: Biweekly Contest 24 Q2
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [1414. Find the Minimum Number of Fibonacci Numbers Whose Sum Is K](https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k)

[中文文档](/solution/1400-1499/1414.Find%20the%20Minimum%20Number%20of%20Fibonacci%20Numbers%20Whose%20Sum%20Is%20K/README.md)

## Description

<!-- description:start -->

<p>Given an integer&nbsp;<code>k</code>, <em>return the minimum number of Fibonacci numbers whose sum is equal to </em><code>k</code>. The same Fibonacci number can be used multiple times.</p>

<p>The Fibonacci numbers are defined as:</p>

<ul>
	<li><code>F<sub>1</sub> = 1</code></li>
	<li><code>F<sub>2</sub> = 1</code></li>
	<li><code>F<sub>n</sub> = F<sub>n-1</sub> + F<sub>n-2</sub></code> for <code>n &gt; 2.</code></li>
</ul>
It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to <code>k</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 7
<strong>Output:</strong> 2 
<strong>Explanation:</strong> The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ... 
For k = 7 we can use 2 + 5 = 7.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 10
<strong>Output:</strong> 2 
<strong>Explanation:</strong> For k = 10 we can use 2 + 8 = 10.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 19
<strong>Output:</strong> 3 
<strong>Explanation:</strong> For k = 19 we can use 1 + 5 + 13 = 19.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can greedily select the largest Fibonacci number that does not exceed $k$ each time, then subtract this number from $k$ and increment the answer by one. This process is repeated until $k = 0$.

Since we greedily select the largest Fibonacci number that does not exceed $k$ each time, suppose this number is $b$, the previous number is $a$, and the next number is $c$. Subtracting $b$ from $k$ results in a value that is less than $a$, which means that after selecting $b$, we will not select $a$. This is because if we could select $a$, then we could have greedily selected the next Fibonacci number $c$ instead of $b$ earlier, which contradicts our assumption. Therefore, after selecting $b$, we can greedily reduce the Fibonacci number.

The time complexity is $O(\log k)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMinFibonacciNumbers(self, k: int) -> int:
        a = b = 1
        while b <= k:
            a, b = b, a + b
        ans = 0
        while k:
            if k >= b:
                k -= b
                ans += 1
            a, b = b - a, a
        return ans
```

#### Java

```java
class Solution {
    public int findMinFibonacciNumbers(int k) {
        int a = 1, b = 1;
        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
        }
        int ans = 0;
        while (k > 0) {
            if (k >= b) {
                k -= b;
                ++ans;
            }
            int c = b - a;
            b = a;
            a = c;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMinFibonacciNumbers(int k) {
        int a = 1, b = 1;
        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
        }
        int ans = 0;
        while (k > 0) {
            if (k >= b) {
                k -= b;
                ++ans;
            }
            int c = b - a;
            b = a;
            a = c;
        }
        return ans;
    }
};
```

#### Go

```go
func findMinFibonacciNumbers(k int) (ans int) {
	a, b := 1, 1
	for b <= k {
		c := a + b
		a = b
		b = c
	}

	for k > 0 {
		if k >= b {
			k -= b
			ans++
		}
		c := b - a
		b = a
		a = c
	}
	return
}
```

#### TypeScript

```ts
function findMinFibonacciNumbers(k: number): number {
    let [a, b] = [1, 1];
    while (b <= k) {
        let c = a + b;
        a = b;
        b = c;
    }

    let ans = 0;
    while (k > 0) {
        if (k >= b) {
            k -= b;
            ans++;
        }
        let c = b - a;
        b = a;
        a = c;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_min_fibonacci_numbers(mut k: i32) -> i32 {
        let mut a = 1;
        let mut b = 1;
        while b <= k {
            let c = a + b;
            a = b;
            b = c;
        }

        let mut ans = 0;
        while k > 0 {
            if k >= b {
                k -= b;
                ans += 1;
            }
            let c = b - a;
            b = a;
            a = c;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
