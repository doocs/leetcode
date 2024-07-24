---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0338.Counting%20Bits/README.md
tags:
    - 位运算
    - 动态规划
---

<!-- problem:start -->

# [338. 比特位计数](https://leetcode.cn/problems/counting-bits)

[English Version](/solution/0300-0399/0338.Counting%20Bits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，对于&nbsp;<code>0 &lt;= i &lt;= n</code> 中的每个 <code>i</code> ，计算其二进制表示中 <strong><code>1</code> 的个数</strong> ，返回一个长度为 <code>n + 1</code> 的数组 <code>ans</code> 作为答案。</p>

<p>&nbsp;</p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>[0,1,1]
<strong>解释：</strong>
0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>[0,1,1,2,1,2]
<strong>解释：</strong>
0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
3 --&gt; 11
4 --&gt; 100
5 --&gt; 101
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>很容易就能实现时间复杂度为 <code>O(n log n)</code> 的解决方案，你可以在线性时间复杂度 <code>O(n)</code> 内用一趟扫描解决此问题吗？</li>
	<li>你能不使用任何内置函数解决此问题吗？（如，C++ 中的&nbsp;<code>__builtin_popcount</code> ）</li>
</ul>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们直接枚举 $0 \leq i \leq n$ 中的每个数，对于每个数 $i$，我们用库函数或者 $lowbit$ 运算得到 $i$ 中二进制位 $1$ 的个数。

时间复杂度 $O(n \times \log n)$，忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBits(self, n: int) -> List[int]:
        return [i.bit_count() for i in range(n + 1)]
```

#### Java

```java
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countBits(int n) {
        vector<int> ans(n + 1);
        for (int i = 0; i <= n; ++i) {
            ans[i] = __builtin_popcount(i);
        }
        return ans;
    }
};
```

#### Go

```go
func countBits(n int) []int {
	ans := make([]int, n+1)
	for i := 0; i <= n; i++ {
		ans[i] = bits.OnesCount(uint(i))
	}
	return ans
}
```

#### TypeScript

```ts
function countBits(n: number): number[] {
    const ans: number[] = Array(n + 1).fill(0);
    for (let i = 0; i <= n; ++i) {
        ans[i] = bitCount(i);
    }
    return ans;
}

function bitCount(n: number): number {
    let count = 0;
    while (n) {
        n &= n - 1;
        ++count;
    }
    return count;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们定义一个长度为 $n+1$ 的答案数组 $ans$，初始时 $ans[0]=0$。

对于 $1 \leq i \leq n$，我们有 $ans[i] = ans[i \textit{ and } (i-1)] + 1$。其中 $i \textit{ and } (i-1)$ 表示将 $i$ 的二进制表示中的最低位 $1$ 变成 $0$ 之后的数，显然 $i \textit{ and } (i-1) < i$，且 $ans[i \textit{ and } (i-1)]$ 已经被计算出来了，我们就能以 $O(1)$ 的时间得到 $ans[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBits(self, n: int) -> List[int]:
        ans = [0] * (n + 1)
        for i in range(1, n + 1):
            ans[i] = ans[i & (i - 1)] + 1
        return ans
```

#### Java

```java
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countBits(int n) {
        vector<int> ans(n + 1);
        for (int i = 1; i <= n; ++i) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func countBits(n int) []int {
	ans := make([]int, n+1)
	for i := 1; i <= n; i++ {
		ans[i] = ans[i&(i-1)] + 1
	}
	return ans
}
```

#### TypeScript

```ts
function countBits(n: number): number[] {
    const ans: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        ans[i] = ans[i & (i - 1)] + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
