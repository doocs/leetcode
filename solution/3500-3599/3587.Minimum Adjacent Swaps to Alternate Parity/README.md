---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README.md
rating: 1548
source: 第 159 场双周赛 Q1
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [3587. 最小相邻交换至奇偶交替](https://leetcode.cn/problems/minimum-adjacent-swaps-to-alternate-parity)

[English Version](/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由互不相同的整数组成的数组 <code>nums</code>&nbsp;。</p>

<p>在一次操作中，你可以交换任意两个&nbsp;<strong>相邻&nbsp;</strong>元素。</p>

<p>在一个排列中，当所有相邻元素的奇偶性交替出现，我们认为该排列是 <strong>有效排列</strong>。这意味着每对相邻元素中一个是偶数，一个是奇数。</p>

<p>请返回将 <code>nums</code> 变成任意一种&nbsp;<strong>有效排列</strong>&nbsp;所需的最小相邻交换次数。</p>

<p>如果无法重排 <code>nums</code> 来获得有效排列，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,4,6,5,7]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>将 5 和 6 交换，数组变成&nbsp; <code>[2,4,5,6,7]</code></p>

<p>将 5 和 4&nbsp;交换，数组变成&nbsp; <code>[2,5,4,6,7]</code></p>

<p>将 6&nbsp;和 7&nbsp;交换，数组变成&nbsp;&nbsp;<code>[2,5,4,7,6]</code>。此时是一个有效排列。因此答案是 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,4,5,7]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>将 4&nbsp;和 5&nbsp;交换，数组变成 <code>[2,5,4,7]</code>。此时是一个有效排列。因此答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组已经是有效排列，因此不需要任何操作。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><b>输入：</b>&nbsp;<span class="example-io">nums = [4,5,6,8]</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><b>解释：</b></p>

<p>没有任何一种排列可以满足奇偶交替的要求，因此返回 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code>&nbsp;中的所有元素都是 <strong>唯一</strong> 的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分类讨论 + 贪心

对于一个有效排列，奇数和偶数的个数只能相差 1 或者相等。因此，如果奇数和偶数的个数相差超过 1，则无法构成有效排列，直接返回 -1。

我们用一个数组 $\text{pos}$ 来存储奇数和偶数的下标，其中 $\text{pos}[0]$ 存储偶数的下标，而 $\text{pos}[1]$ 存储奇数的下标。

如果奇数和偶数的个数相等，则可以有两种有效排列：奇数在偶数前面，或者偶数在奇数前面。我们可以计算这两种排列的交换次数，取最小值。

如果奇数的个数大于偶数的个数，则只有一种有效排列，即奇数在偶数前面。此时，我们只需要计算这种排列的交换次数。

因此，我们定义一个函数 $\text{calc}(k)$，其中 $k$ 表示第一个元素的奇偶性（0 表示偶数，1 表示奇数）。该函数计算从当前排列到以 $k$ 开头的有效排列所需的交换次数。我们只需要遍历 $\text{pos}[k]$ 中的下标，计算每个下标与其在有效排列中的位置之间的差值之和。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\text{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        def calc(k: int) -> int:
            return sum(abs(i - j) for i, j in zip(range(0, len(nums), 2), pos[k]))

        pos = [[], []]
        for i, x in enumerate(nums):
            pos[x & 1].append(i)
        if abs(len(pos[0]) - len(pos[1])) > 1:
            return -1
        if len(pos[0]) > len(pos[1]):
            return calc(0)
        if len(pos[0]) < len(pos[1]):
            return calc(1)
        return min(calc(0), calc(1))
```

#### Java

```java
class Solution {
    private List<Integer>[] pos = new List[2];
    private int[] nums;

    public int minSwaps(int[] nums) {
        this.nums = nums;
        Arrays.setAll(pos, k -> new ArrayList<>());
        for (int i = 0; i < nums.length; ++i) {
            pos[nums[i] & 1].add(i);
        }
        if (Math.abs(pos[0].size() - pos[1].size()) > 1) {
            return -1;
        }
        if (pos[0].size() > pos[1].size()) {
            return calc(0);
        }
        if (pos[0].size() < pos[1].size()) {
            return calc(1);
        }
        return Math.min(calc(0), calc(1));
    }

    private int calc(int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += Math.abs(pos[k].get(i / 2) - i);
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        vector<int> pos[2];
        for (int i = 0; i < nums.size(); ++i) {
            pos[nums[i] & 1].push_back(i);
        }
        if (abs(int(pos[0].size() - pos[1].size())) > 1) {
            return -1;
        }
        auto calc = [&](int k) {
            int res = 0;
            for (int i = 0; i < nums.size(); i += 2) {
                res += abs(pos[k][i / 2] - i);
            }
            return res;
        };
        if (pos[0].size() > pos[1].size()) {
            return calc(0);
        }
        if (pos[0].size() < pos[1].size()) {
            return calc(1);
        }
        return min(calc(0), calc(1));
    }
};
```

#### Go

```go
func minSwaps(nums []int) int {
	pos := [2][]int{}
	for i, x := range nums {
		pos[x&1] = append(pos[x&1], i)
	}
	if abs(len(pos[0])-len(pos[1])) > 1 {
		return -1
	}
	calc := func(k int) int {
		res := 0
		for i := 0; i < len(nums); i += 2 {
			res += abs(pos[k][i/2] - i)
		}
		return res
	}
	if len(pos[0]) > len(pos[1]) {
		return calc(0)
	}
	if len(pos[0]) < len(pos[1]) {
		return calc(1)
	}
	return min(calc(0), calc(1))
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minSwaps(nums: number[]): number {
    const pos: number[][] = [[], []];
    for (let i = 0; i < nums.length; ++i) {
        pos[nums[i] & 1].push(i);
    }
    if (Math.abs(pos[0].length - pos[1].length) > 1) {
        return -1;
    }
    const calc = (k: number): number => {
        let res = 0;
        for (let i = 0; i < nums.length; i += 2) {
            res += Math.abs(pos[k][i >> 1] - i);
        }
        return res;
    };
    if (pos[0].length > pos[1].length) {
        return calc(0);
    }
    if (pos[0].length < pos[1].length) {
        return calc(1);
    }
    return Math.min(calc(0), calc(1));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
