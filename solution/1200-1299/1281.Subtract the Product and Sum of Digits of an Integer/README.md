# [1281. 整数的各位积和之差](https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer)

[English Version](/solution/1200-1299/1281.Subtract%20the%20Product%20and%20Sum%20of%20Digits%20of%20an%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 234
<strong>输出：</strong>15 
<strong>解释：</strong>
各位数之积 = 2 * 3 * 4 = 24 
各位数之和 = 2 + 3 + 4 = 9 
结果 = 24 - 9 = 15
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 4421
<strong>输出：</strong>21
<strong>解释： 
</strong>各位数之积 = 4 * 4 * 2 * 1 = 32 
各位数之和 = 4 + 4 + 2 + 1 = 11 
结果 = 32 - 11 = 21
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        s, p = 0, 1
        while n:
            t = n % 10
            n //= 10
            s += t
            p *= t
        return p - s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subtractProductAndSum(int n) {
        int s = 0, p = 1;
        while (n != 0) {
            int t = n % 10;
            n /= 10;
            s += t;
            p *= t;
        }
        return p - s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subtractProductAndSum(int n) {
        int s = 0, p = 1;
        while (n) {
            int t = n % 10;
            n /= 10;
            s += t;
            p *= t;
        }
        return p - s;
    }
};
```

### **Go**

```go
func subtractProductAndSum(n int) int {
	s, p := 0, 1
	for n != 0 {
		t := n % 10
		n /= 10
		s += t
		p *= t
	}
	return p - s
}
```

### **TypeScript**

```ts
function subtractProductAndSum(n: number): number {
    let p = 1;
    let s = 0;
    while (n) {
        const num = n % 10;
        n = Math.floor(n / 10);
        p *= num;
        s += num;
    }
    return p - s;
}
```

### **Rust**

```rust
impl Solution {
    pub fn subtract_product_and_sum(mut n: i32) -> i32 {
        let mut p = 1;
        let mut s = 0;
        while n != 0 {
            let num = n % 10;
            n /= 10;
            p *= num;
            s += num;
        }
        p - s
    }
}
```

### **C**

```c
int subtractProductAndSum(int n) {
    int p = 1;
    int s = 0;
    while (n) {
        int num = n % 10;
        n /= 10;
        p *= num;
        s += num;
    }
    return p - s;
}
```

### **...**

```

```

<!-- tabs:end -->
