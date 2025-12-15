---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2207.Maximize%20Number%20of%20Subsequences%20in%20a%20String/README.md
rating: 1550
source: 第 74 场双周赛 Q2
tags:
    - 贪心
    - 字符串
    - 前缀和
---

<!-- problem:start -->

# [2207. 字符串中最多数目的子序列](https://leetcode.cn/problems/maximize-number-of-subsequences-in-a-string)

[English Version](/solution/2200-2299/2207.Maximize%20Number%20of%20Subsequences%20in%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>text</code>&nbsp;和另一个下标从 <strong>0</strong>&nbsp;开始且长度为 <code>2</code>&nbsp;的字符串&nbsp;<code>pattern</code>&nbsp;，两者都只包含小写英文字母。</p>

<p>你可以在 <code>text</code>&nbsp;中任意位置插入 <strong>一个</strong> 字符，这个插入的字符必须是&nbsp;<code>pattern[0]</code>&nbsp;<b>或者</b>&nbsp;<code>pattern[1]</code>&nbsp;。注意，这个字符可以插入在 <code>text</code>&nbsp;开头或者结尾的位置。</p>

<p>请你返回插入一个字符后，<code>text</code>&nbsp;中最多包含多少个等于 <code>pattern</code>&nbsp;的 <strong>子序列</strong>&nbsp;。</p>

<p><strong>子序列</strong> 指的是将一个字符串删除若干个字符后（也可以不删除），剩余字符保持原本顺序得到的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>text = "abdcdbc", pattern = "ac"
<b>输出：</b>4
<strong>解释：</strong>
如果我们在 text[1] 和 text[2] 之间添加 pattern[0] = 'a' ，那么我们得到 "ab<em><strong>a</strong></em>dcdbc" 。那么 "ac" 作为子序列出现 4 次。
其他得到 4 个 "ac" 子序列的方案还有 "<em><strong>a</strong></em>abdcdbc" 和 "abd<em><strong>a</strong></em>cdbc" 。
但是，"abdc<em><strong>a</strong></em>dbc" ，"abd<em><strong>c</strong></em>cdbc" 和 "abdcdbc<em><strong>c</strong></em>" 这些字符串虽然是可行的插入方案，但是只出现了 3 次 "ac" 子序列，所以不是最优解。
可以证明插入一个字符后，无法得到超过 4 个 "ac" 子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>text = "aabb", pattern = "ab"
<b>输出：</b>6
<strong>解释：</strong>
可以得到 6 个 "ab" 子序列的部分方案为 "<em><strong>a</strong></em>aabb" ，"aa<em><strong>a</strong></em>bb" 和 "aab<em><strong>b</strong></em>b" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10<sup>5</sup></code></li>
	<li><code>pattern.length == 2</code></li>
	<li><code>text</code> 和&nbsp;<code>pattern</code>&nbsp;都只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历 + 计数

我们可以使用两个变量 $x$ 和 $y$ 分别记录当前字符串中 $\textit{pattern}[0]$ 和 $\textit{pattern}[1]$ 出现的次数。

然后遍历字符串 $\textit{text}$，对于当前遍历到的字符 $c$：

- 如果 $c$ 等于 $\textit{pattern}[1]$，我们将 $y$ 加一，此时之前出现过的所有 $\textit{pattern}[0]$ 都可以和当前的 $c$ 组成一个 $\textit{pattern}$ 子序列，因此答案加上 $x$；
- 如果 $c$ 等于 $\textit{pattern}[0]$，我们将 $x$ 加一。

遍历结束后，由于我们可以插入一个字符，因此，如果我们在字符串开头加上 $\textit{pattern}[0]$，那么可以得到 $y$ 个 $\textit{pattern}$ 子序列；如果我们在字符串结尾加上 $\textit{pattern}[1]$，那么可以得到 $x$ 个 $\textit{pattern}$ 子序列。因此，我们将答案加上 $x$ 和 $y$ 中的较大值即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{text}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSubsequenceCount(self, text: str, pattern: str) -> int:
        ans = x = y = 0
        for c in text:
            if c == pattern[1]:
                y += 1
                ans += x
            if c == pattern[0]:
                x += 1
        ans += max(x, y)
        return ans
```

#### Java

```java
class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        long ans = 0;
        int x = 0, y = 0;
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == pattern.charAt(1)) {
                ++y;
                ans += x;
            }
            if (text.charAt(i) == pattern.charAt(0)) {
                ++x;
            }
        }
        ans += Math.max(x, y);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumSubsequenceCount(string text, string pattern) {
        long long ans = 0;
        int x = 0, y = 0;
        for (char& c : text) {
            if (c == pattern[1]) {
                ++y;
                ans += x;
            }
            if (c == pattern[0]) {
                ++x;
            }
        }
        ans += max(x, y);
        return ans;
    }
};
```

#### Go

```go
func maximumSubsequenceCount(text string, pattern string) (ans int64) {
	x, y := 0, 0
	for _, c := range text {
		if byte(c) == pattern[1] {
			y++
			ans += int64(x)
		}
		if byte(c) == pattern[0] {
			x++
		}
	}
	ans += int64(max(x, y))
	return
}
```

#### TypeScript

```ts
function maximumSubsequenceCount(text: string, pattern: string): number {
    let ans = 0;
    let [x, y] = [0, 0];
    for (const c of text) {
        if (c === pattern[1]) {
            ++y;
            ans += x;
        }
        if (c === pattern[0]) {
            ++x;
        }
    }
    ans += Math.max(x, y);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
