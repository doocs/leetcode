# [71. Simplify Path](https://leetcode.com/problems/simplify-path)

[中文文档](/solution/0000-0099/0071.Simplify%20Path/README.md)

## Description

<p>Given a string <code>path</code>, which is an <strong>absolute path</strong> (starting with a slash <code>&#39;/&#39;</code>) to a file or directory in a Unix-style file system, convert it to the simplified <strong>canonical path</strong>.</p>

<p>In a Unix-style file system, a period <code>&#39;.&#39;</code> refers to the current directory, a double period <code>&#39;..&#39;</code> refers to the directory up a level, and any multiple consecutive slashes (i.e. <code>&#39;//&#39;</code>) are treated as a single slash <code>&#39;/&#39;</code>. For this problem, any other format of periods such as <code>&#39;...&#39;</code> are treated as file/directory names.</p>

<p>The <strong>canonical path</strong> should have the following format:</p>

<ul>
	<li>The path starts with a single slash <code>&#39;/&#39;</code>.</li>
	<li>Any two directories are separated by a single slash <code>&#39;/&#39;</code>.</li>
	<li>The path does not end with a trailing <code>&#39;/&#39;</code>.</li>
	<li>The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period <code>&#39;.&#39;</code> or double period <code>&#39;..&#39;</code>)</li>
</ul>

<p>Return <em>the simplified <strong>canonical path</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> path = &quot;/home/&quot;
<strong>Output:</strong> &quot;/home&quot;
<strong>Explanation:</strong> Note that there is no trailing slash after the last directory name.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> path = &quot;/../&quot;
<strong>Output:</strong> &quot;/&quot;
<strong>Explanation:</strong> Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> path = &quot;/home//foo/&quot;
<strong>Output:</strong> &quot;/home/foo&quot;
<strong>Explanation:</strong> In the canonical path, multiple consecutive slashes are replaced by a single one.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= path.length &lt;= 3000</code></li>
	<li><code>path</code> consists of English letters, digits, period <code>&#39;.&#39;</code>, slash <code>&#39;/&#39;</code> or <code>&#39;_&#39;</code>.</li>
	<li><code>path</code> is a valid absolute Unix path.</li>
</ul>

## Solutions

Using stack.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **Go**

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

```go
func simplifyPath(path string) string {
    return filepath.Clean(path)
}
```

### **C#**

```cs
using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Solution {
    public string SimplifyPath(string path) {
        var stack = new Stack<string>();
        var sb = new StringBuilder();
        foreach (var ch in ((IEnumerable<char>)path).Concat(Enumerable.Repeat('/', 1)))
        {
            if (ch == '/')
            {
                if (sb.Length > 0)
                {
                    var folder = sb.ToString();
                    sb.Clear();
                    switch (folder)
                    {
                        case ".":
                            break;
                        case "..":
                            if (stack.Any())
                            {
                                stack.Pop();
                            }
                            break;
                        default:
                            stack.Push(folder);
                            break;
                    }
                }
            }
            else
            {
                sb.Append(ch);
            }
        }

        if (stack.Count == 0)
        {
            sb.Append('/');
        }
        foreach (var folder in ((IEnumerable<string>)stack.ToList()).Reverse())
        {
            sb.Append('/');
            sb.Append(folder);
        }
        return sb.ToString();
    }
}
```

### **TypeScript**

```ts
function simplifyPath(path: string): string {
    // 添加辅助斜线
    path += '/';

    const stack = [];
    let str = '';
    for (let i = 1; i < path.length; i++) {
        const c = path[i];
        if (c === '/') {
            if (str !== '' && str !== '.') {
                if (str === '..') {
                    if (stack.length !== 0) {
                        stack.pop();
                    }
                } else {
                    stack.push(str);
                }
            }
            str = '';
        } else {
            str += c;
        }
    }

    return '/' + stack.join('/');
}
```

### **C++**

```cpp
class Solution {
public:
    string simplifyPath(string path) {
        deque<string> stk;
        string res, tmp;
        stringstream ss(path);
        while (getline(ss, tmp, '/')) {
            if (tmp == "" || tmp == ".") continue;
            if (tmp == "..") {
                if (!stk.empty())
                    stk.pop_back();
            } else
                stk.push_back(tmp);
        }
        for (auto str : stk)
            res += "/" + str;
        return res.empty() ? "/" : res;
    }
};
```

<!-- tabs:end -->
