---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2389.Longest%20Subsequence%20With%20Limited%20Sum/README_EN.md
rating: 1387
source: Weekly Contest 308 Q1
tags:
    - Greedy
    - Array
    - Binary Search
    - Prefix Sum
    - Sorting
---

<!-- problem:start -->

# [2389. Longest Subsequence With Limited Sum](https://leetcode.com/problems/longest-subsequence-with-limited-sum)

[中文文档](/solution/2300-2399/2389.Longest%20Subsequence%20With%20Limited%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, and an integer array <code>queries</code> of length <code>m</code>.</p>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>m</code><em> where </em><code>answer[i]</code><em> is the <strong>maximum</strong> size of a <strong>subsequence</strong> that you can take from </em><code>nums</code><em> such that the <strong>sum</strong> of its elements is less than or equal to </em><code>queries[i]</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,2,1], queries = [3,10,21]
<strong>Output:</strong> [2,3,4]
<strong>Explanation:</strong> We answer the queries as follows:
- The subsequence [2,1] has a sum less than or equal to 3. It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
- The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
- The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4,5], queries = [1]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], queries[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Prefix Sum + Binary Search

According to the problem description, for each $queries[i]$, we need to find a subsequence such that the sum of its elements does not exceed $queries[i]$ and the length of this subsequence is maximized. Obviously, we should choose the smallest possible elements to maximize the length of the subsequence.

Therefore, we can first sort the array $nums$ in ascending order. Then, for each $queries[i]$, we can use binary search to find the smallest index $j$ such that $nums[0] + nums[1] + \cdots + nums[j] \gt queries[i]$. At this point, $nums[0] + nums[1] + \cdots + nums[j - 1]$ is the sum of the elements of the subsequence that meets the condition, and the length of this subsequence is $j$. Therefore, we can add $j$ to the answer array.

The time complexity is $O((n + m) \times \log n)$, and the space complexity is $O(n)$ or $O(\log n)$. Here, $n$ and $m$ are the lengths of the arrays $nums$ and $queries$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        s = list(accumulate(nums))
        return [bisect_right(s, q) for q in queries]
```

#### Java

```java
class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = search(nums, queries[i]);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
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
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        for (int i = 1; i < nums.size(); i++) {
            nums[i] += nums[i - 1];
        }
        vector<int> ans;
        for (auto& q : queries) {
            ans.push_back(upper_bound(nums.begin(), nums.end(), q) - nums.begin());
        }
        return ans;
    }
};
```

#### Go

```go
func answerQueries(nums []int, queries []int) (ans []int) {
	sort.Ints(nums)
	for i := 1; i < len(nums); i++ {
		nums[i] += nums[i-1]
	}
	for _, q := range queries {
		ans = append(ans, sort.SearchInts(nums, q+1))
	}
	return
}
```

#### TypeScript

```ts
function answerQueries(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    for (let i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
    }
    const ans: number[] = [];
    const search = (nums: number[], x: number) => {
        let l = 0;
        let r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (const q of queries) {
        ans.push(search(nums, q));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn answer_queries(mut nums: Vec<i32>, queries: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        nums.sort();
        queries
            .into_iter()
            .map(|query| {
                let mut sum = 0;
                for i in 0..n {
                    sum += nums[i];
                    if sum > query {
                        return i as i32;
                    }
                }
                n as i32
            })
            .collect()
    }
}
```

#### C#

```cs
public class Solution {
    public int[] AnswerQueries(int[] nums, int[] queries) {
        int[] result = new int[queries.Length];
        Array.Sort(nums);
        for (int i = 0; i < queries.Length; i++) {
            result[i] = getSubsequent(nums, queries[i]);
        }
        return result;

    }

    public int getSubsequent(int[] nums,int query) {
        int sum = 0;
        for (int i = 0; i < nums.Length; i++) {
            sum += nums[i];
            if (sum > query) {
                return i;
            }
        }
        return nums.Length;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorting + Offline Query + Two Pointers

Similar to Solution 1, we can first sort the array $nums$ in ascending order.

Next, we define an index array $idx$ of the same length as $queries$, where $idx[i] = i$. Then, we sort the array $idx$ in ascending order based on the values in $queries$. This way, we can process the elements in $queries$ in ascending order.

We use a variable $s$ to record the sum of the currently selected elements and a variable $j$ to record the number of currently selected elements. Initially, $s = j = 0$.

We traverse the index array $idx$, and for each index $i$ in it, we iteratively add elements from the array $nums$ to the current subsequence until $s + nums[j] \gt queries[i]$. At this point, $j$ is the length of the subsequence that meets the condition. We set the value of $ans[i]$ to $j$ and then continue to process the next index.

After traversing the index array $idx$, we obtain the answer array $ans$, where $ans[i]$ is the length of the subsequence that satisfies $queries[i]$.

The time complexity is $O(n \times \log n + m)$, and the space complexity is $O(m)$. Here, $n$ and $m$ are the lengths of the arrays $nums$ and $queries$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        m = len(queries)
        ans = [0] * m
        idx = sorted(range(m), key=lambda i: queries[i])
        s = j = 0
        for i in idx:
            while j < len(nums) and s + nums[j] <= queries[i]:
                s += nums[j]
                j += 1
            ans[i] = j
        return ans
```

#### Java

```java
class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int m = queries.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[i] - queries[j]);
        int[] ans = new int[m];
        int s = 0, j = 0;
        for (int i : idx) {
            while (j < nums.length && s + nums[j] <= queries[i]) {
                s += nums[j++];
            }
            ans[i] = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        int m = queries.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return queries[i] < queries[j];
        });
        vector<int> ans(m);
        int s = 0, j = 0;
        for (int i : idx) {
            while (j < nums.size() && s + nums[j] <= queries[i]) {
                s += nums[j++];
            }
            ans[i] = j;
        }
        return ans;
    }
};
```

#### Go

```go
func answerQueries(nums []int, queries []int) (ans []int) {
	sort.Ints(nums)
	m := len(queries)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[i]] < queries[idx[j]] })
	ans = make([]int, m)
	s, j := 0, 0
	for _, i := range idx {
		for j < len(nums) && s+nums[j] <= queries[i] {
			s += nums[j]
			j++
		}
		ans[i] = j
	}
	return
}
```

#### TypeScript

```ts
function answerQueries(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    const m = queries.length;
    const idx: number[] = new Array(m);
    for (let i = 0; i < m; i++) {
        idx[i] = i;
    }
    idx.sort((i, j) => queries[i] - queries[j]);
    const ans: number[] = new Array(m);
    let s = 0;
    let j = 0;
    for (const i of idx) {
        while (j < nums.length && s + nums[j] <= queries[i]) {
            s += nums[j++];
        }
        ans[i] = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
