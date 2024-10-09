---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2295.Replace%20Elements%20in%20an%20Array/README.md
rating: 1445
source: 第 296 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 模拟
---

<!-- problem:start -->

# [2295. 替换数组中的元素](https://leetcode.cn/problems/replace-elements-in-an-array)

[English Version](/solution/2200-2299/2295.Replace%20Elements%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，它包含 <code>n</code>&nbsp;个 <strong>互不相同</strong>&nbsp;的正整数。请你对这个数组执行 <code>m</code>&nbsp;个操作，在第 <code>i</code>&nbsp;个操作中，你需要将数字&nbsp;<code>operations[i][0]</code> 替换成&nbsp;<code>operations[i][1]</code>&nbsp;。</p>

<p>题目保证在第 <code>i</code>&nbsp;个操作中：</p>

<ul>
	<li><code>operations[i][0]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中存在。</li>
	<li><code>operations[i][1]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中不存在。</li>
</ul>

<p>请你返回执行完所有操作后的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
<b>输出：</b>[3,2,7,1]
<b>解释：</b>我们对 nums 执行以下操作：
- 将数字 1 替换为 3 。nums 变为 [<em><strong>3</strong></em>,2,4,6] 。
- 将数字 4 替换为 7 。nums 变为 [3,2,<em><strong>7</strong></em>,6] 。
- 将数字 6 替换为 1 。nums 变为 [3,2,7,<em><strong>1</strong></em>] 。
返回最终数组 [3,2,7,1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2], operations = [[1,3],[2,1],[3,2]]
<b>输出：</b>[2,1]
<b>解释：</b>我们对 nums 执行以下操作：
- 将数字 1 替换为 3 。nums 变为 [<em><strong>3</strong></em>,2] 。
- 将数字 2 替换为 1 。nums 变为 [3,<em><strong>1</strong></em>] 。
- 将数字 3 替换为 2 。nums 变为 [<em><strong>2</strong></em>,1] 。
返回最终数组 [2,1] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == operations.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code>&nbsp;中所有数字 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>operations[i].length == 2</code></li>
	<li><code>1 &lt;= nums[i], operations[i][0], operations[i][1] &lt;= 10<sup>6</sup></code></li>
	<li>在执行第&nbsp;<code>i</code> 个操作时，<code>operations[i][0]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中存在。</li>
	<li>在执行第&nbsp;<code>i</code>&nbsp;个操作时，<code>operations[i][1]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中不存在。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们先用哈希表 $d$ 记录数组 $\textit{nums}$ 中每个数字的下标，然后遍历操作数组 $\textit{operations}$，对于每个操作 $[x, y]$，我们将 $x$ 在 $\textit{nums}$ 中的下标 $d[x]$ 对应的数字替换为 $y$，并更新 $d$ 中 $y$ 的下标为 $d[x]$。

最后返回 $\textit{nums}$ 即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $\textit{nums}$ 的长度和操作数组 $\textit{operations}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arrayChange(self, nums: List[int], operations: List[List[int]]) -> List[int]:
        d = {x: i for i, x in enumerate(nums)}
        for x, y in operations:
            nums[d[x]] = y
            d[y] = d[x]
        return nums
```

#### Java

```java
class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, Integer> d = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            d.put(nums[i], i);
        }
        for (var op : operations) {
            int x = op[0], y = op[1];
            nums[d.get(x)] = y;
            d.put(y, d.get(x));
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> arrayChange(vector<int>& nums, vector<vector<int>>& operations) {
        unordered_map<int, int> d;
        for (int i = 0; i < nums.size(); ++i) {
            d[nums[i]] = i;
        }
        for (auto& op : operations) {
            int x = op[0], y = op[1];
            nums[d[x]] = y;
            d[y] = d[x];
        }
        return nums;
    }
};
```

#### Go

```go
func arrayChange(nums []int, operations [][]int) []int {
	d := map[int]int{}
	for i, x := range nums {
		d[x] = i
	}
	for _, op := range operations {
		x, y := op[0], op[1]
		nums[d[x]] = y
		d[y] = d[x]
	}
	return nums
}
```

#### TypeScript

```ts
function arrayChange(nums: number[], operations: number[][]): number[] {
    const d: Map<number, number> = new Map(nums.map((x, i) => [x, i]));
    for (const [x, y] of operations) {
        nums[d.get(x)!] = y;
        d.set(y, d.get(x)!);
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
