---
comments: true
difficulty: 困难
tags:
    - 数学
---

<!-- problem:start -->

# [660. 移除 9 🔒](https://leetcode.cn/problems/remove-9)

[English Version](/solution/0600-0699/0660.Remove%209/README_EN.md)

## 题目描述

<!-- description:start -->

<p>从 <code>1</code> 开始，移除包含数字 <code>9</code> 的所有整数，例如 <code>9</code>，<code>19</code>，<code>29</code>，……</p>

<p>这样就获得了一个新的整数数列：<code>1</code>，<code>2</code>，<code>3</code>，<code>4</code>，<code>5</code>，<code>6</code>，<code>7</code>，<code>8</code>，<code>10</code>，<code>11</code>，……</p>

<p>给你一个整数 <code>n</code>，请你返回新数列中第 <code>n</code> 个数字是多少（下标从 <strong>1</strong> 开始）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 9
<strong>输出：</strong>10
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>11
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8 * 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 去掉所有含数字 $9$ 的自然数后，求第 $n$ 个剩余数。直接枚举到第 $n$ 个合法数，$n$ 可达 $9$ 位数时过慢。
>
> 不含 $9$ 等价于用 $0..8$ 这九个符号计数，即把 $n$ 写成九进制再按位解释。当前题解页尚未填入实现代码。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
