---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2233.Maximum%20Product%20After%20K%20Increments/README.md
rating: 1685
source: 第 288 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [2233. K 次增加后的最大乘积](https://leetcode.cn/problems/maximum-product-after-k-increments)

[English Version](/solution/2200-2299/2233.Maximum%20Product%20After%20K%20Increments/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个非负整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。每次操作，你可以选择&nbsp;<code>nums</code>&nbsp;中 <strong>任一</strong>&nbsp;元素并将它 <strong>增加</strong>&nbsp;<code>1</code>&nbsp;。</p>

<p>请你返回 <strong>至多</strong>&nbsp;<code>k</code>&nbsp;次操作后，能得到的<em>&nbsp;</em><code>nums</code>的&nbsp;<strong>最大乘积</strong>&nbsp;。由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [0,4], k = 5
<b>输出：</b>20
<b>解释：</b>将第一个数增加 5 次。
得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
可以证明 20 是能得到的最大乘积，所以我们返回 20 。
存在其他增加 nums 的方法，也能得到最大乘积。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [6,3,3,2], k = 2
<b>输出：</b>216
<b>解释：</b>将第二个数增加 1 次，将第四个数增加 1 次。
得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
可以证明 216 是能得到的最大乘积，所以我们返回 216 。
存在其他增加 nums 的方法，也能得到最大乘积。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（小根堆）

根据题目描述，要使得乘积最大，我们需要尽量增大较小的数，因此我们可以使用小根堆来维护数组 $\textit{nums}$。每次从小根堆中取出最小的数，将其增加 $1$，然后重新放回小根堆中。重复这个过程 $k$ 次后，我们将当前小根堆中的所有数相乘，即可得到答案。

时间复杂度 $O(k \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumProduct(self, nums: List[int], k: int) -> int:
        heapify(nums)
        for _ in range(k):
            heapreplace(nums, nums[0] + 1)
        mod = 10**9 + 7
        return reduce(lambda x, y: x * y % mod, nums)
```

#### Java

```java
class Solution {
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : nums) {
            pq.offer(x);
        }
        while (k-- > 0) {
            pq.offer(pq.poll() + 1);
        }
        final int mod = (int) 1e9 + 7;
        long ans = 1;
        for (int x : pq) {
            ans = (ans * x) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumProduct(vector<int>& nums, int k) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for (int x : nums) {
            pq.push(x);
        }
        while (k-- > 0) {
            int smallest = pq.top();
            pq.pop();
            pq.push(smallest + 1);
        }
        const int mod = 1e9 + 7;
        long long ans = 1;
        while (!pq.empty()) {
            ans = (ans * pq.top()) % mod;
            pq.pop();
        }
        return static_cast<int>(ans);
    }
};
```

#### Go

```go
func maximumProduct(nums []int, k int) int {
	h := hp{nums}
	for heap.Init(&h); k > 0; k-- {
		h.IntSlice[0]++
		heap.Fix(&h, 0)
	}
	ans := 1
	for _, x := range nums {
		ans = (ans * x) % (1e9 + 7)
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (hp) Push(any)     {}
func (hp) Pop() (_ any) { return }
```

#### TypeScript

```ts
function maximumProduct(nums: number[], k: number): number {
    const pq = new MinPriorityQueue();
    nums.forEach(x => pq.enqueue(x));
    while (k--) {
        const x = pq.dequeue().element;
        pq.enqueue(x + 1);
    }
    let ans = 1;
    const mod = 10 ** 9 + 7;
    while (!pq.isEmpty()) {
        ans = (ans * pq.dequeue().element) % mod;
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumProduct = function (nums, k) {
    const pq = new MinPriorityQueue();
    nums.forEach(x => pq.enqueue(x));
    while (k--) {
        const x = pq.dequeue().element;
        pq.enqueue(x + 1);
    }
    let ans = 1;
    const mod = 10 ** 9 + 7;
    while (!pq.isEmpty()) {
        ans = (ans * pq.dequeue().element) % mod;
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
