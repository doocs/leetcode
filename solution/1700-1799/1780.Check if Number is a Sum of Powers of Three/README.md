# [1780. 判断一个数字是否可以表示成三的幂的和](https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three)

[English Version](/solution/1700-1799/1780.Check%20if%20Number%20is%20a%20Sum%20of%20Powers%20of%20Three/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，如果你可以将 <code>n</code> 表示成若干个不同的三的幂之和，请你返回 <code>true</code> ，否则请返回 <code>false</code> 。</p>

<p>对于一个整数 <code>y</code> ，如果存在整数 <code>x</code> 满足 <code>y == 3<sup>x</sup></code> ，我们称这个整数 <code>y</code> 是三的幂。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 12
<b>输出：</b>true
<b>解释：</b>12 = 3<sup>1</sup> + 3<sup>2</sup>
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 91
<b>输出：</b>true
<b>解释：</b>91 = 3<sup>0</sup> + 3<sup>2</sup> + 3<sup>4</sup>
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>n = 21
<b>输出：</b>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学分析**

我们发现，如果一个数 $n$ 可以表示成若干个“不同的”三的幂之和，那么 $n$ 的三进制表示中，每一位上的数字只能是 $0$ 或者 $1$。

因此，我们将 $n$ 转换成三进制，然后判断每一位上的数字是否是 $0$ 或者 $1$。如果不是，那么 $n$ 就不可以表示成若干个三的幂之和，直接返回 `false`；否则 $n$ 可以表示成若干个三的幂之和，返回 `true`。

时间复杂度 $O(\log_3 n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkPowersOfThree(self, n: int) -> bool:
        while n:
            if n % 3 > 1:
                return False
            n //= 3
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 > 1) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkPowersOfThree(int n) {
        while (n) {
            if (n % 3 > 1) return false;
            n /= 3;
        }
        return true;
    }
};
```

### **Go**

```go
func checkPowersOfThree(n int) bool {
	for n > 0 {
		if n%3 > 1 {
			return false
		}
		n /= 3
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
