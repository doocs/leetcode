---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0869.Reordered%20Power%20of%202/README.md
tags:
    - 哈希表
    - 数学
    - 计数
    - 枚举
    - 排序
---

<!-- problem:start -->

# [869. 重新排序得到 2 的幂](https://leetcode.cn/problems/reordered-power-of-2)

[English Version](/solution/0800-0899/0869.Reordered%20Power%20of%202/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定正整数&nbsp;<code>n</code>&nbsp;，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。</p>

<p>如果我们可以通过上述方式得到&nbsp;2 的幂，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以在 $[1, 10^9]$ 的范围内枚举所有的 $2$ 的幂，判断它们的数字组成是否与给定的数字相同。

定义一个函数 $f(x)$，表示数字 $x$ 的数字组成。我们可以将数字 $x$ 转换为一个长度为 $10$ 的数组，或者一个按数字大小排序的字符串。

首先，我们计算给定数字 $n$ 的数字组成 $\text{target} = f(n)$。然后，我们枚举 $i$ 从 1 开始，每次将 $i$ 左移一位（相当于乘以 $2$），直到 $i$ 超过 $10^9$。对于每个 $i$，我们计算它的数字组成，并与 $\text{target}$ 进行比较。如果相同，则返回 $\text{true}$；如果枚举结束仍未找到相同的数字组成，则返回 $\text{false}$。

时间复杂度 $O(\log^2 M)$，空间复杂度 $O(\log M)$。其中 $M$ 是本题的输入范围上限 ${10}^9$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reorderedPowerOf2(self, n: int) -> bool:
        def f(x: int) -> List[int]:
            cnt = [0] * 10
            while x:
                x, v = divmod(x, 10)
                cnt[v] += 1
            return cnt

        target = f(n)
        i = 1
        while i <= 10**9:
            if f(i) == target:
                return True
            i <<= 1
        return False
```

#### Java

```java
class Solution {
    public boolean reorderedPowerOf2(int n) {
        String target = f(n);
        for (int i = 1; i <= 1000000000; i <<= 1) {
            if (target.equals(f(i))) {
                return true;
            }
        }
        return false;
    }

    private String f(int x) {
        char[] cnt = new char[10];
        for (; x > 0; x /= 10) {
            cnt[x % 10]++;
        }
        return new String(cnt);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool reorderedPowerOf2(int n) {
        string target = f(n);
        for (int i = 1; i <= 1000000000; i <<= 1) {
            if (target == f(i)) {
                return true;
            }
        }
        return false;
    }

private:
    string f(int x) {
        char cnt[10] = {};
        while (x > 0) {
            cnt[x % 10]++;
            x /= 10;
        }
        return string(cnt, cnt + 10);
    }
};
```

#### Go

```go
func reorderedPowerOf2(n int) bool {
	target := f(n)
	for i := 1; i <= 1000000000; i <<= 1 {
		if bytes.Equal(target, f(i)) {
			return true
		}
	}
	return false
}

func f(x int) []byte {
	cnt := make([]byte, 10)
	for x > 0 {
		cnt[x%10]++
		x /= 10
	}
	return cnt
}
```

#### TypeScript

```ts
function reorderedPowerOf2(n: number): boolean {
    const f = (x: number) => {
        const cnt = Array(10).fill(0);
        while (x > 0) {
            cnt[x % 10]++;
            x = (x / 10) | 0;
        }
        return cnt.join(',');
    };
    const target = f(n);
    for (let i = 1; i <= 1_000_000_000; i <<= 1) {
        if (target === f(i)) {
            return true;
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn reordered_power_of2(n: i32) -> bool {
        fn f(mut x: i32) -> [u8; 10] {
            let mut cnt = [0u8; 10];
            while x > 0 {
                cnt[(x % 10) as usize] += 1;
                x /= 10;
            }
            cnt
        }

        let target = f(n);
        let mut i = 1i32;
        while i <= 1_000_000_000 {
            if target == f(i) {
                return true;
            }
            i <<= 1;
        }
        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
