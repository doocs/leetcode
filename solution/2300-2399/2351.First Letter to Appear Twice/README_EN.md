# [2351. First Letter to Appear Twice](https://leetcode.com/problems/first-letter-to-appear-twice)

[中文文档](/solution/2300-2399/2351.First%20Letter%20to%20Appear%20Twice/README.md)

## Description

<p>Given a string <code>s</code> consisting of lowercase English letters, return <em>the first letter to appear <strong>twice</strong></em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>A letter <code>a</code> appears twice before another letter <code>b</code> if the <strong>second</strong> occurrence of <code>a</code> is before the <strong>second</strong> occurrence of <code>b</code>.</li>
	<li><code>s</code> will contain at least one letter that appears twice.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abccbaacz&quot;
<strong>Output:</strong> &quot;c&quot;
<strong>Explanation:</strong>
The letter &#39;a&#39; appears on the indexes 0, 5 and 6.
The letter &#39;b&#39; appears on the indexes 1 and 4.
The letter &#39;c&#39; appears on the indexes 2, 3 and 7.
The letter &#39;z&#39; appears on the index 8.
The letter &#39;c&#39; is the first letter to appear twice, because out of all the letters the index of its second occurrence is the smallest.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdd&quot;
<strong>Output:</strong> &quot;d&quot;
<strong>Explanation:</strong>
The only letter that appears twice is &#39;d&#39; so we return &#39;d&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>s</code> has at least one repeated letter.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def repeatedCharacter(self, s: str) -> str:
        cnt = Counter()
        for c in s:
            cnt[c] += 1
            if cnt[c] == 2:
                return c
```

```python
class Solution:
    def repeatedCharacter(self, s: str) -> str:
        mask = 0
        for c in s:
            i = ord(c) - ord('a')
            if mask >> i & 1:
                return c
            mask |= 1 << i
```

### **Java**

```java
class Solution {
    public char repeatedCharacter(String s) {
        int[] cnt = new int[26];
        for (int i = 0; ; ++i) {
            char c = s.charAt(i);
            if (++cnt[c - 'a'] == 2) {
                return c;
            }
        }
    }
}
```

```java
class Solution {
    public char repeatedCharacter(String s) {
        int mask = 0;
        for (int i = 0;; ++i) {
            char c = s.charAt(i);
            if ((mask >> (c - 'a') & 1) == 1) {
                return c;
            }
            mask |= 1 << (c - 'a');
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    char repeatedCharacter(string s) {
        int cnt[26]{};
        for (int i = 0; ; ++i) {
            if (++cnt[s[i] - 'a'] == 2) {
                return s[i];
            }
        }
    }
};
```

```cpp
class Solution {
public:
    char repeatedCharacter(string s) {
        int mask = 0;
        for (int i = 0; ; ++i) {
            if (mask >> (s[i] - 'a') & 1) {
                return s[i];
            }
            mask |= 1 << (s[i] - 'a');
        }
    }
};
```

### **Go**

```go
func repeatedCharacter(s string) byte {
	cnt := [26]int{}
	for i := 0; ; i++ {
		cnt[s[i]-'a']++
		if cnt[s[i]-'a'] == 2 {
			return s[i]
		}
	}
}
```

```go
func repeatedCharacter(s string) byte {
	mask := 0
	for i := 0; ; i++ {
		if mask>>(s[i]-'a')&1 == 1 {
			return s[i]
		}
		mask |= 1 << (s[i] - 'a')
	}
}
```

### **TypeScript**

```ts
function repeatedCharacter(s: string): string {
    const vis = new Array(26).fill(false);
    for (const c of s) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        if (vis[i]) {
            return c;
        }
        vis[i] = true;
    }
    return ' ';
}
```

```ts
function repeatedCharacter(s: string): string {
    let mask = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
        if (mask & (1 << i)) {
            return c;
        }
        mask |= 1 << i;
    }
    return ' ';
}
```

### **Rust**

```rust
impl Solution {
    pub fn repeated_character(s: String) -> char {
        let mut vis = [false; 26];
        for &c in s.as_bytes() {
            if vis[(c - b'a') as usize] {
                return c as char;
            }
            vis[(c - b'a') as usize] = true;
        }
        ' '
    }
}
```

```rust
impl Solution {
    pub fn repeated_character(s: String) -> char {
        let mut mask = 0;
        for &c in s.as_bytes() {
            if mask & 1 << (c - b'a') as i32 != 0 {
                return c as char;
            }
            mask |= 1 << (c - b'a') as i32;
        }
        ' '
    }
}
```

### **C**

```c
char repeatedCharacter(char *s) {
    int vis[26] = {0};
    for (int i = 0; s[i]; i++) {
        if (vis[s[i] - 'a']) {
            return s[i];
        }
        vis[s[i] - 'a']++;
    }
    return ' ';
}
```

```c
char repeatedCharacter(char *s) {
    int mask = 0;
    for (int i = 0; s[i]; i++) {
        if (mask & (1 << s[i] - 'a')) {
            return s[i];
        }
        mask |= 1 << s[i] - 'a';
    }
    return ' ';
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function repeatedCharacter($s) {
        for ($i = 0;; $i++) {
            $hashtable[$s[$i]] += 1;
            if ($hashtable[$s[$i]] == 2) return $s[$i];
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
