---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0071.Simplify%20Path/README.md
tags:
    - 栈
    - 字符串
---

<!-- problem:start -->

# [71. 简化路径](https://leetcode.cn/problems/simplify-path)

[English Version](/solution/0000-0099/0071.Simplify%20Path/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>path</code> ，表示指向某一文件或目录的&nbsp;Unix 风格 <strong>绝对路径 </strong>（以 <code>'/'</code> 开头），请你将其转化为 <strong>更加简洁的规范路径</strong>。</p>

<p class="MachineTrans-lang-zh-CN">在 Unix 风格的文件系统中规则如下：</p>

<ul>
	<li class="MachineTrans-lang-zh-CN">一个点&nbsp;<code>'.'</code>&nbsp;表示当前目录本身。</li>
	<li class="MachineTrans-lang-zh-CN">此外，两个点 <code>'..'</code>&nbsp;表示将目录切换到上一级（指向父目录）。</li>
	<li class="MachineTrans-lang-zh-CN">任意多个连续的斜杠（即，<code>'//'</code>&nbsp;或 <code>'///'</code>）都被视为单个斜杠 <code>'/'</code>。</li>
	<li class="MachineTrans-lang-zh-CN">任何其他格式的点（例如，<code>'...'</code>&nbsp;或 <code>'....'</code>）均被视为有效的文件/目录名称。</li>
</ul>

<p>返回的 <strong>简化路径</strong> 必须遵循下述格式：</p>

<ul>
	<li>始终以斜杠 <code>'/'</code> 开头。</li>
	<li>两个目录名之间必须只有一个斜杠 <code>'/'</code> 。</li>
	<li>最后一个目录名（如果存在）<strong>不能 </strong>以 <code>'/'</code> 结尾。</li>
	<li>此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 <code>'.'</code> 或 <code>'..'</code>）。</li>
</ul>

<p>返回简化后得到的 <strong>规范路径</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">path = "/home/"</span></p>

<p><span class="example-io"><b>输出：</b>"/home"</span></p>

<p><strong>解释：</strong></p>

<p>应删除尾随斜杠。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>path = "/home//foo/"</span></p>

<p><span class="example-io"><b>输出：</b>"/home/foo"</span></p>

<p><strong>解释：</strong></p>

<p>多个连续的斜杠被单个斜杠替换。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">path = "/home/user/Documents/../Pictures"</span></p>

<p><span class="example-io"><b>输出：</b>"/home/user/Pictures"</span></p>

<p><strong>解释：</strong></p>

<p>两个点&nbsp;<code>".."</code>&nbsp;表示上一级目录（父目录）。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>path = "/../"</span></p>

<p><span class="example-io"><b>输出：</b>"/"</span></p>

<p><strong>解释：</strong></p>

<p>不可能从根目录上升一级目录。</p>
</div>

<p><strong class="example">示例 5：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>path = "/.../a/../b/c/../d/./"</span></p>

<p><span class="example-io"><b>输出：</b>"/.../b/d"</span></p>

<p><strong>解释：</strong></p>

<p><code>"..."</code>&nbsp;在这个问题中是一个合法的目录名。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= path.length &lt;= 3000</code></li>
	<li><code>path</code> 由英文字母，数字，<code>'.'</code>，<code>'/'</code> 或 <code>'_'</code> 组成。</li>
	<li><code>path</code> 是一个有效的 Unix 风格绝对路径。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

我们先将路径按照 `'/'` 分割成若干个子串，然后遍历每个子串，根据子串的内容进行如下操作：

- 若子串为空，或者为 `'.'`，则不做任何操作，因为 `'.'` 表示当前目录；
- 若子串为 `'..'`，则需要将栈顶元素弹出，因为 `'..'` 表示上一级目录；
- 若子串为其他字符串，则将该子串入栈，因为该子串表示当前目录的子目录。

最后，我们将栈中的所有元素按照从栈底到栈顶的顺序拼接成字符串，即为简化后的规范路径。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为路径的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def simplifyPath(self, path: str) -> str:
        stk = []
        for s in path.split('/'):
            if not s or s == '.':
                continue
            if s == '..':
                if stk:
                    stk.pop()
            else:
                stk.append(s)
        return '/' + '/'.join(stk)
```

#### Java

```java
class Solution {
    public String simplifyPath(String path) {
        Deque<String> stk = new ArrayDeque<>();
        for (String s : path.split("/")) {
            if ("".equals(s) || ".".equals(s)) {
                continue;
            }
            if ("..".equals(s)) {
                stk.pollLast();
            } else {
                stk.offerLast(s);
            }
        }
        return "/" + String.join("/", stk);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string simplifyPath(string path) {
        deque<string> stk;
        stringstream ss(path);
        string t;
        while (getline(ss, t, '/')) {
            if (t == "" || t == ".") {
                continue;
            }
            if (t == "..") {
                if (!stk.empty()) {
                    stk.pop_back();
                }
            } else {
                stk.push_back(t);
            }
        }
        if (stk.empty()) {
            return "/";
        }
        string ans;
        for (auto& s : stk) {
            ans += "/" + s;
        }
        return ans;
    }
};
```

#### Go

```go
func simplifyPath(path string) string {
	var stk []string
	for _, s := range strings.Split(path, "/") {
		if s == "" || s == "." {
			continue
		}
		if s == ".." {
			if len(stk) > 0 {
				stk = stk[0 : len(stk)-1]
			}
		} else {
			stk = append(stk, s)
		}
	}
	return "/" + strings.Join(stk, "/")
}
```

#### TypeScript

```ts
function simplifyPath(path: string): string {
    const stk: string[] = [];
    for (const s of path.split('/')) {
        if (s === '' || s === '.') {
            continue;
        }
        if (s === '..') {
            if (stk.length) {
                stk.pop();
            }
        } else {
            stk.push(s);
        }
    }
    return '/' + stk.join('/');
}
```

#### Rust

```rust
impl Solution {
    pub fn simplify_path(path: String) -> String {
        let mut stk = Vec::new();
        for s in path.split('/') {
            match s {
                "" | "." => continue,
                ".." => {
                    stk.pop();
                }
                _ => stk.push(s),
            }
        }
        "/".to_string() + &stk.join("/")
    }
}
```

#### C#

```cs
public class Solution {
    public string SimplifyPath(string path) {
        var stk = new Stack<string>();
        foreach (var s in path.Split('/')) {
            if (s == "" || s == ".") {
                continue;
            }
            if (s == "..") {
                if (stk.Count > 0) {
                    stk.Pop();
                }
            } else {
                stk.Push(s);
            }
        }
        var sb = new StringBuilder();
        while (stk.Count > 0) {
            sb.Insert(0, "/" + stk.Pop());
        }
        return sb.Length == 0 ? "/" : sb.ToString();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
