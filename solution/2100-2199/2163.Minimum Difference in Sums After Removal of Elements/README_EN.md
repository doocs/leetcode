---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2163.Minimum%20Difference%20in%20Sums%20After%20Removal%20of%20Elements/README_EN.md
rating: 2225
source: Biweekly Contest 71 Q4
tags:
    - Array
    - Dynamic Programming
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2163. Minimum Difference in Sums After Removal of Elements](https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements)

[中文文档](/solution/2100-2199/2163.Minimum%20Difference%20in%20Sums%20After%20Removal%20of%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> consisting of <code>3 * n</code> elements.</p>

<p>You are allowed to remove any <strong>subsequence</strong> of elements of size <strong>exactly</strong> <code>n</code> from <code>nums</code>. The remaining <code>2 * n</code> elements will be divided into two <strong>equal</strong> parts:</p>

<ul>
	<li>The first <code>n</code> elements belonging to the first part and their sum is <code>sum<sub>first</sub></code>.</li>
	<li>The next <code>n</code> elements belonging to the second part and their sum is <code>sum<sub>second</sub></code>.</li>
</ul>

<p>The <strong>difference in sums</strong> of the two parts is denoted as <code>sum<sub>first</sub> - sum<sub>second</sub></code>.</p>

<ul>
	<li>For example, if <code>sum<sub>first</sub> = 3</code> and <code>sum<sub>second</sub> = 2</code>, their difference is <code>1</code>.</li>
	<li>Similarly, if <code>sum<sub>first</sub> = 2</code> and <code>sum<sub>second</sub> = 3</code>, their difference is <code>-1</code>.</li>
</ul>

<p>Return <em>the <strong>minimum difference</strong> possible between the sums of the two parts after the removal of </em><code>n</code><em> elements</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> Here, nums has 3 elements, so n = 1. 
Thus we have to remove 1 element from nums and divide the array into two equal parts.
- If we remove nums[0] = 3, the array will be [1,2]. The difference in sums of the two parts will be 1 - 2 = -1.
- If we remove nums[1] = 1, the array will be [3,2]. The difference in sums of the two parts will be 3 - 2 = 1.
- If we remove nums[2] = 2, the array will be [3,1]. The difference in sums of the two parts will be 3 - 1 = 2.
The minimum difference between sums of the two parts is min(-1,1,2) = -1. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,9,5,8,1,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Here n = 2. So we must remove 2 elements and divide the remaining array into two parts containing two elements each.
If we remove nums[2] = 5 and nums[3] = 8, the resultant array will be [7,9,1,3]. The difference in sums will be (7+9) - (1+3) = 12.
To obtain the minimum difference, we should remove nums[1] = 9 and nums[4] = 1. The resultant array becomes [7,5,8,3]. The difference in sums of the two parts is (7+5) - (8+3) = 1.
It can be shown that it is not possible to obtain a difference smaller than 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == 3 * n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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

<!-- solution:end -->

<!-- problem:end -->
