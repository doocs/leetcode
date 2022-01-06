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
<strong>Explanation: </strong>In the canonical path, multiple consecutive slashes are replaced by a single one.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> path = &quot;/a/./b/../../c/&quot;
<strong>Output:</strong> &quot;/c&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= path.length &lt;= 3000</code></li>
	<li><code>path</code> consists of English letters, digits, period <code>&#39;.&#39;</code>, slash <code>&#39;/&#39;</code> or <code>&#39;_&#39;</code>.</li>
	<li><code>path</code> is a valid absolute Unix path.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public String simplifyPath(String path) {
        List<String> dirs = new ArrayList<>();
        int dirStart = 0, len = path.length();
        while (dirStart < len) {
            while (dirStart < len && path.charAt(dirStart) == '/') dirStart++;
            int dirEnd = dirStart;
            while (dirEnd < len && path.charAt(dirEnd) != '/') dirEnd++;
            String dir = path.substring(dirStart, dirEnd);
            if (!".".equals(dir)) {
                if ("..".equals(dir)) {
                    if (! dirs.isEmpty()) dirs.remove(dirs.size() - 1);
                } else if (dir.length() > 0) {
                    dirs.add(dir);
                }
            }
            dirStart = ++dirEnd;
        }
        StringBuilder sb = new StringBuilder("/");
        for (int i = 0; i < dirs.size(); i++) {
            if (i == dirs.size() - 1) sb.append(dirs.get(i));
            else sb.append(dirs.get(i)).append("/");
        }
        return sb.toString();
    }
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

### **...**

```

```

<!-- tabs:end -->
