---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3164.Find%20the%20Number%20of%20Good%20Pairs%20II/README.md
rating: 1777
source: 第 399 场周赛 Q3
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3164. 优质数对的总数 II](https://leetcode.cn/problems/find-the-number-of-good-pairs-ii)

[English Version](/solution/3100-3199/3164.Find%20the%20Number%20of%20Good%20Pairs%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>nums1</code> 和 <code>nums2</code>，长度分别为 <code>n</code> 和 <code>m</code>。同时给你一个<strong>正整数</strong> <code>k</code>。</p>

<p>如果 <code>nums1[i]</code> 可以被 <code>nums2[j] * k</code> 整除，则称数对 <code>(i, j)</code> 为 <strong>优质数对</strong>（<code>0 &lt;= i &lt;= n - 1</code>, <code>0 &lt;= j &lt;= m - 1</code>）。</p>

<p>返回<strong> 优质数对 </strong>的总数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [1,3,4], nums2 = [1,3,4], k = 1</span></p>

<p><strong>输出：</strong><span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>5个优质数对分别是 <code>(0, 0)</code>, <code>(1, 0)</code>, <code>(1, 1)</code>, <code>(2, 0)</code>, 和 <code>(2, 2)</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [1,2,4,12], nums2 = [2,4], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>2个优质数对分别是 <code>(3, 0)</code> 和 <code>(3, 1)</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[j] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举倍数

我们用一个哈希表 $\textit{cnt1}$ 记录数组 $\textit{nums1}$ 中每个数除以 $k$ 的商的出现次数，用一个哈希表 $\textit{cnt2}$ 记录数组 $\textit{nums2}$ 中每个数的出现次数。

接下来，我们枚举数组 $\textit{nums2}$ 中的每个数 $x$，对于每个数 $x$，我们枚举 $x$ 的倍数 $y$，其中 $y$ 的范围是 $[x, \textit{mx}]$，其中 $\textit{mx}$ 是 $\textit{cnt1}$ 中的最大键值，然后我们统计 $\textit{cnt1}[y]$ 的和，记为 $s$，最后我们将 $s \times v$ 累加到答案中，其中 $v$ 是 $\textit{cnt2}[x]$。

时间复杂度 $O(n + m + (M / k) \times \log m)$，空间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别是数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度，而 $M$ 是数组 $\textit{nums1}$ 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPairs(self, nums1: List[int], nums2: List[int], k: int) -> int:
        cnt1 = Counter(x // k for x in nums1 if x % k == 0)
        if not cnt1:
            return 0
        cnt2 = Counter(nums2)
        ans = 0
        mx = max(cnt1)
        for x, v in cnt2.items():
            s = sum(cnt1[y] for y in range(x, mx + 1, x))
            ans += s * v
        return ans
```

#### Java

```java
class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> cnt1 = new HashMap<>();
        for (int x : nums1) {
            if (x % k == 0) {
                cnt1.merge(x / k, 1, Integer::sum);
            }
        }
        if (cnt1.isEmpty()) {
            return 0;
        }
        Map<Integer, Integer> cnt2 = new HashMap<>();
        for (int x : nums2) {
            cnt2.merge(x, 1, Integer::sum);
        }
        long ans = 0;
        int mx = Collections.max(cnt1.keySet());
        for (var e : cnt2.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            int s = 0;
            for (int y = x; y <= mx; y += x) {
                s += cnt1.getOrDefault(y, 0);
            }
            ans += 1L * s * v;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        unordered_map<int, int> cnt1;
        for (int x : nums1) {
            if (x % k == 0) {
                cnt1[x / k]++;
            }
        }
        if (cnt1.empty()) {
            return 0;
        }
        unordered_map<int, int> cnt2;
        for (int x : nums2) {
            ++cnt2[x];
        }
        int mx = 0;
        for (auto& [x, _] : cnt1) {
            mx = max(mx, x);
        }
        long long ans = 0;
        for (auto& [x, v] : cnt2) {
            long long s = 0;
            for (int y = x; y <= mx; y += x) {
                s += cnt1[y];
            }
            ans += s * v;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfPairs(nums1 []int, nums2 []int, k int) (ans int64) {
	cnt1 := map[int]int{}
	for _, x := range nums1 {
		if x%k == 0 {
			cnt1[x/k]++
		}
	}
	if len(cnt1) == 0 {
		return 0
	}
	cnt2 := map[int]int{}
	for _, x := range nums2 {
		cnt2[x]++
	}
	mx := 0
	for x := range cnt1 {
		mx = max(mx, x)
	}
	for x, v := range cnt2 {
		s := 0
		for y := x; y <= mx; y += x {
			s += cnt1[y]
		}
		ans += int64(s) * int64(v)
	}
	return
}
```

#### TypeScript

```ts
function numberOfPairs(nums1: number[], nums2: number[], k: number): number {
    const cnt1: Map<number, number> = new Map();
    for (const x of nums1) {
        if (x % k === 0) {
            cnt1.set((x / k) | 0, (cnt1.get((x / k) | 0) || 0) + 1);
        }
    }
    if (cnt1.size === 0) {
        return 0;
    }
    const cnt2: Map<number, number> = new Map();
    for (const x of nums2) {
        cnt2.set(x, (cnt2.get(x) || 0) + 1);
    }
    const mx = Math.max(...cnt1.keys());
    let ans = 0;
    for (const [x, v] of cnt2) {
        let s = 0;
        for (let y = x; y <= mx; y += x) {
            s += cnt1.get(y) || 0;
        }
        ans += s * v;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
