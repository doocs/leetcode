---
comments: true
difficulty: Hard
rating: 2619
source: Weekly Contest 355 Q3
tags:
    - Greedy
    - Array
    - Math
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [2790. Maximum Number of Groups With Increasing Length](https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length)

[中文文档](/solution/2700-2799/2790.Maximum%20Number%20of%20Groups%20With%20Increasing%20Length/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>usageLimits</code> of length <code>n</code>.</p>

<p>Your task is to create <strong>groups</strong> using numbers from <code>0</code> to <code>n - 1</code>, ensuring that each number, <code>i</code>, is used no more than <code>usageLimits[i]</code> times in total <strong>across all groups</strong>. You must also satisfy the following conditions:</p>

<ul>
	<li>Each group must consist of <strong>distinct </strong>numbers, meaning that no duplicate numbers are allowed within a single group.</li>
	<li>Each group (except the first one) must have a length <strong>strictly greater</strong> than the previous group.</li>
</ul>

<p>Return <em>an integer denoting the <strong>maximum</strong> number of groups you can create while satisfying these conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> <code>usageLimits</code> = [1,2,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, we can use 0 at most once, 1 at most twice, and 2 at most five times.
One way of creating the maximum number of groups while satisfying the conditions is: 
Group 1 contains the number [2].
Group 2 contains the numbers [1,2].
Group 3 contains the numbers [0,1,2]. 
It can be shown that the maximum number of groups is 3. 
So, the output is 3. </pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> <code>usageLimits</code> = [2,1,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example, we can use 0 at most twice, 1 at most once, and 2 at most twice.
One way of creating the maximum number of groups while satisfying the conditions is:
Group 1 contains the number [0].
Group 2 contains the numbers [1,2].
It can be shown that the maximum number of groups is 2.
So, the output is 2. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> <code>usageLimits</code> = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, we can use both 0 and 1 at most once.
One way of creating the maximum number of groups while satisfying the conditions is:
Group 1 contains the number [0].
It can be shown that the maximum number of groups is 1.
So, the output is 1. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= usageLimits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= usageLimits[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

<!-- thinking:start -->

> **Thinking**
>
> Group $k$ needs $k$ distinct numbers, and number $i$ may be used at most $usageLimits[i]$ times; we want the most groups. Trying every group count and matching quotas is heavy.
>
> Sort the limits and consume from the left: whenever the running remainder can open group $k+1$, do so and subtract $k+1$, rolling the rest into the next limit. Using smaller quotas first maximizes the number of groups.

<!-- thinking:end -->

Sort the limits in increasing order and accumulate them. Each extra unit of remaining quota is used to try opening one more group; if it succeeds, subtract that group's cost from the running sum. The final group count is the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxIncreasingGroups(self, usageLimits: List[int]) -> int:
        usageLimits.sort()
        k, n = 0, len(usageLimits)
        for i in range(n):
            if usageLimits[i] > k:
                k += 1
                usageLimits[i] -= k
            if i + 1 < n:
                usageLimits[i + 1] += usageLimits[i]
        return k
```

#### Java

```java
class Solution {
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        Collections.sort(usageLimits);
        int k = 0;
        long s = 0;
        for (int x : usageLimits) {
            s += x;
            if (s > k) {
                ++k;
                s -= k;
            }
        }
        return k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxIncreasingGroups(vector<int>& usageLimits) {
        sort(usageLimits.begin(), usageLimits.end());
        int k = 0;
        long long s = 0;
        for (int x : usageLimits) {
            s += x;
            if (s > k) {
                ++k;
                s -= k;
            }
        }
        return k;
    }
};
```

#### Go

```go
func maxIncreasingGroups(usageLimits []int) int {
	sort.Ints(usageLimits)
	s, k := 0, 0
	for _, x := range usageLimits {
		s += x
		if s > k {
			k++
			s -= k
		}
	}
	return k
}
```

#### TypeScript

```ts
function maxIncreasingGroups(usageLimits: number[]): number {
    usageLimits.sort((a, b) => a - b);
    let k = 0;
    let s = 0;
    for (const x of usageLimits) {
        s += x;
        if (s > k) {
            ++k;
            s -= k;
        }
    }
    return k;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
