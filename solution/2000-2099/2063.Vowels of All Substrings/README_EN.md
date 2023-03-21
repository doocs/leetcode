# [2063. Vowels of All Substrings](https://leetcode.com/problems/vowels-of-all-substrings)

[中文文档](/solution/2000-2099/2063.Vowels%20of%20All%20Substrings/README.md)

## Description

<p>Given a string <code>word</code>, return <em>the <strong>sum of the number of vowels</strong> (</em><code>&#39;a&#39;</code>, <code>&#39;e&#39;</code><em>,</em> <code>&#39;i&#39;</code><em>,</em> <code>&#39;o&#39;</code><em>, and</em> <code>&#39;u&#39;</code><em>)</em> <em>in every substring of </em><code>word</code>.</p>

<p>A <strong>substring</strong> is a contiguous (non-empty) sequence of characters within a string.</p>

<p><strong>Note:</strong> Due to the large constraints, the answer may not fit in a signed 32-bit integer. Please be careful during the calculations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aba&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
All possible substrings are: &quot;a&quot;, &quot;ab&quot;, &quot;aba&quot;, &quot;b&quot;, &quot;ba&quot;, and &quot;a&quot;.
- &quot;b&quot; has 0 vowels in it
- &quot;a&quot;, &quot;ab&quot;, &quot;ba&quot;, and &quot;a&quot; have 1 vowel each
- &quot;aba&quot; has 2 vowels in it
Hence, the total sum of vowels = 0 + 1 + 1 + 1 + 1 + 2 = 6. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
All possible substrings are: &quot;a&quot;, &quot;ab&quot;, &quot;abc&quot;, &quot;b&quot;, &quot;bc&quot;, and &quot;c&quot;.
- &quot;a&quot;, &quot;ab&quot;, and &quot;abc&quot; have 1 vowel each
- &quot;b&quot;, &quot;bc&quot;, and &quot;c&quot; have 0 vowels each
Hence, the total sum of vowels = 1 + 1 + 1 + 0 + 0 + 0 = 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;ltcd&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no vowels in any substring of &quot;ltcd&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countVowels(self, word: str) -> int:
        n = len(word)
        return sum((i + 1) * (n - i) for i, c in enumerate(word) if c in 'aeiou')
```

### **Java**

```java
class Solution {
    public long countVowels(String word) {
        long ans = 0;
        for (int i = 0, n = word.length(); i < n; ++i) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                ans += (i + 1L) * (n - i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countVowels(string word) {
        long long ans = 0;
        for (int i = 0, n = word.size(); i < n; ++i) {
            char c = word[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                ans += (i + 1LL) * (n - i);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countVowels(word string) (ans int64) {
	for i, c := range word {
		if c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' {
			ans += int64((i + 1) * (len(word) - i))
		}
	}
	return
}
```

### **TypeScript**

```ts
function countVowels(word: string): number {
    const n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (['a', 'e', 'i', 'o', 'u'].includes(word[i])) {
            ans += (i + 1) * (n - i);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
