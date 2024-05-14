# [2520. 统计能整除数字的位数](https://leetcode.cn/problems/count-the-digits-that-divide-a-number)

[English Version](/solution/2500-2599/2520.Count%20the%20Digits%20That%20Divide%20a%20Number/README_EN.md)

<!-- tags:数学 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>num</code> ，返回 <code>num</code> 中能整除 <code>num</code> 的数位的数目。</p>

<p>如果满足&nbsp;<code>nums % val == 0</code> ，则认为整数 <code>val</code> 可以整除 <code>nums</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 7
<strong>输出：</strong>1
<strong>解释：</strong>7 被自己整除，因此答案是 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 121
<strong>输出：</strong>2
<strong>解释：</strong>121 可以被 1 整除，但无法被 2 整除。由于 1 出现两次，所以返回 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = 1248
<strong>输出：</strong>4
<strong>解释：</strong>1248 可以被它每一位上的数字整除，因此答案是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
	<li><code>num</code> 的数位中不含 <code>0</code></li>
</ul>

## 解法

### 方法一：枚举

我们直接枚举整数 $num$ 的每一位上的数 $val$，若 $val$ 能够整除 $num$，那么答案加一。

枚举结束后，返回答案即可。

时间复杂度 $O(\log num)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countDigits(self, num: int) -> int:
        ans, x = 0, num
        while x:
            x, val = divmod(x, 10)
            ans += num % val == 0
        return ans
```

```java
class Solution {
    public int countDigits(int num) {
        int ans = 0;
        for (int x = num; x > 0; x /= 10) {
            if (num % (x % 10) == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countDigits(int num) {
        int ans = 0;
        for (int x = num; x > 0; x /= 10) {
            if (num % (x % 10) == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func countDigits(num int) (ans int) {
	for x := num; x > 0; x /= 10 {
		if num%(x%10) == 0 {
			ans++
		}
	}
	return
}
```

```ts
function countDigits(num: number): number {
    let ans = 0;
    for (let x = num; x; x = (x / 10) | 0) {
        if (num % (x % 10) === 0) {
            ++ans;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn count_digits(num: i32) -> i32 {
        let mut ans = 0;
        let mut cur = num;
        while cur != 0 {
            if num % (cur % 10) == 0 {
                ans += 1;
            }
            cur /= 10;
        }
        ans
    }
}
```

```c
int countDigits(int num) {
    int ans = 0;
    int cur = num;
    while (cur) {
        if (num % (cur % 10) == 0) {
            ans++;
        }
        cur /= 10;
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```ts
function countDigits(num: number): number {
    let ans = 0;
    for (const s of num.toString()) {
        if (num % Number(s) === 0) {
            ans++;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn count_digits(num: i32) -> i32 {
        num
            .to_string()
            .chars()
            .filter(|&c| c != '0')
            .filter(|&c| num % (c.to_digit(10).unwrap() as i32) == 0)
            .count() as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
