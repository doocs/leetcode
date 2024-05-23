---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3154.Find%20Number%20of%20Ways%20to%20Reach%20the%20K-th%20Stair/README.md
tags:
    - 位运算
    - 记忆化搜索
    - 数学
    - 动态规划
    - 组合数学
---

<!-- problem:start -->

# [3154. 到达第 K 级台阶的方案数](https://leetcode.cn/problems/find-number-of-ways-to-reach-the-k-th-stair)

[English Version](/solution/3100-3199/3154.Find%20Number%20of%20Ways%20to%20Reach%20the%20K-th%20Stair/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你有一个 <strong>非负</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。有一个无限长度的台阶，<strong>最低</strong>&nbsp;一层编号为 0 。</p>

<p>虎老师有一个整数&nbsp;<code>jump</code>&nbsp;，一开始值为 0 。虎老师从台阶 1 开始，虎老师可以使用 <strong>任意</strong>&nbsp;次操作，目标是到达第&nbsp;<code>k</code> 级台阶。假设虎老师位于台阶 <code>i</code> ，一次 <strong>操作</strong> 中，虎老师可以：</p>

<ul>
	<li>向下走一级到&nbsp;<code>i - 1</code>&nbsp;，但该操作&nbsp;<strong>不能</strong>&nbsp;连续使用，如果在台阶第 0 级也不能使用。</li>
	<li>向上走到台阶&nbsp;<code>i + 2<sup>jump</sup></code>&nbsp;处，然后&nbsp;<code>jump</code>&nbsp;变为&nbsp;<code>jump + 1</code>&nbsp;。</li>
</ul>

<p>请你返回虎老师到达台阶 <code>k</code>&nbsp;处的总方案数。</p>

<p><b>注意</b>&nbsp;，虎老师可能到达台阶 <code>k</code>&nbsp;处后，通过一些操作重新回到台阶 <code>k</code>&nbsp;处，这视为不同的方案。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>k = 0</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>2 种到达台阶 0 的方案为：</p>

<ul>
	<li>虎老师从台阶&nbsp;1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
	</ul>
	</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
		<li>执行第二种操作，向上走 2<sup>0</sup>&nbsp;级台阶到台阶 1 。</li>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>k = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>4 种到达台阶 1 的方案为：</p>

<ul>
	<li>虎老师从台阶 1 开始，已经到达台阶 1 。</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
		<li>执行第二种操作，向上走 2<sup>0</sup>&nbsp;级台阶到台阶 1 。</li>
	</ul>
	</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第二种操作，向上走 2<sup>0</sup>&nbsp;级台阶到台阶 2 。</li>
		<li>执行第一种操作，向下走 1 级台阶到台阶 1 。</li>
	</ul>
	</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
		<li>执行第二种操作，向上走&nbsp;2<sup>0</sup>&nbsp;级台阶到台阶 1 。</li>
		<li>执行第一种操作，向下走 1 级台阶到台阶 0 。</li>
		<li>执行第二种操作，向上走 2<sup>1</sup>&nbsp;级台阶到台阶 2 。</li>
		<li>执行第一种操作，向下走&nbsp;1 级台阶到台阶 1 。</li>
	</ul>
	</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\text{dfs}(i, j, \text{jump})$，表示当前位于第 $i$ 级台阶，且进行了 $j$ 次操作 $1$ 和 $\text{jump}$ 次操作 $2$，到达第 $k$ 级台阶的方案数。那么答案就是 $\text{dfs}(1, 0, 0)$。

函数 $\text{dfs}(i, j, \text{jump})$ 的计算过程如下：

-   如果 $i > k + 1$，由于无法连续两次向下走，所以无法再到达第 $k$ 级台阶，返回 $0$；
-   如果 $i = k$，表示已经到达第 $k$ 级台阶，答案初始化为 $1$，然后继续计算；
-   如果 $i > 0$ 且 $j = 0$，表示可以向下走，递归计算 $\text{dfs}(i - 1, 1, \text{jump})$；
-   递归计算 $\text{dfs}(i + 2^{\text{jump}}, 0, \text{jump} + 1)$，累加到答案中。

为了避免重复计算，我们使用记忆化搜索，将已经计算过的状态保存起来。

时间复杂度 $(\log ^2 k)$，空间复杂度 $(\log ^2 k)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToReachStair(self, k: int) -> int:
        @cache
        def dfs(i: int, j: int, jump: int) -> int:
            if i > k + 1:
                return 0
            ans = int(i == k)
            if i > 0 and j == 0:
                ans += dfs(i - 1, 1, jump)
            ans += dfs(i + (1 << jump), 0, jump + 1)
            return ans

        return dfs(1, 0, 0)
```

#### Java

```java
class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private int k;

    public int waysToReachStair(int k) {
        this.k = k;
        return dfs(1, 0, 0);
    }

    private int dfs(int i, int j, int jump) {
        if (i > k + 1) {
            return 0;
        }
        long key = ((long) i << 32) | jump << 1 | j;
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int ans = i == k ? 1 : 0;
        if (i > 0 && j == 0) {
            ans += dfs(i - 1, 1, jump);
        }
        ans += dfs(i + (1 << jump), 0, jump + 1);
        f.put(key, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int waysToReachStair(int k) {
        this->k = k;
        return dfs(1, 0, 0);
    }

private:
    unordered_map<long long, int> f;
    int k;

    int dfs(int i, int j, int jump) {
        if (i > k + 1) {
            return 0;
        }
        long long key = ((long long) i << 32) | jump << 1 | j;
        if (f.contains(key)) {
            return f[key];
        }
        int ans = i == k ? 1 : 0;
        if (i > 0 && j == 0) {
            ans += dfs(i - 1, 1, jump);
        }
        ans += dfs(i + (1 << jump), 0, jump + 1);
        f[key] = ans;
        return ans;
    }
};
```

#### Go

```go
func waysToReachStair(k int) int {
	f := map[int]int{}
	var dfs func(i, j, jump int) int
	dfs = func(i, j, jump int) int {
		if i > k+1 {
			return 0
		}
		key := (i << 32) | jump<<1 | j
		if v, has := f[key]; has {
			return v
		}
		ans := 0
		if i == k {
			ans++
		}
		if i > 0 && j == 0 {
			ans += dfs(i-1, 1, jump)
		}
		ans += dfs(i+(1<<jump), 0, jump+1)
		f[key] = ans
		return ans
	}
	return dfs(1, 0, 0)
}
```

#### TypeScript

```ts
function waysToReachStair(k: number): number {
    const f: Map<bigint, number> = new Map();

    const dfs = (i: number, j: number, jump: number): number => {
        if (i > k + 1) {
            return 0;
        }

        const key: bigint = (BigInt(i) << BigInt(32)) | BigInt(jump << 1) | BigInt(j);
        if (f.has(key)) {
            return f.get(key)!;
        }

        let ans: number = 0;
        if (i === k) {
            ans++;
        }

        if (i > 0 && j === 0) {
            ans += dfs(i - 1, 1, jump);
        }

        ans += dfs(i + (1 << jump), 0, jump + 1);
        f.set(key, ans);
        return ans;
    };

    return dfs(1, 0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
