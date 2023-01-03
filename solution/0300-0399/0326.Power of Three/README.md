# [326. 3 的幂](https://leetcode.cn/problems/power-of-three)

[English Version](/solution/0300-0399/0326.Power%20of%20Three/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数，写一个函数来判断它是否是 3&nbsp;的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>整数 <code>n</code> 是 3 的幂次方需满足：存在整数 <code>x</code> 使得 <code>n == 3<sup>x</sup></code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 27
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 9
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 45
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能不使用循环或者递归来完成本题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：试除法**

如果 $n \gt 2$，我们可以不断地将 $n$ 除以 $3$，如果不能整除，说明 $n$ 不是 $3$ 的幂，否则继续除以 $3$，直到 $n$ 小于等于 $2$。如果 $n$ 等于 $1$，说明 $n$ 是 $3$ 的幂，否则不是 $3$ 的幂。

时间复杂度 $O(\log_3n)$，空间复杂度 $O(1)$。

**方法二：数学**

如果 $n$ 是 $3$ 的幂，那么 $n$ 最大是 $3^{19} = 1162261467$，因此我们只需要判断 $n$ 是否是 $3^{19}$ 的约数即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPowerOfThree(self, n: int) -> bool:
        while n > 2:
            if n % 3:
                return False
            n //= 3
        return n == 1
```

```python
class Solution:
    def isPowerOfThree(self, n: int) -> bool:
        return n > 0 and 1162261467 % n == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        while (n > 2) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return n == 1;
    }
}
```

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPowerOfThree(int n) {
        while (n > 2) {
            if (n % 3) {
                return false;
            }
            n /= 3;
        }
        return n == 1;
    }
};
```

```cpp
class Solution {
public:
    bool isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
};
```

### **Go**

```go
func isPowerOfThree(n int) bool {
	for n > 2 {
		if n%3 != 0 {
			return false
		}
		n /= 3
	}
	return n == 1
}
```

```go
func isPowerOfThree(n int) bool {
	return n > 0 && 1162261467%n == 0
}
```

### **TypeScript**

```ts
function isPowerOfThree(n: number): boolean {
    return n > 0 && 1162261467 % n == 0;
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfThree = function (n) {
    return n > 0 && 1162261467 % n == 0;
};
```

### **...**

```

```

<!-- tabs:end -->
