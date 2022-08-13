# [01.01. Is Unique](https://leetcode.cn/problems/is-unique-lcci)

[中文文档](/lcci/01.01.Is%20Unique/README.md)

## Description

<p>Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong> = &quot;leetcode&quot;

<strong>Output: </strong>false

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>s = &quot;abc&quot;

<strong>Output: </strong>true

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>0 &lt;= len(s) &lt;= 100 </code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
