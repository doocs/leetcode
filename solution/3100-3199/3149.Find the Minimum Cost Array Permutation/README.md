---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/README.md
---

<!-- problem:start -->

# [3149. 找出分数最低的排列](https://leetcode.cn/problems/find-the-minimum-cost-array-permutation)

[English Version](/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>nums</code> ，它是 <code>[0, 1, 2, ..., n - 1]</code> 的一个<span data-keyword="permutation">排列</span> 。对于任意一个 <code>[0, 1, 2, ..., n - 1]</code> 的排列 <code>perm</code> ，其 <strong>分数 </strong>定义为：</p>

<p><code>score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|</code></p>

<p>返回具有<strong> 最低</strong> 分数的排列 <code>perm</code> 。如果存在多个满足题意且分数相等的排列，则返回其中<span data-keyword="lexicographically-smaller-array">字典序最小</span>的一个。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,0,2]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,1,2]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example0gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>字典序最小且分数最低的排列是 <code>[0,1,2]</code>。这个排列的分数是 <code>|0 - 0| + |1 - 2| + |2 - 1| = 2</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [0,2,1]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,2,1]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example1gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>字典序最小且分数最低的排列是 <code>[0,2,1]</code>。这个排列的分数是 <code>|0 - 1| + |2 - 2| + |1 - 0| = 2</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 14</code></li>
	<li><code>nums</code> 是 <code>[0, 1, 2, ..., n - 1]</code> 的一个排列。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们注意到，对于任意一个排列 $\text{perm}$，把它循环向左移动任意次，得到的排列分数依然是相同的。由于题目要求返回字典序最小的排列，因此我们可以确定排列的第一个元素一定是 $0$。

另外，由于题目的数据范围不超过 $14$，我们可以考虑使用状态压缩的方法，来表示当前排列选取的数字集合。我们用一个长度为 $n$ 的二进制数 $\text{mask}$ 来表示当前排列选取的数字集合，其中 $\text{mask}$ 的第 $i$ 位为 $1$ 表示数字 $i$ 已经被选取，为 $0$ 表示数字 $i$ 还未被选取。

我们设计一个函数 $\text{dfs}(\text{mask}, \text{pre})$，表示当前排列选取的数字集合为 $\text{mask}$，且最后一个选取的数字为 $\text{pre}$ 时，得到的排列的最小分数。初始时我们将数字 $0$ 加入到排列中。

函数 $\text{dfs}(\text{mask}, \text{pre})$ 的计算过程如下：

-   如果 $\text{mask}$ 的二进制表示中 $1$ 的个数为 $n$，即 $\text{mask} = 2^n - 1$，表示所有数字都已经被选取，此时返回 $|\text{pre} - \text{nums}[0]|$；
-   否则，我们枚举下一个选取的数字 $\text{cur}$，如果数字 $\text{cur}$ 还未被选取，那么我们可以将数字 $\text{cur}$ 加入到排列中，此时排列的分数为 $|\text{pre} - \text{nums}[\text{cur}]| + \text{dfs}(\text{mask} \, | \, 1 << \text{cur}, \text{cur})$，我们需要取所有 $\text{cur}$ 中分数的最小值。

最后，我们利用一个函数 $\text{g}(\text{mask}, \text{pre})$ 来构造得到最小分数的排列。我们首先将数字 $\text{pre}$ 加入到排列中，然后枚举下一个选取的数字 $\text{cur}$，如果数字 $\text{cur}$ 还未被选取，且满足 $|\text{pre} - \text{nums}[\text{cur}]| + \text{dfs}(\text{mask} \, | \, 1 << \text{cur}, \text{cur})$ 的值等于 $\text{dfs}(\text{mask}, \text{pre})$，那么我们就可以将数字 $\text{cur}$ 加入到排列中。

时间复杂度 $(n^2 \times 2^n)$，空间复杂度 $O(n \times 2^n)$。其中 $n$ 为数组 $\text{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPermutation(self, nums: List[int]) -> List[int]:
        @cache
        def dfs(mask: int, pre: int) -> int:
            if mask == (1 << n) - 1:
                return abs(pre - nums[0])
            res = inf
            for cur in range(1, n):
                if mask >> cur & 1 ^ 1:
                    res = min(res, abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur))
            return res

        def g(mask: int, pre: int):
            ans.append(pre)
            if mask == (1 << n) - 1:
                return
            res = dfs(mask, pre)
            for cur in range(1, n):
                if mask >> cur & 1 ^ 1:
                    if abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur) == res:
                        g(mask | 1 << cur, cur)
                        break

        n = len(nums)
        ans = []
        g(1, 0)
        return ans
```

#### Java

```java
class Solution {
    private Integer[][] f;
    private int[] nums;
    private int[] ans;
    private int n;

    public int[] findPermutation(int[] nums) {
        n = nums.length;
        ans = new int[n];
        this.nums = nums;
        f = new Integer[1 << n][n];
        g(1, 0, 0);
        return ans;
    }

    private int dfs(int mask, int pre) {
        if (mask == (1 << n) - 1) {
            return Math.abs(pre - nums[0]);
        }
        if (f[mask][pre] != null) {
            return f[mask][pre];
        }
        int res = Integer.MAX_VALUE;
        for (int cur = 1; cur < n; ++cur) {
            if ((mask >> cur & 1) == 0) {
                res = Math.min(res, Math.abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur));
            }
        }
        return f[mask][pre] = res;
    }

    private void g(int mask, int pre, int k) {
        ans[k] = pre;
        if (mask == (1 << n) - 1) {
            return;
        }
        int res = dfs(mask, pre);
        for (int cur = 1; cur < n; ++cur) {
            if ((mask >> cur & 1) == 0) {
                if (Math.abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur) == res) {
                    g(mask | 1 << cur, cur, k + 1);
                    break;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findPermutation(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans;
        int f[1 << n][n];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int mask, int pre) {
            if (mask == (1 << n) - 1) {
                return abs(pre - nums[0]);
            }
            int* res = &f[mask][pre];
            if (*res != -1) {
                return *res;
            }
            *res = INT_MAX;
            for (int cur = 1; cur < n; ++cur) {
                if (mask >> cur & 1 ^ 1) {
                    *res = min(*res, abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur));
                }
            }
            return *res;
        };
        function<void(int, int)> g = [&](int mask, int pre) {
            ans.push_back(pre);
            if (mask == (1 << n) - 1) {
                return;
            }
            int res = dfs(mask, pre);
            for (int cur = 1; cur < n; ++cur) {
                if (mask >> cur & 1 ^ 1) {
                    if (abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur) == res) {
                        g(mask | 1 << cur, cur);
                        break;
                    }
                }
            }
        };
        g(1, 0);
        return ans;
    }
};
```

#### Go

```go
func findPermutation(nums []int) (ans []int) {
	n := len(nums)
	f := make([][]int, 1<<n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(mask, pre int) int {
		if mask == 1<<n-1 {
			return abs(pre - nums[0])
		}
		if f[mask][pre] != -1 {
			return f[mask][pre]
		}
		res := &f[mask][pre]
		*res = math.MaxInt32
		for cur := 1; cur < n; cur++ {
			if mask>>cur&1 == 0 {
				*res = min(*res, abs(pre-nums[cur])+dfs(mask|1<<cur, cur))
			}
		}
		return *res
	}
	var g func(int, int)
	g = func(mask, pre int) {
		ans = append(ans, pre)
		if mask == 1<<n-1 {
			return
		}
		res := dfs(mask, pre)
		for cur := 1; cur < n; cur++ {
			if mask>>cur&1 == 0 {
				if abs(pre-nums[cur])+dfs(mask|1<<cur, cur) == res {
					g(mask|1<<cur, cur)
					break
				}
			}
		}
	}
	g(1, 0)
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function findPermutation(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = [];
    const f: number[][] = Array.from({ length: 1 << n }, () => Array(n).fill(-1));
    const dfs = (mask: number, pre: number): number => {
        if (mask === (1 << n) - 1) {
            return Math.abs(pre - nums[0]);
        }
        if (f[mask][pre] !== -1) {
            return f[mask][pre];
        }
        let res = Infinity;
        for (let cur = 1; cur < n; ++cur) {
            if (((mask >> cur) & 1) ^ 1) {
                res = Math.min(res, Math.abs(pre - nums[cur]) + dfs(mask | (1 << cur), cur));
            }
        }
        return (f[mask][pre] = res);
    };
    const g = (mask: number, pre: number) => {
        ans.push(pre);
        if (mask === (1 << n) - 1) {
            return;
        }
        const res = dfs(mask, pre);
        for (let cur = 1; cur < n; ++cur) {
            if (((mask >> cur) & 1) ^ 1) {
                if (Math.abs(pre - nums[cur]) + dfs(mask | (1 << cur), cur) === res) {
                    g(mask | (1 << cur), cur);
                    break;
                }
            }
        }
    };
    g(1, 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
