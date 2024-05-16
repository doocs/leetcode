---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0231.Power%20of%20Two/README.md
tags:
    - 位运算
    - 递归
    - 数学
---

<!-- problem:start -->

# [231. 2 的幂](https://leetcode.cn/problems/power-of-two)

[English Version](/solution/0200-0299/0231.Power%20of%20Two/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，请你判断该整数是否是 2 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>如果存在一个整数 <code>x</code> 使得&nbsp;<code>n == 2<sup>x</sup></code> ，则认为 <code>n</code> 是 2 的幂次方。</p>

<p>&nbsp;</p>

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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能够不使用循环/递归解决此问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

$\texttt{n\&(n-1)}$ 可将最后一个二进制形式的 $n$ 的最后一位 $1$ 移除，若移除后为 $0$，说明 $n$ 是 $2$ 的幂。

<!-- tabs:start -->

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0
```

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
};
```

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && (n&(n-1)) == 0
}
```

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && (n & (n - 1)) === 0;
}
```

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && (n & (n - 1)) == 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：lowbit

$\texttt{n\&(-n)}$ 可以得到 $n$ 的最后一位 $1$ 表示的十进制数，若与 $n$ 相等，说明 $n$ 是 $2$ 的幂。

注意：要满足 $n$ 是 $2$ 的幂次方，需要保证 $n$ 大于 $0$。

<!-- tabs:start -->

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and n == n & (-n)
```

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
}
```

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
};
```

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && n == (n&(-n))
}
```

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && (n & (n - 1)) === 0;
}
```

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && n == (n & -n);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
