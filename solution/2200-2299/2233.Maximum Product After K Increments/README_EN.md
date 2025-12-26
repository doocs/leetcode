---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2233.Maximum%20Product%20After%20K%20Increments/README_EN.md
rating: 1685
source: Weekly Contest 288 Q3
tags:
    - Greedy
    - Array
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2233. Maximum Product After K Increments](https://leetcode.com/problems/maximum-product-after-k-increments)

[中文文档](/solution/2200-2299/2233.Maximum%20Product%20After%20K%20Increments/README.md)

## Description

<!-- description:start -->

<p>You are given an array of non-negative integers <code>nums</code> and an integer <code>k</code>. In one operation, you may choose <strong>any</strong> element from <code>nums</code> and <strong>increment</strong> it by <code>1</code>.</p>

<p>Return<em> the <strong>maximum</strong> <strong>product</strong> of </em><code>nums</code><em> after <strong>at most</strong> </em><code>k</code><em> operations. </em>Since the answer may be very large, return it <b>modulo</b> <code>10<sup>9</sup> + 7</code>. Note that you should maximize the product before taking the modulo.&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,4], k = 5
<strong>Output:</strong> 20
<strong>Explanation:</strong> Increment the first number 5 times.
Now nums = [5, 4], with a product of 5 * 4 = 20.
It can be shown that 20 is maximum product possible, so we return 20.
Note that there may be other ways to increment nums to have the maximum product.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,3,3,2], k = 2
<strong>Output:</strong> 216
<strong>Explanation:</strong> Increment the second number 1 time and increment the fourth number 1 time.
Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
It can be shown that 216 is maximum product possible, so we return 216.
Note that there may be other ways to increment nums to have the maximum product.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Priority Queue (Min-Heap)

According to the problem description, to maximize the product, we need to increase the smaller numbers as much as possible. Therefore, we can use a min-heap to maintain the array $\textit{nums}$. Each time, we take the smallest number from the min-heap, increase it by $1$, and then put it back into the min-heap. After repeating this process $k$ times, we multiply all the numbers currently in the min-heap to get the answer.

The time complexity is $O(k \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

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
    const pq = new MinPriorityQueue<number>();
    nums.forEach(x => pq.enqueue(x));
    while (k--) {
        const x = pq.dequeue();
        pq.enqueue(x + 1);
    }
    let ans = 1;
    const mod = 10 ** 9 + 7;
    while (!pq.isEmpty()) {
        ans = (ans * pq.dequeue()) % mod;
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
        const x = pq.dequeue();
        pq.enqueue(x + 1);
    }
    let ans = 1;
    const mod = 10 ** 9 + 7;
    while (!pq.isEmpty()) {
        ans = (ans * pq.dequeue()) % mod;
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
