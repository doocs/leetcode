---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1864.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20Binary%20String%20Alternating/README.md
rating: 1600
source: 第 241 场周赛 Q2
tags:
    - 贪心
    - 字符串
---

<!-- problem:start -->

# [1864. 构成交替字符串需要的最小交换次数](https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating)

[English Version](/solution/1800-1899/1864.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20Binary%20String%20Alternating/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串 <code>s</code> ，现需要将其转化为一个 <strong>交替字符串</strong> 。请你计算并返回转化所需的 <strong>最小</strong> 字符交换次数，如果无法完成转化，返回<em> </em><code>-1</code><em> </em>。</p>

<p><strong>交替字符串</strong> 是指：相邻字符之间不存在相等情况的字符串。例如，字符串 <code>"010"</code> 和 <code>"1010"</code> 属于交替字符串，但 <code>"0100"</code> 不是。</p>

<p>任意两个字符都可以进行交换，<strong>不必相邻</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "111000"
<strong>输出：</strong>1
<strong>解释：</strong>交换位置 1 和 4："1<em><strong>1</strong></em>10<em><strong>0</strong></em>0" -> "1<em><strong>0</strong></em>10<em><strong>1</strong></em>0" ，字符串变为交替字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "010"
<strong>输出：</strong>0
<strong>解释：</strong>字符串已经是交替字符串了，不需要交换。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "1110"
<strong>输出：</strong>-1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>s[i]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们先统计字符串 $\textit{s}$ 中字符 $0$ 和字符 $1$ 的个数，分别记为 $n_0$ 和 $n_1$。

如果 $n_0$ 和 $n_1$ 的绝对值大于 $1$，那么无法构成交替字符串，返回 $-1$。

如果 $n_0$ 和 $n_1$ 相等，那么我们可以分别计算将字符串转化为以 $0$ 开头和以 $1$ 开头的交替字符串所需要的交换次数，取最小值。

如果 $n_0$ 和 $n_1$ 不相等，那么我们只需要计算将字符串转化为以字符个数较多的字符开头的交替字符串所需要的交换次数。

问题转换为：计算字符串 $\textit{s}$ 转化为以字符 $c$ 开头的交替字符串所需要的交换次数。

我们定义一个函数 $\text{calc}(c)$，表示将字符串 $\textit{s}$ 转化为以字符 $c$ 开头的交替字符串所需要的交换次数。我们遍历字符串 $\textit{s}$，对于每个位置 $i$，如果 $i$ 与 $c$ 的奇偶性不同，那么我们需要交换这个位置的字符，计数器 $+1$。由于每次交换都会使两个位置的字符变得相同，因此最终的交换次数为计数器的一半。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $\textit{s}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSwaps(self, s: str) -> int:
        def calc(c: int) -> int:
            return sum((c ^ i & 1) != x for i, x in enumerate(map(int, s))) // 2

        n0 = s.count("0")
        n1 = len(s) - n0
        if abs(n0 - n1) > 1:
            return -1
        if n0 == n1:
            return min(calc(0), calc(1))
        return calc(0 if n0 > n1 else 1)
```

#### Java

```java
class Solution {
    private char[] s;

    public int minSwaps(String s) {
        this.s = s.toCharArray();
        int n1 = 0;
        for (char c : this.s) {
            n1 += (c - '0');
        }
        int n0 = this.s.length - n1;
        if (Math.abs(n0 - n1) > 1) {
            return -1;
        }
        if (n0 == n1) {
            return Math.min(calc(0), calc(1));
        }
        return calc(n0 > n1 ? 0 : 1);
    }

    private int calc(int c) {
        int cnt = 0;
        for (int i = 0; i < s.length; ++i) {
            int x = s[i] - '0';
            if ((i & 1 ^ c) != x) {
                ++cnt;
            }
        }
        return cnt / 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSwaps(string s) {
        int n0 = ranges::count(s, '0');
        int n1 = s.size() - n0;
        if (abs(n0 - n1) > 1) {
            return -1;
        }
        auto calc = [&](int c) -> int {
            int cnt = 0;
            for (int i = 0; i < s.size(); ++i) {
                int x = s[i] - '0';
                if ((i & 1 ^ c) != x) {
                    ++cnt;
                }
            }
            return cnt / 2;
        };
        if (n0 == n1) {
            return min(calc(0), calc(1));
        }
        return calc(n0 > n1 ? 0 : 1);
    }
};
```

#### Go

```go
func minSwaps(s string) int {
	n0 := strings.Count(s, "0")
	n1 := len(s) - n0
	if abs(n0-n1) > 1 {
		return -1
	}
	calc := func(c int) int {
		cnt := 0
		for i, ch := range s {
			x := int(ch - '0')
			if i&1^c != x {
				cnt++
			}
		}
		return cnt / 2
	}
	if n0 == n1 {
		return min(calc(0), calc(1))
	}
	if n0 > n1 {
		return calc(0)
	}
	return calc(1)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minSwaps(s: string): number {
    const n0 = (s.match(/0/g) || []).length;
    const n1 = s.length - n0;
    if (Math.abs(n0 - n1) > 1) {
        return -1;
    }
    const calc = (c: number): number => {
        let cnt = 0;
        for (let i = 0; i < s.length; i++) {
            const x = +s[i];
            if (((i & 1) ^ c) !== x) {
                cnt++;
            }
        }
        return Math.floor(cnt / 2);
    };
    if (n0 === n1) {
        return Math.min(calc(0), calc(1));
    }
    return calc(n0 > n1 ? 0 : 1);
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var minSwaps = function (s) {
    const n0 = (s.match(/0/g) || []).length;
    const n1 = s.length - n0;
    if (Math.abs(n0 - n1) > 1) {
        return -1;
    }
    const calc = c => {
        let cnt = 0;
        for (let i = 0; i < s.length; i++) {
            const x = +s[i];
            if (((i & 1) ^ c) !== x) {
                cnt++;
            }
        }
        return Math.floor(cnt / 2);
    };
    if (n0 === n1) {
        return Math.min(calc(0), calc(1));
    }
    return calc(n0 > n1 ? 0 : 1);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
