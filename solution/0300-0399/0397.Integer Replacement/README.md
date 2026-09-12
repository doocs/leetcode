---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0397.Integer%20Replacement/README.md
tags:
    - 贪心
    - 位运算
    - 记忆化
    - 动态规划
---

<!-- problem:start -->

# [397. 整数替换](https://leetcode.cn/problems/integer-replacement)

[English Version](/solution/0300-0399/0397.Integer%20Replacement/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数&nbsp;<code>n</code> ，你可以做如下操作：</p>

<ol>
	<li>如果&nbsp;<code>n</code><em>&nbsp;</em>是偶数，则用&nbsp;<code>n / 2</code>替换&nbsp;<code>n</code><em> </em>。</li>
	<li>如果&nbsp;<code>n</code><em>&nbsp;</em>是奇数，则可以用&nbsp;<code>n + 1</code>或<code>n - 1</code>替换&nbsp;<code>n</code> 。</li>
</ol>

<p>返回 <code>n</code><em>&nbsp;</em>变为 <code>1</code> 所需的 <em>最小替换次数</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 8
<strong>输出：</strong>3
<strong>解释：</strong>8 -&gt; 4 -&gt; 2 -&gt; 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 7
<strong>输出：</strong>4
<strong>解释：</strong>7 -&gt; 8 -&gt; 4 -&gt; 2 -&gt; 1
或 7 -&gt; 6 -&gt; 3 -&gt; 2 -&gt; 1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 偶则除 $2$，奇则加一或减一，求到 $1$ 的最少步。BFS 可行，但贪心按二进制更短。偶必右移；奇时若末两位为 $11$ 且不是 $3$，加一可消更多 $1$，否则减一。
>
> $3$ 特判减一，避免加到 $4$ 再除两次多走。循环直到 $1$。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def integerReplacement(self, n: int) -> int:
        ans = 0
        while n != 1:
            if (n & 1) == 0:
                n >>= 1
            elif n != 3 and (n & 3) == 3:
                n += 1
            else:
                n -= 1
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int integerReplacement(int n) {
        int ans = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n != 3 && (n & 3) == 3) {
                ++n;
            } else {
                --n;
            }
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int integerReplacement(int N) {
        int ans = 0;
        long n = N;
        while (n != 1) {
            if ((n & 1) == 0)
                n >>= 1;
            else if (n != 3 && (n & 3) == 3)
                ++n;
            else
                --n;
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func integerReplacement(n int) int {
	ans := 0
	for n != 1 {
		if (n & 1) == 0 {
			n >>= 1
		} else if n != 3 && (n&3) == 3 {
			n++
		} else {
			n--
		}
		ans++
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
