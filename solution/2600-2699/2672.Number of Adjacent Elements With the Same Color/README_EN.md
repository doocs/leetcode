---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2672.Number%20of%20Adjacent%20Elements%20With%20the%20Same%20Color/README_EN.md
rating: 1705
source: Weekly Contest 344 Q3
tags:
    - Array
---

<!-- problem:start -->

# [2672. Number of Adjacent Elements With the Same Color](https://leetcode.com/problems/number-of-adjacent-elements-with-the-same-color)

[中文文档](/solution/2600-2699/2672.Number%20of%20Adjacent%20Elements%20With%20the%20Same%20Color/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing an array <code>colors</code> of length <code>n</code> where all elements are set to 0&#39;s meaning <strong>uncolored</strong>. You are also given a 2D integer array <code>queries</code> where <code>queries[i] = [index<sub>i</sub>, color<sub>i</sub>]</code>. For the <code>i<sup>th</sup></code> <strong>query</strong>:</p>

<ul>
	<li>Set <code>colors[index<sub>i</sub>]</code> to <code>color<sub>i</sub></code>.</li>
	<li>Count the number of adjacent pairs in <code>colors</code> which have the same color (regardless of <code>color<sub>i</sub></code>).</li>
</ul>

<p>Return an array <code>answer</code> of the same length as <code>queries</code> where <code>answer[i]</code> is the answer to the <code>i<sup>th</sup></code> query.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1,1,0,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Initially array colors = [0,0,0,0], where 0 denotes uncolored elements of the array.</li>
	<li>After the 1<sup>st</sup> query colors = [2,0,0,0]. The count of adjacent pairs with the same color is 0.</li>
	<li>After the 2<sup>nd</sup> query colors = [2,2,0,0]. The count of adjacent pairs with the same color is 1.</li>
	<li>After the 3<sup>rd</sup> query colors = [2,2,0,1]. The count of adjacent pairs with the same color is 1.</li>
	<li>After the 4<sup>th</sup> query colors = [2,1,0,1]. The count of adjacent pairs with the same color is 0.</li>
	<li>After the 5<sup>th</sup> query colors = [2,1,1,1]. The count of adjacent pairs with the same color is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, queries = [[0,100000]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>

<p><strong>Explanation:</strong></p>

<p>After the 1<sup>st</sup> query colors = [100000]. The count of adjacent pairs with the same color is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length&nbsp;== 2</code></li>
	<li><code>0 &lt;= index<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>1 &lt;=&nbsp; color<sub>i</sub>&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def colorTheArray(self, n: int, queries: List[List[int]]) -> List[int]:
        nums = [0] * n
        ans = [0] * len(queries)
        x = 0
        for k, (i, c) in enumerate(queries):
            if i > 0 and nums[i] and nums[i - 1] == nums[i]:
                x -= 1
            if i < n - 1 and nums[i] and nums[i + 1] == nums[i]:
                x -= 1
            if i > 0 and nums[i - 1] == c:
                x += 1
            if i < n - 1 and nums[i + 1] == c:
                x += 1
            ans[k] = x
            nums[i] = c
        return ans
```

#### Java

```java
class Solution {
    public int[] colorTheArray(int n, int[][] queries) {
        int m = queries.length;
        int[] nums = new int[n];
        int[] ans = new int[m];
        for (int k = 0, x = 0; k < m; ++k) {
            int i = queries[k][0], c = queries[k][1];
            if (i > 0 && nums[i] > 0 && nums[i - 1] == nums[i]) {
                --x;
            }
            if (i < n - 1 && nums[i] > 0 && nums[i + 1] == nums[i]) {
                --x;
            }
            if (i > 0 && nums[i - 1] == c) {
                ++x;
            }
            if (i < n - 1 && nums[i + 1] == c) {
                ++x;
            }
            ans[k] = x;
            nums[i] = c;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> colorTheArray(int n, vector<vector<int>>& queries) {
        vector<int> nums(n);
        vector<int> ans;
        int x = 0;
        for (auto& q : queries) {
            int i = q[0], c = q[1];
            if (i > 0 && nums[i] > 0 && nums[i - 1] == nums[i]) {
                --x;
            }
            if (i < n - 1 && nums[i] > 0 && nums[i + 1] == nums[i]) {
                --x;
            }
            if (i > 0 && nums[i - 1] == c) {
                ++x;
            }
            if (i < n - 1 && nums[i + 1] == c) {
                ++x;
            }
            ans.push_back(x);
            nums[i] = c;
        }
        return ans;
    }
};
```

#### Go

```go
func colorTheArray(n int, queries [][]int) (ans []int) {
	nums := make([]int, n)
	x := 0
	for _, q := range queries {
		i, c := q[0], q[1]
		if i > 0 && nums[i] > 0 && nums[i-1] == nums[i] {
			x--
		}
		if i < n-1 && nums[i] > 0 && nums[i+1] == nums[i] {
			x--
		}
		if i > 0 && nums[i-1] == c {
			x++
		}
		if i < n-1 && nums[i+1] == c {
			x++
		}
		ans = append(ans, x)
		nums[i] = c
	}
	return
}
```

#### TypeScript

```ts
function colorTheArray(n: number, queries: number[][]): number[] {
    const nums: number[] = new Array(n).fill(0);
    const ans: number[] = [];
    let x = 0;
    for (const [i, c] of queries) {
        if (i > 0 && nums[i] > 0 && nums[i - 1] == nums[i]) {
            --x;
        }
        if (i < n - 1 && nums[i] > 0 && nums[i + 1] == nums[i]) {
            --x;
        }
        if (i > 0 && nums[i - 1] == c) {
            ++x;
        }
        if (i < n - 1 && nums[i + 1] == c) {
            ++x;
        }
        ans.push(x);
        nums[i] = c;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
