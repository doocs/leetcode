---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2172.Maximum%20AND%20Sum%20of%20Array/README.md
rating: 2392
source: 第 280 场周赛 Q4
tags:
    - 位运算
    - 数组
    - 动态规划
    - 状态压缩
---

<!-- problem:start -->

# [2172. 数组的最大与和](https://leetcode.cn/problems/maximum-and-sum-of-array)

[English Version](/solution/2100-2199/2172.Maximum%20AND%20Sum%20of%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>numSlots</code>&nbsp;，满足<code>2 * numSlots &gt;= n</code>&nbsp;。总共有&nbsp;<code>numSlots</code>&nbsp;个篮子，编号为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>numSlots</code>&nbsp;。</p>

<p>你需要把所有&nbsp;<code>n</code>&nbsp;个整数分到这些篮子中，且每个篮子 <strong>至多</strong>&nbsp;有 2 个整数。一种分配方案的 <strong>与和</strong>&nbsp;定义为每个数与它所在篮子编号的 <strong>按位与运算</strong>&nbsp;结果之和。</p>

<ul>
	<li>比方说，将数字&nbsp;<code>[1, 3]</code>&nbsp;放入篮子&nbsp;<strong><em><code>1</code></em></strong>&nbsp;中，<code>[4, 6]</code> 放入篮子&nbsp;<strong><em><code>2</code></em></strong>&nbsp;中，这个方案的与和为&nbsp;<code>(1 AND <strong><em>1</em></strong>) + (3 AND <strong><em>1</em></strong>) + (4 AND <em><strong>2</strong></em>) + (6 AND <em><strong>2</strong></em>) = 1 + 1 + 0 + 2 = 4</code>&nbsp;。</li>
</ul>

<p>请你返回将 <code>nums</code>&nbsp;中所有数放入<em>&nbsp;</em><code>numSlots</code>&nbsp;个篮子中的最大与和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4,5,6], numSlots = 3
<b>输出：</b>9
<b>解释：</b>一个可行的方案是 [1, 4] 放入篮子 <em><strong>1</strong></em>&nbsp;中，[2, 6] 放入篮子 <strong><em>2</em></strong>&nbsp;中，[3, 5] 放入篮子 <strong><em>3</em></strong> 中。
最大与和为 (1 AND <strong><em>1</em></strong>) + (4 AND <strong><em>1</em></strong>) + (2 AND <strong><em>2</em></strong>) + (6 AND <strong><em>2</em></strong>) + (3 AND <strong><em>3</em></strong>) + (5 AND <em><strong>3</strong></em>) = 1 + 0 + 2 + 2 + 3 + 1 = 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,3,10,4,7,1], numSlots = 9
<b>输出：</b>24
<b>解释：</b>一个可行的方案是 [1, 1] 放入篮子 <em><strong>1</strong></em> 中，[3] 放入篮子 <em><strong>3</strong></em> 中，[4] 放入篮子 <strong><em>4</em></strong> 中，[7] 放入篮子 <strong><em>7</em></strong> 中，[10] 放入篮子 <strong><em>9</em></strong>&nbsp;中。
最大与和为 (1 AND <strong><em>1</em></strong>) + (1 AND <strong><em>1</em></strong>) + (3 AND <strong><em>3</em></strong>) + (4 AND <strong><em>4</em></strong>) + (7 AND <strong><em>7</em></strong>) + (10 AND <strong><em>9</em></strong>) = 1 + 1 + 3 + 4 + 7 + 8 = 24 。
注意，篮子 2 ，5 ，6 和 8 是空的，这是允许的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= numSlots &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 2 * numSlots</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 15</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 动态规划

由于每个篮子最多只能放两个数，我们不妨将篮子数乘以 $2$，这样每个篮子最多只能放一个数。

接下来，我们定义 $f[i]$ 表示篮子状态为 $i$ 时的最大与和，其中 $i$ 是一个二进制数，表示每个篮子是否放了数。初始时 $f[0]=0$。

接下来，我们考虑 $f[i]$ 如何进行状态转移。

我们可以枚举 $i$，记 $i$ 的二进制表示中 $1$ 的个数为 $cnt$。如果 $cnt \gt n$，那么 $i$ 不是一个合法的状态，我们可以直接跳过。否则，我们可以枚举 $i$ 的二进制表示中的每一位 $j$，如果 $i$ 的第 $j$ 位为 $1$，那么我们可以将第 $(cnt-1)$ 个数 $nums[cnt-1]$ 放入第 $j$ 个篮子中，此时有：

$$
f[i] = \max\{f[i], f[i \oplus (1 << j)] + (nums[cnt-1] \wedge  (j / 2 + 1))\}
$$

其中 $\oplus$ 表示异或运算，而 $\wedge$ 表示按位与运算。

答案为 $\max\{f[i]\}$。

时间复杂度 $O(4^k \times k \times 2)$，空间复杂度 $O(4^k)$。其中 $k$ 表示篮子的数量，即题目中的 $numSlots$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumANDSum(self, nums: List[int], numSlots: int) -> int:
        n = len(nums)
        m = numSlots << 1
        f = [0] * (1 << m)
        for i in range(1 << m):
            cnt = i.bit_count()
            if cnt > n:
                continue
            for j in range(m):
                if i >> j & 1:
                    f[i] = max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j // 2 + 1)))
        return max(f)
```

#### Java

```java
class Solution {
    public int maximumANDSum(int[] nums, int numSlots) {
        int n = nums.length;
        int m = numSlots << 1;
        int[] f = new int[1 << m];
        int ans = 0;
        for (int i = 0; i < 1 << m; ++i) {
            int cnt = Integer.bitCount(i);
            if (cnt > n) {
                continue;
            }
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j / 2 + 1)));
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumANDSum(vector<int>& nums, int numSlots) {
        int n = nums.size();
        int m = numSlots << 1;
        int f[1 << m];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < 1 << m; ++i) {
            int cnt = __builtin_popcount(i);
            if (cnt > n) {
                continue;
            }
            for (int j = 0; j < m; ++j) {
                if (i >> j & 1) {
                    f[i] = max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & (j / 2 + 1)));
                }
            }
        }
        return *max_element(f, f + (1 << m));
    }
};
```

#### Go

```go
func maximumANDSum(nums []int, numSlots int) int {
	n := len(nums)
	m := numSlots << 1
	f := make([]int, 1<<m)
	for i := range f {
		cnt := bits.OnesCount(uint(i))
		if cnt > n {
			continue
		}
		for j := 0; j < m; j++ {
			if i>>j&1 == 1 {
				f[i] = max(f[i], f[i^(1<<j)]+(nums[cnt-1]&(j/2+1)))
			}
		}
	}
	return slices.Max(f)
}
```

#### TypeScript

```ts
function maximumANDSum(nums: number[], numSlots: number): number {
    const n = nums.length;
    const m = numSlots << 1;
    const f: number[] = new Array(1 << m).fill(0);
    for (let i = 0; i < 1 << m; ++i) {
        const cnt = i
            .toString(2)
            .split('')
            .filter(c => c === '1').length;
        if (cnt > n) {
            continue;
        }
        for (let j = 0; j < m; ++j) {
            if (((i >> j) & 1) === 1) {
                f[i] = Math.max(f[i], f[i ^ (1 << j)] + (nums[cnt - 1] & ((j >> 1) + 1)));
            }
        }
    }
    return Math.max(...f);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
