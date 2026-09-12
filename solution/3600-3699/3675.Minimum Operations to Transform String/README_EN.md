---
comments: true
difficulty: Medium
rating: 1414
source: Weekly Contest 466 Q2
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [3675. Minimum Operations to Transform String](https://leetcode.com/problems/minimum-operations-to-transform-string)

[中文文档](/solution/3600-3699/3675.Minimum%20Operations%20to%20Transform%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting only of lowercase English letters.</p>

<p>You can perform the following operation any number of times (including zero):</p>

<ul>
	<li>
	<p>Choose any character <code>c</code> in the string and replace <strong>every</strong> occurrence of <code>c</code> with the <strong>next</strong> lowercase letter in the English alphabet.</p>
	</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to transform <code>s</code> into a string consisting of <strong>only</strong> <code>&#39;a&#39;</code> characters.</p>

<p><strong>Note: </strong>Consider the alphabet as circular, thus <code>&#39;a&#39;</code> comes after <code>&#39;z&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;yz&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Change <code>&#39;y&#39;</code> to <code>&#39;z&#39;</code> to get <code>&quot;zz&quot;</code>.</li>
	<li>Change <code>&#39;z&#39;</code> to <code>&#39;a&#39;</code> to get <code>&quot;aa&quot;</code>.</li>
	<li>Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;a&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The string <code>&quot;a&quot;</code> only consists of <code>&#39;a&#39;</code>​​​​​​​ characters. Thus, the answer is 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

<!-- thinking:start -->

> **Thinking**
>
> One operation advances every occurrence of a chosen letter. The target is the all-$a$ string, so each non-$a$ character must walk forward to $a$.
>
> Operations on the same letter apply in parallel, and the total is the farthest distance to $a$, i.e. the maximum of $26-(c-\texttt{a})$.
>
> An all-$a$ string needs $0$ operations. One scan records that maximum.

<!-- thinking:end -->

According to the problem description, we always start from the character 'b' and successively change each character to the next one until it becomes 'a'. Therefore, we only need to find the character in the string that is farthest from 'a' and calculate its distance to 'a' to get the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, s: str) -> int:
        return max((26 - (ord(c) - 97) for c in s if c != "a"), default=0)
```

#### Java

```java
class Solution {
    public int minOperations(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c != 'a') {
                ans = Math.max(ans, 26 - (c - 'a'));
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
    int minOperations(string s) {
        int ans = 0;
        for (char c : s) {
            if (c != 'a') {
                ans = max(ans, 26 - (c - 'a'));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(s string) (ans int) {
	for _, c := range s {
		if c != 'a' {
			ans = max(ans, 26-int(c-'a'))
		}
	}
	return
}
```

#### TypeScript

```ts
function minOperations(s: string): number {
    let ans = 0;
    for (const c of s) {
        if (c !== 'a') {
            ans = Math.max(ans, 26 - (c.charCodeAt(0) - 97));
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
