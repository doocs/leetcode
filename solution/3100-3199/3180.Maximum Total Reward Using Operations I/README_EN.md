---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3180.Maximum%20Total%20Reward%20Using%20Operations%20I/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3180. Maximum Total Reward Using Operations I](https://leetcode.com/problems/maximum-total-reward-using-operations-i)

[中文文档](/solution/3100-3199/3180.Maximum%20Total%20Reward%20Using%20Operations%20I/README.md)

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
	<li><code>1 &lt;= rewardValues.length &lt;= 2000</code></li>
	<li><code>1 &lt;= rewardValues[i] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Memoization + Binary Search

We can sort the `rewardValues` array and then use memoization to solve for the maximum total reward.

We define a function $\text{dfs}(x)$, representing the maximum total reward that can be obtained when the current total reward is $x$. Thus, the answer is $\text{dfs}(0)$.

The execution process of the function $\text{dfs}(x)$ is as follows:

1. Perform a binary search in the `rewardValues` array for the index $i$ of the first element greater than $x$;
2. Iterate over the elements in the `rewardValues` array starting from index $i$, and for each element $v$, calculate the maximum value of $v + \text{dfs}(x + v)$.
3. Return the result.

To avoid repeated calculations, we use a memoization array `f` to record the results that have already been computed.

The time complexity is $O(n \times (\log n + M))$, and the space complexity is $O(M)$. Where $n$ is the length of the `rewardValues` array, and $M$ is twice the maximum value in the `rewardValues` array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTotalReward(self, rewardValues: List[int]) -> int:
        @cache
        def dfs(x: int) -> int:
            i = bisect_right(rewardValues, x)
            ans = 0
            for v in rewardValues[i:]:
                ans = max(ans, v + dfs(x + v))
            return ans

        rewardValues.sort()
        return dfs(0)
```

#### Java

```java
class Solution {
    private int[] nums;
    private Integer[] f;

    public int maxTotalReward(int[] rewardValues) {
        nums = rewardValues;
        Arrays.sort(nums);
        int n = nums.length;
        f = new Integer[nums[n - 1] << 1];
        return dfs(0);
    }

    private int dfs(int x) {
        if (f[x] != null) {
            return f[x];
        }
        int i = Arrays.binarySearch(nums, x + 1);
        i = i < 0 ? -i - 1 : i;
        int ans = 0;
        for (; i < nums.length; ++i) {
            ans = Math.max(ans, nums[i] + dfs(x + nums[i]));
        }
        return f[x] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxTotalReward(vector<int>& rewardValues) {
        sort(rewardValues.begin(), rewardValues.end());
        int n = rewardValues.size();
        int f[rewardValues.back() << 1];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int x) {
            if (f[x] != -1) {
                return f[x];
            }
            auto it = upper_bound(rewardValues.begin(), rewardValues.end(), x);
            int ans = 0;
            for (; it != rewardValues.end(); ++it) {
                ans = max(ans, rewardValues[it - rewardValues.begin()] + dfs(x + *it));
            }
            return f[x] = ans;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxTotalReward(rewardValues []int) int {
	sort.Ints(rewardValues)
	n := len(rewardValues)
	f := make([]int, rewardValues[n-1]<<1)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(x int) int {
		if f[x] != -1 {
			return f[x]
		}
		i := sort.SearchInts(rewardValues, x+1)
		f[x] = 0
		for _, v := range rewardValues[i:] {
			f[x] = max(f[x], v+dfs(x+v))
		}
		return f[x]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxTotalReward(rewardValues: number[]): number {
    rewardValues.sort((a, b) => a - b);
    const search = (x: number): number => {
        let [l, r] = [0, rewardValues.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (rewardValues[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const f: number[] = Array(rewardValues.at(-1)! << 1).fill(-1);
    const dfs = (x: number): number => {
        if (f[x] !== -1) {
            return f[x];
        }
        let ans = 0;
        for (let i = search(x); i < rewardValues.length; ++i) {
            ans = Math.max(ans, rewardValues[i] + dfs(x + rewardValues[i]));
        }
        return (f[x] = ans);
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We define $f[i][j]$ as whether it is possible to obtain a total reward of $j$ using the first $i$ reward values. Initially, $f[0][0] = \text{True}$, and all other values are $\text{False}$.

We consider the $i$-th reward value $v$. If we do not choose it, then $f[i][j] = f[i - 1][j]$. If we choose it, then $f[i][j] = f[i - 1][j - v]$, where $0 \leq j - v < v$. Thus, the state transition equation is:

$$
f[i][j] = f[i - 1][j] \vee f[i - 1][j - v]
$$

The final answer is $\max\{j \mid f[n][j] = \text{True}\}$.

Since $f[i][j]$ only depends on $f[i - 1][j]$ and $f[i - 1][j - v]$, we can optimize away the first dimension and use only a one-dimensional array for state transitions.

The time complexity is $O(n \times M)$, and the space complexity is $O(M)$. Where $n$ is the length of the `rewardValues` array, and $M$ is twice the maximum value in the `rewardValues` array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTotalReward(self, rewardValues: List[int]) -> int:
        nums = sorted(set(rewardValues))
        m = nums[-1] << 1
        f = [False] * m
        f[0] = True
        for v in nums:
            for j in range(m):
                if 0 <= j - v < v:
                    f[j] |= f[j - v]
        ans = m - 1
        while not f[ans]:
            ans -= 1
        return ans
```

#### Java

```java
class Solution {
    public int maxTotalReward(int[] rewardValues) {
        int[] nums = Arrays.stream(rewardValues).distinct().sorted().toArray();
        int n = nums.length;
        int m = nums[n - 1] << 1;
        boolean[] f = new boolean[m];
        f[0] = true;
        for (int v : nums) {
            for (int j = 0; j < m; ++j) {
                if (0 <= j - v && j - v < v) {
                    f[j] |= f[j - v];
                }
            }
        }
        int ans = m - 1;
        while (!f[ans]) {
            --ans;
        }
        return ans;
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
        int n = rewardValues.size();
        int m = rewardValues.back() << 1;
        bool f[m];
        memset(f, false, sizeof(f));
        f[0] = true;
        for (int v : rewardValues) {
            for (int j = 1; j < m; ++j) {
                if (0 <= j - v && j - v < v) {
                    f[j] = f[j] || f[j - v];
                }
            }
        }
        int ans = m - 1;
        while (!f[ans]) {
            --ans;
        }
        return ans;
    }
};
```

#### Go

```go
func maxTotalReward(rewardValues []int) int {
	slices.Sort(rewardValues)
	nums := slices.Compact(rewardValues)
	n := len(nums)
	m := nums[n-1] << 1
	f := make([]bool, m)
	f[0] = true
	for _, v := range nums {
		for j := 1; j < m; j++ {
			if 0 <= j-v && j-v < v {
				f[j] = f[j] || f[j-v]
			}
		}
	}
	ans := m - 1
	for !f[ans] {
		ans--
	}
	return ans
}
```

#### TypeScript

```ts
function maxTotalReward(rewardValues: number[]): number {
    const nums = Array.from(new Set(rewardValues)).sort((a, b) => a - b);
    const n = nums.length;
    const m = nums[n - 1] << 1;
    const f: boolean[] = Array(m).fill(false);
    f[0] = true;
    for (const v of nums) {
        for (let j = 1; j < m; ++j) {
            if (0 <= j - v && j - v < v) {
                f[j] = f[j] || f[j - v];
            }
        }
    }
    let ans = m - 1;
    while (!f[ans]) {
        --ans;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Dynamic Programming + Bit Manipulation

We can optimize Solution 2 by defining a binary number $f$ to save the current state, where the $i$-th bit of $f$ being $1$ indicates that a total reward of $i$ is reachable.

Observing the state transition equation from Solution 2, $f[j] = f[j] \vee f[j - v]$, this is equivalent to taking the lower $v$ bits of $f$, shifting them left by $v$ bits, and then performing an OR operation with the original $f$.

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
import java.util.Arrays;

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
