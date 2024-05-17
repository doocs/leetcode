---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2210.Count%20Hills%20and%20Valleys%20in%20an%20Array/README.md
rating: 1354
source: 第 285 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [2210. 统计数组中峰和谷的数量](https://leetcode.cn/problems/count-hills-and-valleys-in-an-array)

[English Version](/solution/2200-2299/2210.Count%20Hills%20and%20Valleys%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。如果两侧距 <code>i</code> 最近的不相等邻居的值均小于 <code>nums[i]</code> ，则下标 <code>i</code> 是 <code>nums</code> 中，某个峰的一部分。类似地，如果两侧距 <code>i</code> 最近的不相等邻居的值均大于 <code>nums[i]</code> ，则下标 <code>i</code> 是 <code>nums</code> 中某个谷的一部分。对于相邻下标&nbsp;<code>i</code> 和 <code>j</code> ，如果&nbsp;<code>nums[i] == nums[j]</code> ， 则认为这两下标属于 <strong>同一个</strong> 峰或谷。</p>

<p>注意，要使某个下标所做峰或谷的一部分，那么它左右两侧必须 <strong>都</strong> 存在不相等邻居。</p>

<p>返回 <code>nums</code> 中峰和谷的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,1,1,6,5]
<strong>输出：</strong>3
<strong>解释：</strong>
在下标 0 ：由于 2 的左侧不存在不相等邻居，所以下标 0 既不是峰也不是谷。
在下标 1 ：4 的最近不相等邻居是 2 和 1 。由于 4 &gt; 2 且 4 &gt; 1 ，下标 1 是一个峰。
在下标 2 ：1 的最近不相等邻居是 4 和 6 。由于 1 &lt; 4 且 1 &lt; 6 ，下标 2 是一个谷。
在下标 3 ：1 的最近不相等邻居是 4 和 6 。由于 1 &lt; 4 且 1 &lt; 6 ，下标 3 符合谷的定义，但需要注意它和下标 2 是同一个谷的一部分。
在下标 4 ：6 的最近不相等邻居是 1 和 5 。由于 6 &gt; 1 且 6 &gt; 5 ，下标 4 是一个峰。
在下标 5 ：由于 5 的右侧不存在不相等邻居，所以下标 5 既不是峰也不是谷。
共有 3 个峰和谷，所以返回 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,6,5,5,4,1]
<strong>输出：</strong>0
<strong>解释：</strong>
在下标 0 ：由于 6 的左侧不存在不相等邻居，所以下标 0 既不是峰也不是谷。
在下标 1 ：由于 6 的左侧不存在不相等邻居，所以下标 1 既不是峰也不是谷。
在下标 2 ：5 的最近不相等邻居是 6 和 4 。由于 5 &lt; 6 且 5 &gt; 4 ，下标 2 既不是峰也不是谷。
在下标 3 ：5 的最近不相等邻居是 6 和 4 。由于 5 &lt; 6 且 5 &gt; 4 ，下标 3 既不是峰也不是谷。
在下标 4 ：4 的最近不相等邻居是 5 和 1 。由于 4 &lt; 5 且 4 &gt; 1 ，下标 4 既不是峰也不是谷。
在下标 5 ：由于 1 的右侧不存在不相等邻居，所以下标 5 既不是峰也不是谷。
共有 0 个峰和谷，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们初始化一个指针 $j$ 指向下标 $0$ 的位置，然后在 $[1, n-1]$ 的范围内遍历数组。对于每一个位置 $i$：

-   如果 $nums[i] = nums[i+1]$，则跳过。
-   否则，如果 $nums[i]$ 大于 $nums[j]$ 且 $nums[i]$ 大于 $nums[i+1]$，则 $i$ 是一个峰；如果 $nums[i]$ 小于 $nums[j]$ 且 $nums[i]$ 小于 $nums[i+1]$，则 $i$ 是一个谷。
-   然后，我们将 $j$ 更新为 $i$，继续遍历。

遍历结束后，我们就可以得到峰和谷的数量。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countHillValley(self, nums: List[int]) -> int:
        ans = j = 0
        for i in range(1, len(nums) - 1):
            if nums[i] == nums[i + 1]:
                continue
            if nums[i] > nums[j] and nums[i] > nums[i + 1]:
                ans += 1
            if nums[i] < nums[j] and nums[i] < nums[i + 1]:
                ans += 1
            j = i
        return ans
```

```java
class Solution {
    public int countHillValley(int[] nums) {
        int ans = 0;
        for (int i = 1, j = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            if (nums[i] > nums[j] && nums[i] > nums[i + 1]) {
                ++ans;
            }
            if (nums[i] < nums[j] && nums[i] < nums[i + 1]) {
                ++ans;
            }
            j = i;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countHillValley(vector<int>& nums) {
        int ans = 0;
        for (int i = 1, j = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            if (nums[i] > nums[j] && nums[i] > nums[i + 1]) {
                ++ans;
            }
            if (nums[i] < nums[j] && nums[i] < nums[i + 1]) {
                ++ans;
            }
            j = i;
        }
        return ans;
    }
};
```

```go
func countHillValley(nums []int) int {
	ans := 0
	for i, j := 1, 0; i < len(nums)-1; i++ {
		if nums[i] == nums[i+1] {
			continue
		}
		if nums[i] > nums[j] && nums[i] > nums[i+1] {
			ans++
		}
		if nums[i] < nums[j] && nums[i] < nums[i+1] {
			ans++
		}
		j = i
	}
	return ans
}
```

```ts
function countHillValley(nums: number[]): number {
    let ans = 0;
    for (let i = 1, j = 0; i < nums.length - 1; ++i) {
        if (nums[i] === nums[i + 1]) {
            continue;
        }
        if (nums[i] > nums[j] && nums[i] > nums[i + 1]) {
            ans++;
        }
        if (nums[i] < nums[j] && nums[i] < nums[i + 1]) {
            ans++;
        }
        j = i;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn count_hill_valley(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut j = 0;

        for i in 1..nums.len() - 1 {
            if nums[i] == nums[i + 1] {
                continue;
            }
            if nums[i] > nums[j] && nums[i] > nums[i + 1] {
                ans += 1;
            }
            if nums[i] < nums[j] && nums[i] < nums[i + 1] {
                ans += 1;
            }
            j = i;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
