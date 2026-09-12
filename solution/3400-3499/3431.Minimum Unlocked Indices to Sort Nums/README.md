---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3431.Minimum%20Unlocked%20Indices%20to%20Sort%20Nums/README.md
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3431. 对数字排序的最小解锁下标 🔒](https://leetcode.cn/problems/minimum-unlocked-indices-to-sort-nums)

[English Version](/solution/3400-3499/3431.Minimum%20Unlocked%20Indices%20to%20Sort%20Nums/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个仅包含 1、2、3 的整数的数组&nbsp;<code>nums</code>，以及一个相同大小的&nbsp;<strong>二进制</strong>&nbsp;数组&nbsp;<code>locked</code>。</p>

<p>当满足&nbsp;<code>nums[i] - nums[i + 1] == 1</code> 且 <code>locked[i] == 0</code>时，则允许交换下标&nbsp;<code>i</code> 和 <code>i + 1</code> 处的元素；如果可以通过交换相邻元素将&nbsp;<code>nums</code>&nbsp;升序排序，我们认为 <code>nums</code> 是 <strong>可排序的。</strong></p>

<p>你可以进行若干次操作，每次操作可以将 <code>locked[i]</code> 设置为 <code>0</code>，从而解锁下标&nbsp;<code>i</code>。</p>

<p>返回使&nbsp;<code>nums</code>&nbsp;满足&nbsp;<strong>可排序的</strong> 所需 <strong>最小</strong>&nbsp;操作次数。如果不可能使&nbsp;<code>nums</code>&nbsp;<strong>可排序</strong>，返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,2,3,2], locked = [1,0,1,1,0,1]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>我们可以按如下交换来排序&nbsp;<code>nums</code>。</p>

<ul>
	<li>交换下标 1 和 2</li>
	<li>交换下标 4 和 5</li>
</ul>

<p>所以，不需要解锁任何下标。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,1,3,2,2], locked = [1,0,1,1,0,1,0]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>如果我们解锁下标 2 和 5，我们可以按如下交换来排序&nbsp;<code>nums</code>。</p>

<ul>
	<li>交换下标 1 和 2</li>
	<li>交换下标 2 和 3</li>
	<li>交换下标 4 和 5</li>
	<li>交换下标 5 和 6</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2,1,2,3,2,1], locked = [0,0,0,0,0,0,0]</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><strong>解释：</strong></p>

<p>尽管所有下标都是解锁的，可以发现&nbsp;<code>nums</code>&nbsp;不可排序。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 3</code></li>
	<li><code>locked.length == nums.length</code></li>
	<li><code>0 &lt;= locked[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

<!-- thinking:start -->

> **思考**
>
> 数组只含 $1,2,3$，相邻交换还受 $\textit{locked}$ 限制。若把所有逆序都模拟出来，$n\le 10^5$ 无法承受。
>
> 能跨越的只有 $1$ 与 $2$、$2$ 与 $3$。$3$ 无法越过 $1$，故若某个 $3$ 出现在某个 $1$ 之前，无解。
>
> 需要解锁的位置，正是「最左的 $2$ 到最右的 $1$」以及「最左的 $3$ 到最右的 $2$」这两段里仍然上锁的下标。一遍扫描记下四个端点再计数即可。

<!-- thinking:end -->

根据题目描述，要使得 $\textit{nums}$ 变成可排序的数组，需要满足数字 $3$ 的位置在数字 $1$ 的位置之后。如果数字 $3$ 的位置在数字 $1$ 的位置之前，那么无论怎么交换，数字 $3$ 都无法到达数字 $1$ 的位置，因此无法使得 $\textit{nums}$ 变成可排序的数组。

我们分别用 $\textit{first2}$ 和 $\textit{first3}$ 表示数字 $2$ 和 $3$ 第一次出现的位置，用 $\textit{last1}$ 和 $\textit{last2}$ 表示数字 $1$ 和 $2$ 最后一次出现的位置。

那么当下标 $i$ 位于 $[\textit{first2}, \textit{last1})$ 或者 $[\textit{first3}, \textit{last2})]$ 时，对应的 $\textit{locked}[i]$ 必须为 $0$，否则我们需要一次操作。因此，我们只需要遍历数组 $\textit{locked}$，统计不满足条件的下标即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minUnlockedIndices(self, nums: List[int], locked: List[int]) -> int:
        n = len(nums)
        first2 = first3 = n
        last1 = last2 = -1
        for i, x in enumerate(nums):
            if x == 1:
                last1 = i
            elif x == 2:
                first2 = min(first2, i)
                last2 = i
            else:
                first3 = min(first3, i)
        if first3 < last1:
            return -1
        return sum(
            st and (first2 <= i < last1 or first3 <= i < last2)
            for i, st in enumerate(locked)
        )
```

#### Java

```java
class Solution {
    public int minUnlockedIndices(int[] nums, int[] locked) {
        int n = nums.length;
        int first2 = n, first3 = n;
        int last1 = -1, last2 = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                last1 = i;
            } else if (nums[i] == 2) {
                first2 = Math.min(first2, i);
                last2 = i;
            } else {
                first3 = Math.min(first3, i);
            }
        }
        if (first3 < last1) {
            return -1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (locked[i] == 1 && ((first2 <= i && i < last1) || (first3 <= i && i < last2))) {
                ++ans;
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
    int minUnlockedIndices(vector<int>& nums, vector<int>& locked) {
        int n = nums.size();
        int first2 = n, first3 = n;
        int last1 = -1, last2 = -1;

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                last1 = i;
            } else if (nums[i] == 2) {
                first2 = min(first2, i);
                last2 = i;
            } else {
                first3 = min(first3, i);
            }
        }

        if (first3 < last1) {
            return -1;
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (locked[i] == 1 && ((first2 <= i && i < last1) || (first3 <= i && i < last2))) {
                ++ans;
            }
        }

        return ans;
    }
};
```

#### Go

```go
func minUnlockedIndices(nums []int, locked []int) (ans int) {
	n := len(nums)
	first2, first3 := n, n
	last1, last2 := -1, -1
	for i, x := range nums {
		if x == 1 {
			last1 = i
		} else if x == 2 {
			if i < first2 {
				first2 = i
			}
			last2 = i
		} else {
			if i < first3 {
				first3 = i
			}
		}
	}
	if first3 < last1 {
		return -1
	}
	for i, st := range locked {
		if st == 1 && ((first2 <= i && i < last1) || (first3 <= i && i < last2)) {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minUnlockedIndices(nums: number[], locked: number[]): number {
    const n = nums.length;
    let [first2, first3] = [n, n];
    let [last1, last2] = [-1, -1];

    for (let i = 0; i < n; i++) {
        if (nums[i] === 1) {
            last1 = i;
        } else if (nums[i] === 2) {
            first2 = Math.min(first2, i);
            last2 = i;
        } else {
            first3 = Math.min(first3, i);
        }
    }

    if (first3 < last1) {
        return -1;
    }

    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (locked[i] === 1 && ((first2 <= i && i < last1) || (first3 <= i && i < last2))) {
            ans++;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
