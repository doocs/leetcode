---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9864.%20%E6%B1%821%2B2%2B%E2%80%A6%2Bn/README.md
---

# [面试题 64. 求 1+2+…+n](https://leetcode.cn/problems/qiu-12n-lcof/)

## 题目描述

<p>求 <code>1+2+...+n</code> ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> n = 3
<strong>输出:&nbsp;</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> n = 9
<strong>输出:&nbsp;</strong>45
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= n&nbsp;&lt;= 10000</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def sumNums(self, n: int) -> int:
        return n and (n + self.sumNums(n - 1))
```

```java
class Solution {
    public int sumNums(int n) {
        int s = n;
        boolean t = n > 0 && (s += sumNums(n - 1)) > 0;
        return s;
    }
}
```

```cpp
class Solution {
public:
    int sumNums(int n) {
        n && (n += sumNums(n - 1));
        return n;
    }
};
```

```go
func sumNums(n int) int {
	s := 0
	var sum func(int) bool
	sum = func(n int) bool {
		s += n
		return n > 0 && sum(n-1)
	}
	sum(n)
	return s
}
```

```ts
var sumNums = function (n: number): number {
    return n && n + sumNums(n - 1);
};
```

```rust
impl Solution {
    pub fn sum_nums(mut n: i32) -> i32 {
        n != 0 &&
            (
                {
                    n += Solution::sum_nums(n - 1);
                },
                true,
            ).1;
        n
    }
}
```

```js
/**
 * @param {number} n
 * @return {number}
 */
var sumNums = function (n) {
    return (n ** 2 + n) >> 1;
};
```

```cs
public class Solution {
    public int result;
    public int SumNums(int n) {
        helper(n);
        return result;
    }

    public bool helper(int n) {
        result += n;
        return n == 0 || helper(n - 1);
    }
}
```

<!-- tabs:end -->

<!-- end -->
