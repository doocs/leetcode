# [2954. 统计感冒序列的数目](https://leetcode.cn/problems/count-the-number-of-infection-sequences)

[English Version](/solution/2900-2999/2954.Count%20the%20Number%20of%20Infection%20Sequences/README_EN.md)

<!-- tags:数组,数学,组合数学 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>sick</code>&nbsp;，数组按 <strong>升序</strong>&nbsp;排序。</p>

<p>有&nbsp;<code>n</code>&nbsp;位小朋友站成一排，按顺序编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;。数组&nbsp;<code>sick</code>&nbsp;包含一开始得了感冒的小朋友的位置。如果位置为&nbsp;<code>i</code>&nbsp;的小朋友得了感冒，他会传染给下标为 <code>i - 1</code>&nbsp;或者 <code>i + 1</code>&nbsp;的小朋友，<strong>前提</strong> 是被传染的小朋友存在且还没有得感冒。每一秒中， <strong>至多一位</strong>&nbsp;还没感冒的小朋友会被传染。</p>

<p>经过有限的秒数后，队列中所有小朋友都会感冒。<strong>感冒序列</strong>&nbsp;指的是 <strong>所有</strong>&nbsp;一开始没有感冒的小朋友最后得感冒的顺序序列。请你返回所有感冒序列的数目。</p>

<p>由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p><b>注意</b>，感冒序列 <strong>不</strong> 包含一开始就得了感冒的小朋友的下标。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 5, sick = [0,4]
<b>输出：</b>4
<b>解释：</b>一开始，下标为 1 ，2 和 3 的小朋友没有感冒。总共有 4 个可能的感冒序列：
- 一开始，下标为 1 和 3 的小朋友可以被传染，因为他们分别挨着有感冒的小朋友 0 和 4 ，令下标为 1 的小朋友先被传染。
然后，下标为 2 的小朋友挨着感冒的小朋友 1 ，下标为 3 的小朋友挨着感冒的小朋友 4 ，两位小朋友都可以被传染，令下标为 2 的小朋友被传染。
最后，下标为 3 的小朋友被传染，因为他挨着感冒的小朋友 2 和 4 ，感冒序列为 [1,2,3] 。
- 一开始，下标为 1 和 3 的小朋友可以被传染，因为他们分别挨着感冒的小朋友 0 和 4 ，令下标为 1 的小朋友先被传染。
然后，下标为 2 的小朋友挨着感冒的小朋友 1 ，下标为 3 的小朋友挨着感冒的小朋友 4 ，两位小朋友都可以被传染，令下标为 3 的小朋友被传染。
最后，下标为 2 的小朋友被传染，因为他挨着感冒的小朋友 1 和 3 ，感冒序列为  [1,3,2] 。
- 感冒序列 [3,1,2] ，被传染的顺序：[<strong><em>0</em></strong>,1,2,3,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,1,2,<strong><em>3</em></strong>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,2,<em><strong>3</strong></em>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>4</em></strong>] 。
- 感冒序列 [3,2,1] ，被传染的顺序：[<strong><em>0</em></strong>,1,2,3,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,1,2,<strong><em>3</em></strong>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,1,<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>4</em></strong>] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 4, sick = [1]
<b>输出：</b>3
<b>解释：</b>一开始，下标为 0 ，2 和 3 的小朋友没有感冒。总共有 3 个可能的感冒序列：
- 感冒序列 [0,2,3] ，被传染的顺序：[0,<strong><em>1</em></strong>,2,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,2,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] 。
- 感冒序列 [2,0,3] ，被传染的顺序：[0,<strong><em>1</em></strong>,2,3] =&gt; [0,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] 。
- 感冒序列 [2,3,0] ，被传染的顺序：[0,<strong><em>1</em></strong>,2,3] =&gt; [0,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [0,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sick.length &lt;= n - 1</code></li>
	<li><code>0 &lt;= sick[i] &lt;= n - 1</code></li>
	<li><code>sick</code>&nbsp;按升序排列。</li>
</ul>

## 解法

### 方法一：组合数学 + 乘法逆元 + 快速幂

根据题目描述，感冒的小朋友把还没有感冒的小朋友划分成了若干个连续段。我们可以用一个数组 $nums$ 记录每一段不感冒的小朋友的认识，不感冒人数一共有 $s = \sum_{i=0}^{k} nums[k]$ 人。我们可以发现，感冒序列的数目就是 $s$ 个不同元素的全排列数，即 $s!$。

假设每一段不感冒小朋友只有一种传播方案，那么一共有 $\frac{s!}{\prod_{i=0}^{k} nums[k]!}$ 种感冒序列。

接下来，我们再考虑每一段不感冒小朋友的传播方案。假设一段不感冒小朋友有 $x$ 个人，那么他们的传播方案有 $2^{x-1}$ 种，因为每一次可以从一段的左右两端选择其中一端传播，即：两种选择，一共有 $x-1$ 次传播。不过，如果是第一段或者最后一段，那么只有一种选择。

综上所述，总的感冒序列数目为：

$$
\frac{s!}{\prod_{i=0}^{k} nums[k]!} \prod_{i=1}^{k-1} 2^{nums[i]-1}
$$

最后，我们需要考虑到答案可能很大，需要对 $10^9 + 7$ 取模。因此，我们需要预处理阶乘和乘法逆元。

时间复杂度 $O(m)$，其中 $m$ 是数组 $sick$ 的长度。忽略预处理数组的空间消耗，空间复杂度 $O(m)$。

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
