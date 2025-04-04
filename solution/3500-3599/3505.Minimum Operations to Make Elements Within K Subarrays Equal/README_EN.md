---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3505.Minimum%20Operations%20to%20Make%20Elements%20Within%20K%20Subarrays%20Equal/README_EN.md
tags:
    - Array
    - Hash Table
    - Math
    - Dynamic Programming
    - Sliding Window
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3505. Minimum Operations to Make Elements Within K Subarrays Equal](https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal)

[中文文档](/solution/3500-3599/3505.Minimum%20Operations%20to%20Make%20Elements%20Within%20K%20Subarrays%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers, <code>x</code> and <code>k</code>. You can perform the following operation any number of times (<strong>including zero</strong>):</p>

<ul>
	<li>Increase or decrease any element of <code>nums</code> by 1.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations needed to have <strong>at least</strong> <code>k</code> <em>non-overlapping <span data-keyword="subarray-nonempty">subarrays</span></em> of size <strong>exactly</strong> <code>x</code> in <code>nums</code>, where all elements within each subarray are equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,-2,1,3,7,3,6,4,-1], x = 3, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Use 3 operations to add 3 to <code>nums[1]</code> and use 2 operations to subtract 2 from <code>nums[3]</code>. The resulting array is <code>[5, 1, 1, 1, 7, 3, 6, 4, -1]</code>.</li>
	<li>Use 1 operation to add 1 to <code>nums[5]</code> and use 2 operations to subtract 2 from <code>nums[6]</code>. The resulting array is <code>[5, 1, 1, 1, 7, 4, 4, 4, -1]</code>.</li>
	<li>Now, all elements within each subarray <code>[1, 1, 1]</code> (from indices 1 to 3) and <code>[4, 4, 4]</code> (from indices 5 to 7) are equal. Since 8 total operations were used, 8 is the output.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,-2,-2,-2,1,5], x = 2, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Use 3 operations to subtract 3 from <code>nums[4]</code>. The resulting array is <code>[9, -2, -2, -2, -2, 5]</code>.</li>
	<li>Now, all elements within each subarray <code>[-2, -2]</code> (from indices 1 to 2) and <code>[-2, -2]</code> (from indices 3 to 4) are equal. Since 3 operations were used, 3 is the output.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>2 &lt;= x &lt;= nums.length</code></li>
	<li><code>1 &lt;= k &lt;= 15</code></li>
	<li><code>2 &lt;= k * x &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python


from sortedcontainers import SortedList as SL
fmin = lambda a, b: a if a < b else b
class Solution:
    def minOperations(self, nums: List[int], l: int, k: int) -> int:
        n = len(nums)
        lt, gt = SL(), SL()
        ls, gs = 0, 0
        def add(x):
            nonlocal ls, gs
            if not lt or x <= lt[-1]:
                lt.add(x)
                ls += x
            else:
                gt.add(x)
                gs += x
        def remove(x):
            nonlocal ls, gs
            if x <= lt[-1]:
                lt.remove(x)
                ls -= x
            else:
                gt.remove(x)
                gs -= x
        def adjust():
            nonlocal ls, gs
            if len(lt) - len(gt) > 1:
                x = lt.pop(-1)
                ls -= x
                gt.add(x)
                gs += x
            elif len(gt) - len(lt) > 0:
                x = gt.pop(0)
                gs -= x
                lt.add(x)
                ls += x
        def getmed():
            return lt[-1]
        cost = [0] * (n-l+1)
        for i, x in enumerate(nums):
            add(x)
            adjust()
            if i >= l:
                remove(nums[i-l])
            adjust()
            if i >= l-1:
                med = getmed()
                cur = gs - med*(len(gt)) + med*len(lt) - ls
                cost[i-l+1] = cur
        # print(cost)
        dp = [[inf] * (k+1) for _ in range(n+1)]
        dp[0][0] = 0
        for i in range(1, n+1):
            for j in range(k+1):
                dp[i][j] = dp[i-1][j]
                if i-l >= 0 and k > 0:
                    dp[i][j] = fmin(dp[i][j], dp[i-l][j-1] + cost[i-l])

        return dp[n][k]

		
```

#### Java

```java


	class Solution {

    public long minOperations(int[] nums, int x, int k) {
        TreeSet<Integer> set1 = new TreeSet<>((o, p) -> nums[o] == nums[p] ? o - p : nums[o] - nums[p]), set2 = new TreeSet<>((o, p) -> nums[o] == nums[p] ? o - p : nums[o] - nums[p]);
        long sum[] = new long[nums.length - x + 1], left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            set2.add(i);
            left += nums[set2.first()];
            right += nums[i] - nums[set2.first()];
            set1.add(set2.pollFirst());
            if (set1.size() > set2.size()) {
                left -= nums[set1.last()];
                right += nums[set1.last()];
                set2.add(set1.pollLast());
            }
            if (i >= x - 1) {
                sum[i - x + 1] = nums[set2.first()] * (set1.size() - set2.size()) - left + right;
                left -= set1.remove(i - x + 1) ? nums[i - x + 1] : 0;
                right -= set2.remove(i - x + 1) ? nums[i - x + 1] : 0;
            }
        }
        long[][] dp = new long[sum.length + x][k + 1];
        for (int i = 0; i < sum.length + x; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = 1000000000000000000L;
            }
        }
        for (int i = 0; i < sum.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i + x][j] = Math.min(dp[i + x - 1][j], sum[i] + dp[i][j - 1]);
            }
        }
        return dp[sum.length + x - 1][k];
    }
}


```

#### C++

```cpp


	class Solution
{
public:
#define ll long long
    ll f[100003][23];
    long long minOperations(vector<int> &a, int x, int k)
    {
        int n = a.size();
        for (int j = 0; j <= n; ++j)
            for (int i = 0; i <= k; ++i)
                f[j][i] = 1e18;
        f[0][0] = 0;
        multiset<int> A, B;
        ll as = 0, bs = 0;
        for (int i = 0; i < n; ++i)
        {
            if (B.empty())
                as += a[i], A.insert(a[i]);
            else if (A.empty())
                bs += a[i], B.insert(a[i]);
            else if (a[i] <= *A.rbegin())
                as += a[i], A.insert(a[i]);
            else
                bs += a[i], B.insert(a[i]);
            if (i - x >= 0)
            {
                if (A.find(a[i - x]) != A.end())
                {
                    as -= a[i - x], A.erase(A.find(a[i - x]));
                }
                else
                {
                    bs -= a[i - x], B.erase(B.find(a[i - x]));
                }
            }
            int sa = A.size(), sb = B.size();
            while (sa - sb >= 2)
            {
                int o = *A.rbegin();
                as -= o, bs += o;
                B.insert(*A.rbegin()), A.erase(prev(A.end()));
                --sa, ++sb;
            }
            while (sa < sb)
            {
                int o = *B.begin();
                bs -= o, as += o;
                A.insert(*B.begin()), B.erase(B.begin());
                ++sa, --sb;
            }
            // 3 2
            // 3 3
            for (int j = 0; j <= k; ++j)
                f[i + 1][j] = f[i][j];
            if (i >= x - 1)
            {
                ll Z = *A.rbegin();
                ll cost = Z * A.size() - as + bs - Z * B.size();
                for (int j = 1; j <= k; ++j)
                    f[i + 1][j] = min(f[i + 1][j], f[i - x + 1][j - 1] + cost);
            }
        }
        return f[n][k];
    }
};


```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
