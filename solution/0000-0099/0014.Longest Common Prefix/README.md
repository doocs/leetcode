# [14. 最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix)

[English Version](/solution/0000-0099/0014.Longest%20Common%20Prefix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个函数来查找字符串数组中的最长公共前缀。</p>

<p>如果不存在公共前缀，返回空字符串 <code>""</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["flower","flow","flight"]
<strong>输出：</strong>"fl"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["dog","racecar","car"]
<strong>输出：</strong>""
<strong>解释：</strong>输入不存在公共前缀。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= strs.length <= 200</code></li>
	<li><code>0 <= strs[i].length <= 200</code></li>
	<li><code>strs[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        n = len(strs)
        if n == 0:
            return ''
        for i in range(len(strs[0])):
            for j in range(1, n):
                if len(strs[j]) <= i or strs[j][i] != strs[0][i]:
                    return strs[0][:i]
        return strs[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n;
        if ((n = strs.length) == 0) return "";
        for (int i = 0; i < strs[0].length(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        int n;
        if ((n = strs.size()) == 0) return "";
        for (int i = 0; i < strs[0].size(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].size() <= i || strs[j][i] != strs[0][i]) {
                    return strs[0].substr(0, i);
                }
            }
        }
        return strs[0];
    }
};
```

### **Go**

```go
func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}

	var b strings.Builder
	m, n := len(strs[0]), len(strs)

LOOP:
	for i := 0; i < m; i++ {
		for j := 1; j < n; j++ {
			if i >= len(strs[j]) || strs[0][i] != strs[j][i] {
				break LOOP
			}
		}
		b.WriteByte(strs[0][i])
	}

	return b.String()
}
```

### **...**

```

```

<!-- tabs:end -->
