---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3978.Unique%20Middle%20Element/README.md
rating: 1180
source: 第 186 场双周赛 Q1
---

<!-- problem:start -->

# [3978. 唯一中间元素](https://leetcode.cn/problems/unique-middle-element)

[English Version](/solution/3900-3999/3978.Unique%20Middle%20Element/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为奇数 <code>n</code> 的整数数组 <code>nums</code> 。</p>

<p>如果 <code>nums</code> 的下标中间元素在数组中&nbsp;<strong>恰好&nbsp;</strong>出现一次，返回 <code>true</code> 。否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的中间元素是 2 ，它恰好出现一次。</p>

<p>因此，答案为 <code>true</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 的中间元素是 2 ，它出现了两次。</p>

<p>因此，答案为 <code>false</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>n</code> 是奇数。</li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们取出数组中间下标的元素，统计其在数组中出现的次数，若为 $1$ 则返回 $\textit{true}$，否则返回 $\textit{false}$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMiddleElementUnique(self, nums: list[int]) -> bool:
        return nums.count(nums[len(nums) // 2]) == 1
```

#### Java

```java
class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        int cnt = 0;
        for (int x : nums) {
            if (x == nums[nums.length / 2]) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isMiddleElementUnique(vector<int>& nums) {
        int n = nums.size();
        int cnt = 0;
        for (int x : nums) {
            if (x == nums[n / 2]) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
};
```

#### Go

```go
func isMiddleElementUnique(nums []int) bool {
	cnt := 0
	for _, x := range nums {
		if x == nums[len(nums)/2] {
			cnt++
		}
	}
	return cnt == 1
}
```

#### TypeScript

```ts
function isMiddleElementUnique(nums: number[]): boolean {
    let cnt: number = 0;
    for (const x of nums) {
        if (x === nums[nums.length >> 1]) {
            ++cnt;
        }
    }
    return cnt === 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
