---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4071. Minimum Rotations to Dial a Number II](https://leetcode.cn/problems/minimum-rotations-to-dial-a-number-ii)

[English Version](/solution/4000-4099/4071.Minimum%20Rotations%20to%20Dial%20a%20Number%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer <code>n</code> and a string <code>s</code> of length <code>n</code> consisting of digits.</p>

<p>The dial contains the digits 0 through 9 in order and is <strong>circular</strong>, so 0 and 9 are adjacent. The pointer initially points to 0.</p>

<p>To dial each digit of <code>s</code> <strong>in order</strong>, rotate the pointer until it points to that digit. Each rotation moves the pointer to an <strong>adjacent</strong> digit, and you may rotate in <strong>either</strong> direction. Dialing a digit that the pointer already points to requires no rotations.</p>

<p>Before dialing, you may perform the following operation <strong>at most once</strong>:</p>

<ul>
	<li>Choose an index <code>k</code> such that <code>0 &lt;= k &lt; n</code> and <strong>reverse</strong> the <strong><span data-keyword="string-suffix">suffix</span></strong> <code>s[k..n - 1]</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> total number of rotations needed to dial the string after optimally choosing whether to perform the operation and which suffix to reverse.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, s = &quot;1502&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>Reverse the suffix starting at <code>k = 1</code> to obtain <code>&quot;1205&quot;</code>, then dial it.</p>

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
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>1 + 1 + 2 + 5 = 9</code>, which is the minimum total number of rotations.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, s = &quot;2916&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose not to reverse a suffix and dial <code>&quot;2916&quot;</code>.</p>

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
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>2 + 3 + 2 + 5 = 12</code>, which is the minimum total number of rotations.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, s = &quot;4219&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Reverse the suffix starting at <code>k = 0</code>, which reverses the entire string, to obtain <code>&quot;9124&quot;</code>, then dial it.</p>

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
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>1 + 2 + 1 + 2 = 6</code>, which is the minimum total number of rotations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>s</code> consists only of digits <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 如果不考虑翻转，总代价就是从 $0$ 到首字符的代价，再加上字符串相邻字符之间的最短环形距离之和。
>
> 翻转后缀 $s[k..n-1]$ 之后，后缀内部的相邻关系虽然顺序反了，但每一条边的两个端点没有变，所以这些内部代价总和不变。
>
> 真正变化的只有两处：起点接到哪一个字符，以及前缀和后缀之间那一条连接边。于是可以枚举分界点 $k$，在 $O(1)$ 时间更新答案。

<!-- thinking:end -->

设相邻字符之间的总旋转代价为 $T$，即

$$
T=\sum_{i=1}^{n-1} \operatorname{dist}(s[i-1], s[i]),
$$

其中 $\operatorname{dist}(a,b)$ 表示拨号盘上两个数字之间的最短环形距离。

如果翻转整个字符串，即 $k=0$，总代价为

$$
\operatorname{dist}(0, s[n-1]) + T.
$$

如果 $k>0$，那么前缀 $s[0..k-1]$ 不变，后缀内部总代价仍然保留，唯一被替换的是边 $(s[k-1], s[k])$，它会变成 $(s[k-1], s[n-1])$。同时起始位置仍然是从 $0$ 到 $s[0]$。因此此时总代价为

$$
T - \operatorname{dist}(s[k-1], s[k]) + \operatorname{dist}(0, s[0]) + \operatorname{dist}(s[k-1], s[n-1]).
$$

枚举所有 $k$ 取最小值即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
	def minRotations(self, n: int, s: str) -> int:
		def dist(a: str, b: str) -> int:
			d = abs(ord(a) - ord(b))
			return min(d, 10 - d)

		total = sum(dist(a, b) for a, b in pairwise(s))

		first = s[0]
		last = s[-1]
		to_first = dist("0", first)
		ans = total + dist("0", last)

		for pre, cur in pairwise(s):
			ans = min(ans, total - dist(pre, cur) + to_first + dist(pre, last))

		return ans
```

#### Java

```java
class Solution {
	public int minRotations(int n, String s) {
		int total = 0;
		for (int i = 1; i < n; ++i) {
			int diff = Math.abs(s.charAt(i) - s.charAt(i - 1));
			total += Math.min(diff, 10 - diff);
		}

		char first = s.charAt(0);
		char last = s.charAt(n - 1);
		int toFirst = Math.min(first - '0', 10 - (first - '0'));
		int ans = total + Math.min(last - '0', 10 - (last - '0'));

		for (int i = 1; i < n; ++i) {
			char pre = s.charAt(i - 1);
			char cur = s.charAt(i);
			int diff = Math.abs(pre - cur);
			int edge = Math.min(diff, 10 - diff);
			diff = Math.abs(pre - last);
			int toLast = Math.min(diff, 10 - diff);
			ans = Math.min(ans, total - edge + toFirst + toLast);
		}

		return ans;
	}
}
```

#### C++

```cpp
class Solution {
public:
	int minRotations(int n, string s) {
		int total = 0;
		for (int i = 1; i < n; ++i) {
			int diff = abs(s[i] - s[i - 1]);
			total += min(diff, 10 - diff);
		}

		char first = s[0];
		char last = s[n - 1];
		int toFirst = min(first - '0', 10 - (first - '0'));
		int ans = total + min(last - '0', 10 - (last - '0'));

		for (int i = 1; i < n; ++i) {
			char pre = s[i - 1];
			char cur = s[i];
			int diff = abs(pre - cur);
			int edge = min(diff, 10 - diff);
			diff = abs(pre - last);
			int toLast = min(diff, 10 - diff);
			ans = min(ans, total - edge + toFirst + toLast);
		}

		return ans;
	}
};
```

#### Go

```go
func minRotations(n int, s string) int {
	total := 0
	for i := 1; i < n; i++ {
		diff := int(s[i]) - int(s[i-1])
		if diff < 0 {
			diff = -diff
		}
		total += min(diff, 10-diff)
	}

	first := int(s[0] - '0')
	last := int(s[n-1] - '0')
	toFirst := min(first, 10-first)
	ans := total + min(last, 10-last)

	for i := 1; i < n; i++ {
		pre := int(s[i-1])
		cur := int(s[i])
		diff := cur - pre
		if diff < 0 {
			diff = -diff
		}
		edge := min(diff, 10-diff)

		diff = pre - int(s[n-1])
		if diff < 0 {
			diff = -diff
		}
		toLast := min(diff, 10-diff)

		ans = min(ans, total-edge+toFirst+toLast)
	}

	return ans
}
```

#### TypeScript

```ts
function minRotations(n: number, s: string): number {
    let total = 0;
    for (let i = 1; i < n; ++i) {
        const diff = Math.abs(s.charCodeAt(i) - s.charCodeAt(i - 1));
        total += Math.min(diff, 10 - diff);
    }

    const first = s[0];
    const last = s[n - 1];
    const toFirst = Math.min(first.charCodeAt(0) - 48, 10 - (first.charCodeAt(0) - 48));
    let ans = total + Math.min(last.charCodeAt(0) - 48, 10 - (last.charCodeAt(0) - 48));

    for (let i = 1; i < n; ++i) {
        const pre = s[i - 1];
        const cur = s[i];
        const diff = Math.abs(pre.charCodeAt(0) - cur.charCodeAt(0));
        const edge = Math.min(diff, 10 - diff);
        const toLastDiff = Math.abs(pre.charCodeAt(0) - last.charCodeAt(0));
        const toLast = Math.min(toLastDiff, 10 - toLastDiff);
        ans = Math.min(ans, total - edge + toFirst + toLast);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
