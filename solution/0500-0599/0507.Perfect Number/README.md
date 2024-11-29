---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0507.Perfect%20Number/README.md
tags:
    - 数学
---

<!-- problem:start -->

# [507. 完美数](https://leetcode.cn/problems/perfect-number)

[English Version](/solution/0500-0599/0507.Perfect%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>对于一个&nbsp;<strong>正整数</strong>，如果它和除了它自身以外的所有 <strong>正因子</strong> 之和相等，我们称它为 <strong>「完美数」</strong>。</p>

<p>给定一个&nbsp;<strong>整数&nbsp;</strong><code>n</code>，&nbsp;如果是完美数，返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 28
<strong>输出：</strong>true
<strong>解释：</strong>28 = 1 + 2 + 4 + 7 + 14
1, 2, 4, 7, 和 14 是 28 的所有正因子。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 7
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们首先判断 $\textit{num}$ 是否为 1，如果为 1，则 $\textit{num}$ 不是完美数，返回 $\text{false}$。

然后，我们从 2 开始枚举 $\textit{num}$ 的所有正因子，如果 $\textit{num}$ 能被 $\textit{num}$ 的某个正因子 $i$ 整除，那么我们将 $i$ 加入到答案 $\textit{s}$ 中。如果 $\textit{num}$ 除以 $i$ 得到的商不等于 $i$，我们也将 $\textit{num}$ 除以 $i$ 得到的商加入到答案 $\textit{s}$ 中。

最后，我们判断 $\textit{s}$ 是否等于 $\textit{num}$ 即可。

时间复杂度 $O(\sqrt{n})$，其中 $n$ 为 $\textit{num}$ 的大小。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkPerfectNumber(self, num: int) -> bool:
        if num == 1:
            return False
        s, i = 1, 2
        while i <= num // i:
            if num % i == 0:
                s += i
                if i != num // i:
                    s += num // i
            i += 1
        return s == num
```

#### Java

```java
class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int s = 1;
        for (int i = 2; i <= num / i; ++i) {
            if (num % i == 0) {
                s += i;
                if (i != num / i) {
                    s += num / i;
                }
            }
        }
        return s == num;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int s = 1;
        for (int i = 2; i <= num / i; ++i) {
            if (num % i == 0) {
                s += i;
                if (i != num / i) {
                    s += num / i;
                }
            }
        }
        return s == num;
    }
};
```

#### Go

```go
func checkPerfectNumber(num int) bool {
	if num == 1 {
		return false
	}
	s := 1
	for i := 2; i <= num/i; i++ {
		if num%i == 0 {
			s += i
			if j := num / i; i != j {
				s += j
			}
		}
	}
	return s == num
}
```

#### TypeScript

```ts
function checkPerfectNumber(num: number): boolean {
    if (num <= 1) {
        return false;
    }
    let s = 1;
    for (let i = 2; i <= num / i; ++i) {
        if (num % i === 0) {
            s += i;
            if (i * i !== num) {
                s += num / i;
            }
        }
    }
    return s === num;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
