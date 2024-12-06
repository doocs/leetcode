---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1009.Complement%20of%20Base%2010%20Integer/README.md
rating: 1234
source: 第 128 场周赛 Q1
tags:
    - 位运算
---

<!-- problem:start -->

# [1009. 十进制整数的反码](https://leetcode.cn/problems/complement-of-base-10-integer)

[English Version](/solution/1000-1099/1009.Complement%20of%20Base%2010%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>每个非负整数&nbsp;<code>N</code>&nbsp;都有其二进制表示。例如，&nbsp;<code>5</code>&nbsp;可以被表示为二进制&nbsp;<code>&quot;101&quot;</code>，<code>11</code> 可以用二进制&nbsp;<code>&quot;1011&quot;</code>&nbsp;表示，依此类推。注意，除&nbsp;<code>N = 0</code>&nbsp;外，任何二进制表示中都不含前导零。</p>

<p>二进制的反码表示是将每个&nbsp;<code>1</code>&nbsp;改为&nbsp;<code>0</code>&nbsp;且每个&nbsp;<code>0</code>&nbsp;变为&nbsp;<code>1</code>。例如，二进制数&nbsp;<code>&quot;101&quot;</code>&nbsp;的二进制反码为&nbsp;<code>&quot;010&quot;</code>。</p>

<p>给你一个十进制数&nbsp;<code>N</code>，请你返回其二进制表示的反码所对应的十进制整数。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>5
<strong>输出：</strong>2
<strong>解释：</strong>5 的二进制表示为 &quot;101&quot;，其二进制反码为 &quot;010&quot;，也就是十进制中的 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>7
<strong>输出：</strong>0
<strong>解释：</strong>7 的二进制表示为 &quot;111&quot;，其二进制反码为 &quot;000&quot;，也就是十进制中的 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>10
<strong>输出：</strong>5
<strong>解释：</strong>10 的二进制表示为 &quot;1010&quot;，其二进制反码为 &quot;0101&quot;，也就是十进制中的 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= N &lt; 10^9</code></li>
	<li>本题与 476：<a href="https://leetcode.cn/problems/number-complement/">https://leetcode.cn/problems/number-complement/</a> 相同</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们首先判断 $n$ 是否为 $0$，如果是，则返回 $1$。

接着我们定义两个变量 $\textit{ans}$ 和 $i$，初始化为 $0$。然后我们遍历 $n$，在每次遍历中，我们将 $\textit{ans}$ 的第 $i$ 位设置为 $n$ 的第 $i$ 位取反，然后将 $i$ 加 $1$，并且$n$ 右移 $1$ 位。

最后返回 $\textit{ans}$ 即可。

时间复杂度 $O(\log n)$，其中 $n$ 为给定的十进制数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bitwiseComplement(self, n: int) -> int:
        if n == 0:
            return 1
        ans = i = 0
        while n:
            ans |= ((n & 1 ^ 1) << i)
            i += 1
            n >>= 1
        return ans
```

#### Java

```java
class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0, i = 0;
        while (n != 0) {
            ans |= (n & 1 ^ 1) << (i++);
            n >>= 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0, i = 0;
        while (n != 0) {
            ans |= (n & 1 ^ 1) << (i++);
            n >>= 1;
        }
        return ans;
    }
};
```

#### Go

```go
func bitwiseComplement(n int) (ans int) {
	if n == 0 {
		return 1
	}
	for i := 0; n != 0; n >>= 1 {
		ans |= (n&1 ^ 1) << i
		i++
	}
	return
}
```

#### TypeScript

```ts
function bitwiseComplement(n: number): number {
    if (n === 0) {
        return 1;
    }
    let ans = 0;
    for (let i = 0; n; n >>= 1) {
        ans |= ((n & 1) ^ 1) << i++;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn bitwise_complement(mut n: i32) -> i32 {
        if n == 0 {
            return 1;
        }
        let mut ans = 0;
        let mut i = 0;
        while n != 0 {
            ans |= ((n & 1) ^ 1) << i;
            n >>= 1;
            i += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
