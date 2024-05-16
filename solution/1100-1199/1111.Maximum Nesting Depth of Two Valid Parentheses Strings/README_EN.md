---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1111.Maximum%20Nesting%20Depth%20of%20Two%20Valid%20Parentheses%20Strings/README_EN.md
rating: 1749
source: Weekly Contest 144 Q4
tags:
    - Stack
    - String
---

# [1111. Maximum Nesting Depth of Two Valid Parentheses Strings](https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings)

[中文文档](/solution/1100-1199/1111.Maximum%20Nesting%20Depth%20of%20Two%20Valid%20Parentheses%20Strings/README.md)

## Description

<p>A string is a <em>valid parentheses string</em>&nbsp;(denoted VPS) if and only if it consists of <code>&quot;(&quot;</code> and <code>&quot;)&quot;</code> characters only, and:</p>

<ul>

    <li>It is the empty string, or</li>

    <li>It can be written as&nbsp;<code>AB</code>&nbsp;(<code>A</code>&nbsp;concatenated with&nbsp;<code>B</code>), where&nbsp;<code>A</code>&nbsp;and&nbsp;<code>B</code>&nbsp;are VPS&#39;s, or</li>

    <li>It can be written as&nbsp;<code>(A)</code>, where&nbsp;<code>A</code>&nbsp;is a VPS.</li>

</ul>

<p>We can&nbsp;similarly define the <em>nesting depth</em> <code>depth(S)</code> of any VPS <code>S</code> as follows:</p>

<ul>

    <li><code>depth(&quot;&quot;) = 0</code></li>

    <li><code>depth(A + B) = max(depth(A), depth(B))</code>, where <code>A</code> and <code>B</code> are VPS&#39;s</li>

    <li><code>depth(&quot;(&quot; + A + &quot;)&quot;) = 1 + depth(A)</code>, where <code>A</code> is a VPS.</li>

</ul>

<p>For example,&nbsp; <code>&quot;&quot;</code>,&nbsp;<code>&quot;()()&quot;</code>, and&nbsp;<code>&quot;()(()())&quot;</code>&nbsp;are VPS&#39;s (with nesting depths 0, 1, and 2), and <code>&quot;)(&quot;</code> and <code>&quot;(()&quot;</code> are not VPS&#39;s.</p>

<p>&nbsp;</p>

<p>Given a VPS <font face="monospace">seq</font>, split it into two disjoint subsequences <code>A</code> and <code>B</code>, such that&nbsp;<code>A</code> and <code>B</code> are VPS&#39;s (and&nbsp;<code>A.length + B.length = seq.length</code>).</p>

<p>Now choose <strong>any</strong> such <code>A</code> and <code>B</code> such that&nbsp;<code>max(depth(A), depth(B))</code> is the minimum possible value.</p>

<p>Return an <code>answer</code> array (of length <code>seq.length</code>) that encodes such a&nbsp;choice of <code>A</code> and <code>B</code>:&nbsp; <code>answer[i] = 0</code> if <code>seq[i]</code> is part of <code>A</code>, else <code>answer[i] = 1</code>.&nbsp; Note that even though multiple answers may exist, you may return any of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> seq = &quot;(()())&quot;
<strong>Output:</strong> [0,1,1,1,1,0]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> seq = &quot;()(())()&quot;
<strong>Output:</strong> [0,0,0,1,1,0,1,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= seq.size &lt;= 10000</code></li>
</ul>

## Solutions

### Solution 1: Greedy

We use a variable $x$ to maintain the current balance of parentheses, which is the number of left parentheses minus the number of right parentheses.

We traverse the string $seq$, updating the value of $x$. If $x$ is odd, we assign the current left parenthesis to $A$, otherwise we assign it to $B$.

The time complexity is $O(n)$, where $n$ is the length of the string $seq$. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

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

<!-- end -->
