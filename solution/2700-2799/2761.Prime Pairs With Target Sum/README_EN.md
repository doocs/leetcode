---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2761.Prime%20Pairs%20With%20Target%20Sum/README_EN.md
rating: 1504
source: Weekly Contest 352 Q2
tags:
    - Array
    - Math
    - Enumeration
    - Number Theory
---

<!-- problem:start -->

# [2761. Prime Pairs With Target Sum](https://leetcode.com/problems/prime-pairs-with-target-sum)

[中文文档](/solution/2700-2799/2761.Prime%20Pairs%20With%20Target%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>. We say that two integers <code>x</code> and <code>y</code> form a prime number pair if:</p>

<ul>
	<li><code>1 &lt;= x &lt;= y &lt;= n</code></li>
	<li><code>x + y == n</code></li>
	<li><code>x</code> and <code>y</code> are prime numbers</li>
</ul>

<p>Return <em>the 2D sorted list of prime number pairs</em> <code>[x<sub>i</sub>, y<sub>i</sub>]</code>. The list should be sorted in <strong>increasing</strong> order of <code>x<sub>i</sub></code>. If there are no prime number pairs at all, return <em>an empty array</em>.</p>

<p><strong>Note:</strong> A prime number is a natural number greater than <code>1</code> with only two factors, itself and <code>1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> [[3,7],[5,5]]
<strong>Explanation:</strong> In this example, there are two prime pairs that satisfy the criteria. 
These pairs are [3,7] and [5,5], and we return them in the sorted order as described in the problem statement.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> []
<strong>Explanation:</strong> We can show that there is no prime number pair that gives a sum of 2, so we return an empty array. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Preprocessing + Enumeration

First, we pre-process all the prime numbers within the range of $n$, and record them in the array $primes$, where $primes[i]$ is `true` if $i$ is a prime number.

Next, we enumerate $x$ in the range of $[2, \frac{n}{2}]$. In this case, $y = n - x$. If both $primes[x]$ and $primes[y]$ are `true`, then $(x, y)$ is a pair of prime numbers, which is added to the answer.

After the enumeration is complete, we return the answer.

The time complexity is $O(n \log \log n)$ and the space complexity is $O(n)$, where $n$ is the number given in the problem.

<!-- tabs:start -->

```python
class Solution:
    def findPrimePairs(self, n: int) -> List[List[int]]:
        primes = [True] * n
        for i in range(2, n):
            if primes[i]:
                for j in range(i + i, n, i):
                    primes[j] = False
        ans = []
        for x in range(2, n // 2 + 1):
            y = n - x
            if primes[x] and primes[y]:
                ans.append([x, y])
        return ans
```

```java
class Solution {
    public List<List<Integer>> findPrimePairs(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                for (int j = i + i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int x = 2; x <= n / 2; ++x) {
            int y = n - x;
            if (primes[x] && primes[y]) {
                ans.add(List.of(x, y));
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> findPrimePairs(int n) {
        bool primes[n];
        memset(primes, true, sizeof(primes));
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                for (int j = i + i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        vector<vector<int>> ans;
        for (int x = 2; x <= n / 2; ++x) {
            int y = n - x;
            if (primes[x] && primes[y]) {
                ans.push_back({x, y});
            }
        }
        return ans;
    }
};
```

```go
func findPrimePairs(n int) (ans [][]int) {
	primes := make([]bool, n)
	for i := range primes {
		primes[i] = true
	}
	for i := 2; i < n; i++ {
		if primes[i] {
			for j := i + i; j < n; j += i {
				primes[j] = false
			}
		}
	}
	for x := 2; x <= n/2; x++ {
		y := n - x
		if primes[x] && primes[y] {
			ans = append(ans, []int{x, y})
		}
	}
	return
}
```

```ts
function findPrimePairs(n: number): number[][] {
    const primes: boolean[] = new Array(n).fill(true);
    for (let i = 2; i < n; ++i) {
        if (primes[i]) {
            for (let j = i + i; j < n; j += i) {
                primes[j] = false;
            }
        }
    }
    const ans: number[][] = [];
    for (let x = 2; x <= n / 2; ++x) {
        const y = n - x;
        if (primes[x] && primes[y]) {
            ans.push([x, y]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
