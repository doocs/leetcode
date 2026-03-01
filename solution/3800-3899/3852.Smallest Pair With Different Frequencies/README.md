---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3852.Smallest%20Pair%20With%20Different%20Frequencies/README.md
---

<!-- problem:start -->

# [3852. 不同频率的最小数对](https://leetcode.cn/problems/smallest-pair-with-different-frequencies)

[English Version](/solution/3800-3899/3852.Smallest%20Pair%20With%20Different%20Frequencies/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>从 <code>nums</code> 中找出两个 <strong>互不相同</strong> 的值 <code>x</code> 和 <code>y</code>，使得：</p>

<ul>
	<li><code>x &lt; y</code></li>
	<li><code>x</code> 和 <code>y</code> 在 <code>nums</code> 中的频率不同。</li>
</ul>

<p>在所有满足条件的数对中：</p>

<ul>
	<li>选择 <code>x</code> 的值尽可能小的数对。</li>
	<li>如果存在多个 <code>x</code> 相同的数对，选择 <code>y</code> 的值尽可能小的那个。</li>
</ul>

<p>返回一个整数数组 <code>[x, y]</code>。如果不存在有效的数对，返回 <code>[-1, -1]</code>。</p>

<p>一个值 <code>x</code> 的 <strong>频率</strong> 是指它在数组中出现的次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,2,2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,3]</span></p>

<p><strong>解释：</strong></p>

<p>最小的值是 1，频率为 2。比 1 大且频率与 1 不同的最小值是 3，其频率为 1。因此，答案是 <code>[1, 3]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>解释：</strong></p>

<p>两个值的频率相同，因此不存在有效的数对。返回 <code>[-1, -1]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7]</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>解释：</strong></p>

<p>数组中只有一个值，因此不存在有效的数对。返回 <code>[-1, -1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{cnt}$ 来统计每个值在数组中的频率。然后我们找到最小的值 $x$，以及比 $x$ 大且频率与 $x$ 不同的最小值 $y$。如果不存在这样的 $y$，则返回 $[-1, -1]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDistinctFreqPair(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        x = min(cnt.keys())
        min_y = inf
        for y in cnt.keys():
            if y < min_y and cnt[x] != cnt[y]:
                min_y = y
        return [-1, -1] if min_y == inf else [x, min_y]
```

#### Java

```java
class Solution {
    public int[] minDistinctFreqPair(int[] nums) {
        final int inf = Integer.MAX_VALUE;
        Map<Integer, Integer> cnt = new HashMap<>();
        int x = inf;
        for (int v : nums) {
            cnt.merge(v, 1, Integer::sum);
            x = Math.min(x, v);
        }
        int minY = inf;
        for (int y : cnt.keySet()) {
            if (y < minY && cnt.get(x) != cnt.get(y)) {
                minY = y;
            }
        }
        return minY == inf ? new int[] {-1, -1} : new int[] {x, minY};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minDistinctFreqPair(vector<int>& nums) {
        const int inf = INT_MAX;
        unordered_map<int, int> cnt;
        int x = inf;

        for (int v : nums) {
            cnt[v]++;
            x = min(x, v);
        }

        int minY = inf;
        for (auto& [y, _] : cnt) {
            if (y < minY && cnt[x] != cnt[y]) {
                minY = y;
            }
        }

        if (minY == inf) {
            return {-1, -1};
        }
        return {x, minY};
    }
};
```

#### Go

```go
func minDistinctFreqPair(nums []int) []int {
	const inf = math.MaxInt
	cnt := make(map[int]int)

	for _, v := range nums {
		cnt[v]++
	}

	x := slices.Min(nums)

	minY := inf
	for y := range cnt {
		if y < minY && cnt[x] != cnt[y] {
			minY = y
		}
	}

	if minY == inf {
		return []int{-1, -1}
	}
	return []int{x, minY}
}
```

#### TypeScript

```ts
function minDistinctFreqPair(nums: number[]): number[] {
    const inf = Number.MAX_SAFE_INTEGER;
    const cnt = new Map<number, number>();

    let x = inf;
    for (const v of nums) {
        cnt.set(v, (cnt.get(v) ?? 0) + 1);
        x = Math.min(x, v);
    }

    let minY = inf;
    for (const [y] of cnt) {
        if (y < minY && cnt.get(x)! !== cnt.get(y)!) {
            minY = y;
        }
    }

    return minY === inf ? [-1, -1] : [x, minY];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
