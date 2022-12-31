# [2071. 你可以安排的最多任务数目](https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign)

[English Version](/solution/2000-2099/2071.Maximum%20Number%20of%20Tasks%20You%20Can%20Assign/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你&nbsp;<code>n</code>&nbsp;个任务和&nbsp;<code>m</code>&nbsp;个工人。每个任务需要一定的力量值才能完成，需要的力量值保存在下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>tasks</code>&nbsp;中，第 <code>i</code>&nbsp;个任务需要&nbsp;<code>tasks[i]</code>&nbsp;的力量才能完成。每个工人的力量值保存在下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>workers</code>&nbsp;中，第&nbsp;<code>j</code>&nbsp;个工人的力量值为&nbsp;<code>workers[j]</code>&nbsp;。每个工人只能完成 <strong>一个</strong>&nbsp;任务，且力量值需要 <strong>大于等于</strong>&nbsp;该任务的力量要求值（即&nbsp;<code>workers[j] &gt;= tasks[i]</code>&nbsp;）。</p>

<p>除此以外，你还有&nbsp;<code>pills</code>&nbsp;个神奇药丸，可以给 <strong>一个工人的力量值</strong>&nbsp;增加&nbsp;<code>strength</code>&nbsp;。你可以决定给哪些工人使用药丸，但每个工人&nbsp;<strong>最多</strong>&nbsp;只能使用&nbsp;<strong>一片</strong>&nbsp;药丸。</p>

<p>给你下标从 <strong>0</strong>&nbsp;开始的整数数组<code>tasks</code> 和&nbsp;<code>workers</code>&nbsp;以及两个整数&nbsp;<code>pills</code> 和&nbsp;<code>strength</code>&nbsp;，请你返回 <strong>最多</strong>&nbsp;有多少个任务可以被完成。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [<em><strong>3</strong></em>,<em><strong>2</strong></em>,<em><strong>1</strong></em>], workers = [<em><strong>0</strong></em>,<em><strong>3</strong></em>,<em><strong>3</strong></em>], pills = 1, strength = 1
<b>输出：</b>3
<strong>解释：</strong>
我们可以按照如下方案安排药丸：
- 给 0 号工人药丸。
- 0 号工人完成任务 2（0 + 1 &gt;= 1）
- 1 号工人完成任务 1（3 &gt;= 2）
- 2 号工人完成任务 0（3 &gt;= 3）
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [<em><strong>5</strong></em>,4], workers = [<em><strong>0</strong></em>,0,0], pills = 1, strength = 5
<b>输出：</b>1
<strong>解释：</strong>
我们可以按照如下方案安排药丸：
- 给 0 号工人药丸。
- 0 号工人完成任务 0（0 + 5 &gt;= 5）
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>tasks = [<em><strong>10</strong></em>,<em><strong>15</strong></em>,30], workers = [<em><strong>0</strong></em>,<em><strong>10</strong></em>,10,10,10], pills = 3, strength = 10
<b>输出：</b>2
<strong>解释：</strong>
我们可以按照如下方案安排药丸：
- 给 0 号和 1 号工人药丸。
- 0 号工人完成任务 0（0 + 10 &gt;= 10）
- 1 号工人完成任务 1（10 + 10 &gt;= 15）
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>tasks = [<em><strong>5</strong></em>,9,<em><strong>8</strong></em>,<em><strong>5</strong></em>,9], workers = [1,<em><strong>6</strong></em>,<em><strong>4</strong></em>,2,<em><strong>6</strong></em>], pills = 1, strength = 5
<b>输出：</b>3
<strong>解释：</strong>
我们可以按照如下方案安排药丸：
- 给 2 号工人药丸。
- 1 号工人完成任务 0（6 &gt;= 5）
- 2 号工人完成任务 2（4 + 5 &gt;= 8）
- 4 号工人完成任务 3（6 &gt;= 5）
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == tasks.length</code></li>
	<li><code>m == workers.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= pills &lt;= m</code></li>
	<li><code>0 &lt;= tasks[i], workers[j], strength &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 二分查找**

将任务按照完成时间从小到大排序，将工人按照能力从小到大排序。

假设我们要安排的任务数为 $x$，那么我们可以贪心地将前 $x$ 个任务分配给力量值最大的 $x$ 个工人。假设能完成任务数为 $x$，那么也一定能完成任务数为 $x-1$，$x-2$，$x-3$，…，$1$，$0$ 的情况。因此，我们可以使用二分查找的方法，找到最大的 $x$，使得能够完成任务数为 $x$ 的情况。

我们定义一个函数 $check(x)$，表示是否能够完成任务数为 $x$ 的情况。

函数 $check(x)$ 的实现如下：

从小到大遍历力量值最大的 $x$ 个工人，记当前遍历到的工人为 $j$，那么当前可选任务必须满足 $tasks[i] \leq workers[j] + strength$。

如果当前可选任务中要求力量值最小的一个 $task[i]$ 小于等于 $workers[j]$，那么第 $j$ 个工人不用吃药就可以完成任务 $task[i]$。否则，当前工人必须吃药，如果还有药丸，那么吃药，并且在当前可选任务中选择要求力量值最大的一个任务完成。否则，返回 `false`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为任务数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxTaskAssign(self, tasks: List[int], workers: List[int], pills: int, strength: int) -> int:
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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
