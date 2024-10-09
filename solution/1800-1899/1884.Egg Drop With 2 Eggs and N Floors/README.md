---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1884.Egg%20Drop%20With%202%20Eggs%20and%20N%20Floors/README.md
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [1884. 鸡蛋掉落-两枚鸡蛋](https://leetcode.cn/problems/egg-drop-with-2-eggs-and-n-floors)

[English Version](/solution/1800-1899/1884.Egg%20Drop%20With%202%20Eggs%20and%20N%20Floors/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你 <strong>2&nbsp;枚相同 </strong>的鸡蛋，和一栋从第 <code>1</code>&nbsp;层到第 <code>n</code> 层共有 <code>n</code> 层楼的建筑。</p>

<p>已知存在楼层 <code>f</code> ，满足&nbsp;<code>0 &lt;= f &lt;= n</code> ，任何从 <strong>高于 </strong><code>f</code> 的楼层落下的鸡蛋都<strong> 会碎 </strong>，从 <strong><code>f</code> 楼层或比它低 </strong>的楼层落下的鸡蛋都 <strong>不会碎 </strong>。</p>

<p>每次操作，你可以取一枚<strong> 没有碎</strong> 的鸡蛋并把它从任一楼层 <code>x</code> 扔下（满足&nbsp;<code>1 &lt;= x &lt;= n</code>）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中<strong> 重复使用 </strong>这枚鸡蛋。</p>

<p>请你计算并返回要确定 <code>f</code> <strong>确切的值 </strong>的 <strong>最小操作次数</strong> 是多少？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>2
<strong>解释：</strong>我们可以将第一枚鸡蛋从 1 楼扔下，然后将第二枚从 2 楼扔下。
如果第一枚鸡蛋碎了，可知 f = 0；
如果第二枚鸡蛋碎了，但第一枚没碎，可知 f = 1；
否则，当两个鸡蛋都没碎时，可知 f = 2。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 100
<strong>输出：</strong>14
<strong>解释：
</strong>一种最优的策略是：
- 将第一枚鸡蛋从 9 楼扔下。如果碎了，那么 f 在 0 和 8 之间。将第二枚从 1 楼扔下，然后每扔一次上一层楼，在 8 次内找到 f 。总操作次数 = 1 + 8 = 9 。
- 如果第一枚鸡蛋没有碎，那么再把第一枚鸡蛋从 22 层扔下。如果碎了，那么 f 在 9 和 21 之间。将第二枚鸡蛋从 10 楼扔下，然后每扔一次上一层楼，在 12 次内找到 f 。总操作次数 = 2 + 12 = 14 。
- 如果第一枚鸡蛋没有再次碎掉，则按照类似的方法从 34, 45, 55, 64, 72, 79, 85, 90, 94, 97, 99 和 100 楼分别扔下第一枚鸡蛋。
不管结果如何，最多需要扔 14 次来确定 f 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示有两枚鸡蛋，在 $i$ 层楼中确定 $f$ 的最小操作次数。初始时 $f[0] = 0$，其余 $f[i] = +\infty$。答案为 $f[n]$。

考虑 $f[i]$，我们可以枚举第一枚鸡蛋从第 $j$ 层楼扔下，其中 $1 \leq j \leq i$，此时有两种情况：

-   鸡蛋碎了，此时我们剩余一枚鸡蛋，需要在 $j - 1$ 层楼中确定 $f$，这需要 $j - 1$ 次操作，因此总操作次数为 $1 + (j - 1)$；
-   鸡蛋没碎，此时我们剩余两枚鸡蛋，需要在 $i - j$ 层楼中确定 $f$，这需要 $f[i - j]$ 次操作，因此总操作次数为 $1 + f[i - j]$。

综上，我们可以得到状态转移方程：

$$
f[i] = \min_{1 \leq j \leq i} \{1 + \max(j - 1, f[i - j])\}
$$

最后，我们返回 $f[n]$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为楼层数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoEggDrop(self, n: int) -> int:
        f = [0] + [inf] * n
        for i in range(1, n + 1):
            for j in range(1, i + 1):
                f[i] = min(f[i], 1 + max(j - 1, f[i - j]))
        return f[n]
```

#### Java

```java
class Solution {
    public int twoEggDrop(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f, 1 << 29);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] = Math.min(f[i], 1 + Math.max(j - 1, f[i - j]));
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int twoEggDrop(int n) {
        int f[n + 1];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] = min(f[i], 1 + max(j - 1, f[i - j]));
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func twoEggDrop(n int) int {
	f := make([]int, n+1)
	for i := range f {
		f[i] = 1 << 29
	}
	f[0] = 0
	for i := 1; i <= n; i++ {
		for j := 1; j <= i; j++ {
			f[i] = min(f[i], 1+max(j-1, f[i-j]))
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function twoEggDrop(n: number): number {
    const f: number[] = Array(n + 1).fill(Infinity);
    f[0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= i; ++j) {
            f[i] = Math.min(f[i], 1 + Math.max(j - 1, f[i - j]));
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
