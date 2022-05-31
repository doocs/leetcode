# [231. 2 的幂](https://leetcode.cn/problems/power-of-two)

[English Version](/solution/0200-0299/0231.Power%20of%20Two/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code>，请你判断该整数是否是 2 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>如果存在一个整数 <code>x</code> 使得 <code>n == 2<sup>x</sup></code> ，则认为 <code>n</code> 是 2 的幂次方。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>true
<strong>解释：</strong>2<sup>0</sup> = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 16
<strong>输出：</strong>true
<strong>解释：</strong>2<sup>4</sup> = 16
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>true
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能够不使用循环/递归解决此问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

$\texttt{n\&(n-1)}$ 可将最后一个二进制形式的 $n$ 的最后一位 $1$ 移除，若移除后为 $0$，说明 $n$ 是 $2$ 的幂。

**方法二：lowbit**

$\texttt{n\&(-n)}$ 可以得到 $n$ 的最后一位 $1$ 表示的十进制数，若与 $n$ 相等，说明 $n$ 是 $2$ 的幂。

注意：要满足 $n$ 是 $2$ 的幂次方，需要保证 $n$ 大于 $0$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0
```

lowbit:

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and n == n & (-n)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

lowbit:

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
};
```

lowbit:

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && (n & (n - 1)) == 0;
};
```

lowbit:

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && n == (n & -n);
};
```

### **Go**

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && (n&(n-1)) == 0
}
```

lowbit:

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && n == (n&(-n))
}
```

### **TypeScript**

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && (n & (n - 1)) == 0;
}
```

lowbit:

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && (n & (n - 1)) == 0;
}
```

### **...**

```

```

<!-- tabs:end -->
