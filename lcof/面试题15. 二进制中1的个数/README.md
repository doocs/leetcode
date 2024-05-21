---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9815.%20%E4%BA%8C%E8%BF%9B%E5%88%B6%E4%B8%AD1%E7%9A%84%E4%B8%AA%E6%95%B0/README.md
---

<!-- problem:start -->

# [面试题 15. 二进制中 1 的个数](https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

## 题目描述

<!-- description:start -->

<p>编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 <a href="http://en.wikipedia.org/wiki/Hamming_weight" target="_blank">汉明重量</a>).）。</p>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li>
	<li>在 Java 中，编译器使用 <a href="https://baike.baidu.com/item/二进制补码/5295284">二进制补码</a> 记法来表示有符号整数。因此，在上面的 <strong>示例 3 </strong>中，输入表示有符号整数 <code>-3</code>。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 11 (控制台输入 00000000000000000000000000001011)
<strong>输出：</strong>3
<strong>解释：</strong>输入的二进制串 <code><strong>00000000000000000000000000001011</strong> 中，共有三位为 '1'。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 128 (控制台输入 00000000000000000000000010000000)
<strong>输出：</strong>1
<strong>解释：</strong>输入的二进制串 <strong>00000000000000000000000010000000</strong> 中，共有一位为 '1'。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4294967293 (控制台输入 11111111111111111111111111111101，部分语言中 n = -3）
<strong>输出：</strong>31
<strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 中，共有 31 位为 '1'。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>输入必须是长度为 <code>32</code> 的 <strong>二进制串</strong> 。</li>
</ul>

<p> </p>

<p>注意：本题与主站 191 题相同：<a href="https://leetcode.cn/problems/number-of-1-bits/">https://leetcode.cn/problems/number-of-1-bits/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

由于 $n \& (n - 1)$ 可以消除 $n$ 的二进制表示中最右边的 1，因此不断执行 $n \& (n - 1)$，直到 $n = 0$，统计执行次数即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        ans = 0
        while n:
            n &= n - 1
            ans += 1
        return ans
```

#### Java

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= (n - 1);
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while (n) {
            n &= (n - 1);
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func hammingWeight(n uint32) (ans int) {
	for n != 0 {
		n &= n - 1
		ans++
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number} n - a positive integer
 * @return {number}
 */
var hammingWeight = function (n) {
    let ans = 0;
    while (n) {
        n &= n - 1;
        ++ans;
    }
    return ans;
};
```

#### Swift

```swift
class Solution {
    func hammingWeight(_ n: UInt32) -> Int {
        var n = n
        var ans = 0
        while n != 0 {
            n &= (n - 1)
            ans += 1
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：位运算（lowbit）

根据位运算的性质，我们知道 $n \& (-n)$ 可以得到 $n$ 的二进制表示中最右边的 $1$，因此不断将 $n$ 减去 $n \& (-n)$，直到 $n = 0$，统计执行次数即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        ans = 0
        while n:
            n -= n & (-n)
            ans += 1
        return ans
```

#### Java

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n -= n & -n;
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while (n) {
            n -= n & -n;
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func hammingWeight(n uint32) (ans int) {
	for n != 0 {
		n -= n & -n
		ans++
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
