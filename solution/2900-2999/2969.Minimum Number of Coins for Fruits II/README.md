# [2969. 购买水果需要的最少金币数 II](https://leetcode.cn/problems/minimum-number-of-coins-for-fruits-ii)

[English Version](/solution/2900-2999/2969.Minimum%20Number%20of%20Coins%20for%20Fruits%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你在一个水果超市里，货架上摆满了玲琅满目的奇珍异果。</p>

<p>给你一个 <b>下标从 1 开始</b>&nbsp;的数组&nbsp;<code>prices</code>，其中&nbsp;<code>prices[i]</code>&nbsp;表示你购买第&nbsp;<code>i</code>&nbsp;个水果所需的硬币数量。</p>

<p>水果市场有以下优惠活动：</p>

<ul>
	<li>如果你用&nbsp;<code>prices[i]</code>&nbsp;个硬币购买第 <code>i</code> 个水果， 那么接下来的 i 个水果你都可以免费获得。</li>
</ul>

<p><strong>请注意</strong>&nbsp;即使你 <strong>可以</strong> 免费获得第&nbsp;<code>j</code>&nbsp;个水果，你仍然可以用&nbsp;<code>prices[j]</code>&nbsp;个硬币来购买它，以获取新的优惠。</p>

<p>返回 <em>获得所有水果所需的 <strong>最小</strong> 硬币数量。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>prices = [3,1,2]
<strong>输出：</strong>4
<strong>解释：</strong>你可以按以下方式获取水果：
- 用3个硬币购买第1个水果，并且可以免费获得第2个水果。
- 用1个硬币购买第2个水果，并且可以免费获得第3个水果。
- 免费获得第三个水果。
请注意，即使你可以免费获得第2个水果，你还是购买了它，因为这是更优的选择。
可以证明4是获取所有水果所需的最小硬币数量。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
输入：prices = [1,10,1,1]
输出：2
解释：你可以按以下方式获取水果：
- 用1个硬币购买第1个水果，并且可以免费获得第2个水果。
- 免费获得第2个水果。
- 用1个硬币购买第3个水果，并且可以免费获得第4个水果。
- 免费获得第4个水果。
可以证明2是获取所有水果所需的最小硬币数量。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i]$ 表示从第 $i$ 个水果开始购买所有水果所需要的最少金币数。那么答案就是 $f[1]$。

状态转移方程为 $f[i] = \min_{i + 1 \le j \le 2i + 1} f[j] + prices[i - 1]$。

在实现上，我们从后往前计算，并且可以直接在数组 $prices$ 上进行状态转移，这样可以节省空间。

以上做法的时间复杂度为 $O(n^2)$，由于本题 $n$ 的规模达到 $10^5$，因此会超时。

我们观察状态转移方程，可以发现，对于每个 $i$，我们需要求出 $f[i + 1], f[i + 2], \cdots, f[2i + 1]$ 的最小值，并且随着 $i$ 的减小，这些值的范围也在减小。这实际上是求一个单调收窄的滑动窗口的最小值，我们可以使用单调队列来优化。

我们从后往前计算，维护一个单调递增的队列 $q$，队列中存储的是下标。如果 $q$ 的队首元素大于 $i \times 2 + 1$，说明 $i$ 之后的元素都不会被用到，所以我们将队首元素出队。如果 $i$ 不大于 $(n - 1) / 2$，那么我们可以将 $prices[q[0] - 1]$ 加到 $prices[i - 1]$ 上，然后将 $i$ 加入队尾。如果 $q$ 的队尾元素对应的水果价格大于等于 $prices[i - 1]$，那么我们将队尾元素出队，直到队尾元素对应的水果价格小于 $prices[i - 1]$ 或者队列为空，然后将 $i$ 加入队尾。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumCoins(self, prices: List[int]) -> int:
        n = len(prices)
        q = deque()
        for i in range(n, 0, -1):
            while q and q[0] > i * 2 + 1:
                q.popleft()
            if i <= (n - 1) // 2:
                prices[i - 1] += prices[q[0] - 1]
            while q and prices[q[-1] - 1] >= prices[i - 1]:
                q.pop()
            q.append(i)
        return prices[0]
```

```java
class Solution {
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = n; i > 0; --i) {
            while (!q.isEmpty() && q.peek() > i * 2 + 1) {
                q.poll();
            }
            if (i <= (n - 1) / 2) {
                prices[i - 1] += prices[q.peek() - 1];
            }
            while (!q.isEmpty() && prices[q.peekLast() - 1] >= prices[i - 1]) {
                q.pollLast();
            }
            q.offer(i);
        }
        return prices[0];
    }
}
```

```cpp
class Solution {
public:
    int minimumCoins(vector<int>& prices) {
        int n = prices.size();
        deque<int> q;
        for (int i = n; i; --i) {
            while (q.size() && q.front() > i * 2 + 1) {
                q.pop_front();
            }
            if (i <= (n - 1) / 2) {
                prices[i - 1] += prices[q.front() - 1];
            }
            while (q.size() && prices[q.back() - 1] >= prices[i - 1]) {
                q.pop_back();
            }
            q.push_back(i);
        }
        return prices[0];
    }
};
```

```go
func minimumCoins(prices []int) int {
	n := len(prices)
	q := Deque{}
	for i := n; i > 0; i-- {
		for q.Size() > 0 && q.Front() > i*2+1 {
			q.PopFront()
		}
		if i <= (n-1)/2 {
			prices[i-1] += prices[q.Front()-1]
		}
		for q.Size() > 0 && prices[q.Back()-1] >= prices[i-1] {
			q.PopBack()
		}
		q.PushBack(i)
	}
	return prices[0]
}

// template
type Deque struct{ l, r []int }

func (q Deque) Empty() bool {
	return len(q.l) == 0 && len(q.r) == 0
}

func (q Deque) Size() int {
	return len(q.l) + len(q.r)
}

func (q *Deque) PushFront(v int) {
	q.l = append(q.l, v)
}

func (q *Deque) PushBack(v int) {
	q.r = append(q.r, v)
}

func (q *Deque) PopFront() (v int) {
	if len(q.l) > 0 {
		q.l, v = q.l[:len(q.l)-1], q.l[len(q.l)-1]
	} else {
		v, q.r = q.r[0], q.r[1:]
	}
	return
}

func (q *Deque) PopBack() (v int) {
	if len(q.r) > 0 {
		q.r, v = q.r[:len(q.r)-1], q.r[len(q.r)-1]
	} else {
		v, q.l = q.l[0], q.l[1:]
	}
	return
}

func (q Deque) Front() int {
	if len(q.l) > 0 {
		return q.l[len(q.l)-1]
	}
	return q.r[0]
}

func (q Deque) Back() int {
	if len(q.r) > 0 {
		return q.r[len(q.r)-1]
	}
	return q.l[0]
}

func (q Deque) Get(i int) int {
	if i < len(q.l) {
		return q.l[len(q.l)-1-i]
	}
	return q.r[i-len(q.l)]
}
```

```ts
function minimumCoins(prices: number[]): number {
    const n = prices.length;
    const q = new Deque<number>();
    for (let i = n; i; --i) {
        while (q.getSize() && q.frontValue()! > i * 2 + 1) {
            q.popFront();
        }
        if (i <= (n - 1) >> 1) {
            prices[i - 1] += prices[q.frontValue()! - 1];
        }
        while (q.getSize() && prices[q.backValue()! - 1] >= prices[i - 1]) {
            q.popBack();
        }
        q.pushBack(i);
    }
    return prices[0];
}

class Node<T> {
    value: T;
    next: Node<T> | null;
    prev: Node<T> | null;

    constructor(value: T) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

class Deque<T> {
    private front: Node<T> | null;
    private back: Node<T> | null;
    private size: number;

    constructor() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    pushFront(val: T): void {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.next = this.front;
            this.front!.prev = newNode;
            this.front = newNode;
        }
        this.size++;
    }

    pushBack(val: T): void {
        const newNode = new Node(val);
        if (this.isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        } else {
            newNode.prev = this.back;
            this.back!.next = newNode;
            this.back = newNode;
        }
        this.size++;
    }

    popFront(): T | undefined {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.front!.value;
        this.front = this.front!.next;
        if (this.front !== null) {
            this.front.prev = null;
        } else {
            this.back = null;
        }
        this.size--;
        return value;
    }

    popBack(): T | undefined {
        if (this.isEmpty()) {
            return undefined;
        }
        const value = this.back!.value;
        this.back = this.back!.prev;
        if (this.back !== null) {
            this.back.next = null;
        } else {
            this.front = null;
        }
        this.size--;
        return value;
    }

    frontValue(): T | undefined {
        return this.front?.value;
    }

    backValue(): T | undefined {
        return this.back?.value;
    }

    getSize(): number {
        return this.size;
    }

    isEmpty(): boolean {
        return this.size === 0;
    }
}
```

<!-- tabs:end -->

<!-- end -->
