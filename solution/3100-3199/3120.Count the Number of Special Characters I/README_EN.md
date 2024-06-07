---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3120.Count%20the%20Number%20of%20Special%20Characters%20I/README_EN.md
rating: 1205
source: Weekly Contest 394 Q1
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [3120. Count the Number of Special Characters I](https://leetcode.com/problems/count-the-number-of-special-characters-i)

[中文文档](/solution/3100-3199/3120.Count%20the%20Number%20of%20Special%20Characters%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>word</code>. A letter is called <strong>special</strong> if it appears <strong>both</strong> in lowercase and uppercase in <code>word</code>.</p>

<p>Return the number of<em> </em><strong>special</strong> letters in<em> </em><code>word</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aaAbcBC&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The special characters in <code>word</code> are <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No character in <code>word</code> appears in uppercase.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;abBCab&quot;</span></p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>The only special character in <code>word</code> is <code>&#39;b&#39;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 50</code></li>
	<li><code>word</code> consists of only lowercase and uppercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table or Array

We use a hash table or array $s$ to record the characters that appear in the string $word$. Then we traverse the 26 letters. If both the lowercase and uppercase letters appear in $s$, the count of special characters is incremented by one.

Finally, return the count of special characters.

The time complexity is $O(n + |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Where $n$ is the length of the string $word$, and $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| \leq 128$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        s = set(word)
        return sum(a in s and b in s for a, b in zip(ascii_lowercase, ascii_uppercase))
```

#### Java

```java
class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] s = new boolean['z' + 1];
        for (int i = 0; i < word.length(); ++i) {
            s[word.charAt(i)] = true;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (s['a' + i] && s['A' + i]) {
                ++ans;
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
    int numberOfSpecialChars(string word) {
        vector<bool> s('z' + 1);
        for (char& c : word) {
            s[c] = true;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans += s['a' + i] && s['A' + i];
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSpecialChars(word string) (ans int) {
	s := make([]bool, 'z'+1)
	for _, c := range word {
		s[c] = true
	}
	for i := 0; i < 26; i++ {
		if s['a'+i] && s['A'+i] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfSpecialChars(word: string): number {
    const s: boolean[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => false);
    for (let i = 0; i < word.length; ++i) {
        s[word.charCodeAt(i)] = true;
    }
    let ans: number = 0;
    for (let i = 0; i < 26; ++i) {
        if (s['a'.charCodeAt(0) + i] && s['A'.charCodeAt(0) + i]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
