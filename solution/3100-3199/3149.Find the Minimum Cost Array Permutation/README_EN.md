---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/README_EN.md
---

<!-- problem:start -->

# [3149. Find the Minimum Cost Array Permutation](https://leetcode.com/problems/find-the-minimum-cost-array-permutation)

[中文文档](/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> which is a <span data-keyword="permutation">permutation</span> of <code>[0, 1, 2, ..., n - 1]</code>. The <strong>score</strong> of any permutation of <code>[0, 1, 2, ..., n - 1]</code> named <code>perm</code> is defined as:</p>

<p><code>score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|</code></p>

<p>Return the permutation <code>perm</code> which has the <strong>minimum</strong> possible score. If <em>multiple</em> permutations exist with this score, return the one that is <span data-keyword="lexicographically-smaller-array">lexicographically smallest</span> among them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example0gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>The lexicographically smallest permutation with minimum cost is <code>[0,1,2]</code>. The cost of this permutation is <code>|0 - 0| + |1 - 2| + |2 - 1| = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3149.Find%20the%20Minimum%20Cost%20Array%20Permutation/images/example1gif.gif" style="width: 235px; height: 235px;" /></strong></p>

<p>The lexicographically smallest permutation with minimum cost is <code>[0,2,1]</code>. The cost of this permutation is <code>|0 - 1| + |2 - 2| + |1 - 0| = 2</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 14</code></li>
	<li><code>nums</code> is a permutation of <code>[0, 1, 2, ..., n - 1]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We notice that for any permutation $\text{perm}$, if we cyclically shift it to the left any number of times, the score of the permutation remains the same. Since the problem requires returning the lexicographically smallest permutation, we can determine that the first element of the permutation must be $0$.

Also, since the data range of the problem does not exceed $14$, we can consider using the method of state compression to represent the set of numbers selected in the current permutation. We use a binary number $\text{mask}$ of length $n$ to represent the set of numbers selected in the current permutation, where the $i$-th bit of $\text{mask}$ is $1$ indicates that the number $i$ has been selected, and $0$ indicates that the number $i$ has not been selected yet.

We design a function $\text{dfs}(\text{mask}, \text{pre})$, which represents the minimum score of the permutation obtained when the set of numbers selected in the current permutation is $\text{mask}$ and the last selected number is $\text{pre}$. Initially, we add the number $0$ to the permutation.

The calculation process of the function $\text{dfs}(\text{mask}, \text{pre})$ is as follows:

-   If the number of $1$s in the binary representation of $\text{mask}$ is $n$, that is, $\text{mask} = 2^n - 1$, it means that all numbers have been selected, then return $|\text{pre} - \text{nums}[0]|$;
-   Otherwise, we enumerate the next selected number $\text{cur}$. If the number $\text{cur}$ has not been selected yet, then we can add the number $\text{cur}$ to the permutation. At this time, the score of the permutation is $|\text{pre} - \text{nums}[\text{cur}]| + \text{dfs}(\text{mask} \, | \, 1 << \text{cur}, \text{cur})$. We need to take the minimum score among all $\text{cur}$.

Finally, we use a function $\text{g}(\text{mask}, \text{pre})$ to construct the permutation that gets the minimum score. We first add the number $\text{pre}$ to the permutation, and then enumerate the next selected number $\text{cur}$. If the number $\text{cur}$ has not been selected yet, and it satisfies that the value of $|\text{pre} - \text{nums}[\text{cur}]| + \text{dfs}(\text{mask} \, | \, 1 << \text{cur}, \text{cur})$ is equal to $\text{dfs}(\text{mask}, \text{pre})$, then we can add the number $\text{cur}$ to the permutation.

The time complexity is $(n^2 \times 2^n)$, and the space complexity is $O(n \times 2^n)$. Where $n$ is the length of the array $\text{nums}$.

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
