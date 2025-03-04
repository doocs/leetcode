---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1940.Longest%20Common%20Subsequence%20Between%20Sorted%20Arrays/README.md
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [1940. 排序数组之间的最长公共子序列 🔒](https://leetcode.cn/problems/longest-common-subsequence-between-sorted-arrays)

[English Version](/solution/1900-1999/1940.Longest%20Common%20Subsequence%20Between%20Sorted%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由整数数组组成的数组&nbsp;<code>arrays</code>，其中&nbsp;<code>arrays[i]</code>&nbsp;是 <strong>严格递增</strong> 排序的，返回一个 <strong>所有</strong> 数组均包含的 <strong>最长公共子序列</strong> 的整数数组。</p>

<p><strong>子序列</strong> 是从另一个序列派生出来的序列，删除一些元素或不删除任何元素，而不改变其余元素的顺序。</p>

<p><strong>示例1:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[<u>1</u>,3,<u>4</u>],
&nbsp;              [<u>1</u>,<u>4</u>,7,9]]
<strong>输出:</strong> [1,4]
<strong>解释:</strong>&nbsp;这两个数组中的最长子序列是[1,4]。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[<u>2</u>,<u>3</u>,<u>6</u>,8],
&nbsp;              [1,<u>2</u>,<u>3</u>,5,<u>6</u>,7,10],
&nbsp;              [<u>2</u>,<u>3</u>,4,<u>6</u>,9]]
<strong>输出:</strong> [2,3,6]
<strong>解释:</strong>&nbsp;这三个数组中的最长子序列是 [2,3,6]。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[1,2,3,4,5],
&nbsp;              [6,7,8]]
<strong>输出:</strong> []
<strong>解释:</strong>&nbsp;这两个数组之间没有公共子序列。
</pre>

<p>&nbsp;</p>

<p><strong>限制条件:</strong></p>

<ul>
	<li><code>2 &lt;= arrays.length &lt;= 100</code></li>
	<li><code>1 &lt;= arrays[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= arrays[i][j] &lt;= 100</code></li>
	<li><code>arrays[i]</code> 是严格递增排序.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们注意到，元素的范围是 $[1, 100]$，我们可以用一个长度为 $101$ 的数组 $\textit{cnt}$ 来记录每个元素出现的次数。

由于数组 $\textit{arrays}$ 中的每个数组都是严格递增排序的，因此，公共子序列的元素必然是单调递增，并且这些元素的出现次数都等于 $\textit{arrays}$ 的长度。

因此，我们可以遍历 $\textit{arrays}$ 中的每个数组，统计每个元素的出现次数，最后，从小到大遍历 $\textit{cnt}$ 的每个元素，如果出现次数等于 $\textit{arrays}$ 的长度，那么这个元素就是公共子序列的元素之一，我们将其加入答案数组中。

遍历结束后，返回答案数组即可。

时间复杂度 $O(M + N)$，空间复杂度 $O(M)$。其中 $M$ 为元素的范围，本题中 $M = 101$，而 $N$ 为数组所有元素的个数。

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
