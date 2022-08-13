# [925. 长按键入](https://leetcode.cn/problems/long-pressed-name)

[English Version](/solution/0900-0999/0925.Long%20Pressed%20Name/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的朋友正在使用键盘输入他的名字&nbsp;<code>name</code>。偶尔，在键入字符&nbsp;<code>c</code>&nbsp;时，按键可能会被<em>长按</em>，而字符可能被输入 1 次或多次。</p>

<p>你将会检查键盘输入的字符&nbsp;<code>typed</code>。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回&nbsp;<code>True</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>name = "alex", typed = "aaleex"
<strong>输出：</strong>true
<strong>解释：</strong>'alex' 中的 'a' 和 'e' 被长按。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>name = "saeed", typed = "ssaaedd"
<strong>输出：</strong>false
<strong>解释：</strong>'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= name.length, typed.length &lt;= 1000</code></li>
	<li><code>name</code> 和&nbsp;<code>typed</code>&nbsp;的字符都是小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        m, n = len(name), len(typed)
        i = j = 0
        while i < m and j < n:
            if name[i] != typed[j]:
                return False
            cnt1 = cnt2 = 0
            c = name[i]
            while i + 1 < m and name[i + 1] == c:
                i += 1
                cnt1 += 1
            while j + 1 < n and typed[j + 1] == c:
                j += 1
                cnt2 += 1
            if cnt1 > cnt2:
                return False
            i, j = i + 1, j + 1
        return i == m and j == n
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        for (; i < m && j < n; ++i, ++j) {
            if (name.charAt(i) != typed.charAt(j)) {
                return false;
            }
            int cnt1 = 0, cnt2 = 0;
            char c = name.charAt(i);
            while (i + 1 < m && name.charAt(i + 1) == c) {
                ++i;
                ++cnt1;
            }
            while (j + 1 < n && typed.charAt(j + 1) == c) {
                ++j;
                ++cnt2;
            }
            if (cnt1 > cnt2) {
                return false;
            }
        }
        return i == m && j == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isLongPressedName(string name, string typed) {
        int m = name.size(), n = typed.size();
        int i = 0, j = 0;
        for (; i < m && j < n; ++i, ++j) {
            if (name[i] != typed[j]) return false;
            int cnt1 = 0, cnt2 = 0;
            char c = name[i];
            while (i + 1 < m && name[i + 1] == c) {
                ++i;
                ++cnt1;
            }
            while (j + 1 < n && typed[j + 1] == c) {
                ++j;
                ++cnt2;
            }
            if (cnt1 > cnt2) return false;
        }
        return i == m && j == n;
    }
};
```

### **Go**

```go
func isLongPressedName(name string, typed string) bool {
	m, n := len(name), len(typed)
	i, j := 0, 0
	for ; i < m && j < n; i, j = i+1, j+1 {
		if name[i] != typed[j] {
			return false
		}
		cnt1, cnt2 := 0, 0
		c := name[i]
		for i+1 < m && name[i+1] == c {
			i++
			cnt1++
		}
		for j+1 < n && typed[j+1] == c {
			j++
			cnt2++
		}
		if cnt1 > cnt2 {
			return false
		}
	}
	return i == m && j == n
}
```

### **...**

```

```

<!-- tabs:end -->
