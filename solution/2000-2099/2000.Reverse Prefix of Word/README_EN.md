# [2000. Reverse Prefix of Word](https://leetcode.com/problems/reverse-prefix-of-word)

[中文文档](/solution/2000-2099/2000.Reverse%20Prefix%20of%20Word/README.md)

## Description

<p>Given a <strong>0-indexed</strong> string <code>word</code> and a character <code>ch</code>, <strong>reverse</strong> the segment of <code>word</code> that starts at index <code>0</code> and ends at the index of the <strong>first occurrence</strong> of <code>ch</code> (<strong>inclusive</strong>). If the character <code>ch</code> does not exist in <code>word</code>, do nothing.</p>

<ul>
	<li>For example, if <code>word = &quot;abcdefd&quot;</code> and <code>ch = &quot;d&quot;</code>, then you should <strong>reverse</strong> the segment that starts at <code>0</code> and ends at <code>3</code> (<strong>inclusive</strong>). The resulting string will be <code>&quot;<u>dcba</u>efd&quot;</code>.</li>
</ul>

<p>Return <em>the resulting string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;<u>abcd</u>efd&quot;, ch = &quot;d&quot;
<strong>Output:</strong> &quot;<u>dcba</u>efd&quot;
<strong>Explanation:</strong>&nbsp;The first occurrence of &quot;d&quot; is at index 3. 
Reverse the part of word from 0 to 3 (inclusive), the resulting string is &quot;dcbaefd&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;<u>xyxz</u>xe&quot;, ch = &quot;z&quot;
<strong>Output:</strong> &quot;<u>zxyx</u>xe&quot;
<strong>Explanation:</strong>&nbsp;The first and only occurrence of &quot;z&quot; is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is &quot;zxyxxe&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abcd&quot;, ch = &quot;z&quot;
<strong>Output:</strong> &quot;abcd&quot;
<strong>Explanation:</strong>&nbsp;&quot;z&quot; does not exist in word.
You should not do any reverse operation, the resulting string is &quot;abcd&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 250</code></li>
	<li><code>word</code> consists of lowercase English letters.</li>
	<li><code>ch</code> is a lowercase English letter.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reversePrefix(self, word: str, ch: str) -> str:
        i = word.find(ch)
        return word if i == -1 else word[i::-1] + word[i + 1 :]
```

### **Java**

```java
class Solution {
    public String reversePrefix(String word, char ch) {
        int j = word.indexOf(ch);
        if (j == -1) {
            return word;
        }
        char[] cs = word.toCharArray();
        for (int i = 0; i < j; ++i, --j) {
            char t = cs[i];
            cs[i] = cs[j];
            cs[j] = t;
        }
        return String.valueOf(cs);
    }
}
```

```java
class Solution {
    public String reversePrefix(String word, char ch) {
        int j = word.indexOf(ch);
        if (j == -1) {
            return word;
        }
        return new StringBuilder(word.substring(0, j + 1))
            .reverse()
            .append(word.substring(j + 1))
            .toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reversePrefix(string word, char ch) {
        int i = word.find(ch);
        if (i != string::npos) {
            reverse(word.begin(), word.begin() + i + 1);
        }
        return word;
    }
};
```

### **Go**

```go
func reversePrefix(word string, ch byte) string {
	j := strings.IndexByte(word, ch)
	if j < 0 {
		return word
	}
	s := []byte(word)
	for i := 0; i < j; i++ {
		s[i], s[j] = s[j], s[i]
		j--
	}
	return string(s)
}
```

### **TypeScript**

```ts
function reversePrefix(word: string, ch: string): string {
    const i = word.indexOf(ch) + 1;
    if (!i) {
        return word;
    }
    return [...word.slice(0, i)].reverse().join('') + word.slice(i);
}
```

### **Rust**

```rust
impl Solution {
    pub fn reverse_prefix(word: String, ch: char) -> String {
        match word.find(ch) {
            Some(i) => word[..=i].chars().rev().collect::<String>() + &word[i + 1..],
            None => word,
        }
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String $word
     * @param String $ch
     * @return String
     */
    function reversePrefix($word, $ch) {
        $len = strlen($word);
        $rs = '';
        for ($i = 0; $i < $len; $i++) {
            $rs = $rs.$word[$i];
            if ($word[$i] == $ch) {
                break;
            }
        }
        if (strlen($rs) == $len && $rs[$len - 1] != $ch) {
            return $word;
        }
        $rs = strrev($rs);
        $rs = $rs.substr($word, strlen($rs));
        return $rs;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
