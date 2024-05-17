---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1111.Maximum%20Nesting%20Depth%20of%20Two%20Valid%20Parentheses%20Strings/README.md
rating: 1749
source: 第 144 场周赛 Q4
tags:
    - 栈
    - 字符串
---

<!-- problem:start -->

# [1111. 有效括号的嵌套深度](https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings)

[English Version](/solution/1100-1199/1111.Maximum%20Nesting%20Depth%20of%20Two%20Valid%20Parentheses%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>有效括号字符串 </strong>定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「<strong>有效括号字符串</strong>」部分。</p>

<p><strong>嵌套深度</strong> <code>depth</code> 定义：即有效括号字符串嵌套的层数，<code>depth(A)</code> 表示有效括号字符串 <code>A</code> 的嵌套深度。详情参见题末「<strong>嵌套深度</strong>」部分。</p>

<p>有效括号字符串类型与对应的嵌套深度计算方法如下图所示：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1111.Maximum%20Nesting%20Depth%20of%20Two%20Valid%20Parentheses%20Strings/images/1111.png" style="height: 152px; width: 600px;"></p>

<p>&nbsp;</p>

<p>给你一个「有效括号字符串」 <code>seq</code>，请你将其分成两个不相交的有效括号字符串，<code>A</code> 和&nbsp;<code>B</code>，并使这两个字符串的深度最小。</p>

<ul>
	<li>不相交：每个 <code>seq[i]</code> 只能分给 <code>A</code> 和 <code>B</code> 二者中的一个，不能既属于 <code>A</code> 也属于 <code>B</code> 。</li>
	<li><code>A</code> 或 <code>B</code> 中的元素在原字符串中可以不连续。</li>
	<li><code>A.length + B.length = seq.length</code></li>
	<li>深度最小：<code>max(depth(A), depth(B))</code>&nbsp;的可能取值最小。&nbsp;</li>
</ul>

<p>划分方案用一个长度为 <code>seq.length</code> 的答案数组 <code>answer</code> 表示，编码规则如下：</p>

<ul>
	<li><code>answer[i] = 0</code>，<code>seq[i]</code> 分给 <code>A</code> 。</li>
	<li><code>answer[i] = 1</code>，<code>seq[i]</code> 分给 <code>B</code> 。</li>
</ul>

<p>如果存在多个满足要求的答案，只需返回其中任意 <strong>一个 </strong>即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>seq = &quot;(()())&quot;
<strong>输出：</strong>[0,1,1,1,1,0]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>seq = &quot;()(())()&quot;
<strong>输出：</strong>[0,0,0,1,1,0,1,1]
<strong>解释：</strong>本示例答案不唯一。
按此输出 A = &quot;()()&quot;, B = &quot;()()&quot;, max(depth(A), depth(B)) = 1，它们的深度最小。
像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = &quot;()()()&quot;, B = &quot;()&quot;, max(depth(A), depth(B)) = 1 。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;&nbsp;seq.size &lt;= 10000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>有效括号字符串：</strong></p>

<pre>仅由&nbsp;<code>&quot;(&quot;</code> 和&nbsp;<code>&quot;)&quot;</code>&nbsp;构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。
下述几种情况同样属于有效括号字符串：

  1. 空字符串
  2. 连接，可以记作&nbsp;<code>AB</code>（<code>A</code> 与 <code>B</code> 连接），其中&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;都是有效括号字符串
  3. 嵌套，可以记作&nbsp;<code>(A)</code>，其中&nbsp;<code>A</code>&nbsp;是有效括号字符串
</pre>

<p><strong>嵌套深度：</strong></p>

<pre>类似地，我们可以定义任意有效括号字符串 <code>s</code> 的 <strong>嵌套深度</strong>&nbsp;<code>depth(S)</code>：

  1.<code> s</code> 为空时，<code>depth(&quot;&quot;) = 0</code>
<code>  2. s</code> 为 <code>A</code> 与 <code>B</code> 连接时，<code>depth(A + B) = max(depth(A), depth(B))</code>，其中&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是有效括号字符串
<code>  3. s</code> 为嵌套情况，<code>depth(&quot;(&quot; + A + &quot;)&quot;) = 1 + depth(A)</code>，其中 <code>A</code> 是有效括号字符串

例如：<code>&quot;&quot;</code>，<code>&quot;()()&quot;</code>，和&nbsp;<code>&quot;()(()())&quot;</code>&nbsp;都是有效括号字符串，嵌套深度分别为 0，1，2，而&nbsp;<code>&quot;)(&quot;</code> 和&nbsp;<code>&quot;(()&quot;</code>&nbsp;都不是有效括号字符串。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们用一个变量 $x$ 维护当前括号的平衡度，也就是左括号的数量减去右括号的数量。

遍历字符串 $seq$，更新 $x$ 的值。如果 $x$ 为奇数，我们将当前的左括号分给 $A$，否则分给 $B$。

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
