---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2672.Number%20of%20Adjacent%20Elements%20With%20the%20Same%20Color/README.md
rating: 1705
source: 第 344 场周赛 Q3
tags:
    - 数组
---

<!-- problem:start -->

# [2672. 有相同颜色的相邻元素数目](https://leetcode.cn/problems/number-of-adjacent-elements-with-the-same-color)

[English Version](/solution/2600-2699/2672.Number%20of%20Adjacent%20Elements%20With%20the%20Same%20Color/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数&nbsp;<code>n</code>&nbsp;表示一个长度为&nbsp;<code>n</code> 的数组&nbsp; <code>colors</code>，初始所有元素均为 0 ，表示是 <strong>未染色 </strong>的。同时给定一个二维整数数组&nbsp;<code>queries</code>，其中&nbsp;<code>queries[i] = [index<sub>i</sub>, color<sub>i</sub>]</code>。对于第&nbsp;<code>i</code> 个&nbsp;<strong>查询</strong>：</p>

<ul>
	<li>将&nbsp;<code>colors[index<sub>i</sub>]</code>&nbsp;染色为 <code>color<sub>i</sub></code>。</li>
	<li>统计&nbsp;<code>colors</code>&nbsp;中颜色相同的相邻对的数量（无论 <code>color<sub>i</sub></code>）。</li>
</ul>

<p>请你返回一个长度与 <code>queries</code>&nbsp;相等的数组<em>&nbsp;</em><code>answer</code><em>&nbsp;</em>，其中<em>&nbsp;</em><code>answer[i]</code>是前 <code>i</code>&nbsp;个操作的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]</span></p>

<p><span class="example-io"><b>输出：</b>[0,1,1,0,2]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>一开始 colors = [0,0,0,0]，其中 0 表示数组中未染色的元素。</li>
	<li>在第 1 次查询后&nbsp;colors = [2,0,0,0]。颜色相同的相邻对的数量是 0。</li>
	<li>在第 2&nbsp;次查询后 colors = [2,2,0,0]。颜色相同的相邻对的数量是 1。</li>
	<li>在第 3&nbsp;次查询后 colors = [2,2,0,1]。颜色相同的相邻对的数量是 1。</li>
	<li>在第 4&nbsp;次查询后 colors = [2,1,0,1]。颜色相同的相邻对的数量是 0。</li>
	<li>在第 5&nbsp;次查询后 colors = [2,1,1,1]。颜色相同的相邻对的数量是 2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 1, queries = [[0,100000]]</span></p>

<p><span class="example-io"><b>输出：</b>[0]</span></p>

<p><strong>解释：</strong></p>

<p>在第一次查询后&nbsp;colors = [100000]。颜色相同的相邻对的数量是 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length&nbsp;== 2</code></li>
	<li><code>0 &lt;= index<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>1 &lt;=&nbsp; color<sub>i</sub>&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
