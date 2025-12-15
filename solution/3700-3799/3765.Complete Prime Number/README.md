---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3765.Complete%20Prime%20Number/README.md
rating: 1378
source: 第 171 场双周赛 Q1
tags:
    - 数学
    - 枚举
    - 数论
---

<!-- problem:start -->

# [3765. 完全质数](https://leetcode.cn/problems/complete-prime-number)

[English Version](/solution/3700-3799/3765.Complete%20Prime%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>num</code>。</p>

<p>如果一个数 <code>num</code> 的每一个 <strong>前缀</strong> 和每一个 <strong>后缀</strong> 都是 <strong>质数</strong>，则称该数为 <strong>完全质数</strong>。</p>

<p>如果 <code>num</code> 是完全质数，返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p><strong>注意</strong>：</p>

<ul>
	<li>一个数的 <strong>前缀</strong> 是由该数的 <strong>前</strong> <code>k</code> 位数字构成的。</li>
	<li>一个数的 <strong>后缀</strong> 是由该数的 <strong>后</strong> <code>k</code> 位数字构成的。</li>
	<li><strong>质数</strong> 是大于 1 且只有两个因子（1 和它本身）的自然数。</li>
	<li>个位数只有在它是 <strong>质数</strong> 时才被视为完全质数。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 23</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>num = 23</code> 的前缀是 2 和 23，它们都是质数。</li>
	<li><code>num = 23</code> 的后缀是 3 和 23，它们都是质数。</li>
	<li>所有的前缀和后缀都是质数，所以 23 是完全质数，答案是 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 39</span></p>

<p><strong>输出：</strong><span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>num = 39</code> 的前缀是 3 和 39。3 是质数，但 39 不是质数。</li>
	<li><code>num = 39</code> 的后缀是 9 和 39。9 和 39 都不是质数。</li>
	<li>至少有一个前缀或后缀不是质数，所以 39 不是完全质数，答案是 <code>false</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">num = 7</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>7 是质数，所以它的所有前缀和后缀都是质数，答案是 <code>true</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们定义一个函数 $\text{is\_prime}(x)$ 来判断一个数 $x$ 是否为质数。具体地，如果 $x < 2$，则 $x$ 不是质数；否则，我们检查从 $2$ 到 $\sqrt{x}$ 的所有整数 $i$，如果存在某个 $i$ 能整除 $x$，则 $x$ 不是质数，否则 $x$ 是质数。

接下来，我们将整数 $\textit{num}$ 转换为字符串 $s$，并依次检查 $s$ 的每个前缀和后缀所对应的整数是否为质数。对于前缀，我们从左到右构造整数 $x$，对于后缀，我们从右到左构造整数 $x$。如果在检查过程中发现某个前缀或后缀对应的整数不是质数，则返回 $\text{false}$；如果所有前缀和后缀对应的整数都是质数，则返回 $\text{true}$。

时间复杂度 $O(\sqrt{n} \times \log n)$，空间复杂度 $O(\log n)$，其中 $n$ 是整数 $\textit{num}$ 的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def completePrime(self, num: int) -> bool:
        def is_prime(x: int) -> bool:
            if x < 2:
                return False
            return all(x % i for i in range(2, int(sqrt(x)) + 1))

        s = str(num)
        x = 0
        for c in s:
            x = x * 10 + int(c)
            if not is_prime(x):
                return False
        x, p = 0, 1
        for c in s[::-1]:
            x = p * int(c) + x
            p *= 10
            if not is_prime(x):
                return False
        return True
```

#### Java

```java
class Solution {
    public boolean completePrime(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int x = 0;
        for (int i = 0; i < s.length; i++) {
            x = x * 10 + (s[i] - '0');
            if (!isPrime(x)) {
                return false;
            }
        }
        x = 0;
        int p = 1;
        for (int i = s.length - 1; i >= 0; i--) {
            x = p * (s[i] - '0') + x;
            p *= 10;
            if (!isPrime(x)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool completePrime(int num) {
        auto isPrime = [&](int x) {
            if (x < 2) {
                return false;
            }
            for (int i = 2; i * i <= x; ++i) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        };

        string s = to_string(num);

        int x = 0;
        for (char c : s) {
            x = x * 10 + (c - '0');
            if (!isPrime(x)) {
                return false;
            }
        }

        x = 0;
        int p = 1;
        for (int i = (int) s.size() - 1; i >= 0; --i) {
            x = p * (s[i] - '0') + x;
            p *= 10;
            if (!isPrime(x)) {
                return false;
            }
        }

        return true;
    }
};
```

#### Go

```go
func completePrime(num int) bool {
    isPrime := func(x int) bool {
        if x < 2 {
            return false
        }
        for i := 2; i*i <= x; i++ {
            if x%i == 0 {
                return false
            }
        }
        return true
    }

    s := strconv.Itoa(num)

    x := 0
    for i := 0; i < len(s); i++ {
        x = x*10 + int(s[i]-'0')
        if !isPrime(x) {
            return false
        }
    }

    x = 0
    p := 1
    for i := len(s) - 1; i >= 0; i-- {
        x = p*int(s[i]-'0') + x
        p *= 10
        if !isPrime(x) {
            return false
        }
    }

    return true
}
```

#### TypeScript

```ts
function completePrime(num: number): boolean {
    const isPrime = (x: number): boolean => {
        if (x < 2) return false;
        for (let i = 2; i * i <= x; i++) {
            if (x % i === 0) {
                return false;
            }
        }
        return true;
    };

    const s = String(num);

    let x = 0;
    for (let i = 0; i < s.length; i++) {
        x = x * 10 + (s.charCodeAt(i) - 48);
        if (!isPrime(x)) {
            return false;
        }
    }

    x = 0;
    let p = 1;
    for (let i = s.length - 1; i >= 0; i--) {
        x = p * (s.charCodeAt(i) - 48) + x;
        p *= 10;
        if (!isPrime(x)) {
            return false;
        }
    }

    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
