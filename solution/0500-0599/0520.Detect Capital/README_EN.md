---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0520.Detect%20Capital/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [520. Detect Capital](https://leetcode.com/problems/detect-capital)

[中文文档](/solution/0500-0599/0520.Detect%20Capital/README.md)

## Description

<p>We define the usage of capitals in a word to be right when one of the following cases holds:</p>

<ul>
	<li>All letters in this word are capitals, like <code>&quot;USA&quot;</code>.</li>
	<li>All letters in this word are not capitals, like <code>&quot;leetcode&quot;</code>.</li>
	<li>Only the first letter in this word is capital, like <code>&quot;Google&quot;</code>.</li>
</ul>

<p>Given a string <code>word</code>, return <code>true</code> if the usage of capitals in it is right.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> word = "USA"
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> word = "FlaG"
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> consists of lowercase and uppercase English letters.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Count the Number of Uppercase Letters

We can count the number of uppercase letters in the string, and then determine whether it meets the requirements of the problem based on the number of uppercase letters.

-   If the number of uppercase letters is 0 or equal to the length of the string, then return `true`.
-   If the number of uppercase letters is 1 and the first letter is an uppercase letter, then return `true`.
-   Otherwise, return `false`.

The time complexity is $O(n)$, where $n$ is the length of the string `word`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def detectCapitalUse(self, word: str) -> bool:
        cnt = sum(c.isupper() for c in word)
        return cnt == 0 or cnt == len(word) or (cnt == 1 and word[0].isupper())
```

```java
class Solution {
    public boolean detectCapitalUse(String word) {
        int cnt = 0;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                ++cnt;
            }
        }
        return cnt == 0 || cnt == word.length()
            || (cnt == 1 && Character.isUpperCase(word.charAt(0)));
    }
}
```

```cpp
class Solution {
public:
    bool detectCapitalUse(string word) {
        int cnt = count_if(word.begin(), word.end(), [](char c) { return isupper(c); });
        return cnt == 0 || cnt == word.length() || (cnt == 1 && isupper(word[0]));
    }
};
```

```go
func detectCapitalUse(word string) bool {
	cnt := 0
	for _, c := range word {
		if unicode.IsUpper(c) {
			cnt++
		}
	}
	return cnt == 0 || cnt == len(word) || (cnt == 1 && unicode.IsUpper(rune(word[0])))
}
```

```ts
function detectCapitalUse(word: string): boolean {
    const cnt = word.split('').reduce((acc, c) => acc + (c === c.toUpperCase() ? 1 : 0), 0);
    return cnt === 0 || cnt === word.length || (cnt === 1 && word[0] === word[0].toUpperCase());
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
