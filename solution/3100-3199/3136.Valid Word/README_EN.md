---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3136.Valid%20Word/README_EN.md
rating: 1249
source: Weekly Contest 396 Q1
tags:
    - String
---

<!-- problem:start -->

# [3136. Valid Word](https://leetcode.com/problems/valid-word)

[中文文档](/solution/3100-3199/3136.Valid%20Word/README.md)

## Description

<!-- description:start -->

<p>A word is considered <strong>valid</strong> if:</p>

<ul>
	<li>It contains a <strong>minimum</strong> of 3 characters.</li>
	<li>It contains only digits (0-9), and English letters (uppercase and lowercase).</li>
	<li>It includes <strong>at least</strong> one <strong>vowel</strong>.</li>
	<li>It includes <strong>at least</strong> one <strong>consonant</strong>.</li>
</ul>

<p>You are given a string <code>word</code>.</p>

<p>Return <code>true</code> if <code>word</code> is valid, otherwise, return <code>false</code>.</p>

<p><strong>Notes:</strong></p>

<ul>
	<li><code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>, and their uppercases are <strong>vowels</strong>.</li>
	<li>A <strong>consonant</strong> is an English letter that is not a vowel.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;234Adas&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>This word satisfies the conditions.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;b3&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>The length of this word is fewer than 3, and does not have a vowel.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;a3$e&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>This word contains a <code>&#39;$&#39;</code> character and does not have a consonant.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 20</code></li>
	<li><code>word</code> consists of English uppercase and lowercase letters, digits, <code>&#39;@&#39;</code>, <code>&#39;#&#39;</code>, and <code>&#39;$&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

First, we check if the length of the string is less than 3. If it is, we return `false`.

Next, we iterate through the string, checking if each character is a letter or a number. If it's not, we return `false`. Otherwise, we check if the character is a vowel. If it is, we set `has_vowel` to `true`. If it's not, we set `has_consonant` to `true`.

Finally, if both `has_vowel` and `has_consonant` are `true`, we return `true`. Otherwise, we return `false`.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isValid(self, word: str) -> bool:
        if len(word) < 3:
            return False
        has_vowel = has_consonant = False
        vs = set("aeiouAEIOU")
        for c in word:
            if not c.isalnum():
                return False
            if c.isalpha():
                if c in vs:
                    has_vowel = True
                else:
                    has_consonant = True
        return has_vowel and has_consonant
```

#### Java

```java
class Solution {
    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }
        boolean hasVowel = false, hasConsonant = false;
        boolean[] vs = new boolean[26];
        for (char c : "aeiou".toCharArray()) {
            vs[c - 'a'] = true;
        }
        for (char c : word.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                if (vs[Character.toLowerCase(c) - 'a']) {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return hasVowel && hasConsonant;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isValid(string word) {
        if (word.size() < 3) {
            return false;
        }
        bool has_vowel = false, has_consonant = false;
        bool vs[26]{};
        string vowels = "aeiou";
        for (char c : vowels) {
            vs[c - 'a'] = true;
        }
        for (char c : word) {
            if (isalpha(c)) {
                if (vs[tolower(c) - 'a']) {
                    has_vowel = true;
                } else {
                    has_consonant = true;
                }
            } else if (!isdigit(c)) {
                return false;
            }
        }
        return has_vowel && has_consonant;
    }
};
```

#### Go

```go
func isValid(word string) bool {
	if len(word) < 3 {
		return false
	}
	hasVowel := false
	hasConsonant := false
	vs := make([]bool, 26)
	for _, c := range "aeiou" {
		vs[c-'a'] = true
	}
	for _, c := range word {
		if unicode.IsLetter(c) {
			if vs[unicode.ToLower(c)-'a'] {
				hasVowel = true
			} else {
				hasConsonant = true
			}
		} else if !unicode.IsDigit(c) {
			return false
		}
	}
	return hasVowel && hasConsonant
}
```

#### TypeScript

```ts
function isValid(word: string): boolean {
    if (word.length < 3) {
        return false;
    }
    let hasVowel: boolean = false;
    let hasConsonant: boolean = false;
    const vowels: Set<string> = new Set(['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']);
    for (const c of word) {
        if (!c.match(/[a-zA-Z0-9]/)) {
            return false;
        }
        if (/[a-zA-Z]/.test(c)) {
            if (vowels.has(c)) {
                hasVowel = true;
            } else {
                hasConsonant = true;
            }
        }
    }
    return hasVowel && hasConsonant;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_valid(word: String) -> bool {
        if word.len() < 3 {
            return false;
        }

        let mut has_vowel = false;
        let mut has_consonant = false;
        let vowels = ['a', 'e', 'i', 'o', 'u'];

        for c in word.chars() {
            if !c.is_alphanumeric() {
                return false;
            }
            if c.is_alphabetic() {
                let lower_c = c.to_ascii_lowercase();
                if vowels.contains(&lower_c) {
                    has_vowel = true;
                } else {
                    has_consonant = true;
                }
            }
        }

        has_vowel && has_consonant
    }
}
```

#### C#

```cs
public class Solution {
    public bool IsValid(string word) {
        if (word.Length < 3) {
            return false;
        }

        bool hasVowel = false, hasConsonant = false;
        bool[] vs = new bool[26];
        foreach (char c in "aeiou") {
            vs[c - 'a'] = true;
        }

        foreach (char c in word) {
            if (char.IsLetter(c)) {
                char lower = char.ToLower(c);
                if (vs[lower - 'a']) {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            } else if (!char.IsDigit(c)) {
                return false;
            }
        }

        return hasVowel && hasConsonant;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
