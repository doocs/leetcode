# [258. 各位相加](https://leetcode.cn/problems/add-digits)

[English Version](/solution/0200-0299/0258.Add%20Digits/README_EN.md)

<!-- tags:数学,数论,模拟 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def addDigits(self, num: int) -> int:
        return 0 if num == 0 else (num - 1) % 9 + 1
```

```java
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
```

```cpp
class Solution {
public:
    int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
};
```

```go
func addDigits(num int) int {
	if num == 0 {
		return 0
	}
	return (num-1)%9 + 1
}
```

```rust
impl Solution {
    pub fn add_digits(num: i32) -> i32 {
        if num < 10 {
            return num;
        }
        Self::add_digits(
            num
                .to_string()
                .chars()
                .map(|c| c.to_string().parse::<i32>().unwrap())
                .sum::<i32>()
        )
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```rust
impl Solution {
    pub fn add_digits(mut num: i32) -> i32 {
        ((num - 1) % 9) + 1
    }
}
```

<!-- tabs:end -->

<!-- end -->
