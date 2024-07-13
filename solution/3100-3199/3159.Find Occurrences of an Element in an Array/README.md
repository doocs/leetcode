---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3159.Find%20Occurrences%20of%20an%20Element%20in%20an%20Array/README.md
rating: 1262
source: 第 131 场双周赛 Q2
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3159. 查询数组中元素的出现位置](https://leetcode.cn/problems/find-occurrences-of-an-element-in-an-array)

[English Version](/solution/3100-3199/3159.Find%20Occurrences%20of%20an%20Element%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，一个整数数组&nbsp;<code>queries</code>&nbsp;和一个整数&nbsp;<code>x</code>&nbsp;。</p>

<p>对于每个查询&nbsp;<code>queries[i]</code>&nbsp;，你需要找到&nbsp;<code>nums</code>&nbsp;中第&nbsp;<code>queries[i]</code>&nbsp;个&nbsp;<code>x</code>&nbsp;的位置，并返回它的下标。如果数组中&nbsp;<code>x</code>&nbsp;的出现次数少于&nbsp;<code>queries[i]</code>&nbsp;，该查询的答案为 -1 。</p>

<p>请你返回一个整数数组&nbsp;<code>answer</code>&nbsp;，包含所有查询的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,3,1,7], queries = [1,3,2,4], x = 1</span></p>

<p><span class="example-io"><b>输出：</b>[0,-1,2,-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 1 个查询，第一个 1 出现在下标 0 处。</li>
	<li>第 2 个查询，<code>nums</code>&nbsp;中只有两个 1 ，所以答案为 -1 。</li>
	<li>第 3 个查询，第二个 1 出现在下标 2 处。</li>
	<li>第 4 个查询，<code>nums</code>&nbsp;中只有两个 1 ，所以答案为 -1 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3], queries = [10], x = 5</span></p>

<p><span class="example-io"><b>输出：</b>[-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 1 个查询，<code>nums</code>&nbsp;中没有 5 ，所以答案为 -1 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], x &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

根据题目描述，我们可以先遍历一遍数组 $\text{nums}$，找出所有值为 $x$ 的元素的下标，记录在数组 $\text{ids}$ 中。

接着遍历数组 $\text{queries}$，对于每个查询 $i$，如果 $i - 1$ 小于 $\text{ids}$ 的长度，那么答案就是 $\text{ids}[i - 1]$，否则答案就是 $-1$。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是数组 $\text{nums}$ 和数组 $\text{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def occurrencesOfElement(
        self, nums: List[int], queries: List[int], x: int
    ) -> List[int]:
        ids = [i for i, v in enumerate(nums) if v == x]
        return [ids[i - 1] if i - 1 < len(ids) else -1 for i in queries]
```

#### Java

```java
class Solution {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == x) {
                ids.add(i);
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = queries[i] - 1;
            ans[i] = j < ids.size() ? ids.get(j) : -1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> occurrencesOfElement(vector<int>& nums, vector<int>& queries, int x) {
        vector<int> ids;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == x) {
                ids.push_back(i);
            }
        }
        vector<int> ans;
        for (int& i : queries) {
            ans.push_back(i - 1 < ids.size() ? ids[i - 1] : -1);
        }
        return ans;
    }
};
```

#### Go

```go
func occurrencesOfElement(nums []int, queries []int, x int) (ans []int) {
	ids := []int{}
	for i, v := range nums {
		if v == x {
			ids = append(ids, i)
		}
	}
	for _, i := range queries {
		if i-1 < len(ids) {
			ans = append(ans, ids[i-1])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
```

#### TypeScript

```ts
function occurrencesOfElement(nums: number[], queries: number[], x: number): number[] {
    const ids: number[] = nums.map((v, i) => (v === x ? i : -1)).filter(v => v !== -1);
    return queries.map(i => ids[i - 1] ?? -1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
