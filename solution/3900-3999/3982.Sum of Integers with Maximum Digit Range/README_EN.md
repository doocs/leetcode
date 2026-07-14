---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3982.Sum%20of%20Integers%20with%20Maximum%20Digit%20Range/README_EN.md
rating: 1200
source: Weekly Contest 509 Q1
---

<!-- problem:start -->

# [3982. Sum of Integers with Maximum Digit Range](https://leetcode.com/problems/sum-of-integers-with-maximum-digit-range)

[中文文档](/solution/3900-3999/3982.Sum%20of%20Integers%20with%20Maximum%20Digit%20Range/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>The <strong>digit range</strong> of an integer is defined as the difference between its <strong>largest</strong> digit and <strong>smallest</strong> digit.</p>

<p>For example, the digit range of 5724 is <code>7 - 2 = 5</code>.</p>

<p>Return the sum of all integers in <code>nums</code> whose <strong>digit range</strong> is equal to the <strong>maximum digit range</strong> among all integers in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5724,111,350]</span></p>

<p><strong>Output:</strong> <span class="example-io">6074</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<tbody>
		<tr>
			<th style="text-align:center;"><code>i</code></th>
			<th style="text-align:center;"><code>nums[i]</code></th>
			<th style="text-align:center;">Largest</th>
			<th style="text-align:center;">Smallest</th>
			<th style="text-align:center;">Digit Range</th>
		</tr>
		<tr>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">5724</td>
			<td style="text-align:center;">7</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">5</td>
		</tr>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">111</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">0</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">350</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">5</td>
		</tr>
	</tbody>
</table>

<p>The maximum digit range is 5. The integers with this digit range are 5724 and 350, so the answer is <code>5724 + 350 = 6074</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [90,900]</span></p>

<p><strong>Output:</strong> <span class="example-io">990</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<tbody>
		<tr>
			<th style="text-align:center;"><code>i</code></th>
			<th style="text-align:center;"><code>nums[i]</code></th>
			<th style="text-align:center;">Largest</th>
			<th style="text-align:center;">Smallest</th>
			<th style="text-align:center;">Digit Range</th>
		</tr>
		<tr>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">90</td>
			<td style="text-align:center;">9</td>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">9</td>
		</tr>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">900</td>
			<td style="text-align:center;">9</td>
			<td style="text-align:center;">0</td>
			<td style="text-align:center;">9</td>
		</tr>
	</tbody>
</table>

<p>The maximum digit range is 9. Both integers have this digit range, so the answer is <code>90 + 900 = 990</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>10 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We traverse the array $\textit{nums}$. For each integer $x$, we extract its digits to find the largest digit $b$ and the smallest digit $a$, then compute the digit range $r = b - a$. If $r$ is greater than the current maximum digit range $\textit{mx}$, we update $\textit{mx} = r$ and reset the answer to $x$; if $r$ equals $\textit{mx}$, we add $x$ to the answer.

The time complexity is $O(n \log M)$, and the space complexity is $O(1)$, where $n$ is the length of the array $\textit{nums}$ and $M$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDigitRange(self, nums: list[int]) -> int:
        ans = mx = 0
        for x in nums:
            a, b = 10, 0
            y = x
            while y:
                v = y % 10
                y //= 10
                a = min(a, v)
                b = max(b, v)
            r = b - a
            if mx < r:
                mx = r
                ans = x
            elif mx == r:
                ans += x
        return ans
```

#### Java

```java
class Solution {
    public int maxDigitRange(int[] nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            int a = 10, b = 0;
            for (int y = x; y > 0; y /= 10) {
                int v = y % 10;
                a = Math.min(a, v);
                b = Math.max(b, v);
            }
            int r = b - a;
            if (mx < r) {
                mx = r;
                ans = x;
            } else if (mx == r) {
                ans += x;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDigitRange(vector<int>& nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            int a = 10, b = 0;
            for (int y = x; y > 0; y /= 10) {
                int v = y % 10;
                a = min(a, v);
                b = max(b, v);
            }
            int r = b - a;
            if (mx < r) {
                mx = r;
                ans = x;
            } else if (mx == r) {
                ans += x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDigitRange(nums []int) (ans int) {
	mx := 0
	for _, x := range nums {
		a, b := 10, 0
		for y := x; y > 0; y /= 10 {
			v := y % 10
			a = min(a, v)
			b = max(b, v)
		}
		r := b - a
		if mx < r {
			mx = r
			ans = x
		} else if mx == r {
			ans += x
		}
	}
	return
}
```

#### TypeScript

```ts
function maxDigitRange(nums: number[]): number {
    let [ans, mx] = [0, 0];
    for (const x of nums) {
        let [a, b] = [10, 0];
        for (let y = x; y; y = (y / 10) | 0) {
            const v = y % 10;
            a = Math.min(a, v);
            b = Math.max(b, v);
        }
        const r = b - a;
        if (mx < r) {
            mx = r;
            ans = x;
        } else if (mx == r) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
