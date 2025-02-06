---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1940.Longest%20Common%20Subsequence%20Between%20Sorted%20Arrays/README_EN.md
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [1940. Longest Common Subsequence Between Sorted Arrays 🔒](https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays)

[中文文档](/solution/1900-1999/1940.Longest%20Common%20Subsequence%20Between%20Sorted%20Arrays/README.md)

## Description

<!-- description:start -->

<p>Given an array of integer arrays <code>arrays</code> where each <code>arrays[i]</code> is sorted in <strong>strictly increasing</strong> order, return <em>an integer array representing the <strong>longest common subsequence</strong> among&nbsp;<strong>all</strong> the arrays</em>.</p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from another sequence by deleting some elements (possibly none) without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arrays = [[<u>1</u>,3,<u>4</u>],
                 [<u>1</u>,<u>4</u>,7,9]]
<strong>Output:</strong> [1,4]
<strong>Explanation:</strong> The longest common subsequence in the two arrays is [1,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arrays = [[<u>2</u>,<u>3</u>,<u>6</u>,8],
                 [1,<u>2</u>,<u>3</u>,5,<u>6</u>,7,10],
                 [<u>2</u>,<u>3</u>,4,<u>6</u>,9]]
<strong>Output:</strong> [2,3,6]
<strong>Explanation:</strong> The longest common subsequence in all three arrays is [2,3,6].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arrays = [[1,2,3,4,5],
                 [6,7,8]]
<strong>Output:</strong> []
<strong>Explanation:</strong> There is no common subsequence between the two arrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arrays.length &lt;= 100</code></li>
	<li><code>1 &lt;= arrays[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= arrays[i][j] &lt;= 100</code></li>
	<li><code>arrays[i]</code> is sorted in <strong>strictly increasing</strong> order.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We note that the range of elements is $[1, 100]$, so we can use an array $\textit{cnt}$ of length $101$ to record the number of occurrences of each element.

Since each array in $\textit{arrays}$ is strictly increasing, the elements of the common subsequence must be monotonically increasing, and the number of occurrences of these elements must be equal to the length of $\textit{arrays}$.

Therefore, we can traverse each array in $\textit{arrays}$ and count the number of occurrences of each element. Finally, traverse each element of $\textit{cnt}$ from smallest to largest. If the number of occurrences is equal to the length of $\textit{arrays}$, then this element is one of the elements of the common subsequence, and we add it to the answer array.

After the traversal, return the answer array.

The time complexity is $O(M + N)$, and the space complexity is $O(M)$. Here, $M$ is the range of elements, and in this problem, $M = 101$, and $N$ is the total number of elements in the arrays.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommonSubsequence(self, arrays: List[List[int]]) -> List[int]:
        cnt = [0] * 101
        for row in arrays:
            for x in row:
                cnt[x] += 1
        return [x for x, v in enumerate(cnt) if v == len(arrays)]
```

#### Java

```java
class Solution {
    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        int[] cnt = new int[101];
        for (var row : arrays) {
            for (int x : row) {
                ++cnt[x];
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 101; ++i) {
            if (cnt[i] == arrays.length) {
                ans.add(i);
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
    vector<int> longestCommonSubsequence(vector<vector<int>>& arrays) {
        int cnt[101]{};
        for (const auto& row : arrays) {
            for (int x : row) {
                ++cnt[x];
            }
        }
        vector<int> ans;
        for (int i = 0; i < 101; ++i) {
            if (cnt[i] == arrays.size()) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestCommonSubsequence(arrays [][]int) (ans []int) {
	cnt := [101]int{}
	for _, row := range arrays {
		for _, x := range row {
			cnt[x]++
		}
	}
	for x, v := range cnt {
		if v == len(arrays) {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function longestCommonSubsequence(arrays: number[][]): number[] {
    const cnt: number[] = Array(101).fill(0);
    for (const row of arrays) {
        for (const x of row) {
            ++cnt[x];
        }
    }
    const ans: number[] = [];
    for (let i = 0; i < 101; ++i) {
        if (cnt[i] === arrays.length) {
            ans.push(i);
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} arrays
 * @return {number[]}
 */
var longestCommonSubsequence = function (arrays) {
    const cnt = Array(101).fill(0);
    for (const row of arrays) {
        for (const x of row) {
            ++cnt[x];
        }
    }
    const ans = [];
    for (let i = 0; i < 101; ++i) {
        if (cnt[i] === arrays.length) {
            ans.push(i);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
