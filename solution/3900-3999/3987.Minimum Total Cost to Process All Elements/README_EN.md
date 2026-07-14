---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3987.Minimum%20Total%20Cost%20to%20Process%20All%20Elements/README_EN.md
---

<!-- problem:start -->

# [3987. Minimum Total Cost to Process All Elements](https://leetcode.com/problems/minimum-total-cost-to-process-all-elements)

[中文文档](/solution/3900-3999/3987.Minimum%20Total%20Cost%20to%20Process%20All%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Initially, you have <code>k</code> units of resources.</p>

<p>You must process the elements of <code>nums</code> from left to right. To process the <code>i<sup>th</sup></code> element, you need <code>nums[i]</code> resources.</p>

<p>If your available resources are less than <code>nums[i]</code>, you may perform an operation that increases your available resources by <code>k</code>. The value of <code>k</code> is fixed and does not change throughout the process. The first such operation incurs a cost of 1, the second incurs a cost of 2, and so on.</p>

<p>After processing the <code>i<sup>th</sup></code> element, your available resources decrease by <code>nums[i]</code>.</p>

<p>Return an integer denoting the <strong>minimum total cost</strong> required to process all elements. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After processing <code>nums[0]</code>, we have <code>4 - 1 = 3</code> units of resources left.</li>
	<li>After processing <code>nums[1]</code>, we have <code>3 - 2 = 1</code> unit of resources left.</li>
	<li>Since <code>nums[2] = 3</code> and only 1 unit of resources is available, we perform the first operation costing 1. After processing <code>nums[2]</code>, we have <code>1 + 4 - 3 = 2</code> units of resources left.</li>
	<li>Since <code>nums[3] = 4</code> and only 2 units of resources are available, we perform the second operation costing 2, to have <code>2 + 4 = 6</code> units of resources,&nbsp;which is enough to&nbsp;process <code>nums[3]</code>.</li>
	<li>Thus, the total cost is <code>1 + 2 = 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,7,14], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After processing <code>nums[0]</code>, we have <code>4 - 1 = 3</code> units of resources left.</li>
	<li>After processing <code>nums[1]</code>, we have <code>3 - 1 = 2</code> units of resources left.</li>
	<li>Since <code>nums[2] = 7</code> and only 2 units of resources are available, we perform two operations costing <code>1 + 2 = 3</code>. After processing <code>nums[2]</code>, we have <code>2 + 4 + 4 - 7 = 3</code> units of resources left.</li>
	<li>Since <code>nums[3] = 14</code> and only 3 units of resources are available, we perform three operations costing <code>3 + 4 + 5 = 12</code>, to have <code>3 + 4 + 4 + 4 = 15</code> units of resources,&nbsp;which is enough to&nbsp;process <code>nums[3]</code>.</li>
	<li>Thus, the total cost is <code>3 + 12 = 15</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], k = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>To process all elements, we can use the initial 10 units of resources without performing any operations. Thus, the total cost required is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

The $i$-th operation costs $i$, so if we perform $\textit{cnt}$ operations in total, the total cost is $1 + 2 + \cdots + \textit{cnt} = \dfrac{\textit{cnt}(\textit{cnt}+1)}{2}$. Minimizing the total cost is equivalent to minimizing the number of operations.

Simulate the process from left to right. Maintain the current available resources $\textit{cur}$ (initially $k$) and the number of operations performed $\textit{cnt}$. When processing an element $x$:

- If $\textit{cur} \ge x$, simply subtract $x$;
- Otherwise, perform $m = \left\lceil\dfrac{x - \textit{cur}}{k}\right\rceil$ more operations to increase resources by $m \times k$, then subtract $x$.

After the traversal, compute the triangular number of $\textit{cnt}$ and take the result modulo $10^9 + 7$.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(self, nums: list[int], k: int) -> int:
        cnt = 0
        cur = k
        mod = 10**9 + 7
        for x in nums:
            diff = x - cur
            if diff > 0:
                m = (diff + k - 1) // k
                cur += m * k
                cnt += m
            cur -= x
        cnt %= mod
        return (1 + cnt) * cnt // 2 % mod
```

#### Java

```java
class Solution {
    public int minimumCost(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        long cnt = 0;
        long cur = k;

        for (int x : nums) {
            long diff = (long) x - cur;
            if (diff > 0) {
                long m = (diff + k - 1L) / k;
                cur += m * (long) k;
                cnt += m;
            }
            cur -= x;
        }

        cnt %= MOD;
        return (int) ((cnt + 1) * cnt / 2 % MOD);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumCost(vector<int>& nums, int k) {
        const int MOD = 1'000'000'007;
        long long cnt = 0;
        long long cur = k;

        for (int x : nums) {
            long long diff = (long long) x - cur;
            if (diff > 0) {
                long long m = (diff + k - 1LL) / k;
                cur += m * k;
                cnt += m;
            }
            cur -= x;
        }

        cnt %= MOD;
        return (cnt + 1) * cnt / 2 % MOD;
    }
};
```

#### Go

```go
func minimumCost(nums []int, k int) int {
	const mod int64 = 1_000_000_007
	var cnt int64
	cur := int64(k)

	for _, x := range nums {
		diff := int64(x) - cur
		if diff > 0 {
			m := (diff + int64(k) - 1) / int64(k)
			cur += m * int64(k)
			cnt += m
		}
		cur -= int64(x)
	}

	cnt %= mod
	return int((cnt + 1) * cnt / 2 % mod)
}
```

#### TypeScript

```ts
function minimumCost(nums: number[], k: number): number {
    const MOD = 1000000007n;
    let cnt = 0n;
    let cur = BigInt(k);
    const K = BigInt(k);

    for (const x of nums) {
        const diff = BigInt(x) - cur;
        if (diff > 0n) {
            const m = (diff + K - 1n) / K;
            cur += m * K;
            cnt += m;
        }
        cur -= BigInt(x);
    }

    cnt %= MOD;
    return Number((((cnt + 1n) * cnt) / 2n) % MOD);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
