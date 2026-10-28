---
comments: true
difficulty: 简单
rating: 1152
source: 第 269 场周赛 Q1
tags:
    - 数组
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [2089. 找出数组排序后的目标下标](https://leetcode.cn/problems/find-target-indices-after-sorting-array)

[English Version](/solution/2000-2099/2089.Find%20Target%20Indices%20After%20Sorting%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 以及一个目标元素 <code>target</code> 。</p>

<p><strong>目标下标</strong> 是一个满足&nbsp;<code>nums[i] == target</code> 的下标 <code>i</code> 。</p>

<p>将 <code>nums</code> 按 <strong>非递减</strong> 顺序排序后，返回由 <code>nums</code> 中目标下标组成的列表。如果不存在目标下标，返回一个 <strong>空</strong> 列表。返回的列表必须按 <strong>递增</strong> 顺序排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 2
<strong>输出：</strong>[1,2]
<strong>解释：</strong>排序后，nums 变为 [1,<em><strong>2</strong></em>,<em><strong>2</strong></em>,3,5] 。
满足 nums[i] == 2 的下标是 1 和 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 3
<strong>输出：</strong>[3]
<strong>解释：</strong>排序后，nums 变为 [1,2,2,<em><strong>3</strong></em>,5] 。
满足 nums[i] == 3 的下标是 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 5
<strong>输出：</strong>[4]
<strong>解释：</strong>排序后，nums 变为 [1,2,2,3,<em><strong>5</strong></em>] 。
满足 nums[i] == 5 的下标是 4 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 4
<strong>输出：</strong>[]
<strong>解释：</strong>nums 中不含值为 4 的元素。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i], target &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

<!-- thinking:start -->

> **思考**
>
> $n \le 100$，排序后收集等于 $target$ 的下标。下标已按升序产生。
>
> 若需线性时间，也可数更小值个数与相等个数，再生成区间；当前实现直接排序。

<!-- thinking:end -->

将数组 `nums` 排序后，遍历数组，找出所有等于 `target` 的元素的下标，将其加入结果数组中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def targetIndices(self, nums: List[int], target: int) -> List[int]:
        nums.sort()
        return [i for i, v in enumerate(nums) if v == target]
```

#### Java

```java
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                ans.add(i);
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
    vector<int> targetIndices(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        vector<int> ans;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == target) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func targetIndices(nums []int, target int) (ans []int) {
	sort.Ints(nums)
	for i, v := range nums {
		if v == target {
			ans = append(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function targetIndices(nums: number[], target: number): number[] {
    nums.sort((a, b) => a - b);
    let ans: number[] = [];
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] == target) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：计数

<!-- thinking:start -->

> **思考**
>
> 方法一为了得到下标，先对整个数组做比较排序，时间停在 $O(n\log n)$。返回的下标只由 $target$ 在有序序列中的位置决定，其它元素彼此的先后并不影响答案。
>
> 严格小于 $target$ 的个数就是这段连续下标的起点，等于 $target$ 的个数就是区间长度。这两个量在原数组上各计一次即可，不必再比较元素的相对次序，因此用一次扫描代替排序。

<!-- thinking:end -->

等于 $target$ 的元素在非递减排序后占据一段连续下标。这段下标的起点等于数组中严格小于 $target$ 的元素个数，长度等于等于 $target$ 的元素个数。

遍历一次 $\textit{nums}$，分别用 $\textit{less}$、 $\textit{equal}$ 统计这两类元素。答案就是区间 $[\textit{less}, \textit{less} + \textit{equal})$ 中的全部整数。 $\textit{equal} = 0$ 时区间为空。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def targetIndices(self, nums: List[int], target: int) -> List[int]:
        less = equal = 0
        for x in nums:
            if x < target:
                less += 1
            elif x == target:
                equal += 1
        return list(range(less, less + equal))
```

#### Java

```java
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, equal = 0;
        for (int x : nums) {
            if (x < target) {
                ++less;
            } else if (x == target) {
                ++equal;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = less; i < less + equal; ++i) {
            ans.add(i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> targetIndices(vector<int>& nums, int target) {
        int less = 0, equal = 0;
        for (int x : nums) {
            if (x < target) {
                ++less;
            } else if (x == target) {
                ++equal;
            }
        }
        vector<int> ans;
        for (int i = less; i < less + equal; ++i) {
            ans.push_back(i);
        }
        return ans;
    }
};
```

#### Go

```go
func targetIndices(nums []int, target int) (ans []int) {
	less, equal := 0, 0
	for _, x := range nums {
		if x < target {
			less++
		} else if x == target {
			equal++
		}
	}
	for i := less; i < less+equal; i++ {
		ans = append(ans, i)
	}
	return
}
```

#### TypeScript

```ts
function targetIndices(nums: number[], target: number): number[] {
    let less = 0;
    let equal = 0;
    for (const x of nums) {
        if (x < target) {
            ++less;
        } else if (x == target) {
            ++equal;
        }
    }
    const ans: number[] = [];
    for (let i = less; i < less + equal; ++i) {
        ans.push(i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
