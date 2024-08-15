---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2202.Maximize%20the%20Topmost%20Element%20After%20K%20Moves/README.md
rating: 1716
source: 第 284 场周赛 Q3
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [2202. K 次操作后最大化顶端元素](https://leetcode.cn/problems/maximize-the-topmost-element-after-k-moves)

[English Version](/solution/2200-2299/2202.Maximize%20the%20Topmost%20Element%20After%20K%20Moves/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它表示一个 <strong>堆</strong> ，其中 <code>nums[0]</code>&nbsp;是堆顶的元素。</p>

<p>每一次操作中，你可以执行以下操作 <strong>之一</strong>&nbsp;：</p>

<ul>
	<li>如果堆非空，那么 <strong>删除</strong>&nbsp;堆顶端的元素。</li>
	<li>如果存在 1 个或者多个被删除的元素，你可以从它们中选择任何一个，<b>添加</b>&nbsp;回堆顶，这个元素成为新的堆顶元素。</li>
</ul>

<p>同时给你一个整数&nbsp;<code>k</code>&nbsp;，它表示你总共需要执行操作的次数。</p>

<p>请你返回 <strong>恰好</strong>&nbsp;执行 <code>k</code>&nbsp;次操作以后，堆顶元素的 <strong>最大值</strong>&nbsp;。如果执行完 <code>k</code>&nbsp;次操作以后，堆一定为空，请你返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [5,2,2,4,0,6], k = 4
<b>输出：</b>5
<strong>解释：</strong>
4 次操作后，堆顶元素为 5 的方法之一为：
- 第 1 次操作：删除堆顶元素 5 ，堆变为 [2,2,4,0,6] 。
- 第 2 次操作：删除堆顶元素 2 ，堆变为 [2,4,0,6] 。
- 第 3 次操作：删除堆顶元素 2 ，堆变为 [4,0,6] 。
- 第 4 次操作：将 5 添加回堆顶，堆变为 [5,4,0,6] 。
注意，这不是最后堆顶元素为 5 的唯一方式。但可以证明，4 次操作以后 5 是能得到的最大堆顶元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2], k = 1
<b>输出：</b>-1
<b>解释：</b>
第 1 次操作中，我们唯一的选择是将堆顶元素弹出堆。
由于 1 次操作后无法得到一个非空的堆，所以我们返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTop(self, nums: List[int], k: int) -> int:
        if k == 0:
            return nums[0]
        n = len(nums)
        if n == 1:
            if k % 2:
                return -1
            return nums[0]
        ans = max(nums[: k - 1], default=-1)
        if k < n:
            ans = max(ans, nums[k])
        return ans
```

#### Java

```java
class Solution {
    public int maximumTop(int[] nums, int k) {
        if (k == 0) {
            return nums[0];
        }
        int n = nums.length;
        if (n == 1) {
            if (k % 2 == 1) {
                return -1;
            }
            return nums[0];
        }
        int ans = -1;
        for (int i = 0; i < Math.min(k - 1, n); ++i) {
            ans = Math.max(ans, nums[i]);
        }
        if (k < n) {
            ans = Math.max(ans, nums[k]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumTop(vector<int>& nums, int k) {
        if (k == 0) return nums[0];
        int n = nums.size();
        if (n == 1) {
            if (k % 2) return -1;
            return nums[0];
        }
        int ans = -1;
        for (int i = 0; i < min(k - 1, n); ++i) ans = max(ans, nums[i]);
        if (k < n) ans = max(ans, nums[k]);
        return ans;
    }
};
```

#### Go

```go
func maximumTop(nums []int, k int) int {
	if k == 0 {
		return nums[0]
	}
	n := len(nums)
	if n == 1 {
		if k%2 == 1 {
			return -1
		}
		return nums[0]
	}
	ans := -1
	for i := 0; i < min(k-1, n); i++ {
		ans = max(ans, nums[i])
	}
	if k < n {
		ans = max(ans, nums[k])
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
