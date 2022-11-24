# [953. Verifying an Alien Dictionary](https://leetcode.com/problems/verifying-an-alien-dictionary)

[中文文档](/solution/0900-0999/0953.Verifying%20an%20Alien%20Dictionary/README.md)

## Description

<p>In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different <code>order</code>. The <code>order</code> of the alphabet is some permutation of lowercase letters.</p>

<p>Given a sequence of <code>words</code> written in the alien language, and the <code>order</code> of the alphabet, return <code>true</code> if and only if the given <code>words</code> are sorted lexicographically in this alien language.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;hello&quot;,&quot;leetcode&quot;], order = &quot;hlabcdefgijkmnopqrstuvwxyz&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>As &#39;h&#39; comes before &#39;l&#39; in this language, then the sequence is sorted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;word&quot;,&quot;world&quot;,&quot;row&quot;], order = &quot;worldabcefghijkmnpqstuvxyz&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>As &#39;d&#39; comes after &#39;l&#39; in this language, then words[0] &gt; words[1], hence the sequence is unsorted.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;apple&quot;,&quot;app&quot;], order = &quot;abcdefghijklmnopqrstuvwxyz&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>The first three characters &quot;app&quot; match, and the second string is shorter (in size.) According to lexicographical rules &quot;apple&quot; &gt; &quot;app&quot;, because &#39;l&#39; &gt; &#39;&empty;&#39;, where &#39;&empty;&#39; is defined as the blank character which is less than any other character (<a href="https://en.wikipedia.org/wiki/Lexicographical_order" target="_blank">More info</a>).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>order.length == 26</code></li>
	<li>All characters in <code>words[i]</code> and <code>order</code> are English lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        m = {c: i for i, c in enumerate(order)}
        for i in range(20):
            prev = -1
            valid = True
            for x in words:
                curr = -1 if i >= len(x) else m[x[i]]
                if prev > curr:
                    return False
                if prev == curr:
                    valid = False
                prev = curr
            if valid:
                return True
        return True
```

### **Java**

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] m = new int[26];
        for (int i = 0; i < 26; ++i) {
            m[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < 20; ++i) {
            int prev = -1;
            boolean valid = true;
            for (String x : words) {
                int curr = i >= x.length() ? -1 : m[x.charAt(i) - 'a'];
                if (prev > curr) {
                    return false;
                }
                if (prev == curr) {
                    valid = false;
                }
                prev = curr;
            }
            if (valid) {
                break;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isAlienSorted(vector<string>& words, string order) {
        vector<int> m(26);
        for (int i = 0; i < 26; ++i) m[order[i] - 'a'] = i;
        for (int i = 0; i < 20; ++i) {
            int prev = -1;
            bool valid = true;
            for (auto& x : words) {
                int curr = i >= x.size() ? -1 : m[x[i] - 'a'];
                if (prev > curr) return false;
                if (prev == curr) valid = false;
                prev = curr;
            }
            if (valid) break;
        }
        return true;
    }
};
```

### **Go**

```go
func isAlienSorted(words []string, order string) bool {
	m := make([]int, 26)
	for i, c := range order {
		m[c-'a'] = i
	}
	for i := 0; i < 20; i++ {
		prev := -1
		valid := true
		for _, x := range words {
			curr := -1
			if i < len(x) {
				curr = m[x[i]-'a']
			}
			if prev > curr {
				return false
			}
			if prev == curr {
				valid = false
			}
			prev = curr
		}
		if valid {
			break
		}
	}
	return true
}
```

### **TypeScript**

```ts
function isAlienSorted(words: string[], order: string): boolean {
    const map = new Map();
    for (const c of order) {
        map.set(c, map.size);
    }
    const n = words.length;
    for (let i = 1; i < n; i++) {
        const s1 = words[i - 1];
        const s2 = words[i];
        const m = Math.min(s1.length, s2.length);
        let isEqual = false;
        for (let j = 0; j < m; j++) {
            if (map.get(s1[j]) > map.get(s2[j])) {
                return false;
            }
            if (map.get(s1[j]) < map.get(s2[j])) {
                isEqual = true;
                break;
            }
        }
        if (!isEqual && s1.length > s2.length) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn is_alien_sorted(words: Vec<String>, order: String) -> bool {
        let n = words.len();
        let mut map = HashMap::new();
        order.as_bytes().iter().enumerate().for_each(|(i, &v)| {
            map.insert(v, i);
        });
        for i in 1..n {
            let s1 = words[i - 1].as_bytes();
            let s2 = words[i].as_bytes();
            let mut is_equal = true;
            for i in 0..s1.len().min(s2.len()) {
                if map.get(&s1[i]) > map.get(&s2[i]) {
                    return false;
                }
                if map.get(&s1[i]) < map.get(&s2[i]) {
                    is_equal = false;
                    break;
                }
            }
            if is_equal && s1.len() > s2.len() {
                return false;
            }
        }
        true
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

bool isAlienSorted(char **words, int wordsSize, char *order) {
    int map[26] = {0};
    for (int i = 0; i < 26; i++) {
        map[order[i] - 'a'] = i;
    }
    for (int i = 1; i < wordsSize; i++) {
        char *s1 = words[i - 1];
        char *s2 = words[i];
        int n = strlen(s1);
        int m = strlen(s2);
        int len = min(n, m);
        int isEqual = 1;
        for (int j = 0; j < len; j++) {
            if (map[s1[j] - 'a'] > map[s2[j] - 'a']) {
                return 0;
            }
            if (map[s1[j] - 'a'] < map[s2[j] - 'a']) {
                isEqual = 0;
                break;
            }
        }
        if (isEqual && n > m) {
            return 0;
        }
    }
    return 1;
}
```

### **...**

```

```

<!-- tabs:end -->
