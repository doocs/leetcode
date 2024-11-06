---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2534.Time%20Taken%20to%20Cross%20the%20Door/README.md
tags:
    - 队列
    - 数组
    - 模拟
---

<!-- problem:start -->

# [2534. 通过门的时间 🔒](https://leetcode.cn/problems/time-taken-to-cross-the-door)

[English Version](/solution/2500-2599/2534.Time%20Taken%20to%20Cross%20the%20Door/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>n</code> 个人，按从 <code>0</code> 到 <code>n - 1</code> 编号。现在有一扇门，每个人只能通过门进入或离开一次，耗时一秒。</p>

<p>给你一个 <strong>非递减顺序</strong> 排列的整数数组 <code>arrival</code> ，数组长度为 <code>n</code> ，其中 <code>arrival[i]</code> 是第 <code>i</code> 个人到达门前的时间。另给你一个长度为 <code>n</code> 的数组 <code>state</code> ，其中 <code>state[i]</code> 是 <code>0</code> 则表示第 <code>i</code> 个人希望进入这扇门，是 <code>1</code> 则表示 TA 想要离开这扇门。</p>

<p>如果 <strong>同时</strong> 有两个或更多人想要使用这扇门，则必须遵循以下规则：</p>

<ul>
	<li>如果前一秒 <strong>没有</strong> 使用门，那么想要 <strong>离开</strong> 的人会先离开。</li>
	<li>如果前一秒使用门 <strong>进入</strong> ，那么想要 <strong>进入</strong> 的人会先进入。</li>
	<li>如果前一秒使用门 <strong>离开</strong> ，那么想要 <strong>离开</strong> 的人会先离开。</li>
	<li>如果多个人都想朝同一方向走（都进入或都离开），编号最小的人会先通过门。</li>
</ul>

<p>返回一个长度为 <code>n</code> 的数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer[i]</code><em> </em>是第 <code>i</code> 个人通过门的时刻（秒）。</p>
<strong>注意：</strong>

<ul>
	<li>每秒只有一个人可以通过门。</li>
	<li>为遵循上述规则，一个人可以在到达门附近后等待，而不通过门进入或离开。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arrival = [0,1,1,2,4], state = [0,1,0,0,1]
<strong>输出：</strong>[0,3,1,2,4]
<strong>解释：</strong>每秒发生的情况如下：
- t = 0 ：第 0 个人是唯一一个想要进入的人，所以 TA 可以直接进入。
- t = 1 ：第 1 个人想要离开，第 2 个人想要进入。因为前一秒有人使用门进入，所以第 2 个人先进入。
- t = 2 ：第 1 个人还是想要离开，第 3 个人想要进入。因为前一秒有人使用门进入，所以第 3 个人先进入。
- t = 3 ：第 1 个人是唯一一个想要离开的人，所以 TA 可以直接离开。
- t = 4 ：第 4 个人是唯一一个想要进入的人，所以 TA 可以直接离开。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arrival = [0,0,0], state = [1,0,1]
<strong>输出：</strong>[0,2,1]
<strong>解释：</strong>每秒发生的情况如下：
- t = 0 ：第 1 个人想要进入，但是第 0 个人和第 2 个人都想要离开。因为前一秒没有使用门，所以想要离开的人会先离开。又因为第 0 个人的编号更小，所以 TA 先离开。
- t = 1 ：第 1 个人想要进入，第 2 个人想要离开。因为前一秒有人使用门离开，所以第 2 个人先离开。
- t = 2 ：第 1 个人是唯一一个想要进入的人，所以 TA 可以直接进入。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == arrival.length == state.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arrival[i] &lt;= n</code></li>
	<li><code>arrival</code> 按 <strong>非递减顺序</strong> 排列</li>
	<li><code>state[i]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：队列 + 模拟

我们定义两个队列，其中 $q[0]$ 存放想要进入的人的编号，而 $q[1]$ 存放想要离开的人的编号。

我们维护一个时间 $t$，表示当前时间，一个状态 $st$，表示当前门的状态，当 $st = 1$ 表示门没使用或者上一秒有人离开，当 $st = 0$ 表示上一秒有人进入。初始时 $t = 0$，而 $st = 1$。

我们遍历数组 $\textit{arrival}$，对于每个人，如果当前时间 $t$ 小于等于该人到达门前的时间 $arrival[i]$，我们将该人的编号加入对应的队列 $q[\text{state}[i]]$ 中。

然后我们判断当前队列 $q[0]$ 和 $q[1]$ 是否都不为空，如果都不为空，我们将 $q[st]$ 队列的队首元素出队，并将当前时间 $t$ 赋值给该人的通过时间；如果只有一个队列不为空，我们根据哪个队列不为空，更新 $st$ 的值，然后将该队列的队首元素出队，并将当前时间 $t$ 赋值给该人的通过时间；如果两个队列都为空，我们将 $st$ 的值更新为 $1$，表示门没使用。

接下来，我们将时间 $t$ 自增 $1$，继续遍历数组 $\textit{arrival}$，直到所有人都通过门。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 表示数组 $\textit{arrival}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def timeTaken(self, arrival: List[int], state: List[int]) -> List[int]:
        q = [deque(), deque()]
        n = len(arrival)
        t = i = 0
        st = 1
        ans = [0] * n
        while i < n or q[0] or q[1]:
            while i < n and arrival[i] <= t:
                q[state[i]].append(i)
                i += 1
            if q[0] and q[1]:
                ans[q[st].popleft()] = t
            elif q[0] or q[1]:
                st = 0 if q[0] else 1
                ans[q[st].popleft()] = t
            else:
                st = 1
            t += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] timeTaken(int[] arrival, int[] state) {
        Deque<Integer>[] q = new Deque[2];
        Arrays.setAll(q, i -> new ArrayDeque<>());
        int n = arrival.length;
        int t = 0, i = 0, st = 1;
        int[] ans = new int[n];
        while (i < n || !q[0].isEmpty() || !q[1].isEmpty()) {
            while (i < n && arrival[i] <= t) {
                q[state[i]].add(i++);
            }
            if (!q[0].isEmpty() && !q[1].isEmpty()) {
                ans[q[st].poll()] = t;
            } else if (!q[0].isEmpty() || !q[1].isEmpty()) {
                st = q[0].isEmpty() ? 1 : 0;
                ans[q[st].poll()] = t;
            } else {
                st = 1;
            }
            ++t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> timeTaken(vector<int>& arrival, vector<int>& state) {
        int n = arrival.size();
        queue<int> q[2];
        int t = 0, i = 0, st = 1;
        vector<int> ans(n);

        while (i < n || !q[0].empty() || !q[1].empty()) {
            while (i < n && arrival[i] <= t) {
                q[state[i]].push(i++);
            }

            if (!q[0].empty() && !q[1].empty()) {
                ans[q[st].front()] = t;
                q[st].pop();
            } else if (!q[0].empty() || !q[1].empty()) {
                st = q[0].empty() ? 1 : 0;
                ans[q[st].front()] = t;
                q[st].pop();
            } else {
                st = 1;
            }

            ++t;
        }

        return ans;
    }
};
```

#### Go

```go
func timeTaken(arrival []int, state []int) []int {
	n := len(arrival)
	q := [2][]int{}
	t, i, st := 0, 0, 1
	ans := make([]int, n)

	for i < n || len(q[0]) > 0 || len(q[1]) > 0 {
		for i < n && arrival[i] <= t {
			q[state[i]] = append(q[state[i]], i)
			i++
		}

		if len(q[0]) > 0 && len(q[1]) > 0 {
			ans[q[st][0]] = t
			q[st] = q[st][1:]
		} else if len(q[0]) > 0 || len(q[1]) > 0 {
			if len(q[0]) == 0 {
				st = 1
			} else {
				st = 0
			}
			ans[q[st][0]] = t
			q[st] = q[st][1:]
		} else {
			st = 1
		}

		t++
	}

	return ans
}
```

#### TypeScript

```ts
function timeTaken(arrival: number[], state: number[]): number[] {
    const n = arrival.length;
    const q: number[][] = [[], []];
    let [t, i, st] = [0, 0, 1];
    const ans: number[] = Array(n).fill(0);

    while (i < n || q[0].length || q[1].length) {
        while (i < n && arrival[i] <= t) {
            q[state[i]].push(i++);
        }

        if (q[0].length && q[1].length) {
            ans[q[st][0]] = t;
            q[st].shift();
        } else if (q[0].length || q[1].length) {
            st = q[0].length ? 0 : 1;
            ans[q[st][0]] = t;
            q[st].shift();
        } else {
            st = 1;
        }

        t++;
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn time_taken(arrival: Vec<i32>, state: Vec<i32>) -> Vec<i32> {
        let n = arrival.len();
        let mut q = vec![VecDeque::new(), VecDeque::new()];
        let mut t = 0;
        let mut i = 0;
        let mut st = 1;
        let mut ans = vec![-1; n];

        while i < n || !q[0].is_empty() || !q[1].is_empty() {
            while i < n && arrival[i] <= t {
                q[state[i] as usize].push_back(i);
                i += 1;
            }

            if !q[0].is_empty() && !q[1].is_empty() {
                ans[*q[st].front().unwrap()] = t;
                q[st].pop_front();
            } else if !q[0].is_empty() || !q[1].is_empty() {
                st = if q[0].is_empty() { 1 } else { 0 };
                ans[*q[st].front().unwrap()] = t;
                q[st].pop_front();
            } else {
                st = 1;
            }

            t += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
