---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1354.Construct%20Target%20Array%20With%20Multiple%20Sums/README_EN.md
rating: 2014
source: Weekly Contest 176 Q4
tags:
    - Array
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1354. Construct Target Array With Multiple Sums](https://leetcode.com/problems/construct-target-array-with-multiple-sums)

[中文文档](/solution/1300-1399/1354.Construct%20Target%20Array%20With%20Multiple%20Sums/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>target</code> of n integers. From a starting array <code>arr</code> consisting of <code>n</code> 1&#39;s, you may perform the following procedure :</p>

<ul>
	<li>let <code>x</code> be the sum of all elements currently in your array.</li>
	<li>choose index <code>i</code>, such that <code>0 &lt;= i &lt; n</code> and set the value of <code>arr</code> at index <code>i</code> to <code>x</code>.</li>
	<li>You may repeat this procedure as many times as needed.</li>
</ul>

<p>Return <code>true</code> <em>if it is possible to construct the</em> <code>target</code> <em>array from</em> <code>arr</code><em>, otherwise, return</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [9,3,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> Start with arr = [1, 1, 1] 
[1, 1, 1], sum = 3 choose index 1
[1, 3, 1], sum = 5 choose index 2
[1, 3, 5], sum = 9 choose index 0
[9, 3, 5] Done
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [1,1,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> Impossible to create target array from [1,1,1,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [8,5]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= target[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Construction + Priority Queue (Max Heap)

We observe that if we start constructing the target array $\textit{target}$ from the array $\textit{arr}$ in a forward manner, it is difficult to determine which index $i$ to choose each time, making the problem quite complex. However, if we construct in reverse starting from the array $\textit{target}$, each construction step must select the largest element in the current array, which ensures that each construction is unique, making the problem relatively simple.

Therefore, we can use a priority queue (max heap) to store the elements of array $\textit{target}$, and use a variable $s$ to record the sum of all elements in array $\textit{target}$. Each time we extract the maximum element $mx$ from the priority queue and calculate the sum $t$ of all elements in the current array except $mx$. If $t \lt 1$ or $mx - t \lt 1$, it means the target array $\textit{target}$ cannot be constructed, and we return `false`. Otherwise, we calculate $mx \bmod t$. If $mx \bmod t = 0$, we set $x = t$; otherwise, we set $x = mx \bmod t$. We add $x$ to the priority queue and update the value of $s$. We repeat this process until all elements in the priority queue become $1$, at which point we return `true`.

The time complexity is $O(n \log n)$ and the space complexity is $O(n)$, where $n$ is the length of array $\textit{target}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPossible(self, target: List[int]) -> bool:
        s = sum(target)
        pq = [-x for x in target]
        heapify(pq)
        while -pq[0] > 1:
            mx = -heappop(pq)
            t = s - mx
            if t == 0 or mx - t < 1:
                return False
            x = (mx % t) or t
            heappush(pq, -x)
            s = s - mx + x
        return True
```

#### Java

```java
class Solution {
    public boolean isPossible(int[] target) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long s = 0;
        for (int x : target) {
            s += x;
            pq.offer((long) x);
        }
        while (pq.peek() > 1) {
            long mx = pq.poll();
            long t = s - mx;
            if (t == 0 || mx - t < 1) {
                return false;
            }
            long x = mx % t;
            if (x == 0) {
                x = t;
            }
            pq.offer(x);
            s = s - mx + x;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPossible(vector<int>& target) {
        priority_queue<int> pq;
        long long s = 0;
        for (int i = 0; i < target.size(); i++) {
            s += target[i];
            pq.push(target[i]);
        }
        while (pq.top() != 1) {
            int mx = pq.top();
            pq.pop();
            long long t = s - mx;
            if (t < 1 || mx - t < 1) {
                return false;
            }
            int x = mx % t;
            if (x == 0) {
                x = t;
            }
            pq.push(x);
            s = s - mx + x;
        }
        return true;
    }
};
```

#### Go

```go
func isPossible(target []int) bool {
	pq := &hp{target}
	s := 0
	for _, x := range target {
		s += x
	}
	heap.Init(pq)
	for target[0] > 1 {
		mx := target[0]
		t := s - mx
		if t < 1 || mx-t < 1 {
			return false
		}
		x := mx % t
		if x == 0 {
			x = t
		}
		target[0] = x
		heap.Fix(pq, 0)
		s = s - mx + x
	}
	return true
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Pop() (_ any)         { return }
func (hp) Push(any)             {}
```

#### TypeScript

```ts
function isPossible(target: number[]): boolean {
    const pq = new MaxPriorityQueue<number>();
    let s = 0;
    for (const x of target) {
        s += x;
        pq.enqueue(x);
    }
    while (pq.front() > 1) {
        const mx = pq.dequeue();
        const t = s - mx;
        if (t < 1 || mx - t < 1) {
            return false;
        }
        const x = mx % t || t;
        pq.enqueue(x);
        s = s - mx + x;
    }
    return true;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn is_possible(target: Vec<i32>) -> bool {
        let mut pq = BinaryHeap::from(target.clone());
        let mut s: i64 = target.iter().map(|&x| x as i64).sum();

        while let Some(&mx) = pq.peek() {
            if mx == 1 {
                break;
            }
            let mx = pq.pop().unwrap() as i64;
            let t = s - mx;
            if t < 1 || mx - t < 1 {
                return false;
            }
            let x = if mx % t == 0 { t } else { mx % t };
            pq.push(x as i32);
            s = s - mx + x;
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
