---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/README.md
rating: 1951
source: 第 13 场双周赛 Q4
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [1259. 不相交的握手 🔒](https://leetcode.cn/problems/handshakes-that-dont-cross)

[English Version](/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>偶数</strong>&nbsp;个人站成一个圆，总人数为&nbsp;<code>num_people</code>&nbsp;。每个人与除自己外的一个人握手，所以总共会有&nbsp;<code>num_people / 2</code>&nbsp;次握手。</p>

<p>将握手的人之间连线，请你返回连线不会相交的握手方案数。</p>

<p>由于结果可能会很大，请你返回答案 <strong>模</strong>&nbsp;<strong><code>10^9+7</code></strong>&nbsp;后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num_people = 2
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/images/5125_example_2.png" style="height: 311px; width: 651px;"></p>

<pre><strong>输入：</strong>num_people = 4
<strong>输出：</strong>2
<strong>解释：</strong>总共有两种方案，第一种方案是 [(1,2),(3,4)] ，第二种方案是 [(2,3),(4,1)] 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/images/5125_example_3.png" style="height: 992px; width: 664px;"></p>

<pre><strong>输入：</strong>num_people = 6
<strong>输出：</strong>5
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>num_people = 8
<strong>输出：</strong>14
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= num_people &lt;= 1000</code></li>
	<li><code>num_people % 2 == 0</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i)$，表示 $i$ 个人的握手方案数。答案为 $dfs(n)$。

函数 $dfs(i)$ 的执行逻辑如下：

-   如果 $i \lt 2$，那么只有一种握手方案，即不握手，返回 $1$。
-   否则，我们可以枚举第一个人与谁握手，记剩余的左边的人数为 $l$，右边的人数为 $r=i-l-2$，那么有 $dfs(i)= \sum_{l=0}^{i-1} dfs(l) \times dfs(r)$。

为了避免重复计算，我们使用记忆化搜索的方法。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为 $numPeople$ 的大小。

<!-- tabs:start -->

```python
class Solution:
    def numberOfWays(self, numPeople: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if i < 2:
                return 1
            ans = 0
            for l in range(0, i, 2):
                r = i - l - 2
                ans += dfs(l) * dfs(r)
                ans %= mod
            return ans

        mod = 10**9 + 7
        return dfs(numPeople)
```

```java
class Solution {
    private int[] f;
    private final int mod = (int) 1e9 + 7;

    public int numberOfWays(int numPeople) {
        f = new int[numPeople + 1];
        return dfs(numPeople);
    }

    private int dfs(int i) {
        if (i < 2) {
            return 1;
        }
        if (f[i] != 0) {
            return f[i];
        }
        for (int l = 0; l < i; l += 2) {
            int r = i - l - 2;
            f[i] = (int) ((f[i] + (1L * dfs(l) * dfs(r) % mod)) % mod);
        }
        return f[i];
    }
}
```

```cpp
class Solution {
public:
    int numberOfWays(int numPeople) {
        const int mod = 1e9 + 7;
        int f[numPeople + 1];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i < 2) {
                return 1;
            }
            if (f[i]) {
                return f[i];
            }
            for (int l = 0; l < i; l += 2) {
                int r = i - l - 2;
                f[i] = (f[i] + 1LL * dfs(l) * dfs(r) % mod) % mod;
            }
            return f[i];
        };
        return dfs(numPeople);
    }
};
```

```go
func numberOfWays(numPeople int) int {
	const mod int = 1e9 + 7
	f := make([]int, numPeople+1)
	var dfs func(int) int
	dfs = func(i int) int {
		if i < 2 {
			return 1
		}
		if f[i] != 0 {
			return f[i]
		}
		for l := 0; l < i; l += 2 {
			r := i - l - 2
			f[i] = (f[i] + dfs(l)*dfs(r)) % mod
		}
		return f[i]
	}
	return dfs(numPeople)
}
```

```ts
function numberOfWays(numPeople: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(numPeople + 1).fill(0);
    const dfs = (i: number): number => {
        if (i < 2) {
            return 1;
        }
        if (f[i] !== 0) {
            return f[i];
        }
        for (let l = 0; l < i; l += 2) {
            const r = i - l - 2;
            f[i] += Number((BigInt(dfs(l)) * BigInt(dfs(r))) % BigInt(mod));
            f[i] %= mod;
        }
        return f[i];
    };
    return dfs(numPeople);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
