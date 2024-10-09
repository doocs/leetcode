---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README_EN.md
rating: 1426
source: Weekly Contest 126 Q2
tags:
    - Stack
    - String
---

<!-- problem:start -->

# [1003. Check If Word Is Valid After Substitutions](https://leetcode.com/problems/check-if-word-is-valid-after-substitutions)

[中文文档](/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, determine if it is <strong>valid</strong>.</p>

<p>A string <code>s</code> is <strong>valid</strong> if, starting with an empty string <code>t = &quot;&quot;</code>, you can <strong>transform </strong><code>t</code><strong> into </strong><code>s</code> after performing the following operation <strong>any number of times</strong>:</p>

<ul>
	<li>Insert string <code>&quot;abc&quot;</code> into any position in <code>t</code>. More formally, <code>t</code> becomes <code>t<sub>left</sub> + &quot;abc&quot; + t<sub>right</sub></code>, where <code>t == t<sub>left</sub> + t<sub>right</sub></code>. Note that <code>t<sub>left</sub></code> and <code>t<sub>right</sub></code> may be <strong>empty</strong>.</li>
</ul>

<p>Return <code>true</code> <em>if </em><code>s</code><em> is a <strong>valid</strong> string, otherwise, return</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabcbc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
&quot;&quot; -&gt; &quot;<u>abc</u>&quot; -&gt; &quot;a<u>abc</u>bc&quot;
Thus, &quot;aabcbc&quot; is valid.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcababcc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
&quot;&quot; -&gt; &quot;<u>abc</u>&quot; -&gt; &quot;abc<u>abc</u>&quot; -&gt; &quot;abcabc<u>abc</u>&quot; -&gt; &quot;abcabcab<u>abc</u>c&quot;
Thus, &quot;abcabcababcc&quot; is valid.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abccba&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to get &quot;abccba&quot; using the operation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack

We observe the operations in the problem and find that each time a string $\textit{"abc"}$ is inserted at any position in the string. Therefore, after each insertion operation, the length of the string increases by $3$. If the string $s$ is valid, its length must be a multiple of $3$. Thus, we first check the length of the string $s$. If it is not a multiple of $3$, then $s$ must be invalid, and we can directly return $\textit{false}$.

Next, we traverse each character $c$ in the string $s$. We first push the character $c$ onto the stack $t$. If the length of the stack $t$ is greater than or equal to $3$, and the top three elements of the stack form the string $\textit{"abc"}$, then we pop the top three elements from the stack. We then continue to traverse the next character in the string $s$.

After the traversal, if the stack $t$ is empty, it means the string $s$ is valid, and we return $\textit{true}$; otherwise, we return $\textit{false}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isValid(self, s: str) -> bool:
        if len(s) % 3:
            return False
        t = []
        for c in s:
            t.append(c)
            if ''.join(t[-3:]) == 'abc':
                t[-3:] = []
        return not t
```

#### Java

```java
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 3 > 0) {
            return false;
        }
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            t.append(c);
            if (t.length() >= 3 && "abc".equals(t.substring(t.length() - 3))) {
                t.delete(t.length() - 3, t.length());
            }
        }
        return t.isEmpty();
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isValid(string s) {
        if (s.size() % 3) {
            return false;
        }
        string t;
        for (char c : s) {
            t.push_back(c);
            if (t.size() >= 3 && t.substr(t.size() - 3, 3) == "abc") {
                t.erase(t.end() - 3, t.end());
            }
        }
        return t.empty();
    }
};
```

#### Go

```go
func isValid(s string) bool {
	if len(s)%3 > 0 {
		return false
	}
	t := []byte{}
	for i := range s {
		t = append(t, s[i])
		if len(t) >= 3 && string(t[len(t)-3:]) == "abc" {
			t = t[:len(t)-3]
		}
	}
	return len(t) == 0
}
```

#### TypeScript

```ts
function isValid(s: string): boolean {
    if (s.length % 3 !== 0) {
        return false;
    }
    const t: string[] = [];
    for (const c of s) {
        t.push(c);
        if (t.slice(-3).join('') === 'abc') {
            t.splice(-3);
        }
    }
    return t.length === 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
