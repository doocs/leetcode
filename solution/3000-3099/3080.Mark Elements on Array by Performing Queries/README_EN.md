---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3080.Mark%20Elements%20on%20Array%20by%20Performing%20Queries/README_EN.md
rating: 1607
source: Biweekly Contest 126 Q2
tags:
    - Array
    - Hash Table
    - Sorting
    - Simulation
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3080. Mark Elements on Array by Performing Queries](https://leetcode.com/problems/mark-elements-on-array-by-performing-queries)

[中文文档](/solution/3000-3099/3080.Mark%20Elements%20on%20Array%20by%20Performing%20Queries/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of size <code>n</code> consisting of positive integers.</p>

<p>You are also given a 2D array <code>queries</code> of size <code>m</code> where <code>queries[i] = [index<sub>i</sub>, k<sub>i</sub>]</code>.</p>

<p>Initially all elements of the array are <strong>unmarked</strong>.</p>

<p>You need to apply <code>m</code> queries on the array in order, where on the <code>i<sup>th</sup></code> query you do the following:</p>

<ul>
	<li>Mark the element at index <code>index<sub>i</sub></code> if it is not already marked.</li>
	<li>Then mark <code>k<sub>i</sub></code> unmarked elements in the array with the <strong>smallest</strong> values. If multiple such elements exist, mark the ones with the smallest indices. And if less than <code>k<sub>i</sub></code> unmarked elements exist, then mark all of them.</li>
</ul>

<p>Return <em>an array answer of size </em><code>m</code><em> where </em><code>answer[i]</code><em> is the <strong>sum</strong> of unmarked elements in the array after the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">[8,3,0]</span></p>

<p><strong>Explanation:</strong></p>

<p>We do the following queries on the array:</p>

<ul>
	<li>Mark the element at index <code>1</code>, and <code>2</code> of the smallest unmarked elements with the smallest indices if they exist, the marked elements now are <code>nums = [<strong><u>1</u></strong>,<u><strong>2</strong></u>,2,<u><strong>1</strong></u>,2,3,1]</code>. The sum of unmarked elements is <code>2 + 2 + 3 + 1 = 8</code>.</li>
	<li>Mark the element at index <code>3</code>, since it is already marked we skip it. Then we mark <code>3</code> of the smallest unmarked elements with the smallest indices, the marked elements now are <code>nums = [<strong><u>1</u></strong>,<u><strong>2</strong></u>,<u><strong>2</strong></u>,<u><strong>1</strong></u>,<u><strong>2</strong></u>,3,<strong><u>1</u></strong>]</code>. The sum of unmarked elements is <code>3</code>.</li>
	<li>Mark the element at index <code>4</code>, since it is already marked we skip it. Then we mark <code>2</code> of the smallest unmarked elements with the smallest indices if they exist, the marked elements now are <code>nums = [<strong><u>1</u></strong>,<u><strong>2</strong></u>,<u><strong>2</strong></u>,<u><strong>1</strong></u>,<u><strong>2</strong></u>,<strong><u>3</u></strong>,<u><strong>1</strong></u>]</code>. The sum of unmarked elements is <code>0</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,4,2,3], queries = [[0,1]]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">[7]</span></p>

<p><strong>Explanation: </strong> We do one query which is mark the element at index <code>0</code> and mark the smallest element among unmarked elements. The marked elements will be <code>nums = [<strong><u>1</u></strong>,4,<u><strong>2</strong></u>,3]</code>, and the sum of unmarked elements is <code>4 + 3 = 7</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= index<sub>i</sub>, k<sub>i</sub> &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Simulation

First, we calculate the sum $s$ of the array $nums$. We define an array $mark$ to indicate whether the elements in the array have been marked, initializing all elements as unmarked.

Then, we create an array $arr$, where each element is a tuple $(x, i)$, indicating that the $i$-th element in the array has a value of $x$. We sort the array $arr$ by the value of the elements. If the values are equal, we sort them in ascending order of the index.

Next, we traverse the array $queries$. For each query $[index, k]$, we first check whether the element at index $index$ has been marked. If it has not been marked, we mark it and subtract the value of the element at index $index$ from $s$. Then, we traverse the array $arr$. For each element $(x, i)$, if element $i$ has not been marked, we mark it and subtract the value $x$ corresponding to element $i$ from $s$, until $k$ is $0$ or the array $arr$ is fully traversed. Then, we add $s$ to the answer array.

After traversing all the queries, we get the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def unmarkedSumArray(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        n = len(nums)
        s = sum(nums)
        mark = [False] * n
        arr = sorted((x, i) for i, x in enumerate(nums))
        j = 0
        ans = []
        for index, k in queries:
            if not mark[index]:
                mark[index] = True
                s -= nums[index]
            while k and j < n:
                if not mark[arr[j][1]]:
                    mark[arr[j][1]] = True
                    s -= arr[j][0]
                    k -= 1
                j += 1
            ans.append(s)
        return ans
```

#### Java

```java
class Solution {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        long s = Arrays.stream(nums).asLongStream().sum();
        boolean[] mark = new boolean[n];
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[]{nums[i], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int m = queries.length;
        long[] ans = new long[m];
        for (int i = 0, j = 0; i < m; ++i) {
            int index = queries[i][0], k = queries[i][1];
            if (!mark[index]) {
                mark[index] = true;
                s -= nums[index];
            }
            for (; k > 0 && j < n; ++j) {
                if (!mark[arr[j][1]]) {
                    mark[arr[j][1]] = true;
                    s -= arr[j][0];
                    --k;
                }
            }
            ans[i] = s;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> unmarkedSumArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        vector<bool> mark(n);
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(nums[i], i);
        }
        sort(arr.begin(), arr.end());
        vector<long long> ans;
        int m = queries.size();
        for (int i = 0, j = 0; i < m; ++i) {
            int index = queries[i][0], k = queries[i][1];
            if (!mark[index]) {
                mark[index] = true;
                s -= nums[index];
            }
            for (; k && j < n; ++j) {
                if (!mark[arr[j].second]) {
                    mark[arr[j].second] = true;
                    s -= arr[j].first;
                    --k;
                }
            }
            ans.push_back(s);
        }
        return ans;
    }
};
```

#### Go

```go
func unmarkedSumArray(nums []int, queries [][]int) []int64 {
	n := len(nums)
	var s int64
	for _, x := range nums {
		s += int64(x)
	}
	mark := make([]bool, n)
	arr := make([][2]int, 0, n)
	for i, x := range nums {
		arr = append(arr, [2]int{x, i})
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] == arr[j][0] {
			return arr[i][1] < arr[j][1]
		}
		return arr[i][0] < arr[j][0]
	})
	ans := make([]int64, len(queries))
	j := 0
	for i, q := range queries {
		index, k := q[0], q[1]
		if !mark[index] {
			mark[index] = true
			s -= int64(nums[index])
		}
		for ; k > 0 && j < n; j++ {
			if !mark[arr[j][1]] {
				mark[arr[j][1]] = true
				s -= int64(arr[j][0])
				k--
			}
		}
		ans[i] = s
	}
	return ans
}
```

#### TypeScript

```ts
function unmarkedSumArray(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    let s = nums.reduce((acc, x) => acc + x, 0);
    const mark: boolean[] = Array(n).fill(false);
    const arr = nums.map((x, i) => [x, i]);
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    let j = 0;
    const ans: number[] = [];
    for (let [index, k] of queries) {
        if (!mark[index]) {
            mark[index] = true;
            s -= nums[index];
        }
        for (; k && j < n; ++j) {
            if (!mark[arr[j][1]]) {
                mark[arr[j][1]] = true;
                s -= arr[j][0];
                --k;
            }
        }
        ans.push(s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
