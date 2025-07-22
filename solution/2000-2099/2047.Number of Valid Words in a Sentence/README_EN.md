---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2047.Number%20of%20Valid%20Words%20in%20a%20Sentence/README_EN.md
rating: 1471
source: Weekly Contest 264 Q1
tags:
    - String
---

<!-- problem:start -->

# [2047. Number of Valid Words in a Sentence](https://leetcode.com/problems/number-of-valid-words-in-a-sentence)

[中文文档](/solution/2000-2099/2047.Number%20of%20Valid%20Words%20in%20a%20Sentence/README.md)

## Description

<!-- description:start -->

<p>A sentence consists of lowercase letters (<code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>), digits (<code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>), hyphens (<code>&#39;-&#39;</code>), punctuation marks (<code>&#39;!&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39;,&#39;</code>), and spaces (<code>&#39; &#39;</code>) only. Each sentence can be broken down into <strong>one or more tokens</strong> separated by one or more spaces <code>&#39; &#39;</code>.</p>

<p>A token is a valid word if <strong>all three</strong> of the following are true:</p>

<ul>
	<li>It only contains lowercase letters, hyphens, and/or punctuation (<strong>no</strong> digits).</li>
	<li>There is <strong>at most one</strong> hyphen <code>&#39;-&#39;</code>. If present, it <strong>must</strong> be surrounded by lowercase characters (<code>&quot;a-b&quot;</code> is valid, but <code>&quot;-ab&quot;</code> and <code>&quot;ab-&quot;</code> are not valid).</li>
	<li>There is <strong>at most one</strong> punctuation mark. If present, it <strong>must</strong> be at the <strong>end</strong> of the token (<code>&quot;ab,&quot;</code>, <code>&quot;cd!&quot;</code>, and <code>&quot;.&quot;</code> are valid, but <code>&quot;a!b&quot;</code> and <code>&quot;c.,&quot;</code> are not valid).</li>
</ul>

<p>Examples of valid words include <code>&quot;a-b.&quot;</code>, <code>&quot;afad&quot;</code>, <code>&quot;ba-c&quot;</code>, <code>&quot;a!&quot;</code>, and <code>&quot;!&quot;</code>.</p>

<p>Given a string <code>sentence</code>, return <em>the <strong>number</strong> of valid words in </em><code>sentence</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;<u>cat</u> <u>and</u>  <u>dog</u>&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The valid words in the sentence are &quot;cat&quot;, &quot;and&quot;, and &quot;dog&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;!this  1-s b8d!&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no valid words in the sentence.
&quot;!this&quot; is invalid because it starts with a punctuation mark.
&quot;1-s&quot; and &quot;b8d&quot; are invalid because they contain digits.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;<u>alice</u> <u>and</u>  <u>bob</u> <u>are</u> <u>playing</u> stone-game10&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The valid words in the sentence are &quot;alice&quot;, &quot;and&quot;, &quot;bob&quot;, &quot;are&quot;, and &quot;playing&quot;.
&quot;stone-game10&quot; is invalid because it contains digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 1000</code></li>
	<li><code>sentence</code> only contains lowercase English letters, digits, <code>&#39; &#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;!&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39;,&#39;</code>.</li>
	<li>There will be at least&nbsp;<code>1</code> token.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

First, we split the sentence into words by spaces, and then check each word to determine if it is a valid word.

For each word, we can use a boolean variable $\textit{st}$ to record whether a hyphen has already appeared, and then traverse each character in the word, judging according to the rules described in the problem.

For each character $s[i]$, we have the following cases:

-   If $s[i]$ is a digit, then $s$ is not a valid word, and we return $\text{false}$ directly;
-   If $s[i]$ is a punctuation mark ('!', '.', ','), and $i < \text{len}(s) - 1$, then $s$ is not a valid word, and we return $\text{false}$ directly;
-   If $s[i]$ is a hyphen, then we need to check if the following conditions are met:
    -   The hyphen can only appear once;
    -   The hyphen cannot appear at the beginning or end of the word;
    -   Both sides of the hyphen must be letters;
-   If $s[i]$ is a letter, then we do not need to do anything.

Finally, we count the number of valid words in the sentence.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the sentence.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countValidWords(self, sentence: str) -> int:
        def check(s: str) -> bool:
            st = False
            for i, c in enumerate(s):
                if c.isdigit() or (c in "!.," and i < len(s) - 1):
                    return False
                if c == "-":
                    if (
                        st
                        or i in (0, len(s) - 1)
                        or not s[i - 1].isalpha()
                        or not s[i + 1].isalpha()
                    ):
                        return False
                    st = True
            return True

        return sum(check(s) for s in sentence.split())
```

#### Java

```java
class Solution {
    public int countValidWords(String sentence) {
        int ans = 0;
        for (String s : sentence.split(" ")) {
            ans += check(s.toCharArray());
        }
        return ans;
    }

    private int check(char[] s) {
        if (s.length == 0) {
            return 0;
        }
        boolean st = false;
        for (int i = 0; i < s.length; ++i) {
            if (Character.isDigit(s[i])) {
                return 0;
            }
            if ((s[i] == '!' || s[i] == '.' || s[i] == ',') && i < s.length - 1) {
                return 0;
            }
            if (s[i] == '-') {
                if (st || i == 0 || i == s.length - 1) {
                    return 0;
                }
                if (!Character.isAlphabetic(s[i - 1]) || !Character.isAlphabetic(s[i + 1])) {
                    return 0;
                }
                st = true;
            }
        }
        return 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countValidWords(string sentence) {
        auto check = [](const string& s) -> int {
            bool st = false;
            for (int i = 0; i < s.length(); ++i) {
                if (isdigit(s[i])) {
                    return 0;
                }
                if ((s[i] == '!' || s[i] == '.' || s[i] == ',') && i < s.length() - 1) {
                    return 0;
                }
                if (s[i] == '-') {
                    if (st || i == 0 || i == s.length() - 1) {
                        return 0;
                    }
                    if (!isalpha(s[i - 1]) || !isalpha(s[i + 1])) {
                        return 0;
                    }
                    st = true;
                }
            }
            return 1;
        };

        int ans = 0;
        stringstream ss(sentence);
        string s;
        while (ss >> s) {
            ans += check(s);
        }
        return ans;
    }
};
```

#### Go

```go
func countValidWords(sentence string) (ans int) {
	check := func(s string) int {
		if len(s) == 0 {
			return 0
		}
		st := false
		for i, r := range s {
			if unicode.IsDigit(r) {
				return 0
			}
			if (r == '!' || r == '.' || r == ',') && i < len(s)-1 {
				return 0
			}
			if r == '-' {
				if st || i == 0 || i == len(s)-1 {
					return 0
				}
				if !unicode.IsLetter(rune(s[i-1])) || !unicode.IsLetter(rune(s[i+1])) {
					return 0
				}
				st = true
			}
		}
		return 1
	}
	for _, s := range strings.Fields(sentence) {
		ans += check(s)
	}
	return ans
}
```

#### TypeScript

```ts
function countValidWords(sentence: string): number {
    const check = (s: string): number => {
        if (s.length === 0) {
            return 0;
        }
        let st = false;
        for (let i = 0; i < s.length; ++i) {
            if (/\d/.test(s[i])) {
                return 0;
            }
            if (['!', '.', ','].includes(s[i]) && i < s.length - 1) {
                return 0;
            }
            if (s[i] === '-') {
                if (st || [0, s.length - 1].includes(i)) {
                    return 0;
                }
                if (!/[a-zA-Z]/.test(s[i - 1]) || !/[a-zA-Z]/.test(s[i + 1])) {
                    return 0;
                }
                st = true;
            }
        }
        return 1;
    };
    return sentence.split(/\s+/).reduce((acc, s) => acc + check(s), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
