---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0985.Sum%20of%20Even%20Numbers%20After%20Queries/README_EN.md
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [985. Sum of Even Numbers After Queries](https://leetcode.com/problems/sum-of-even-numbers-after-queries)

[中文文档](/solution/0900-0999/0985.Sum%20of%20Even%20Numbers%20After%20Queries/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an array <code>queries</code> where <code>queries[i] = [val<sub>i</sub>, index<sub>i</sub>]</code>.</p>

<p>For each query <code>i</code>, first, apply <code>nums[index<sub>i</sub>] = nums[index<sub>i</sub>] + val<sub>i</sub></code>, then print the sum of the even values of <code>nums</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em> where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
<strong>Output:</strong> [8,6,2,4]
<strong>Explanation:</strong> At the beginning, the array is [1,2,3,4].
After adding 1 to nums[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
After adding -3 to nums[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
After adding -4 to nums[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
After adding 2 to nums[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], queries = [[4,0]]
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= val<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= index<sub>i</sub> &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use an integer variable $\textit{s}$ to record the sum of all even numbers in the array $\textit{nums}$. Initially, $\textit{s}$ is the sum of all even numbers in the array $\textit{nums}$.

For each query $(v, i)$, we first check if $\textit{nums}[i]$ is even. If $\textit{nums}[i]$ is even, we subtract $\textit{nums}[i]$ from $\textit{s}$. Then, we add $v$ to $\textit{nums}[i]$. If $\textit{nums}[i]$ is even, we add $\textit{nums}[i]$ to $\textit{s}$, and then add $\textit{s}$ to the answer array.

The time complexity is $O(n + m)$, where $n$ and $m$ are the lengths of the arrays $\textit{nums}$ and $\textit{queries}$, respectively. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumEvenAfterQueries(
        self, nums: List[int], queries: List[List[int]]
    ) -> List[int]:
        s = sum(x for x in nums if x % 2 == 0)
        ans = []
        for v, i in queries:
            if nums[i] % 2 == 0:
                s -= nums[i]
            nums[i] += v
            if nums[i] % 2 == 0:
                s += nums[i]
            ans.append(s)
        return ans
```

#### Java

```java
class Solution {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int s = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                s += x;
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        int k = 0;
        for (var q : queries) {
            int v = q[0], i = q[1];
            if (nums[i] % 2 == 0) {
                s -= nums[i];
            }
            nums[i] += v;
            if (nums[i] % 2 == 0) {
                s += nums[i];
            }
            ans[k++] = s;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sumEvenAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        int s = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                s += x;
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int v = q[0], i = q[1];
            if (nums[i] % 2 == 0) {
                s -= nums[i];
            }
            nums[i] += v;
            if (nums[i] % 2 == 0) {
                s += nums[i];
            }
            ans.push_back(s);
        }
        return ans;
    }
};
```

#### Go

```go
func sumEvenAfterQueries(nums []int, queries [][]int) (ans []int) {
	s := 0
	for _, x := range nums {
		if x%2 == 0 {
			s += x
		}
	}
	for _, q := range queries {
		v, i := q[0], q[1]
		if nums[i]%2 == 0 {
			s -= nums[i]
		}
		nums[i] += v
		if nums[i]%2 == 0 {
			s += nums[i]
		}
		ans = append(ans, s)
	}
	return
}
```

#### TypeScript

```ts
function sumEvenAfterQueries(nums: number[], queries: number[][]): number[] {
    let s = nums.reduce((acc, x) => acc + (x % 2 === 0 ? x : 0), 0);
    const ans: number[] = [];
    for (const [v, i] of queries) {
        if (nums[i] % 2 === 0) {
            s -= nums[i];
        }
        nums[i] += v;
        if (nums[i] % 2 === 0) {
            s += nums[i];
        }
        ans.push(s);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn sum_even_after_queries(mut nums: Vec<i32>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let mut s: i32 = nums.iter().filter(|&x| x % 2 == 0).sum();
        let mut ans = Vec::with_capacity(queries.len());

        for query in queries {
            let (v, i) = (query[0], query[1] as usize);
            if nums[i] % 2 == 0 {
                s -= nums[i];
            }
            nums[i] += v;
            if nums[i] % 2 == 0 {
                s += nums[i];
            }
            ans.push(s);
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number[][]} queries
 * @return {number[]}
 */
var sumEvenAfterQueries = function (nums, queries) {
    let s = nums.reduce((acc, cur) => acc + (cur % 2 === 0 ? cur : 0), 0);
    const ans = [];
    for (const [v, i] of queries) {
        if (nums[i] % 2 === 0) {
            s -= nums[i];
        }
        nums[i] += v;
        if (nums[i] % 2 === 0) {
            s += nums[i];
        }
        ans.push(s);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int[] SumEvenAfterQueries(int[] nums, int[][] queries) {
        int s = nums.Where(x => x % 2 == 0).Sum();
        int[] ans = new int[queries.Length];

        for (int j = 0; j < queries.Length; j++) {
            int v = queries[j][0], i = queries[j][1];
            if (nums[i] % 2 == 0) {
                s -= nums[i];
            }
            nums[i] += v;
            if (nums[i] % 2 == 0) {
                s += nums[i];
            }
            ans[j] = s;
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
