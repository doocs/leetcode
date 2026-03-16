---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3870.Count%20Commas%20in%20Range/README_EN.md
---

<!-- problem:start -->

# [3870. Count Commas in Range](https://leetcode.com/problems/count-commas-in-range)

[中文文档](/solution/3800-3899/3870.Count%20Commas%20in%20Range/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>Return the <strong>total</strong> number of commas used when writing all integers from <code>[1, n]</code> (inclusive) in <strong>standard</strong> number formatting.</p>

<p>In <strong>standard</strong> formatting:</p>

<ul>
	<li>A comma is inserted after <strong>every three</strong> digits from the right.</li>
	<li>Numbers with <strong>fewer</strong> than 4 digits contain no commas.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1002</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The numbers <code>&quot;1,000&quot;</code>, <code>&quot;1,001&quot;</code>, and <code>&quot;1,002&quot;</code> each contain one comma, giving a total of 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 998</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

Numbers from 1 to 999 contain no commas, so when $n$ is less than or equal to 999, the answer is 0.

Since the range of $n$ is $[1, 10^5]$, when $n$ is greater than or equal to 1000, each number contains exactly one comma, so the answer is $n - 999$.

Therefore, the answer is $\max(0, n - 999)$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCommas(self, n: int) -> int:
        return max(0, n - 999)
```

#### Java

```java
class Solution {
    public int countCommas(int n) {
        return Math.max(0, n - 999);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCommas(int n) {
        return max(0, n - 999);
    }
};
```

#### Go

```go
func countCommas(n int) int {
	return max(0, n-999)
}
```

#### TypeScript

```ts
function countCommas(n: number): number {
    return Math.max(0, n - 999);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
