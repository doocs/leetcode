---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2944.Minimum%20Number%20of%20Coins%20for%20Fruits/README_EN.md
rating: 1708
source: Biweekly Contest 118 Q3
tags:
    - Queue
    - Array
    - Dynamic Programming
    - Monotonic Queue
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2944. Minimum Number of Coins for Fruits](https://leetcode.com/problems/minimum-number-of-coins-for-fruits)

[中文文档](/solution/2900-2999/2944.Minimum%20Number%20of%20Coins%20for%20Fruits/README.md)

## Description

<!-- description:start -->

<p>You are given an <strong>0-indexed</strong> integer array <code>prices</code> where <code>prices[i]</code> denotes the number of coins needed to purchase the <code>(i + 1)<sup>th</sup></code> fruit.</p>

<p>The fruit market has the following reward for each fruit:</p>

<ul>
	<li>If you purchase the <code>(i + 1)<sup>th</sup></code> fruit at <code>prices[i]</code> coins, you can get any number of the next <code>i</code> fruits for free.</li>
</ul>

<p><strong>Note</strong> that even if you <strong>can</strong> take fruit <code>j</code> for free, you can still purchase it for <code>prices[j - 1]</code> coins to receive its reward.</p>

<p>Return the <strong>minimum</strong> number of coins needed to acquire all the fruits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Purchase the 1<sup>st</sup> fruit with <code>prices[0] = 3</code> coins, you are allowed to take the 2<sup>nd</sup> fruit for free.</li>
	<li>Purchase the 2<sup>nd</sup> fruit with <code>prices[1] = 1</code> coin, you are allowed to take the 3<sup>rd</sup> fruit for free.</li>
	<li>Take the 3<sup>rd</sup> fruit for free.</li>
</ul>

<p>Note that even though you could take the 2<sup>nd</sup> fruit for free as a reward of buying 1<sup>st</sup> fruit, you purchase it to receive its reward, which is more optimal.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [1,10,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Purchase the 1<sup>st</sup> fruit with <code>prices[0] = 1</code> coin, you are allowed to take the 2<sup>nd</sup> fruit for free.</li>
	<li>Take the 2<sup>nd</sup> fruit for free.</li>
	<li>Purchase the 3<sup>rd</sup> fruit for <code>prices[2] = 1</code> coin, you are allowed to take the 4<sup>th</sup> fruit for free.</li>
	<li>Take the 4<sup>t</sup><sup>h</sup> fruit for free.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [26,18,6,12,49,7,45,45]</span></p>

<p><strong>Output:</strong> <span class="example-io">39</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Purchase the 1<sup>st</sup> fruit with <code>prices[0] = 26</code> coin, you are allowed to take the 2<sup>nd</sup> fruit for free.</li>
	<li>Take the 2<sup>nd</sup> fruit for free.</li>
	<li>Purchase the 3<sup>rd</sup> fruit for <code>prices[2] = 6</code> coin, you are allowed to take the 4<sup>th</sup>, 5<sup>th</sup> and 6<sup>th</sup> (the next three) fruits for free.</li>
	<li>Take the 4<sup>t</sup><sup>h</sup> fruit for free.</li>
	<li>Take the 5<sup>t</sup><sup>h</sup> fruit for free.</li>
	<li>Purchase the 6<sup>th</sup> fruit with <code>prices[5] = 7</code> coin, you are allowed to take the 8<sup>th</sup> and 9<sup>th</sup> fruit for free.</li>
	<li>Take the 7<sup>t</sup><sup>h</sup> fruit for free.</li>
	<li>Take the 8<sup>t</sup><sup>h</sup> fruit for free.</li>
</ul>

<p>Note that even though you could take the 6<sup>th</sup> fruit for free as a reward of buying 3<sup>rd</sup> fruit, you purchase it to receive its reward, which is more optimal.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We define a function $\textit{dfs}(i)$ to represent the minimum number of coins needed to buy all the fruits starting from the $i$-th fruit. The answer is $\textit{dfs}(1)$.

The execution logic of the function $\textit{dfs}(i)$ is as follows:

-   If $i \times 2 \geq n$, it means that buying the $(i - 1)$-th fruit is sufficient, and the remaining fruits can be obtained for free, so return $\textit{prices}[i - 1]$.
-   Otherwise, we can buy fruit $i$, and then choose a fruit $j$ to start buying from the next $i + 1$ to $2i + 1$ fruits. Thus, $\textit{dfs}(i) = \textit{prices}[i - 1] + \min_{i + 1 \le j \le 2i + 1} \textit{dfs}(j)$.

To avoid redundant calculations, we use memoization to store the results that have already been computed. When encountering the same situation again, we directly return the result.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{prices}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCoins(self, prices: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i * 2 >= len(prices):
                return prices[i - 1]
            return prices[i - 1] + min(dfs(j) for j in range(i + 1, i * 2 + 2))

        return dfs(1)
```

#### Java

```java
class Solution {
    private int[] prices;
    private int[] f;
    private int n;

    public int minimumCoins(int[] prices) {
        n = prices.length;
        f = new int[n + 1];
        this.prices = prices;
        return dfs(1);
    }

    private int dfs(int i) {
        if (i * 2 >= n) {
            return prices[i - 1];
        }
        if (f[i] == 0) {
            f[i] = 1 << 30;
            for (int j = i + 1; j <= i * 2 + 1; ++j) {
                f[i] = Math.min(f[i], prices[i - 1] + dfs(j));
            }
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumCoins(vector<int>& prices) {
        int n = prices.size();
        int f[n + 1];
        memset(f, 0x3f, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i * 2 >= n) {
                return prices[i - 1];
            }
            if (f[i] == 0x3f3f3f3f) {
                for (int j = i + 1; j <= i * 2 + 1; ++j) {
                    f[i] = min(f[i], prices[i - 1] + dfs(j));
                }
            }
            return f[i];
        };
        return dfs(1);
    }
};
```

#### Go

```go
func minimumCoins(prices []int) int {
	n := len(prices)
	f := make([]int, n+1)
	var dfs func(int) int
	dfs = func(i int) int {
		if i*2 >= n {
			return prices[i-1]
		}
		if f[i] == 0 {
			f[i] = 1 << 30
			for j := i + 1; j <= i*2+1; j++ {
				f[i] = min(f[i], dfs(j)+prices[i-1])
			}
		}
		return f[i]
	}
	return dfs(1)
}
```

#### TypeScript

```ts
function minimumCoins(prices: number[]): number {
    const n = prices.length;
    const f: number[] = Array(n + 1).fill(0);
    const dfs = (i: number): number => {
        if (i * 2 >= n) {
            return prices[i - 1];
        }
        if (f[i] === 0) {
            f[i] = 1 << 30;
            for (let j = i + 1; j <= i * 2 + 1; ++j) {
                f[i] = Math.min(f[i], prices[i - 1] + dfs(j));
            }
        }
        return f[i];
    };
    return dfs(1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can rewrite the memoization search in Solution 1 into a dynamic programming form.

Similar to Solution 1, we define $f[i]$ to represent the minimum number of coins needed to buy all the fruits starting from the $i$-th fruit. The answer is $f[1]$.

The state transition equation is $f[i] = \min_{i + 1 \le j \le 2i + 1} f[j] + \textit{prices}[i - 1]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{prices}$.

In the code implementation, we can directly use the $\textit{prices}$ array to store the $f$ array, thus optimizing the space complexity to $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCoins(self, prices: List[int]) -> int:
        n = len(prices)
        for i in range((n - 1) // 2, 0, -1):
            prices[i - 1] += min(prices[i : i * 2 + 1])
        return prices[0]
```

#### Java

```java
class Solution {
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        for (int i = (n - 1) / 2; i > 0; --i) {
            int mi = 1 << 30;
            for (int j = i; j <= i * 2; ++j) {
                mi = Math.min(mi, prices[j]);
            }
            prices[i - 1] += mi;
        }
        return prices[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumCoins(vector<int>& prices) {
        int n = prices.size();
        for (int i = (n - 1) / 2; i; --i) {
            prices[i - 1] += *min_element(prices.begin() + i, prices.begin() + 2 * i + 1);
        }
        return prices[0];
    }
};
```

#### Go

```go
func minimumCoins(prices []int) int {
	for i := (len(prices) - 1) / 2; i > 0; i-- {
		prices[i-1] += slices.Min(prices[i : i*2+1])
	}
	return prices[0]
}
```

#### TypeScript

```ts
function minimumCoins(prices: number[]): number {
    for (let i = (prices.length - 1) >> 1; i; --i) {
        prices[i - 1] += Math.min(...prices.slice(i, i * 2 + 1));
    }
    return prices[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Dynamic Programming + Monotonic Queue Optimization

Observing the state transition equation in Solution 2, we can see that for each $i$, we need to find the minimum value of $f[i + 1], f[i + 2], \cdots, f[2i + 1]$. As $i$ decreases, the range of these values also decreases. This is essentially finding the minimum value in a sliding window with a narrowing range, which can be optimized using a monotonic queue.

We calculate from back to front, maintaining a monotonically increasing queue $q$, where the queue stores indices. If the front element of $q$ is greater than $i \times 2 + 1$, it means that the elements after $i$ will not be used, so we dequeue the front element. If $i$ is not greater than $(n - 1) / 2$, we can add $\textit{prices}[q[0] - 1]$ to $\textit{prices}[i - 1]$, and then add $i$ to the back of the queue. If the fruit price corresponding to the back element of $q$ is greater than or equal to $\textit{prices}[i - 1]$, we dequeue the back element until the fruit price corresponding to the back element is less than $\textit{prices}[i - 1]$ or the queue is empty, then add $i$ to the back of the queue.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{prices}$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
