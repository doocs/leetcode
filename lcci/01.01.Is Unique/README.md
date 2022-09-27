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

**方法一：位运算**

根据示例，可以假定字符串中只包含小写字母（实际验证，也符合假设）。

因此，我们可以使用一个 $32$ 位整数 `mask` 的每一位来表示字符串中的每一个字符是否出现过。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isUnique(self, astr: str) -> bool:
        mask = 0
        for c in astr:
            i = ord(c) - ord('a')
            if (mask >> i) & 1:
                return False
            mask |= 1 << i
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isUnique(String astr) {
        int mask = 0;
        for (char c : astr.toCharArray()) {
            int i = c - 'a';
            if (((mask >> i) & 1) == 1) {
                return false;
            }
            mask |= 1 << i;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isUnique(string astr) {
        int mask = 0;
        for (char c : astr) {
            int i = c - 'a';
            if (mask >> i & 1) {
                return false;
            }
            mask |= 1 << i;
        }
        return true;
    }
};
```

### **Go**

```go
func isUnique(astr string) bool {
	mask := 0
	for _, c := range astr {
		i := c - 'a'
		if mask>>i&1 == 1 {
			return false
		}
		mask |= 1 << i
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {string} astr
 * @return {boolean}
 */
var isUnique = function (astr) {
    let mask = 0;
    for (const c of astr) {
        const i = c.charCodeAt() - 'a'.charCodeAt();
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
};
```

### **TypeScript**

```ts
function isUnique(astr: string): boolean {
    let mask = 0;
    for (let j = 0; j < astr.length; ++j) {
        const i = astr.charCodeAt(j) - 'a'.charCodeAt(0);
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
