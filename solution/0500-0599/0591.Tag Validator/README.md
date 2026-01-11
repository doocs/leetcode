---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0591.Tag%20Validator/README.md
tags:
    - 栈
    - 字符串
---

<!-- problem:start -->

# [591. 标签验证器](https://leetcode.cn/problems/tag-validator)

[English Version](/solution/0500-0599/0591.Tag%20Validator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个表示代码片段的字符串，你需要实现一个验证器来解析这段代码，并返回它是否合法。合法的代码片段需要遵守以下的所有规则：</p>

<ol>
	<li>代码必须被<strong>合法的闭合标签</strong>包围。否则，代码是无效的。</li>
	<li><strong>闭合标签</strong>（不一定合法）要严格符合格式：<code>&lt;TAG_NAME&gt;TAG_CONTENT&lt;/TAG_NAME&gt;</code>。其中，<code>&lt;TAG_NAME&gt;</code>是起始标签，<code>&lt;/TAG_NAME&gt;</code>是结束标签。起始和结束标签中的 TAG_NAME 应当相同。当且仅当&nbsp;TAG_NAME 和 TAG_CONTENT 都是合法的，闭合标签才是<strong>合法的</strong>。</li>
	<li><strong>合法的</strong>&nbsp;<code>TAG_NAME</code>&nbsp;仅含有<strong>大写字母</strong>，长度在范围 [1,9] 之间。否则，该&nbsp;<code>TAG_NAME</code>&nbsp;是<strong>不合法的</strong>。</li>
	<li><strong>合法的</strong>&nbsp;<code>TAG_CONTENT</code>&nbsp;可以包含其他<strong>合法的闭合标签</strong>，<strong>cdata</strong>&nbsp;（请参考规则7）和任意字符（注意参考规则1）<strong>除了</strong>不匹配的<code>&lt;</code>、不匹配的起始和结束标签、不匹配的或带有不合法 TAG_NAME 的闭合标签。否则，<code>TAG_CONTENT</code>&nbsp;是<strong>不合法的</strong>。</li>
	<li>一个起始标签，如果没有具有相同&nbsp;TAG_NAME 的结束标签与之匹配，是不合法的。反之亦然。不过，你也需要考虑标签嵌套的问题。</li>
	<li>一个<code>&lt;</code>，如果你找不到一个后续的<code>&gt;</code>与之匹配，是不合法的。并且当你找到一个<code>&lt;</code>或<code>&lt;/</code>时，所有直到下一个<code>&gt;</code>的前的字符，都应当被解析为&nbsp;TAG_NAME（不一定合法）。</li>
	<li>cdata 有如下格式：<code>&lt;![CDATA[CDATA_CONTENT]]&gt;</code>。<code>CDATA_CONTENT</code>&nbsp;的范围被定义成&nbsp;<code>&lt;![CDATA[</code>&nbsp;和<strong>后续的第一个</strong>&nbsp;<code>]]&gt;</code>之间的字符。</li>
	<li><code>CDATA_CONTENT</code>&nbsp;可以包含<strong>任意字符</strong>。cdata 的功能是阻止验证器解析<code>CDATA_CONTENT</code>，所以即使其中有一些字符可以被解析为标签（无论合法还是不合法），也应该将它们视为<strong>常规字符</strong>。</li>
</ol>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>code = "&lt;DIV&gt;This is the first line &lt;![CDATA[&lt;div&gt;]]&gt;&lt;/DIV&gt;"
<b>输出：</b>true
<b>解释：</b>
代码被闭合的标签包围：&lt;DIV&gt; 和 &lt;/DIV&gt;。
TAG_NAME 是合法的，TAG_CONTENT 只包含一些字母和 cdata。
尽管 CDATA_CONTENT 有一个非法 TAG_NAME 的未匹配开始标签，它可以被认为是普通文本，不被解析为一个标签。
所以 TAG_CONTENT 是合法的，并且代码是合法的。因此返回 true。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>code = "&lt;DIV&gt;&gt;&gt;  ![cdata[]] &lt;![CDATA[&lt;div&gt;]&gt;]]&gt;]]&gt;&gt;]&lt;/DIV&gt;"
<b>输出：</b>true
<strong>解释：</strong>
我们首先将代码分割为：start_tag|tag_content|end_tag。
start_tag -&gt; <b>"&lt;DIV&gt;"</b>
end_tag -&gt; <b>"&lt;/DIV&gt;"</b>
tag_content 也可以被分割为：text1|cdata|text2。
text1 -&gt; <b>"&gt;&gt;  ![cdata[]] "</b>
cdata -&gt; <b>"&lt;![CDATA[&lt;div&gt;]&gt;]]&gt;"</b>，其中 CDATA_CONTENT 是 <b>"&lt;div&gt;]&gt;"</b>
text2 -&gt; <b>"]]&gt;&gt;]"</b>
start_tag 不是 <b>"&lt;DIV&gt;&gt;&gt;"</b> 的原因是规则 6。
cdata 不是 <b>"&lt;![CDATA[&lt;div&gt;]&gt;]]&gt;]]&gt;"</b> 的原因是规则 7。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>code = "&lt;A&gt;  &lt;B&gt; &lt;/A&gt;   &lt;/B&gt;"
<b>输出：</b>false
<b>解释：</b>不平衡。如果 "&lt;A&gt;" 是闭合的，那么 "&lt;B&gt;" 一定没有匹配，反之亦然。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= code.length &lt;= 500</code></li>
	<li><code>code</code>&nbsp;只包含英文字母，数字，<code>'&lt;'</code>，<code>'&gt;'</code>，<code>'/'</code>，<code>'!'</code>，<code>'['</code>，<code>']'</code>，<code>'.'</code>&nbsp;和&nbsp;<code>' '</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈模拟

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### Rust

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
                    Some(n) => {
                        i += n + 11;
                    }
                    None => {
                        return false;
                    }
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
                    None => {
                        return false;
                    }
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
                    None => {
                        return false;
                    }
                };
            }
            i += 1;
        }
        stk.is_empty()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
