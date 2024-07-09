---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3210.Find%20the%20Encrypted%20String/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [3210. Find the Encrypted String](https://leetcode.com/problems/find-the-encrypted-string)

[中文文档](/solution/3200-3299/3210.Find%20the%20Encrypted%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>. Encrypt the string using the following algorithm:</p>

<ul>
	<li>For each character <code>c</code> in <code>s</code>, replace <code>c</code> with the <code>k<sup>th</sup></code> character after <code>c</code> in the string (in a cyclic manner).</li>
</ul>

<p>Return the <em>encrypted string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;dart&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;tdar&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, the 3<sup>rd</sup> character after <code>&#39;d&#39;</code> is <code>&#39;t&#39;</code>.</li>
	<li>For <code>i = 1</code>, the 3<sup>rd</sup> character after <code>&#39;a&#39;</code> is <code>&#39;d&#39;</code>.</li>
	<li>For <code>i = 2</code>, the 3<sup>rd</sup> character after <code>&#39;r&#39;</code> is <code>&#39;a&#39;</code>.</li>
	<li>For <code>i = 3</code>, the 3<sup>rd</sup> character after <code>&#39;t&#39;</code> is <code>&#39;r&#39;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaa&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aaa&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>As all the characters are the same, the encrypted string will also be the same.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can use the simulation method. For the $i^{th}$ character of the string, we replace it with the character at position $(i + k) \bmod n$ of the string.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getEncryptedString(self, s: str, k: int) -> str:
        cs = list(s)
        n = len(s)
        for i in range(n):
            cs[i] = s[(i + k) % n]
        return "".join(cs)
```

#### Java

```java
class Solution {
    public String getEncryptedString(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; ++i) {
            cs[i] = s.charAt((i + k) % n);
        }
        return new String(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string getEncryptedString(string s, int k) {
        int n = s.length();
        string cs(n, ' ');
        for (int i = 0; i < n; ++i) {
            cs[i] = s[(i + k) % n];
        }
        return cs;
    }
};
```

#### Go

```go
func getEncryptedString(s string, k int) string {
	cs := []byte(s)
	for i := range s {
		cs[i] = s[(i+k)%len(s)]
	}
	return string(cs)
}
```

#### TypeScript

```ts
function getEncryptedString(s: string, k: number): string {
    const cs: string[] = [];
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        cs[i] = s[(i + k) % n];
    }
    return cs.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
