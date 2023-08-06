# [2810. 故障键盘](https://leetcode.cn/problems/faulty-keyboard)

[English Version](/solution/2800-2899/2810.Faulty%20Keyboard/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你的笔记本键盘存在故障，每当你在上面输入字符 <code>'i'</code> 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。</p>

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> ，请你用故障键盘依次输入每个字符。</p>

<p>返回最终笔记本屏幕上输出的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "string"
<strong>输出：</strong>"rtsng"
<strong>解释：</strong>
输入第 1 个字符后，屏幕上的文本是："s" 。
输入第 2 个字符后，屏幕上的文本是："st" 。
输入第 3 个字符后，屏幕上的文本是："str" 。
因为第 4 个字符是 'i' ，屏幕上的文本被反转，变成 "rts" 。
输入第 5 个字符后，屏幕上的文本是："rtsn" 。
输入第 6 个字符后，屏幕上的文本是： "rtsng" 。
因此，返回 "rtsng" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "poiinter"
<strong>输出：</strong>"ponter"
<strong>解释：</strong>
输入第 1 个字符后，屏幕上的文本是："p" 。
输入第 2 个字符后，屏幕上的文本是："po" 。
因为第 3 个字符是 'i' ，屏幕上的文本被反转，变成 "op" 。
因为第 4 个字符是 'i' ，屏幕上的文本被反转，变成 "po" 。
输入第 5 个字符后，屏幕上的文本是："pon" 。
输入第 6 个字符后，屏幕上的文本是："pont" 。
输入第 7 个字符后，屏幕上的文本是："ponte" 。
输入第 8 个字符后，屏幕上的文本是："ponter" 。
因此，返回 "ponter" 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>s[0] != 'i'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def finalString(self, s: str) -> str:
        t = []
        for c in s:
            if c == "i":
                t = t[::-1]
            else:
                t.append(c)
        return "".join(t)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String finalString(String s) {
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                t.reverse();
            } else {
                t.append(c);
            }
        }
        return t.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string finalString(string s) {
        string t;
        for (char c : s) {
            if (c == 'i') {
                reverse(t.begin(), t.end());
            } else {
                t.push_back(c);
            }
        }
        return t;
    }
};
```

### **Go**

```go
func finalString(s string) string {
	t := []rune{}
	for _, c := range s {
		if c == 'i' {
			for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
				t[i], t[j] = t[j], t[i]
			}
		} else {
			t = append(t, c)
		}
	}
	return string(t)
}
```

### **TypeScript**

```ts
function finalString(s: string): string {
    const t: string[] = [];
    for (const c of s) {
        if (c === 'i') {
            t.reverse();
        } else {
            t.push(c);
        }
    }
    return t.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
