---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2398.Maximum%20Number%20of%20Robots%20Within%20Budget/README.md
rating: 1917
source: 第 86 场双周赛 Q4
tags:
    - 队列
    - 数组
    - 二分查找
    - 前缀和
    - 滑动窗口
    - 单调队列
    - 堆（优先队列）
---

<!-- problem:start -->

# [2398. 预算内的最多机器人数目](https://leetcode.cn/problems/maximum-number-of-robots-within-budget)

[English Version](/solution/2300-2399/2398.Maximum%20Number%20of%20Robots%20Within%20Budget/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有&nbsp;<code>n</code>&nbsp;个机器人，给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>chargeTimes</code> 和&nbsp;<code>runningCosts</code>&nbsp;，两者长度都为&nbsp;<code>n</code>&nbsp;。第&nbsp;<code>i</code>&nbsp;个机器人充电时间为&nbsp;<code>chargeTimes[i]</code>&nbsp;单位时间，花费&nbsp;<code>runningCosts[i]</code>&nbsp;单位时间运行。再给你一个整数&nbsp;<code>budget</code>&nbsp;。</p>

<p>运行&nbsp;<code>k</code>&nbsp;个机器人 <strong>总开销</strong>&nbsp;是&nbsp;<code>max(chargeTimes) + k * sum(runningCosts)</code>&nbsp;，其中&nbsp;<code>max(chargeTimes)</code>&nbsp;是这&nbsp;<code>k</code>&nbsp;个机器人中最大充电时间，<code>sum(runningCosts)</code>&nbsp;是这 <code>k</code>&nbsp;个机器人的运行时间之和。</p>

<p>请你返回在 <strong>不超过</strong>&nbsp;<code>budget</code>&nbsp;的前提下，你 <strong>最多</strong>&nbsp;可以 <strong>连续</strong>&nbsp;运行的机器人数目为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
<b>输出：</b>3
<b>解释：</b>
可以在 budget 以内运行所有单个机器人或者连续运行 2 个机器人。
选择前 3 个机器人，可以得到答案最大值 3 。总开销是 max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 ，小于 25 。
可以看出无法在 budget 以内连续运行超过 3 个机器人，所以我们返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
<b>输出：</b>0
<b>解释：</b>即使运行任何一个单个机器人，还是会超出 budget，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>chargeTimes.length == runningCosts.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= chargeTimes[i], runningCosts[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针 + 单调队列

问题实际上是求滑动窗口内的最大值，可以用单调队列来求解。

我们只需要二分枚举窗口 $k$ 的大小，找到一个最大的 $k$，使得满足题目要求。

实现过程中，实际上不需要进行二分枚举，只需要将固定窗口改为双指针非固定窗口即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是题目中机器人的数目。

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
