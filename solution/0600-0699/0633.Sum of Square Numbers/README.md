---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README.md
tags:
    - 数学
    - 双指针
    - 二分查找
---

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

### 方法一：数学 + 双指针

我们可以使用双指针的方法来解决这个问题，定义两个指针 $a$ 和 $b$，分别指向 $0$ 和 $\sqrt{c}$。在每一步中，我们会计算 $s = a^2 + b^2$ 的值，然后比较 $s$ 与 $c$ 的大小。如果 $s = c$，我们就找到了两个整数 $a$ 和 $b$，使得 $a^2 + b^2 = c$。如果 $s < c$，我们将 $a$ 的值增加 $1$，如果 $s > c$，我们将 $b$ 的值减小 $1$。我们不断进行这个过程直到找到答案，或者 $a$ 的值大于 $b$ 的值，返回 `false`。

时间复杂度 $O(\sqrt{c})$，其中 $c$ 是给定的非负整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

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

```cpp
class Solution {
public:
    bool judgeSquareSum(int c) {
        long long a = 0, b = sqrt(c);
        while (a <= b) {
            long long s = a * a + b * b;
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
};
```

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

```ts
function judgeSquareSum(c: number): boolean {
    let [a, b] = [0, Math.floor(Math.sqrt(c))];
    while (a <= b) {
        const s = a * a + b * b;
        if (s === c) {
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
```

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn judge_square_sum(c: i32) -> bool {
        let mut a: i64 = 0;
        let mut b: i64 = (c as f64).sqrt() as i64;
        while a <= b {
            let s = a * a + b * b;
            match s.cmp(&(c as i64)) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    a += 1;
                }
                Ordering::Greater => {
                    b -= 1;
                }
            }
        }
        false
    }
}
```

<!-- tabs:end -->

<!-- end -->
