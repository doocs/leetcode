# [1805. Number of Different Integers in a String](https://leetcode.com/problems/number-of-different-integers-in-a-string)

[中文文档](/solution/1800-1899/1805.Number%20of%20Different%20Integers%20in%20a%20String/README.md)

## Description

<p>You are given a string <code>word</code> that consists of digits and lowercase English letters.</p>

<p>You will replace every non-digit character with a space. For example, <code>&quot;a123bc34d8ef34&quot;</code> will become <code>&quot; 123&nbsp; 34 8&nbsp; 34&quot;</code>. Notice that you are left with some integers that are separated by at least one space: <code>&quot;123&quot;</code>, <code>&quot;34&quot;</code>, <code>&quot;8&quot;</code>, and <code>&quot;34&quot;</code>.</p>

<p>Return <em>the number of <strong>different</strong> integers after performing the replacement operations on </em><code>word</code>.</p>

<p>Two integers are considered different if their decimal representations <strong>without any leading zeros</strong> are different.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;a<u>123</u>bc<u>34</u>d<u>8</u>ef<u>34</u>&quot;
<strong>Output:</strong> 3
<strong>Explanation: </strong>The three different integers are &quot;123&quot;, &quot;34&quot;, and &quot;8&quot;. Notice that &quot;34&quot; is only counted once.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;leet<u>1234</u>code<u>234</u>&quot;
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;a<u>1</u>b<u>01</u>c<u>001</u>&quot;
<strong>Output:</strong> 1
<strong>Explanation: </strong>The three integers &quot;1&quot;, &quot;01&quot;, and &quot;001&quot; all represent the same integer because
the leading zeros are ignored when comparing their decimal values.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 1000</code></li>
	<li><code>word</code> consists of digits and lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numDifferentIntegers(self, word: str) -> int:
        s = set()
        i, n = 0, len(word)
        while i < n:
            if word[i].isdigit():
                while i < n and word[i] == '0':
                    i += 1
                j = i
                while j < n and word[j].isdigit():
                    j += 1
                s.add(word[i: j])
                i = j
            i += 1
        return len(s)
```

### **Java**

```java
class Solution {
    public int numDifferentIntegers(String word) {
        Set<String> s = new HashSet<>();
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(word.charAt(i))) {
                while (i < n && word.charAt(i) == '0') {
                    ++i;
                }
                int j = i;
                while (j < n && Character.isDigit(word.charAt(j))) {
                    ++j;
                }
                s.add(word.substring(i, j));
                i = j;
            }
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numDifferentIntegers(string word) {
        unordered_set<string> s;
        int n = word.size();
        for (int i = 0; i < n; ++i) {
            if (isdigit(word[i])) {
                while (i < n && word[i] == '0') ++i;
                int j = i;
                while (j < n && isdigit(word[j])) ++j;
                s.insert(word.substr(i, j - i));
                i = j;
            }
        }
        return s.size();
    }
};
```

### **Go**

```go
func numDifferentIntegers(word string) int {
	s := map[string]struct{}{}
	n := len(word)
	for i := 0; i < n; i++ {
		if word[i] >= '0' && word[i] <= '9' {
			for i < n && word[i] == '0' {
				i++
			}
			j := i
			for j < n && word[j] >= '0' && word[j] <= '9' {
				j++
			}
			s[word[i:j]] = struct{}{}
			i = j
		}
	}
	return len(s)
}
```

### **TypeScript**

```ts
function numDifferentIntegers(word: string): number {
    return new Set(
        word
            .replace(/\D+/g, ' ')
            .trim()
            .split(' ')
            .filter(v => v !== '')
            .map(v => v.replace(/^0+/g, '')),
    ).size;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn num_different_integers(word: String) -> i32 {
        let s = word.as_bytes();
        let n = s.len();
        let mut set = HashSet::new();
        let mut i = 0;
        while i < n {
            if s[i] >= b'0' && s[i] <= b'9' {
                let mut j = i;
                while j < n && s[j] >= b'0' && s[j] <= b'9' {
                    j += 1;
                }
                while i < j - 1 && s[i] == b'0' {
                    i += 1;
                }
                set.insert(String::from_utf8(s[i..j].to_vec()).unwrap());
                i = j;
            } else {
                i += 1;
            }
        }
        set.len() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
