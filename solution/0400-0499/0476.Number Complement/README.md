---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0476.Number%20Complement/README.md
tags:
    - 位运算
---

<!-- problem:start -->

# [476. 数字的补数](https://leetcode.cn/problems/number-complement)

[English Version](/solution/0400-0499/0476.Number%20Complement/README_EN.md)

## 题目描述

<!-- description:start -->

<p>对整数的二进制表示取反（<code>0</code> 变 <code>1</code> ，<code>1</code> 变 <code>0</code>）后，再转换为十进制表示，可以得到这个整数的补数。</p>

<ul>
	<li>例如，整数 <code>5</code> 的二进制表示是 <code>"101"</code> ，取反后得到 <code>"010"</code> ，再转回十进制表示得到补数 <code>2</code> 。</li>
</ul>

<p>给你一个整数 <code>num</code> ，输出它的补数。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = 5
<strong>输出：</strong>2
<strong>解释：</strong>5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = 1
<strong>输出：</strong>0
<strong>解释：</strong>1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt; 2<sup>31</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与 1009 <a href="https://leetcode.cn/problems/complement-of-base-10-integer/">https://leetcode.cn/problems/complement-of-base-10-integer/</a> 相同</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

根据题目描述，我们可以通过异或运算来实现取反的操作，步骤如下：

我们首先找到 $\textit{num}$ 的二进制表示中最高位的 $1$，位置记为 $k$。

然后，构造一个二进制数，第 $k$ 位为 $0$，其余低位为 $1$，即 $2^k - 1$；

最后，将 $\textit{num}$ 与上述构造的二进制数进行异或运算，即可得到答案。

时间复杂度 $O(\log \textit{num})$，其中 $\textit{num}$ 为输入的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findComplement(self, num: int) -> int:
        return num ^ ((1 << num.bit_length()) - 1)
```

#### Java

```java
class Solution {
    public int findComplement(int num) {
        return num ^ ((1 << (32 - Integer.numberOfLeadingZeros(num))) - 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findComplement(int num) {
        return num ^ ((1LL << (64 - __builtin_clzll(num))) - 1);
    }
};
```

#### Go

```go
func findComplement(num int) int {
	return num ^ ((1 << bits.Len(uint(num))) - 1)
}
```

#### TypeScript

```ts
function findComplement(num: number): number {
    return num ^ (2 ** num.toString(2).length - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
