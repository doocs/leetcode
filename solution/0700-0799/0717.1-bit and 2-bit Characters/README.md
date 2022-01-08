# [717. 1 比特与 2 比特字符](https://leetcode-cn.com/problems/1-bit-and-2-bit-characters)

[English Version](/solution/0700-0799/0717.1-bit%20and%202-bit%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两种特殊字符。第一种字符可以用一比特<code>0</code>来表示。第二种字符可以用两比特(<code>10</code>&nbsp;或&nbsp;<code>11</code>)来表示。</p>

<p>现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> 
bits = [1, 0, 0]
<strong>输出:</strong> True
<strong>解释:</strong> 
唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> 
bits = [1, 1, 1, 0]
<strong>输出:</strong> False
<strong>解释:</strong> 
唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
</pre>

<p><strong>注意:</strong></p>

<ul>
	<li><code>1 &lt;= len(bits) &lt;= 1000</code>.</li>
	<li><code>bits[i]</code> 总是<code>0</code> 或&nbsp;<code>1</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isOneBitCharacter(self, bits: List[int]) -> bool:
        i, n = 0, len(bits)
        while i < n - 1:
            i += bits[i] + 1
        return i == n - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0, n = bits.length;
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isOneBitCharacter(vector<int>& bits) {
        int i = 0, n = bits.size();
        while (i < n - 1) i += bits[i] + 1;
        return i == n - 1;
    }
};
```

### **Go**

```go
func isOneBitCharacter(bits []int) bool {
	i, n := 0, len(bits)
	for i < n-1 {
		i += bits[i] + 1
	}
	return i == n-1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} bits
 * @return {boolean}
 */
var isOneBitCharacter = function (bits) {
    let i = 0;
    const n = bits.length;
    while (i < n - 1) {
        i += bits[i] + 1;
    }
    return i == n - 1;
};
```

### **...**

```

```

<!-- tabs:end -->
