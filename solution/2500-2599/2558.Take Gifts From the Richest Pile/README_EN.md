---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2558.Take%20Gifts%20From%20the%20Richest%20Pile/README_EN.md
rating: 1276
source: Weekly Contest 331 Q1
tags:
    - Array
    - Simulation
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2558. Take Gifts From the Richest Pile](https://leetcode.com/problems/take-gifts-from-the-richest-pile)

[中文文档](/solution/2500-2599/2558.Take%20Gifts%20From%20the%20Richest%20Pile/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>gifts</code> denoting the number of gifts in various piles. Every second, you do the following:</p>

<ul>
	<li>Choose the pile with the maximum number of gifts.</li>
	<li>If there is more than one pile with the maximum number of gifts, choose any.</li>
	<li>Reduce the number of gifts in the pile to the floor of the square root of the original number of gifts in the pile.</li>
</ul>

<p>Return <em>the number of gifts remaining after </em><code>k</code><em> seconds.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> gifts = [25,64,9,4,100], k = 4
<strong>Output:</strong> 29
<strong>Explanation:</strong> 
The gifts are taken in the following way:
- In the first second, the last pile is chosen and 10 gifts are left behind.
- Then the second pile is chosen and 8 gifts are left behind.
- After that the first pile is chosen and 5 gifts are left behind.
- Finally, the last pile is chosen again and 3 gifts are left behind.
The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> gifts = [1,1,1,1], k = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile. 
That is, you can&#39;t take any pile with you. 
So, the total gifts remaining are 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= gifts.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= gifts[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Max Heap)

We can store the array $gifts$ in a max heap, and then loop $k$ times, each time taking out the top element of the heap, taking the square root of it, and putting the result back into the heap.

Finally, we add up all the elements in the heap as the answer.

The time complexity is $O(n + k \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $gifts$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pickGifts(self, gifts: List[int], k: int) -> int:
        h = [-v for v in gifts]
        heapify(h)
        for _ in range(k):
            heapreplace(h, -int(sqrt(-h[0])))
        return -sum(h)
```

#### Java

```java
class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int v : gifts) {
            pq.offer(v);
        }
        while (k-- > 0) {
            pq.offer((int) Math.sqrt(pq.poll()));
        }
        long ans = 0;
        for (int v : pq) {
            ans += v;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long pickGifts(vector<int>& gifts, int k) {
        make_heap(gifts.begin(), gifts.end());
        while (k--) {
            pop_heap(gifts.begin(), gifts.end());
            gifts.back() = sqrt(gifts.back());
            push_heap(gifts.begin(), gifts.end());
        }
        return accumulate(gifts.begin(), gifts.end(), 0LL);
    }
};
```

#### Go

```go
func pickGifts(gifts []int, k int) (ans int64) {
	h := &hp{gifts}
	heap.Init(h)
	for ; k > 0; k-- {
		gifts[0] = int(math.Sqrt(float64(gifts[0])))
		heap.Fix(h, 0)
	}
	for _, x := range gifts {
		ans += int64(x)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Pop() (_ any)         { return }
func (hp) Push(any)             {}
```

#### TypeScript

```ts
function pickGifts(gifts: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    gifts.forEach(v => pq.enqueue(v));
    while (k--) {
        let v = pq.dequeue().element;
        v = Math.floor(Math.sqrt(v));
        pq.enqueue(v);
    }
    let ans = 0;
    while (!pq.isEmpty()) {
        ans += pq.dequeue().element;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn pick_gifts(gifts: Vec<i32>, k: i32) -> i64 {
        let mut h = std::collections::BinaryHeap::from(gifts);
        let mut ans = 0;

        for _ in 0..k {
            if let Some(mut max_gift) = h.pop() {
                max_gift = (max_gift as f64).sqrt().floor() as i32;
                h.push(max_gift);
            }
        }

        for x in h {
            ans += x as i64;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
