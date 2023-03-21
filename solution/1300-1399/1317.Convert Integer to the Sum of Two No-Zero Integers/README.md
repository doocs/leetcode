# [1317. 将整数转换为两个无零整数的和](https://leetcode.cn/problems/convert-integer-to-the-sum-of-two-no-zero-integers)

[English Version](/solution/1300-1399/1317.Convert%20Integer%20to%20the%20Sum%20of%20Two%20No-Zero%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>「无零整数」是十进制表示中 <strong>不含任何 0</strong>&nbsp;的正整数。</p>

<p>给你一个整数&nbsp;<code>n</code>，请你返回一个 <strong>由两个整数组成的列表</strong> <code>[A, B]</code>，满足：</p>

<ul>
	<li><code>A</code> 和 <code>B</code>&nbsp;都是无零整数</li>
	<li><code>A + B = n</code></li>
</ul>

<p>题目数据保证至少有一个有效的解决方案。</p>

<p>如果存在多个有效解决方案，你可以返回其中任意一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>[1,1]
<strong>解释：</strong>A = 1, B = 1. A + B = n 并且 A 和 B 的十进制表示形式都不包含任何 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 11
<strong>输出：</strong>[2,9]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 10000
<strong>输出：</strong>[1,9999]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 69
<strong>输出：</strong>[1,68]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>n = 1010
<strong>输出：</strong>[11,999]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接枚举**

从 $1$ 开始枚举 $a$，判断 $a$ 和 $n - a$ 是否满足条件，如果满足则返回。

时间复杂度 $O(n\times \log n)$，空间复杂度 $O(1)$。其中 $n$ 为题目给定的整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getNoZeroIntegers(self, n: int) -> List[int]:
        for a in range(1, n):
            b = n - a
            if "0" not in str(a) + str(b):
                return [a, b]
```

```python
class Solution:
    def getNoZeroIntegers(self, n: int) -> List[int]:
        def f(x):
            while x:
                if x % 10 == 0:
                    return False
                x //= 10
            return True

        for a in range(1, n):
            b = n - a
            if f(a) and f(b):
                return [a, b]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; ; ++a) {
            int b = n - a;
            if (!(a + "" + b).contains("0")) {
                return new int[] {a, b};
            }
        }
    }
}
```

```java
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1;; ++a) {
            int b = n - a;
            if (f(a) && f(b)) {
                return new int[] {a, b};
            }
        }
    }

    private boolean f(int x) {
        for (; x > 0; x /= 10) {
            if (x % 10 == 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getNoZeroIntegers(int n) {
        for (int a = 1; ; ++a) {
            int b = n - a;
            if ((to_string(a) + to_string(b)).find('0') == -1) {
                return {a, b};
            }
        }
    }
};
```

```cpp
class Solution {
public:
    vector<int> getNoZeroIntegers(int n) {
        auto f = [](int x) {
            for (; x; x /= 10) {
                if (x % 10 == 0) {
                    return false;
                }
            }
            return true;
        };
        for (int a = 1; ; ++a) {
            int b = n - a;
            if (f(a) && f(b)) {
                return {a, b};
            }
        }
    }
};
```

### **Go**

```go
func getNoZeroIntegers(n int) []int {
	for a := 1; ; a++ {
		b := n - a
		if !strings.Contains(strconv.Itoa(a)+strconv.Itoa(b), "0") {
			return []int{a, b}
		}
	}
}
```

```go
func getNoZeroIntegers(n int) []int {
	f := func(x int) bool {
		for ; x > 0; x /= 10 {
			if x%10 == 0 {
				return false
			}
		}
		return true
	}
	for a := 1; ; a++ {
		b := n - a
		if f(a) && f(b) {
			return []int{a, b}
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
