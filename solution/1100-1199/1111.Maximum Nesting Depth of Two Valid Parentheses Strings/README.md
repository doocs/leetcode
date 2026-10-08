---
comments: true
difficulty: 中等
rating: 1749
source: 第 144 场周赛 Q4
tags:
    - 栈
    - 字符串
    - 括号序列
---

<!-- problem:start -->

# [1111. 有效括号的嵌套深度](https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings)

[English Version](/solution/1100-1199/1111.Maximum%20Nesting%20Depth%20of%20Two%20Valid%20Parentheses%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个字符串仅由字符 <code>"("</code> 和 <code>")"</code> 组成，并且满足以下条件，则称为有效括号字符串（VPS）：</p>

<ul>
	<li>它是空字符串，或</li>
	<li>它可以表示为 <code>AB</code>（<code>A</code> 连接 <code>B</code>），其中 <code>A</code> 和 <code>B</code> 都是VPS，或者</li>
	<li>它可以表示为 <code>(A)</code>，其中 <code>A</code> 是一个 VPS。</li>
</ul>

<p>我们可以类似地定义任何 VPS <code>S</code> 的嵌套深度 <code>depth(S)</code> 如下：</p>

<ul>
	<li><code>depth("") = 0</code></li>
	<li><code>depth(A + B) = max(depth(A), depth(B))</code>，其中&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是 VPS</li>
	<li><code>depth("(" + A + ")") = 1 + depth(A)</code>，其中&nbsp;<code>A</code>&nbsp;是一个 VPS。</li>
</ul>

<p>例如，<code>""</code>，<code>"()()"</code>&nbsp;和&nbsp;<code>"()(()())"</code>&nbsp;都是 VPS（嵌套深度 0，1 和 2），并且&nbsp;<code>")("</code> 和&nbsp;<code>"(()"</code>&nbsp;不是 VPS。</p>

<p>给定一个 VPS 序列，将其拆分成两个不相交的子序列 <code>A</code> 和 <code>B</code>，使得 <code>A</code> 和 <code>B</code> 都是 VPS（且 <code>A.length + B.length = seq.length</code>）。这些子序列不一定是连续的。</p>

<p>例如，对于序列&nbsp;<code>123456789</code>，一种可能的拆分是：</p>

<ul data-end="822" data-start="776">
	<li data-end="800" data-start="776">
	<p data-end="800" data-start="778"><code data-end="799" data-start="778">A = {1, 3, 5, 7, 9}</code>，</p>
	</li>
	<li data-end="822" data-start="801">
	<p data-end="822" data-start="803"><code data-end="821" data-start="803">B = {2, 4, 6, 8}</code>。</p>
	</li>
	<li data-end="822" data-start="801">
	<p data-end="822" data-start="803">这对应于输出 <code>[0, 1, 0, 1, 0, 1, 0, 1, 0]</code>，其中 0 表示属于 <code>A</code>，1 表示属于 <code>B</code>。</p>
	</li>
</ul>

<p>现在选择 <strong>任意</strong> 这样的 <code>A</code> 和 <code>B</code>，使得 <code>max(depth(A), depth(B))</code> 的值是最小的。</p>

<p>返回一个&nbsp;<code>answer</code> 数组（长度为 <code>seq.length</code>），该数组编码了 <code>A</code> 和 <code>B</code> 的选择：如果 <code>seq[i]</code> 是 <code>A</code> 的一部分则 <code>answer[i] = 0</code>，否则 <code>answer[i] = 1</code>。请注意，尽管可能存在多种答案，但你可以返回其中任意一种。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>seq = "(()())"
<strong>输出：</strong>[0,1,1,1,1,0]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>seq = "()(())()"
<strong>输出：</strong>[0,0,0,1,1,0,1,1]
<strong>解释：</strong>本示例答案不唯一。
按此输出 A = "()()", B = "()()", max(depth(A), depth(B)) = 1，它们的深度最小。
像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = "()()()", B = "()", max(depth(A), depth(B)) = 1 。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;&nbsp;seq.size &lt;= 10000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>有效括号字符串：</strong></p>

<pre>
仅由&nbsp;<code>"("</code> 和&nbsp;<code>")"</code>&nbsp;构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。
下述几种情况同样属于有效括号字符串：

  1. 空字符串
  2. 连接，可以记作&nbsp;<code>AB</code>（<code>A</code> 与 <code>B</code> 连接），其中&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;都是有效括号字符串
  3. 嵌套，可以记作&nbsp;<code>(A)</code>，其中&nbsp;<code>A</code>&nbsp;是有效括号字符串
</pre>

<p><strong>嵌套深度：</strong></p>

<pre>
类似地，我们可以定义任意有效括号字符串 <code>s</code> 的 <strong>嵌套深度</strong>&nbsp;<code>depth(S)</code>：

  1.<code> s</code> 为空时，<code>depth("") = 0</code>
<code>  2. s</code> 为 <code>A</code> 与 <code>B</code> 连接时，<code>depth(A + B) = max(depth(A), depth(B))</code>，其中&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是有效括号字符串
<code>  3. s</code> 为嵌套情况，<code>depth("(" + A + ")") = 1 + depth(A)</code>，其中 <code>A</code> 是有效括号字符串

例如：<code>""</code>，<code>"()()"</code>，和&nbsp;<code>"()(()())"</code>&nbsp;都是有效括号字符串，嵌套深度分别为 0，1，2，而&nbsp;<code>")("</code> 和&nbsp;<code>"(()"</code>&nbsp;都不是有效括号字符串。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

<!-- thinking:start -->

> **思考**
>
> 每个括号都要指定组别，再检查两组是否都合法。 $n \le 10^4$，把每个位置的组别枚举一遍并不现实。改成贪心也可以：左括号分给当前较浅的一组，右括号跟回同一组。这样做是对的，但扫描时要同时记下两组的深度。
>
> 深度是沿嵌套一层层加上去的。一条深度为 $d$ 的链有 $d$ 层，分给两组之后，其中一组至少分到 $\lceil d/2 \rceil$ 层。把相邻层交错开，两组的深度就分别是 $\lceil d/2 \rceil$ 和 $\lfloor d/2 \rfloor$。
>
> 组号取层号的奇偶就够了，只留一个平衡度 $x$。左括号落在即将进入的那一层，要看加深之前的 $x$；右括号得跟着配对的左括号走，要看回退之后的 $x$。同一对括号读到的奇偶相同，拆出来的两个子序列仍然合法。

<!-- thinking:end -->

我们用变量 $x$ 记录当前的平衡度，也就是尚未匹配的左括号个数，它同时也是当前位置的嵌套深度。

从左到右扫描 $seq$。遇到左括号，先把 $x$ 的奇偶写入答案，偶数为 $0$、奇数为 $1$，然后 $x$ 加一。遇到右括号，先将 $x$ 减一，再按新的奇偶写入答案。匹配的一对括号读到同一个奇偶，进入同一组，因此两组都仍然是合法括号串。设原串嵌套深度为 $d$，两组深度的最大值等于 $\lceil d/2 \rceil$。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $seq$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDepthAfterSplit(self, seq: str) -> List[int]:
        ans = [0] * len(seq)
        x = 0
        for i, c in enumerate(seq):
            if c == "(":
                ans[i] = x & 1
                x += 1
            else:
                x -= 1
                ans[i] = x & 1
        return ans
```

#### Java

```java
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        for (int i = 0, x = 0; i < n; ++i) {
            if (seq.charAt(i) == '(') {
                ans[i] = x++ & 1;
            } else {
                ans[i] = --x & 1;
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
    vector<int> maxDepthAfterSplit(string seq) {
        int n = seq.size();
        vector<int> ans(n);
        for (int i = 0, x = 0; i < n; ++i) {
            if (seq[i] == '(') {
                ans[i] = x++ & 1;
            } else {
                ans[i] = --x & 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDepthAfterSplit(seq string) []int {
	n := len(seq)
	ans := make([]int, n)
	for i, x := 0, 0; i < n; i++ {
		if seq[i] == '(' {
			ans[i] = x & 1
			x++
		} else {
			x--
			ans[i] = x & 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxDepthAfterSplit(seq: string): number[] {
    const n = seq.length;
    const ans: number[] = new Array(n);
    for (let i = 0, x = 0; i < n; ++i) {
        if (seq[i] === '(') {
            ans[i] = x++ & 1;
        } else {
            ans[i] = --x & 1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_depth_after_split(seq: String) -> Vec<i32> {
        let n = seq.len();
        let mut ans = vec![0; n];
        let mut x = 0;
        for (i, c) in seq.bytes().enumerate() {
            if c == b'(' {
                ans[i] = x & 1;
                x += 1;
            } else {
                x -= 1;
                ans[i] = x & 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
