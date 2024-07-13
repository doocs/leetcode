---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2679.Sum%20in%20a%20Matrix/README.md
rating: 1333
source: 第 104 场双周赛 Q2
tags:
    - 数组
    - 矩阵
    - 排序
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [2679. 矩阵中的和](https://leetcode.cn/problems/sum-in-a-matrix)

[English Version](/solution/2600-2699/2679.Sum%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>nums</code>&nbsp;。一开始你的分数为&nbsp;<code>0</code>&nbsp;。你需要执行以下操作直到矩阵变为空：</p>

<ol>
	<li>矩阵中每一行选取最大的一个数，并删除它。如果一行中有多个最大的数，选择任意一个并删除。</li>
	<li>在步骤 1 删除的所有数字中找到最大的一个数字，将它添加到你的 <strong>分数</strong>&nbsp;中。</li>
</ol>

<p>请你返回最后的 <strong>分数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
<b>输出：</b>15
<b>解释：</b>第一步操作中，我们删除 7 ，6 ，6 和 3 ，将分数增加 7 。下一步操作中，删除 2 ，4 ，5 和 2 ，将分数增加 5 。最后删除 1 ，2 ，3 和 1 ，将分数增加 3 。所以总得分为 7 + 5 + 3 = 15 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [[1]]
<b>输出：</b>1
<b>解释：</b>我们删除 1 并将分数增加 1 ，所以返回 1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 300</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 500</code></li>
	<li><code>0 &lt;= nums[i][j] &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们可以先遍历矩阵的每一行，将每一行排序。

接下来，遍历矩阵的每一列，找到每一列的最大值，将这些最大值相加即可。

时间复杂度 $O(m \times n \times \log n)$，空间复杂度 $(\log n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def matrixSum(self, nums: List[List[int]]) -> int:
        for row in nums:
            row.sort()
        return sum(map(max, zip(*nums)))
```

#### Java

```java
class Solution {
    public int matrixSum(int[][] nums) {
        for (var row : nums) {
            Arrays.sort(row);
        }
        int ans = 0;
        for (int j = 0; j < nums[0].length; ++j) {
            int mx = 0;
            for (var row : nums) {
                mx = Math.max(mx, row[j]);
            }
            ans += mx;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int matrixSum(vector<vector<int>>& nums) {
        for (auto& row : nums) {
            sort(row.begin(), row.end());
        }
        int ans = 0;
        for (int j = 0; j < nums[0].size(); ++j) {
            int mx = 0;
            for (auto& row : nums) {
                mx = max(mx, row[j]);
            }
            ans += mx;
        }
        return ans;
    }
};
```

#### Go

```go
func matrixSum(nums [][]int) (ans int) {
	for _, row := range nums {
		sort.Ints(row)
	}
	for i := 0; i < len(nums[0]); i++ {
		mx := 0
		for _, row := range nums {
			mx = max(mx, row[i])
		}
		ans += mx
	}
	return
}
```

#### TypeScript

```ts
function matrixSum(nums: number[][]): number {
    for (const row of nums) {
        row.sort((a, b) => a - b);
    }
    let ans = 0;
    for (let j = 0; j < nums[0].length; ++j) {
        let mx = 0;
        for (const row of nums) {
            mx = Math.max(mx, row[j]);
        }
        ans += mx;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn matrix_sum(mut nums: Vec<Vec<i32>>) -> i32 {
        for row in &mut nums {
            row.sort();
        }
        (0..nums[0].len())
            .map(|col| nums.iter().map(|row| row[col]).max().unwrap())
            .sum()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
