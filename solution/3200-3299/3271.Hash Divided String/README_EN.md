---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3271.Hash%20Divided%20String/README_EN.md
rating: 1292
source: Biweekly Contest 138 Q2
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [3271. Hash Divided String](https://leetcode.com/problems/hash-divided-string)

[中文文档](/solution/3200-3299/3271.Hash%20Divided%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> and an integer <code>k</code>, where <code>n</code> is a <strong>multiple</strong> of <code>k</code>. Your task is to hash the string <code>s</code> into a new string called <code>result</code>, which has a length of <code>n / k</code>.</p>

<p>First, divide <code>s</code> into <code>n / k</code> <strong><span data-keyword="substring-nonempty">substrings</span></strong>, each with a length of <code>k</code>. Then, initialize <code>result</code> as an <strong>empty</strong> string.</p>

<p>For each <strong>substring</strong> in order from the beginning:</p>

<ul>
	<li>The <strong>hash value</strong> of a character is the index of that characte<!-- notionvc: 4b67483a-fa95-40b6-870d-2eacd9bc18d8 -->r in the <strong>English alphabet</strong> (e.g., <code>&#39;a&#39; &rarr;<!-- notionvc: d3f8e4c2-23cd-41ad-a14b-101dfe4c5aba --> 0</code>, <code>&#39;b&#39; &rarr;<!-- notionvc: d3f8e4c2-23cd-41ad-a14b-101dfe4c5aba --> 1</code>, ..., <code>&#39;z&#39; &rarr;<!-- notionvc: d3f8e4c2-23cd-41ad-a14b-101dfe4c5aba --> 25</code>).</li>
	<li>Calculate the <em>sum</em> of all the <strong>hash values</strong> of the characters in the substring.</li>
	<li>Find the remainder of this sum when divided by 26, which is called <code>hashedChar</code>.</li>
	<li>Identify the character in the English lowercase alphabet that corresponds to <code>hashedChar</code>.</li>
	<li>Append that character to the end of <code>result</code>.</li>
</ul>

<p>Return <code>result</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcd&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;bf&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>First substring: <code>&quot;ab&quot;</code>, <code>0 + 1 = 1</code>, <code>1 % 26 = 1</code>, <code>result[0] = &#39;b&#39;</code>.</p>

<p>Second substring: <code>&quot;cd&quot;</code>, <code>2 + 3 = 5</code>, <code>5 % 26 = 5</code>, <code>result[1] = &#39;f&#39;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;mxz&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;i&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The only substring: <code>&quot;mxz&quot;</code>, <code>12 + 23 + 25 = 60</code>, <code>60 % 26 = 8</code>, <code>result[0] = &#39;i&#39;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>k &lt;= s.length &lt;= 1000</code></li>
	<li><code>s.length</code> is divisible by <code>k</code>.</li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate the process according to the steps described in the problem.

Traverse the string $s$, and each time take $k$ characters, calculate the sum of their hash values, denoted as $t$. Then, take $t$ modulo $26$ to find the corresponding character and add it to the end of the result string.

Finally, return the result string.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. Ignoring the space consumption of the answer string, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stringHash(self, s: str, k: int) -> str:
        ans = []
        for i in range(0, len(s), k):
            t = 0
            for j in range(i, i + k):
                t += ord(s[j]) - ord("a")
            hashedChar = t % 26
            ans.append(chr(ord("a") + hashedChar))
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String stringHash(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i += k) {
            int t = 0;
            for (int j = i; j < i + k; ++j) {
                t += s.charAt(j) - 'a';
            }
            int hashedChar = t % 26;
            ans.append((char) ('a' + hashedChar));
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string stringHash(string s, int k) {
        string ans;
        int n = s.length();
        for (int i = 0; i < n; i += k) {
            int t = 0;
            for (int j = i; j < i + k; ++j) {
                t += s[j] - 'a';
            }
            int hashedChar = t % 26;
            ans += ('a' + hashedChar);
        }
        return ans;
    }
};
```

#### Go

```go
func stringHash(s string, k int) string {
	n := len(s)
	ans := make([]byte, 0, n/k)

	for i := 0; i < n; i += k {
		t := 0
		for j := i; j < i+k; j++ {
			t += int(s[j] - 'a')
		}
		hashedChar := t % 26
		ans = append(ans, 'a'+byte(hashedChar))
	}

	return string(ans)
}
```

#### TypeScript

```ts
function stringHash(s: string, k: number): string {
    const ans: string[] = [];
    const n: number = s.length;

    for (let i = 0; i < n; i += k) {
        let t: number = 0;
        for (let j = i; j < i + k; j++) {
            t += s.charCodeAt(j) - 97;
        }
        const hashedChar: number = t % 26;
        ans.push(String.fromCharCode(97 + hashedChar));
    }

    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
