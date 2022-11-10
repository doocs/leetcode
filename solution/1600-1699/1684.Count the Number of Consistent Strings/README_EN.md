# [1684. Count the Number of Consistent Strings](https://leetcode.com/problems/count-the-number-of-consistent-strings)

[中文文档](/solution/1600-1699/1684.Count%20the%20Number%20of%20Consistent%20Strings/README.md)

## Description

<p>You are given a string <code>allowed</code> consisting of <strong>distinct</strong> characters and an array of strings <code>words</code>. A string is <strong>consistent </strong>if all characters in the string appear in the string <code>allowed</code>.</p>

<p>Return<em> the number of <strong>consistent</strong> strings in the array </em><code>words</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;ab&quot;, words = [&quot;ad&quot;,&quot;bd&quot;,&quot;aaab&quot;,&quot;baa&quot;,&quot;badab&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Strings &quot;aaab&quot; and &quot;baa&quot; are consistent since they only contain characters &#39;a&#39; and &#39;b&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;abc&quot;, words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;ab&quot;,&quot;ac&quot;,&quot;bc&quot;,&quot;abc&quot;]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All strings are consistent.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;cad&quot;, words = [&quot;cc&quot;,&quot;acd&quot;,&quot;b&quot;,&quot;ba&quot;,&quot;bac&quot;,&quot;bad&quot;,&quot;ac&quot;,&quot;d&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Strings &quot;cc&quot;, &quot;acd&quot;, &quot;ac&quot;, and &quot;d&quot; are consistent.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= allowed.length &lt;=<sup> </sup>26</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li>The characters in <code>allowed</code> are <strong>distinct</strong>.</li>
	<li><code>words[i]</code> and <code>allowed</code> contain only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        s = set(allowed)
        return sum(all(c in s for c in w) for w in words)
```

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        def f(w):
            return reduce(or_, (1 << (ord(c) - ord('a')) for c in w))

        mask = f(allowed)
        return sum((mask | f(w)) == mask for w in words)
```

### **Java**

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] s = new boolean[26];
        for (char c : allowed.toCharArray()) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        for (String w : words) {
            if (check(w, s)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String w, boolean[] s) {
        for (int i = 0; i < w.length(); ++i) {
            if (!s[w.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int mask = f(allowed);
        int ans = 0;
        for (String w : words) {
            if ((mask | f(w)) == mask) {
                ++ans;
            }
        }
        return ans;
    }

    private int f(String w) {
        int mask = 0;
        for (int i = 0; i < w.length(); ++i) {
            mask |= 1 << (w.charAt(i) - 'a');
        }
        return mask;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        bitset<26> s;
        for (auto& c : allowed) s[c - 'a'] = 1;
        int ans = 0;
        auto check = [&](string& w) {
            for (auto& c : w) if (!s[c - 'a']) return false;
            return true;
        };
        for (auto& w : words) ans += check(w);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        auto f = [](string& w) {
            int mask = 0;
            for (auto& c : w) mask |= 1 << (c - 'a');
            return mask;
        };
        int mask = f(allowed);
        int ans = 0;
        for (auto& w : words) ans += (mask | f(w)) == mask;
        return ans;
    }
};
```

### **Go**

```go
func countConsistentStrings(allowed string, words []string) (ans int) {
	s := [26]bool{}
	for _, c := range allowed {
		s[c-'a'] = true
	}
	check := func(w string) bool {
		for _, c := range w {
			if !s[c-'a'] {
				return false
			}
		}
		return true
	}
	for _, w := range words {
		if check(w) {
			ans++
		}
	}
	return ans
}
```

```go
func countConsistentStrings(allowed string, words []string) (ans int) {
	f := func(w string) (mask int) {
		for _, c := range w {
			mask |= 1 << (c - 'a')
		}
		return
	}

	mask := f(allowed)
	for _, w := range words {
		if (mask | f(w)) == mask {
			ans++
		}
	}
	return
}
```

### **C**

```c
int countConsistentStrings(char *allowed, char **words, int wordsSize) {
    int n = strlen(allowed);
    int make[26] = {0};
    for (int i = 0; i < n; i++) {
        make[allowed[i] - 'a'] = 1;
    }
    int ans = wordsSize;
    for (int i = 0; i < wordsSize; i++) {
        char *word = words[i];
        for (int j = 0; j < strlen(word); j++) {
            if (!make[word[j] - 'a']) {
                ans--;
                break;
            }
        }
    }
    return ans;
}
```

```c
int helper(char *s) {
    int res = 0;
    int n = strlen(s);
    for (int i = 0; i < n; i++) {
        res |= 1 << (s[i] - 'a');
    }
    return res;
}

int countConsistentStrings(char *allowed, char **words, int wordsSize) {
    int mask = helper(allowed);
    int ans = 0;
    for (int i = 0; i < wordsSize; i++) {
        if ((mask | helper(words[i])) == mask) {
            ans++;
        }
    }
    return ans;
}
```

### **TypeScript**

```ts
function countConsistentStrings(allowed: string, words: string[]): number {
    const set = new Set([...allowed]);
    const n = words.length;
    let ans = n;
    for (const word of words) {
        for (const c of word) {
            if (!set.has(c)) {
                ans--;
                break;
            }
        }
    }
    return ans;
}
```

```ts
function countConsistentStrings(allowed: string, words: string[]): number {
    const helper = (s: string) => {
        let res = 0;
        for (const c of s) {
            res |= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
        return res;
    };
    const mask = helper(allowed);
    let ans = 0;
    for (const word of words) {
        if ((mask | helper(word)) === mask) {
            ans++;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_consistent_strings(allowed: String, words: Vec<String>) -> i32 {
        let n = words.len();
        let mut make = [false; 26];
        for c in allowed.as_bytes() {
            make[(c - b'a') as usize] = true;
        }
        let mut ans = n as i32;
        for word in words.iter() {
            for c in word.as_bytes().iter() {
                if !make[(c - b'a') as usize] {
                    ans -= 1;
                    break;
                }
            }
        }
        ans
    }
}
```

```rust
impl Solution {
    fn helper(s: &String) -> i32 {
        let mut res = 0;
        for c in s.as_bytes().iter() {
            res |= 1 << (c - b'a') as i32;
        }
        res
    }

    pub fn count_consistent_strings(allowed: String, words: Vec<String>) -> i32 {
        let mask = Self::helper(&allowed);
        let mut ans = 0;
        for word in words.iter() {
            if (mask | Self::helper(word)) == mask {
                ans += 1;
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
