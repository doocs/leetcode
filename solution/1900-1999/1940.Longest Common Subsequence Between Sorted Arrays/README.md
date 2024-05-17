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

<p>给定一个由整数数组组成的数组<code>arrays</code>，其中<code>arrays[i]</code>是严格递增排序的，返回一个表示所有数组之间的最长公共子序列的整数数组。</p>

<p>子序列是从另一个序列派生出来的序列，删除一些元素或不删除任何元素，而不改变其余元素的顺序。</p>

<p><strong>示例1:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[<strong><em>1</em></strong>,3,<strong><em>4</em></strong>],
               [<strong><em>1</em></strong>,<strong><em>4</em></strong>,7,9]]
<strong>输出:</strong> [1,4]
<strong>解释:</strong> 这两个数组中的最长子序列是[1,4]。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>6</em></strong>,8],
               [1,<strong><em>2</em></strong>,<strong><em>3</em></strong>,5,<strong><em>6</em></strong>,7,10],
               [<strong><em>2</em></strong>,<strong><em>3</em></strong>,4,<em><strong>6</strong></em>,9]]
<strong>输出:</strong> [2,3,6]
<strong>解释:</strong> 这三个数组中的最长子序列是[2,3,6]。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[1,2,3,4,5],
               [6,7,8]]
<strong>输出:</strong> []
<strong>解释:</strong> 这两个数组之间没有公共子序列。
</pre>

<p> </p>

<p><strong>限制条件:</strong></p>

<ul>
	<li><code>2 <= arrays.length <= 100</code></li>
	<li><code>1 <= arrays[i].length <= 100</code></li>
	<li><code>1 <= arrays[i][j] <= 100</code></li>
	<li><code>arrays[i]</code> 是严格递增排序.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommomSubsequence(self, arrays: List[List[int]]) -> List[int]:
        n = len(arrays)
        counter = defaultdict(int)
        for array in arrays:
            for e in array:
                counter[e] += 1
        return [e for e, count in counter.items() if count == n]
```

#### Java

```java
class Solution {
    public List<Integer> longestCommomSubsequence(int[][] arrays) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int[] array : arrays) {
            for (int e : array) {
                counter.put(e, counter.getOrDefault(e, 0) + 1);
            }
        }
        int n = arrays.length;
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == n) {
                res.add(entry.getKey());
            }
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> longestCommomSubsequence(vector<vector<int>>& arrays) {
        unordered_map<int, int> counter;
        vector<int> res;
        int n = arrays.size();
        for (auto array : arrays) {
            for (auto e : array) {
                counter[e] += 1;
                if (counter[e] == n) {
                    res.push_back(e);
                }
            }
        }
        return res;
    }
};
```

#### Go

```go
func longestCommomSubsequence(arrays [][]int) []int {
	counter := make(map[int]int)
	n := len(arrays)
	var res []int
	for _, array := range arrays {
		for _, e := range array {
			counter[e]++
			if counter[e] == n {
				res = append(res, e)
			}
		}
	}
	return res
}
```

#### JavaScript

```js
/**
 * @param {number[][]} arrays
 * @return {number[]}
 */
var longestCommonSubsequence = function (arrays) {
    const m = new Map();
    const rs = [];
    const len = arrays.length;
    for (let i = 0; i < len; i++) {
        for (let j = 0; j < arrays[i].length; j++) {
            m.set(arrays[i][j], (m.get(arrays[i][j]) || 0) + 1);
            if (m.get(arrays[i][j]) === len) rs.push(arrays[i][j]);
        }
    }
    return rs;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommomSubsequence(self, arrays: List[List[int]]) -> List[int]:
        def common(l1, l2):
            i, j, n1, n2 = 0, 0, len(l1), len(l2)
            res = []
            while i < n1 and j < n2:
                if l1[i] == l2[j]:
                    res.append(l1[i])
                    i += 1
                    j += 1
                elif l1[i] > l2[j]:
                    j += 1
                else:
                    i += 1
            return res

        n = len(arrays)
        for i in range(1, n):
            arrays[i] = common(arrays[i - 1], arrays[i])
        return arrays[n - 1]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
