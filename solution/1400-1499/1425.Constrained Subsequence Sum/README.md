---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1425.Constrained%20Subsequence%20Sum/README.md
rating: 2032
source: 第 186 场周赛 Q4
tags:
    - 队列
    - 数组
    - 动态规划
    - 滑动窗口
    - 单调队列
    - 堆（优先队列）
---

<!-- problem:start -->

# [1425. 带限制的子序列和](https://leetcode.cn/problems/constrained-subsequence-sum)

[English Version](/solution/1400-1499/1425.Constrained%20Subsequence%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回 <strong>非空</strong>&nbsp;子序列元素和的最大值，子序列需要满足：子序列中每两个 <strong>相邻</strong>&nbsp;的整数&nbsp;<code>nums[i]</code>&nbsp;和&nbsp;<code>nums[j]</code>&nbsp;，它们在原数组中的下标&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;满足&nbsp;<code>i &lt; j</code>&nbsp;且 <code>j - i &lt;= k</code> 。</p>

<p>数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [10,2,-10,5,20], k = 2
<strong>输出：</strong>37
<strong>解释：</strong>子序列为 [10, 2, 5, 20] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [-1,-2,-3], k = 1
<strong>输出：</strong>-1
<strong>解释：</strong>子序列必须是非空的，所以我们选择最大的数字。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [10,-2,-10,-5,20], k = 2
<strong>输出：</strong>23
<strong>解释：</strong>子序列为 [10, -2, -5, 20] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>-10^4&nbsp;&lt;= nums[i] &lt;= 10^4</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划 + 单调队列

我们定义 $f[i]$ 表示以 $\textit{nums}[i]$ 结尾的满足条件的子序列的最大和。初始时 $f[i] = 0$，答案为 $\max_{0 \leq i \lt n} f(i)$。

我们注意到题目需要我们维护滑动窗口的最大值，这就是一个典型的单调队列应用场景。我们可以使用单调队列来优化动态规划的转移。

我们维护一个从队首到队尾单调递减的单调队列 $q$，队列中存储的是下标 $i$，初始时，我们将一个哨兵 $0$ 加入队列中。

我们遍历 $i$ 从 $0$ 到 $n - 1$，对于每个 $i$，我们执行以下操作：

- 如果队首元素 $q[0]$ 满足 $i - q[0] > k$，说明队首元素已经不在滑动窗口内，我们需要从队首弹出队首元素；
- 然后，我们计算 $f[i] = \max(0, f[q[0]]) + \textit{nums}[i]$，表示我们将 $\textit{nums}[i]$ 加入滑动窗口后的最大子序列和；
- 接下来，我们更新答案 $\textit{ans} = \max(\textit{ans}, f[i])$；
- 最后，我们将 $i$ 加入队列尾部，并且保持队列的单调性，即如果 $f[q[\textit{back}]] \leq f[i]$，我们需要将队尾元素弹出，直到队列为空或者 $f[q[\textit{back}]] > f[i]$。

最终答案即为 $\textit{ans}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def constrainedSubsetSum(self, nums: List[int], k: int) -> int:
        q = deque([0])
        n = len(nums)
        f = [0] * n
        ans = -inf
        for i, x in enumerate(nums):
            while i - q[0] > k:
                q.popleft()
            f[i] = max(0, f[q[0]]) + x
            ans = max(ans, f[i])
            while q and f[q[-1]] <= f[i]:
                q.pop()
            q.append(i)
        return ans
```

#### Java

```java
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int n = nums.length;
        int[] f = new int[n];
        int ans = -(1 << 30);
        for (int i = 0; i < n; ++i) {
            while (i - q.peekFirst() > k) {
                q.pollFirst();
            }
            f[i] = Math.max(0, f[q.peekFirst()]) + nums[i];
            ans = Math.max(ans, f[i]);
            while (!q.isEmpty() && f[q.peekLast()] <= f[i]) {
                q.pollLast();
            }
            q.offerLast(i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int constrainedSubsetSum(vector<int>& nums, int k) {
        deque<int> q = {0};
        int n = nums.size();
        int f[n];
        f[0] = 0;
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            while (i - q.front() > k) {
                q.pop_front();
            }
            f[i] = max(0, f[q.front()]) + nums[i];
            ans = max(ans, f[i]);
            while (!q.empty() && f[q.back()] <= f[i]) {
                q.pop_back();
            }
            q.push_back(i);
        }
        return ans;
    }
};
```

#### Go

```go
func constrainedSubsetSum(nums []int, k int) int {
	q := Deque{}
	q.PushFront(0)
	n := len(nums)
	f := make([]int, n)
	ans := nums[0]
	for i, x := range nums {
		for i-q.Front() > k {
			q.PopFront()
		}
		f[i] = max(0, f[q.Front()]) + x
		ans = max(ans, f[i])
		for !q.Empty() && f[q.Back()] <= f[i] {
			q.PopBack()
		}
		q.PushBack(i)
	}
	return ans
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
function constrainedSubsetSum(nums: number[], k: number): number {
    const q = new Deque<number>();
    const n = nums.length;
    q.pushBack(0);
    let ans = nums[0];
    const f: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        while (i - q.frontValue()! > k) {
            q.popFront();
        }
        f[i] = Math.max(0, f[q.frontValue()!]!) + nums[i];
        ans = Math.max(ans, f[i]);
        while (!q.isEmpty() && f[q.backValue()!]! <= f[i]) {
            q.popBack();
        }
        q.pushBack(i);
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
