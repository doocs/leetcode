---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1424.Diagonal%20Traverse%20II/README.md
rating: 1779
source: 第 186 场周赛 Q3
tags:
    - 数组
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [1424. 对角线遍历 II](https://leetcode.cn/problems/diagonal-traverse-ii)

[English Version](/solution/1400-1499/1424.Diagonal%20Traverse%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个列表&nbsp;<code>nums</code>&nbsp;，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回&nbsp;<code>nums</code>&nbsp;中对角线上的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1424.Diagonal%20Traverse%20II/images/sample_1_1784.png" style="height: 143px; width: 158px;"></strong></p>

<pre><strong>输入：</strong>nums = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,4,2,7,5,3,8,6,9]
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1424.Diagonal%20Traverse%20II/images/sample_2_1784.png" style="height: 177px; width: 230px;"></strong></p>

<pre><strong>输入：</strong>nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
<strong>输出：</strong>[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
<strong>输出：</strong>[1,4,2,5,3,8,6,9,7,10,11]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [[1,2,3,4,5,6]]
<strong>输出：</strong>[1,2,3,4,5,6]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= nums[i].length &lt;=&nbsp;10^5</code></li>
	<li><code>1 &lt;= nums[i][j] &lt;= 10^9</code></li>
	<li><code>nums</code>&nbsp;中最多有&nbsp;<code>10^5</code>&nbsp;个数字。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们观察到：

-   每一条对角线上的 $i + j$ 的值都是相同的；
-   下一条对角线的 $i + j$ 的值比前一条对角线的大；
-   在同一条对角线中的 $i + j$ 是相同的，而 $j$ 值是从小到大递增。

因此，我们将所有数字以 $(i, j, \textit{nums}[i][j])$ 的形式存进 $\textit{arr}$，然后按照前两项排序。最后返回 $\textit{arr}$ 所有元素下标为 $2$ 的值组成的数组即可。

时间复杂度 $O(n \times \log n)$，其中 $n$ 是数组 $\textit{nums}$ 中元素的个数。空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findDiagonalOrder(self, nums: List[List[int]]) -> List[int]:
        arr = []
        for i, row in enumerate(nums):
            for j, v in enumerate(row):
                arr.append((i + j, j, v))
        arr.sort()
        return [v[2] for v in arr]
```

#### Java

```java
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < nums.size(); ++i) {
            for (int j = 0; j < nums.get(i).size(); ++j) {
                arr.add(new int[] {i + j, j, nums.get(i).get(j)});
            }
        }
        arr.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] ans = new int[arr.size()];
        for (int i = 0; i < arr.size(); ++i) {
            ans[i] = arr.get(i)[2];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& nums) {
        vector<tuple<int, int, int>> arr;
        for (int i = 0; i < nums.size(); ++i) {
            for (int j = 0; j < nums[i].size(); ++j) {
                arr.push_back({i + j, j, nums[i][j]});
            }
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (auto& e : arr) {
            ans.push_back(get<2>(e));
        }
        return ans;
    }
};
```

#### Go

```go
func findDiagonalOrder(nums [][]int) []int {
	arr := [][]int{}
	for i, row := range nums {
		for j, v := range row {
			arr = append(arr, []int{i + j, j, v})
		}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] == arr[j][0] {
			return arr[i][1] < arr[j][1]
		}
		return arr[i][0] < arr[j][0]
	})
	ans := []int{}
	for _, v := range arr {
		ans = append(ans, v[2])
	}
	return ans
}
```

#### TypeScript

```ts
function findDiagonalOrder(nums: number[][]): number[] {
    const arr: number[][] = [];
    for (let i = 0; i < nums.length; ++i) {
        for (let j = 0; j < nums[i].length; ++j) {
            arr.push([i + j, j, nums[i][j]]);
        }
    }
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    return arr.map(x => x[2]);
}
```

#### C#

```cs
public class Solution {
    public int[] FindDiagonalOrder(IList<IList<int>> nums) {
        List<int[]> arr = new List<int[]>();
        for (int i = 0; i < nums.Count; ++i) {
            for (int j = 0; j < nums[i].Count; ++j) {
                arr.Add(new int[] { i + j, j, nums[i][j] });
            }
        }
        arr.Sort((a, b) => a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] ans = new int[arr.Count];
        for (int i = 0; i < arr.Count; ++i) {
            ans[i] = arr[i][2];
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
