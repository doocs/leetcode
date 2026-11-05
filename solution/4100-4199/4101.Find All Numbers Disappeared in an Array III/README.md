---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4101. Find All Numbers Disappeared in an Array III 🔒](https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array-iii)

[English Version](/solution/4100-4199/4101.Find%20All%20Numbers%20Disappeared%20in%20an%20Array%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>lower</code> and <code>upper</code>.</p>

<p>An integer is <strong>missing</strong> if it lies in the inclusive range <code>[lower, upper]</code> and does not appear in <code>nums</code>.</p>

<p>Return a 2D integer array representing all missing integers grouped into <strong>contiguous ranges</strong>. Represent each range as <code>[start, end]</code>, including both endpoints, and return the ranges in increasing order of <code>start</code>.</p>

<p>Consecutive missing integers must belong to the same range. If there are no missing integers, return an empty array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,9,7], lower = 1, upper = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,2],[4,6],[8,8],[10,12]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The missing integers are <code>[1, 2, 4, 5, 6, 8, 10, 11, 12]</code>.</li>
	<li>Grouping the missing integers into the minimum number of contiguous ranges, we get <code>[1, 2]</code>, <code>[4, 6]</code>, <code>[8, 8]</code>, and <code>[10, 12]</code>.</li>
	<li>Therefore, the answer is <code>[[1, 2], [4, 6], [8, 8], [10, 12]]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1000000000], lower = 5, upper = 1000000000</span></p>

<p><strong>Output:</strong> <span class="example-io">[[5,999999999]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The value 1 lies outside <code>[lower, upper]</code>, so it does not cover any integer in the range.</li>
	<li>The only integer of the range that appears in <code>nums</code> is 1000000000.</li>
	<li>Every integer from 5 to 999999999 is missing, and they are consecutive, so they form the single range <code>[5, 999999999]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5], lower = 2, upper = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Both 2 and 3 appear in <code>nums</code>, so there are no missing integers.</li>
	<li>Therefore, the answer is <code>[]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= lower &lt;= upper &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

<!-- thinking:start -->

> **思考**
>
> 区间 $[\textit{lower}, \textit{upper}]$ 的上界高达 $10^9$，逐个检查区间内的整数是否出现在 $\textit{nums}$ 中会直接超时。
>
> 数组长度最多为 $10^5$，真正把 $[\textit{lower}, \textit{upper}]$ 切开的只有落在这一范围内的数组元素。将 $\textit{nums}$ 排序后，可以用二分查找定位出落入 $[\textit{lower}, \textit{upper}]$ 的下标区间 $[l, r)$，相邻元素之间的空隙就是一段连续的缺失整数。
>
> 用 $\textit{pre}$ 表示当前待覆盖的最小整数（初始为 $\textit{lower}$），依次遍历下标区间 $[l, r)$ 内的元素 $\textit{cur}$：若 $\textit{pre} < \textit{cur}$，则 $[\textit{pre}, \textit{cur} - 1]$ 为一段缺失区间，随后将 $\textit{pre}$ 推进到 $\textit{cur} + 1$，最后再补上末尾可能剩余的 $[\textit{pre}, \textit{upper}]$。

<!-- thinking:end -->

我们先将数组 $\textit{nums}$ 升序排序，然后通过二分查找找到第一个大于等于 $\textit{lower}$ 的下标 $l$，以及第一个大于等于 $\textit{upper} + 1$ 的下标 $r$。此时下标区间 $[l, r)$ 内的元素即为 $\textit{nums}$ 中所有落在 $[\textit{lower}, \textit{upper}]$ 范围内的数。

我们用变量 $\textit{pre}$ 记录当前尚未被覆盖的最小整数，初始时 $\textit{pre} = \textit{lower}$。遍历 $i \in [l, r)$，设当前元素为 $\textit{cur} = \textit{nums}[i]$：

- 若 $\textit{pre} < \textit{cur}$，说明区间 $[\textit{pre}, \textit{cur} - 1]$ 内的整数均未出现，将其加入答案数组 $\textit{ans}$；
- 随后更新 $\textit{pre} = \textit{cur} + 1$（若存在重复元素，此时 $\textit{pre} > \textit{cur}$ 会自然跳过）。

遍历结束后，若仍有 $\textit{pre} \le \textit{upper}$，说明末尾区间 $[\textit{pre}, \textit{upper}]$ 内的整数也缺失，将其加入答案数组 $\textit{ans}$。最后返回 $\textit{ans}$ 即可。

时间复杂度 $O(n \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findDisappearedNumbers(
        self, nums: list[int], lower: int, upper: int
    ) -> list[list[int]]:
        nums.sort()
        l = bisect_left(nums, lower)
        r = bisect_left(nums, upper + 1)
        ans = []
        pre = lower
        for i in range(l, r):
            cur = nums[i]
            if pre < cur:
                ans.append([pre, cur - 1])
            pre = cur + 1
        if pre <= upper:
            ans.append([pre, upper])
        return ans
```

#### Java

```java
class Solution {
    public int[][] findDisappearedNumbers(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int l = lowerBound(nums, lower);
        int r = lowerBound(nums, upper + 1);
        List<int[]> ans = new ArrayList<>();
        int pre = lower;
        for (int i = l; i < r; i++) {
            int cur = nums[i];
            if (pre < cur) {
                ans.add(new int[] {pre, cur - 1});
            }
            pre = cur + 1;
        }
        if (pre <= upper) {
            ans.add(new int[] {pre, upper});
        }
        return ans.toArray(new int[ans.size()][]);
    }

    private int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> findDisappearedNumbers(vector<int>& nums, int lower, int upper) {
        sort(nums.begin(), nums.end());
        int l = lower_bound(nums.begin(), nums.end(), lower) - nums.begin();
        int r = lower_bound(nums.begin(), nums.end(), upper + 1) - nums.begin();
        vector<vector<int>> ans;
        int pre = lower;
        for (int i = l; i < r; i++) {
            int cur = nums[i];
            if (pre < cur) {
                ans.push_back({pre, cur - 1});
            }
            pre = cur + 1;
        }
        if (pre <= upper) {
            ans.push_back({pre, upper});
        }
        return ans;
    }
};
```

#### Go

```go
func findDisappearedNumbers(nums []int, lower int, upper int) [][]int {
	sort.Ints(nums)
	l := sort.SearchInts(nums, lower)
	r := sort.Search(len(nums), func(i int) bool {
		return nums[i] > upper
	})
	ans := [][]int{}
	pre := lower
	for i := l; i < r; i++ {
		cur := nums[i]
		if pre < cur {
			ans = append(ans, []int{pre, cur - 1})
		}
		pre = cur + 1
	}
	if pre <= upper {
		ans = append(ans, []int{pre, upper})
	}
	return ans
}
```

#### TypeScript

```ts
function findDisappearedNumbers(nums: number[], lower: number, upper: number): number[][] {
    nums.sort((a, b) => a - b);
    const l = _.sortedIndex(nums, lower);
    const r = _.sortedIndex(nums, upper + 1);
    const ans: number[][] = [];
    let pre = lower;

    for (let i = l; i < r; i++) {
        const cur = nums[i];
        if (pre < cur) {
            ans.push([pre, cur - 1]);
        }
        pre = cur + 1;
    }

    if (pre <= upper) {
        ans.push([pre, upper]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
