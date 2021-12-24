# [2059. Minimum Operations to Convert Number](https://leetcode.com/problems/minimum-operations-to-convert-number)

[中文文档](/solution/2000-2099/2059.Minimum%20Operations%20to%20Convert%20Number/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> containing <strong>distinct</strong> numbers, an integer <code>start</code>, and an integer <code>goal</code>. There is an integer <code>x</code>&nbsp;that is initially set to <code>start</code>, and you want to perform operations on <code>x</code> such that it is converted to <code>goal</code>. You can perform the following operation repeatedly on the number <code>x</code>:</p>

<p>If <code>0 &lt;= x &lt;= 1000</code>, then for any index <code>i</code> in the array (<code>0 &lt;= i &lt; nums.length</code>), you can set <code>x</code> to any of the following:</p>

<ul>
	<li><code>x + nums[i]</code></li>
	<li><code>x - nums[i]</code></li>
	<li><code>x ^ nums[i]</code> (bitwise-XOR)</li>
</ul>

<p>Note that you can use each <code>nums[i]</code> any number of times in any order. Operations that set <code>x</code> to be out of the range <code>0 &lt;= x &lt;= 1000</code> are valid, but no more operations can be done afterward.</p>

<p>Return <em>the <strong>minimum</strong> number of operations needed to convert </em><code>x = start</code><em> into </em><code>goal</code><em>, and </em><code>-1</code><em> if it is not possible</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3], start = 6, goal = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can go from 6 &rarr; 7 &rarr; 4 with the following 2 operations.
- 6 ^ 1 = 7
- 7 ^ 3 = 4
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,12], start = 2, goal = 12
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can go from 2 &rarr; 14 &rarr; 12 with the following 2 operations.
- 2 + 12 = 14
- 14 - 2 = 12
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,7], start = 0, goal = -4
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can go from 0 &rarr; 3 &rarr; -4 with the following 2 operations.
- 0 + 3 = 3
- 3 - 7 = -4
Note that the last operation sets x out of the range 0 &lt;= x &lt;= 1000, which is valid.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,8,16], start = 0, goal = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong>
There is no way to convert 0 into 1.</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], start = 0, goal = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong>
We can go from 0 &rarr; 1 &rarr; 2 &rarr; 3 with the following 3 operations.
- 0 + 1 = 1
- 1 + 1 = 2
- 2 + 1 = 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i], goal &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= start &lt;= 1000</code></li>
	<li><code>start != goal</code></li>
	<li>All the integers in <code>nums</code> are distinct.</li>
</ul>

## Solutions

BFS

<!-- tabs:start -->

### **Python3**

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
                    if nx >= 0 and nx <= 1000 and not vis[nx]:
                        q.append((nx, step + 1))
                        vis[nx] = True
        return -1
```

### **Java**

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

### **C++**

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums, int start, int goal) {
        using pii = pair<int, int>;
        vector<function<int(int, int)>> ops{
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

### **TypeScript**

```ts
function minimumOperations(
    nums: number[],
    start: number,
    goal: number
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
