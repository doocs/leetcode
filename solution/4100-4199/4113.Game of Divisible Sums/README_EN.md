---
comments: true
difficulty: Medium
rating: 1791
source: Weekly Contest 528 Q3
---

<!-- problem:start -->

# [4113. Game of Divisible Sums](https://leetcode.com/problems/game-of-divisible-sums)

[中文文档](/solution/4100-4199/4113.Game%20of%20Divisible%20Sums/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are playing a game with an integer array <code>nums</code>. Alice goes first.</p>

<p>On each turn, the current player takes and removes either the <strong>leftmost element</strong> or, if at least two elements remain, the <strong>two leftmost elements</strong>.</p>

<p>The <strong>sum</strong> of the elements taken in that turn determines who plays next:</p>

<ul>
    <li>If the sum is divisible by 3, the other player plays next.</li>
    <li>Otherwise, the same player plays again.</li>
</ul>

<p>The <strong>score</strong> of a player is the sum of the values of all elements that player takes.</p>

<p>Both players play optimally, with each player trying to <strong>maximize</strong> their own score.</p>

<p>The game ends when all elements have been taken.</p>

<p>Return Alice's score <strong>minus</strong> Bob's score.</p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,6,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>One sequence of optimal turns is as follows:</p>

<ol>
	<li>Alice takes the two leftmost elements. Their sum is <code>3 + 6 = 9</code>, which is divisible by 3, so Bob plays next. Alice&#39;s score is now 9, and the remaining array is <code>[4]</code>.</li>
	<li>Bob takes the only remaining element. His score is 4, and the game ends.</li>
</ol>

<p>Alice&#39;s score minus Bob&#39;s score is <code>9 - 4 = 5</code>, so the answer is 5.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,1,2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>One sequence of optimal turns is as follows:</p>

<ol>
	<li>Alice takes the two leftmost elements. Their sum is <code>6 + 1 = 7</code>, which is not divisible by 3, so Alice plays again. Her score is now 7, and the remaining array is <code>[2, 1, 2]</code>.</li>
	<li>Alice takes the leftmost element. Its value is 2, which is not divisible by 3, so Alice plays again. Her score is now <code>7 + 2 = 9</code>, and the remaining array is <code>[1, 2]</code>.</li>
	<li>Alice takes both remaining elements. Her score is now <code>9 + 1 + 2 = 12</code>, and the game ends.</li>
</ol>

<p>Bob takes no elements, so his score is 0. Alice&#39;s score minus Bob&#39;s score is <code>12 - 0 = 12</code>, so the answer is 12.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">-3</span></p>

<p><strong>Explanation:</strong></p>

<p>One sequence of optimal turns is as follows:</p>

<ol>
	<li>Alice takes the two leftmost elements. Their sum is <code>3 + 3 = 6</code>, which is divisible by 3, so Bob plays next. Alice&#39;s score is now 6, and the remaining array is <code>[4, 5]</code>.</li>
	<li>Bob takes both remaining elements. His score is <code>4 + 5 = 9</code>, and the game ends.</li>
</ol>

<p>Alice&#39;s score minus Bob&#39;s score is <code>6 - 9 = -3</code>, so the answer is -3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

<!-- thinking:start -->

> **Thinking**
>
> $n$ reaches $10^5$. Each move takes one element or two, so the full game tree is exponential.
>
> Every element of a suffix is taken by someone, so the two scores on that suffix have a fixed sum. Maximizing one's own score is the same as maximizing the difference between the two scores. Who moves next depends only on whether the sum just taken is divisible by $3$.
>
> The state is therefore the start index $i$ of the remaining suffix. $\textit{dfs}(i)$ is the largest difference the player to move can obtain on that suffix. If the sum is divisible by $3$, the opponent moves next and the difference subtracts the following $\textit{dfs}$ value; otherwise the same player continues and the difference adds it.
>
> From each start, try taking one element and taking two. Memoization evaluates each index once.

<!-- thinking:end -->

Let $\textit{dfs}(i)$ be the maximum of the current player's score minus the opponent's score on the suffix that starts at index $i$, when it is the current player's turn. The answer is $\textit{dfs}(0)$.

Every element of the suffix is eventually taken, so the two scores have a fixed sum. Maximizing one's own score is the same as maximizing the difference. The opponent also maximizes that difference on their own turn, so the current player subtracts or adds the resulting $\textit{dfs}$ value according to who moves next.

If $i$ is past the end of the array, return $0$.

The current player may take $\textit{nums}[i]$. Let $x=\textit{nums}[i]$. If $x$ is divisible by $3$, the opponent moves next and the difference is $x-\textit{dfs}(i+1)$. Otherwise the current player moves again and the difference is $x+\textit{dfs}(i+1)$.

When another element remains, the current player may instead take $\textit{nums}[i]$ and $\textit{nums}[i+1]$. Let $s$ be their sum. If $s$ is divisible by $3$, the difference is $s-\textit{dfs}(i+2)$; otherwise it is $s+\textit{dfs}(i+2)$.

Take the larger of the two moves, and store $\textit{dfs}(i)$ so that each index is computed once.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDifference(self, nums: list[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(nums):
                return 0
            x = nums[i]
            res = x - dfs(i + 1) if x % 3 == 0 else x + dfs(i + 1)
            if i + 1 < len(nums):
                s = x + nums[i + 1]
                t = s - dfs(i + 2) if s % 3 == 0 else s + dfs(i + 2)
                res = max(res, t)
            return res

        return dfs(0)
```

#### Java

```java
class Solution {
    private int[] nums;
    private Long[] memo;

    public long maxDifference(int[] nums) {
        this.nums = nums;
        this.memo = new Long[nums.length];
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= nums.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        int x = nums[i];
        long res = x % 3 == 0 ? x - dfs(i + 1) : x + dfs(i + 1);
        if (i + 1 < nums.length) {
            long s = x + nums[i + 1];
            long t = s % 3 == 0 ? s - dfs(i + 2) : s + dfs(i + 2);
            res = Math.max(res, t);
        }
        memo[i] = res;
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxDifference(vector<int>& nums) {
        int n = nums.size();
        vector<long long> memo(n, LLONG_MIN);
        auto dfs = [&](this auto&& dfs, int i) -> long long {
            if (i >= n) {
                return 0;
            }
            if (memo[i] != LLONG_MIN) {
                return memo[i];
            }
            int x = nums[i];
            long long res = x % 3 == 0 ? x - dfs(i + 1) : x + dfs(i + 1);
            if (i + 1 < n) {
                long long s = x + nums[i + 1];
                long long t = s % 3 == 0 ? s - dfs(i + 2) : s + dfs(i + 2);
                res = max(res, t);
            }
            memo[i] = res;
            return res;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxDifference(nums []int) int64 {
	n := len(nums)
	const INF int64 = 1 << 62
	memo := make([]int64, n)
	for i := range memo {
		memo[i] = -INF
	}
	var dfs func(i int) int64
	dfs = func(i int) int64 {
		if i >= n {
			return 0
		}
		if memo[i] != -INF {
			return memo[i]
		}
		x := nums[i]
		var res int64
		if x%3 == 0 {
			res = int64(x) - dfs(i+1)
		} else {
			res = int64(x) + dfs(i+1)
		}
		if i+1 < n {
			s := int64(x + nums[i+1])
			var t int64
			if s%3 == 0 {
				t = s - dfs(i+2)
			} else {
				t = s + dfs(i+2)
			}
			res = max(res, t)
		}
		memo[i] = res
		return res
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxDifference(nums: number[]): number {
    const n = nums.length;
    const memo: number[] = new Array(n).fill(NaN);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (!Number.isNaN(memo[i])) {
            return memo[i];
        }
        const x = nums[i];
        let res = x % 3 === 0 ? x - dfs(i + 1) : x + dfs(i + 1);
        if (i + 1 < n) {
            const s = x + nums[i + 1];
            const t = s % 3 === 0 ? s - dfs(i + 2) : s + dfs(i + 2);
            res = Math.max(res, t);
        }
        memo[i] = res;
        return res;
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
