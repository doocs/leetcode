---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1545.Find%20Kth%20Bit%20in%20Nth%20Binary%20String/README.md
rating: 1479
source: 第 201 场周赛 Q2
tags:
    - 递归
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [1545. 找出第 N 个二进制字符串中的第 K 位](https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string)

[English Version](/solution/1500-1599/1545.Find%20Kth%20Bit%20in%20Nth%20Binary%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数 <code>n</code> 和 <code>k</code>，二进制字符串  <code>S<sub>n</sub></code> 的形成规则如下：</p>

<ul>
	<li><code>S<sub>1</sub> = "0"</code></li>
	<li>当 <code>i > 1</code> 时，<code>S<sub>i</sub> = S<sub>i-1</sub> + "1" + reverse(invert(S<sub>i-1</sub>))</code></li>
</ul>

<p>其中 <code>+</code> 表示串联操作，<code>reverse(x)</code> 返回反转 <code>x</code> 后得到的字符串，而 <code>invert(x)</code> 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。</p>

<p>例如，符合上述描述的序列的前 4 个字符串依次是：</p>

<ul>
	<li><code>S<sub>1 </sub>= "0"</code></li>
	<li><code>S<sub>2 </sub>= "0<strong>1</strong>1"</code></li>
	<li><code>S<sub>3 </sub>= "011<strong>1</strong>001"</code></li>
	<li><code>S<sub>4</sub> = "0111001<strong>1</strong>0110001"</code></li>
</ul>

<p>请你返回  <code>S<sub>n</sub></code> 的 <strong>第 <code>k</code> 位字符</strong> ，题目数据保证 <code>k</code> 一定在 <code>S<sub>n</sub></code> 长度范围以内。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 1
<strong>输出：</strong>"0"
<strong>解释：</strong>S<sub>3</sub> 为 "<strong>0</strong>111001"，其第 1 位为 "0" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, k = 11
<strong>输出：</strong>"1"
<strong>解释：</strong>S<sub>4</sub> 为 "0111001101<strong>1</strong>0001"，其第 11 位为 "1" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 1
<strong>输出：</strong>"0"
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 2, k = 3
<strong>输出：</strong>"1"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 20</code></li>
	<li><code>1 <= k <= 2<sup>n</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分类讨论 + 递归

我们可以发现，对于 $S_n$，其前半部分和 $S_{n-1}$ 是一样的，而后半部分是 $S_{n-1}$ 的反转取反。因此我们可以设计一个函数 $dfs(n, k)$，表示第 $n$ 个字符串的第 $k$ 位字符。答案即为 $dfs(n, k)$。

函数 $dfs(n, k)$ 的计算过程如下：

-   如果 $k = 1$，那么答案为 $0$；
-   如果 $k$ 是 $2$ 的幂次方，那么答案为 $1$；
-   如果 $k \times 2 \lt 2^n - 1$，说明 $k$ 在前半部分，答案为 $dfs(n - 1, k)$；
-   否则，答案为 $dfs(n - 1, 2^n - k) \oplus 1$，其中 $\oplus$ 表示异或运算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为题目给定的 $n$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findKthBit(self, n: int, k: int) -> str:
        def dfs(n: int, k: int) -> int:
            if k == 1:
                return 0
            if (k & (k - 1)) == 0:
                return 1
            m = 1 << n
            if k * 2 < m - 1:
                return dfs(n - 1, k)
            return dfs(n - 1, m - k) ^ 1

        return str(dfs(n, k))
```

#### Java

```java
class Solution {
    public char findKthBit(int n, int k) {
        return (char) ('0' + dfs(n, k));
    }

    private int dfs(int n, int k) {
        if (k == 1) {
            return 0;
        }
        if ((k & (k - 1)) == 0) {
            return 1;
        }
        int m = 1 << n;
        if (k * 2 < m - 1) {
            return dfs(n - 1, k);
        }
        return dfs(n - 1, m - k) ^ 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    char findKthBit(int n, int k) {
        function<int(int, int)> dfs = [&](int n, int k) {
            if (k == 1) {
                return 0;
            }
            if ((k & (k - 1)) == 0) {
                return 1;
            }
            int m = 1 << n;
            if (k * 2 < m - 1) {
                return dfs(n - 1, k);
            }
            return dfs(n - 1, m - k) ^ 1;
        };
        return '0' + dfs(n, k);
    }
};
```

#### Go

```go
func findKthBit(n int, k int) byte {
	var dfs func(n, k int) int
	dfs = func(n, k int) int {
		if k == 1 {
			return 0
		}
		if k&(k-1) == 0 {
			return 1
		}
		m := 1 << n
		if k*2 < m-1 {
			return dfs(n-1, k)
		}
		return dfs(n-1, m-k) ^ 1
	}
	return byte('0' + dfs(n, k))
}
```

#### TypeScript

```ts
function findKthBit(n: number, k: number): string {
    const dfs = (n: number, k: number): number => {
        if (k === 1) {
            return 0;
        }
        if ((k & (k - 1)) === 0) {
            return 1;
        }
        const m = 1 << n;
        if (k * 2 < m - 1) {
            return dfs(n - 1, k);
        }
        return dfs(n - 1, m - k) ^ 1;
    };
    return dfs(n, k).toString();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：位运算

<!-- tabs:start -->

#### TypeScript

```ts
const findKthBit = (n: number, k: number): string =>
    String((((k / (k & -k)) >> 1) & 1) ^ (k & 1) ^ 1);
```

#### JavaScript

```js
const findKthBit = (n, k) => String((((k / (k & -k)) >> 1) & 1) ^ (k & 1) ^ 1);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
