---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3776.Minimum%20Moves%20to%20Balance%20Circular%20Array/README_EN.md
rating: 1739
source: Weekly Contest 480 Q3
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3776. Minimum Moves to Balance Circular Array](https://leetcode.com/problems/minimum-moves-to-balance-circular-array)

[中文文档](/solution/3700-3799/3776.Minimum%20Moves%20to%20Balance%20Circular%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>circular</strong> array <code>balance</code> of length <code>n</code>, where <code>balance[i]</code> is the net balance of person <code>i</code>.</p>

<p>In one move, a person can transfer <strong>exactly</strong> 1 unit of balance to either their left or right neighbor.</p>

<p>Return the <strong>minimum</strong> number of moves required so that every person has a <strong>non-negative</strong> balance. If it is impossible, return <code>-1</code>.</p>

<p><strong>Note</strong>: You are guaranteed that <strong>at most</strong> 1 index has a <strong>negative</strong> balance initially.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [5,1,-4]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of moves is:</p>

<ul>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 2</code>, resulting in <code>balance = [5, 0, -3]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 2</code>, resulting in <code>balance = [4, 0, -2]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 2</code>, resulting in <code>balance = [3, 0, -1]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 2</code>, resulting in <code>balance = [2, 0, 0]</code></li>
</ul>

<p>Thus, the minimum number of moves required is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [1,2,-5,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of moves is:</p>

<ul>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 2</code>, resulting in <code>balance = [1, 1, -4, 2]</code></li>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 2</code>, resulting in <code>balance = [1, 0, -3, 2]</code></li>
	<li>Move 1 unit from <code>i = 3</code> to <code>i = 2</code>, resulting in <code>balance = [1, 0, -2, 1]</code></li>
	<li>Move 1 unit from <code>i = 3</code> to <code>i = 2</code>, resulting in <code>balance = [1, 0, -1, 0]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 1</code>, resulting in <code>balance = [0, 1, -1, 0]</code></li>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 2</code>, resulting in <code>balance = [0, 0, 0, 0]</code></li>
</ul>

<p>Thus, the minimum number of moves required is 6.​​​</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [-3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​</strong>It is impossible to make all balances non-negative for <code>balance = [-3, 2]</code>, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == balance.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= balance[i] &lt;= 10<sup>9</sup></code></li>
	<li>There is at most one negative value in <code>balance</code> initially.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first calculate the sum of the array $\textit{balance}$. If the sum is less than $0$, it is impossible to make all balances non-negative, so we directly return $-1$. Then we find the minimum balance in the array and its index. If the minimum balance is greater than or equal to $0$, all balances are already non-negative, so we directly return $0$.

Next, we calculate the amount of balance needed $\textit{need}$, which is the opposite of the minimum balance. Then starting from the index of the minimum balance, we traverse the array to the left and right, taking as much balance as possible from each position to fill $\textit{need}$, and calculate the number of moves. We continue until $\textit{need}$ becomes $0$, and return the total number of moves.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{balance}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, balance: List[int]) -> int:
        if sum(balance) < 0:
            return -1
        mn = min(balance)
        if mn >= 0:
            return 0
        need = -mn
        i = balance.index(mn)
        n = len(balance)
        ans = 0
        for j in range(1, n):
            a = balance[(i - j + n) % n]
            b = balance[(i + j - n) % n]
            c1 = min(a, need)
            need -= c1
            ans += c1 * j
            c2 = min(b, need)
            need -= c2
            ans += c2 * j
        return ans
```

#### Java

```java
class Solution {
    public long minMoves(int[] balance) {
        long sum = 0;
        for (int b : balance) {
            sum += b;
        }
        if (sum < 0) {
            return -1;
        }

        int n = balance.length;
        int mn = balance[0];
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (balance[i] < mn) {
                mn = balance[i];
                idx = i;
            }
        }

        if (mn >= 0) {
            return 0;
        }

        int need = -mn;
        long ans = 0;

        for (int j = 1; j < n; j++) {
            int a = balance[(idx - j + n) % n];
            int b = balance[(idx + j) % n];

            int c1 = Math.min(a, need);
            need -= c1;
            ans += (long) c1 * j;

            int c2 = Math.min(b, need);
            need -= c2;
            ans += (long) c2 * j;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minMoves(vector<int>& balance) {
        long long sum = 0;
        for (int b : balance) {
            sum += b;
        }
        if (sum < 0) {
            return -1;
        }

        int n = balance.size();
        int mn = balance[0];
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (balance[i] < mn) {
                mn = balance[i];
                idx = i;
            }
        }

        if (mn >= 0) {
            return 0;
        }

        int need = -mn;
        long long ans = 0;

        for (int j = 1; j < n; j++) {
            int a = balance[(idx - j + n) % n];
            int b = balance[(idx + j) % n];

            int c1 = min(a, need);
            need -= c1;
            ans += 1LL * c1 * j;

            int c2 = min(b, need);
            need -= c2;
            ans += 1LL * c2 * j;
        }

        return ans;
    }
};
```

#### Go

```go
func minMoves(balance []int) int64 {
	var sum int64
	for _, b := range balance {
		sum += int64(b)
	}
	if sum < 0 {
		return -1
	}

	n := len(balance)
	mn := balance[0]
	idx := 0
	for i := 1; i < n; i++ {
		if balance[i] < mn {
			mn = balance[i]
			idx = i
		}
	}

	if mn >= 0 {
		return 0
	}

	need := -mn
	var ans int64

	for j := 1; j < n; j++ {
		a := balance[(idx-j+n)%n]
		b := balance[(idx+j)%n]

		c1 := min(a, need)
		need -= c1
		ans += int64(c1) * int64(j)

		c2 := min(b, need)
		need -= c2
		ans += int64(c2) * int64(j)
	}

	return ans
}
```

#### TypeScript

```ts
function minMoves(balance: number[]): number {
    const sum = balance.reduce((a, b) => a + b, 0);
    if (sum < 0) {
        return -1;
    }

    const n = balance.length;
    let mn = balance[0],
        idx = 0;
    for (let i = 1; i < n; i++) {
        if (balance[i] < mn) {
            mn = balance[i];
            idx = i;
        }
    }

    if (mn >= 0) {
        return 0;
    }

    let need = -mn;
    let ans = 0;

    for (let j = 1; j < n; j++) {
        const a = balance[(idx - j + n) % n];
        const b = balance[(idx + j) % n];

        const c1 = Math.min(a, need);
        need -= c1;
        ans += c1 * j;

        const c2 = Math.min(b, need);
        need -= c2;
        ans += c2 * j;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
