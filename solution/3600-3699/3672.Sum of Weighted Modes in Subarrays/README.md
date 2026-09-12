---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3672.Sum%20of%20Weighted%20Modes%20in%20Subarrays/README.md
tags:
    - 数组
    - 哈希表
    - 计数
    - 有序集合
    - 滑动窗口
---

<!-- problem:start -->

# [3672. 子数组中加权众数的总和 🔒](https://leetcode.cn/problems/sum-of-weighted-modes-in-subarrays)

[English Version](/solution/3600-3699/3672.Sum%20of%20Weighted%20Modes%20in%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>对于每个长度为 <code>k</code>&nbsp;的 <strong>子数组</strong>：</p>

<ul>
	<li>众数 <code>mode</code> 是指 <strong>出现频率最高</strong> 的元素。如果有多个众数，取其中 <strong>最小</strong> 的那个元素。</li>
	<li><strong>权重</strong>&nbsp;定义为&nbsp;<code>mode * frequency(mode)</code>。</li>
</ul>

<p>返回长度为 <code>k</code> 的所有 <strong>子数组</strong> 的权重之 <strong>和</strong>。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><strong>子数组</strong> 是数组中连续的 <strong>非空</strong> 元素序列。</li>
	<li>元素 <code>x</code> 的 <strong>频率</strong> 是它在数组中出现的次数。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,2,3], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">8</span></p>

<p><strong>解释：</strong></p>

<p>长度为 <code>k = 3</code>&nbsp;的子数组是：</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">频率</th>
			<th style="border: 1px solid black;">众数</th>
			<th style="border: 1px solid black;">众数频率</th>
			<th style="border: 1px solid black;">权重</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">[1, 2, 2]</td>
			<td style="border: 1px solid black;">1: 1, 2: 2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 × 2 = 4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[2, 2, 3]</td>
			<td style="border: 1px solid black;">2: 2, 3: 1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 × 2 = 4</td>
		</tr>
	</tbody>
</table>

<p>因此，权重的和是&nbsp;<code>4 + 4 = 8</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>长度为&nbsp;<code>k = 2</code>&nbsp;的子数组是：</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">频率</th>
			<th style="border: 1px solid black;">众数</th>
			<th style="border: 1px solid black;">众数频率</th>
			<th style="border: 1px solid black;">权重</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">1: 1, 2: 1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 × 1 = 1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[2, 1]</td>
			<td style="border: 1px solid black;">2: 1, 1: 1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 × 1 = 1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">1: 1, 2: 1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 × 1 = 1</td>
		</tr>
	</tbody>
</table>

<p>因此，权重的和是&nbsp;<code>1 + 1 + 1 = 3</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,3,4,3], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">14</span></p>

<p><strong>解释：</strong></p>

<p>长度为&nbsp;<code>k = 3</code>&nbsp;的子数组是：</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">频率</th>
			<th style="border: 1px solid black;">众数</th>
			<th style="border: 1px solid black;">众数频率</th>
			<th style="border: 1px solid black;">权重</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">[4, 3, 4]</td>
			<td style="border: 1px solid black;">4: 2, 3: 1</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 × 4 = 8</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[3, 4, 3]</td>
			<td style="border: 1px solid black;">3: 2, 4: 1</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2 × 3 = 6</td>
		</tr>
	</tbody>
</table>

<p>因此，权重的和是 <code>8 + 6 = 14</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 优先队列 + 滑动窗口 + 懒删除

<!-- thinking:start -->

> **思考**
>
> 每个长 $k$ 的窗口贡献 $\textit{mode}\times\textit{freq}$，并列取较小的 mode。窗口滑动时若每次重扫，总代价为 $O(nk)$。
>
> 哈希表维护频次，堆按 $(-\textit{freq},\textit{val})$ 取顶。懒删除：堆顶频次与表不一致则弹出。
>
> 滑窗时对新旧值分别入堆。$\textit{get\_mode}$ 在堆顶稳定后返回乘积。每个下标引发常数次堆操作。

<!-- thinking:end -->

我们用一个哈希表 $\textit{cnt}$ 记录当前窗口中每个数字的出现次数。我们用一个优先队列 $\textit{pq}$ 记录当前窗口中每个数字的出现次数和数字本身，优先级为出现次数从大到小，如果出现次数相同，则数字从小到大。

我们设计一个函数 $\textit{get\_mode()}$，用于获取当前窗口的众数及其出现次数。具体做法是不断弹出优先队列的堆顶元素，直到堆顶元素的出现次数与哈希表中记录的出现次数相同为止，此时堆顶元素即为当前窗口的众数及其出现次数。

我们用一个变量 $\textit{ans}$ 记录所有窗口的权重和。初始时，我们将数组的前 $k$ 个数字加入哈希表和优先队列中，然后调用 $\textit{get\_mode()}$ 获取第一个窗口的众数及其出现次数，并将其权重加入 $\textit{ans}$。

然后，我们从数组的第 $k$ 个数字开始，依次将每个数字加入哈希表和优先队列中，同时将窗口的左端数字从哈希表中删除（出现次数减一）。然后调用 $\textit{get\_mode()}$ 获取当前窗口的众数及其出现次数，并将其权重加入 $\textit{ans}$。

最后，返回 $\textit{ans}$。

时间复杂度 $O(n \log k)$，其中 $n$ 是数组的长度。空间复杂度 $O(k)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def modeWeight(self, nums: List[int], k: int) -> int:
        pq = []
        cnt = defaultdict(int)
        for x in nums[:k]:
            cnt[x] += 1
            heappush(pq, (-cnt[x], x))

        def get_mode() -> int:
            while -pq[0][0] != cnt[pq[0][1]]:
                heappop(pq)
            freq, val = -pq[0][0], pq[0][1]
            return freq * val

        ans = 0
        ans += get_mode()

        for i in range(k, len(nums)):
            x, y = nums[i], nums[i - k]
            cnt[x] += 1
            cnt[y] -= 1
            heappush(pq, (-cnt[x], x))
            heappush(pq, (-cnt[y], y))

            ans += get_mode()

        return ans
```

#### Java

```java
class Solution {
    public long modeWeight(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        for (int i = 0; i < k; i++) {
            int x = nums[i];
            cnt.merge(x, 1, Integer::sum);
            pq.offer(new int[] {-cnt.get(x), x});
        }

        long ans = 0;

        Supplier<Long> getMode = () -> {
            while (true) {
                int[] top = pq.peek();
                int val = top[1];
                int freq = -top[0];
                if (cnt.getOrDefault(val, 0) == freq) {
                    return 1L * freq * val;
                }
                pq.poll();
            }
        };

        ans += getMode.get();

        for (int i = k; i < nums.length; i++) {
            int x = nums[i], y = nums[i - k];
            cnt.merge(x, 1, Integer::sum);
            pq.offer(new int[] {-cnt.get(x), x});
            cnt.merge(y, -1, Integer::sum);
            pq.offer(new int[] {-cnt.get(y), y});
            ans += getMode.get();
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long modeWeight(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        priority_queue<pair<int, int>> pq; // {freq, -val}

        for (int i = 0; i < k; i++) {
            int x = nums[i];
            cnt[x]++;
            pq.push({cnt[x], -x});
        }

        auto get_mode = [&]() {
            while (true) {
                auto [freq, negVal] = pq.top();
                int val = -negVal;
                if (cnt[val] == freq) {
                    return 1LL * freq * val;
                }
                pq.pop();
            }
        };

        long long ans = 0;
        ans += get_mode();

        for (int i = k; i < nums.size(); i++) {
            int x = nums[i], y = nums[i - k];
            cnt[x]++;
            cnt[y]--;
            pq.push({cnt[x], -x});
            pq.push({cnt[y], -y});
            ans += get_mode();
        }

        return ans;
    }
};
```

#### Go

```go
func modeWeight(nums []int, k int) int64 {
	cnt := make(map[int]int)
	pq := &MaxHeap{}
	heap.Init(pq)

	for i := 0; i < k; i++ {
		x := nums[i]
		cnt[x]++
		heap.Push(pq, pair{cnt[x], x})
	}

	getMode := func() int64 {
		for {
			top := (*pq)[0]
			if cnt[top.val] == top.freq {
				return int64(top.freq) * int64(top.val)
			}
			heap.Pop(pq)
		}
	}

	var ans int64
	ans += getMode()

	for i := k; i < len(nums); i++ {
		x, y := nums[i], nums[i-k]
		cnt[x]++
		cnt[y]--
		heap.Push(pq, pair{cnt[x], x})
		heap.Push(pq, pair{cnt[y], y})
		ans += getMode()
	}

	return ans
}

type pair struct {
	freq int
	val  int
}

type MaxHeap []pair

func (h MaxHeap) Len() int { return len(h) }
func (h MaxHeap) Less(i, j int) bool {
	if h[i].freq != h[j].freq {
		return h[i].freq > h[j].freq
	}
	return h[i].val < h[j].val
}
func (h MaxHeap) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *MaxHeap) Push(x any) {
	*h = append(*h, x.(pair))
}
func (h *MaxHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
