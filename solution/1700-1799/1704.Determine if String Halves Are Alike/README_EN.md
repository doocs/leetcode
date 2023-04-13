# [1704. Determine if String Halves Are Alike](https://leetcode.com/problems/determine-if-string-halves-are-alike)

[中文文档](/solution/1700-1799/1704.Determine%20if%20String%20Halves%20Are%20Alike/README.md)

## Description

<p>You are given a string <code>s</code> of even length. Split this string into two halves of equal lengths, and let <code>a</code> be the first half and <code>b</code> be the second half.</p>

<p>Two strings are <strong>alike</strong> if they have the same number of vowels (<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>, <code>&#39;A&#39;</code>, <code>&#39;E&#39;</code>, <code>&#39;I&#39;</code>, <code>&#39;O&#39;</code>, <code>&#39;U&#39;</code>). Notice that <code>s</code> contains uppercase and lowercase letters.</p>

<p>Return <code>true</code><em> if </em><code>a</code><em> and </em><code>b</code><em> are <strong>alike</strong></em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;book&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> a = &quot;b<u>o</u>&quot; and b = &quot;<u>o</u>k&quot;. a has 1 vowel and b has 1 vowel. Therefore, they are alike.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;textbook&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> a = &quot;t<u>e</u>xt&quot; and b = &quot;b<u>oo</u>k&quot;. a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s.length</code> is even.</li>
	<li><code>s</code> consists of <strong>uppercase and lowercase</strong> letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def halvesAreAlike(self, s: str) -> bool:
        cnt, n = 0, len(s) >> 1
        vowels = set('aeiouAEIOU')
        for i in range(n):
            cnt += s[i] in vowels
            cnt -= s[i + n] in vowels
        return cnt == 0
```

```python
class Solution:
    def halvesAreAlike(self, s: str) -> bool:
        vowels = set('aeiouAEIOU')
        a, b = s[:len(s) >> 1], s[len(s) >> 1:]
        return sum(c in vowels for c in a) == sum(c in vowels for c in b)
```

### **Java**

```java
class Solution {
    private static final Set<Character> VOWELS
        = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public boolean halvesAreAlike(String s) {
        int cnt = 0, n = s.length() >> 1;
        for (int i = 0; i < n; ++i) {
            cnt += VOWELS.contains(s.charAt(i)) ? 1 : 0;
            cnt -= VOWELS.contains(s.charAt(i + n)) ? 1 : 0;
        }
        return cnt == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool halvesAreAlike(string s) {
        unordered_set<char> vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int cnt = 0, n = s.size() / 2;
        for (int i = 0; i < n; ++i) {
            cnt += vowels.count(s[i]);
            cnt -= vowels.count(s[i + n]);
        }
        return cnt == 0;
    }
};
```

### **Go**

```go
func halvesAreAlike(s string) bool {
	vowels := map[byte]bool{}
	for _, c := range "aeiouAEIOU" {
		vowels[byte(c)] = true
	}
	cnt, n := 0, len(s)>>1
	for i := 0; i < n; i++ {
		if vowels[s[i]] {
			cnt++
		}
		if vowels[s[i+n]] {
			cnt--
		}
	}
	return cnt == 0
}
```

### **TypeScript**

```ts
function halvesAreAlike(s: string): boolean {
    const set = new Set(['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']);
    const n = s.length >> 1;
    let count = 0;
    for (let i = 0; i < n; i++) {
        set.has(s[i]) && count++;
        set.has(s[n + i]) && count--;
    }
    return count === 0;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn halves_are_alike(s: String) -> bool {
        let set: HashSet<&u8> = [b'a', b'e', b'i', b'o', b'u', b'A', b'E', b'I', b'O', b'U']
            .into_iter()
            .collect();
        let s = s.as_bytes();
        let n = s.len() >> 1;
        let mut count = 0;
        for i in 0..n {
            if set.contains(&s[i]) {
                count += 1;
            }
            if set.contains(&s[n + i]) {
                count -= 1;
            }
        }
        count == 0
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function halvesAreAlike($s) {
        $cnt = 0;
        for ($i = 0; $i < strlen($s) / 2; $i++) {
            if (strpos("aeiouAEIOU", $s[$i]) !== false) $cnt++;
            if (strpos("aeiouAEIOU", $s[strlen($s) / 2 + $i]) !== false) $cnt--;
        }
        return $cnt == 0;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var halvesAreAlike = function (s) {
    const str = 'aeiouAEIOU';
    let cnt = 0;
    for (let i = 0; i < s.length / 2; i++) {
        if (str.indexOf(s[i]) > -1) cnt++;
        if (str.indexOf(s[s.length - 1 - i]) > -1) cnt--;
    }
    return cnt === 0;
};
```

### **...**

```

```

<!-- tabs:end -->
