# [2645. 构造有效字符串的最少插入数](https://leetcode.cn/problems/minimum-additions-to-make-valid-string)

[English Version](/solution/2600-2699/2645.Minimum%20Additions%20to%20Make%20Valid%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code> ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 <code>word</code> <strong>有效</strong> 需要插入的最少字母数。</p>

<p>如果字符串可以由 "abc" 串联多次得到，则认为该字符串 <strong>有效</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>word = "b"
<strong>输出：</strong>2
<strong>解释：</strong>在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "<strong>a</strong>b<strong>c</strong>" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>word = "aaa"
<strong>输出：</strong>6
<strong>解释：</strong>在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "a<strong>bc</strong>a<strong>bc</strong>a<strong>bc</strong>" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>word = "abc"
<strong>输出：</strong>0
<strong>解释：</strong>word 已经是有效字符串，不需要进行修改。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 50</code></li>
	<li><code>word</code> 仅由字母 "a"、"b" 和 "c" 组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def addMinimum(self, word: str) -> int:
        s = 'abc'
        ans, n = 0, len(word)
        i = j = 0
        while j < n:
            if word[j] != s[i]:
                ans += 1
            else:
                j += 1
            i = (i + 1) % 3
        if word[-1] != 'c':
            ans += 1 if word[-1] == 'b' else 2
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int addMinimum(String word) {
        String s = "abc";
        int ans = 0, n = word.length();
        for (int i = 0, j = 0; j < n; i = (i + 1) % 3) {
            if (word.charAt(j) != s.charAt(i)) {
                ++ans;
            } else {
                ++j;
            }
        }
        if (word.charAt(n - 1) != 'c') {
            ans += word.charAt(n - 1) == 'b' ? 1 : 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int addMinimum(string word) {
        string s = "abc";
        int ans = 0, n = word.size();
        for (int i = 0, j = 0; j < n; i = (i + 1) % 3) {
            if (word[j] != s[i]) {
                ++ans;
            } else {
                ++j;
            }
        }
        if (word[n - 1] != 'c') {
            ans += word[n - 1] == 'b' ? 1 : 2;
        }
        return ans;
    }
};
```

### **Go**

```go
func addMinimum(word string) (ans int) {
	s := "abc"
	n := len(word)
	for i, j := 0, 0; j < n; i = (i + 1) % 3 {
		if word[j] != s[i] {
			ans++
		} else {
			j++
		}
	}
	if word[n-1] == 'b' {
		ans++
	} else if word[n-1] == 'a' {
		ans += 2
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
