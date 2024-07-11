---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1893.Check%20if%20All%20the%20Integers%20in%20a%20Range%20Are%20Covered/README_EN.md
rating: 1307
source: Biweekly Contest 54 Q1
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [1893. Check if All the Integers in a Range Are Covered](https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered)

[中文文档](/solution/1800-1899/1893.Check%20if%20All%20the%20Integers%20in%20a%20Range%20Are%20Covered/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>ranges</code> and two integers <code>left</code> and <code>right</code>. Each <code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represents an <strong>inclusive</strong> interval between <code>start<sub>i</sub></code> and <code>end<sub>i</sub></code>.</p>

<p>Return <code>true</code> <em>if each integer in the inclusive range</em> <code>[left, right]</code> <em>is covered by <strong>at least one</strong> interval in</em> <code>ranges</code>. Return <code>false</code> <em>otherwise</em>.</p>

<p>An integer <code>x</code> is covered by an interval <code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> if <code>start<sub>i</sub> &lt;= x &lt;= end<sub>i</sub></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> Every integer between 2 and 5 is covered:
- 2 is covered by the first range.
- 3 and 4 are covered by the second range.
- 5 is covered by the third range.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ranges = [[1,10],[10,20]], left = 21, right = 21
<strong>Output:</strong> false
<strong>Explanation:</strong> 21 is not covered by any range.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ranges.length &lt;= 50</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 50</code></li>
	<li><code>1 &lt;= left &lt;= right &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

We can use the idea of a difference array to create a difference array $\textit{diff}$ of length $52$.

Next, we iterate through the array $\textit{ranges}$. For each interval $[l, r]$, we increment $\textit{diff}[l]$ by $1$ and decrement $\textit{diff}[r + 1]$ by $1$.

Then, we iterate through the difference array $\textit{diff}$, maintaining a prefix sum $s$. For each position $i$, we increment $s$ by $\textit{diff}[i]$. If $s \le 0$ and $left \le i \le right$, it indicates that an integer $i$ within the interval $[left, right]$ is not covered, and we return $\textit{false}$.

If we finish iterating through the difference array $\textit{diff}$ without returning $\textit{false}$, it means that every integer within the interval $[left, right]$ is covered by at least one interval in $\textit{ranges}$, and we return $\textit{true}$.

The time complexity is $O(n + M)$, and the space complexity is $O(M)$. Here, $n$ is the length of the array $\textit{ranges}$, and $M$ is the maximum value of the interval, which in this case is $M \le 50$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isCovered(self, ranges: List[List[int]], left: int, right: int) -> bool:
        diff = [0] * 52
        for l, r in ranges:
            diff[l] += 1
            diff[r + 1] -= 1
        s = 0
        for i, x in enumerate(diff):
            s += x
            if s <= 0 and left <= i <= right:
                return False
        return True
```

#### Java

```java
class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            int l = range[0], r = range[1];
            ++diff[l];
            --diff[r + 1];
        }
        int s = 0;
        for (int i = 0; i < diff.length; ++i) {
            s += diff[i];
            if (s <= 0 && left <= i && i <= right) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isCovered(vector<vector<int>>& ranges, int left, int right) {
        vector<int> diff(52);
        for (auto& range : ranges) {
            int l = range[0], r = range[1];
            ++diff[l];
            --diff[r + 1];
        }
        int s = 0;
        for (int i = 0; i < diff.size(); ++i) {
            s += diff[i];
            if (s <= 0 && left <= i && i <= right) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isCovered(ranges [][]int, left int, right int) bool {
	diff := [52]int{}
	for _, e := range ranges {
		l, r := e[0], e[1]
		diff[l]++
		diff[r+1]--
	}
	s := 0
	for i, x := range diff {
		s += x
		if s <= 0 && left <= i && i <= right {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isCovered(ranges: number[][], left: number, right: number): boolean {
    const diff: number[] = Array(52).fill(0);
    for (const [l, r] of ranges) {
        ++diff[l];
        --diff[r + 1];
    }
    let s = 0;
    for (let i = 0; i < diff.length; ++i) {
        s += diff[i];
        if (s <= 0 && left <= i && i <= right) {
            return false;
        }
    }
    return true;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} ranges
 * @param {number} left
 * @param {number} right
 * @return {boolean}
 */
var isCovered = function (ranges, left, right) {
    const diff = Array(52).fill(0);
    for (const [l, r] of ranges) {
        ++diff[l];
        --diff[r + 1];
    }
    let s = 0;
    for (let i = 0; i < diff.length; ++i) {
        s += diff[i];
        if (s <= 0 && left <= i && i <= right) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
