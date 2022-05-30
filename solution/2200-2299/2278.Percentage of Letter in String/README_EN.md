# [2278. Percentage of Letter in String](https://leetcode.com/problems/percentage-of-letter-in-string)

[中文文档](/solution/2200-2299/2278.Percentage%20of%20Letter%20in%20String/README.md)

## Description

<p>Given a string <code>s</code> and a character <code>letter</code>, return<em> the <strong>percentage</strong> of characters in </em><code>s</code><em> that equal </em><code>letter</code><em> <strong>rounded down</strong> to the nearest whole percent.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;foobar&quot;, letter = &quot;o&quot;
<strong>Output:</strong> 33
<strong>Explanation:</strong>
The percentage of characters in s that equal the letter &#39;o&#39; is 2 / 6 * 100% = 33% when rounded down, so we return 33.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;jjjj&quot;, letter = &quot;k&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The percentage of characters in s that equal the letter &#39;k&#39; is 0%, so we return 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>letter</code> is a lowercase English letter.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def percentageLetter(self, s: str, letter: str) -> int:
        return s.count(letter) * 100 // len(s)
```

### **Java**

```java
class Solution {
    public int percentageLetter(String s, char letter) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                ++cnt;
            }
        }
        return cnt * 100 / s.length();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int percentageLetter(string s, char letter) {
        int cnt = 0;
        for (char& c : s) cnt += c == letter;
        return cnt * 100 / s.size();
    }
};
```

### **Go**

```go
func percentageLetter(s string, letter byte) int {
	cnt := 0
	for i := range s {
		if s[i] == letter {
			cnt++
		}
	}
	return cnt * 100 / len(s)
}
```

### **TypeScript**

```ts
function percentageLetter(s: string, letter: string): number {
    let count = 0;
    let total = s.length;
    for (let i of s) {
        if (i === letter) count++;
    }
    return Math.floor((count / total) * 100);
}
```

### **Rust**

```rust
impl Solution {
    pub fn percentage_letter(s: String, letter: char) -> i32 {
        let mut count = 0;
        for c in s.chars() {
            if c == letter {
                count += 1;
            }
        }
        (count * 100 / s.len()) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
