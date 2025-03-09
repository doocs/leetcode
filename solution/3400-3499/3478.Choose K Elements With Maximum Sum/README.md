---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3478.Choose%20K%20Elements%20With%20Maximum%20Sum/README.md
---

<!-- problem:start -->

# [3478. 选出和最大的 K 个元素](https://leetcode.cn/problems/choose-k-elements-with-maximum-sum)

[English Version](/solution/3400-3499/3478.Choose%20K%20Elements%20With%20Maximum%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组，<code>nums1</code> 和 <code>nums2</code>，长度均为 <code>n</code>，以及一个正整数 <code>k</code> 。</p>

<p>对从 <code>0</code> 到 <code>n - 1</code> 每个下标 <code>i</code> ，执行下述操作：</p>

<ul>
	<li>找出所有满足 <code>nums1[j]</code> 小于 <code>nums1[i]</code> 的下标 <code>j</code> 。</li>
	<li>从这些下标对应的 <code>nums2[j]</code> 中选出 <strong>至多</strong> <code>k</code> 个，并 <strong>最大化</strong> 这些值的总和作为结果。</li>
</ul>

<p>返回一个长度为 <code>n</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 表示对应下标 <code>i</code> 的结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">[80,30,0,80,50]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>i = 0</code> ：满足 <code>nums1[j] &lt; nums1[0]</code> 的下标为 <code>[1, 2, 4]</code> ，选出其中值最大的两个，结果为 <code>50 + 30 = 80</code> 。</li>
	<li>对于 <code>i = 1</code> ：满足 <code>nums1[j] &lt; nums1[1]</code> 的下标为 <code>[2]</code> ，只能选择这个值，结果为 <code>30</code> 。</li>
	<li>对于 <code>i = 2</code> ：不存在满足 <code>nums1[j] &lt; nums1[2]</code> 的下标，结果为 <code>0</code> 。</li>
	<li>对于 <code>i = 3</code> ：满足 <code>nums1[j] &lt; nums1[3]</code> 的下标为 <code>[0, 1, 2, 4]</code> ，选出其中值最大的两个，结果为 <code>50 + 30 = 80</code> 。</li>
	<li>对于 <code>i = 4</code> ：满足 <code>nums1[j] &lt; nums1[4]</code> 的下标为 <code>[1, 2]</code> ，选出其中值最大的两个，结果为 <code>30 + 20 = 50</code> 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums1 = [2,2,2,2], nums2 = [3,1,2,3], k = 1</span></p>

<p><strong>输出：</strong><span class="example-io">[0,0,0,0]</span></p>

<p><strong>解释：</strong>由于 <code>nums1</code> 中的所有元素相等，不存在满足条件 <code>nums1[j] &lt; nums1[i]</code>，所有位置的结果都是 0 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 优先队列（小根堆）

我们可以将数组 $\textit{nums1}$ 转换成一个数组 $\textit{arr}$，其中每个元素是一个二元组 $(x, i)$，表示 $\textit{nums1}[i]$ 的值为 $x$。然后对数组 $\textit{arr}$ 按照 $x$ 进行升序排序。

我们使用一个小根堆 $\textit{pq}$ 来维护数组 $\textit{nums2}$ 中的元素，初始时 $\textit{pq}$ 为空。用一个变量 $\textit{s}$ 来记录 $\textit{pq}$ 中的元素之和。另外，我们用一个指针 $j$ 来维护当前需要添加到 $\textit{pq}$ 中的元素在数组 $\textit{arr}$ 中的位置。

我们遍历数组 $\textit{arr}$，对于第 $h$ 个元素 $(x, i)$，我们将所有满足 $j < h$ 并且 $\textit{arr}[j][0] < x$ 的元素 $\textit{nums2}[\textit{arr}[j][1]]$ 添加到 $\textit{pq}$ 中，并将这些元素的和加到 $\textit{s}$ 中。如果 $\textit{pq}$ 的大小超过了 $k$，我们将 $\textit{pq}$ 中的最小元素弹出，并将其从 $\textit{s}$ 中减去。然后，我们更新 $\textit{ans}[i]$ 的值为 $\textit{s}$。

遍历结束后，返回答案数组 $\textit{ans}$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxSum(self, nums1: List[int], nums2: List[int], k: int) -> List[int]:
        arr = [(x, i) for i, x in enumerate(nums1)]
        arr.sort()
        pq = []
        s = j = 0
        n = len(arr)
        ans = [0] * n
        for h, (x, i) in enumerate(arr):
            while j < h and arr[j][0] < x:
                y = nums2[arr[j][1]]
                heappush(pq, y)
                s += y
                if len(pq) > k:
                    s -= heappop(pq)
                j += 1
            ans[i] = s
        return ans
```

#### Java

```java
class Solution {
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums1[i], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long s = 0;
        long[] ans = new long[n];
        int j = 0;
        for (int h = 0; h < n; ++h) {
            int x = arr[h][0], i = arr[h][1];
            while (j < h && arr[j][0] < x) {
                int y = nums2[arr[j][1]];
                pq.offer(y);
                s += y;
                if (pq.size() > k) {
                    s -= pq.poll();
                }
                ++j;
            }
            ans[i] = s;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> findMaxSum(vector<int>& nums1, vector<int>& nums2, int k) {
        int n = nums1.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {nums1[i], i};
        }
        ranges::sort(arr);
        priority_queue<int, vector<int>, greater<int>> pq;
        long long s = 0;
        int j = 0;
        vector<long long> ans(n);
        for (int h = 0; h < n; ++h) {
            auto [x, i] = arr[h];
            while (j < h && arr[j].first < x) {
                int y = nums2[arr[j].second];
                pq.push(y);
                s += y;
                if (pq.size() > k) {
                    s -= pq.top();
                    pq.pop();
                }
                ++j;
            }
            ans[i] = s;
        }
        return ans;
    }
};
```

#### Go

```go
func findMaxSum(nums1 []int, nums2 []int, k int) []int64 {
	n := len(nums1)
	arr := make([][2]int, n)
	for i, x := range nums1 {
		arr[i] = [2]int{x, i}
	}
	ans := make([]int64, n)
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] })
	pq := hp{}
	var s int64
	j := 0
	for h, e := range arr {
		x, i := e[0], e[1]
		for j < h && arr[j][0] < x {
			y := nums2[arr[j][1]]
			heap.Push(&pq, y)
			s += int64(y)
			if pq.Len() > k {
				s -= int64(heap.Pop(&pq).(int))
			}
			j++
		}
		ans[i] = s
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function findMaxSum(nums1: number[], nums2: number[], k: number): number[] {
    const n = nums1.length;
    const arr = nums1.map((x, i) => [x, i]).sort((a, b) => a[0] - b[0]);
    const pq = new MinPriorityQueue();
    let [s, j] = [0, 0];
    const ans: number[] = Array(k).fill(0);
    for (let h = 0; h < n; ++h) {
        const [x, i] = arr[h];
        while (j < h && arr[j][0] < x) {
            const y = nums2[arr[j++][1]];
            pq.enqueue(y);
            s += y;
            if (pq.size() > k) {
                s -= pq.dequeue();
            }
        }
        ans[i] = s;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
