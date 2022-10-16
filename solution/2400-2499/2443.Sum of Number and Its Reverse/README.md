# [2443. 反转之后的数字和](https://leetcode.cn/problems/sum-of-number-and-its-reverse)

[English Version](/solution/2400-2499/2443.Sum%20of%20Number%20and%20Its%20Reverse/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>非负</strong> 整数 <code>num</code> 。如果存在某个 <strong>非负</strong> 整数 <code>k</code> 满足 <code>k + reverse(k) = num</code>&nbsp; ，则返回 <code>true</code> ；否则，返回<em> </em><code>false</code> 。</p>

<p><code>reverse(k)</code> 表示 <code>k</code> 反转每个数位后得到的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 443
<strong>输出：</strong>true
<strong>解释：</strong>172 + 271 = 443 ，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 63
<strong>输出：</strong>false
<strong>解释：</strong>63 不能表示为非负整数及其反转后数字之和，返回 false 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = 181
<strong>输出：</strong>true
<strong>解释：</strong>140 + 041 = 181 ，所以返回 true 。注意，反转后的数字可能包含前导零。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

在 $[0,.., num]$ 范围内枚举 $k$，判断 $k + reverse(k)$ 是否等于 $num$ 即可。

时间复杂度 $O(n\times \log n)。其中 $n$ 为 `num` 的大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOfNumberAndReverse(self, num: int) -> bool:
        return any(k + int(str(k)[::-1]) == num for k in range(num + 1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean sumOfNumberAndReverse(int num) {
        for (int x = 0; x <= num; ++x) {
            int k = x;
            int y = 0;
            while (k > 0) {
                y = y * 10 + k % 10;
                k /= 10;
            }
            if (x + y == num) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool sumOfNumberAndReverse(int num) {
        for (int x = 0; x <= num; ++x) {
            int k = x;
            int y = 0;
            while (k > 0) {
                y = y * 10 + k % 10;
                k /= 10;
            }
            if (x + y == num) {
                return true;
            }
        }
        return false;
    }
};
```

### **Go**

```go
func sumOfNumberAndReverse(num int) bool {
	for x := 0; x <= num; x++ {
		k, y := x, 0
		for k > 0 {
			y = y*10 + k%10
			k /= 10
		}
		if x+y == num {
			return true
		}
	}
	return false
}
```

### **C**

```c
bool sumOfNumberAndReverse(int num) {
    for (int i = 0; i <= num; i++) {
        int t = i;
        int j = 0;
        while (t > 0) {
            j = j * 10 + t % 10;
            t /= 10;
        }
        if (i + j == num) {
            return 1;
        }
    }
    return 0;
}
```

### **TypeScript**

```ts
function sumOfNumberAndReverse(num: number): boolean {
    for (let i = 0; i <= num; i++) {
        if (i + Number([...(i + '')].reverse().join('')) === num) {
            return true;
        }
    }
    return false;
}
```

### **Rust**

```rust
impl Solution {
    pub fn sum_of_number_and_reverse(num: i32) -> bool {
        for i in 0..=num {
            if i + {
                let mut t = i;
                let mut j = 0;
                while t > 0 {
                    j = j * 10 + t % 10;
                    t /= 10;
                }
                j
            } == num
            {
                return true;
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
