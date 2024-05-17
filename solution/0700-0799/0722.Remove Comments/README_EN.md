---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0722.Remove%20Comments/README_EN.md
tags:
    - Array
    - String
---

<!-- problem:start -->

# [722. Remove Comments](https://leetcode.com/problems/remove-comments)

[中文文档](/solution/0700-0799/0722.Remove%20Comments/README.md)

## Description

<!-- description:start -->

<p>Given a C++ program, remove comments from it. The program source is an array of strings <code>source</code> where <code>source[i]</code> is the <code>i<sup>th</sup></code> line of the source code. This represents the result of splitting the original source code string by the newline character <code>&#39;\n&#39;</code>.</p>

<p>In C++, there are two types of comments, line comments, and block comments.</p>

<ul>
	<li>The string <code>&quot;//&quot;</code> denotes a line comment, which represents that it and the rest of the characters to the right of it in the same line should be ignored.</li>
	<li>The string <code>&quot;/*&quot;</code> denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of <code>&quot;*/&quot;</code> should be ignored. (Here, occurrences happen in reading order: line by line from left to right.) To be clear, the string <code>&quot;/*/&quot;</code> does not yet end the block comment, as the ending would be overlapping the beginning.</li>
</ul>

<p>The first effective comment takes precedence over others.</p>

<ul>
	<li>For example, if the string <code>&quot;//&quot;</code> occurs in a block comment, it is ignored.</li>
	<li>Similarly, if the string <code>&quot;/*&quot;</code> occurs in a line or block comment, it is also ignored.</li>
</ul>

<p>If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.</p>

<p>There will be no control characters, single quote, or double quote characters.</p>

<ul>
	<li>For example, <code>source = &quot;string s = &quot;/* Not a comment. */&quot;;&quot;</code> will not be a test case.</li>
</ul>

<p>Also, nothing else such as defines or macros will interfere with the comments.</p>

<p>It is guaranteed that every open block comment will eventually be closed, so <code>&quot;/*&quot;</code> outside of a line or block comment always starts a new comment.</p>

<p>Finally, implicit newline characters can be deleted by block comments. Please see the examples below for details.</p>

<p>After removing the comments from the source code, return <em>the source code in the same format</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> source = [&quot;/*Test program */&quot;, &quot;int main()&quot;, &quot;{ &quot;, &quot;  // variable declaration &quot;, &quot;int a, b, c;&quot;, &quot;/* This is a test&quot;, &quot;   multiline  &quot;, &quot;   comment for &quot;, &quot;   testing */&quot;, &quot;a = b + c;&quot;, &quot;}&quot;]
<strong>Output:</strong> [&quot;int main()&quot;,&quot;{ &quot;,&quot;  &quot;,&quot;int a, b, c;&quot;,&quot;a = b + c;&quot;,&quot;}&quot;]
<strong>Explanation:</strong> The line by line code is visualized as below:
/*Test program */
int main()
{ 
  // variable declaration 
int a, b, c;
/* This is a test
   multiline  
   comment for 
   testing */
a = b + c;
}
The string /* denotes a block comment, including line 1 and lines 6-9. The string // denotes line 4 as comments.
The line by line output code is visualized as below:
int main()
{ 
  
int a, b, c;
a = b + c;
}
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> source = [&quot;a/*comment&quot;, &quot;line&quot;, &quot;more_comment*/b&quot;]
<strong>Output:</strong> [&quot;ab&quot;]
<strong>Explanation:</strong> The original source string is &quot;a/*comment\nline\nmore_comment*/b&quot;, where we have bolded the newline characters.  After deletion, the implicit newline characters are deleted, leaving the string &quot;ab&quot;, which when delimited by newline characters becomes [&quot;ab&quot;].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= source.length &lt;= 100</code></li>
	<li><code>0 &lt;= source[i].length &lt;= 80</code></li>
	<li><code>source[i]</code> consists of printable <strong>ASCII</strong> characters.</li>
	<li>Every open block comment is eventually closed.</li>
	<li>There are no single-quote or&nbsp;double-quote in the input.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def removeComments(self, source: List[str]) -> List[str]:
        ans = []
        t = []
        block_comment = False
        for s in source:
            i, m = 0, len(s)
            while i < m:
                if block_comment:
                    if i + 1 < m and s[i : i + 2] == "*/":
                        block_comment = False
                        i += 1
                else:
                    if i + 1 < m and s[i : i + 2] == "/*":
                        block_comment = True
                        i += 1
                    elif i + 1 < m and s[i : i + 2] == "//":
                        break
                    else:
                        t.append(s[i])
                i += 1
            if not block_comment and t:
                ans.append("".join(t))
                t.clear()
        return ans
```

```java
class Solution {
    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean blockComment = false;
        for (String s : source) {
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                if (blockComment) {
                    if (i + 1 < m && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                        blockComment = false;
                        ++i;
                    }
                } else {
                    if (i + 1 < m && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                        blockComment = true;
                        ++i;
                    } else if (i + 1 < m && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                        break;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
            if (!blockComment && sb.length() > 0) {
                ans.add(sb.toString());
                sb.setLength(0);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> removeComments(vector<string>& source) {
        vector<string> ans;
        string t;
        bool blockComment = false;
        for (auto& s : source) {
            int m = s.size();
            for (int i = 0; i < m; ++i) {
                if (blockComment) {
                    if (i + 1 < m && s[i] == '*' && s[i + 1] == '/') {
                        blockComment = false;
                        ++i;
                    }
                } else {
                    if (i + 1 < m && s[i] == '/' && s[i + 1] == '*') {
                        blockComment = true;
                        ++i;
                    } else if (i + 1 < m && s[i] == '/' && s[i + 1] == '/') {
                        break;
                    } else {
                        t.push_back(s[i]);
                    }
                }
            }
            if (!blockComment && !t.empty()) {
                ans.emplace_back(t);
                t.clear();
            }
        }
        return ans;
    }
};
```

```go
func removeComments(source []string) (ans []string) {
	t := []byte{}
	blockComment := false
	for _, s := range source {
		m := len(s)
		for i := 0; i < m; i++ {
			if blockComment {
				if i+1 < m && s[i] == '*' && s[i+1] == '/' {
					blockComment = false
					i++
				}
			} else {
				if i+1 < m && s[i] == '/' && s[i+1] == '*' {
					blockComment = true
					i++
				} else if i+1 < m && s[i] == '/' && s[i+1] == '/' {
					break
				} else {
					t = append(t, s[i])
				}
			}
		}
		if !blockComment && len(t) > 0 {
			ans = append(ans, string(t))
			t = []byte{}
		}
	}
	return
}
```

```ts
function removeComments(source: string[]): string[] {
    const ans: string[] = [];
    const t: string[] = [];
    let blockComment = false;
    for (const s of source) {
        const m = s.length;
        for (let i = 0; i < m; ++i) {
            if (blockComment) {
                if (i + 1 < m && s.slice(i, i + 2) === '*/') {
                    blockComment = false;
                    ++i;
                }
            } else {
                if (i + 1 < m && s.slice(i, i + 2) === '/*') {
                    blockComment = true;
                    ++i;
                } else if (i + 1 < m && s.slice(i, i + 2) === '//') {
                    break;
                } else {
                    t.push(s[i]);
                }
            }
        }
        if (!blockComment && t.length) {
            ans.push(t.join(''));
            t.length = 0;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn remove_comments(source: Vec<String>) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();
        let mut t: Vec<String> = Vec::new();
        let mut blockComment = false;

        for s in &source {
            let m = s.len();
            let mut i = 0;
            while i < m {
                if blockComment {
                    if i + 1 < m && &s[i..i + 2] == "*/" {
                        blockComment = false;
                        i += 2;
                    } else {
                        i += 1;
                    }
                } else {
                    if i + 1 < m && &s[i..i + 2] == "/*" {
                        blockComment = true;
                        i += 2;
                    } else if i + 1 < m && &s[i..i + 2] == "//" {
                        break;
                    } else {
                        t.push(s.chars().nth(i).unwrap().to_string());
                        i += 1;
                    }
                }
            }
            if !blockComment && !t.is_empty() {
                ans.push(t.join(""));
                t.clear();
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
