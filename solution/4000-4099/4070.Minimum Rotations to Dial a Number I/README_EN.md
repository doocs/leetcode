---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4070. Minimum Rotations to Dial a Number I](https://leetcode.com/problems/minimum-rotations-to-dial-a-number-i)

[中文文档](/solution/4000-4099/4070.Minimum%20Rotations%20to%20Dial%20a%20Number%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length 10 consisting of digits.</p>

<p>The dial contains the digits 0 through 9 in order and is <strong>circular</strong>, so 0 and 9 are adjacent. The pointer initially points to 0.</p>

<p>To dial each digit of <code>s</code> <strong>in order</strong>, rotate the pointer until it points to that digit. Each rotation moves the pointer to an <strong>adjacent</strong> digit, and you may rotate in <strong>either</strong> direction. Dialing a digit that the pointer already points to requires no rotations.</p>

<p>Return the <strong>minimum</strong> total number of rotations needed to dial every digit of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0192837465&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">Step</th>
			<th style="border: 1px solid #ccc; padding: 5px;">From</th>
			<th style="border: 1px solid #ccc; padding: 5px;">To</th>
			<th style="border: 1px solid #ccc; padding: 5px;">Rotations</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">10</td>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>0 + 1 + 2 + 3 + 4 + 5 + 4 + 3 + 2 + 1 = 25</code>, which is the minimum total number of rotations.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1200210200&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">Step</th>
			<th style="border: 1px solid #ccc; padding: 5px;">From</th>
			<th style="border: 1px solid #ccc; padding: 5px;">To</th>
			<th style="border: 1px solid #ccc; padding: 5px;">Rotations</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">7</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">8</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">10</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>1 + 1 + 2 + 0 + 2 + 1 + 1 + 2 + 2 + 0 = 12</code>, which is the minimum total number of rotations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s.length == 10</code></li>
	<li><code>s</code> consists only of digits <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Each step only depends on the current pointer position and the next digit to dial, so we can process the string greedily from left to right.
>
> If we move from digit $a$ to digit $b$, the clockwise and counterclockwise distances add up to $10$. Let $d=|a-b|$, then the minimum cost of this move is $\min(d, 10-d)$.
>
> Starting from digit $0$, we sum this minimum cost for every transition.

<!-- thinking:end -->

Let $\textit{pre}$ be the previous digit under the pointer and $\textit{cur}$ be the next target digit. Because the dial is circular, the shortest distance between them is

$$
\min\bigl(|\textit{cur}-\textit{pre}|, 10-|\textit{cur}-\textit{pre}|\bigr).
$$

We iterate through the string, add this value to the answer, and update $\textit{pre}$ to $\textit{cur}$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
	def minRotations(self, s: str) -> int:
		ans = pre = 0
		for cur in map(int, s):
			diff = abs(cur - pre)
			ans += min(diff, 10 - diff)
			pre = cur
		return ans
```

#### Java

```java
class Solution {
	public int minRotations(String s) {
		int ans = 0;
		int pre = 0;
		for (int i = 0; i < s.length(); ++i) {
			int cur = s.charAt(i) - '0';
			int diff = Math.abs(cur - pre);
			ans += Math.min(diff, 10 - diff);
			pre = cur;
		}
		return ans;
	}
}
```

#### C++

```cpp
class Solution {
public:
	int minRotations(string s) {
		int ans = 0;
		int pre = 0;
		for (int i = 0; i < s.size(); ++i) {
			int cur = s[i] - '0';
			int diff = abs(cur - pre);
			ans += min(diff, 10 - diff);
			pre = cur;
		}
		return ans;
	}
};
```

#### Go

```go
func minRotations(s string) int {
	ans := 0
	pre := 0
	for i := 0; i < len(s); i++ {
		cur := int(s[i] - '0')
		diff := abs(cur - pre)
		ans += min(diff, 10-diff)
		pre = cur
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minRotations(s: string): number {
    let ans = 0;
    let pre = 0;
    for (let i = 0; i < s.length; ++i) {
        const cur = s.charCodeAt(i) - 48;
        const diff = Math.abs(cur - pre);
        ans += Math.min(diff, 10 - diff);
        pre = cur;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
