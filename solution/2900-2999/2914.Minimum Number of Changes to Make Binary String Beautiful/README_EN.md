---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2914.Minimum%20Number%20of%20Changes%20to%20Make%20Binary%20String%20Beautiful/README_EN.md
rating: 1479
source: Biweekly Contest 116 Q2
tags:
    - String
---

<!-- problem:start -->

# [2914. Minimum Number of Changes to Make Binary String Beautiful](https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful)

[中文文档](/solution/2900-2999/2914.Minimum%20Number%20of%20Changes%20to%20Make%20Binary%20String%20Beautiful/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> binary string <code>s</code> having an even length.</p>

<p>A string is <strong>beautiful</strong> if it&#39;s possible to partition it into one or more substrings such that:</p>

<ul>
	<li>Each substring has an <strong>even length</strong>.</li>
	<li>Each substring contains <strong>only</strong> <code>1</code>&#39;s or <strong>only</strong> <code>0</code>&#39;s.</li>
</ul>

<p>You can change any character in <code>s</code> to <code>0</code> or <code>1</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of changes required to make the string </em><code>s</code> <em>beautiful</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1001&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We change s[1] to 1 and s[3] to 0 to get string &quot;1100&quot;.
It can be seen that the string &quot;1100&quot; is beautiful because we can partition it into &quot;11|00&quot;.
It can be proven that 2 is the minimum number of changes needed to make the string beautiful.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We change s[1] to 1 to get string &quot;11&quot;.
It can be seen that the string &quot;11&quot; is beautiful because we can partition it into &quot;11&quot;.
It can be proven that 1 is the minimum number of changes needed to make the string beautiful.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0000&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> We don&#39;t need to make any changes as the string &quot;0000&quot; is beautiful already.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> has an even length.</li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We only need to traverse all odd indices $1, 3, 5, \cdots$ of the string $s$. If the current odd index is different from the previous index, i.e., $s[i] \ne s[i - 1]$, we need to modify the current character so that $s[i] = s[i - 1]$. Therefore, the answer needs to be incremented by $1$.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minChanges(self, s: str) -> int:
        return sum(s[i] != s[i - 1] for i in range(1, len(s), 2))
```

```java
class Solution {
    public int minChanges(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minChanges(string s) {
        int ans = 0;
        int n = s.size();
        for (int i = 1; i < n; i += 2) {
            ans += s[i] != s[i - 1];
        }
        return ans;
    }
};
```

```go
func minChanges(s string) (ans int) {
	for i := 1; i < len(s); i += 2 {
		if s[i] != s[i-1] {
			ans++
		}
	}
	return
}
```

```ts
function minChanges(s: string): number {
    let ans = 0;
    for (let i = 1; i < s.length; i += 2) {
        if (s[i] !== s[i - 1]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
