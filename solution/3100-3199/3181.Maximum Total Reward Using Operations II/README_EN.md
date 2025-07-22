---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3181.Maximum%20Total%20Reward%20Using%20Operations%20II/README_EN.md
rating: 2688
source: Weekly Contest 401 Q4
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3181. Maximum Total Reward Using Operations II](https://leetcode.com/problems/maximum-total-reward-using-operations-ii)

[中文文档](/solution/3100-3199/3181.Maximum%20Total%20Reward%20Using%20Operations%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>rewardValues</code> of length <code>n</code>, representing the values of rewards.</p>

<p>Initially, your total reward <code>x</code> is 0, and all indices are <strong>unmarked</strong>. You are allowed to perform the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Choose an <strong>unmarked</strong> index <code>i</code> from the range <code>[0, n - 1]</code>.</li>
	<li>If <code>rewardValues[i]</code> is <strong>greater</strong> than your current total reward <code>x</code>, then add <code>rewardValues[i]</code> to <code>x</code> (i.e., <code>x = x + rewardValues[i]</code>), and <strong>mark</strong> the index <code>i</code>.</li>
</ul>

<p>Return an integer denoting the <strong>maximum </strong><em>total reward</em> you can collect by performing the operations optimally.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">rewardValues = [1,1,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>During the operations, we can choose to mark the indices 0 and 2 in order, and the total reward will be 4, which is the maximum.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">rewardValues = [1,6,4,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>Mark the indices 0, 2, and 1 in order. The total reward will then be 11, which is the maximum.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rewardValues.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= rewardValues[i] &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Bit Manipulation

We define $f[i][j]$ as whether it is possible to obtain a total reward of $j$ using the first $i$ reward values. Initially, $f[0][0] = \textit{True}$, and all other values are $\textit{False}$.

We consider the $i$-th reward value $v$. If we do not choose it, then $f[i][j] = f[i - 1][j]$; if we choose it, then $f[i][j] = f[i - 1][j - v]$, where $0 \leq j - v < v$. Thus, the state transition equation is:

$$
f[i][j] = f[i - 1][j] \vee f[i - 1][j - v]
$$

The final answer is $\max\{j \mid f[n][j] = \textit{True}\}$.

Since $f[i][j]$ only depends on $f[i - 1][j]$ and $f[i - 1][j - v]$, we can optimize away the first dimension and use only a one-dimensional array for state transitions. Additionally, due to the large data range of this problem, we need to use bit manipulation to optimize the efficiency of state transitions.

We define a binary number $f$ to save the current state, where the $i$-th bit of $f$ being $1$ indicates that a total reward of $i$ is reachable.

Observing the state transition equation $f[j] = f[j] \vee f[j - v]$, this is equivalent to taking the lower $v$ bits of $f$, shifting them left by $v$ bits, and then performing an OR operation with the original $f$.

Thus, the answer is the position of the highest bit in $f$.

The time complexity is $O(n \times M / w)$, and the space complexity is $O(n + M / w)$. Where $n$ is the length of the `rewardValues` array, $M$ is twice the maximum value in the `rewardValues` array, and the integer $w = 32$ or $64$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTotalReward(self, rewardValues: List[int]) -> int:
        nums = sorted(set(rewardValues))
        f = 1
        for v in nums:
            f |= (f & ((1 << v) - 1)) << v
        return f.bit_length() - 1
```

#### Java

```java
import java.math.BigInteger;

class Solution {
    public int maxTotalReward(int[] rewardValues) {
        int[] nums = Arrays.stream(rewardValues).distinct().sorted().toArray();
        BigInteger f = BigInteger.ONE;
        for (int v : nums) {
            BigInteger mask = BigInteger.ONE.shiftLeft(v).subtract(BigInteger.ONE);
            BigInteger shifted = f.and(mask).shiftLeft(v);
            f = f.or(shifted);
        }
        return f.bitLength() - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxTotalReward(vector<int>& rewardValues) {
        sort(rewardValues.begin(), rewardValues.end());
        rewardValues.erase(unique(rewardValues.begin(), rewardValues.end()), rewardValues.end());
        bitset<100000> f{1};
        for (int v : rewardValues) {
            int shift = f.size() - v;
            f |= f << shift >> (shift - v);
        }
        for (int i = rewardValues.back() * 2 - 1;; i--) {
            if (f.test(i)) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func maxTotalReward(rewardValues []int) int {
	slices.Sort(rewardValues)
	rewardValues = slices.Compact(rewardValues)
	one := big.NewInt(1)
	f := big.NewInt(1)
	p := new(big.Int)
	for _, v := range rewardValues {
		mask := p.Sub(p.Lsh(one, uint(v)), one)
		f.Or(f, p.Lsh(p.And(f, mask), uint(v)))
	}
	return f.BitLen() - 1
}
```

#### TypeScript

```ts
function maxTotalReward(rewardValues: number[]): number {
    rewardValues.sort((a, b) => a - b);
    rewardValues = [...new Set(rewardValues)];
    let f = 1n;
    for (const x of rewardValues) {
        const mask = (1n << BigInt(x)) - 1n;
        f = f | ((f & mask) << BigInt(x));
    }
    return f.toString(2).length - 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
