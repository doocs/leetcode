# [633. 平方数之和](https://leetcode.cn/problems/sum-of-square-numbers)

[English Version](/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数&nbsp;<code>c</code>&nbsp;，你要判断是否存在两个整数 <code>a</code> 和 <code>b</code>，使得&nbsp;<code>a<sup>2</sup> + b<sup>2</sup> = c</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>c = 5
<strong>输出：</strong>true
<strong>解释：</strong>1 * 1 + 2 * 2 = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>c = 3
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= c &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

![](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/images/table.png)

上图为 a，b，c 之间的关系，这题其实就是在这张“表”里查找 c。

从表的右上角看，不难发现它类似一棵二叉查找树，所以只需从右上角开始，按照二叉查找树的规律进行搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        a, b = 0, int(sqrt(c))
        while a <= b:
            s = a**2 + b**2
            if s == c:
                return True
            if s < c:
                a += 1
            else:
                b -= 1
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        long a = 0, b = (long) Math.sqrt(c);
        while (a <= b) {
            long s = a * a + b * b;
            if (s == c) {
                return true;
            }
            if (s < c) {
                ++a;
            } else {
                --b;
            }
        }
        return false;
    }
}
```

### **TypeScript**

```ts
function judgeSquareSum(c: number): boolean {
    let a = 0,
        b = Math.floor(Math.sqrt(c));
    while (a <= b) {
        let sum = a ** 2 + b ** 2;
        if (sum == c) return true;
        if (sum < c) {
            ++a;
        } else {
            --b;
        }
    }
    return false;
}
```

### **C++**

```cpp
class Solution {
public:
    bool judgeSquareSum(int c) {
        long a = 0, b = (long)sqrt(c);
        while (a <= b) {
            long s = a * a + b * b;
            if (s == c) return true;
            if (s < c)
                ++a;
            else
                --b;
        }
        return false;
    }
};
```

### **Go**

```go
func judgeSquareSum(c int) bool {
	a, b := 0, int(math.Sqrt(float64(c)))
	for a <= b {
		s := a*a + b*b
		if s == c {
			return true
		}
		if s < c {
			a++
		} else {
			b--
		}
	}
	return false
}
```

### **Rust**

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn judge_square_sum(c: i32) -> bool {
        let c = c as i64;
        let mut left = 0;
        let mut right = (c as f64).sqrt() as i64;
        while left <= right {
            let num = left * left + right * right;
            match num.cmp(&c) {
                Ordering::Less => left += 1,
                Ordering::Greater => right -= 1,
                Ordering::Equal => return true,
            }
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
