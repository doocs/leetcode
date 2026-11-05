---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4101. Find All Numbers Disappeared in an Array III 🔒](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array-iii)

[中文文档](/solution/4100-4199/4101.Find%20All%20Numbers%20Disappeared%20in%20an%20Array%20III/README.md)

## Description

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

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

<!-- thinking:start -->

> **Thinking**
>
> The upper bound $\textit{upper}$ can be up to $10^9$, so checking each integer in $[\textit{lower}, \textit{upper}]$ one by one will time out.
>
> Since $\textit{nums}$ has at most $10^5$ elements, only the elements that fall within $[\textit{lower}, \textit{upper}]$ split the range into missing segments. After sorting $\textit{nums}$, binary search locates the index range $[l, r)$ of elements in $[\textit{lower}, \textit{upper}]$, and each gap between consecutive elements forms a contiguous range of missing integers.
>
> Maintain $\textit{pre}$ as the smallest uncovered integer (initially $\textit{lower}$) and scan each element $\textit{cur}$ in $[l, r)$: whenever $\textit{pre} < \textit{cur}$, $[\textit{pre}, \textit{cur} - 1]$ is a missing range, then advance $\textit{pre}$ to $\textit{cur} + 1$, and finally append $[\textit{pre}, \textit{upper}]$ if $\textit{pre} \le \textit{upper}$.

<!-- thinking:end -->

We first sort the array $\textit{nums}$ in ascending order, and then use binary search to find the first index $l$ where $\textit{nums}[l] \ge \textit{lower}$ and the first index $r$ where $\textit{nums}[r] \ge \textit{upper} + 1$. The elements in the index range $[l, r)$ are all the numbers in $\textit{nums}$ that fall within $[\textit{lower}, \textit{upper}]$.

We maintain a variable $\textit{pre}$ representing the smallest integer not yet covered, initialized to $\textit{pre} = \textit{lower}$. We iterate through $i \in [l, r)$ and let $\textit{cur} = \textit{nums}[i]$:

- If $\textit{pre} < \textit{cur}$, all integers in $[\textit{pre}, \textit{cur} - 1]$ are missing, so we append $[\textit{pre}, \textit{cur} - 1]$ to the answer list $\textit{ans}$;
- We then update $\textit{pre} = \textit{cur} + 1$ (duplicate values naturally have $\textit{pre} > \textit{cur}$ and are skipped).

After the loop, if $\textit{pre} \le \textit{upper}$, the trailing range $[\textit{pre}, \textit{upper}]$ is also missing, so we append it to $\textit{ans}$. Finally, we return $\textit{ans}$.

The time complexity is $O(n \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

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
