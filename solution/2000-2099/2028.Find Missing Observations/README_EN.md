---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2028.Find%20Missing%20Observations/README_EN.md
rating: 1444
source: Weekly Contest 261 Q2
tags:
    - Array
    - Math
    - Simulation
---

<!-- problem:start -->

# [2028. Find Missing Observations](https://leetcode.com/problems/find-missing-observations)

[中文文档](/solution/2000-2099/2028.Find%20Missing%20Observations/README.md)

## Description

<!-- description:start -->

<p>You have observations of <code>n + m</code> <strong>6-sided</strong> dice rolls with each face numbered from <code>1</code> to <code>6</code>. <code>n</code> of the observations went missing, and you only have the observations of <code>m</code> rolls. Fortunately, you have also calculated the <strong>average value</strong> of the <code>n + m</code> rolls.</p>

<p>You are given an integer array <code>rolls</code> of length <code>m</code> where <code>rolls[i]</code> is the value of the <code>i<sup>th</sup></code> observation. You are also given the two integers <code>mean</code> and <code>n</code>.</p>

<p>Return <em>an array of length </em><code>n</code><em> containing the missing observations such that the <strong>average value </strong>of the </em><code>n + m</code><em> rolls is <strong>exactly</strong> </em><code>mean</code>. If there are multiple valid answers, return <em>any of them</em>. If no such array exists, return <em>an empty array</em>.</p>

<p>The <strong>average value</strong> of a set of <code>k</code> numbers is the sum of the numbers divided by <code>k</code>.</p>

<p>Note that <code>mean</code> is an integer, so the sum of the <code>n + m</code> rolls should be divisible by <code>n + m</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rolls = [3,2,4,3], mean = 4, n = 2
<strong>Output:</strong> [6,6]
<strong>Explanation:</strong> The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rolls = [1,5,6], mean = 3, n = 4
<strong>Output:</strong> [2,3,2,2]
<strong>Explanation:</strong> The mean of all n + m rolls is (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> rolls = [1,2,3,4], mean = 6, n = 4
<strong>Output:</strong> []
<strong>Explanation:</strong> It is impossible for the mean to be 6 no matter what the 4 missing rolls are.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == rolls.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rolls[i], mean &lt;= 6</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Construction

According to the problem description, the sum of all numbers is $(n + m) \times \textit{mean}$, and the sum of known numbers is $\sum_{i=0}^{m-1} \textit{rolls}[i]$. Therefore, the sum of the missing numbers is $s = (n + m) \times \textit{mean} - \sum_{i=0}^{m-1} \textit{rolls}[i]$.

If $s \gt n \times 6$ or $s \lt n$, it means there is no answer that satisfies the conditions, so we return an empty array.

Otherwise, we can evenly distribute $s$ to $n$ numbers, that is, the value of each number is $s / n$, and the value of $s \bmod n$ numbers is increased by $1$.

The time complexity is $O(n + m)$, where $n$ and $m$ are the number of missing numbers and known numbers, respectively. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingRolls(self, rolls: List[int], mean: int, n: int) -> List[int]:
        m = len(rolls)
        s = (n + m) * mean - sum(rolls)
        if s > n * 6 or s < n:
            return []
        ans = [s // n] * n
        for i in range(s % n):
            ans[i] += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int s = (n + m) * mean;
        for (int v : rolls) {
            s -= v;
        }
        if (s > n * 6 || s < n) {
            return new int[0];
        }
        int[] ans = new int[n];
        Arrays.fill(ans, s / n);
        for (int i = 0; i < s % n; ++i) {
            ++ans[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> missingRolls(vector<int>& rolls, int mean, int n) {
        int m = rolls.size();
        int s = (n + m) * mean - accumulate(rolls.begin(), rolls.end(), 0);
        if (s > n * 6 || s < n) {
            return {};
        }
        vector<int> ans(n, s / n);
        for (int i = 0; i < s % n; ++i) {
            ++ans[i];
        }
        return ans;
    }
};
```

#### Go

```go
func missingRolls(rolls []int, mean int, n int) []int {
	m := len(rolls)
	s := (n + m) * mean
	for _, v := range rolls {
		s -= v
	}
	if s > n*6 || s < n {
		return []int{}
	}
	ans := make([]int, n)
	for i, j := 0, 0; i < n; i, j = i+1, j+1 {
		ans[i] = s / n
		if j < s%n {
			ans[i]++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function missingRolls(rolls: number[], mean: number, n: number): number[] {
    const m = rolls.length;
    const s = (n + m) * mean - rolls.reduce((a, b) => a + b, 0);
    if (s > n * 6 || s < n) {
        return [];
    }
    const ans: number[] = Array(n).fill((s / n) | 0);
    for (let i = 0; i < s % n; ++i) {
        ans[i]++;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_rolls(rolls: Vec<i32>, mean: i32, n: i32) -> Vec<i32> {
        let m = rolls.len() as i32;
        let s = (n + m) * mean - rolls.iter().sum::<i32>();

        if s > n * 6 || s < n {
            return vec![];
        }

        let mut ans = vec![s / n; n as usize];
        for i in 0..(s % n) as usize {
            ans[i] += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
