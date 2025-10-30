---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3723.Maximize%20Sum%20of%20Squares%20of%20Digits/README_EN.md
rating: 1536
source: Biweekly Contest 168 Q2
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [3723. Maximize Sum of Squares of Digits](https://leetcode.com/problems/maximize-sum-of-squares-of-digits)

[中文文档](/solution/3700-3799/3723.Maximize%20Sum%20of%20Squares%20of%20Digits/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>positive</strong> integers <code>num</code> and <code>sum</code>.</p>

<p>A positive integer <code>n</code> is <strong>good</strong> if it satisfies both of the following:</p>

<ul>
	<li>The number of digits in <code>n</code> is <strong>exactly</strong> <code>num</code>.</li>
	<li>The sum of digits in <code>n</code> is <strong>exactly</strong> <code>sum</code>.</li>
</ul>

<p>The <strong>score</strong> of a <strong>good</strong> integer <code>n</code> is the sum of the squares of digits in <code>n</code>.</p>

<p>Return a <strong>string</strong> denoting the <strong>good</strong> integer <code>n</code> that achieves the <strong>maximum</strong> <strong>score</strong>. If there are multiple possible integers, return the <strong>maximum </strong>​​​​​​​one. If no such integer exists, return an empty string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 2, sum = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;30&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 3 good integers: 12, 21, and 30.</p>

<ul>
	<li>The score of 12 is <code>1<sup>2</sup> + 2<sup>2</sup> = 5</code>.</li>
	<li>The score of 21 is <code>2<sup>2</sup> + 1<sup>2</sup> = 5</code>.</li>
	<li>The score of 30 is <code>3<sup>2</sup> + 0<sup>2</sup> = 9</code>.</li>
</ul>

<p>The maximum score is 9, which is achieved by the good integer 30. Therefore, the answer is <code>&quot;30&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 2, sum = 17</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;98&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 good integers: 89 and 98.</p>

<ul>
	<li>The score of 89 is <code>8<sup>2</sup> + 9<sup>2</sup> = 145</code>.</li>
	<li>The score of 98 is <code>9<sup>2</sup> + 8<sup>2</sup> = 145</code>.</li>
</ul>

<p>The maximum score is 145. The maximum good integer that achieves this score is 98. Therefore, the answer is <code>&quot;98&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 1, sum = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no integers that have exactly 1 digit and whose digits sum to 10. Therefore, the answer is <code>&quot;&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sum &lt;= 2 * 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

If $\text{num} \times 9 < \text{sum}$, then there is no valid good integer, so we return an empty string.

Otherwise, we can use as many digits $9$ as possible to form the good integer, since $9^2$ is the largest and will maximize the score. Specifically, we calculate how many $9$s are contained in $\text{sum}$, denoted as $k$, and the remaining part $s = \text{sum} - 9 \times k$. Then, we construct the good integer with the first $k$ digits as $9$. If $s > 0$, we append a digit $s$ at the end, and finally pad with $0$s to reach a total of $\text{num}$ digits.

The time complexity is $O(\text{num})$ and the space complexity is $O(\text{num})$, where $\text{num}$ is the number of digits in the good integer.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if num * 9 < sum:
            return ""
        k, s = divmod(sum, 9)
        ans = "9" * k
        if s:
            ans += digits[s]
        ans += "0" * (num - len(ans))
        return ans
```

#### Java

```java
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        int k = sum / 9;
        sum %= 9;
        StringBuilder ans = new StringBuilder("9".repeat(k));
        if (sum > 0) {
            ans.append(sum);
        }
        ans.append("0".repeat(num - ans.length()));
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        int k = sum / 9, s = sum % 9;
        string ans(k, '9');
        if (s > 0) {
            ans += char('0' + s);
        }
        ans += string(num - ans.size(), '0');
        return ans;
    }
};
```

#### Go

```go
func maxSumOfSquares(num int, sum int) string {
	if num*9 < sum {
		return ""
	}

	k, s := sum/9, sum%9
	ans := strings.Repeat("9", k)
	if s > 0 {
		ans += string('0' + byte(s))
	}
	if len(ans) < num {
		ans += strings.Repeat("0", num-len(ans))
	}

	return ans
}
```

#### TypeScript

```ts
function maxSumOfSquares(num: number, sum: number): string {
    if (num * 9 < sum) {
        return '';
    }

    const k = Math.floor(sum / 9);
    const s = sum % 9;

    let ans = '9'.repeat(k);
    if (s > 0) {
        ans += String.fromCharCode('0'.charCodeAt(0) + s);
    }
    if (ans.length < num) {
        ans += '0'.repeat(num - ans.length);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
