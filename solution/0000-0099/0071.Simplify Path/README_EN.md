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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> path = &quot;/home/&quot;
<strong>Output:</strong> &quot;/home&quot;
<strong>Explanation:</strong> Note that there is no trailing slash after the last directory name.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> path = &quot;/../&quot;
<strong>Output:</strong> &quot;/&quot;
<strong>Explanation:</strong> Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
</pre>

<p><strong class="example">Example 3:</strong></p>

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

**Solution 1: Stack**

We first split the path into a number of substrings split by `'/'`. Then, we traverse each substring and perform the following operations based on the content of the substring:

-   If the substring is empty or `'.'`, no operation is performed because `'.'` represents the current directory.
-   If the substring is `'..'`, the top element of the stack is popped, because `'..'` represents the parent directory.
-   If the substring is other strings, the substring is pushed into the stack, because the substring represents the subdirectory of the current directory.

Finally, we concatenate all the elements in the stack from the bottom to the top of the stack to form a string, which is the simplified canonical path.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the path.

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

### **C++**

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

### **Rust**

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn simplify_path(path: String) -> String {
        let mut s: Vec<&str> = Vec::new();

        // Split the path
        let p_vec = path.split("/").collect::<Vec<&str>>();

        // Traverse the path vector
        for p in p_vec {
            match p {
                // Do nothing for "" or "."
                "" | "." => {
                    continue;
                }
                ".." => {
                    if !s.is_empty() {
                        s.pop();
                    }
                }
                _ => s.push(p),
            }
        }

        "/".to_string() + &s.join("/")
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

### **TypeScript**

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

### **C#**

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

### **...**

```

```

<!-- tabs:end -->
