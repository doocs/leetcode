# [2059. 转化数字的最小运算数](https://leetcode.cn/problems/minimum-operations-to-convert-number)

[English Version](/solution/2000-2099/2059.Minimum%20Operations%20to%20Convert%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，该数组由 <strong>互不相同</strong> 的数字组成。另给你两个整数 <code>start</code> 和 <code>goal</code> 。</p>

<p>整数 <code>x</code> 的值最开始设为 <code>start</code> ，你打算执行一些运算使 <code>x</code> 转化为 <code>goal</code> 。你可以对数字 <code>x</code> 重复执行下述运算：</p>

<p>如果 <code>0 &lt;= x &lt;= 1000</code> ，那么，对于数组中的任一下标 <code>i</code>（<code>0 &lt;= i &lt; nums.length</code>），可以将 <code>x</code> 设为下述任一值：</p>

<ul>
	<li><code>x + nums[i]</code></li>
	<li><code>x - nums[i]</code></li>
	<li><code>x ^ nums[i]</code>（按位异或 XOR）</li>
</ul>

<p>注意，你可以按任意顺序使用每个 <code>nums[i]</code> 任意次。使 <code>x</code> 越过 <code>0 &lt;= x &lt;= 1000</code> 范围的运算同样可以生效，但该该运算执行后将不能执行其他运算。</p>

<p>返回将 <code>x = start</code><em> </em>转化为<em> </em><code>goal</code><em> </em>的最小操作数；如果无法完成转化，则返回<em> </em><code>-1</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,12], start = 2, goal = 12
<strong>输出：</strong>2
<strong>解释：</strong>
可以按 2 → 14 → 12 的转化路径进行，只需执行下述 2 次运算：
- 2 + 12 = 14
- 14 - 2 = 12
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,5,7], start = 0, goal = -4
<strong>输出：</strong>2
<strong>解释：</strong>
可以按 0 → 3 → -4 的转化路径进行，只需执行下述 2 次运算：
- 0 + 3 = 3
- 3 - 7 = -4
注意，最后一步运算使 x 超过范围 0 &lt;= x &lt;= 1000 ，但该运算仍然可以生效。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,8,16], start = 0, goal = 1
<strong>输出：</strong>-1
<strong>解释：</strong>
无法将 0 转化为 1</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i], goal &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= start &lt;= 1000</code></li>
	<li><code>start != goal</code></li>
	<li><code>nums</code> 中的所有整数互不相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS 最小步数模型。本题搜索空间不大，可以直接使用朴素 BFS，以下题解中还提供了双向 BFS 的题解代码，仅供参考。

双向 BFS 是 BFS 常见的一个优化方法，主要实现思路如下：

1. 创建两个队列 q1, q2 分别用于“起点 -> 终点”、“终点 -> 起点”两个方向的搜索；
2. 创建两个哈希表 m1, m2 分别记录访问过的节点以及对应的扩展次数（步数）；
3. 每次搜索时，优先选择元素数量较少的队列进行搜索扩展，如果在扩展过程中，搜索到另一个方向已经访问过的节点，说明找到了最短路径；
4. 只要其中一个队列为空，说明当前方向的搜索已经进行不下去了，说明起点到终点不连通，无需继续搜索。

```python
while q1 and q2:
    if len(q1) <= len(q2):
        # 优先选择较少元素的队列进行扩展
        extend(m1, m2, q1)
    else:
        extend(m2, m1, q2)


def extend(m1, m2, q):
    # 新一轮扩展
    for _ in range(len(q)):
        p = q.popleft()
        step = m1[p]
        for t in next(p):
            if t in m1:
                # 此前已经访问过
                continue
            if t in m2:
                # 另一个方向已经搜索过，说明找到了一条最短的连通路径
                return step + 1 + m2[t]
            q.append(t)
            m1[t] = step + 1
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumOperations(self, nums: List[int], start: int, goal: int) -> int:
        op1 = lambda x, y: x + y
        op2 = lambda x, y: x - y
        op3 = lambda x, y: x ^ y
        ops = [op1, op2, op3]
        vis = [False] * 1001
        q = deque([(start, 0)])
        while q:
            x, step = q.popleft()
            for num in nums:
                for op in ops:
                    nx = op(x, num)
                    if nx == goal:
                        return step + 1
                    if 0 <= nx <= 1000 and not vis[nx]:
                        q.append((nx, step + 1))
                        vis[nx] = True
        return -1
```

```python
class Solution:
    def minimumOperations(self, nums: List[int], start: int, goal: int) -> int:
        def next(x):
            res = []
            for num in nums:
                res.append(x + num)
                res.append(x - num)
                res.append(x ^ num)
            return res

        q = deque([start])
        vis = {start}
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                x = q.popleft()
                for y in next(x):
                    if y == goal:
                        return ans
                    if 0 <= y <= 1000 and y not in vis:
                        vis.add(y)
                        q.append(y)
        return -1
```

双向 BFS：

```python
class Solution:
    def minimumOperations(self, nums: List[int], start: int, goal: int) -> int:
        def next(x):
            res = []
            for num in nums:
                res.append(x + num)
                res.append(x - num)
                res.append(x ^ num)
            return res

        def extend(m1, m2, q):
            for _ in range(len(q)):
                x = q.popleft()
                step = m1[x]
                for y in next(x):
                    if y in m1:
                        continue
                    if y in m2:
                        return step + 1 + m2[y]
                    if 0 <= y <= 1000:
                        m1[y] = step + 1
                        q.append(y)
            return -1

        m1, m2 = {start: 0}, {goal: 0}
        q1, q2 = deque([start]), deque([goal])
        while q1 and q2:
            t = extend(m1, m2, q1) if len(q1) <= len(q2) else extend(m2, m1, q2)
            if t != -1:
                return t
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumOperations(int[] nums, int start, int goal) {
        IntBinaryOperator op1 = (x, y) -> x + y;
        IntBinaryOperator op2 = (x, y) -> x - y;
        IntBinaryOperator op3 = (x, y) -> x ^ y;
        IntBinaryOperator[] ops = { op1, op2, op3 };
        boolean[] vis = new boolean[1001];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { start, 0 });
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0], step = p[1];
            for (int num : nums) {
                for (IntBinaryOperator op : ops) {
                    int nx = op.applyAsInt(x, num);
                    if (nx == goal) {
                        return step + 1;
                    }
                    if (nx >= 0 && nx <= 1000 && !vis[nx]) {
                        queue.offer(new int[] { nx, step + 1 });
                        vis[nx] = true;
                    }
                }
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    public int minimumOperations(int[] nums, int start, int goal) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        boolean[] vis = new boolean[1001];
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int n = q.size(); n > 0; --n) {
                int x = q.poll();
                for (int y : next(nums, x)) {
                    if (y == goal) {
                        return ans;
                    }
                    if (y >= 0 && y <= 1000 && !vis[y]) {
                        vis[y] = true;
                        q.offer(y);
                    }
                }
            }
        }
        return -1;
    }

    private List<Integer> next(int[] nums, int x) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(x + num);
            res.add(x - num);
            res.add(x ^ num);
        }
        return res;
    }
}
```

双向 BFS：

```java
class Solution {
    private int[] nums;

    public int minimumOperations(int[] nums, int start, int goal) {
        this.nums = nums;
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        m1.put(start, 0);
        m2.put(goal, 0);
        q1.offer(start);
        q2.offer(goal);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    private int extend(Map<Integer, Integer> m1, Map<Integer, Integer> m2, Deque<Integer> q) {
        for (int i = q.size(); i > 0; --i) {
            int x = q.poll();
            int step = m1.get(x);
            for (int y : next(x)) {
                if (m1.containsKey(y)) {
                    continue;
                }
                if (m2.containsKey(y)) {
                    return step + 1 + m2.get(y);
                }
                if (y >= 0 && y <= 1000) {
                    m1.put(y, step + 1);
                    q.offer(y);
                }
            }
        }
        return -1;
    }

    private List<Integer> next(int x) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(x + num);
            res.add(x - num);
            res.add(x ^ num);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums, int start, int goal) {
        using pii = pair<int, int>;
        vector<function<int(int, int)>> ops {
            [](int x, int y) { return x + y; },
            [](int x, int y) { return x - y; },
            [](int x, int y) { return x ^ y; },
        };
        vector<bool> vis(1001, false);
        queue<pii> q;
        q.push({start, 0});
        while (!q.empty()) {
            auto [x, step] = q.front();
            q.pop();
            for (int num : nums) {
                for (auto op : ops) {
                    int nx = op(x, num);
                    if (nx == goal) {
                        return step + 1;
                    }
                    if (nx >= 0 && nx <= 1000 && !vis[nx]) {
                        q.push({nx, step + 1});
                        vis[nx] = true;
                    }
                }
            }
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums, int start, int goal) {
        queue<int> q{{start}};
        vector<bool> vis(1001);
        int ans = 0;
        while (!q.empty())
        {
            ++ans;
            for (int n = q.size(); n > 0; --n)
            {
                int x = q.front();
                q.pop();
                for (int y : next(nums, x))
                {
                    if (y == goal) return ans;
                    if (y >= 0 && y <= 1000 && !vis[y])
                    {
                        vis[y] = true;
                        q.push(y);
                    }
                }
            }
        }
        return -1;
    }

    vector<int> next(vector<int>& nums, int x) {
        vector<int> res;
        for (int num : nums)
        {
            res.push_back(x + num);
            res.push_back(x - num);
            res.push_back(x ^ num);
        }
        return res;
    }
};
```

双向 BFS：

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums, int start, int goal) {
        unordered_map<int, int> m1;
        unordered_map<int, int> m2;
        m1[start] = 0;
        m2[goal] = 0;
        queue<int> q1{{start}};
        queue<int> q2{{goal}};
        while (!q1.empty() && !q2.empty())
        {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1, nums) : extend(m2, m1, q2, nums);
            if (t != -1) return t;
        }
        return -1;
    }

    int extend(unordered_map<int, int>& m1, unordered_map<int, int>& m2, queue<int>& q, vector<int>& nums) {
        for (int i = q.size(); i > 0; --i)
        {
            int x = q.front();
            int step = m1[x];
            q.pop();
            for (int y : next(nums, x))
            {
                if (m1.count(y)) continue;
                if (m2.count(y)) return step + 1 + m2[y];
                if (y >= 0 && y <= 1000)
                {
                    m1[y] = step + 1;
                    q.push(y);
                }
            }
        }
        return -1;
    }

    vector<int> next(vector<int>& nums, int x) {
        vector<int> res;
        for (int num : nums)
        {
            res.push_back(x + num);
            res.push_back(x - num);
            res.push_back(x ^ num);
        }
        return res;
    }
};
```

### **Go**

```go
func minimumOperations(nums []int, start int, goal int) int {
	type pair struct {
		x    int
		step int
	}

	ops := []func(int, int) int{
		func(x, y int) int { return x + y },
		func(x, y int) int { return x - y },
		func(x, y int) int { return x ^ y },
	}
	vis := make([]bool, 1001)
	q := []pair{{start, 0}}

	for len(q) > 0 {
		x, step := q[0].x, q[0].step
		q = q[1:]
		for _, num := range nums {
			for _, op := range ops {
				nx := op(x, num)
				if nx == goal {
					return step + 1
				}
				if nx >= 0 && nx <= 1000 && !vis[nx] {
					q = append(q, pair{nx, step + 1})
					vis[nx] = true
				}
			}
		}
	}
	return -1
}
```

```go
func minimumOperations(nums []int, start int, goal int) int {
	next := func(x int) []int {
		var res []int
		for _, num := range nums {
			res = append(res, []int{x + num, x - num, x ^ num}...)
		}
		return res
	}
	q := []int{start}
	vis := make([]bool, 1001)
	ans := 0
	for len(q) > 0 {
		ans++
		for n := len(q); n > 0; n-- {
			x := q[0]
			q = q[1:]
			for _, y := range next(x) {
				if y == goal {
					return ans
				}
				if y >= 0 && y <= 1000 && !vis[y] {
					vis[y] = true
					q = append(q, y)
				}
			}
		}
	}
	return -1
}
```

双向 BFS：

```go
func minimumOperations(nums []int, start int, goal int) int {
	next := func(x int) []int {
		var res []int
		for _, num := range nums {
			res = append(res, []int{x + num, x - num, x ^ num}...)
		}
		return res
	}
	m1, m2 := map[int]int{start: 0}, map[int]int{goal: 0}
	q1, q2 := []int{start}, []int{goal}
	extend := func() int {
		for i := len(q1); i > 0; i-- {
			x := q1[0]
			q1 = q1[1:]
			step, _ := m1[x]
			for _, y := range next(x) {
				if _, ok := m1[y]; ok {
					continue
				}
				if v, ok := m2[y]; ok {
					return step + 1 + v
				}
				if y >= 0 && y <= 1000 {
					m1[y] = step + 1
					q1 = append(q1, y)
				}
			}
		}
		return -1
	}
	for len(q1) > 0 && len(q2) > 0 {
		if len(q1) > len(q2) {
			m1, m2 = m2, m1
			q1, q2 = q2, q1
		}
		t := extend()
		if t != -1 {
			return t
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function minimumOperations(
    nums: number[],
    start: number,
    goal: number,
): number {
    const n = nums.length;
    const op1 = function (x: number, y: number): number {
        return x + y;
    };
    const op2 = function (x: number, y: number): number {
        return x - y;
    };
    const op3 = function (x: number, y: number): number {
        return x ^ y;
    };
    const ops = [op1, op2, op3];
    let vis = new Array(1001).fill(false);
    let quenue: Array<Array<number>> = [[start, 0]];
    vis[start] = true;
    while (quenue.length) {
        let [x, step] = quenue.shift();
        for (let i = 0; i < n; i++) {
            for (let j = 0; j < ops.length; j++) {
                const nx = ops[j](x, nums[i]);
                if (nx == goal) {
                    return step + 1;
                }
                if (nx >= 0 && nx <= 1000 && !vis[nx]) {
                    vis[nx] = true;
                    quenue.push([nx, step + 1]);
                }
            }
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
