# [剑指 Offer II 003. 前 n 个数字二进制中 1 的个数](https://leetcode.cn/problems/w3tCBm)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数 <code>n</code><b>&nbsp;</b>，请计算 <code>0</code> 到 <code>n</code> 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>n =<strong> </strong>2
<strong>输出: </strong>[0,1,1]
<strong>解释: 
</strong>0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入: </strong>n =<strong> </strong>5
<strong>输出: </strong><code>[0,1,1,2,1,2]
</code><span style="white-space: pre-wrap;"><strong>解释:</strong>
</span>0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
3 --&gt; 11
4 --&gt; 100
5 --&gt; 101
</pre>

<p>&nbsp;</p>

<p><strong>说明 :</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶:</strong></p>

<ul>
	<li>给出时间复杂度为&nbsp;<code>O(n*sizeof(integer))</code><strong>&nbsp;</strong>的解答非常容易。但你可以在线性时间&nbsp;<code>O(n)</code><strong>&nbsp;</strong>内用一趟扫描做到吗？</li>
	<li>要求算法的空间复杂度为&nbsp;<code>O(n)</code>&nbsp;。</li>
	<li>你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的&nbsp;<code>__builtin_popcount</code><strong>&nbsp;</strong>）来执行此操作。</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 338&nbsp;题相同：<a href="https://leetcode.cn/problems/counting-bits/">https://leetcode.cn/problems/counting-bits/</a></p>

## 解法

### 方法一：动态规划

我们定义 $f[i]$ 表示整数 $i$ 的二进制表示中 $1$ 的个数。那么对于一个整数 $i$，它的二进制表示中 $1$ 的个数为 $f[i \wedge (i - 1)] + 1$，其中 $i \wedge (i - 1)$ 是将 $i$ 的二进制表示中的最低位的 $1$ 变成 $0$ 之后的数，显然 $i \wedge (i - 1) \lt i$，且 $f[i \wedge (i - 1)]$ 已经被计算出来了，因此我们可以得到状态转移方程：

$$
f[i] = f[i \wedge (i - 1)] + 1
$$

时间复杂度 $O(n)$，其中 $n$ 是题目给定的整数。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countBits(self, n: int) -> List[int]:
        f = [0] * (n + 1)
        for i in range(1, n + 1):
            f[i] = f[i & (i - 1)] + 1
        return f
```

```java
class Solution {
    public int[] countBits(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i & (i - 1)] + 1;
        }
        return f;
    }
}
```

```cpp
class Solution {
public:
    vector<int> countBits(int n) {
        vector<int> f(n + 1);
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i & (i - 1)] + 1;
        }
        return f;
    }
};
```

```go
func countBits(n int) []int {
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = f[i&(i-1)] + 1
	}
	return f
}
```

```ts
function countBits(n: number): number[] {
    const f: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        f[i] = f[i & (i - 1)] + 1;
    }
    return f;
}
```

<!-- tabs:end -->

<!-- end -->
