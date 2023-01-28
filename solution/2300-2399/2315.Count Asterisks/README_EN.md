# [2315. Count Asterisks](https://leetcode.com/problems/count-asterisks)

[中文文档](/solution/2300-2399/2315.Count%20Asterisks/README.md)

## Description

<p>You are given a string <code>s</code>, where every <strong>two</strong> consecutive vertical bars <code>&#39;|&#39;</code> are grouped into a <strong>pair</strong>. In other words, the 1<sup>st</sup> and 2<sup>nd</sup> <code>&#39;|&#39;</code> make a pair, the 3<sup>rd</sup> and 4<sup>th</sup> <code>&#39;|&#39;</code> make a pair, and so forth.</p>

<p>Return <em>the number of </em><code>&#39;*&#39;</code><em> in </em><code>s</code><em>, <strong>excluding</strong> the </em><code>&#39;*&#39;</code><em> between each pair of </em><code>&#39;|&#39;</code>.</p>

<p><strong>Note</strong> that each <code>&#39;|&#39;</code> will belong to <strong>exactly</strong> one pair.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;l|*e*et|c**o|*de|&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The considered characters are underlined: &quot;<u>l</u>|*e*et|<u>c**o</u>|*de|&quot;.
The characters between the first and second &#39;|&#39; are excluded from the answer.
Also, the characters between the third and fourth &#39;|&#39; are excluded from the answer.
There are 2 asterisks considered. Therefore, we return 2.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;iamprogrammer&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> In this example, there are no asterisks in s. Therefore, we return 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;yo|uar|e**|b|e***au|tifu|l&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The considered characters are underlined: &quot;<u>yo</u>|uar|<u>e**</u>|b|<u>e***au</u>|tifu|<u>l</u>&quot;. There are 5 asterisks considered. Therefore, we return 5.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase English letters, vertical bars <code>&#39;|&#39;</code>, and asterisks <code>&#39;*&#39;</code>.</li>
	<li><code>s</code> contains an <strong>even</strong> number of vertical bars <code>&#39;|&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countAsterisks(self, s: str) -> int:
        ans, ok = 0, 1
        for c in s:
            if c == "*":
                ans += ok
            elif c == "|":
                ok ^= 1
        return ans
```

### **Java**

```java
class Solution {
    public int countAsterisks(String s) {
        int ans = 0;
        for (int i = 0, ok = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '*') {
                ans += ok;
            } else if (c == '|') {
                ok ^= 1;
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
    int countAsterisks(string s) {
        int ans = 0, ok = 1;
        for (char& c : s) {
            if (c == '*') {
                ans += ok;
            } else if (c == '|') {
                ok ^= 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countAsterisks(s string) (ans int) {
	ok := 1
	for _, c := range s {
		if c == '*' {
			ans += ok
		} else if c == '|' {
			ok ^= 1
		}
	}
	return
}
```

### **TypeScript**

```ts
function countAsterisks(s: string): number {
    let ans = 0;
    let ok = 1;
    for (const c of s) {
        if (c === '*') {
            ans += ok;
        } else if (c === '|') {
            ok ^= 1;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_asterisks(s: String) -> i32 {
        let mut ans = 0;
        let mut ok = 1;
        for &c in s.as_bytes() {
            if c == b'*' {
                ans += ok
            } else if c == b'|' {
                ok ^= 1
            }
        }
        ans
    }
}
```

### **C**

```c
int countAsterisks(char * s){
    int ans = 0;
    int ok = 1;
    for (int i = 0; s[i]; i++) {
        if (s[i] == '*') {
            ans += ok;
        } else if (s[i] == '|') {
            ok ^= 1;
        }
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int CountAsterisks(string s) {
        int ans = 0, ok = 1;
        foreach (char c in s) {
            if (c == '*') {
                ans += ok;
            } else if (c == '|') {
                ok ^= 1;
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
