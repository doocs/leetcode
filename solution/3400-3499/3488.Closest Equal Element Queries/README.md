---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3488.Closest%20Equal%20Element%20Queries/README.md
rating: 1699
source: 第 441 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 二分查找
---

<!-- problem:start -->

# [3488. 距离最小相等元素查询](https://leetcode.cn/problems/closest-equal-element-queries)

[English Version](/solution/3400-3499/3488.Closest%20Equal%20Element%20Queries/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>环形&nbsp;</strong>数组&nbsp;<code>nums</code>&nbsp;和一个数组&nbsp;<code>queries</code>&nbsp;。</p>

<p>对于每个查询&nbsp;<code>i</code>&nbsp;，你需要找到以下内容：</p>

<ul>
	<li>数组&nbsp;<code>nums</code>&nbsp;中下标&nbsp;<code>queries[i]</code>&nbsp;处的元素与&nbsp;<strong>任意&nbsp;</strong>其他下标&nbsp;<code>j</code>（满足&nbsp;<code>nums[j] == nums[queries[i]]</code>）之间的&nbsp;<strong>最小&nbsp;</strong>距离。如果不存在这样的下标&nbsp;<code>j</code>，则该查询的结果为 <code>-1</code> 。</li>
</ul>

<p>返回一个数组&nbsp;<code>answer</code>，其大小与&nbsp;<code>queries</code>&nbsp;相同，其中&nbsp;<code>answer[i]</code>&nbsp;表示查询<code>i</code>的结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,3,1,4,1,3,2], queries = [0,3,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,-1,3]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>查询 0：下标&nbsp;<code>queries[0] = 0</code>&nbsp;处的元素为&nbsp;<code>nums[0] = 1</code>&nbsp;。最近的相同值下标为 2，距离为 2。</li>
	<li>查询 1：下标&nbsp;<code>queries[1] = 3</code>&nbsp;处的元素为&nbsp;<code>nums[3] = 4</code>&nbsp;。不存在其他包含值 4 的下标，因此结果为 -1。</li>
	<li>查询 2：下标&nbsp;<code>queries[2] = 5</code>&nbsp;处的元素为&nbsp;<code>nums[5] = 3</code>&nbsp;。最近的相同值下标为 1，距离为 3（沿着循环路径：<code>5 -&gt; 6 -&gt; 0 -&gt; 1</code>）。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4], queries = [0,1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,-1,-1,-1]</span></p>

<p><strong>解释：</strong></p>

<p>数组&nbsp;<code>nums</code>&nbsp;中的每个值都是唯一的，因此没有下标与查询的元素值相同。所有查询的结果均为 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= queries[i] &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：环形数组 + 哈希表

根据题目描述，我们需要找出数组每个元素与上一个相同元素的最小距离，以及与下一个相同元素的最小距离。并且，由于数组是循环的，所以我们需要考虑数组的环形特性，我们可以将数组扩展为原数组的两倍，然后使用哈希表 $\textit{left}$ 和 $\textit{right}$ 分别记录每个元素上一次出现的位置和下一次出现的位置，计算出每个位置的元素与另一个相同元素的最小距离，记录在数组 $\textit{d}$ 中。最后，我们遍历查询，对于每个查询 $i$，我们取 $\textit{d}[i]$ 和 $\textit{d}[i+n]$ 中的最小值，如果该值大于等于 $n$，则说明不存在与查询元素相同的元素，返回 $-1$，否则返回该值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def solveQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        n = len(nums)
        m = n << 1
        d = [m] * m
        left = {}
        for i in range(m):
            x = nums[i % n]
            if x in left:
                d[i] = min(d[i], i - left[x])
            left[x] = i
        right = {}
        for i in range(m - 1, -1, -1):
            x = nums[i % n]
            if x in right:
                d[i] = min(d[i], right[x] - i)
            right[x] = i
        for i in range(n):
            d[i] = min(d[i], d[i + n])
        return [-1 if d[i] >= n else d[i] for i in queries]
```

#### Java

```java
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = n * 2;
        int[] d = new int[m];
        Arrays.fill(d, m);

        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x = nums[i % n];
            if (left.containsKey(x)) {
                d[i] = Math.min(d[i], i - left.get(x));
            }
            left.put(x, i);
        }

        Map<Integer, Integer> right = new HashMap<>();
        for (int i = m - 1; i >= 0; i--) {
            int x = nums[i % n];
            if (right.containsKey(x)) {
                d[i] = Math.min(d[i], right.get(x) - i);
            }
            right.put(x, i);
        }

        for (int i = 0; i < n; i++) {
            d[i] = Math.min(d[i], d[i + n]);
        }

        List<Integer> ans = new ArrayList<>();
        for (int query : queries) {
            ans.add(d[query] >= n ? -1 : d[query]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> solveQueries(vector<int>& nums, vector<int>& queries) {
        int n = nums.size();
        int m = n * 2;
        vector<int> d(m, m);

        unordered_map<int, int> left;
        for (int i = 0; i < m; i++) {
            int x = nums[i % n];
            if (left.count(x)) {
                d[i] = min(d[i], i - left[x]);
            }
            left[x] = i;
        }

        unordered_map<int, int> right;
        for (int i = m - 1; i >= 0; i--) {
            int x = nums[i % n];
            if (right.count(x)) {
                d[i] = min(d[i], right[x] - i);
            }
            right[x] = i;
        }

        for (int i = 0; i < n; i++) {
            d[i] = min(d[i], d[i + n]);
        }

        vector<int> ans;
        for (int query : queries) {
            ans.push_back(d[query] >= n ? -1 : d[query]);
        }
        return ans;
    }
};
```

#### Go

```go
func solveQueries(nums []int, queries []int) []int {
	n := len(nums)
	m := n * 2
	d := make([]int, m)
	for i := range d {
		d[i] = m
	}

	left := make(map[int]int)
	for i := 0; i < m; i++ {
		x := nums[i%n]
		if idx, exists := left[x]; exists {
			d[i] = min(d[i], i-idx)
		}
		left[x] = i
	}

	right := make(map[int]int)
	for i := m - 1; i >= 0; i-- {
		x := nums[i%n]
		if idx, exists := right[x]; exists {
			d[i] = min(d[i], idx-i)
		}
		right[x] = i
	}

	for i := 0; i < n; i++ {
		d[i] = min(d[i], d[i+n])
	}

	ans := make([]int, len(queries))
	for i, query := range queries {
		if d[query] >= n {
			ans[i] = -1
		} else {
			ans[i] = d[query]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function solveQueries(nums: number[], queries: number[]): number[] {
    const n = nums.length;
    const m = n * 2;
    const d: number[] = Array(m).fill(m);

    const left = new Map<number, number>();
    for (let i = 0; i < m; i++) {
        const x = nums[i % n];
        if (left.has(x)) {
            d[i] = Math.min(d[i], i - left.get(x)!);
        }
        left.set(x, i);
    }

    const right = new Map<number, number>();
    for (let i = m - 1; i >= 0; i--) {
        const x = nums[i % n];
        if (right.has(x)) {
            d[i] = Math.min(d[i], right.get(x)! - i);
        }
        right.set(x, i);
    }

    for (let i = 0; i < n; i++) {
        d[i] = Math.min(d[i], d[i + n]);
    }

    return queries.map(query => (d[query] >= n ? -1 : d[query]));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
