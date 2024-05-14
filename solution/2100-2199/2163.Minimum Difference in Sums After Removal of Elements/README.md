# [2163. 删除元素后和的最小差值](https://leetcode.cn/problems/minimum-difference-in-sums-after-removal-of-elements)

[English Version](/solution/2100-2199/2163.Minimum%20Difference%20in%20Sums%20After%20Removal%20of%20Elements/README_EN.md)

<!-- tags:数组,动态规划,堆（优先队列） -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它包含&nbsp;<code>3 * n</code>&nbsp;个元素。</p>

<p>你可以从 <code>nums</code>&nbsp;中删除 <strong>恰好</strong>&nbsp;<code>n</code>&nbsp;个元素，剩下的 <code>2 * n</code>&nbsp;个元素将会被分成两个 <strong>相同大小</strong>&nbsp;的部分。</p>

<ul>
	<li>前面&nbsp;<code>n</code>&nbsp;个元素属于第一部分，它们的和记为&nbsp;<code>sum<sub>first</sub></code>&nbsp;。</li>
	<li>后面&nbsp;<code>n</code>&nbsp;个元素属于第二部分，它们的和记为&nbsp;<code>sum<sub>second</sub></code>&nbsp;。</li>
</ul>

<p>两部分和的 <strong>差值</strong>&nbsp;记为&nbsp;<code>sum<sub>first</sub> - sum<sub>second</sub></code>&nbsp;。</p>

<ul>
	<li>比方说，<code>sum<sub>first</sub> = 3</code> 且&nbsp;<code>sum<sub>second</sub> = 2</code>&nbsp;，它们的差值为&nbsp;<code>1</code>&nbsp;。</li>
	<li>再比方，<code>sum<sub>first</sub> = 2</code> 且&nbsp;<code>sum<sub>second</sub> = 3</code>&nbsp;，它们的差值为&nbsp;<code>-1</code>&nbsp;。</li>
</ul>

<p>请你返回删除 <code>n</code>&nbsp;个元素之后，剩下两部分和的 <strong>差值的最小值</strong>&nbsp;是多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,1,2]
<b>输出：</b>-1
<b>解释：</b>nums 有 3 个元素，所以 n = 1 。
所以我们需要从 nums 中删除 1 个元素，并将剩下的元素分成两部分。
- 如果我们删除 nums[0] = 3 ，数组变为 [1,2] 。两部分和的差值为 1 - 2 = -1 。
- 如果我们删除 nums[1] = 1 ，数组变为 [3,2] 。两部分和的差值为 3 - 2 = 1 。
- 如果我们删除 nums[2] = 2 ，数组变为 [3,1] 。两部分和的差值为 3 - 1 = 2 。
两部分和的最小差值为 min(-1,1,2) = -1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [7,9,5,8,1,3]
<b>输出：</b>1
<b>解释：</b>n = 2 。所以我们需要删除 2 个元素，并将剩下元素分为 2 部分。
如果我们删除元素 nums[2] = 5 和 nums[3] = 8 ，剩下元素为 [7,9,1,3] 。和的差值为 (7+9) - (1+3) = 12 。
为了得到最小差值，我们应该删除 nums[1] = 9 和 nums[4] = 1 ，剩下的元素为 [7,5,8,3] 。和的差值为 (7+5) - (8+3) = 1 。
观察可知，最优答案为 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == 3 * n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：优先队列（大小根堆）+ 前后缀和 + 枚举分割点

题目实际上等价于在 $nums$ 中找到一个分割点，将数组分成左右两部分，在前一部分中选取最小的 $n$ 个元素，在后一部分中选取最大的 $n$ 个元素，使得两部分和的差值最小。

我们可以用一个大根堆维护前缀中最小的 $n$ 个元素，用一个小根堆维护后缀中最大的 $n$ 个元素。我们定义 $pre[i]$ 表示在数组 $nums$ 的前 $i$ 个元素中选择最小的 $n$ 个元素的和，定义 $suf[i]$ 表示从数组第 $i$ 个元素到最后一个元素中选择最大的 $n$ 个元素的和。在维护大小根堆的过程中，更新 $pre[i]$ 和 $suf[i]$ 的值。

最后，我们在 $i \in [n, 2n]$ 的范围内枚举分割点，计算 $pre[i] - suf[i + 1]$ 的值，取最小值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumDifference(self, nums: List[int]) -> int:
        m = len(nums)
        n = m // 3

        s = 0
        pre = [0] * (m + 1)
        q1 = []
        for i, x in enumerate(nums[: n * 2], 1):
            s += x
            heappush(q1, -x)
            if len(q1) > n:
                s -= -heappop(q1)
            pre[i] = s

        s = 0
        suf = [0] * (m + 1)
        q2 = []
        for i in range(m, n, -1):
            x = nums[i - 1]
            s += x
            heappush(q2, x)
            if len(q2) > n:
                s -= heappop(q2)
            suf[i] = s

        return min(pre[i] - suf[i + 1] for i in range(n, n * 2 + 1))
```

```java
class Solution {
    public long minimumDifference(int[] nums) {
        int m = nums.length;
        int n = m / 3;
        long s = 0;
        long[] pre = new long[m + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 1; i <= n * 2; ++i) {
            int x = nums[i - 1];
            s += x;
            pq.offer(x);
            if (pq.size() > n) {
                s -= pq.poll();
            }
            pre[i] = s;
        }
        s = 0;
        long[] suf = new long[m + 1];
        pq = new PriorityQueue<>();
        for (int i = m; i > n; --i) {
            int x = nums[i - 1];
            s += x;
            pq.offer(x);
            if (pq.size() > n) {
                s -= pq.poll();
            }
            suf[i] = s;
        }
        long ans = 1L << 60;
        for (int i = n; i <= n * 2; ++i) {
            ans = Math.min(ans, pre[i] - suf[i + 1]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minimumDifference(vector<int>& nums) {
        int m = nums.size();
        int n = m / 3;

        using ll = long long;
        ll s = 0;
        ll pre[m + 1];
        priority_queue<int> q1;
        for (int i = 1; i <= n * 2; ++i) {
            int x = nums[i - 1];
            s += x;
            q1.push(x);
            if (q1.size() > n) {
                s -= q1.top();
                q1.pop();
            }
            pre[i] = s;
        }
        s = 0;
        ll suf[m + 1];
        priority_queue<int, vector<int>, greater<int>> q2;
        for (int i = m; i > n; --i) {
            int x = nums[i - 1];
            s += x;
            q2.push(x);
            if (q2.size() > n) {
                s -= q2.top();
                q2.pop();
            }
            suf[i] = s;
        }
        ll ans = 1e18;
        for (int i = n; i <= n * 2; ++i) {
            ans = min(ans, pre[i] - suf[i + 1]);
        }
        return ans;
    }
};
```

```go
func minimumDifference(nums []int) int64 {
	m := len(nums)
	n := m / 3
	s := 0
	pre := make([]int, m+1)
	q1 := hp{}
	for i := 1; i <= n*2; i++ {
		x := nums[i-1]
		s += x
		heap.Push(&q1, -x)
		if q1.Len() > n {
			s -= -heap.Pop(&q1).(int)
		}
		pre[i] = s
	}
	s = 0
	suf := make([]int, m+1)
	q2 := hp{}
	for i := m; i > n; i-- {
		x := nums[i-1]
		s += x
		heap.Push(&q2, x)
		if q2.Len() > n {
			s -= heap.Pop(&q2).(int)
		}
		suf[i] = s
	}
	ans := int64(1e18)
	for i := n; i <= n*2; i++ {
		ans = min(ans, int64(pre[i]-suf[i+1]))
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

```ts
function minimumDifference(nums: number[]): number {
    const m = nums.length;
    const n = Math.floor(m / 3);
    let s = 0;
    const pre: number[] = Array(m + 1);
    const q1 = new MaxPriorityQueue();
    for (let i = 1; i <= n * 2; ++i) {
        const x = nums[i - 1];
        s += x;
        q1.enqueue(x, x);
        if (q1.size() > n) {
            s -= q1.dequeue().element;
        }
        pre[i] = s;
    }
    s = 0;
    const suf: number[] = Array(m + 1);
    const q2 = new MinPriorityQueue();
    for (let i = m; i > n; --i) {
        const x = nums[i - 1];
        s += x;
        q2.enqueue(x, x);
        if (q2.size() > n) {
            s -= q2.dequeue().element;
        }
        suf[i] = s;
    }
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = n; i <= n * 2; ++i) {
        ans = Math.min(ans, pre[i] - suf[i + 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
