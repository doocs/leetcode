---
comments: true
difficulty: 困难
rating: 2735
source: 第 393 场周赛 Q4
tags:
    - 位运算
    - 线段树
    - 队列
    - 数组
    - 二分查找
    - 动态规划
---

<!-- problem:start -->

# [3117. 划分数组得到最小的值之和](https://leetcode.cn/problems/minimum-sum-of-values-by-dividing-array)

[English Version](/solution/3100-3199/3117.Minimum%20Sum%20of%20Values%20by%20Dividing%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个数组 <code>nums</code> 和 <code>andValues</code>，长度分别为 <code>n</code> 和 <code>m</code>。</p>

<p>数组的 <strong>值 </strong>等于该数组的 <strong>最后一个 </strong>元素。</p>

<p>你需要将 <code>nums</code> 划分为 <code>m</code> 个 <strong>不相交的连续 </strong><span data-keyword="subarray-nonempty">子数组</span>，对于第 <code>i<sup>th</sup></code> 个子数组 <code>[l<sub>i</sub>, r<sub>i</sub>]</code>，子数组元素的按位&nbsp;<code>AND</code>&nbsp;运算结果等于 <code>andValues[i]</code>，换句话说，对所有的 <code>1 &lt;= i &lt;= m</code>，<code>nums[l<sub>i</sub>] &amp; nums[l<sub>i</sub> + 1] &amp; ... &amp; nums[r<sub>i</sub>] == andValues[i]</code> ，其中 <code>&amp;</code> 表示按位&nbsp;<code>AND</code>&nbsp;运算符。</p>

<p>返回将 <code>nums</code> 划分为 <code>m</code> 个子数组所能得到的可能的 <strong>最小 </strong>子数组 <strong>值</strong> 之和。如果无法完成这样的划分，则返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,4,3,3,2], andValues = [0,3,3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>唯一可能的划分方法为：</p>

<ol>
	<li><code>[1,4]</code> 因为 <code>1 &amp; 4 == 0</code></li>
	<li><code>[3]</code> 因为单元素子数组的按位 <code>AND</code> 结果就是该元素本身</li>
	<li><code>[3]</code> 因为单元素子数组的按位 <code>AND</code> 结果就是该元素本身</li>
	<li><code>[2]</code> 因为单元素子数组的按位 <code>AND</code> 结果就是该元素本身</li>
</ol>

<p>这些子数组的值之和为 <code>4 + 3 + 3 + 2 = 12</code></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,3,5,7,7,7,5], andValues = [0,7,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">17</span></p>

<p><strong>解释：</strong></p>

<p>划分 <code>nums</code> 的三种方式为：</p>

<ol>
	<li><code>[[2,3,5],[7,7,7],[5]]</code> 其中子数组的值之和为 <code>5 + 7 + 5 = 17</code></li>
	<li><code>[[2,3,5,7],[7,7],[5]]</code> 其中子数组的值之和为 <code>7 + 7 + 5 = 19</code></li>
	<li><code>[[2,3,5,7,7],[7],[5]]</code> 其中子数组的值之和为 <code>7 + 7 + 5 = 19</code></li>
</ol>

<p>子数组值之和的最小可能值为 <code>17</code></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4], andValues = [2]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>整个数组 <code>nums</code> 的按位 <code>AND</code> 结果为 <code>0</code>。由于无法将 <code>nums</code> 划分为单个子数组使得元素的按位 <code>AND</code> 结果为 <code>2</code>，因此返回 <code>-1</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m == andValues.length &lt;= min(n, 10)</code></li>
	<li><code>1 &lt;= nums[i] &lt; 10<sup>5</sup></code></li>
	<li><code>0 &lt;= andValues[j] &lt; 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 需要把数组划成 $m$ 段，使第 $j$ 段的按位与恰为 $\textit{andValues}[j]$，并最小化各段右端元素之和。枚举所有分割点是指数级的。
>
> 按位与只减不增，一旦当前段的与值已经小于目标便可剪枝。状态可由「当前位置、已完成段数、当前段累积与」唯一确定，且与值种类约为 $O(\log M)$。
>
> 因此记忆化 $dfs(i,j,a)$：将 $a$ 与 $nums[i]$ 相与后，或继续延长本段，或在与值恰好等于目标时截断并加上 $nums[i]$。剩余长度不足或越界则返回无穷大。

<!-- thinking:end -->

我们设计一个函数 $dfs(i, j, a)$，表示从第 $i$ 个元素开始，当前已经划分了 $j$ 个子数组，且当前待划分的子数组的按位与结果为 $a$ 的情况下，所能得到的可能的最小子数组值之和。那么答案就是 $dfs(0, 0, -1)$。

函数 $dfs(i, j, a)$ 的执行过程如下：

- 如果 $n - i < m - j$，那么说明剩下的元素不足以划分出 $m - j$ 个子数组，返回 $+\infty$。
- 如果 $j = m$，那么说明已经划分出了 $m$ 个子数组，此时判断 $i = n$ 是否成立，如果成立返回 $0$，否则返回 $+\infty$。
- 否则，我们将 $a$ 与 $nums[i]$ 进行按位与操作，得到新的 $a$。如果 $a < andValues[j]$，那么说明当前待划分的子数组的按位与结果不满足要求，返回 $+\infty$。否则，我们有两种选择：
    - 不划分当前元素，即 $dfs(i + 1, j, a)$。
    - 划分当前元素，即 $dfs(i + 1, j + 1, -1) + nums[i]$。
- 返回上述两种选择的最小值。

为了避免重复计算，我们使用记忆化搜索的方法，将 $dfs(i, j, a)$ 的结果存储在一个哈希表中。

时间复杂度 $O(n \times m \times \log M)$，空间复杂度 $O(n \times m \times \log M)$。其中 $n$ 和 $m$ 分别是数组 $nums$ 和 $andValues$ 的长度；而 $M$ 是数组 $nums$ 中的最大值，本题中 $M \leq 10^5$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumValueSum(self, nums: List[int], andValues: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, a: int) -> int:
            if n - i < m - j:
                return inf
            if j == m:
                return 0 if i == n else inf
            a &= nums[i]
            if a < andValues[j]:
                return inf
            ans = dfs(i + 1, j, a)
            if a == andValues[j]:
                ans = min(ans, dfs(i + 1, j + 1, -1) + nums[i])
            return ans

        n, m = len(nums), len(andValues)
        ans = dfs(0, 0, -1)
        return ans if ans < inf else -1
```

#### Java

```java
class Solution {
    private int[] nums;
    private int[] andValues;
    private final int inf = 1 << 29;
    private Map<Long, Integer> f = new HashMap<>();

    public int minimumValueSum(int[] nums, int[] andValues) {
        this.nums = nums;
        this.andValues = andValues;
        int ans = dfs(0, 0, -1);
        return ans >= inf ? -1 : ans;
    }

    private int dfs(int i, int j, int a) {
        if (nums.length - i < andValues.length - j) {
            return inf;
        }
        if (j == andValues.length) {
            return i == nums.length ? 0 : inf;
        }
        a &= nums[i];
        if (a < andValues[j]) {
            return inf;
        }
        long key = (long) i << 36 | (long) j << 32 | a;
        if (f.containsKey(key)) {
            return f.get(key);
        }

        int ans = dfs(i + 1, j, a);
        if (a == andValues[j]) {
            ans = Math.min(ans, dfs(i + 1, j + 1, -1) + nums[i]);
        }
        f.put(key, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumValueSum(vector<int>& nums, vector<int>& andValues) {
        this->nums = nums;
        this->andValues = andValues;
        n = nums.size();
        m = andValues.size();
        int ans = dfs(0, 0, -1);
        return ans >= inf ? -1 : ans;
    }

private:
    vector<int> nums;
    vector<int> andValues;
    int n;
    int m;
    const int inf = 1 << 29;
    unordered_map<long long, int> f;

    int dfs(int i, int j, int a) {
        if (n - i < m - j) {
            return inf;
        }
        if (j == m) {
            return i == n ? 0 : inf;
        }
        a &= nums[i];
        if (a < andValues[j]) {
            return inf;
        }
        long long key = (long long) i << 36 | (long long) j << 32 | a;
        if (f.contains(key)) {
            return f[key];
        }
        int ans = dfs(i + 1, j, a);
        if (a == andValues[j]) {
            ans = min(ans, dfs(i + 1, j + 1, -1) + nums[i]);
        }
        return f[key] = ans;
    }
};
```

#### Go

```go
func minimumValueSum(nums []int, andValues []int) int {
	n, m := len(nums), len(andValues)
	f := map[int]int{}
	const inf int = 1 << 29
	var dfs func(i, j, a int) int
	dfs = func(i, j, a int) int {
		if n-i < m-j {
			return inf
		}
		if j == m {
			if i == n {
				return 0
			}
			return inf
		}
		a &= nums[i]
		if a < andValues[j] {
			return inf
		}
		key := i<<36 | j<<32 | a
		if v, ok := f[key]; ok {
			return v
		}
		ans := dfs(i+1, j, a)
		if a == andValues[j] {
			ans = min(ans, dfs(i+1, j+1, -1)+nums[i])
		}
		f[key] = ans
		return ans
	}
	if ans := dfs(0, 0, -1); ans < inf {
		return ans
	}
	return -1
}
```

#### TypeScript

```ts
function minimumValueSum(nums: number[], andValues: number[]): number {
    const [n, m] = [nums.length, andValues.length];
    const f: Map<bigint, number> = new Map();
    const dfs = (i: number, j: number, a: number): number => {
        if (n - i < m - j) {
            return Infinity;
        }
        if (j === m) {
            return i === n ? 0 : Infinity;
        }
        a &= nums[i];
        if (a < andValues[j]) {
            return Infinity;
        }
        const key = (BigInt(i) << 36n) | (BigInt(j) << 32n) | BigInt(a);
        if (f.has(key)) {
            return f.get(key)!;
        }
        let ans = dfs(i + 1, j, a);
        if (a === andValues[j]) {
            ans = Math.min(ans, dfs(i + 1, j + 1, -1) + nums[i]);
        }
        f.set(key, ans);
        return ans;
    };
    const ans = dfs(0, 0, -1);
    return ans >= Infinity ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 要把数组切成 $m$ 段，使第 $j$ 段的按位与等于 $\textit{andValues}[j]$，并最小化各段末元素之和。$n$ 可达 $10^4$，枚举全部切点是指数级的。
>
> 状态是当前位置、已完成段数和当前段的累积与。延长本段的调用总是先发生，从下标 $0$ 走到 $n$ 的链长度就是 $n$。$n=2000$ 时 Python 抛出 RecursionError，$n=10^4$ 时 Java 栈溢出，Node 在 $n=8000$ 时超出调用栈。
>
> 按位与只减不增。固定右端点时，不同起点的与值只有 $O(\log M)$ 种，之后的最优代价只跟段号和当前与值有关。
>
> 因此从左到右只保留一层映射，键是 $(j,a)$，值是到达该状态的最小代价。$a=-1$ 表示新段还没有元素。并入 $nums[i]$ 后可以继续留在第 $j$ 段；与值恰好等于目标时，也可以在此截断，把 $nums[i]$ 计入答案并打开下一段。最后一段只能在数组末尾结束。

<!-- thinking:end -->

我们从左到右扫描数组，只保留当前这一层的状态。状态 $(j, a)$ 表示已经划完 $j$ 段，当前段在并入下一个元素之前的按位与为 $a$，对应的值是到达该状态的最小末元素之和。$a = -1$ 表示当前段还是空的。初始只有 $(0, -1)$，代价为 $0$。答案是扫完整个数组之后 $(m, -1)$ 的代价。

处理 $nums[i]$ 时，对每个状态 $(j, a)$：

- 若 $n - i < m - j$，剩下的元素不够再划出 $m - j$ 段，丢掉该状态。
- 令 $a'$ 为 $a$ 与 $nums[i]$ 的按位与。空段的 $-1$ 与任何数相与，结果就是这个数本身。若 $a' < \textit{andValues}[j]$，以后的按位与只会更小，不可能再等于目标，丢掉该状态。
- 否则可以把 $nums[i]$ 留在当前段，转移到 $(j, a')$，代价不变。
- 若 $a' = \textit{andValues}[j]$，还可以在下标 $i$ 结束当前段，代价加上 $nums[i]$。当 $j + 1 < m$ 时，新状态是 $(j + 1, -1)$；当 $j + 1 = m$ 时，只有 $i = n - 1$ 才把整个数组划完，记为 $(m, -1)$。

同一状态只保留更小的代价。若不存在 $(m, -1)$，返回 $-1$。

时间复杂度 $O(n \times m \times \log M)$，空间复杂度 $O(m \times \log M)$。其中 $n$ 和 $m$ 分别是数组 $nums$ 和 $andValues$ 的长度，而 $M$ 是数组 $nums$ 中的最大值，本题中 $M \leq 10^5$。固定右端点时，不同起点的按位与至多变化 $O(\log M)$ 次，所以每一层的状态数是 $O(m \times \log M)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumValueSum(self, nums: List[int], andValues: List[int]) -> int:
        n, m = len(nums), len(andValues)
        f = {(0, -1): 0}
        for i, x in enumerate(nums):
            g = {}
            for (j, a), cost in f.items():
                if n - i < m - j:
                    continue
                na = a & x
                if na < andValues[j]:
                    continue
                g[j, na] = min(g.get((j, na), inf), cost)
                if na == andValues[j]:
                    t = cost + x
                    if j + 1 == m:
                        if i == n - 1:
                            g[m, -1] = min(g.get((m, -1), inf), t)
                    else:
                        g[j + 1, -1] = min(g.get((j + 1, -1), inf), t)
            f = g
        ans = f.get((m, -1), inf)
        return ans if ans < inf else -1
```

#### Java

```java
class Solution {
    public int minimumValueSum(int[] nums, int[] andValues) {
        final int inf = 1 << 29;
        int n = nums.length, m = andValues.length;
        final int stride = 100001;
        Map<Integer, Integer> f = new HashMap<>();
        f.put(0, 0);
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> g = new HashMap<>();
            for (var e : f.entrySet()) {
                int key = e.getKey(), cost = e.getValue();
                int j = key / stride;
                int a = key % stride - 1;
                if (n - i < m - j) {
                    continue;
                }
                int na = a & nums[i];
                if (na < andValues[j]) {
                    continue;
                }
                int nk = j * stride + na + 1;
                g.put(nk, Math.min(g.getOrDefault(nk, inf), cost));
                if (na == andValues[j]) {
                    int t = cost + nums[i];
                    if (j + 1 == m) {
                        if (i == n - 1) {
                            int done = m * stride;
                            g.put(done, Math.min(g.getOrDefault(done, inf), t));
                        }
                    } else {
                        int nk2 = (j + 1) * stride;
                        g.put(nk2, Math.min(g.getOrDefault(nk2, inf), t));
                    }
                }
            }
            f = g;
        }
        int ans = f.getOrDefault(m * stride, inf);
        return ans >= inf ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumValueSum(vector<int>& nums, vector<int>& andValues) {
        int n = nums.size(), m = andValues.size();
        const int stride = 100001;
        unordered_map<int, int> f;
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            unordered_map<int, int> g;
            for (auto& [key, cost] : f) {
                int j = key / stride;
                int a = key % stride - 1;
                if (n - i < m - j) {
                    continue;
                }
                int na = a & nums[i];
                if (na < andValues[j]) {
                    continue;
                }
                int nk = j * stride + na + 1;
                if (!g.contains(nk) || g[nk] > cost) {
                    g[nk] = cost;
                }
                if (na == andValues[j]) {
                    int t = cost + nums[i];
                    if (j + 1 == m) {
                        if (i == n - 1) {
                            int done = m * stride;
                            if (!g.contains(done) || g[done] > t) {
                                g[done] = t;
                            }
                        }
                    } else {
                        int nk2 = (j + 1) * stride;
                        if (!g.contains(nk2) || g[nk2] > t) {
                            g[nk2] = t;
                        }
                    }
                }
            }
            f.swap(g);
        }
        int done = m * stride;
        return f.contains(done) ? f[done] : -1;
    }
};
```

#### Go

```go
func minimumValueSum(nums []int, andValues []int) int {
	n, m := len(nums), len(andValues)
	const stride = 100001
	f := map[int]int{0: 0}
	for i, x := range nums {
		g := map[int]int{}
		for key, cost := range f {
			j := key / stride
			a := key%stride - 1
			if n-i < m-j {
				continue
			}
			na := a & x
			if na < andValues[j] {
				continue
			}
			nk := j*stride + na + 1
			if v, ok := g[nk]; !ok || v > cost {
				g[nk] = cost
			}
			if na == andValues[j] {
				t := cost + x
				if j+1 == m {
					if i == n-1 {
						done := m * stride
						if v, ok := g[done]; !ok || v > t {
							g[done] = t
						}
					}
				} else {
					nk2 := (j + 1) * stride
					if v, ok := g[nk2]; !ok || v > t {
						g[nk2] = t
					}
				}
			}
		}
		f = g
	}
	if ans, ok := f[m*stride]; ok {
		return ans
	}
	return -1
}
```

#### TypeScript

```ts
function minimumValueSum(nums: number[], andValues: number[]): number {
    const n = nums.length;
    const m = andValues.length;
    const inf = 1 << 29;
    const stride = 100001;
    let f = new Map<number, number>();
    f.set(0, 0);
    for (let i = 0; i < n; ++i) {
        const g = new Map<number, number>();
        for (const [key, cost] of f) {
            const j = (key / stride) | 0;
            const a = (key % stride) - 1;
            if (n - i < m - j) {
                continue;
            }
            const na = a & nums[i];
            if (na < andValues[j]) {
                continue;
            }
            const nk = j * stride + na + 1;
            g.set(nk, Math.min(g.get(nk) ?? inf, cost));
            if (na === andValues[j]) {
                const t = cost + nums[i];
                if (j + 1 === m) {
                    if (i === n - 1) {
                        const done = m * stride;
                        g.set(done, Math.min(g.get(done) ?? inf, t));
                    }
                } else {
                    const nk2 = (j + 1) * stride;
                    g.set(nk2, Math.min(g.get(nk2) ?? inf, t));
                }
            }
        }
        f = g;
    }
    const ans = f.get(m * stride) ?? inf;
    return ans >= inf ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
