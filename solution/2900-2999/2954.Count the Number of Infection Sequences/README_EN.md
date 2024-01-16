# [2954. Count the Number of Infection Sequences](https://leetcode.com/problems/count-the-number-of-infection-sequences)

[中文文档](/solution/2900-2999/2954.Count%20the%20Number%20of%20Infection%20Sequences/README.md)

## Description

<p>You are given an integer <code>n</code> and a <strong>0-indexed</strong><strong> </strong>integer array <code>sick</code> which is <strong>sorted</strong> in <strong>increasing</strong> order.</p>

<p>There are <code>n</code> children standing in a queue with positions <code>0</code> to <code>n - 1</code> assigned to them. The array <code>sick</code> contains the positions of the children who are infected with an infectious disease. An infected child at position <code>i</code> can spread the disease to either of its immediate neighboring children at positions <code>i - 1</code> and <code>i + 1</code> <strong>if</strong> they exist and are currently not infected. <strong>At most one</strong> child who was previously not infected can get infected with the disease in one second.</p>

<p>It can be shown that after a finite number of seconds, all the children in the queue will get infected with the disease. An <strong>infection sequence</strong> is the sequential order of positions in which <strong>all</strong> of the non-infected children get infected with the disease. Return <em>the total number of possible infection sequences</em>.</p>

<p>Since the answer may be large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong> that an infection sequence <strong>does not</strong> contain positions of children who were already infected with the disease in the beginning.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, sick = [0,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Children at positions 1, 2, and 3 are not infected in the beginning. There are 4 possible infection sequences:
- The children at positions 1 and 3 can get infected since their positions are adjacent to the infected children 0 and 4. The child at position 1 gets infected first.
Now, the child at position 2 is adjacent to the child at position 1 who is infected and the child at position 3 is adjacent to the child at position 4 who is infected, hence either of them can get infected. The child at position 2 gets infected.
Finally, the child at position 3 gets infected because it is adjacent to children at positions 2 and 4 who are infected. The infection sequence is [1,2,3].
- The children at positions 1 and 3 can get infected because their positions are adjacent to the infected children 0 and 4. The child at position 1 gets infected first.
Now, the child at position 2 is adjacent to the child at position 1 who is infected and the child at position 3 is adjacent to the child at position 4 who is infected, hence either of them can get infected. The child at position 3 gets infected.
Finally, the child at position 2 gets infected because it is adjacent to children at positions 1 and 3 who are infected. The infection sequence is [1,3,2].
- The infection sequence is [3,1,2]. The order of infection of disease in the children can be seen as: [<u>0</u>,1,2,3,<u>4</u>] =&gt; [<u>0</u>,1,2,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,<u>1</u>,2,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>].
- The infection sequence is [3,2,1]. The order of infection of disease in the children can be seen as: [<u>0</u>,1,2,3,<u>4</u>] =&gt; [<u>0</u>,1,2,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,1,<u>2</u>,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, sick = [1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Children at positions 0, 2, and 3 are not infected in the beginning. There are 3 possible infection sequences:
- The infection sequence is [0,2,3]. The order of infection of disease in the children can be seen as: [0,<u>1</u>,2,3] =&gt; [<u>0</u>,<u>1</u>,2,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>].
- The infection sequence is [2,0,3]. The order of infection of disease in the children can be seen as: [0,<u>1</u>,2,3] =&gt; [0,<u>1</u>,<u>2</u>,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>].
- The infection sequence is [2,3,0]. The order of infection of disease in the children can be seen as: [0,<u>1</u>,2,3] =&gt; [0,<u>1</u>,<u>2</u>,3] =&gt; [0,<u>1</u>,<u>2</u>,<u>3</u>] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sick.length &lt;= n - 1</code></li>
	<li><code>0 &lt;= sick[i] &lt;= n - 1</code></li>
	<li><code>sick</code> is sorted in increasing order.</li>
</ul>

## Solutions

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

<!-- end -->
