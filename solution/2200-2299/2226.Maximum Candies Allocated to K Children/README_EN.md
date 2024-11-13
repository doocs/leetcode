---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2226.Maximum%20Candies%20Allocated%20to%20K%20Children/README_EN.md
rating: 1646
source: Weekly Contest 287 Q3
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [2226. Maximum Candies Allocated to K Children](https://leetcode.com/problems/maximum-candies-allocated-to-k-children)

[中文文档](/solution/2200-2299/2226.Maximum%20Candies%20Allocated%20to%20K%20Children/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>candies</code>. Each element in the array denotes a pile of candies of size <code>candies[i]</code>. You can divide each pile into any number of <strong>sub piles</strong>, but you <strong>cannot</strong> merge two piles together.</p>

<p>You are also given an integer <code>k</code>. You should allocate piles of candies to <code>k</code> children such that each child gets the <strong>same</strong> number of candies. Each child can take <strong>at most one</strong> pile of candies and some piles of candies may go unused.</p>

<p>Return <em>the <strong>maximum number of candies</strong> each child can get.</em></p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> candies = [5,8,6], k = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. It can be proven that each child cannot receive more than 5 candies.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> candies = [2,5], k = 11
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at least one candy. Thus, each child gets no candy and the answer is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candies.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candies[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>12</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if each child can receive $v$ candies, then for any $v' \lt v$, each child can also receive $v'$ candies. Therefore, we can use binary search to find the maximum $v$ such that each child can receive $v$ candies.

We define the left boundary of the binary search as $l = 0$ and the right boundary as $r = \max(\text{candies})$, where $\max(\text{candies})$ represents the maximum value in the array $\text{candies}$. During the binary search, we take the middle value $v = \left\lfloor \frac{l + r + 1}{2} \right\rfloor$ each time, and then calculate the total number of candies each child can receive. If the total is greater than or equal to $k$, it means each child can receive $v$ candies, so we update the left boundary $l = v$. Otherwise, we update the right boundary $r = v - 1$. Finally, when $l = r$, we have found the maximum $v$.

The time complexity is $O(n \times \log M)$, where $n$ is the length of the array $\text{candies}$, and $M$ is the maximum value in the array $\text{candies}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumCandies(self, candies: List[int], k: int) -> int:
        l, r = 0, max(candies)
        while l < r:
            mid = (l + r + 1) >> 1
            if sum(x // mid for x in candies) >= k:
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class Solution {
    public int maximumCandies(int[] candies, long k) {
        int l = 0, r = Arrays.stream(candies).max().getAsInt();
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            long cnt = 0;
            for (int x : candies) {
                cnt += x / mid;
            }
            if (cnt >= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        int l = 0, r = ranges::max(candies);
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            long long cnt = 0;
            for (int x : candies) {
                cnt += x / mid;
            }
            if (cnt >= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func maximumCandies(candies []int, k int64) int {
	return sort.Search(1e7, func(v int) bool {
		v++
		var cnt int64
		for _, x := range candies {
			cnt += int64(x / v)
		}
		return cnt < k
	})
}
```

#### TypeScript

```ts
function maximumCandies(candies: number[], k: number): number {
    let [l, r] = [0, Math.max(...candies)];
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        const cnt = candies.reduce((acc, cur) => acc + Math.floor(cur / mid), 0);
        if (cnt >= k) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
