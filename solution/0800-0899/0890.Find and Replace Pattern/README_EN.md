# [890. Find and Replace Pattern](https://leetcode.com/problems/find-and-replace-pattern)

[中文文档](/solution/0800-0899/0890.Find%20and%20Replace%20Pattern/README.md)

## Description

<p>Given a list of strings <code>words</code> and a string <code>pattern</code>, return <em>a list of</em> <code>words[i]</code> <em>that match</em> <code>pattern</code>. You may return the answer in <strong>any order</strong>.</p>

<p>A word matches the pattern if there exists a permutation of letters <code>p</code> so that after replacing every letter <code>x</code> in the pattern with <code>p(x)</code>, we get the desired word.</p>

<p>Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;deq&quot;,&quot;mee&quot;,&quot;aqq&quot;,&quot;dkd&quot;,&quot;ccc&quot;], pattern = &quot;abb&quot;
<strong>Output:</strong> [&quot;mee&quot;,&quot;aqq&quot;]
<strong>Explanation:</strong> &quot;mee&quot; matches the pattern because there is a permutation {a -&gt; m, b -&gt; e, ...}. 
&quot;ccc&quot; does not match the pattern because {a -&gt; c, b -&gt; c, ...} is not a permutation, since a and b map to the same letter.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], pattern = &quot;a&quot;
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 20</code></li>
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>words[i].length == pattern.length</code></li>
	<li><code>pattern</code> and <code>words[i]</code> are lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findAndReplacePattern(self, words: List[str], pattern: str) -> List[str]:
        def match(s, t):
            m1, m2 = [0] * 128, [0] * 128
            for i, (a, b) in enumerate(zip(s, t), 1):
                if m1[ord(a)] != m2[ord(b)]:
                    return False
                m1[ord(a)] = m2[ord(b)] = i
            return True

        return [word for word in words if match(word, pattern)]
```

### **Java**

```java
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean match(String s, String t) {
        int[] m1 = new int[128];
        int[] m2 = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (m1[c1] != m2[c2]) {
                return false;
            }
            m1[c1] = i + 1;
            m2[c2] = i + 1;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        vector<string> ans;
        for (auto& word : words)
            if (match(word, pattern))
                ans.push_back(word);
        return ans;
    }

    bool match(string s, string t) {
        vector<int> m1(128);
        vector<int> m2(128);
        for (int i = 0; i < s.size(); ++i) {
            if (m1[s[i]] != m2[t[i]]) return 0;
            m1[s[i]] = i + 1;
            m2[t[i]] = i + 1;
        }
        return 1;
    }
};
```

### **Go**

```go
func findAndReplacePattern(words []string, pattern string) []string {
	match := func(s, t string) bool {
		m1, m2 := make([]int, 128), make([]int, 128)
		for i := 0; i < len(s); i++ {
			if m1[s[i]] != m2[t[i]] {
				return false
			}
			m1[s[i]] = i + 1
			m2[t[i]] = i + 1
		}
		return true
	}
	var ans []string
	for _, word := range words {
		if match(word, pattern) {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findAndReplacePattern(words: string[], pattern: string): string[] {
    return words.filter(word => {
        const map1 = new Map<string, number>();
        const map2 = new Map<string, number>();
        for (let i = 0; i < word.length; i++) {
            if (map1.get(word[i]) !== map2.get(pattern[i])) {
                return false;
            }
            map1.set(word[i], i);
            map2.set(pattern[i], i);
        }
        return true;
    });
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn find_and_replace_pattern(words: Vec<String>, pattern: String) -> Vec<String> {
        let pattern = pattern.as_bytes();
        let n = pattern.len();
        words
            .into_iter()
            .filter(|word| {
                let word = word.as_bytes();
                let mut map1 = HashMap::new();
                let mut map2 = HashMap::new();
                for i in 0..n {
                    if map1.get(&word[i]).unwrap_or(&n) != map2.get(&pattern[i]).unwrap_or(&n) {
                        return false;
                    }
                    map1.insert(word[i], i);
                    map2.insert(pattern[i], i);
                }
                true
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
