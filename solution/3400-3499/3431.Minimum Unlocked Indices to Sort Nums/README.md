---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3431.Minimum%20Unlocked%20Indices%20to%20Sort%20Nums/README.md
---

<!-- problem:start -->

# [3431. Minimum Unlocked Indices to Sort Nums 🔒](https://leetcode.cn/problems/minimum-unlocked-indices-to-sort-nums)

[English Version](/solution/3400-3499/3431.Minimum%20Unlocked%20Indices%20to%20Sort%20Nums/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of integers between 1 and 3, and a <strong>binary</strong> array <code>locked</code> of the same size.</p>

<p>We consider <code>nums</code> <strong>sortable</strong> if it can be sorted using adjacent swaps, where a swap between two indices <code>i</code> and <code>i + 1</code> is allowed if <code>nums[i] - nums[i + 1] == 1</code> and <code>locked[i] == 0</code>.</p>

<p>In one operation, you can unlock any index <code>i</code> by setting <code>locked[i]</code> to 0.</p>

<p>Return the <strong>minimum</strong> number of operations needed to make <code>nums</code> <strong>sortable</strong>. If it is not possible to make <code>nums</code> sortable, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2,3,2], locked = [1,0,1,1,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>We can sort <code>nums</code> using the following swaps:</p>

<ul>
	<li>swap indices 1 with 2</li>
	<li>swap indices 4 with 5</li>
</ul>

<p>So, there is no need to unlock any index.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,1,3,2,2], locked = [1,0,1,1,0,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>If we unlock indices 2 and 5, we can sort <code>nums</code> using the following swaps:</p>

<ul>
	<li>swap indices 1 with 2</li>
	<li>swap indices 2 with 3</li>
	<li>swap indices 4 with 5</li>
	<li>swap indices 5 with 6</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2,3,2,1], locked = [0,0,0,0,0,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>Even if all indices are unlocked, it can be shown that <code>nums</code> is not sortable.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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
