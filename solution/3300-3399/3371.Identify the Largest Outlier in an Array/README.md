---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3371.Identify%20the%20Largest%20Outlier%20in%20an%20Array/README.md
rating: 1643
source: 第 426 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 计数
    - 枚举
---

<!-- problem:start -->

# [3371. 识别数组中的最大异常值](https://leetcode.cn/problems/identify-the-largest-outlier-in-an-array)

[English Version](/solution/3300-3399/3371.Identify%20the%20Largest%20Outlier%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。该数组包含 <code>n</code> 个元素，其中&nbsp;<strong>恰好&nbsp;</strong>有 <code>n - 2</code> 个元素是&nbsp;<strong>特殊数字&nbsp;</strong>。剩下的&nbsp;<strong>两个&nbsp;</strong>元素中，一个是所有&nbsp;<strong>特殊数字&nbsp;</strong>的 <strong>和</strong> ，另一个是&nbsp;<strong>异常值&nbsp;</strong>。</p>

<p><strong>异常值</strong> 的定义是：既不是原始特殊数字之一，也不是所有特殊数字的和。</p>

<p><strong>注意</strong>，特殊数字、和 以及 异常值 的下标必须&nbsp;<strong>不同&nbsp;</strong>，但可以共享&nbsp;<strong>相同</strong> 的值。</p>

<p>返回 <code>nums</code> 中可能的&nbsp;<strong>最大</strong><strong>异常值</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,3,5,10]</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>特殊数字可以是 2 和 3，因此和为 5，异常值为 10。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-2,-1,-3,-6,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>特殊数字可以是 -2、-1 和 -3，因此和为 -6，异常值为 4。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,1,1,1,5,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>特殊数字可以是 1、1、1、1 和 1，因此和为 5，另一个 5 为异常值。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li>输入保证 <code>nums</code> 中至少存在&nbsp;<strong>一个&nbsp;</strong>可能的异常值。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

我们用一个哈希表 $\textit{cnt}$ 记录数组 $\textit{nums}$ 中每个元素出现的次数。

接下来，我们枚举数组 $\textit{nums}$ 中的每个元素 $x$ 作为可能的异常值，对于每个 $x$，我们计算数组 $\textit{nums}$ 中除了 $x$ 之外的所有元素的和 $t$，如果 $t$ 不是偶数，或者 $t$ 的一半不在 $\textit{cnt}$ 中，那么 $x$ 不满足条件，我们跳过这个 $x$。否则，如果 $x$ 不等于 $t$ 的一半，或者 $x$ 在 $\textit{cnt}$ 中出现的次数大于 $1$，那么 $x$ 是一个可能的异常值，我们更新答案。

枚举结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLargestOutlier(self, nums: List[int]) -> int:
        s = sum(nums)
        cnt = Counter(nums)
        ans = -inf
        for x, v in cnt.items():
            t = s - x
            if t % 2 or cnt[t // 2] == 0:
                continue
            if x != t // 2 or v > 1:
                ans = max(ans, x)
        return ans
```

#### Java

```java
class Solution {
    public int getLargestOutlier(int[] nums) {
        int s = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            s += x;
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = Integer.MIN_VALUE;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            int t = s - x;
            if (t % 2 != 0 || !cnt.containsKey(t / 2)) {
                continue;
            }
            if (x != t / 2 || v > 1) {
                ans = Math.max(ans, x);
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
    int getLargestOutlier(vector<int>& nums) {
        int s = 0;
        unordered_map<int, int> cnt;
        for (int x : nums) {
            s += x;
            cnt[x]++;
        }
        int ans = INT_MIN;
        for (auto [x, v] : cnt) {
            int t = s - x;
            if (t % 2 || !cnt.contains(t / 2)) {
                continue;
            }
            if (x != t / 2 || v > 1) {
                ans = max(ans, x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getLargestOutlier(nums []int) int {
	s := 0
	cnt := map[int]int{}
	for _, x := range nums {
		s += x
		cnt[x]++
	}
	ans := math.MinInt32
	for x, v := range cnt {
		t := s - x
		if t%2 != 0 || cnt[t/2] == 0 {
			continue
		}
		if x != t/2 || v > 1 {
			ans = max(ans, x)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function getLargestOutlier(nums: number[]): number {
    let s = 0;
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        s += x;
        cnt[x] = (cnt[x] || 0) + 1;
    }
    let ans = -Infinity;
    for (const [x, v] of Object.entries(cnt)) {
        const t = s - +x;
        if (t % 2 || !cnt.hasOwnProperty((t / 2) | 0)) {
            continue;
        }
        if (+x != ((t / 2) | 0) || v > 1) {
            ans = Math.max(ans, +x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
