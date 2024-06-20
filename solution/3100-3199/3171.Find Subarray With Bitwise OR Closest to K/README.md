---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3171.Find%20Subarray%20With%20Bitwise%20OR%20Closest%20to%20K/README.md
rating: 2162
source: 第 400 场周赛 Q4
tags:
    - 位运算
    - 线段树
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [3171. 找到按位或最接近 K 的子数组](https://leetcode.cn/problems/find-subarray-with-bitwise-or-closest-to-k)

[English Version](/solution/3100-3199/3171.Find%20Subarray%20With%20Bitwise%20OR%20Closest%20to%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。你需要找到&nbsp;<code>nums</code>&nbsp;的一个&nbsp;<span data-keyword="subarray-nonempty">子数组</span>&nbsp;，满足子数组中所有元素按位或运算 <code>OR</code> 的值与 <code>k</code>&nbsp;的 <strong>绝对差</strong>&nbsp;尽可能 <strong>小</strong>&nbsp;。换言之，你需要选择一个子数组&nbsp;<code>nums[l..r]</code>&nbsp;满足 <code>|k - (nums[l] OR nums[l + 1] ... OR nums[r])|</code>&nbsp;最小。</p>

<p>请你返回 <strong>最小</strong>&nbsp;的绝对差值。</p>

<p><strong>子数组 </strong>是数组中连续的&nbsp;<strong>非空</strong>&nbsp;元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,4,5], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>子数组&nbsp;<code>nums[2..3]</code> 的按位 <code>OR</code> 运算值为 3 ，得到最小差值&nbsp;<code>|3 - 3| = 0</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>子数组&nbsp;<code>nums[1..1]</code> 的按位 <code>OR</code> 运算值为 3 ，得到最小差值&nbsp;<code>|3 - 2| = 1</code> 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1], k = 10</span></p>

<p><span class="example-io"><b>输出：</b>9</span></p>

<p><strong>解释：</strong></p>

<p>只有一个子数组，按位 <code>OR</code> 运算值为 1 ，得到最小差值&nbsp;<code>|10 - 1| = 9</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针 + 位运算

根据题目描述，我们需要求出数组 $nums$ 下标 $l$ 到 $r$ 的元素的按位与运算的结果，即 $nums[l] \& nums[l + 1] \& \cdots \& nums[r]$。

如果我们每次固定右端点 $r$，那么左端点 $l$ 的范围是 $[0, r]$。每次移动右端点 $r$，按位与的结果只会变小，我们用一个变量 $s$ 记录当前的按位与的结果，如果 $s$ 小于 $k$，我们就将左端点 $l$ 向右移动，直到 $s$ 大于等于 $k$。在移动左端点 $l$ 的过程中，我们需要维护一个数组 $cnt$，记录当前区间内每个二进制位上 $0$ 的个数，当 $cnt[h]$ 为 $0$ 时，说明当前区间内的元素在第 $h$ 位上都为 $1$，我们就可以将 $s$ 的第 $h$ 位设置为 $1$。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(\log M)$。其中 $n$ 和 $M$ 分别是数组 $nums$ 的长度和数组 $nums$ 中的最大值。

相似题目：

-   [3097. 或值至少为 K 的最短子数组 II](https://github.com/doocs/leetcode/blob/main/solution/3000-3099/3097.Shortest%20Subarray%20With%20OR%20at%20Least%20K%20II/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        m = max(nums).bit_length()
        cnt = [0] * m
        s, i = -1, 0
        ans = inf
        for j, x in enumerate(nums):
            s &= x
            ans = min(ans, abs(s - k))
            for h in range(m):
                if x >> h & 1 ^ 1:
                    cnt[h] += 1
            while i < j and s < k:
                y = nums[i]
                for h in range(m):
                    if y >> h & 1 ^ 1:
                        cnt[h] -= 1
                        if cnt[h] == 0:
                            s |= 1 << h
                i += 1
                ans = min(ans, abs(s - k))
        return ans
```

#### Java

```java
class Solution {
    public int minimumDifference(int[] nums, int k) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        int[] cnt = new int[m];
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0, j = 0, s = -1; j < n; ++j) {
            s &= nums[j];
            ans = Math.min(ans, Math.abs(s - k));
            for (int h = 0; h < m; ++h) {
                if ((nums[j] >> h & 1) == 0) {
                    ++cnt[h];
                }
            }
            while (i < j && s < k) {
                for (int h = 0; h < m; ++h) {
                    if ((nums[i] >> h & 1) == 0 && --cnt[h] == 0) {
                        s |= 1 << h;
                    }
                }
                ++i;
                ans = Math.min(ans, Math.abs(s - k));
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        int mx = *max_element(nums.begin(), nums.end());
        int m = 32 - __builtin_clz(mx);
        int n = nums.size();
        int ans = INT_MAX;
        vector<int> cnt(m);
        for (int i = 0, j = 0, s = -1; j < n; ++j) {
            s &= nums[j];
            ans = min(ans, abs(s - k));
            for (int h = 0; h < m; ++h) {
                if (nums[j] >> h & 1 ^ 1) {
                    ++cnt[h];
                }
            }
            while (i < j && s < k) {
                for (int h = 0; h < m; ++h) {
                    if (nums[i] >> h & 1 ^ 1 && --cnt[h] == 0) {
                        s |= 1 << h;
                    }
                }
                ans = min(ans, abs(s - k));
                ++i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumDifference(nums []int, k int) int {
	m := bits.Len(uint(slices.Max(nums)))
	cnt := make([]int, m)
	ans := math.MaxInt32
	s, i := -1, 0
	for j, x := range nums {
		s &= x
		ans = min(ans, abs(s-k))
		for h := 0; h < m; h++ {
			if x>>h&1 == 0 {
				cnt[h]++
			}
		}
		for i < j && s < k {
			y := nums[i]
			for h := 0; h < m; h++ {
				if y>>h&1 == 0 {
					cnt[h]--
					if cnt[h] == 0 {
						s |= 1 << h
					}
				}
			}
			ans = min(ans, abs(s-k))
			i++
		}
	}
	return ans
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
function minimumDifference(nums: number[], k: number): number {
    const m = Math.max(...nums).toString(2).length;
    const n = nums.length;
    const cnt: number[] = numsay(m).fill(0);
    let ans = Infinity;
    for (let i = 0, j = 0, s = -1; j < n; ++j) {
        s &= nums[j];
        ans = Math.min(ans, Math.abs(s - k));
        for (let h = 0; h < m; ++h) {
            if (((nums[j] >> h) & 1) ^ 1) {
                ++cnt[h];
            }
        }
        while (i < j && s < k) {
            for (let h = 0; h < m; ++h) {
                if (((nums[i] >> h) & 1) ^ 1 && --cnt[h] === 0) {
                    s |= 1 << h;
                }
            }
            ans = Math.min(ans, Math.abs(s - k));
            ++i;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：哈希表 + 枚举

根据题目描述，我们需要求出数组 $nums$ 下标 $l$ 到 $r$ 的元素的按位与运算的结果，即 $nums[l] \& nums[l + 1] \& \cdots \& nums[r]$。

如果我们每次固定右端点 $r$，那么左端点 $l$ 的范围是 $[0, r]$。由于按位与之和随着 $l$ 的减小而单调递减，并且 $nums[i]$ 的值不超过 $10^9$，因此区间 $[0, r]$ 最多只有 $30$ 种不同的值。因此，我们可以用一个集合来维护所有的 $nums[l] \& nums[l + 1] \& \cdots \& nums[r]$ 的值。当我们从 $r$ 遍历到 $r+1$ 时，以 $r+1$ 为右端点的值，就是集合中每个值与 $nums[r + 1]$ 进行按位与运算得到的值，再加上 $nums[r + 1]$ 本身。因此，我们只需要枚举集合中的每个值，与 $nums[r]$ 进行按位与运算，就可以得到以 $r$ 为右端点的所有值，将每个值与 $k$ 相减后取绝对值，就可以得到以 $r$ 为右端点的所有值与 $k$ 的差的绝对值，其中的最小值就是答案。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(\log M)$。其中 $n$ 和 $M$ 分别是数组 $nums$ 的长度和数组 $nums$ 中的最大值。

相似题目：

-   [1521. 找到最接近目标值的函数值](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        ans = abs(nums[0] - k)
        s = {nums[0]}
        for x in nums:
            s = {x & y for y in s} | {x}
            ans = min(ans, min(abs(y - k) for y in s))
        return ans
```

#### Java

```java
class Solution {
    public int minimumDifference(int[] nums, int k) {
        int ans = Math.abs(nums[0] - k);
        Set<Integer> pre = new HashSet<>();
        pre.add(nums[0]);
        for (int x : nums) {
            Set<Integer> cur = new HashSet<>();
            for (int y : pre) {
                cur.add(x & y);
            }
            cur.add(x);
            for (int y : cur) {
                ans = Math.min(ans, Math.abs(y - k));
            }
            pre = cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        int ans = abs(nums[0] - k);
        unordered_set<int> pre;
        pre.insert(nums[0]);
        for (int x : nums) {
            unordered_set<int> cur;
            cur.insert(x);
            for (int y : pre) {
                cur.insert(x & y);
            }
            for (int y : cur) {
                ans = min(ans, abs(y - k));
            }
            pre = move(cur);
        }
        return ans;
    }
};
```

#### Go

```go
func minimumDifference(nums []int, k int) int {
	ans := abs(nums[0] - k)
	pre := map[int]bool{nums[0]: true}
	for _, x := range nums {
		cur := map[int]bool{x: true}
		for y := range pre {
			cur[x&y] = true
		}
		for y := range cur {
			ans = min(ans, abs(y-k))
		}
		pre = cur
	}
	return ans
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
function minimumDifference(nums: number[], k: number): number {
    let ans = Math.abs(nums[0] - k);
    let pre = new Set<number>();
    pre.add(nums[0]);
    for (const x of nums) {
        const cur = new Set<number>();
        cur.add(x);
        for (const y of pre) {
            cur.add(x & y);
        }
        for (const y of cur) {
            ans = Math.min(ans, Math.abs(y - k));
        }
        pre = cur;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
