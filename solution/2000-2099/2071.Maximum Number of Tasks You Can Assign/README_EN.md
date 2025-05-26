---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2071.Maximum%20Number%20of%20Tasks%20You%20Can%20Assign/README_EN.md
rating: 2648
source: Biweekly Contest 65 Q4
tags:
    - Greedy
    - Queue
    - Array
    - Two Pointers
    - Binary Search
    - Sorting
    - Monotonic Queue
---

<!-- problem:start -->

# [2071. Maximum Number of Tasks You Can Assign](https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign)

[中文文档](/solution/2000-2099/2071.Maximum%20Number%20of%20Tasks%20You%20Can%20Assign/README.md)

## Description

<!-- description:start -->

<p>You have <code>n</code> tasks and <code>m</code> workers. Each task has a strength requirement stored in a <strong>0-indexed</strong> integer array <code>tasks</code>, with the <code>i<sup>th</sup></code> task requiring <code>tasks[i]</code> strength to complete. The strength of each worker is stored in a <strong>0-indexed</strong> integer array <code>workers</code>, with the <code>j<sup>th</sup></code> worker having <code>workers[j]</code> strength. Each worker can only be assigned to a <strong>single</strong> task and must have a strength <strong>greater than or equal</strong> to the task&#39;s strength requirement (i.e., <code>workers[j] &gt;= tasks[i]</code>).</p>

<p>Additionally, you have <code>pills</code> magical pills that will <strong>increase a worker&#39;s strength</strong> by <code>strength</code>. You can decide which workers receive the magical pills, however, you may only give each worker <strong>at most one</strong> magical pill.</p>

<p>Given the <strong>0-indexed </strong>integer arrays <code>tasks</code> and <code>workers</code> and the integers <code>pills</code> and <code>strength</code>, return <em>the <strong>maximum</strong> number of tasks that can be completed.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [<u><strong>3</strong></u>,<u><strong>2</strong></u>,<u><strong>1</strong></u>], workers = [<u><strong>0</strong></u>,<u><strong>3</strong></u>,<u><strong>3</strong></u>], pills = 1, strength = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong>
We can assign the magical pill and tasks as follows:
- Give the magical pill to worker 0.
- Assign worker 0 to task 2 (0 + 1 &gt;= 1)
- Assign worker 1 to task 1 (3 &gt;= 2)
- Assign worker 2 to task 0 (3 &gt;= 3)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [<u><strong>5</strong></u>,4], workers = [<u><strong>0</strong></u>,0,0], pills = 1, strength = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can assign the magical pill and tasks as follows:
- Give the magical pill to worker 0.
- Assign worker 0 to task 0 (0 + 5 &gt;= 5)
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> tasks = [<u><strong>10</strong></u>,<u><strong>15</strong></u>,30], workers = [<u><strong>0</strong></u>,<u><strong>10</strong></u>,10,10,10], pills = 3, strength = 10
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can assign the magical pills and tasks as follows:
- Give the magical pill to worker 0 and worker 1.
- Assign worker 0 to task 0 (0 + 10 &gt;= 10)
- Assign worker 1 to task 1 (10 + 10 &gt;= 15)
The last pill is not given because it will not make any worker strong enough for the last task.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == tasks.length</code></li>
	<li><code>m == workers.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= pills &lt;= m</code></li>
	<li><code>0 &lt;= tasks[i], workers[j], strength &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Binary Search

Sort the tasks in ascending order of completion time and the workers in ascending order of ability.

Suppose the number of tasks we want to assign is $x$. We can greedily assign the first $x$ tasks to the $x$ workers with the highest strength. If it is possible to complete $x$ tasks, then it is also possible to complete $x-1$, $x-2$, $x-3$, ..., $1$, $0$ tasks. Therefore, we can use binary search to find the maximum $x$ such that it is possible to complete $x$ tasks.

We define a function $check(x)$ to determine whether it is possible to complete $x$ tasks.

The implementation of $check(x)$ is as follows:

Iterate through the $x$ workers with the highest strength in ascending order. Let the current worker being processed be $j$. The current available tasks must satisfy $tasks[i] \leq workers[j] + strength$.

If the smallest required strength task $task[i]$ among the current available tasks is less than or equal to $workers[j]$, then worker $j$ can complete task $task[i]$ without using a pill. Otherwise, the current worker must use a pill. If there are pills remaining, use one pill and complete the task with the highest required strength among the current available tasks. Otherwise, return `false`.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the number of tasks.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTaskAssign(
        self, tasks: List[int], workers: List[int], pills: int, strength: int
    ) -> int:
        def check(x):
            i = 0
            q = deque()
            p = pills
            for j in range(m - x, m):
                while i < x and tasks[i] <= workers[j] + strength:
                    q.append(tasks[i])
                    i += 1
                if not q:
                    return False
                if q[0] <= workers[j]:
                    q.popleft()
                elif p == 0:
                    return False
                else:
                    p -= 1
                    q.pop()
            return True

        n, m = len(tasks), len(workers)
        tasks.sort()
        workers.sort()
        left, right = 0, min(n, m)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

#### Java

```java
class Solution {
    private int[] tasks;
    private int[] workers;
    private int strength;
    private int pills;
    private int m;
    private int n;

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        this.tasks = tasks;
        this.workers = workers;
        this.strength = strength;
        this.pills = pills;
        n = tasks.length;
        m = workers.length;
        int left = 0, right = Math.min(m, n);
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int x) {
        int i = 0;
        Deque<Integer> q = new ArrayDeque<>();
        int p = pills;
        for (int j = m - x; j < m; ++j) {
            while (i < x && tasks[i] <= workers[j] + strength) {
                q.offer(tasks[i++]);
            }
            if (q.isEmpty()) {
                return false;
            }
            if (q.peekFirst() <= workers[j]) {
                q.pollFirst();
            } else if (p == 0) {
                return false;
            } else {
                --p;
                q.pollLast();
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxTaskAssign(vector<int>& tasks, vector<int>& workers, int pills, int strength) {
        sort(tasks.begin(), tasks.end());
        sort(workers.begin(), workers.end());
        int n = tasks.size(), m = workers.size();
        int left = 0, right = min(m, n);
        auto check = [&](int x) {
            int p = pills;
            deque<int> q;
            int i = 0;
            for (int j = m - x; j < m; ++j) {
                while (i < x && tasks[i] <= workers[j] + strength) {
                    q.push_back(tasks[i++]);
                }
                if (q.empty()) {
                    return false;
                }
                if (q.front() <= workers[j]) {
                    q.pop_front();
                } else if (p == 0) {
                    return false;
                } else {
                    --p;
                    q.pop_back();
                }
            }
            return true;
        };
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
func maxTaskAssign(tasks []int, workers []int, pills int, strength int) int {
	sort.Ints(tasks)
	sort.Ints(workers)
	n, m := len(tasks), len(workers)
	left, right := 0, min(m, n)
	check := func(x int) bool {
		p := pills
		q := []int{}
		i := 0
		for j := m - x; j < m; j++ {
			for i < x && tasks[i] <= workers[j]+strength {
				q = append(q, tasks[i])
				i++
			}
			if len(q) == 0 {
				return false
			}
			if q[0] <= workers[j] {
				q = q[1:]
			} else if p == 0 {
				return false
			} else {
				p--
				q = q[:len(q)-1]
			}
		}
		return true
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

#### TypeScript

```ts
function maxTaskAssign(
    tasks: number[],
    workers: number[],
    pills: number,
    strength: number,
): number {
    tasks.sort((a, b) => a - b);
    workers.sort((a, b) => a - b);

    const n = tasks.length;
    const m = workers.length;

    const check = (x: number): boolean => {
        const dq = new Array<number>(x);
        let head = 0;
        let tail = 0;
        const empty = () => head === tail;
        const pushBack = (val: number) => {
            dq[tail++] = val;
        };
        const popFront = () => {
            head++;
        };
        const popBack = () => {
            tail--;
        };
        const front = () => dq[head];

        let i = 0;
        let p = pills;

        for (let j = m - x; j < m; j++) {
            while (i < x && tasks[i] <= workers[j] + strength) {
                pushBack(tasks[i]);
                i++;
            }

            if (empty()) return false;

            if (front() <= workers[j]) {
                popFront();
            } else {
                if (p === 0) return false;
                p--;
                popBack();
            }
        }
        return true;
    };

    let [left, right] = [0, Math.min(n, m)];
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) left = mid;
        else right = mid - 1;
    }
    return left;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
