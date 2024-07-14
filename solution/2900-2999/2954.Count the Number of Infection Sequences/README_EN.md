---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2954.Count%20the%20Number%20of%20Infection%20Sequences/README_EN.md
rating: 2644
source: Weekly Contest 374 Q4
tags:
    - Array
    - Math
    - Combinatorics
---

<!-- problem:start -->

# [2954. Count the Number of Infection Sequences](https://leetcode.com/problems/count-the-number-of-infection-sequences)

[中文文档](/solution/2900-2999/2954.Count%20the%20Number%20of%20Infection%20Sequences/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an array <code>sick</code> sorted in increasing order, representing positions of infected people in a line of <code>n</code> people.</p>

<p>At each step, <strong>one </strong>uninfected person <strong>adjacent</strong> to an infected person gets infected. This process continues until everyone is infected.</p>

<p>An <strong>infection sequence</strong> is the order in which uninfected people become infected, excluding those initially infected.</p>

<p>Return the number of different infection sequences possible, modulo <code>10<sup>9</sup>+7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, sick = [0,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>There is a total of 6 different sequences overall.</p>

<ul>
	<li>Valid infection sequences are <code>[1,2,3]</code>, <code>[1,3,2]</code>, <code>[3,2,1]</code> and <code>[3,1,2]</code>.</li>
	<li><code>[2,3,1]</code> and <code>[2,1,3]</code> are not valid infection sequences because the person at index 2 cannot be infected at the first step.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, sick = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>There is a total of 6 different sequences overall.</p>

<ul>
	<li>Valid infection sequences are <code>[0,2,3]</code>, <code>[2,0,3]</code> and <code>[2,3,0]</code>.</li>
	<li><code>[3,2,0]</code>, <code>[3,0,2]</code>, and <code>[0,3,2]</code> are not valid infection sequences because the infection starts at the person at index 1, then the order of infection is 2, then 3, and hence 3 cannot be infected earlier than 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sick.length &lt;= n - 1</code></li>
	<li><code>0 &lt;= sick[i] &lt;= n - 1</code></li>
	<li><code>sick</code> is sorted in increasing order.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Combinatorial Mathematics + Multiplicative Inverse + Fast Power

According to the problem description, the children who have a cold have divided the children who have not yet caught a cold into several continuous segments. We can use an array $nums$ to record the number of children who are not cold in each segment, and there are a total of $s = \sum_{i=0}^{k} nums[k]$ children who are not cold. We can find that the number of cold sequences is the number of permutations of $s$ different elements, that is, $s!$.

Assuming that there is only one transmission scheme for each segment of children who are not cold, there are $\frac{s!}{\prod_{i=0}^{k} nums[k]!}$ cold sequences in total.

Next, we consider the transmission scheme of each segment of children who are not cold. Suppose there are $x$ children in a segment who are not cold, then they have $2^{x-1}$ transmission schemes, because each time you can choose one end from the left and right ends of a segment to transmit, that is: two choices, there are a total of $x-1$ transmissions. However, if it is the first segment or the last segment, there is only one choice.

In summary, the total number of cold sequences is:

$$
\frac{s!}{\prod_{i=0}^{k} nums[k]!} \prod_{i=1}^{k-1} 2^{nums[i]-1}
$$

Finally, we need to consider that the answer may be very large and need to be modulo $10^9 + 7$. Therefore, we need to preprocess the factorial and multiplicative inverse.

The time complexity is $O(m)$, where $m$ is the length of the array $sick$. Ignoring the space consumption of the preprocessing array, the space complexity is $O(m)$.

<!-- tabs:start -->

#### Python3

```python
mod = 10**9 + 7
mx = 10**5
fac = [1] * (mx + 1)
for i in range(2, mx + 1):
    fac[i] = fac[i - 1] * i % mod


class Solution:
    def numberOfSequence(self, n: int, sick: List[int]) -> int:
        nums = [b - a - 1 for a, b in pairwise([-1] + sick + [n])]
        ans = 1
        s = sum(nums)
        ans = fac[s]
        for x in nums:
            if x:
                ans = ans * pow(fac[x], mod - 2, mod) % mod
        for x in nums[1:-1]:
            if x > 1:
                ans = ans * pow(2, x - 1, mod) % mod
        return ans
```

#### Java

```java
class Solution {
    private static final int MOD = (int) (1e9 + 7);
    private static final int MX = 100000;
    private static final int[] FAC = new int[MX + 1];

    static {
        FAC[0] = 1;
        for (int i = 1; i <= MX; i++) {
            FAC[i] = (int) ((long) FAC[i - 1] * i % MOD);
        }
    }

    public int numberOfSequence(int n, int[] sick) {
        int m = sick.length;
        int[] nums = new int[m + 1];
        nums[0] = sick[0];
        nums[m] = n - sick[m - 1] - 1;
        for (int i = 1; i < m; i++) {
            nums[i] = sick[i] - sick[i - 1] - 1;
        }
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        int ans = FAC[s];
        for (int x : nums) {
            if (x > 0) {
                ans = (int) ((long) ans * qpow(FAC[x], MOD - 2) % MOD);
            }
        }
        for (int i = 1; i < nums.length - 1; ++i) {
            if (nums[i] > 1) {
                ans = (int) ((long) ans * qpow(2, nums[i] - 1) % MOD);
            }
        }
        return ans;
    }

    private int qpow(long a, long n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % MOD;
            }
            a = a * a % MOD;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
const int MX = 1e5;
const int MOD = 1e9 + 7;
int fac[MX + 1];

auto init = [] {
    fac[0] = 1;
    for (int i = 1; i <= MX; ++i) {
        fac[i] = 1LL * fac[i - 1] * i % MOD;
    }
    return 0;
}();

int qpow(long long a, long long n) {
    long long ans = 1;
    for (; n > 0; n >>= 1) {
        if (n & 1) {
            ans = (ans * a) % MOD;
        }
        a = (a * a) % MOD;
    }
    return ans;
}

class Solution {
public:
    int numberOfSequence(int n, vector<int>& sick) {
        int m = sick.size();
        vector<int> nums(m + 1);

        nums[0] = sick[0];
        nums[m] = n - sick[m - 1] - 1;
        for (int i = 1; i < m; i++) {
            nums[i] = sick[i] - sick[i - 1] - 1;
        }

        int s = accumulate(nums.begin(), nums.end(), 0);
        long long ans = fac[s];
        for (int x : nums) {
            if (x > 0) {
                ans = ans * qpow(fac[x], MOD - 2) % MOD;
            }
        }
        for (int i = 1; i < nums.size() - 1; ++i) {
            if (nums[i] > 1) {
                ans = ans * qpow(2, nums[i] - 1) % MOD;
            }
        }
        return ans;
    }
};
```

#### Go

```go
const MX = 1e5
const MOD = 1e9 + 7

var fac [MX + 1]int

func init() {
	fac[0] = 1
	for i := 1; i <= MX; i++ {
		fac[i] = fac[i-1] * i % MOD
	}
}

func qpow(a, n int) int {
	ans := 1
	for n > 0 {
		if n&1 == 1 {
			ans = (ans * a) % MOD
		}
		a = (a * a) % MOD
		n >>= 1
	}
	return ans
}

func numberOfSequence(n int, sick []int) int {
	m := len(sick)
	nums := make([]int, m+1)

	nums[0] = sick[0]
	nums[m] = n - sick[m-1] - 1
	for i := 1; i < m; i++ {
		nums[i] = sick[i] - sick[i-1] - 1
	}

	s := 0
	for _, x := range nums {
		s += x
	}
	ans := fac[s]
	for _, x := range nums {
		if x > 0 {
			ans = ans * qpow(fac[x], MOD-2) % MOD
		}
	}
	for i := 1; i < len(nums)-1; i++ {
		if nums[i] > 1 {
			ans = ans * qpow(2, nums[i]-1) % MOD
		}
	}
	return ans
}
```

#### TypeScript

```ts
const MX = 1e5;
const MOD: bigint = BigInt(1e9 + 7);
const fac: bigint[] = Array(MX + 1);

const init = (() => {
    fac[0] = 1n;
    for (let i = 1; i <= MX; ++i) {
        fac[i] = (fac[i - 1] * BigInt(i)) % MOD;
    }
    return 0;
})();

function qpow(a: bigint, n: number): bigint {
    let ans = 1n;
    for (; n > 0; n >>= 1) {
        if (n & 1) {
            ans = (ans * a) % MOD;
        }
        a = (a * a) % MOD;
    }
    return ans;
}

function numberOfSequence(n: number, sick: number[]): number {
    const m = sick.length;
    const nums: number[] = Array(m + 1);
    nums[0] = sick[0];
    nums[m] = n - sick[m - 1] - 1;
    for (let i = 1; i < m; i++) {
        nums[i] = sick[i] - sick[i - 1] - 1;
    }

    const s = nums.reduce((acc, x) => acc + x, 0);
    let ans = fac[s];
    for (let x of nums) {
        if (x > 0) {
            ans = (ans * qpow(fac[x], Number(MOD) - 2)) % MOD;
        }
    }
    for (let i = 1; i < nums.length - 1; ++i) {
        if (nums[i] > 1) {
            ans = (ans * qpow(2n, nums[i] - 1)) % MOD;
        }
    }
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
