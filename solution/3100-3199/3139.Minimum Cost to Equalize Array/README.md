---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3139.Minimum%20Cost%20to%20Equalize%20Array/README.md
rating: 2666
source: 第 396 场周赛 Q4
tags:
    - 贪心
    - 数组
    - 枚举
---

<!-- problem:start -->

# [3139. 使数组中所有元素相等的最小开销](https://leetcode.cn/problems/minimum-cost-to-equalize-array)

[English Version](/solution/3100-3199/3139.Minimum%20Cost%20to%20Equalize%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和两个整数&nbsp;<code>cost1</code> 和&nbsp;<code>cost2</code>&nbsp;。你可以执行以下&nbsp;<strong>任一</strong>&nbsp;操作&nbsp;<strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>从 <code>nums</code>&nbsp;中选择下标 <code>i</code>&nbsp;并且将 <code>nums[i]</code>&nbsp;<strong>增加</strong> <code>1</code>&nbsp;，开销为 <code>cost1</code>。</li>
	<li>选择 <code>nums</code>&nbsp;中两个 <strong>不同</strong>&nbsp;下标 <code>i</code>&nbsp;和 <code>j</code>&nbsp;，并且将 <code>nums[i]</code>&nbsp;和 <code>nums[j]</code>&nbsp;都&nbsp;<strong>增加</strong> <code>1</code>&nbsp;，开销为 <code>cost2</code>&nbsp;。</li>
</ul>

<p>你的目标是使数组中所有元素都 <strong>相等</strong>&nbsp;，请你返回需要的 <strong>最小开销</strong>&nbsp;之和。</p>

<p>由于答案可能会很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,1], cost1 = 5, cost2 = 2</span></p>

<p><span class="example-io"><b>输出：</b>15</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使数组中所有元素相等：</p>

<ul>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 1 ，开销为 5 ，<code>nums</code> 变为&nbsp;<code>[4,2]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 1 ，开销为 5 ，<code>nums</code> 变为&nbsp;<code>[4,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 1 ，开销为 5 ，<code>nums</code> 变为&nbsp;<code>[4,4]</code>&nbsp;。</li>
</ul>

<p>总开销为 15 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,3,3,3,5], cost1 = 2, cost2 = 1</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><b>解释：</b></p>

<p>执行以下操作可以使数组中所有元素相等：</p>

<ul>
	<li>将&nbsp;<code>nums[0]</code> 和&nbsp;<code>nums[1]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[3,4,3,3,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[0]</code> 和&nbsp;<code>nums[2]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[4,4,4,3,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[0]</code> 和&nbsp;<code>nums[3]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,4,4,4,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[1]</code> 和&nbsp;<code>nums[2]</code>&nbsp;同时增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,5,4,5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[3]</code>&nbsp;增加 1 ，开销为 2 ，<code>nums</code> 变为&nbsp;<code>[5,5,5,5,5]</code>&nbsp;。</li>
</ul>

<p>总开销为 6 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,5,3], cost1 = 1, cost2 = 3</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使数组中所有元素相等：</p>

<ul>
	<li>将&nbsp;<code>nums[0]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[4,5,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[0]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,3]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[2]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,4]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[2]</code>&nbsp;增加 1 ，开销为 1 ，<code>nums</code> 变为&nbsp;<code>[5,5,5]</code>&nbsp;。</li>
</ul>

<p>总开销为 4 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= cost1 &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= cost2 &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

class Solution:
    MOD = 10 ** 9 + 7

    def solve(self, k):
        sumDifferences = k * len(self.nums) - self.sumNums

        ones = max(2 * (k - self.minNums) - sumDifferences, 0)
        if (sumDifferences - ones) & 1 != 0:
            ones += 1

        return ones * self.cost1 + ((sumDifferences - ones) // 2) * self.cost2

    def minCostToEqualizeArray(self, nums: List[int], cost1: int, cost2: int) -> int:
        cost2 = min(2 * cost1, cost2)

        self.nums = nums
        self.minNums = min(nums)
        self.sumNums = sum(nums)
        self.cost1 = cost1
        self.cost2 = cost2

        m = max(nums)

        sameParity = range(m, 10 ** 18, 2)
        diffParity = range(m + 1, 10 ** 18, 2)
        i = bisect_left(sameParity, 0, key=lambda i: self.solve(i + 2) - self.solve(i))
        j = bisect_left(diffParity, 0, key=lambda j: self.solve(j + 2) - self.solve(j))

        return min(self.solve(sameParity[i]), self.solve(diffParity[j])) % Solution.MOD

```

#### Java

```java

class Solution {
        public int minCostToEqualizeArray(int[] A, int c1, int c2) {
        int ma = A[0], mi = A[0], n = A.length, mod = 1000000007;
        long total = 0;
        for (int a: A) {
            mi = Math.min(mi, a);
            ma = Math.max(ma, a);
            total += a;
        }
        total = 1l * ma * n - total;

        if (c1 * 2 <= c2 || n <= 2) {
            return (int) ((total * c1) % mod);
        }

        long op1 = Math.max(0L, (ma - mi) * 2L - total);
        long op2 = total - op1;
        long res = (op1 + op2 % 2) * c1 + op2 / 2 * c2;

        total += op1 / (n - 2) * n;
        op1 %= n - 2;
        op2 = total - op1;
        res = Math.min(res, (op1 + op2 % 2) * c1 + op2 / 2 * c2);

        for (int i = 0; i < 2; i++) {
            total += n;
            res = Math.min(res, total % 2 * c1 + total / 2 * c2);
        }
        return (int) (res % mod);
    }
}

```

#### C++

```cpp

class Solution {
public:
        int minCostToEqualizeArray(vector<int>& A, int c1, int c2) {
        int ma = *max_element(A.begin(), A.end());
        int mi = *min_element(A.begin(), A.end());
        int n = A.size(), mod = 1000000007;
        long long su = accumulate(A.begin(), A.end(), 0LL);
        long long total = 1LL * ma * n - su;

        if (c1 * 2 <= c2 || n <= 2) {
            return (total * c1) % mod;
        }

        long long op1 = max(0LL, (ma - mi) * 2 - total);
        long long op2 = total - op1;
        long long res = (op1 + op2 % 2) * c1 + op2 / 2 * c2;

        total += op1 / (n - 2) * n;
        op1 %= n - 2;
        op2 = total - op1;
        res = min(res, (op1 + op2 % 2) * c1 + op2 / 2 * c2);

        for (int i = 0; i < 2; i++) {
            total += n;
            res = min(res, total % 2 * c1 + total / 2 * c2);
        }

        return res % mod;
    }
};


```

#### Go

```go

func minCostToEqualizeArray(nums []int, cost1 int, cost2 int) int {
    mx, mod := 0, int(1e9)+7
    for _, v := range nums {
        if v > mx {
            mx = v
        }
    }
    mv, cnt := 0, 0
    for _, v := range nums {
        d := mx - v
        if cnt += d; d > mv {
            mv = d
        }
    }
    ans := find(mv, cnt, cost1, cost2)
    if len(nums) <= 2 {
        return ans % mod
    }
    for (mv<<1) > cnt {
        mv, cnt = mv + 1, cnt + len(nums)
        ans = min(ans, find(mv, cnt, cost1, cost2))
    }
    mv, cnt = mv + 1, cnt + len(nums)
    ans = min(ans, find(mv, cnt, cost1, cost2))
    return ans % mod
}

func find(mv, cnt, c1, c2 int) int {
    switch {
    case c1<<1 <= c2:
        return cnt * c1
    case mv<<1 <= cnt:
        return (cnt>>1)*c2 + (cnt&1)*c1
    }
    return (cnt-mv)*c2 + (mv<<1-cnt)*c1
}

func min(x, y int) int {
    if x < y {
        return x
    }
    return y
}


```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
