# [163. 缺失的区间 🔒](https://leetcode.cn/problems/missing-ranges)

[English Version](/solution/0100-0199/0163.Missing%20Ranges/README_EN.md)

<!-- tags:数组 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个闭区间&nbsp;<code>[lower, upper]</code> 和一个 <strong>按从小到大排序</strong> 的整数数组 <code>nums</code><em><strong>&nbsp;</strong></em>，其中元素的范围在闭区间&nbsp;<code>[lower, upper]</code>&nbsp;当中。</p>

<p>如果一个数字 <code>x</code> 在 <code>[lower, upper]</code>&nbsp;区间内，并且 <code>x</code> 不在 <code>nums</code> 中，则认为 <code>x</code> <strong>缺失</strong>。</p>

<p>返回&nbsp;<strong>准确涵盖所有缺失数字&nbsp;</strong>的 <strong>最小排序</strong> 区间列表。也就是说，<code>nums</code> 的任何元素都不在任何区间内，并且每个缺失的数字都在其中一个区间内。</p>
&nbsp;

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[0, 1, 3, 50, 75]</code>, lower = 0 , upper = 99
<strong>输出: </strong>[[2,2],[4,49],[51,74],[76,99]]
<strong>解释：</strong>返回的区间是：
[2,2]
[4,49]
[51,74]
[76,99]</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong> nums = [-1], lower = -1, upper = -1
<strong>输出：</strong> []
<b>解释：</b>&nbsp;没有缺失的区间，因为没有缺失的数字。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>9</sup> &lt;= lower &lt;= upper &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>lower &lt;= nums[i] &lt;= upper</code></li>
	<li><code>nums</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

## 解法

### 方法一：模拟

我们直接按照题意模拟即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findMissingRanges(
        self, nums: List[int], lower: int, upper: int
    ) -> List[List[int]]:
        n = len(nums)
        if n == 0:
            return [[lower, upper]]
        ans = []
        if nums[0] > lower:
            ans.append([lower, nums[0] - 1])
        for a, b in pairwise(nums):
            if b - a > 1:
                ans.append([a + 1, b - 1])
        if nums[-1] < upper:
            ans.append([nums[-1] + 1, upper])
        return ans
```

```java
class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 0) {
            return List.of(List.of(lower, upper));
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (nums[0] > lower) {
            ans.add(List.of(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > 1) {
                ans.add(List.of(nums[i - 1] + 1, nums[i] - 1));
            }
        }
        if (nums[n - 1] < upper) {
            ans.add(List.of(nums[n - 1] + 1, upper));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> findMissingRanges(vector<int>& nums, int lower, int upper) {
        int n = nums.size();
        if (n == 0) {
            return {{lower, upper}};
        }
        vector<vector<int>> ans;
        if (nums[0] > lower) {
            ans.push_back({lower, nums[0] - 1});
        }
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] - nums[i - 1] > 1) {
                ans.push_back({nums[i - 1] + 1, nums[i] - 1});
            }
        }
        if (nums[n - 1] < upper) {
            ans.push_back({nums[n - 1] + 1, upper});
        }
        return ans;
    }
};
```

```go
func findMissingRanges(nums []int, lower int, upper int) (ans [][]int) {
	n := len(nums)
	if n == 0 {
		return [][]int{{lower, upper}}
	}
	if nums[0] > lower {
		ans = append(ans, []int{lower, nums[0] - 1})
	}
	for i, b := range nums[1:] {
		if a := nums[i]; b-a > 1 {
			ans = append(ans, []int{a + 1, b - 1})
		}
	}
	if nums[n-1] < upper {
		ans = append(ans, []int{nums[n-1] + 1, upper})
	}
	return
}
```

```ts
function findMissingRanges(nums: number[], lower: number, upper: number): number[][] {
    const n = nums.length;
    if (n === 0) {
        return [[lower, upper]];
    }
    const ans: number[][] = [];
    if (nums[0] > lower) {
        ans.push([lower, nums[0] - 1]);
    }
    for (let i = 1; i < n; ++i) {
        if (nums[i] - nums[i - 1] > 1) {
            ans.push([nums[i - 1] + 1, nums[i] - 1]);
        }
    }
    if (nums[n - 1] < upper) {
        ans.push([nums[n - 1] + 1, upper]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
