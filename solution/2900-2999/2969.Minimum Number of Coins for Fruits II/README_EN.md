# [2969. Minimum Number of Coins for Fruits II 🔒](https://leetcode.com/problems/minimum-number-of-coins-for-fruits-ii)

[中文文档](/solution/2900-2999/2969.Minimum%20Number%20of%20Coins%20for%20Fruits%20II/README.md)

<!-- tags:Queue,Array,Dynamic Programming,Monotonic Queue,Heap (Priority Queue) -->

## Description

<p>You are at a fruit market with different types of exotic fruits on display.</p>

<p>You are given a <strong>1-indexed</strong> array <code>prices</code>, where <code>prices[i]</code> denotes the number of coins needed to purchase the <code>i<sup>th</sup></code> fruit.</p>

<p>The fruit market has the following offer:</p>

<ul>
	<li>If you purchase the <code>i<sup>th</sup></code> fruit at <code>prices[i]</code> coins, you can get the next <code>i</code> fruits for free.</li>
</ul>

<p><strong>Note</strong> that even if you <strong>can</strong> take fruit <code>j</code> for free, you can still purchase it for <code>prices[j]</code> coins to receive a new offer.</p>

<p>Return <em>the <strong>minimum</strong> number of coins needed to acquire all the fruits</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [3,1,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can acquire the fruits as follows:
- Purchase the 1<sup>st</sup> fruit with 3 coins, and you are allowed to take the 2<sup>nd</sup> fruit for free.
- Purchase the 2<sup>nd</sup> fruit with 1 coin, and you are allowed to take the 3<sup>rd</sup> fruit for free.
- Take the 3<sup>rd</sup> fruit for free.
Note that even though you were allowed to take the 2<sup>nd</sup> fruit for free, you purchased it because it is more optimal.
It can be proven that 4 is the minimum number of coins needed to acquire all the fruits.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,10,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can acquire the fruits as follows:
- Purchase the 1<sup>st</sup> fruit with 1 coin, and you are allowed to take the 2<sup>nd</sup> fruit for free.
- Take the 2<sup>nd</sup> fruit for free.
- Purchase the 3<sup>rd</sup> fruit for 1 coin, and you are allowed to take the 4<sup>th</sup> fruit for free.
- Take the 4<sup>t</sup><sup>h</sup> fruit for free.
It can be proven that 2 is the minimum number of coins needed to acquire all the fruits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i]$ as the minimum number of coins needed to buy all fruits starting from the $i$th fruit. So the answer is $f[1]$.

The state transition equation is $f[i] = \min_{i + 1 \le j \le 2i + 1} f[j] + prices[i - 1]$.

In implementation, we calculate from back to front, and we can directly perform state transition on the array $prices$, which can save space.

The time complexity of the above method is $O(n^2)$. Since the scale of $n$ in this problem reaches $10^5$, it will time out.

We observe the state transition equation and find that for each $i$, we need to find the minimum value of $f[i + 1], f[i + 2], \cdots, f[2i + 1]$, and as $i$ decreases, the range of these values is also decreasing. This is actually finding the minimum value of a monotonically narrowing sliding window, and we can use a monotonic queue to optimize.

We calculate from back to front, maintain a monotonically increasing queue $q$, and the queue stores the index. If the head element of $q$ is greater than $i \times 2 + 1$, it means that the elements after $i$ will not be used, so we dequeue the head element. If $i$ is not greater than $(n - 1) / 2$, then we can add $prices[q[0] - 1]$ to $prices[i - 1]$, and then add $i$ to the tail of the queue. If the price of the fruit corresponding to the tail element of $q$ is greater than or equal to $prices[i - 1]$, then we dequeue the tail element until the price of the fruit corresponding to the tail element is less than $prices[i - 1]$ or the queue is empty, and then add $i$ to the tail of the queue.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $prices$.

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
