# [2062. Count Vowel Substrings of a String](https://leetcode.com/problems/count-vowel-substrings-of-a-string)

[中文文档](/solution/2000-2099/2062.Count%20Vowel%20Substrings%20of%20a%20String/README.md)

## Description

<p>A <strong>substring</strong> is a contiguous (non-empty) sequence of characters within a string.</p>

<p>A <strong>vowel substring</strong> is a substring that <strong>only</strong> consists of vowels (<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>) and has <strong>all five</strong> vowels present in it.</p>

<p>Given a string <code>word</code>, return <em>the number of <strong>vowel substrings</strong> in</em> <code>word</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aeiouu&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The vowel substrings of word are as follows (underlined):
- &quot;<strong><u>aeiou</u></strong>u&quot;
- &quot;<strong><u>aeiouu</u></strong>&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;unicornarihan&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> Not all 5 vowels are present, so there are no vowel substrings.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;cuaieuouac&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> The vowel substrings of word are as follows (underlined):
- &quot;c<strong><u>uaieuo</u></strong>uac&quot;
- &quot;c<strong><u>uaieuou</u></strong>ac&quot;
- &quot;c<strong><u>uaieuoua</u></strong>c&quot;
- &quot;cu<strong><u>aieuo</u></strong>uac&quot;
- &quot;cu<strong><u>aieuou</u></strong>ac&quot;
- &quot;cu<strong><u>aieuoua</u></strong>c&quot;
- &quot;cua<strong><u>ieuoua</u></strong>c&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> consists of lowercase English letters only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countVowelSubstrings(self, word: str) -> int:
        n = len(word)
        s = set('aeiou')
        return sum(set(word[i:j]) == s for i in range(n) for j in range(i + 1, n + 1))
```

```python
class Solution:
    def countVowelSubstrings(self, word: str) -> int:
        s = set('aeiou')
        ans, n = 0, len(word)
        for i in range(n):
            t = set()
            for c in word[i:]:
                if c not in s:
                    break
                t.add(c)
                ans += len(t) == 5
        return ans
```

### **Java**

```java
class Solution {
    public int countVowelSubstrings(String word) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            Set<Character> t = new HashSet<>();
            for (int j = i; j < n; ++j) {
                char c = word.charAt(j);
                if (!isVowel(c)) {
                    break;
                }
                t.add(c);
                if (t.size() == 5) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countVowelSubstrings(string word) {
        int ans = 0;
        int n = word.size();
        for (int i = 0; i < n; ++i) {
            unordered_set<char> t;
            for (int j = i; j < n; ++j) {
                char c = word[j];
                if (!isVowel(c)) break;
                t.insert(c);
                ans += t.size() == 5;
            }
        }
        return ans;
    }

    bool isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
};
```

### **Go**

```go
func countVowelSubstrings(word string) int {
	ans, n := 0, len(word)
	for i := range word {
		t := map[byte]bool{}
		for j := i; j < n; j++ {
			c := word[j]
			if !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				break
			}
			t[c] = true
			if len(t) == 5 {
				ans++
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function countVowelSubstrings(word: string): number {
    let ans = 0;
    const n = word.length;
    for (let i = 0; i < n; ++i) {
        const t = new Set<string>();
        for (let j = i; j < n; ++j) {
            const c = word[j];
            if (
                !(c === 'a' || c === 'e' || c === 'i' || c === 'o' || c === 'u')
            ) {
                break;
            }
            t.add(c);
            if (t.size === 5) {
                ans++;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
