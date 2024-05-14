---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3080.Mark%20Elements%20on%20Array%20by%20Performing%20Queries/README.md
rating: 1607
tags:
    - 数组
    - 哈希表
    - 排序
    - 模拟
    - 堆（优先队列）
---

# [3080. 执行操作标记数组中的元素](https://leetcode.cn/problems/mark-elements-on-array-by-performing-queries)

[English Version](/solution/3000-3099/3080.Mark%20Elements%20on%20Array%20by%20Performing%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的正整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>同时给你一个长度为 <code>m</code>&nbsp;的二维操作数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [index<sub>i</sub>, k<sub>i</sub>]</code>&nbsp;。</p>

<p>一开始，数组中的所有元素都 <strong>未标记</strong>&nbsp;。</p>

<p>你需要依次对数组执行 <code>m</code>&nbsp;次操作，第 <code>i</code>&nbsp;次操作中，你需要执行：</p>

<ul>
	<li>如果下标&nbsp;<code>index<sub>i</sub></code>&nbsp;对应的元素还没标记，那么标记这个元素。</li>
	<li>然后标记&nbsp;<code>k<sub>i</sub></code>&nbsp;个数组中还没有标记的&nbsp;<strong>最小</strong>&nbsp;元素。如果有元素的值相等，那么优先标记它们中下标较小的。如果少于&nbsp;<code>k<sub>i</sub></code>&nbsp;个未标记元素存在，那么将它们全部标记。</li>
</ul>

<p>请你返回一个长度为 <code>m</code>&nbsp;的数组 <code>answer</code>&nbsp;，其中<em>&nbsp;</em><code>answer[i]</code>是第&nbsp;<code>i</code>&nbsp;次操作后数组中还没标记元素的&nbsp;<strong>和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">[8,3,0]</span></p>

<p><strong>解释：</strong></p>

<p>我们依次对数组做以下操作：</p>

<ul>
	<li>标记下标为&nbsp;<code>1</code>&nbsp;的元素，同时标记&nbsp;<code>2</code>&nbsp;个未标记的最小元素。标记完后数组为&nbsp;<code>nums = [<em><strong>1</strong></em>,<em><strong>2</strong></em>,2,<em><strong>1</strong></em>,2,3,1]</code>&nbsp;。未标记元素的和为&nbsp;<code>2 + 2 + 3 + 1 = 8</code>&nbsp;。</li>
	<li>标记下标为&nbsp;<code>3</code>&nbsp;的元素，由于它已经被标记过了，所以我们忽略这次标记，同时标记最靠前的&nbsp;<code>3</code>&nbsp;个未标记的最小元素。标记完后数组为&nbsp;<code>nums = [<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>2</strong></em>,<em><strong>1</strong></em>,<em><strong>2</strong></em>,3,<em><strong>1</strong></em>]</code>&nbsp;。未标记元素的和为&nbsp;<code>3</code>&nbsp;。</li>
	<li>标记下标为 <code>4</code>&nbsp;的元素，由于它已经被标记过了，所以我们忽略这次标记，同时标记最靠前的 <code>2</code>&nbsp;个未标记的最小元素。标记完后数组为&nbsp;<code>nums = [<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>2</strong></em>,<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>1</strong></em>]</code>&nbsp;。未标记元素的和为&nbsp;<code>0</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,4,2,3], queries = [[0,1]]</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">[7]</span></p>

<p><strong>解释：</strong>我们执行一次操作，将下标为&nbsp;<code>0</code>&nbsp;处的元素标记，并且标记最靠前的&nbsp;<code>1</code>&nbsp;个未标记的最小元素。标记完后数组为&nbsp;<code>nums = [<em><strong>1</strong></em>,4,<em><strong>2</strong></em>,3]</code>&nbsp;。未标记元素的和为&nbsp;<code>4 + 3 = 7</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= index<sub>i</sub>, k<sub>i</sub> &lt;= n - 1</code></li>
</ul>

## 解法

### 方法一：排序 + 模拟

我们先计算出数组 $nums$ 的总和 $s$，定义一个数组 $mark$ 用来标记数组中的元素是否被标记过，初始化所有元素都未被标记。

然后我们创建一个数组 $arr$，数组中的每个元素是一个二元组 $(x, i)$，表示数组中的第 $i$ 个元素的值为 $x$。我们对数组 $arr$ 按照元素的值进行排序，如果元素的值相等，我们按照下标从小到大的顺序进行排序。

接下来我们遍历数组 $queries$，对于每个查询 $[index, k]$，我们首先判断下标 $index$ 对应的元素是否被标记过，如果没有被标记过，我们将其标记，并且将 $s$ 减去下标 $index$ 对应的元素的值。然后我们遍历数组 $arr$，对于每个元素 $(x, i)$，如果元素 $i$ 没有被标记过，我们将其标记，并且将 $s$ 减去元素 $i$ 对应的值 $x$，直到 $k$ 为 $0$ 或者数组 $arr$ 遍历完。然后我们将 $s$ 加入答案数组中。

遍历完所有的查询后，我们就得到了答案数组。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def unmarkedSumArray(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        n = len(nums)
        s = sum(nums)
        mark = [False] * n
        arr = sorted((x, i) for i, x in enumerate(nums))
        j = 0
        ans = []
        for index, k in queries:
            if not mark[index]:
                mark[index] = True
                s -= nums[index]
            while k and j < n:
                if not mark[arr[j][1]]:
                    mark[arr[j][1]] = True
                    s -= arr[j][0]
                    k -= 1
                j += 1
            ans.append(s)
        return ans
```

```java
class Solution {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        long s = Arrays.stream(nums).asLongStream().sum();
        boolean[] mark = new boolean[n];
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[]{nums[i], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int m = queries.length;
        long[] ans = new long[m];
        for (int i = 0, j = 0; i < m; ++i) {
            int index = queries[i][0], k = queries[i][1];
            if (!mark[index]) {
                mark[index] = true;
                s -= nums[index];
            }
            for (; k > 0 && j < n; ++j) {
                if (!mark[arr[j][1]]) {
                    mark[arr[j][1]] = true;
                    s -= arr[j][0];
                    --k;
                }
            }
            ans[i] = s;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<long long> unmarkedSumArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        vector<bool> mark(n);
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(nums[i], i);
        }
        sort(arr.begin(), arr.end());
        vector<long long> ans;
        int m = queries.size();
        for (int i = 0, j = 0; i < m; ++i) {
            int index = queries[i][0], k = queries[i][1];
            if (!mark[index]) {
                mark[index] = true;
                s -= nums[index];
            }
            for (; k && j < n; ++j) {
                if (!mark[arr[j].second]) {
                    mark[arr[j].second] = true;
                    s -= arr[j].first;
                    --k;
                }
            }
            ans.push_back(s);
        }
        return ans;
    }
};
```

```go
func unmarkedSumArray(nums []int, queries [][]int) []int64 {
	n := len(nums)
	var s int64
	for _, x := range nums {
		s += int64(x)
	}
	mark := make([]bool, n)
	arr := make([][2]int, 0, n)
	for i, x := range nums {
		arr = append(arr, [2]int{x, i})
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] == arr[j][0] {
			return arr[i][1] < arr[j][1]
		}
		return arr[i][0] < arr[j][0]
	})
	ans := make([]int64, len(queries))
	j := 0
	for i, q := range queries {
		index, k := q[0], q[1]
		if !mark[index] {
			mark[index] = true
			s -= int64(nums[index])
		}
		for ; k > 0 && j < n; j++ {
			if !mark[arr[j][1]] {
				mark[arr[j][1]] = true
				s -= int64(arr[j][0])
				k--
			}
		}
		ans[i] = s
	}
	return ans
}
```

```ts
function unmarkedSumArray(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    let s = nums.reduce((acc, x) => acc + x, 0);
    const mark: boolean[] = Array(n).fill(false);
    const arr = nums.map((x, i) => [x, i]);
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    let j = 0;
    const ans: number[] = [];
    for (let [index, k] of queries) {
        if (!mark[index]) {
            mark[index] = true;
            s -= nums[index];
        }
        for (; k && j < n; ++j) {
            if (!mark[arr[j][1]]) {
                mark[arr[j][1]] = true;
                s -= arr[j][0];
                --k;
            }
        }
        ans.push(s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
