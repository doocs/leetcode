# [2309. Greatest English Letter in Upper and Lower Case](https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case)

[中文文档](/solution/2300-2399/2309.Greatest%20English%20Letter%20in%20Upper%20and%20Lower%20Case/README.md)

## Description

<p>Given a string of English letters <code>s</code>, return <em>the <strong>greatest </strong>English letter which occurs as <strong>both</strong> a lowercase and uppercase letter in</em> <code>s</code>. The returned letter should be in <strong>uppercase</strong>. If no such letter exists, return <em>an empty string</em>.</p>

<p>An English letter <code>b</code> is <strong>greater</strong> than another letter <code>a</code> if <code>b</code> appears <strong>after</strong> <code>a</code> in the English alphabet.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;l<strong><u>Ee</u></strong>TcOd<u><strong>E</strong></u>&quot;
<strong>Output:</strong> &quot;E&quot;
<strong>Explanation:</strong>
The letter &#39;E&#39; is the only letter to appear in both lower and upper case.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a<strong><u>rR</u></strong>AzFif&quot;
<strong>Output:</strong> &quot;R&quot;
<strong>Explanation:</strong>
The letter &#39;R&#39; is the greatest letter to appear in both lower and upper case.
Note that &#39;A&#39; and &#39;F&#39; also appear in both lower and upper case, but &#39;R&#39; is greater than &#39;F&#39; or &#39;A&#39;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AbCdEfGhIjK&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong>
There is no letter that appears in both lower and upper case.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase and uppercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def greatestLetter(self, s: str) -> str:
        ss = set(s)
        for c in ascii_uppercase[::-1]:
            if c in ss and c.lower() in ss:
                return c
        return ''
```

### **Java**

```java
class Solution {
    public String greatestLetter(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                cnt[c - 'a'] |= 1;
            } else if (Character.isUpperCase(c)) {
                cnt[c - 'A'] |= 2;
            }
        }
        for (int i = 25; i >= 0; --i) {
            if (cnt[i] == 3) {
                return String.valueOf((char) ('A' + i));
            }
        }
        return "";
    }
}
```

```java
class Solution {
    public String greatestLetter(String s) {
        Set<Character> ss = new HashSet<>();
        for (char c : s.toCharArray()) {
            ss.add(c);
        }
        for (char a = 'Z'; a >= 'A'; --a) {
            if (ss.contains(a) && ss.contains((char) (a + 32))) {
                return String.valueOf(a);
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string greatestLetter(string s) {
        unordered_set<char> ss;
        for (char& c : s) ss.insert(c);
        for (char c = 'Z'; c >= 'A'; --c)
            if (ss.count(c) && ss.count(char(c + 32)))
                return string(1, c);
        return "";
    }
};
```

### **Go**

```go
func greatestLetter(s string) string {
	ss := map[rune]bool{}
	for _, c := range s {
		ss[c] = true
	}
	for c := 'Z'; c >= 'A'; c-- {
		if ss[c] && ss[rune(c+32)] {
			return string(c)
		}
	}
	return ""
}
```

### **TypeScript**

```ts
function greatestLetter(s: string): string {
    let couter = new Array(128).fill(false);
    for (let char of s) {
        couter[char.charCodeAt(0)] = true;
    }
    for (let i = 90; i >= 65; i--) {
        if (couter[i] && couter[i + 32]) return String.fromCharCode(i);
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn greatest_letter(s: String) -> String {
        let mut arr = [0; 26];
        for &c in s.as_bytes().iter() {
            if c >= b'a' {
                arr[(c - b'a') as usize] |= 1;
            } else {
                arr[(c - b'A') as usize] |= 2;
            }
        }
        for i in (0..26).rev() {
            if arr[i] == 3 {
                return char::from(b'A' + i as u8).to_string();
            }
        }
        "".to_string()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
