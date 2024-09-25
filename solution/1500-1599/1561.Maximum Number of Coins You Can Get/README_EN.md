---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1561.Maximum%20Number%20of%20Coins%20You%20Can%20Get/README_EN.md
rating: 1405
source: Weekly Contest 203 Q2
tags:
    - Greedy
    - Array
    - Math
    - Game Theory
    - Sorting
---

<!-- problem:start -->

# [1561. Maximum Number of Coins You Can Get](https://leetcode.com/problems/maximum-number-of-coins-you-can-get)

[中文文档](/solution/1500-1599/1561.Maximum%20Number%20of%20Coins%20You%20Can%20Get/README.md)

## Description

<!-- description:start -->

<p>There are <code>3n</code> piles of coins of varying size, you and your friends will take piles of coins as follows:</p>

<ul>
	<li>In each step, you will choose <strong>any </strong><code>3</code> piles of coins (not necessarily consecutive).</li>
	<li>Of your choice, Alice will pick the pile with the maximum number of coins.</li>
	<li>You will pick the next pile with the maximum number of coins.</li>
	<li>Your friend Bob will pick the last pile.</li>
	<li>Repeat until there are no more piles of coins.</li>
</ul>

<p>Given an array of integers <code>piles</code> where <code>piles[i]</code> is the number of coins in the <code>i<sup>th</sup></code> pile.</p>

<p>Return the maximum number of coins that you can have.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [2,4,1,2,7,8]
<strong>Output:</strong> 9
<strong>Explanation: </strong>Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with <strong>7</strong> coins and Bob the last one.
Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with <strong>2</strong> coins and Bob the last one.
The maximum number of coins which you can have are: 7 + 2 = 9.
On the other hand if we choose this arrangement (1, <strong>2</strong>, 8), (2, <strong>4</strong>, 7) you only get 2 + 4 = 6 coins which is not optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [2,4,5]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> piles = [9,8,7,6,5,1,2,3,4]
<strong>Output:</strong> 18
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= piles.length &lt;= 10<sup>5</sup></code></li>
	<li><code>piles.length % 3 == 0</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

To maximize the number of coins we get, we can greedily let Bob take the smallest $n$ piles of coins. Each time, we let Alice take the largest pile of coins, then we take the second largest pile of coins, and so on, until there are no more coins to take.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the number of piles of coins.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCoins(self, piles: List[int]) -> int:
        piles.sort()
        return sum(piles[len(piles) // 3 :][::2])
```

#### Java

```java
class Solution {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int ans = 0;
        for (int i = piles.length / 3; i < piles.length; i += 2) {
            ans += piles[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCoins(vector<int>& piles) {
        ranges::sort(piles);
        int ans = 0;
        for (int i = piles.size() / 3; i < piles.size(); i += 2) {
            ans += piles[i];
        }
        return ans;
    }
};
```

#### Go

```go
func maxCoins(piles []int) (ans int) {
	sort.Ints(piles)
	for i := len(piles) / 3; i < len(piles); i += 2 {
		ans += piles[i]
	}
	return
}
```

#### TypeScript

```ts
function maxCoins(piles: number[]): number {
    piles.sort((a, b) => a - b);
    let ans = 0;
    for (let i = piles.length / 3; i < piles.length; i += 2) {
        ans += piles[i];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_coins(mut piles: Vec<i32>) -> i32 {
        piles.sort();
        let mut ans = 0;
        for i in (piles.len() / 3..piles.len()).step_by(2) {
            ans += piles[i];
        }
        ans
    }
}
```

#### C

```c
int compare(const void* a, const void* b) {
    return (*(int*) a - *(int*) b);
}

int maxCoins(int* piles, int pilesSize) {
    qsort(piles, pilesSize, sizeof(int), compare);
    int ans = 0;
    for (int i = pilesSize / 3; i < pilesSize; i += 2) {
        ans += piles[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
