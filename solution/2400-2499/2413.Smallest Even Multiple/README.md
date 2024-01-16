# [2413. 最小偶倍数](https://leetcode.cn/problems/smallest-even-multiple)

[English Version](/solution/2400-2499/2413.Smallest%20Even%20Multiple/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个正整数 <code>n</code> ，返回 <code>2</code><em> </em>和<em> </em><code>n</code> 的最小公倍数（正整数）。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>10
<strong>解释：</strong>5 和 2 的最小公倍数是 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6
<strong>输出：</strong>6
<strong>解释：</strong>6 和 2 的最小公倍数是 6 。注意数字会是它自身的倍数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 150</code></li>
</ul>

## 解法

### 方法一：数学

如果 $n$ 为偶数，那么 $2$ 和 $n$ 的最小公倍数就是 $n$ 本身。否则，$2$ 和 $n$ 的最小公倍数就是 $n \times 2$。

时间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def smallestEvenMultiple(self, n: int) -> int:
        return n if n % 2 == 0 else n * 2
```

```java
class Solution {
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : n * 2;
    }
}
```

```cpp
class Solution {
public:
    int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : n * 2;
    }
};
```

```go
func smallestEvenMultiple(n int) int {
	if n%2 == 0 {
		return n
	}
	return n * 2
}
```

```ts
function smallestEvenMultiple(n: number): number {
    return n % 2 === 0 ? n : n * 2;
}
```

```rust
impl Solution {
    pub fn smallest_even_multiple(n: i32) -> i32 {
        if n % 2 == 0 {
            return n;
        }
        n * 2
    }
}
```

```c
int smallestEvenMultiple(int n) {
    return n % 2 == 0 ? n : n * 2;
}
```

<!-- tabs:end -->

<!-- end -->
