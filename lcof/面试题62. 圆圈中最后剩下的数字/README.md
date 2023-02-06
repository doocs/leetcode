# [面试题 62. 圆圈中最后剩下的数字](https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。</p>

<p>例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> n = 5, m = 3
<strong>输出: </strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> n = 10, m = 17
<strong>输出: </strong>2
</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 <= n <= 10^5</code></li>
	<li><code>1 <= m <= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学 + 递归（迭代）**

我们不妨设 $f(n, m)$ 表示从 $n$ 个数中每次删除第 $m$ 个，最后剩下的是第几个数字。

我们第一次删除了第 $m$ 个数字，剩下 $n-1$ 个数，那么 $x=f(n - 1, m)$ 就表示从剩下的 $n-1$ 个数中，每次删除第 $m$ 个，最后剩下的是第几个数字。

我们求得 $x$ 之后，便可以知道 $f(n, m)$ 应该是从 $m \% n$ 开始数的第 $x$ 个元素，即 $f(n, m) = (m \% n + x) \% n$。

当 $n$ 为 $1$ 时，最后留下的数字序号一定为 $0$。

递归求解即可，也可以改成迭代。

时间复杂度 $O(n)$，递归的空间复杂度 $O(n)$，迭代的空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lastRemaining(self, n: int, m: int) -> int:
        def f(n, m):
            if n == 1:
                return 0
            x = f(n - 1, m)
            return (m + x) % n

        return f(n, m)
```

```python
class Solution:
    def lastRemaining(self, n: int, m: int) -> int:
        f = 0
        for i in range(2, n + 1):
            f = (f + m) % i
        return f
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lastRemaining(int n, int m) {
        return f(n, m);
    }

    private int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }
}
```

```java
class Solution {
    public int lastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i <= n; ++i) {
            f = (f + m) % i;
        }
        return f;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int lastRemaining(int n, int m) {
        return f(n, m);
    }

    int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }
};
```

```cpp
class Solution {
public:
    int lastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i <= n; ++i) {
            f = (f + m) % i;
        }
        return f;
    }
};
```

### **Go**

```go
func lastRemaining(n int, m int) int {
	var f func(n, m int) int
	f = func(n, m int) int {
		if n == 1 {
			return 0
		}
		x := f(n-1, m)
		return (m + x) % n
	}
	return f(n, m)
}
```

```go
func lastRemaining(n int, m int) int {
	f := 0
	for i := 2; i <= n; i++ {
		f = (f + m) % i
	}
	return f
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @param {number} m
 * @return {number}
 */
var lastRemaining = function (n, m) {
    let f = 0;
    for (let i = 2; i <= n; ++i) {
        f = (f + m) % i;
    }
    return f;
};
```

### **C#**

```cs
public class Solution {
    public int LastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i < n + 1; i++) {
            f = (f + m) % i;
        }
        return f;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
