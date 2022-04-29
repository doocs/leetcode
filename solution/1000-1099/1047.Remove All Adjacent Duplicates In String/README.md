# [1047. 删除字符串中的所有相邻重复项](https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string)

[English Version](/solution/1000-1099/1047.Remove%20All%20Adjacent%20Duplicates%20In%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出由小写字母组成的字符串&nbsp;<code>S</code>，<strong>重复项删除操作</strong>会选择两个相邻且相同的字母，并删除它们。</p>

<p>在 S 上反复执行重复项删除操作，直到无法继续删除。</p>

<p>在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>&quot;abbaca&quot;
<strong>输出：</strong>&quot;ca&quot;
<strong>解释：</strong>
例如，在 &quot;abbaca&quot; 中，我们可以删除 &quot;bb&quot; 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 &quot;aaca&quot;，其中又只有 &quot;aa&quot; 可以执行重复项删除操作，所以最后的字符串为 &quot;ca&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 20000</code></li>
	<li><code>S</code> 仅由小写英文字母组成。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

栈实现。

遍历字符串 S 中的每个字符 s，若栈为空或者栈顶值不等于字符 s，s 入栈，否则栈顶元素出栈。

最后返回栈中元素所组成的字符串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeDuplicates(self, S: str) -> str:
        res = []
        for s in S:
            if not res or res[-1] != s:
                res.append(s)
            else:
                res.pop()
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int top = -1;
        for (int i = 0, n = S.length(); i < n; ++i) {
            char s = S.charAt(i);
            if (top == -1 || sb.charAt(top) != s) {
                sb.append(s);
                ++top;
            } else {
                sb.deleteCharAt(top);
                --top;
            }
        }
        return sb.toString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
