---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2944.Minimum%20Number%20of%20Coins%20for%20Fruits/README.md
rating: 1708
source: 第 118 场双周赛 Q3
tags:
    - 队列
    - 数组
    - 动态规划
    - 单调队列
    - 堆（优先队列）
---

<!-- problem:start -->

# [2944. 购买水果需要的最少金币数](https://leetcode.cn/problems/minimum-number-of-coins-for-fruits)

[English Version](/solution/2900-2999/2944.Minimum%20Number%20of%20Coins%20for%20Fruits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你在一个水果超市里，货架上摆满了玲琅满目的奇珍异果。</p>

<p>给你一个下标从 <strong>1</strong>&nbsp;开始的数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i]</code>&nbsp;表示你购买第 <code>i</code>&nbsp;个水果需要花费的金币数目。</p>

<p>水果超市有如下促销活动：</p>

<ul>
	<li>如果你花费 <code>price[i]</code>&nbsp;购买了水果&nbsp;<code>i</code>&nbsp;，那么后面的&nbsp;<code>i</code>&nbsp;个水果你可以免费获得。</li>
</ul>

<p><strong>注意</strong>&nbsp;，即使你&nbsp;<strong>可以</strong>&nbsp;免费获得水果&nbsp;<code>j</code>&nbsp;，你仍然可以花费&nbsp;<code>prices[j]</code>&nbsp;个金币去购买它以便能免费获得接下来的 <code>j</code>&nbsp;个水果。</p>

<p>请你返回获得所有水果所需要的 <strong>最少</strong>&nbsp;金币数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>prices = [3,1,2]
<b>输出：</b>4
<b>解释</b><strong>：</strong>你可以按如下方法获得所有水果：
- 花 3 个金币购买水果 1 ，然后免费获得水果 2 。
- 花 1 个金币购买水果 2 ，然后免费获得水果 3 。
- 免费获得水果 3 。
注意，虽然你可以免费获得水果 2 ，但你还是花 1 个金币去购买它，因为这样的总花费最少。
购买所有水果需要最少花费 4 个金币。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>prices = [1,10,1,1]
<b>输出：</b>2
<b>解释：</b>你可以按如下方法获得所有水果：
- 花 1 个金币购买水果 1 ，然后免费获得水果 2 。
- 免费获得水果 2 。
- 花 1 个金币购买水果 3 ，然后免费获得水果 4 。
- 免费获得水果 4 。
购买所有水果需要最少花费 2 个金币。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们定义一个函数 $dfs(i)$，表示从第 $i$ 个水果开始购买所有水果所需要的最少金币数。那么答案就是 $dfs(1)$。

函数 $dfs(i)$ 的执行逻辑如下：

-   如果 $i \times 2 \geq n$，说明只要买第 $i - 1$ 个水果即可，剩余的水果都可以免费获得，所以返回 $prices[i - 1]$。
-   否则，我们可以购买水果 $i$，然后在接下来的 $i + 1$ 到 $2i + 1$ 个水果中选择一个水果 $j$ 开始购买，那么 $dfs(i) = prices[i - 1] + \min_{i + 1 \le j \le 2i + 1} dfs(j)$。

为了避免重复计算，我们使用记忆化搜索的方法，将已经计算过的结果保存起来，下次遇到相同的情况时，直接返回结果即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

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

### 方法二：动态规划

我们可以将方法一中的记忆化搜索改写成动态规划的形式。

与方法一类似，我们定义 $f[i]$ 表示从第 $i$ 个水果开始购买所有水果所需要的最少金币数。那么答案就是 $f[1]$。

状态转移方程为 $f[i] = \min_{i + 1 \le j \le 2i + 1} f[j] + prices[i - 1]$。

在实现上，我们从后往前计算，并且可以直接在数组 $prices$ 上进行状态转移，这样可以节省空间。

时间复杂度 $O(n^2)$，其中 $n$ 为数组 $prices$ 的长度。空间复杂度 $O(1)$。

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

### 方法三：动态规划 + 单调队列优化

我们观察方法二中的状态转移方程，可以发现，对于每个 $i$，我们需要求出 $f[i + 1], f[i + 2], \cdots, f[2i + 1]$ 的最小值，并且随着 $i$ 的减小，这些值的范围也在减小。这实际上是求一个单调收窄的滑动窗口的最小值，我们可以使用单调队列来优化。

我们从后往前计算，维护一个单调递增的队列 $q$，队列中存储的是下标。如果 $q$ 的队首元素大于 $i \times 2 + 1$，说明 $i$ 之后的元素都不会被用到，所以我们将队首元素出队。如果 $i$ 不大于 $(n - 1) / 2$，那么我们可以将 $prices[q[0] - 1]$ 加到 $prices[i - 1]$ 上，然后将 $i$ 加入队尾。如果 $q$ 的队尾元素对应的水果价格大于等于 $prices[i - 1]$，那么我们将队尾元素出队，直到队尾元素对应的水果价格小于 $prices[i - 1]$ 或者队列为空，然后将 $i$ 加入队尾。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

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
