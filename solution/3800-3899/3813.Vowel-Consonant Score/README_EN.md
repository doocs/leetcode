---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3813.Vowel-Consonant%20Score/README_EN.md
rating: 1216
source: Weekly Contest 485 Q1
---

<!-- problem:start -->

# [3813. Vowel-Consonant Score](https://leetcode.com/problems/vowel-consonant-score)

[中文文档](/solution/3800-3899/3813.Vowel-Consonant%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters, spaces, and digits.</p>

<p>Let <code>v</code> be the number of vowels in <code>s</code> and <code>c</code> be the number of consonants in <code>s</code>.</p>

<p>A vowel is one of the letters <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, or <code>&#39;u&#39;</code>, while any other letter in the English alphabet is considered a consonant.</p>

<p>The <strong>score</strong> of the string <code>s</code> is defined as follows:</p>

<ul>
	<li>If <code>c &gt; 0</code>, the <code>score = floor(v / c)</code> where floor denotes <strong>rounding down</strong> to the nearest integer.</li>
	<li>Otherwise, the <code>score = 0</code>.</li>
</ul>

<p>Return an integer denoting the score of the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cooear&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>s = &quot;cooear&quot;</code> contains <code>v = 4</code> vowels <code>(&#39;o&#39;, &#39;o&#39;, &#39;e&#39;, &#39;a&#39;)</code> and <code>c = 2</code> consonants <code>(&#39;c&#39;, &#39;r&#39;)</code>.</p>

<p>The score is <code>floor(v / c) = floor(4 / 2) = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;axeyizou&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>s = &quot;axeyizou&quot;</code> contains <code>v = 5</code> vowels <code>(&#39;a&#39;, &#39;e&#39;, &#39;i&#39;, &#39;o&#39;, &#39;u&#39;)</code> and <code>c = 3</code> consonants <code>(&#39;x&#39;, &#39;y&#39;, &#39;z&#39;)</code>.</p>

<p>The score is <code>floor(v / c) = floor(5 / 3) = 1</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;au 123&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The string <code>s = &quot;au 123&quot;</code> contains no consonants <code>(c = 0)</code>, so the score is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters, spaces and digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We iterate through the string to count the number of vowels and consonants, denoted as $v$ and $c$, respectively. Finally, we calculate the score based on the problem description.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def vowelConsonantScore(self, s: str) -> int:
        v = c = 0
        for ch in s:
            if ch.isalpha():
                c += 1
                if ch in "aeiou":
                    v += 1
        c -= v
        return 0 if c == 0 else v // c
```

#### Java

```java
class Solution {
    public int vowelConsonantScore(String s) {
        int v = 0, c = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                c++;
                if ("aeiou".indexOf(ch) != -1) {
                    v++;
                }
            }
        }
        c -= v;
        return c == 0 ? 0 : v / c;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int vowelConsonantScore(string s) {
        int v = 0, c = 0;
        for (char ch : s) {
            if (isalpha(ch)) {
                c++;
                if (string("aeiou").find(ch) != string::npos) {
                    v++;
                }
            }
        }
        c -= v;
        return c == 0 ? 0 : v / c;
    }
};
```

#### Go

```go
func vowelConsonantScore(s string) int {
	v, c := 0, 0
	for _, ch := range s {
		if unicode.IsLetter(ch) {
			c++
			if strings.ContainsRune("aeiou", ch) {
				v++
			}
		}
	}
	c -= v
	if c == 0 {
		return 0
	}
	return v / c
}
```

#### TypeScript

```ts
function vowelConsonantScore(s: string): number {
    let [v, c] = [0, 0];
    for (const ch of s) {
        if (/[a-zA-Z]/.test(ch)) {
            c++;
            if ('aeiou'.includes(ch)) {
                v++;
            }
        }
    }
    c -= v;
    return c === 0 ? 0 : Math.floor(v / c);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
