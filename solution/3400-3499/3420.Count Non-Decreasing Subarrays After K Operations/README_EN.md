---
comments: true
difficulty: Hard
rating: 2854
source: Weekly Contest 432 Q4
tags:
    - Stack
    - Segment Tree
    - Queue
    - Array
    - Sliding Window
    - Monotonic Queue
    - Monotonic Stack
---

<!-- problem:start -->

# [3420. Count Non-Decreasing Subarrays After K Operations](https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations)

[中文文档](/solution/3400-3499/3420.Count%20Non-Decreasing%20Subarrays%20After%20K%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of <code>n</code> integers and an integer <code>k</code>.</p>

<p>For each subarray of <code>nums</code>, you can apply <strong>up to</strong> <code>k</code> operations on it. In each operation, you increment any element of the subarray by 1.</p>

<p><strong>Note</strong> that each subarray is considered independently, meaning changes made to one subarray do not persist to another.</p>

<p>Return the number of subarrays that you can make <strong>non-decreasing</strong> ​​​​​after performing at most <code>k</code> operations.</p>

<p>An array is said to be <strong>non-decreasing</strong> if each element is greater than or equal to its previous element, if it exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,3,1,2,4,4], k = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>

<p>Out of all 21 possible subarrays of <code>nums</code>, only the subarrays <code>[6, 3, 1]</code>, <code>[6, 3, 1, 2]</code>, <code>[6, 3, 1, 2, 4]</code> and <code>[6, 3, 1, 2, 4, 4]</code> cannot be made non-decreasing after applying up to k = 7 operations. Thus, the number of non-decreasing subarrays is <code>21 - 4 = 17</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,3,1,3,6], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[3, 1, 3, 6]</code> along with all subarrays of <code>nums</code> with three or fewer elements, except <code>[6, 3, 1]</code>, can be made non-decreasing after <code>k</code> operations. There are 5 subarrays of a single element, 4 subarrays of two elements, and 2 subarrays of three elements except <code>[6, 3, 1]</code>, so there are <code>1 + 5 + 4 + 2 = 12</code> subarrays that can be made non-decreasing.</p>
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

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> An operation increments one element. Making a subarray non-decreasing means raising every position to the prefix maximum on its left; the cost is the total raise. $n\le 10^5$ forbids recomputing every subarray.
>
> Longer subarrays never cost less, so each right end has a farthest left end whose cost is still $\le k$.
>
> A monotonic stack stores segments that act as prefix maxima. The left pointer evicts expired segments and refunds their cost. Summing the number of valid left ends for every right end is the answer.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
from collections import deque

class Solution:
    def countNonDecreasingSubarrays(self, nums, k):
        n = len(nums)
        dq = deque()
        cost = 0
        ans = 0
        j = n - 1

        for i in range(n - 1, -1, -1):
            while dq and nums[dq[-1]] < nums[i]:
                l = dq.pop()
                r = dq[-1] if dq else j + 1
                cost += (r - l) * (nums[i] - nums[l])

            dq.append(i)

            while cost > k:
                cost -= nums[dq[0]] - nums[j]

                if dq[0] == j:
                    dq.popleft()

                j -= 1

            ans += j - i + 1

        return ans
```

#### Java

```java
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();

        long cost = 0;
        long ans = 0;
        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();

                cost += (long) (r - l) * (nums[i] - nums[l]);
            }

            dq.addLast(i);

            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[j];

                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }

                j--;
            }

            ans += j - i + 1;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countNonDecreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        deque<int> dq;

        long long cost = 0, ans = 0;
        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            while (!dq.empty() && nums[dq.back()] < nums[i]) {
                int l = dq.back();
                dq.pop_back();

                int r = dq.empty() ? j + 1 : dq.back();

                cost += 1LL * (r - l) * (nums[i] - nums[l]);
            }

            dq.push_back(i);

            while (cost > k) {
                cost -= nums[dq.front()] - nums[j];

                if (dq.front() == j) {
                    dq.pop_front();
                }

                j--;
            }

            ans += j - i + 1;
        }

        return ans;
    }
};
```

#### Go

```go
func countNonDecreasingSubarrays(nums []int, k int) int64 {
    n := len(nums)
    dq := []int{}

    var cost, ans int64
    j := n - 1

    for i := n - 1; i >= 0; i-- {
        for len(dq) > 0 && nums[dq[len(dq)-1]] < nums[i] {
            l := dq[len(dq)-1]
            dq = dq[:len(dq)-1]

            r := j + 1
            if len(dq) > 0 {
                r = dq[len(dq)-1]
            }

            cost += int64(r-l) * int64(nums[i]-nums[l])
        }

        dq = append(dq, i)

        for cost > int64(k) {
            cost -= int64(nums[dq[0]] - nums[j])

            if dq[0] == j {
                dq = dq[1:]
            }

            j--
        }

        ans += int64(j - i + 1)
    }

    return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
