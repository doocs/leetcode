---
comments: true
difficulty: Hard
rating: 2231
source: Biweekly Contest 124 Q4
tags:
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3041. Maximize Consecutive Elements in an Array After Modification](https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification)

[中文文档](/solution/3000-3099/3041.Maximize%20Consecutive%20Elements%20in%20an%20Array%20After%20Modification/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>Initially, you can increase the value of <strong>any</strong> element in the array by <strong>at most</strong> <code>1</code>.</p>

<p>After that, you need to select <strong>one or more</strong> elements from the final array such that those elements are <strong>consecutive</strong> when sorted in increasing order. For example, the elements <code>[3, 4, 5]</code> are consecutive while <code>[3, 4, 6]</code> and <code>[1, 1, 2, 3]</code> are not.<!-- notionvc: 312f8c5d-40d0-4cd1-96cc-9e96a846735b --></p>

<p>Return <em>the <strong>maximum</strong> number of elements that you can select</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,5,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can increase the elements at indices 0 and 3. The resulting array is nums = [3,1,5,2,1].
We select the elements [<u><strong>3</strong></u>,<u><strong>1</strong></u>,5,<u><strong>2</strong></u>,1] and we sort them to obtain [1,2,3], which are consecutive.
It can be shown that we cannot select more than 3 consecutive elements.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,7,10]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The maximum consecutive elements that we can select is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Each element may increase by at most one, after which we pick the longest subsequence that becomes consecutive. $n \le 10^5$ and values reach $10^6$.
>
> After sorting, neighbors are equal, differ by one, or jump further. Adding one maps a value to $x$ or $x+1$, so the DP only needs whether the current end kept $x$ or became $x+1$.
>
> We scan values in order and keep the best consecutive length ending at $x$ and at $x+1$, updating by whether the gap is $0$, $1$, or at least $2$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSelectedElements(self, nums: List[int]) -> int:
        nums.sort()
        dp = {}
        ans = 0

        for x in nums:
            dp[x + 1] = max(dp.get(x + 1, 0), dp.get(x, 0) + 1)
            dp[x] = max(dp.get(x, 0), dp.get(x - 1, 0) + 1)

            ans = max(ans, dp[x], dp[x + 1])

        return ans
```

#### Java

```java
import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            dp.put(x + 1, Math.max(
                dp.getOrDefault(x + 1, 0),
                dp.getOrDefault(x, 0) + 1
            ));

            dp.put(x, Math.max(
                dp.getOrDefault(x, 0),
                dp.getOrDefault(x - 1, 0) + 1
            ));

            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSelectedElements(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        unordered_map<int, int> dp;
        int ans = 0;

        for (int x : nums) {
            int a = dp[x] + 1;
            int b = dp[x - 1] + 1;

            dp[x + 1] = max(dp[x + 1], a);
            dp[x] = max(dp[x], b);

            ans = max(ans, max(dp[x], dp[x + 1]));
        }

        return ans;
    }
};
```

#### Go

```go
import "sort"

func maxSelectedElements(nums []int) int {
    sort.Ints(nums)
    dp := map[int]int{}
    ans := 0

    for _, x := range nums {
        dp[x+1] = max(dp[x+1], dp[x]+1)
        dp[x] = max(dp[x], dp[x-1]+1)
        ans = max(ans, dp[x], dp[x+1])
    }

    return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
