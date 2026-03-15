---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3871.Count%20Commas%20in%20Range%20II/README_EN.md
---

<!-- problem:start -->

# [3871. Count Commas in Range II](https://leetcode.com/problems/count-commas-in-range-ii)

[中文文档](/solution/3800-3899/3871.Count%20Commas%20in%20Range%20II/README.md)

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

<p><strong>​​​​​​​</strong>All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

Based on the problem description, we can observe the following pattern:

- Numbers in the range [1, 999] contain no commas;
- Numbers in the range [1,000, 999,999] contain one comma;
- Numbers in the range [1,000,000, 999,999,999] contain two commas;
- And so on.

Therefore, we can start from $x = 1000$ and multiply $x$ by 1000 each time until $x$ exceeds $n$. In each iteration, there are $n - x + 1$ numbers that newly gain one comma, and we accumulate their count into the answer.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCommas(self, n: int) -> int:
        ans = 0
        x = 1000
        while x <= n:
            ans += n - x + 1
            x *= 1000
        return ans
```

#### Java

```java
class Solution {
    public long countCommas(long n) {
        long ans = 0;
        for (long x = 1000; x <= n; x *= 1000) {
            ans += n - x + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countCommas(long long n) {
        long long ans = 0;
        for (long long x = 1000; x <= n; x *= 1000) {
            ans += n - x + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func countCommas(n int64) (ans int64) {
	for x := int64(1000); x <= n; x *= 1000 {
		ans += n - x + 1
	}
	return
}
```

#### TypeScript

```ts
function countCommas(n: number): number {
    let ans = 0;
    for (let x = 1000; x <= n; x *= 1000) {
        ans += n - x + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
