# [591. Tag Validator](https://leetcode.com/problems/tag-validator)

[中文文档](/solution/0500-0599/0591.Tag%20Validator/README.md)

## Description

<p>Given a string representing a code snippet, implement a tag validator to parse the code and return whether it is valid.</p>

<p>A code snippet is valid if all the following rules hold:</p>

<ol>
	<li>The code must be wrapped in a <b>valid closed tag</b>. Otherwise, the code is invalid.</li>
	<li>A <b>closed tag</b> (not necessarily valid) has exactly the following format : <code>&lt;TAG_NAME&gt;TAG_CONTENT&lt;/TAG_NAME&gt;</code>. Among them, <code>&lt;TAG_NAME&gt;</code> is the start tag, and <code>&lt;/TAG_NAME&gt;</code> is the end tag. The TAG_NAME in start and end tags should be the same. A closed tag is <b>valid</b> if and only if the TAG_NAME and TAG_CONTENT are valid.</li>
	<li>A <b>valid</b> <code>TAG_NAME</code> only contain <b>upper-case letters</b>, and has length in range [1,9]. Otherwise, the <code>TAG_NAME</code> is <b>invalid</b>.</li>
	<li>A <b>valid</b> <code>TAG_CONTENT</code> may contain other <b>valid closed tags</b>, <b>cdata</b> and any characters (see note1) <b>EXCEPT</b> unmatched <code>&lt;</code>, unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. Otherwise, the <code>TAG_CONTENT</code> is <b>invalid</b>.</li>
	<li>A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. However, you also need to consider the issue of unbalanced when tags are nested.</li>
	<li>A <code>&lt;</code> is unmatched if you cannot find a subsequent <code>&gt;</code>. And when you find a <code>&lt;</code> or <code>&lt;/</code>, all the subsequent characters until the next <code>&gt;</code> should be parsed as TAG_NAME (not necessarily valid).</li>
	<li>The cdata has the following format : <code>&lt;![CDATA[CDATA_CONTENT]]&gt;</code>. The range of <code>CDATA_CONTENT</code> is defined as the characters between <code>&lt;![CDATA[</code> and the <b>first subsequent</b> <code>]]&gt;</code>.</li>
	<li><code>CDATA_CONTENT</code> may contain <b>any characters</b>. The function of cdata is to forbid the validator to parse <code>CDATA_CONTENT</code>, so even it has some characters that can be parsed as tag (no matter valid or invalid), you should treat it as <b>regular characters</b>.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> code = &quot;&lt;DIV&gt;This is the first line &lt;![CDATA[&lt;div&gt;]]&gt;&lt;/DIV&gt;&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> 
The code is wrapped in a closed tag : &lt;DIV&gt; and &lt;/DIV&gt;. 
The TAG_NAME is valid, the TAG_CONTENT consists of some characters and cdata. 
Although CDATA_CONTENT has an unmatched start tag with invalid TAG_NAME, it should be considered as plain text, not parsed as a tag.
So TAG_CONTENT is valid, and then the code is valid. Thus return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> code = &quot;&lt;DIV&gt;&gt;&gt;  ![cdata[]] &lt;![CDATA[&lt;div&gt;]&gt;]]&gt;]]&gt;&gt;]&lt;/DIV&gt;&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
We first separate the code into : start_tag|tag_content|end_tag.
start_tag -&gt; <b>&quot;&lt;DIV&gt;&quot;</b>
end_tag -&gt; <b>&quot;&lt;/DIV&gt;&quot;</b>
tag_content could also be separated into : text1|cdata|text2.
text1 -&gt; <b>&quot;&gt;&gt;  ![cdata[]] &quot;</b>
cdata -&gt; <b>&quot;&lt;![CDATA[&lt;div&gt;]&gt;]]&gt;&quot;</b>, where the CDATA_CONTENT is <b>&quot;&lt;div&gt;]&gt;&quot;</b>
text2 -&gt; <b>&quot;]]&gt;&gt;]&quot;</b>
The reason why start_tag is NOT <b>&quot;&lt;DIV&gt;&gt;&gt;&quot;</b> is because of the rule 6.
The reason why cdata is NOT <b>&quot;&lt;![CDATA[&lt;div&gt;]&gt;]]&gt;]]&gt;&quot;</b> is because of the rule 7.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> code = &quot;&lt;A&gt;  &lt;B&gt; &lt;/A&gt;   &lt;/B&gt;&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> Unbalanced. If &quot;&lt;A&gt;&quot; is closed, then &quot;&lt;B&gt;&quot; must be unmatched, and vice versa.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= code.length &lt;= 500</code></li>
	<li><code>code</code> consists of English letters, digits, <code>&#39;&lt;&#39;</code>, <code>&#39;&gt;&#39;</code>, <code>&#39;/&#39;</code>, <code>&#39;!&#39;</code>, <code>&#39;[&#39;</code>, <code>&#39;]&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39; &#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isValid(self, code: str) -> bool:
        def check(tag):
            return 1 <= len(tag) <= 9 and all(c.isupper() for c in tag)

        stk = []
        i, n = 0, len(code)
        while i < n:
            if i and not stk:
                return False
            if code[i : i + 9] == '<![CDATA[':
                i = code.find(']]>', i + 9)
                if i < 0:
                    return False
                i += 2
            elif code[i : i + 2] == '</':
                j = i + 2
                i = code.find('>', j)
                if i < 0:
                    return False
                t = code[j:i]
                if not check(t) or not stk or stk.pop() != t:
                    return False
            elif code[i] == '<':
                j = i + 1
                i = code.find('>', j)
                if i < 0:
                    return False
                t = code[j:i]
                if not check(t):
                    return False
                stk.append(t)
            i += 1
        return not stk
```

### **Java**

```java
class Solution {
    public boolean isValid(String code) {
        Deque<String> stk = new ArrayDeque<>();
        for (int i = 0; i < code.length(); ++i) {
            if (i > 0 && stk.isEmpty()) {
                return false;
            }
            if (code.startsWith("<![CDATA[", i)) {
                i = code.indexOf("]]>", i + 9);
                if (i < 0) {
                    return false;
                }
                i += 2;
            } else if (code.startsWith("</", i)) {
                int j = i + 2;
                i = code.indexOf(">", j);
                if (i < 0) {
                    return false;
                }
                String t = code.substring(j, i);
                if (!check(t) || stk.isEmpty() || !stk.pop().equals(t)) {
                    return false;
                }
            } else if (code.startsWith("<", i)) {
                int j = i + 1;
                i = code.indexOf(">", j);
                if (i < 0) {
                    return false;
                }
                String t = code.substring(j, i);
                if (!check(t)) {
                    return false;
                }
                stk.push(t);
            }
        }
        return stk.isEmpty();
    }

    private boolean check(String tag) {
        int n = tag.length();
        if (n < 1 || n > 9) {
            return false;
        }
        for (char c : tag.toCharArray()) {
            if (!Character.isUpperCase(c)) {
                return false;
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
    bool isValid(string code) {
        stack<string> stk;
        for (int i = 0; i < code.size(); ++i) {
            if (i && stk.empty()) return false;
            if (code.substr(i, 9) == "<![CDATA[") {
                i = code.find("]]>", i + 9);
                if (i < 0) return false;
                i += 2;
            } else if (code.substr(i, 2) == "</") {
                int j = i + 2;
                i = code.find('>', j);
                if (i < 0) return false;
                string t = code.substr(j, i - j);
                if (!check(t) || stk.empty() || stk.top() != t) return false;
                stk.pop();
            } else if (code.substr(i, 1) == "<") {
                int j = i + 1;
                i = code.find('>', j);
                if (i < 0) return false;
                string t = code.substr(j, i - j);
                if (!check(t)) return false;
                stk.push(t);
            }
        }
        return stk.empty();
    }

    bool check(string tag) {
        int n = tag.size();
        if (n < 1 || n > 9) return false;
        for (char& c : tag)
            if (!isupper(c))
                return false;
        return true;
    }
};
```

### **Go**

```go
func isValid(code string) bool {
	var stk []string
	for i := 0; i < len(code); i++ {
		if i > 0 && len(stk) == 0 {
			return false
		}
		if strings.HasPrefix(code[i:], "<![CDATA[") {
			n := strings.Index(code[i+9:], "]]>")
			if n == -1 {
				return false
			}
			i += n + 11
		} else if strings.HasPrefix(code[i:], "</") {
			if len(stk) == 0 {
				return false
			}
			j := i + 2
			n := strings.IndexByte(code[j:], '>')
			if n == -1 {
				return false
			}
			t := code[j : j+n]
			last := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			if !check(t) || last != t {
				return false
			}
			i += n + 2
		} else if strings.HasPrefix(code[i:], "<") {
			j := i + 1
			n := strings.IndexByte(code[j:], '>')
			if n == -1 {
				return false
			}
			t := code[j : j+n]
			if !check(t) {
				return false
			}
			stk = append(stk, t)
			i += n + 1
		}
	}
	return len(stk) == 0
}

func check(tag string) bool {
	n := len(tag)
	if n < 1 || n > 9 {
		return false
	}
	for _, c := range tag {
		if c < 'A' || c > 'Z' {
			return false
		}
	}
	return true
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_valid(code: String) -> bool {
        fn check(tag: &str) -> bool {
            let n = tag.len();
            n >= 1 && n <= 9 && tag.as_bytes().iter().all(|b| b.is_ascii_uppercase())
        }

        let mut stk = Vec::new();
        let mut i = 0;
        while i < code.len() {
            if i > 0 && stk.is_empty() {
                return false;
            }
            if code[i..].starts_with("<![CDATA[") {
                match code[i + 9..].find("]]>") {
                    Some(n) => i += n + 11,
                    None => return false,
                };
            } else if code[i..].starts_with("</") {
                let j = i + 2;
                match code[j..].find('>') {
                    Some(n) => {
                        let t = &code[j..j + n];
                        if !check(t) || stk.is_empty() || stk.pop().unwrap() != t {
                            return false;
                        }
                        i += n + 2;
                    }
                    None => return false,
                };
            } else if code[i..].starts_with("<") {
                let j = i + 1;
                match code[j..].find('>') {
                    Some(n) => {
                        let t = &code[j..j + n];
                        if !check(t) {
                            return false;
                        }
                        stk.push(t);
                    }
                    None => return false,
                };
            }
            i += 1;
        }
        stk.is_empty()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
