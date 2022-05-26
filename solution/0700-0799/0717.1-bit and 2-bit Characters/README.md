# [717. 1 比特与 2 比特字符](https://leetcode.cn/problems/1-bit-and-2-bit-characters)

[English Version](/solution/0700-0799/0717.1-bit%20and%202-bit%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两种特殊字符：</p>

<ul>
	<li>第一种字符可以用一比特&nbsp;<code>0</code> 表示</li>
	<li>第二种字符可以用两比特（<code>10</code>&nbsp;或&nbsp;<code>11</code>）表示</li>
</ul>

<p>给你一个以 <code>0</code> 结尾的二进制数组&nbsp;<code>bits</code>&nbsp;，如果最后一个字符必须是一个一比特字符，则返回 <code>true</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> bits = [1, 0, 0]
<strong>输出:</strong> true
<strong>解释:</strong> 唯一的解码方式是将其解析为一个两比特字符和一个一比特字符。
所以最后一个字符是一比特字符。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入：</strong>bits = [1,1,1,0]
<strong>输出：</strong>false
<strong>解释：</strong>唯一的解码方式是将其解析为两比特字符和两比特字符。
所以最后一个字符不是一比特字符。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= bits.length &lt;= 1000</code></li>
	<li><code>bits[i]</code> 为 <code>0</code> 或 <code>1</code></li>
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
