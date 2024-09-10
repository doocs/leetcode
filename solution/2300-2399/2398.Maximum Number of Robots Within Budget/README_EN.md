---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2398.Maximum%20Number%20of%20Robots%20Within%20Budget/README_EN.md
rating: 1917
source: Biweekly Contest 86 Q4
tags:
    - Queue
    - Array
    - Binary Search
    - Prefix Sum
    - Sliding Window
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2398. Maximum Number of Robots Within Budget](https://leetcode.com/problems/maximum-number-of-robots-within-budget)

[中文文档](/solution/2300-2399/2398.Maximum%20Number%20of%20Robots%20Within%20Budget/README.md)

## Description

<!-- description:start -->

<p>You have <code>n</code> robots. You are given two <strong>0-indexed</strong> integer arrays, <code>chargeTimes</code> and <code>runningCosts</code>, both of length <code>n</code>. The <code>i<sup>th</sup></code> robot costs <code>chargeTimes[i]</code> units to charge and costs <code>runningCosts[i]</code> units to run. You are also given an integer <code>budget</code>.</p>

<p>The <strong>total cost</strong> of running <code>k</code> chosen robots is equal to <code>max(chargeTimes) + k * sum(runningCosts)</code>, where <code>max(chargeTimes)</code> is the largest charge cost among the <code>k</code> robots and <code>sum(runningCosts)</code> is the sum of running costs among the <code>k</code> robots.</p>

<p>Return<em> the <strong>maximum</strong> number of <strong>consecutive</strong> robots you can run such that the total cost <strong>does not</strong> exceed </em><code>budget</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
It is possible to run all individual and consecutive pairs of robots within budget.
To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
<strong>Output:</strong> 0
<strong>Explanation:</strong> No robot can be run that does not exceed the budget, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>chargeTimes.length == runningCosts.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= chargeTimes[i], runningCosts[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers + Monotonic Queue

The problem is essentially finding the maximum value within a sliding window, which can be solved using a monotonic queue.

We only need to use binary search to enumerate the size of the window $k$, and find the largest $k$ that satisfies the problem requirements.

In the implementation process, we don't actually need to perform binary search enumeration. We just need to change the fixed window to a non-fixed window with double pointers.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of robots in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumRobots(
        self, chargeTimes: List[int], runningCosts: List[int], budget: int
    ) -> int:
        q = deque()
        ans = s = l = 0
        for r, (t, c) in enumerate(zip(chargeTimes, runningCosts)):
            s += c
            while q and chargeTimes[q[-1]] <= t:
                q.pop()
            q.append(r)
            while q and (r - l + 1) * s + chargeTimes[q[0]] > budget:
                if q[0] == l:
                    q.popleft()
                s -= runningCosts[l]
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        Deque<Integer> q = new ArrayDeque<>();
        int n = chargeTimes.length;
        int ans = 0;
        long s = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            s += runningCosts[r];
            while (!q.isEmpty() && chargeTimes[q.peekLast()] <= chargeTimes[r]) {
                q.pollLast();
            }
            q.offerLast(r);
            while (!q.isEmpty() && (r - l + 1) * s + chargeTimes[q.peekFirst()] > budget) {
                if (q.peekFirst() == l) {
                    q.pollFirst();
                }
                s -= runningCosts[l++];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumRobots(vector<int>& chargeTimes, vector<int>& runningCosts, long long budget) {
        deque<int> q;
        long long s = 0;
        int ans = 0;
        int n = chargeTimes.size();
        for (int l = 0, r = 0; r < n; ++r) {
            s += runningCosts[r];
            while (q.size() && chargeTimes[q.back()] <= chargeTimes[r]) {
                q.pop_back();
            }
            q.push_back(r);
            while (q.size() && (r - l + 1) * s + chargeTimes[q.front()] > budget) {
                if (q.front() == l) {
                    q.pop_front();
                }
                s -= runningCosts[l++];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumRobots(chargeTimes []int, runningCosts []int, budget int64) (ans int) {
	q := Deque{}
	s := int64(0)
	l := 0
	for r, t := range chargeTimes {
		s += int64(runningCosts[r])
		for !q.Empty() && chargeTimes[q.Back()] <= t {
			q.PopBack()
		}
		q.PushBack(r)
		for !q.Empty() && int64(r-l+1)*s+int64(chargeTimes[q.Front()]) > budget {
			if q.Front() == l {
				q.PopFront()
			}
			s -= int64(runningCosts[l])
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
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
function maximumRobots(chargeTimes: number[], runningCosts: number[], budget: number): number {
    const q = new Deque<number>();
    const n = chargeTimes.length;
    let [ans, s] = [0, 0];
    for (let l = 0, r = 0; r < n; ++r) {
        s += runningCosts[r];
        while (!q.isEmpty() && chargeTimes[q.backValue()!] <= chargeTimes[r]) {
            q.popBack();
        }
        q.pushBack(r);
        while (!q.isEmpty() && (r - l + 1) * s + chargeTimes[q.frontValue()!] > budget) {
            if (q.frontValue() === l) {
                q.popFront();
            }
            s -= runningCosts[l++];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
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
