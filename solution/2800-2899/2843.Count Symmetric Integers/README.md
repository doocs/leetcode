---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2843.Count%20Symmetric%20Integers/README.md
rating: 1269
source: 第 361 场周赛 Q1
tags:
    - 数学
    - 枚举
---

<!-- problem:start -->

# [2843. 统计对称整数的数目](https://leetcode.cn/problems/count-symmetric-integers)

[English Version](/solution/2800-2899/2843.Count%20Symmetric%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数 <code>low</code> 和 <code>high</code> 。</p>

<p>对于一个由 <code>2 * n</code> 位数字组成的整数 <code>x</code> ，如果其前 <code>n</code> 位数字之和与后 <code>n</code> 位数字之和相等，则认为这个数字是一个对称整数。</p>

<p>返回在 <code>[low, high]</code> 范围内的 <strong>对称整数的数目</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>low = 1, high = 100
<strong>输出：</strong>9
<strong>解释：</strong>在 1 到 100 范围内共有 9 个对称整数：11、22、33、44、55、66、77、88 和 99 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>low = 1200, high = 1230
<strong>输出：</strong>4
<strong>解释：</strong>在 1200 到 1230 范围内共有 4 个对称整数：1203、1212、1221 和 1230 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= low &lt;= high &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们枚举 $[low, high]$ 中的每个整数 $x$，判断其是否是对称整数。如果是，那么答案 $ans$ 增加 $1$。

时间复杂度 $O(n \times \log m)$，空间复杂度 $O(\log m)$。其中 $n$ 是 $[low, high]$ 中整数的个数，而 $m$ 是题目中给出的最大整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSymmetricIntegers(self, low: int, high: int) -> int:
        def f(x: int) -> bool:
            s = str(x)
            if len(s) & 1:
                return False
            n = len(s) // 2
            return sum(map(int, s[:n])) == sum(map(int, s[n:]))

        return sum(f(x) for x in range(low, high + 1))
```

#### Java

```java
class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int x = low; x <= high; ++x) {
            ans += f(x);
        }
        return ans;
    }

    private int f(int x) {
        String s = "" + x;
        int n = s.length();
        if (n % 2 == 1) {
            return 0;
        }
        int a = 0, b = 0;
        for (int i = 0; i < n / 2; ++i) {
            a += s.charAt(i) - '0';
        }
        for (int i = n / 2; i < n; ++i) {
            b += s.charAt(i) - '0';
        }
        return a == b ? 1 : 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        auto f = [](int x) {
            string s = to_string(x);
            int n = s.size();
            if (n & 1) {
                return 0;
            }
            int a = 0, b = 0;
            for (int i = 0; i < n / 2; ++i) {
                a += s[i] - '0';
                b += s[n / 2 + i] - '0';
            }
            return a == b ? 1 : 0;
        };
        for (int x = low; x <= high; ++x) {
            ans += f(x);
        }
        return ans;
    }
};
```

#### Go

```go
func countSymmetricIntegers(low int, high int) (ans int) {
	f := func(x int) int {
		s := strconv.Itoa(x)
		n := len(s)
		if n&1 == 1 {
			return 0
		}
		a, b := 0, 0
		for i := 0; i < n/2; i++ {
			a += int(s[i] - '0')
			b += int(s[n/2+i] - '0')
		}
		if a == b {
			return 1
		}
		return 0
	}
	for x := low; x <= high; x++ {
		ans += f(x)
	}
	return
}
```

#### TypeScript

```ts
function countSymmetricIntegers(low: number, high: number): number {
    let ans = 0;
    const f = (x: number): number => {
        const s = x.toString();
        const n = s.length;
        if (n & 1) {
            return 0;
        }
        let a = 0;
        let b = 0;
        for (let i = 0; i < n >> 1; ++i) {
            a += Number(s[i]);
            b += Number(s[(n >> 1) + i]);
        }
        return a === b ? 1 : 0;
    };
    for (let x = low; x <= high; ++x) {
        ans += f(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
