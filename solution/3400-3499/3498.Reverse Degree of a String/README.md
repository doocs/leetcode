---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3498.Reverse%20Degree%20of%20a%20String/README.md
---

<!-- problem:start -->

# [3498. 字符串的反转度](https://leetcode.cn/problems/reverse-degree-of-a-string)

[English Version](/solution/3400-3499/3498.Reverse%20Degree%20of%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>，计算其 <strong>反转度</strong>。</p>

<p><strong>反转度</strong>的计算方法如下：</p>

<ol>
	<li>对于每个字符，将其在 <strong>反转</strong> 字母表中的位置（<code>'a'</code> = 26, <code>'b'</code> = 25, ..., <code>'z'</code> = 1）与其在字符串中的位置（下标从<strong>1 </strong>开始）相乘。</li>
	<li>将这些乘积加起来，得到字符串中所有字符的和。</li>
</ol>

<p>返回 <strong>反转度</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc"</span></p>

<p><strong>输出：</strong> <span class="example-io">148</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">字母</th>
			<th style="border: 1px solid black;">反转字母表中的位置</th>
			<th style="border: 1px solid black;">字符串中的位置</th>
			<th style="border: 1px solid black;">乘积</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>'a'</code></td>
			<td style="border: 1px solid black;">26</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">26</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>'b'</code></td>
			<td style="border: 1px solid black;">25</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">50</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>'c'</code></td>
			<td style="border: 1px solid black;">24</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">72</td>
		</tr>
	</tbody>
</table>

<p>反转度是 <code>26 + 50 + 72 = 148</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "zaza"</span></p>

<p><strong>输出：</strong> <span class="example-io">160</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">字母</th>
			<th style="border: 1px solid black;">反转字母表中的位置</th>
			<th style="border: 1px solid black;">字符串中的位置</th>
			<th style="border: 1px solid black;">乘积</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>'z'</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>'a'</code></td>
			<td style="border: 1px solid black;">26</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">52</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>'z'</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>'a'</code></td>
			<td style="border: 1px solid black;">26</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">104</td>
		</tr>
	</tbody>
</table>
</div>

<p>反转度是 <code>1 + 52 + 3 + 104 = 160</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 仅包含小写字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以模拟字符串中每个字符的反转度。对于每个字符，我们计算它在反转字母表中的位置，然后乘以它在字符串中的位置，最后将所有结果相加即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseDegree(self, s: str) -> int:
        ans = 0
        for i, c in enumerate(s, 1):
            x = 26 - (ord(c) - ord("a"))
            ans += i * x
        return ans
```

#### Java

```java
class Solution {
    public int reverseDegree(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = 26 - (s.charAt(i - 1) - 'a');
            ans += i * x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int reverseDegree(string s) {
        int n = s.length();
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = 26 - (s[i - 1] - 'a');
            ans += i * x;
        }
        return ans;
    }
};
```

#### Go

```go
func reverseDegree(s string) (ans int) {
	for i, c := range s {
		x := 26 - int(c-'a')
		ans += (i + 1) * x
	}
	return
}
```

#### TypeScript

```ts
function reverseDegree(s: string): number {
    let ans = 0;
    for (let i = 1; i <= s.length; ++i) {
        const x = 26 - (s.charCodeAt(i - 1) - 'a'.charCodeAt(0));
        ans += i * x;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
