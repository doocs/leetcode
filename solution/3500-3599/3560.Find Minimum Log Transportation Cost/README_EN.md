---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3560.Find%20Minimum%20Log%20Transportation%20Cost/README_EN.md
rating: 1339
source: Weekly Contest 451 Q1
tags:
    - Math
---

<!-- problem:start -->

# [3560. Find Minimum Log Transportation Cost](https://leetcode.com/problems/find-minimum-log-transportation-cost)

[中文文档](/solution/3500-3599/3560.Find%20Minimum%20Log%20Transportation%20Cost/README.md)

## Description

<!-- description:start -->

<p>You are given integers <code>n</code>, <code>m</code>, and <code>k</code>.</p>

<p>There are two logs of lengths <code>n</code> and <code>m</code> units, which need to be transported in three trucks where each truck can carry one log with length <strong>at most</strong> <code>k</code> units.</p>

<p>You may cut the logs into smaller pieces, where the cost of cutting a log of length <code>x</code> into logs of length <code>len1</code> and <code>len2</code> is <code>cost = len1 * len2</code> such that <code>len1 + len2 = x</code>.</p>

<p>Return the <strong>minimum total cost</strong> to distribute the logs onto the trucks. If the logs don&#39;t need to be cut, the total cost is 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, m = 5, k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>Cut the log with length 6 into logs with length 1 and 5, at a cost equal to <code>1 * 5 == 5</code>. Now the three logs of length 1, 5, and 5 can fit in one truck each.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, m = 4, k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The two logs can fit in the trucks already, hence we don&#39;t need to cut the logs.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n, m &lt;= 2 * k</code></li>
	<li>The input is generated such that it is always possible to transport the logs.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

If the lengths of both logs do not exceed the truck's maximum load $k$, then no cutting is needed, and we simply return $0$.

Otherwise, it means that only one log has a length greater than $k$, and we need to cut it into two pieces. Let the longer log have length $x$, then the cutting cost is $k \times (x - k)$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCuttingCost(self, n: int, m: int, k: int) -> int:
        x = max(n, m)
        return 0 if x <= k else k * (x - k)
```

#### Java

```java
class Solution {
    public long minCuttingCost(int n, int m, int k) {
        int x = Math.max(n, m);
        return x <= k ? 0 : 1L * k * (x - k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCuttingCost(int n, int m, int k) {
        int x = max(n, m);
        return x <= k ? 0 : 1LL * k * (x - k);
    }
};
```

#### Go

```go
func minCuttingCost(n int, m int, k int) int64 {
	x := max(n, m)
	if x <= k {
		return 0
	}
	return int64(k * (x - k))
}
```

#### TypeScript

```ts
function minCuttingCost(n: number, m: number, k: number): number {
    const x = Math.max(n, m);
    return x <= k ? 0 : k * (x - k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
