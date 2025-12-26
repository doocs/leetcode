---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2599.Make%20the%20Prefix%20Sum%20Non-negative/README.md
tags:
    - 贪心
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [2599. 使前缀和数组非负 🔒](https://leetcode.cn/problems/make-the-prefix-sum-non-negative)

[English Version](/solution/2500-2599/2599.Make%20the%20Prefix%20Sum%20Non-negative/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <strong>下标从0开始</strong> 的整数数组 <code>nums</code> 。你可以任意多次执行以下操作：</p>

<ul>
	<li>从 <code>nums</code> 中选择任意一个元素，并将其放到 <code>nums</code> 的末尾。</li>
</ul>

<p><code>nums</code> 的前缀和数组是一个与 <code>nums</code> 长度相同的数组 <code>prefix</code> ，其中 <code>prefix[i]</code> 是所有整数 <code>nums[j]</code>（其中 <code>j</code> 在包括区间 <code>[0，i]</code> 内）的总和。</p>

<p>返回使前缀和数组不包含负整数的最小操作次数。测试用例的生成方式保证始终可以使前缀和数组非负。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,-5,4]
<b>输出：</b>0
<b>解释：</b>我们不需要执行任何操作。
给定数组为 [2, 3, -5, 4]，它的前缀和数组是 [2, 5, 0, 4]。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums = [3,-5,-2,6]
<b>输出：</b>1
<b>解释：</b>我们可以对索引为1的元素执行一次操作。
操作后的数组为 [3, -2, 6, -5]，它的前缀和数组是 [3, 1, 7, 2]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（小根堆）

我们用变量 $s$ 记录当前数组的前缀和。

遍历数组 $nums$，将当前元素 $x$ 加入前缀和 $s$ 中，如果 $x$ 为负数，则将 $x$ 加入小根堆中。如果此时 $s$ 为负数，我们贪心地取出最小的负数，将其从 $s$ 中减去，同时将答案加一。最终返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makePrefSumNonNegative(self, nums: List[int]) -> int:
        h = []
        ans = s = 0
        for x in nums:
            s += x
            if x < 0:
                heappush(h, x)
            while s < 0:
                s -= heappop(h)
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int makePrefSumNonNegative(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        long s = 0;
        for (int x : nums) {
            s += x;
            if (x < 0) {
                pq.offer(x);
            }
            while (s < 0) {
                s -= pq.poll();
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int makePrefSumNonNegative(vector<int>& nums) {
        priority_queue<int, vector<int>, greater<int>> pq;
        int ans = 0;
        long long s = 0;
        for (int& x : nums) {
            s += x;
            if (x < 0) {
                pq.push(x);
            }
            while (s < 0) {
                s -= pq.top();
                pq.pop();
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func makePrefSumNonNegative(nums []int) (ans int) {
	pq := hp{}
	s := 0
	for _, x := range nums {
		s += x
		if x < 0 {
			heap.Push(&pq, x)
		}
		for s < 0 {
			s -= heap.Pop(&pq).(int)
			ans++
		}
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
function makePrefSumNonNegative(nums: number[]): number {
    const pq = new MinPriorityQueue<number>();
    let ans = 0;
    let s = 0;
    for (const x of nums) {
        s += x;
        if (x < 0) {
            pq.enqueue(x);
        }
        while (s < 0) {
            s -= pq.dequeue();
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
