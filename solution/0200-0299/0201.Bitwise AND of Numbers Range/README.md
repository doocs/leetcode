# [201. 数字范围按位与](https://leetcode.cn/problems/bitwise-and-of-numbers-range)

[English Version](/solution/0200-0299/0201.Bitwise%20AND%20of%20Numbers%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>left</code> 和 <code>right</code> ，表示区间 <code>[left, right]</code> ，返回此区间内所有数字 <strong>按位与</strong> 的结果（包含 <code>left</code> 、<code>right</code> 端点）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>left = 5, right = 7
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>left = 0, right = 0
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>left = 1, right = 2147483647
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= left <= right <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

题目可以转换为求数字的公共二进制前缀。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rangeBitwiseAnd(self, left: int, right: int) -> int:
        while left < right:
            right &= right - 1
        return right
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int rangeBitwiseAnd(int left, int right) {
        while (left < right) right &= (right - 1);
        return right;
    }
};
```

### **Go**

```go
func rangeBitwiseAnd(left int, right int) int {
	for left < right {
		right &= (right - 1)
	}
	return right
}
```

### **C#**

```cs
public class Solution {
    public int RangeBitwiseAnd(int left, int right) {
        while (left < right)
        {
            right &= (right - 1);
        }
        return right;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
