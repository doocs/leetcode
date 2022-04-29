# [1523. 在区间范围内统计奇数数目](https://leetcode.cn/problems/count-odd-numbers-in-an-interval-range)

[English Version](/solution/1500-1599/1523.Count%20Odd%20Numbers%20in%20an%20Interval%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个非负整数&nbsp;<code>low</code> 和&nbsp;<code>high</code>&nbsp;。请你返回<em>&nbsp;</em><code>low</code><em> </em>和<em>&nbsp;</em><code>high</code><em>&nbsp;</em>之间（包括二者）奇数的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>low = 3, high = 7
<strong>输出：</strong>3
<strong>解释：</strong>3 到 7 之间奇数数字为 [3,5,7] 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>low = 8, high = 10
<strong>输出：</strong>1
<strong>解释：</strong>8 到 10 之间奇数数字为 [9] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= low &lt;= high&nbsp;&lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和思想**

`[0, x]` 之间的奇数个数为 `(x + 1) >> 1`，那么 `[low, high]` 之间的奇数个数为 `((high + 1) >> 1) - (low >> 1)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countOdds(self, low: int, high: int) -> int:
        return ((high + 1) >> 1) - (low >> 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countOdds(int low, int high) {
        return ((high + 1) >> 1) - (low >> 1);
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_odds(low: i32, high: i32) -> i32 {
        ((high + 1) >> 1) - (low >> 1)
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countOdds(int low, int high) {
        return (high + 1 >> 1) - (low >> 1);
    }
};
```

### **Go**

```go
func countOdds(low int, high int) int {
	return ((high + 1) >> 1) - (low >> 1)
}
```

### **...**

```

```

<!-- tabs:end -->
