---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1681.Minimum%20Incompatibility/README.md
rating: 2389
source: 第 218 场周赛 Q4
tags:
    - 位运算
    - 数组
    - 动态规划
    - 状态压缩
---

<!-- problem:start -->

# [1681. 最小不兼容性](https://leetcode.cn/problems/minimum-incompatibility)

[English Version](/solution/1600-1699/1681.Minimum%20Incompatibility/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>​​​ 和一个整数 <code>k</code> 。你需要将这个数组划分到 <code>k</code> 个相同大小的子集中，使得同一个子集里面没有两个相同的元素。</p>

<p>一个子集的 <strong>不兼容性</strong> 是该子集里面最大值和最小值的差。</p>

<p>请你返回将数组分成 <code>k</code> 个子集后，各子集 <strong>不兼容性 </strong>的<strong> 和</strong> 的 <strong>最小值</strong> ，如果无法分成分成 <code>k</code> 个子集，返回 <code>-1</code> 。</p>

<p>子集的定义是数组中一些数字的集合，对数字顺序没有要求。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1,4], k = 2
<b>输出：</b>4
<b>解释：</b>最优的分配是 [1,2] 和 [1,4] 。
不兼容性和为 (2-1) + (4-1) = 4 。
注意到 [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [6,3,8,1,3,1,2,2], k = 4
<b>输出：</b>6
<b>解释：</b>最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。
不兼容性和为 (2-1) + (3-2) + (8-6) + (3-1) = 6 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [5,3,3,6,3,3], k = 3
<b>输出：</b>-1
<b>解释：</b>没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= nums.length <= 16</code></li>
	<li><code>nums.length</code> 能被 <code>k</code> 整除。</li>
	<li><code>1 <= nums[i] <= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 状态压缩 + 动态规划

不妨设划分后每个子集的大小为 $m$，那么 $m=\frac{n}{k}$，其中 $n$ 是数组的长度。

我们可以枚举所有的子集 $i$，其中 $i \in [0, 2^n)$，如果子集 $i$ 的二进制表示中有 $m$ 个 $1$，并且子集 $i$ 中的元素没有重复，那么我们就可以计算出子集 $i$ 的不兼容性，记为 $g[i]$，即 $g[i]=\max_{j \in i} \{nums[j]\} - \min_{j \in i} \{nums[j]\}$。

接下来，我们可以使用动态规划来求解。

我们定义 $f[i]$ 表示当前已经划分的子集状态为 $i$ 时，子集的不兼容性和的最小值。初始时 $f[0]=0$，表示没有任何元素被划分到子集中，其余 $f[i]=+\infty$。

对于状态 $i$，我们找出所有未被划分且不重复的元素，用一个状态 $mask$ 表示，如果状态 $mask$ 中的元素个数大于等于 $m$，那么我们就枚举 $mask$ 的所有子集 $j$，并且满足 $j \subset mask$，那么 $f[i \cup j]=\min \{f[i \cup j], f[i]+g[j]\}$。

最后，如果 $f[2^n-1]=+\infty$，那么说明无法划分成 $k$ 个子集，返回 $-1$，否则返回 $f[2^n-1]$。

时间复杂度 $O(3^n)$，空间复杂度 $O(2^n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumIncompatibility(self, nums: List[int], k: int) -> int:
        n = len(nums)
        m = n // k
        g = [-1] * (1 << n)
        for i in range(1, 1 << n):
            if i.bit_count() != m:
                continue
            s = set()
            mi, mx = 20, 0
            for j, x in enumerate(nums):
                if i >> j & 1:
                    if x in s:
                        break
                    s.add(x)
                    mi = min(mi, x)
                    mx = max(mx, x)
            if len(s) == m:
                g[i] = mx - mi
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1 << n):
            if f[i] == inf:
                continue
            s = set()
            mask = 0
            for j, x in enumerate(nums):
                if (i >> j & 1) == 0 and x not in s:
                    s.add(x)
                    mask |= 1 << j
            if len(s) < m:
                continue
            j = mask
            while j:
                if g[j] != -1:
                    f[i | j] = min(f[i | j], f[i] + g[j])
                j = (j - 1) & mask
        return f[-1] if f[-1] != inf else -1
```

```java
class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int m = n / k;
        int[] g = new int[1 << n];
        Arrays.fill(g, -1);
        for (int i = 1; i < 1 << n; ++i) {
            if (Integer.bitCount(i) != m) {
                continue;
            }
            Set<Integer> s = new HashSet<>();
            int mi = 20, mx = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    if (!s.add(nums[j])) {
                        break;
                    }
                    mi = Math.min(mi, nums[j]);
                    mx = Math.max(mx, nums[j]);
                }
            }
            if (s.size() == m) {
                g[i] = mx - mi;
            }
        }
        int[] f = new int[1 << n];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            if (f[i] == inf) {
                continue;
            }
            Set<Integer> s = new HashSet<>();
            int mask = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 0 && !s.contains(nums[j])) {
                    s.add(nums[j]);
                    mask |= 1 << j;
                }
            }
            if (s.size() < m) {
                continue;
            }
            for (int j = mask; j > 0; j = (j - 1) & mask) {
                if (g[j] != -1) {
                    f[i | j] = Math.min(f[i | j], f[i] + g[j]);
                }
            }
        }
        return f[(1 << n) - 1] == inf ? -1 : f[(1 << n) - 1];
    }
}
```

```cpp
class Solution {
public:
    int minimumIncompatibility(vector<int>& nums, int k) {
        int n = nums.size();
        int m = n / k;
        int g[1 << n];
        memset(g, -1, sizeof(g));
        for (int i = 1; i < 1 << n; ++i) {
            if (__builtin_popcount(i) != m) {
                continue;
            }
            unordered_set<int> s;
            int mi = 20, mx = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    if (s.count(nums[j])) {
                        break;
                    }
                    s.insert(nums[j]);
                    mi = min(mi, nums[j]);
                    mx = max(mx, nums[j]);
                }
            }
            if (s.size() == m) {
                g[i] = mx - mi;
            }
        }
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            if (f[i] == 0x3f3f3f3f) {
                continue;
            }
            unordered_set<int> s;
            int mask = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 0 && !s.count(nums[j])) {
                    s.insert(nums[j]);
                    mask |= 1 << j;
                }
            }
            if (s.size() < m) {
                continue;
            }
            for (int j = mask; j; j = (j - 1) & mask) {
                if (g[j] != -1) {
                    f[i | j] = min(f[i | j], f[i] + g[j]);
                }
            }
        }
        return f[(1 << n) - 1] == 0x3f3f3f3f ? -1 : f[(1 << n) - 1];
    }
};
```

```go
func minimumIncompatibility(nums []int, k int) int {
	n := len(nums)
	m := n / k
	const inf = 1 << 30
	f := make([]int, 1<<n)
	g := make([]int, 1<<n)
	for i := range g {
		f[i] = inf
		g[i] = -1
	}
	for i := 1; i < 1<<n; i++ {
		if bits.OnesCount(uint(i)) != m {
			continue
		}
		s := map[int]struct{}{}
		mi, mx := 20, 0
		for j, x := range nums {
			if i>>j&1 == 1 {
				if _, ok := s[x]; ok {
					break
				}
				s[x] = struct{}{}
				mi = min(mi, x)
				mx = max(mx, x)
			}
		}
		if len(s) == m {
			g[i] = mx - mi
		}
	}
	f[0] = 0
	for i := 0; i < 1<<n; i++ {
		if f[i] == inf {
			continue
		}
		s := map[int]struct{}{}
		mask := 0
		for j, x := range nums {
			if _, ok := s[x]; !ok && i>>j&1 == 0 {
				s[x] = struct{}{}
				mask |= 1 << j
			}
		}
		if len(s) < m {
			continue
		}
		for j := mask; j > 0; j = (j - 1) & mask {
			if g[j] != -1 {
				f[i|j] = min(f[i|j], f[i]+g[j])
			}
		}
	}
	if f[1<<n-1] == inf {
		return -1
	}
	return f[1<<n-1]
}
```

```ts
function minimumIncompatibility(nums: number[], k: number): number {
    const n = nums.length;
    const m = Math.floor(n / k);
    const g: number[] = Array(1 << n).fill(-1);
    for (let i = 1; i < 1 << n; ++i) {
        if (bitCount(i) !== m) {
            continue;
        }
        const s: Set<number> = new Set();
        let [mi, mx] = [20, 0];
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                if (s.has(nums[j])) {
                    break;
                }
                s.add(nums[j]);
                mi = Math.min(mi, nums[j]);
                mx = Math.max(mx, nums[j]);
            }
        }
        if (s.size === m) {
            g[i] = mx - mi;
        }
    }
    const inf = 1e9;
    const f: number[] = Array(1 << n).fill(inf);
    f[0] = 0;
    for (let i = 0; i < 1 << n; ++i) {
        if (f[i] === inf) {
            continue;
        }
        const s: Set<number> = new Set();
        let mask = 0;
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 0 && !s.has(nums[j])) {
                s.add(nums[j]);
                mask |= 1 << j;
            }
        }
        if (s.size < m) {
            continue;
        }
        for (let j = mask; j; j = (j - 1) & mask) {
            if (g[j] !== -1) {
                f[i | j] = Math.min(f[i | j], f[i] + g[j]);
            }
        }
    }
    return f[(1 << n) - 1] === inf ? -1 : f[(1 << n) - 1];
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

```cs
public class Solution {
    public int MinimumIncompatibility(int[] nums, int k) {
        int n = nums.Length;
        int m = n / k;
        int[] g = new int[1 << n];
        Array.Fill(g, -1);
        for (int i = 1; i < 1 << n; ++i) {
            if (bitCount(i) != m) {
                continue;
            }
            HashSet<int> s = new();
            int mi = 20, mx = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    if (s.Contains(nums[j])) {
                        break;
                    }
                    s.Add(nums[j]);
                    mi = Math.Min(mi, nums[j]);
                    mx = Math.Max(mx, nums[j]);
                }
            }
            if (s.Count == m) {
                g[i] = mx - mi;
            }
        }
        int[] f = new int[1 << n];
        int inf = 1 << 30;
        Array.Fill(f, inf);
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            if (f[i] == inf) {
                continue;
            }
            HashSet<int> s = new();
            int mask = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 0 && !s.Contains(nums[j])) {
                    s.Add(nums[j]);
                    mask |= 1 << j;
                }
            }
            if (s.Count < m) {
                continue;
            }
            for (int j = mask; j > 0; j = (j - 1) & mask) {
                if (g[j] != -1) {
                    f[i | j] = Math.Min(f[i | j], f[i] + g[j]);
                }
            }
        }
        return f[(1 << n) - 1] == inf ? -1 : f[(1 << n) - 1];
    }

    private int bitCount(int x) {
        int cnt = 0;
        while (x > 0) {
            x &= x - 1;
            ++cnt;
        }
        return cnt;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def minimumIncompatibility(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(mask):
            if mask == (1 << n) - 1:
                return 0
            d = {v: i for i, v in enumerate(nums) if (mask >> i & 1) == 0}
            ans = inf
            if len(d) < m:
                return ans
            for vs in combinations(d.keys(), m):
                nxt = mask
                for v in vs:
                    nxt |= 1 << d[v]
                ans = min(ans, max(vs) - min(vs) + dfs(nxt))
            return ans

        n = len(nums)
        m = n // k
        ans = dfs(0)
        dfs.cache_clear()
        return ans if ans < inf else -1
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
