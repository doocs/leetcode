---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1887.Reduction%20Operations%20to%20Make%20the%20Array%20Elements%20Equal/README.md
rating: 1427
source: 第 244 场周赛 Q2
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [1887. 使数组元素相等的减少操作次数](https://leetcode.cn/problems/reduction-operations-to-make-the-array-elements-equal)

[English Version](/solution/1800-1899/1887.Reduction%20Operations%20to%20Make%20the%20Array%20Elements%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，你的目标是令 <code>nums</code> 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：</p>

<ol>
	<li>找出 <code>nums</code> 中的 <strong>最大</strong> 值。记这个值为 <code>largest</code> 并取其下标 <code>i</code> （<strong>下标从 0 开始计数</strong>）。如果有多个元素都是最大值，则取最小的 <code>i</code> 。</li>
	<li>找出 <code>nums</code> 中的 <strong>下一个最大</strong> 值，这个值 <strong>严格小于</strong> <code>largest</code> ，记为 <code>nextLargest</code> 。</li>
	<li>将 <code>nums[i]</code> 减少到 <code>nextLargest</code> 。</li>
</ol>

<p>返回使<em> </em><code>nums</code><em> </em>中的所有元素相等的操作次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,1,3]
<strong>输出：</strong>3
<strong>解释：</strong>需要 3 次操作使 nums 中的所有元素相等：
1. largest = 5 下标为 0 。nextLargest = 3 。将 nums[0] 减少到 3 。nums = [<strong>3</strong>,1,3] 。
2. largest = 3 下标为 0 。nextLargest = 1 。将 nums[0] 减少到 1 。nums = [<strong>1</strong>,1,3] 。
3. largest = 3 下标为 2 。nextLargest = 1 。将 nums[2] 减少到 1 。nums = [<strong>1</strong>,1,<strong>1</strong>] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1]
<strong>输出：</strong>0
<strong>解释：</strong>nums 中的所有元素已经是相等的。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2,2,3]
<strong>输出：</strong>4
<strong>解释：</strong>需要 4 次操作使 nums 中的所有元素相等：
1. largest = 3 下标为 4 。nextLargest = 2 。将 nums[4] 减少到 2 。nums = [1,1,2,2,<strong>2</strong>] 。
2. largest = 2 下标为 2 。nextLargest = 1 。将 nums[2] 减少到 1 。nums = [1,1,<strong>1</strong>,2,2] 。 
3. largest = 2 下标为 3 。nextLargest = 1 。将 nums[3] 减少到 1 。nums = [1,1,1,<strong>1</strong>,2] 。 
4. largest = 2 下标为 4 。nextLargest = 1 。将 nums[4] 减少到 1 。nums = [1,1,1,1,<strong>1</strong>] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>1 <= nums[i] <= 5 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们首先对数组 $\textit{nums}$ 进行排序，然后从数组的第二个元素开始遍历，如果当前元素和前一个元素不相等，那么我们就将 $\textit{cnt}$ 加一，表示我们需要将当前元素减小到最小值的操作次数。然后我们将 $\textit{ans}$ 加上 $\textit{cnt}$，继续遍历下一个元素。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        nums.sort()
        ans = cnt = 0
        for a, b in pairwise(nums):
            if a != b:
                cnt += 1
            ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] != nums[i - 1]) {
                ++cnt;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.size(); ++i) {
            cnt += nums[i] != nums[i - 1];
            ans += cnt;
        }
        return ans;
    }
};
```

#### Go

```go
func reductionOperations(nums []int) (ans int) {
	sort.Ints(nums)
	cnt := 0
	for i, x := range nums[1:] {
		if x != nums[i] {
			cnt++
		}
		ans += cnt
	}
	return
}
```

#### TypeScript

```ts
function reductionOperations(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let [ans, cnt] = [0, 0];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] !== nums[i - 1]) {
            ++cnt;
        }
        ans += cnt;
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var reductionOperations = function (nums) {
    nums.sort((a, b) => a - b);
    let [ans, cnt] = [0, 0];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] !== nums[i - 1]) {
            ++cnt;
        }
        ans += cnt;
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int ReductionOperations(int[] nums) {
        Array.Sort(nums);
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.Length; i++) {
            if (nums[i] != nums[i - 1]) {
                ++cnt;
            }
            ans += cnt;
        }
        return ans;
    }
}

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
