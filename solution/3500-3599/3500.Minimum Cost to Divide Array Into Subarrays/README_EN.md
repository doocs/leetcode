---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3500.Minimum%20Cost%20to%20Divide%20Array%20Into%20Subarrays/README_EN.md
rating: 2569
source: Biweekly Contest 153 Q3
tags:
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3500. Minimum Cost to Divide Array Into Subarrays](https://leetcode.com/problems/minimum-cost-to-divide-array-into-subarrays)

[中文文档](/solution/3500-3599/3500.Minimum%20Cost%20to%20Divide%20Array%20Into%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>nums</code> and <code>cost</code>, of the same size, and an integer <code>k</code>.</p>

<p>You can divide <code>nums</code> into <span data-keyword="subarray-nonempty">subarrays</span>. The cost of the <code>i<sup>th</sup></code> subarray consisting of elements <code>nums[l..r]</code> is:</p>

<ul>
	<li><code>(nums[0] + nums[1] + ... + nums[r] + k * i) * (cost[l] + cost[l + 1] + ... + cost[r])</code>.</li>
</ul>

<p><strong>Note</strong> that <code>i</code> represents the order of the subarray: 1 for the first subarray, 2 for the second, and so on.</p>

<p>Return the <strong>minimum</strong> total cost possible from any valid division.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,4], cost = [4,6,6], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">110</span></p>

<p><strong>Explanation:</strong></p>
The minimum total cost possible can be achieved by dividing <code>nums</code> into subarrays <code>[3, 1]</code> and <code>[4]</code>.

<ul>
	<li>The cost of the first subarray <code>[3,1]</code> is <code>(3 + 1 + 1 * 1) * (4 + 6) = 50</code>.</li>
	<li>The cost of the second subarray <code>[4]</code> is <code>(3 + 1 + 4 + 1 * 2) * 6 = 60</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,8,5,1,14,2,2,12,1], cost = [7,2,8,4,2,2,1,1,2], k = 7</span></p>

<p><strong>Output:</strong> 985</p>

<p><strong>Explanation:</strong></p>
The minimum total cost possible can be achieved by dividing <code>nums</code> into subarrays <code>[4, 8, 5, 1]</code>, <code>[14, 2, 2]</code>, and <code>[12, 1]</code>.

<ul>
	<li>The cost of the first subarray <code>[4, 8, 5, 1]</code> is <code>(4 + 8 + 5 + 1 + 7 * 1) * (7 + 2 + 8 + 4) = 525</code>.</li>
	<li>The cost of the second subarray <code>[14, 2, 2]</code> is <code>(4 + 8 + 5 + 1 + 14 + 2 + 2 + 7 * 2) * (2 + 2 + 1) = 250</code>.</li>
	<li>The cost of the third subarray <code>[12, 1]</code> is <code>(4 + 8 + 5 + 1 + 14 + 2 + 2 + 12 + 1 + 7 * 3) * (1 + 2) = 210</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>cost.length == nums.length</code></li>
	<li><code>1 &lt;= nums[i], cost[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

class Solution:
    def minimumCost(self, nums: List[int], cost: List[int], k: int) -> int:
        n = len(nums)
        sumN = [0] * (n + 1)
        sumC = [0] * (n + 1)

        for i in range(1, n + 1):
            sumN[i] = sumN[i - 1] + nums[i - 1]
            sumC[i] = sumC[i - 1] + cost[i - 1]

        prevDp = [sys.maxsize] * (n + 1)
        prevDp[0] = 0

        minCost = sys.maxsize

        for m in range(1, n + 1):
            currentDp = [sys.maxsize] * (n + 1)
            queue = []

            for r in range(m, n + 1):
                l = r - 1
                if l >= m - 1:
                    x = sumC[l]
                    y = prevDp[l]

                    while len(queue) >= 2:
                        x1, y1 = queue[-2][0], queue[-2][1]
                        x2, y2 = queue[-1][0], queue[-1][1]
                        if (x2 - x1) * (y - y2) <= (x - x2) * (y2 - y1):
                            queue.pop()
                        else:
                            break
                    queue.append((x, y, l))

                a = -(sumN[r] + k * m)

                while len(queue) >= 2:
                    x1, y1 = queue[0][0], queue[0][1]
                    x2, y2 = queue[1][0], queue[1][1]
                    if a * x1 + y1 >= a * x2 + y2:
                        queue.pop(0)
                    else:
                        break

                if queue:
                    x, y = queue[0][0], queue[0][1]
                    best = a * x + y
                    currentCost = (sumN[r] + k * m) * sumC[r]
                    currentCost += best
                    if currentCost < currentDp[r]:
                        currentDp[r] = currentCost

            if currentDp[n] < minCost:
                minCost = currentDp[n]
            prevDp = currentDp

        return minCost

```

#### Java

```java

class Solution {
    public long minimumCost(int[] nums, int[] cost, int k) {
        int n = nums.length;
        long[] prefixNums = new long[n];
        long[] prefixCosts = new long[n];
        prefixNums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixNums[i] = prefixNums[i - 1] + nums[i];
        }

        prefixCosts[0] = cost[0];
        for (int i = 1; i < n; i++) {
            prefixCosts[i] = prefixCosts[i - 1] + cost[i];
        }

        Long[][] dp = new Long[n][n];
        long ans = solve(0, 0, k, prefixNums, prefixCosts, dp);
        return ans;
    }

    public long solve(int start, int end, int k, long[] prefixNums, long[] prefixCosts, Long[][] dp) {
        int n = prefixNums.length;
        if (end == n) {
            if (start == n) return 0;
            return Long.MAX_VALUE;
        }

        if (dp[start][end] != null) return dp[start][end];

        long currentNumsSum = prefixNums[end], currentCostSum = prefixCosts[n - 1];

        if (start != 0){
            currentNumsSum = prefixNums[end] - prefixNums[start - 1];
            currentCostSum = prefixCosts[n - 1] - prefixCosts[start - 1];
        }

        long currentSubarrayCost = (currentNumsSum + k) * currentCostSum;

        long costIfCutHere = currentSubarrayCost + solve(end + 1, end + 1, k, prefixNums, prefixCosts, dp);
        long costIfExtend = solve(start, end + 1, k, prefixNums, prefixCosts, dp);

        return dp[start][end] = Math.min(costIfCutHere, costIfExtend);
    }
}

```

#### C++

```cpp

class Solution {
    public:
        long long minimumCost(vector<int>& nums, vector<int>& cost, int K) {
            int n = nums.size();

            long long sn[n + 1], sc[n + 1];
            sn[0] = sc[0] = 0;
            for (int i = 1; i <= n; i++) {
                sn[i] = sn[i - 1] + nums[i - 1];
                sc[i] = sc[i - 1] + cost[i - 1];
            }

            const long long INF = 1e18;
            long long f[n + 1];
            for (int i = 0; i <= n; i++) f[i] = INF;
            f[0] = 0;
            for (int i = 1; i <= n; i++) for (int j = 0; j < i; j++) {
                long long t = sn[i] * (sc[i] - sc[j]) + K * (sc[n] - sc[j]);
                f[i] = min(f[i], f[j] + t);
            }

            return f[n];
        }
    };

```

#### Go

```go

func minimumCost(a []int, b []int, k int) int64 {
    n := len(a)
    dp := make([]int, n + 1)
    for i := range n {
        dp[i] = math.MaxInt / 4
    }
    prea := make([]int, n + 1)
    preb := make([]int, n + 1)
    for i := range n {
        prea[i + 1] = prea[i] + a[i]
        preb[i + 1] = preb[i] + b[i]
    }
    for i := n - 1; i >= 0; i-- {
        for j := i; j < n; j++ {
            tot := (preb[j + 1] - preb[i]) * (prea[j + 1] - prea[0]) + (preb[n] - preb[i]) * k
            dp[i] = min(dp[i], tot + dp[j + 1])
        }
    }
    return int64(dp[0])
}

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
