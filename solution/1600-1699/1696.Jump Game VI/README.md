---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1696.Jump%20Game%20VI/README.md
rating: 1954
tags:
    - 队列
    - 数组
    - 动态规划
    - 单调队列
    - 堆（优先队列）
---

# [1696. 跳跃游戏 VI](https://leetcode.cn/problems/jump-game-vi)

[English Version](/solution/1600-1699/1696.Jump%20Game%20VI/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>一开始你在下标 <code>0</code> 处。每一步，你最多可以往前跳 <code>k</code> 步，但你不能跳出数组的边界。也就是说，你可以从下标 <code>i</code> 跳到 <code>[i + 1， min(n - 1, i + k)]</code> <strong>包含</strong> 两个端点的任意位置。</p>

<p>你的目标是到达数组最后一个位置（下标为 <code>n - 1</code> ），你的 <strong>得分</strong> 为经过的所有数字之和。</p>

<p>请你返回你能得到的 <strong>最大得分</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [<strong>1</strong>,<strong>-1</strong>,-2,<strong>4</strong>,-7,<strong>3</strong>], k = 2
<b>输出：</b>7
<b>解释：</b>你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [<strong>10</strong>,-5,-2,<strong>4</strong>,0,<strong>3</strong>], k = 3
<b>输出：</b>17
<b>解释：</b>你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
<b>输出：</b>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li> <code>1 <= nums.length, k <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一：动态规划 + 单调队列优化

我们定义 $f[i]$ 表示到达下标 $i$ 的最大得分，那么 $f[i]$ 的值可以从 $f[j]$ 转移而来，其中 $j$ 满足 $i - k \leq j \leq i - 1$。因此我们可以使用动态规划求解。

状态转移方程为：

$$
f[i] = \max_{j \in [i - k, i - 1]} f[j] + nums[i]
$$

我们可以使用单调队列优化状态转移方程，具体做法是维护一个单调递减的队列，队列中存储的是下标 $j$，并且队列中的下标对应的 $f[j]$ 值是单调递减的。在进行状态转移时，我们只需要取出队首的下标 $j$，即可得到 $f[j]$ 的最大值，然后将 $f[i]$ 的值更新为 $f[j] + nums[i]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxResult(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [0] * n
        q = deque([0])
        for i in range(n):
            if i - q[0] > k:
                q.popleft()
            f[i] = nums[i] + f[q[0]]
            while q and f[q[-1]] <= f[i]:
                q.pop()
            q.append(i)
        return f[-1]
```

```java
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        for (int i = 0; i < n; ++i) {
            if (i - q.peekFirst() > k) {
                q.pollFirst();
            }
            f[i] = nums[i] + f[q.peekFirst()];
            while (!q.isEmpty() && f[q.peekLast()] <= f[i]) {
                q.pollLast();
            }
            q.offerLast(i);
        }
        return f[n - 1];
    }
}
```

```cpp
class Solution {
public:
    int maxResult(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n];
        f[0] = 0;
        deque<int> q = {0};
        for (int i = 0; i < n; ++i) {
            if (i - q.front() > k) {
                q.pop_front();
            }
            f[i] = nums[i] + f[q.front()];
            while (!q.empty() && f[i] >= f[q.back()]) {
                q.pop_back();
            }
            q.push_back(i);
        }
        return f[n - 1];
    }
};
```

```go
func maxResult(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	q := Deque{}
	q.PushBack(0)
	for i := 0; i < n; i++ {
		if i-q.Front() > k {
			q.PopFront()
		}
		f[i] = nums[i] + f[q.Front()]
		for !q.Empty() && f[i] >= f[q.Back()] {
			q.PopBack()
		}
		q.PushBack(i)
	}
	return f[n-1]
}

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
function maxResult(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    const q = new Deque<number>();
    q.pushBack(0);
    for (let i = 0; i < n; ++i) {
        if (i - q.frontValue()! > k) {
            q.popFront();
        }
        f[i] = nums[i] + f[q.frontValue()!];
        while (!q.isEmpty() && f[i] >= f[q.backValue()!]) {
            q.popBack();
        }
        q.pushBack(i);
    }
    return f[n - 1];
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
