---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2558.Take%20Gifts%20From%20the%20Richest%20Pile/README.md
rating: 1276
source: 第 331 场周赛 Q1
tags:
    - 数组
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [2558. 从数量最多的堆取走礼物](https://leetcode.cn/problems/take-gifts-from-the-richest-pile)

[English Version](/solution/2500-2599/2558.Take%20Gifts%20From%20the%20Richest%20Pile/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>gifts</code> ，表示各堆礼物的数量。每一秒，你需要执行以下操作：</p>

<ul>
	<li>选择礼物数量最多的那一堆。</li>
	<li>如果不止一堆都符合礼物数量最多，从中选择任一堆即可。</li>
	<li>将堆中的礼物数量减少到堆中原来礼物数量的平方根，向下取整。</li>
</ul>

<p>返回在 <code>k</code> 秒后剩下的礼物数量<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>gifts = [25,64,9,4,100], k = 4
<strong>输出：</strong>29
<strong>解释：</strong> 
按下述方式取走礼物：
- 在第一秒，选中最后一堆，剩下 10 个礼物。
- 接着第二秒选中第二堆礼物，剩下 8 个礼物。
- 然后选中第一堆礼物，剩下 5 个礼物。
- 最后，再次选中最后一堆礼物，剩下 3 个礼物。
最后剩下的礼物数量分别是 [5,8,9,4,3] ，所以，剩下礼物的总数量是 29 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>gifts = [1,1,1,1], k = 4
<strong>输出：</strong>4
<strong>解释：</strong>
在本例中，不管选中哪一堆礼物，都必须剩下 1 个礼物。 
也就是说，你无法获取任一堆中的礼物。 
所以，剩下礼物的总数量是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= gifts.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= gifts[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（大根堆）

我们将数组 $gifts$ 转存到大根堆中，然后循环 $k$ 次，每次取出堆顶元素，将堆顶元素开根号的结果再放入堆中。

最后累加堆中所有元素之和作为答案。

时间复杂度 $O(n + k \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $gifts$ 的长度。

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
