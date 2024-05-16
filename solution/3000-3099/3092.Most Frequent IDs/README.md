---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3092.Most%20Frequent%20IDs/README.md
rating: 1793
source: 第 390 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 有序集合
    - 堆（优先队列）
---

# [3092. 最高频率的 ID](https://leetcode.cn/problems/most-frequent-ids)

[English Version](/solution/3000-3099/3092.Most%20Frequent%20IDs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要在一个集合里动态记录 ID 的出现频率。给你两个长度都为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code> 和&nbsp;<code>freq</code>&nbsp;，<code>nums</code>&nbsp;中每一个元素表示一个 ID ，对应的 <code>freq</code>&nbsp;中的元素表示这个 ID 在集合中此次操作后需要增加或者减少的数目。</p>

<ul>
	<li><strong>增加 ID 的数目：</strong>如果&nbsp;<code>freq[i]</code>&nbsp;是正数，那么&nbsp;<code>freq[i]</code>&nbsp;个 ID 为&nbsp;<code>nums[i]</code>&nbsp;的元素在第 <code>i</code>&nbsp;步操作后会添加到集合中。</li>
	<li><strong>减少 ID 的数目：</strong>如果&nbsp;<code>freq[i]</code>&nbsp;是负数，那么&nbsp;<code>-freq[i]</code>&nbsp;个 ID 为&nbsp;<code>nums[i]</code>&nbsp;的元素在第 <code>i</code>&nbsp;步操作后会从集合中删除。</li>
</ul>

<p>请你返回一个长度为 <code>n</code>&nbsp;的数组 <code>ans</code>&nbsp;，其中&nbsp;<code>ans[i]</code>&nbsp;表示第 <code>i</code>&nbsp;步操作后出现频率最高的 ID <strong>数目</strong>&nbsp;，如果在某次操作后集合为空，那么 <code>ans[i]</code>&nbsp;为 0 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,3,2,1], freq = [3,2,-3,1]</span></p>

<p><span class="example-io"><b>输出：</b>[3,3,2,2]</span></p>

<p><strong>解释：</strong></p>

<p>第 0 步操作后，有 3 个 ID 为 2 的元素，所以&nbsp;<code>ans[0] = 3</code>&nbsp;。<br />
第 1 步操作后，有 3 个 ID 为 2 的元素和 2 个 ID 为 3 的元素，所以&nbsp;<code>ans[1] = 3</code>&nbsp;。<br />
第 2 步操作后，有 2 个 ID 为 3 的元素，所以&nbsp;<code>ans[2] = 2</code>&nbsp;。<br />
第 3 步操作后，有 2 个 ID 为 3 的元素和 1 个 ID 为 1 的元素，所以&nbsp;<code>ans[3] = 2</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,5,3], freq = [2,-2,1]</span></p>

<p><span class="example-io"><b>输出：</b>[2,0,1]</span></p>

<p><strong>解释：</strong></p>

<p>第 0 步操作后，有 2 个 ID 为 5 的元素，所以&nbsp;<code>ans[0] = 2</code>&nbsp;。<br />
第 1 步操作后，集合中没有任何元素，所以&nbsp;<code>ans[1] = 0</code>&nbsp;。<br />
第 2 步操作后，有 1 个 ID 为 3 的元素，所以&nbsp;<code>ans[2] = 1</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == freq.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= freq[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>freq[i] != 0</code></li>
	<li>输入保证任何操作后，集合中的元素出现次数不会为负数。</li>
</ul>

## 解法

### 方法一：哈希表 + 优先队列（大根堆）

我们用一个哈希表 $cnt$ 来记录每个 ID 的出现次数，用一个哈希表 $lazy$ 来记录每个次数需要被删除的个数。用一个优先队列 $pq$ 来维护出现次数的最大值。

每一次操作 $(x, f)$，我们需要更新 $x$ 的出现次数 $cnt[x]$，这意味着 $cnt[x]$ 在 $lazy$ 中的值需要增加 $1$，表示该次数需要删除的个数增加 $1$。然后我们更新 $cnt[x]$ 的值，将 $cnt[x]$ 加上 $f$。然后我们更新后的 $cnt[x]$ 的值加入优先队列 $pq$ 中。然后我们检查优先队列 $pq$ 的堆顶元素，如果 $lazy$ 中对应的次数需要删除的个数大于 $0$，我们就将堆顶元素弹出。最后，我们判断优先队列是否为空，如果不为空，堆顶元素就是出现次数的最大值，我们将其加入答案数组中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def mostFrequentIDs(self, nums: List[int], freq: List[int]) -> List[int]:
        cnt = Counter()
        lazy = Counter()
        ans = []
        pq = []
        for x, f in zip(nums, freq):
            lazy[cnt[x]] += 1
            cnt[x] += f
            heappush(pq, -cnt[x])
            while pq and lazy[-pq[0]] > 0:
                lazy[-pq[0]] -= 1
                heappop(pq)
            ans.append(0 if not pq else -pq[0])
        return ans
```

```java
class Solution {
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Integer, Long> cnt = new HashMap<>();
        Map<Long, Integer> lazy = new HashMap<>();
        int n = nums.length;
        long[] ans = new long[n];
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; ++i) {
            int x = nums[i], f = freq[i];
            lazy.merge(cnt.getOrDefault(x, 0L), 1, Integer::sum);
            cnt.merge(x, (long) f, Long::sum);
            pq.add(cnt.get(x));
            while (!pq.isEmpty() && lazy.getOrDefault(pq.peek(), 0) > 0) {
                lazy.merge(pq.poll(), -1, Integer::sum);
            }
            ans[i] = pq.isEmpty() ? 0 : pq.peek();
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<long long> mostFrequentIDs(vector<int>& nums, vector<int>& freq) {
        unordered_map<int, long long> cnt;
        unordered_map<long long, int> lazy;
        int n = nums.size();
        vector<long long> ans(n);
        priority_queue<long long> pq;

        for (int i = 0; i < n; ++i) {
            int x = nums[i], f = freq[i];
            lazy[cnt[x]]++;
            cnt[x] += f;
            pq.push(cnt[x]);
            while (!pq.empty() && lazy[pq.top()] > 0) {
                lazy[pq.top()]--;
                pq.pop();
            }
            ans[i] = pq.empty() ? 0 : pq.top();
        }

        return ans;
    }
};
```

```go
func mostFrequentIDs(nums []int, freq []int) []int64 {
	n := len(nums)
	cnt := map[int]int{}
	lazy := map[int]int{}
	ans := make([]int64, n)
	pq := hp{}
	heap.Init(&pq)
	for i, x := range nums {
		f := freq[i]
		lazy[cnt[x]]++
		cnt[x] += f
		heap.Push(&pq, cnt[x])
		for pq.Len() > 0 && lazy[pq.IntSlice[0]] > 0 {
			lazy[pq.IntSlice[0]]--
			heap.Pop(&pq)
		}
		if pq.Len() > 0 {
			ans[i] = int64(pq.IntSlice[0])
		}
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

<!-- tabs:end -->

<!-- end -->
