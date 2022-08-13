# [面试题 01.01. 判定字符是否唯一](https://leetcode.cn/problems/is-unique-lcci)

[English Version](/lcci/01.01.Is%20Unique/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>实现一个算法，确定一个字符串 <code>s</code> 的所有字符是否全都不同。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s = &quot;leetcode&quot;
<strong>输出:</strong> false 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s = &quot;abc&quot;
<strong>输出:</strong> true
</pre>

<p><strong>限制：</strong></p>
<ul>
	<li><code>0 <= len(s) <= 100 </code></li>
	<li>如果你不使用额外的数据结构，会很加分。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

根据示例，可以假定字符串中只包含小写字母（实际验证，也符合假设）。

用 bitmap 标记小写字母是否出现过。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isUnique(self, astr: str) -> bool:
        bitmap = 0
        for c in astr:
            pos = ord(c) - ord('a')
            if (bitmap & (1 << pos)) != 0:
                return False
            bitmap |= 1 << pos
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isUnique(String astr) {
        int bitmap = 0;
        for (char c : astr.toCharArray()) {
            int pos = c - 'a';
            if ((bitmap & (1 << pos)) != 0) {
                return false;
            }
            bitmap |= (1 << pos);
        }
        return true;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} astr
 * @return {boolean}
 */
var isUnique = function (astr) {
    let bitmap = 0;
    for (let i = 0; i < astr.length; ++i) {
        const pos = astr[i].charCodeAt() - 'a'.charCodeAt();
        if ((bitmap & (1 << pos)) != 0) {
            return false;
        }
        bitmap |= 1 << pos;
    }
    return true;
};
```

### **Go**

```go
func isUnique(astr string) bool {
	bitmap := 0
	for _, r := range astr {
		pos := r - 'a'
		if (bitmap & (1 << pos)) != 0 {
			return false
		}
		bitmap |= (1 << pos)
	}
	return true
}
```

### **C++**

```cpp
class Solution {
public:
    bool isUnique(string astr) {
        int bitmap = 0;
        for (char c : astr) {
            int pos = c - 'a';
            if ((bitmap & (1 << pos)) != 0) {
                return false;
            }
            bitmap |= (1 << pos);
        }
        return true;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
