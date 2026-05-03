---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3912.Valid%20Elements%20in%20an%20Array/README.md
rating: 1273
source: 第 499 场周赛 Q1
---

<!-- problem:start -->

# [3912. 数组中的有效元素](https://leetcode.cn/problems/valid-elements-in-an-array)

[English Version](/solution/3900-3999/3912.Valid%20Elements%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果元素 <code>nums[i]</code> 满足以下&nbsp;<strong>至少一个&nbsp;</strong>条件，则认为它是&nbsp;<strong>有效&nbsp;</strong>元素：</p>

<ul>
	<li>它<strong>&nbsp;严格大于&nbsp;</strong>其左侧的所有元素。</li>
	<li>它<strong>&nbsp;严格大于</strong>&nbsp;其右侧的所有元素。</li>
</ul>

<p>第一个元素和最后一个元素始终有效。</p>

<p>返回所有有效元素组成的数组，顺序与它们在 <code>nums</code> 中出现的顺序相同。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,4,2,3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,4,3,2]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums[0]</code> 和 <code>nums[5]</code> 始终有效。</li>
	<li><code>nums[1]</code> 和 <code>nums[2]</code> 都严格大于其左侧的所有元素。</li>
	<li><code>nums[4]</code> 严格大于其右侧的所有元素。</li>
	<li>因此，答案为 <code>[1, 2, 4, 3, 2]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,5,5,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">[5,5]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个元素和最后一个元素始终有效。</li>
	<li>其他元素既不严格大于其左侧的所有元素，也不严格大于其右侧的所有元素。</li>
	<li>因此，答案为 <code>[5, 5]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1]</span></p>

<p><strong>解释：</strong></p>

<p>由于数组中只有一个元素，它始终有效。因此，答案为 <code>[1]</code>。</p>
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

### 方法一：预处理数组

我们可以预处理数组，计算出每个元素右侧的最大值，记录在数组 $\textit{right}$ 中。

然后，我们从左到右遍历数组，使用一个变量 $\textit{left}$ 来记录当前元素左侧的最大值。对于每个元素，如果它满足以下任一条件，则将其加入答案中：

- 它严格大于 $\textit{left}$。
- 它是数组的最后一个元素。
- 它严格大于 $\textit{right}[i + 1]$。

在遍历过程中，我们不断更新 $\textit{left}$ 的值。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findValidElements(self, nums: list[int]) -> list[int]:
        n = len(nums)
        right = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            right[i] = max(right[i + 1], nums[i])
        left = 0
        ans = []
        for i, x in enumerate(nums):
            if x > left or i == n - 1 or x > right[i + 1]:
                ans.append(x)
            left = max(left, x)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], nums[i]);
        }
        int left = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x > left || i == n - 1 || x > right[i + 1]) {
                ans.add(x);
            }
            left = Math.max(left, x);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findValidElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n);
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = max(right[i + 1], nums[i]);
        }
        int left = 0;
        vector<int> ans;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x > left || i == n - 1 || x > right[i + 1]) {
                ans.push_back(x);
            }
            left = max(left, x);
        }
        return ans;
    }
};
```

#### Go

```go
func findValidElements(nums []int) []int {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]
	for i := n - 2; i >= 0; i-- {
		right[i] = max(right[i+1], nums[i])
	}
	left := 0
	ans := []int{}
	for i, x := range nums {
		if x > left || i == n-1 || x > right[i+1] {
			ans = append(ans, x)
		}
		left = max(left, x)
	}
	return ans
}
```

#### TypeScript

```ts
function findValidElements(nums: number[]): number[] {
    const n = nums.length;
    const right = new Array(n);
    right[n - 1] = nums[n - 1];
    for (let i = n - 2; i >= 0; i--) {
        right[i] = Math.max(right[i + 1], nums[i]);
    }
    let left = 0;
    const ans: number[] = [];
    for (let i = 0; i < n; i++) {
        const x = nums[i];
        if (x > left || i === n - 1 || x > right[i + 1]) {
            ans.push(x);
        }
        left = Math.max(left, x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
