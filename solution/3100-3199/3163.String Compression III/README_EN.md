---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3163.String%20Compression%20III/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [3163. String Compression III](https://leetcode.com/problems/string-compression-iii)

[中文文档](/solution/3100-3199/3163.String%20Compression%20III/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>word</code>, compress it using the following algorithm:</p>

<ul>
	<li>Begin with an empty string <code>comp</code>. While <code>word</code> is <strong>not</strong> empty, use the following operation:

    <ul>
    	<li>Remove a maximum length prefix of <code>word</code> made of a <em>single character</em> <code>c</code> repeating <strong>at most</strong> 9 times.</li>
    	<li>Append the length of the prefix followed by <code>c</code> to <code>comp</code>.</li>
    </ul>
    </li>

</ul>

<p>Return the string <code>comp</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;abcde&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1a1b1c1d1e&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>comp = &quot;&quot;</code>. Apply the operation 5 times, choosing <code>&quot;a&quot;</code>, <code>&quot;b&quot;</code>, <code>&quot;c&quot;</code>, <code>&quot;d&quot;</code>, and <code>&quot;e&quot;</code> as the prefix in each operation.</p>

<p>For each prefix, append <code>&quot;1&quot;</code> followed by the character to <code>comp</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aaaaaaaaaaaaaabb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;9a5a2b&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>comp = &quot;&quot;</code>. Apply the operation 3 times, choosing <code>&quot;aaaaaaaaa&quot;</code>, <code>&quot;aaaaa&quot;</code>, and <code>&quot;bb&quot;</code> as the prefix in each operation.</p>

<ul>
	<li>For prefix <code>&quot;aaaaaaaaa&quot;</code>, append <code>&quot;9&quot;</code> followed by <code>&quot;a&quot;</code> to <code>comp</code>.</li>
	<li>For prefix <code>&quot;aaaaa&quot;</code>, append <code>&quot;5&quot;</code> followed by <code>&quot;a&quot;</code> to <code>comp</code>.</li>
	<li>For prefix <code>&quot;bb&quot;</code>, append <code>&quot;2&quot;</code> followed by <code>&quot;b&quot;</code> to <code>comp</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We can use two pointers to count the consecutive occurrences of each character. Suppose the current character $c$ appears consecutively $k$ times, then we divide $k$ into several $x$, each $x$ is at most $9$, then we concatenate $x$ and $c$, and append each $x$ and $c$ to the result.

Finally, return the result.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def compressedString(self, word: str) -> str:
        g = groupby(word)
        ans = []
        for c, v in g:
            k = len(list(v))
            while k:
                x = min(9, k)
                ans.append(str(x) + c)
                k -= x
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String compressedString(String word) {
        StringBuilder ans = new StringBuilder();
        int n = word.length();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && word.charAt(j) == word.charAt(i)) {
                ++j;
            }
            int k = j - i;
            while (k > 0) {
                int x = Math.min(9, k);
                ans.append(x).append(word.charAt(i));
                k -= x;
            }
            i = j;
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string compressedString(string word) {
        string ans;
        int n = word.length();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && word[j] == word[i]) {
                ++j;
            }
            int k = j - i;
            while (k > 0) {
                int x = min(9, k);
                ans.push_back('0' + x);
                ans.push_back(word[i]);
                k -= x;
            }
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func compressedString(word string) string {
	ans := []byte{}
	n := len(word)
	for i := 0; i < n; {
		j := i + 1
		for j < n && word[j] == word[i] {
			j++
		}
		k := j - i
		for k > 0 {
			x := min(9, k)
			ans = append(ans, byte('0'+x))
			ans = append(ans, word[i])
			k -= x
		}
		i = j
	}
	return string(ans)
}
```

#### TypeScript

```ts
function compressedString(word: string): string {
    const ans: string[] = [];
    const n = word.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && word[j] === word[i]) {
            ++j;
        }
        let k = j - i;
        while (k) {
            const x = Math.min(k, 9);
            ans.push(x + word[i]);
            k -= x;
        }
        i = j;
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
