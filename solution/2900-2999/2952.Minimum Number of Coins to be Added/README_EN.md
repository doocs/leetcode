---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2952.Minimum%20Number%20of%20Coins%20to%20be%20Added/README_EN.md
rating: 1784
source: Weekly Contest 374 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [2952. Minimum Number of Coins to be Added](https://leetcode.com/problems/minimum-number-of-coins-to-be-added)

[中文文档](/solution/2900-2999/2952.Minimum%20Number%20of%20Coins%20to%20be%20Added/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>coins</code>, representing the values of the coins available, and an integer <code>target</code>.</p>

<p>An integer <code>x</code> is <strong>obtainable</strong> if there exists a subsequence of <code>coins</code> that sums to <code>x</code>.</p>

<p>Return <em>the<strong> minimum</strong> number of coins <strong>of any value</strong> that need to be added to the array so that every integer in the range</em> <code>[1, target]</code><em> is <strong>obtainable</strong></em>.</p>

<p>A <strong>subsequence</strong> of an array is a new <strong>non-empty</strong> array that is formed from the original array by deleting some (<strong>possibly none</strong>) of the elements without disturbing the relative positions of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,4,10], target = 19
<strong>Output:</strong> 2
<strong>Explanation:</strong> We need to add coins 2 and 8. The resulting array will be [1,2,4,8,10].
It can be shown that all integers from 1 to 19 are obtainable from the resulting array, and that 2 is the minimum number of coins that need to be added to the array. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,4,10,5,7,19], target = 19
<strong>Output:</strong> 1
<strong>Explanation:</strong> We only need to add the coin 2. The resulting array will be [1,2,4,5,7,10,19].
It can be shown that all integers from 1 to 19 are obtainable from the resulting array, and that 1 is the minimum number of coins that need to be added to the array. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,1,1], target = 20
<strong>Output:</strong> 3
<strong>Explanation:</strong> We need to add coins 4, 8, and 16. The resulting array will be [1,1,1,4,8,16].
It can be shown that all integers from 1 to 20 are obtainable from the resulting array, and that 3 is the minimum number of coins that need to be added to the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins[i] &lt;= target</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Construction

Suppose the current amount we need to construct is $s$, and we have already constructed all amounts in $[0,...,s-1]$. If there is a new coin $x$, we add it to the array, which can construct all amounts in $[x, s+x-1]$.

Next, we discuss in two cases:

-   If $x \le s$, then we can merge the two intervals above to get all amounts in $[0, s+x-1]$.
-   If $x \gt s$, then we need to add a coin with a face value of $s$, so that we can construct all amounts in $[0, 2s-1]$. Then we consider the size relationship between $x$ and $s$ and continue to analyze.

Therefore, we sort the array $coins$ in ascending order, and then traverse the coins in the array from small to large. For each coin $x$, we discuss in the above two cases until $s > target$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumAddedCoins(self, coins: List[int], target: int) -> int:
        coins.sort()
        s = 1
        ans = i = 0
        while s <= target:
            if i < len(coins) and coins[i] <= s:
                s += coins[i]
                i += 1
            else:
                s <<= 1
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int ans = 0;
        for (int i = 0, s = 1; s <= target;) {
            if (i < coins.length && coins[i] <= s) {
                s += coins[i++];
            } else {
                s <<= 1;
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumAddedCoins(vector<int>& coins, int target) {
        sort(coins.begin(), coins.end());
        int ans = 0;
        for (int i = 0, s = 1; s <= target;) {
            if (i < coins.size() && coins[i] <= s) {
                s += coins[i++];
            } else {
                s <<= 1;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumAddedCoins(coins []int, target int) (ans int) {
	slices.Sort(coins)
	for i, s := 0, 1; s <= target; {
		if i < len(coins) && coins[i] <= s {
			s += coins[i]
			i++
		} else {
			s <<= 1
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumAddedCoins(coins: number[], target: number): number {
    coins.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, s = 1; s <= target; ) {
        if (i < coins.length && coins[i] <= s) {
            s += coins[i++];
        } else {
            s <<= 1;
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
