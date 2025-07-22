---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0258.Add%20Digits/README.md
tags:
    - 数学
    - 数论
    - 模拟
---

<!-- problem:start -->

# [258. 各位相加](https://leetcode.cn/problems/add-digits)

[English Version](/solution/0200-0299/0258.Add%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个非负整数 <code>num</code>，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> num =<strong> </strong><code>38</code>
<strong>输出:</strong> 2 
<strong>解释: </strong>各位相加的过程为<strong>：
</strong>38 --&gt; 3 + 8 --&gt; 11
11 --&gt; 1 + 1 --&gt; 2
由于&nbsp;<code>2</code> 是一位数，所以返回 2。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> num =<strong> </strong>0
<strong>输出:</strong> 0</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以不使用循环或者递归，在 <code>O(1)</code> 时间复杂度内解决这个问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def addDigits(self, num: int) -> int:
        return 0 if num == 0 else (num - 1) % 9 + 1
```

#### Java

```java
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
};
```

#### Go

```go
func addDigits(num int) int {
	if num == 0 {
		return 0
	}
	return (num-1)%9 + 1
}
```

#### Rust

```rust
impl Solution {
    pub fn add_digits(mut num: i32) -> i32 {
        ((num - 1) % 9) + 1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
