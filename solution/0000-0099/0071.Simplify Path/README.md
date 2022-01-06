# [71. 简化路径](https://leetcode-cn.com/problems/simplify-path)

[English Version](/solution/0000-0099/0071.Simplify%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>path</code> ，表示指向某一文件或目录的 Unix 风格 <strong>绝对路径 </strong>（以 <code>'/'</code> 开头），请你将其转化为更加简洁的规范路径。</p>

<p class="MachineTrans-lang-zh-CN">在 Unix 风格的文件系统中，一个点（<code>.</code>）表示当前目录本身；此外，两个点 （<code>..</code>） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，<code>'//'</code>）都被视为单个斜杠 <code>'/'</code> 。 对于此问题，任何其他格式的点（例如，<code>'...'</code>）均被视为文件/目录名称。</p>

<p>请注意，返回的 <strong>规范路径</strong> 必须遵循下述格式：</p>

<ul>
	<li>始终以斜杠 <code>'/'</code> 开头。</li>
	<li>两个目录名之间必须只有一个斜杠 <code>'/'</code> 。</li>
	<li>最后一个目录名（如果存在）<strong>不能 </strong>以 <code>'/'</code> 结尾。</li>
	<li>此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 <code>'.'</code> 或 <code>'..'</code>）。</li>
</ul>

<p>返回简化后得到的 <strong>规范路径</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>path = "/home/"
<strong>输出：</strong>"/home"
<strong>解释：</strong>注意，最后一个目录名后面没有斜杠。 </pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>path = "/../"
<strong>输出：</strong>"/"
<strong>解释：</strong>从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>path = "/home//foo/"
<strong>输出：</strong>"/home/foo"
<strong>解释：</strong>在规范路径中，多个连续斜杠需要用一个斜杠替换。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>path = "/a/./b/../../c/"
<strong>输出：</strong>"/c"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= path.length <= 3000</code></li>
	<li><code>path</code> 由英文字母，数字，<code>'.'</code>，<code>'/'</code> 或 <code>'_'</code> 组成。</li>
	<li><code>path</code> 是一个有效的 Unix 风格绝对路径。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
