# [720. Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary)

[中文文档](/solution/0700-0799/0720.Longest%20Word%20in%20Dictionary/README.md)

## Description

<p>Given an array of strings <code>words</code> representing an English Dictionary, return <em>the longest word in</em> <code>words</code> <em>that can be built one character at a time by other words in</em> <code>words</code>.</p>

<p>If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.</p>

<p>Note that the word should be built from left to right with each additional character being added to the end of a previous word.&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;w&quot;,&quot;wo&quot;,&quot;wor&quot;,&quot;worl&quot;,&quot;world&quot;]
<strong>Output:</strong> &quot;world&quot;
<strong>Explanation:</strong> The word &quot;world&quot; can be built one character at a time by &quot;w&quot;, &quot;wo&quot;, &quot;wor&quot;, and &quot;worl&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;banana&quot;,&quot;app&quot;,&quot;appl&quot;,&quot;ap&quot;,&quot;apply&quot;,&quot;apple&quot;]
<strong>Output:</strong> &quot;apple&quot;
<strong>Explanation:</strong> Both &quot;apply&quot; and &quot;apple&quot; can be built from other words in the dictionary. However, &quot;apple&quot; is lexicographically smaller than &quot;apply&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestWord(self, words: List[str]) -> str:
        cnt, ans = 0, ''
        s = set(words)
        for w in s:
            n = len(w)
            if all(w[:i] in s for i in range(1, n)):
                if cnt < n:
                    cnt, ans = n, w
                elif cnt == n and w < ans:
                    ans = w
        return ans
```

### **Java**

```java
class Solution {
    private Set<String> s;

    public String longestWord(String[] words) {
        s = new HashSet<>(Arrays.asList(words));
        int cnt = 0;
        String ans = "";
        for (String w : s) {
            int n = w.length();
            if (check(w)) {
                if (cnt < n) {
                    cnt = n;
                    ans = w;
                } else if (cnt == n && w.compareTo(ans) < 0) {
                    ans = w;
                }
            }
        }
        return ans;
    }

    private boolean check(String word) {
        for (int i = 1, n = word.length(); i < n; ++i) {
            if (!s.contains(word.substring(0, i))) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function longestWord(words: string[]): string {
    words.sort((a, b) => {
        const n = a.length;
        const m = b.length;
        if (n === m) {
            return a < b ? -1 : 1;
        }
        return m - n;
    });
    for (const word of words) {
        let isPass = true;
        for (let i = 1; i <= word.length; i++) {
            if (!words.includes(word.slice(0, i))) {
                isPass = false;
                break;
            }
        }
        if (isPass) {
            return word;
        }
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_word(mut words: Vec<String>) -> String {
        words.sort_unstable_by(|a, b| (b.len(), a).cmp(&(a.len(), b)));
        for word in words.iter() {
            let mut is_pass = true;
            for i in 1..=word.len() {
                if !words.contains(&word[..i].to_string()) {
                    is_pass = false;
                    break;
                }
            }
            if is_pass {
                return word.clone();
            }
        }
        String::new()
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string longestWord(vector<string>& words) {
        unordered_set<string> s(words.begin(), words.end());
        int cnt = 0;
        string ans = "";
        for (auto w : s) {
            int n = w.size();
            if (check(w, s)) {
                if (cnt < n) {
                    cnt = n;
                    ans = w;
                } else if (cnt == n && w < ans)
                    ans = w;
            }
        }
        return ans;
    }

    bool check(string& word, unordered_set<string>& s) {
        for (int i = 1, n = word.size(); i < n; ++i)
            if (!s.count(word.substr(0, i)))
                return false;
        return true;
    }
};
```

### **Go**

```go
func longestWord(words []string) string {
	s := make(map[string]bool)
	for _, w := range words {
		s[w] = true
	}
	cnt := 0
	ans := ""
	check := func(word string) bool {
		for i, n := 1, len(word); i < n; i++ {
			if !s[word[:i]] {
				return false
			}
		}
		return true
	}
	for w, _ := range s {
		n := len(w)
		if check(w) {
			if cnt < n {
				cnt, ans = n, w
			} else if cnt == n && w < ans {
				ans = w
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
