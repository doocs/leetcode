---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README.md
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [3355. 零数组变换 I](https://leetcode.cn/problems/zero-array-transformation-i)

[English Version](/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组 <code>nums</code> 和一个二维数组 <code>queries</code>，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>。</p>

<p>对于每个查询&nbsp;<code>queries[i]</code>：</p>

<ul>
	<li>在&nbsp;<code>nums</code>&nbsp;的下标范围&nbsp;<code>[l<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;内选择一个下标子集。</li>
	<li>将选中的每个下标对应的元素值减 1。</li>
</ul>

<p><strong>零数组&nbsp;</strong>是指所有元素都等于 0 的数组。</p>

<p>如果在按顺序处理所有查询后，可以将 <code>nums</code> 转换为&nbsp;<strong>零数组&nbsp;</strong>，则返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>数组的&nbsp;<strong>子集&nbsp;</strong>是对数组元素的选择（可能为空）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,0,1], queries = [[0,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>对于 i = 0：</strong>

    <ul>
    	<li>选择下标子集 <code>[0, 2]</code> 并将这些下标处的值减 1。</li>
    	<li>数组将变为 <code>[0, 0, 0]</code>，这是一个零数组。</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,3,2,1], queries = [[1,3],[0,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>对于 i = 0：</strong>&nbsp;

    <ul>
    	<li>选择下标子集 <code>[1, 2, 3]</code> 并将这些下标处的值减 1。</li>
    	<li>数组将变为 <code>[4, 2, 1, 0]</code>。</li>
    </ul>
    </li>
    <li><strong>对于 i = 1：</strong>
    <ul>
    	<li>选择下标子集 <code>[0, 1, 2]</code> 并将这些下标处的值减 1。</li>
    	<li>数组将变为 <code>[3, 1, 0, 0]</code>，这不是一个零数组。</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

我们可以使用差分数组来解决这个问题。

定义一个长度为 $n + 1$ 的数组 $d$，初始值全部为 $0$。对于每个查询 $[l, r]$，我们将 $d[l]$ 加 $1$，将 $d[r + 1]$ 减 $1$。

然后我们遍历数组 $d$ 在 $[0, n - 1]$ 范围内的每个元素，累加前缀和 $s$，如果 $\textit{nums}[i] > s$，说明 $\textit{nums}$ 不能转换为零数组，返回 $\textit{false}$。

遍历结束后，返回 $\textit{true}$。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为数组 $\textit{nums}$ 和 $\textit{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isZeroArray(self, nums: List[int], queries: List[List[int]]) -> bool:
        d = [0] * (len(nums) + 1)
        for l, r in queries:
            d[l] += 1
            d[r + 1] -= 1
        s = 0
        for x, y in zip(nums, d):
            s += y
            if x > s:
                return False
        return True
```

#### Java

```java
class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] d = new int[n + 1];
        for (var q : queries) {
            int l = q[0], r = q[1];
            ++d[l];
            --d[r + 1];
        }
        for (int i = 0, s = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isZeroArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int d[n + 1];
        memset(d, 0, sizeof(d));
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            ++d[l];
            --d[r + 1];
        }
        for (int i = 0, s = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isZeroArray(nums []int, queries [][]int) bool {
	d := make([]int, len(nums)+1)
	for _, q := range queries {
		l, r := q[0], q[1]
		d[l]++
		d[r+1]--
	}
	s := 0
	for i, x := range nums {
		s += d[i]
		if x > s {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isZeroArray(nums: number[], queries: number[][]): boolean {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    for (const [l, r] of queries) {
        ++d[l];
        --d[r + 1];
    }
    for (let i = 0, s = 0; i < n; ++i) {
        s += d[i];
        if (nums[i] > s) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
