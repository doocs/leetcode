# [1446. 连续字符](https://leetcode.cn/problems/consecutive-characters)

[English Version](/solution/1400-1499/1446.Consecutive%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，字符串的<strong>「能量」</strong>定义为：只包含一种字符的最长非空子字符串的长度。</p>

<p>请你返回字符串 <code>s</code> 的 <strong>能量</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode"
<strong>输出：</strong>2
<strong>解释：</strong>子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abbcccddddeeeeedcba"
<strong>输出：</strong>5
<strong>解释：</strong>子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxPower(self, s: str) -> int:
        ans = t = 0
        for i, c in enumerate(s):
            if i == 0 or c == s[i - 1]:
                t += 1
            else:
                t = 1
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxPower(String s) {
        int ans = 0, t = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i == 0 || s.charAt(i) == s.charAt(i - 1)) {
                ++t;
            } else {
                t = 1;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxPower(string s) {
        int ans = 0, t = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (i == 0 || s[i] == s[i - 1])
                ++t;
            else
                t = 1;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxPower(s string) int {
	ans, t := 0, 0
	for i := range s {
		if i == 0 || s[i] == s[i-1] {
			t++
		} else {
			t = 1
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
