# [722. 删除注释](https://leetcode.cn/problems/remove-comments)

[English Version](/solution/0700-0799/0722.Remove%20Comments/README_EN.md)

<!-- tags:数组,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给一个 C++ 程序，删除程序中的注释。这个程序<code>source</code>是一个数组，其中<code>source[i]</code>表示第&nbsp;<code>i</code>&nbsp;行源码。&nbsp;这表示每行源码由 <code>'\n'</code>&nbsp;分隔。</p>

<p>在 C++ 中有两种注释风格，行内注释和块注释。</p>

<ul>
	<li>字符串<code>//</code> 表示行注释，表示<code>//</code>和其右侧的其余字符应该被忽略。</li>
	<li>字符串<code>/*</code> 表示一个块注释，它表示直到下一个（非重叠）出现的<code>*/</code>之间的所有字符都应该被忽略。（阅读顺序为从左到右）非重叠是指，字符串<code>/*/</code>并没有结束块注释，因为注释的结尾与开头相重叠。</li>
</ul>

<p>第一个有效注释优先于其他注释。</p>

<ul>
	<li>如果字符串<code>//</code>出现在块注释中会被忽略。</li>
	<li>同样，如果字符串<code>/*</code>出现在行或块注释中也会被忽略。</li>
</ul>

<p>如果一行在删除注释之后变为空字符串，那么<strong>不要</strong>输出该行。即，答案列表中的每个字符串都是非空的。</p>

<p>样例中<strong>没有</strong>控制字符，单引号或双引号字符。</p>

<ul>
	<li>比如，<code>source = "string s = "/* Not a comment. */";"</code> 不会出现在测试样例里。</li>
</ul>

<p>此外，没有其他内容（如定义或宏）会干扰注释。</p>

<p>我们保证每一个块注释最终都会被闭合， 所以在行或块注释之外的<code>/*</code>总是开始新的注释。</p>

<p>最后，隐式换行符<strong>可以</strong>通过块注释删除。 有关详细信息，请参阅下面的示例。</p>

<p>从源代码中删除注释后，需要以相同的格式返回源代码。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> source = ["/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"]
<strong>输出:</strong> ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
<strong>解释:</strong> 示例代码可以编排成这样:
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
第 1 行和第 6-9 行的字符串 /* 表示块注释。第 4 行的字符串 // 表示行注释。
编排后: 
int main()
{ 
  
int a, b, c;
a = b + c;
}</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> source = ["a/*comment", "line", "more_comment*/b"]
<strong>输出:</strong> ["ab"]
<strong>解释:</strong> 原始的 source 字符串是 "a/*comment<strong>\n</strong>line<strong>\n</strong>more_comment*/b", 其中我们用粗体显示了换行符。删除注释后，隐含的换行符被删除，留下字符串 "ab" 用换行符分隔成数组时就是 ["ab"].
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= source.length &lt;= 100</code></li>
	<li><code>0 &lt;= source[i].length &lt;= 80</code></li>
	<li><code>source[i]</code>&nbsp;由可打印的 <strong>ASCII</strong> 字符组成。</li>
	<li>每个块注释都会被闭合。</li>
	<li>给定的源码中不会有单引号、双引号或其他控制字符。</li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute"><span style="top:0px"><span style="left:-9999px"><span style="opacity:0"><span style="overflow:hidden">&nbsp;</span></span></span></span></span></span>​​​​​​</span>

## 解法

### 方法一：分情况讨论

我们用一个变量 $blockComment$ 来表示当前是否处于块注释中，初始时 $blockComment$ 为 `false`；用一个变量 $t$ 来存储当前行的有效字符。

接下来，遍历每一行，分情况讨论：

如果当前处于块注释中，那么如果当前字符和下一个字符是 `'*/'`，说明块注释结束，我们将 $blockComment$ 置为 `false`，并且跳过这两个字符；否则，我们继续保持块注释状态，不做任何操作；

如果当前不处于块注释中，那么如果当前字符和下一个字符是 `'/*'`，说明块注释开始，我们将 $blockComment$ 置为 `true`，并且跳过这两个字符；如果当前字符和下一个字符是 `'//'`，那么说明行注释开始，我们直接退出当前行的遍历；否则，说明当前字符是有效字符，我们将其加入 $t$ 中；

遍历完当前行后，如果 $blockComment$ 为 `false`，并且 $t$ 不为空，说明当前行是有效行，我们将其加入答案数组中，并且清空 $t$。继续遍历下一行。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 是源代码的总长度。

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

<!-- end -->
