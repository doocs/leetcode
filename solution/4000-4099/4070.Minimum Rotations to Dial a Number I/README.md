---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4070. 拨号的最少旋转次数 I](https://leetcode.cn/problems/minimum-rotations-to-dial-a-number-i)

[English Version](/solution/4000-4099/4070.Minimum%20Rotations%20to%20Dial%20a%20Number%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 10、由数字组成的字符串 <code>s</code>。</p>

<p>拨号盘上的数字 0 到 9 按顺序排列，且拨号盘是<strong>环形</strong>的，因此 0 和 9 相邻。指针最初指向 0。</p>

<p>要<strong>按顺序</strong>拨出 <code>s</code> 中的每个数字，需要旋转指针，直到它指向该数字。每次旋转都会将指针移动到一个<strong>相邻</strong>的数字，你可以向<strong>任一</strong>方向旋转。如果指针已经指向要拨出的数字，则无需旋转。</p>

<p>返回拨出 <code>s</code> 中所有数字所需的<strong>最少</strong>总旋转次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "0192837465"</span></p>

<p><strong>输出：</strong> <span class="example-io">25</span></p>

<p><strong>解释：</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">步骤</th>
			<th style="border: 1px solid #ccc; padding: 5px;">起始数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">目标数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">旋转次数</th>
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

<p>总旋转次数为 <code>0 + 1 + 2 + 3 + 4 + 5 + 4 + 3 + 2 + 1 = 25</code>，这是最少的总旋转次数。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "1200210200"</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">步骤</th>
			<th style="border: 1px solid #ccc; padding: 5px;">起始数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">目标数字</th>
			<th style="border: 1px solid #ccc; padding: 5px;">旋转次数</th>
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

<p>总旋转次数为 <code>1 + 1 + 2 + 0 + 2 + 1 + 1 + 2 + 2 + 0 = 12</code>，这是最少的总旋转次数。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s.length == 10</code></li>
	<li><code>s</code> 仅由数字 <code>'0'</code> 到 <code>'9'</code> 组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每次拨号只关心当前指针位置和目标数字，其余历史不会影响后续选择，因此可以按顺序贪心处理每一位。
>
> 从数字 $a$ 转到数字 $b$ 时，顺时针和逆时针两条路径的步数分别互补为 $10$。设两者的数字差为 $d=|a-b|$，那么最少旋转次数就是 $\min(d, 10-d)$。
>
> 初始指针在 $0$，依次累加相邻两次拨号之间的最小代价即可。

<!-- thinking:end -->

设上一位所在数字为 $\textit{pre}$，当前要拨到的数字为 $\textit{cur}$。由于拨号盘是环形的，从 $\textit{pre}$ 走到 $\textit{cur}$ 的最短距离为

$$
\min\bigl(|\textit{cur}-\textit{pre}|, 10-|\textit{cur}-\textit{pre}|\bigr).
$$

从初始位置 $0$ 开始遍历字符串中的每个字符，计算这一段最短距离并累加到答案中，然后更新 $\textit{pre}$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

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
