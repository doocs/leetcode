# [1215. Stepping Numbers](https://leetcode.com/problems/stepping-numbers)

[中文文档](/solution/1200-1299/1215.Stepping%20Numbers/README.md)

<!-- tags:Breadth-First Search,Backtracking -->

## Description

<p>A <strong>stepping number</strong> is an integer such that all of its adjacent digits have an absolute difference of exactly <code>1</code>.</p>

<ul>
	<li>For example, <code>321</code> is a <strong>stepping number</strong> while <code>421</code> is not.</li>
</ul>

<p>Given two integers <code>low</code> and <code>high</code>, return <em>a sorted list of all the <strong>stepping numbers</strong> in the inclusive range</em> <code>[low, high]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> low = 0, high = 21
<strong>Output:</strong> [0,1,2,3,4,5,6,7,8,9,10,12,21]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> low = 10, high = 15
<strong>Output:</strong> [10,12]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= low &lt;= high &lt;= 2 * 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: BFS

First, if $low$ is $0$, we need to add $0$ to the answer.

Next, we create a queue $q$ and add $1 \sim 9$ to the queue. Then, we repeatedly take out elements from the queue. Let the current element be $v$. If $v$ is greater than $high$, we stop searching. If $v$ is in the range $[low, high]$, we add $v$ to the answer. Then, we need to record the last digit of $v$ as $x$. If $x \gt 0$, we add $v \times 10 + x - 1$ to the queue. If $x \lt 9$, we add $v \times 10 + x + 1$ to the queue. Repeat the above steps until the queue is empty.

The time complexity is $O(10 \times 2^{\log M})$, and the space complexity is $O(2^{\log M})$, where $M$ is the number of digits in $high$.

<!-- tabs:start -->

```python
class Solution:
    def countSteppingNumbers(self, low: int, high: int) -> List[int]:
        ans = []
        if low == 0:
            ans.append(0)
        q = deque(range(1, 10))
        while q:
            v = q.popleft()
            if v > high:
                break
            if v >= low:
                ans.append(v)
            x = v % 10
            if x:
                q.append(v * 10 + x - 1)
            if x < 9:
                q.append(v * 10 + x + 1)
        return ans
```

```java
class Solution {
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        if (low == 0) {
            ans.add(0);
        }
        Deque<Long> q = new ArrayDeque<>();
        for (long i = 1; i < 10; ++i) {
            q.offer(i);
        }
        while (!q.isEmpty()) {
            long v = q.pollFirst();
            if (v > high) {
                break;
            }
            if (v >= low) {
                ans.add((int) v);
            }
            int x = (int) v % 10;
            if (x > 0) {
                q.offer(v * 10 + x - 1);
            }
            if (x < 9) {
                q.offer(v * 10 + x + 1);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> countSteppingNumbers(int low, int high) {
        vector<int> ans;
        if (low == 0) {
            ans.push_back(0);
        }
        queue<long long> q;
        for (int i = 1; i < 10; ++i) {
            q.push(i);
        }
        while (!q.empty()) {
            long long v = q.front();
            q.pop();
            if (v > high) {
                break;
            }
            if (v >= low) {
                ans.push_back(v);
            }
            int x = v % 10;
            if (x > 0) {
                q.push(v * 10 + x - 1);
            }
            if (x < 9) {
                q.push(v * 10 + x + 1);
            }
        }
        return ans;
    }
};
```

```go
func countSteppingNumbers(low int, high int) []int {
	ans := []int{}
	if low == 0 {
		ans = append(ans, 0)
	}
	q := []int{1, 2, 3, 4, 5, 6, 7, 8, 9}
	for len(q) > 0 {
		v := q[0]
		q = q[1:]
		if v > high {
			break
		}
		if v >= low {
			ans = append(ans, v)
		}
		x := v % 10
		if x > 0 {
			q = append(q, v*10+x-1)
		}
		if x < 9 {
			q = append(q, v*10+x+1)
		}
	}
	return ans
}
```

```ts
function countSteppingNumbers(low: number, high: number): number[] {
    const ans: number[] = [];
    if (low === 0) {
        ans.push(0);
    }
    const q: number[] = [];
    for (let i = 1; i < 10; ++i) {
        q.push(i);
    }
    while (q.length) {
        const v = q.shift()!;
        if (v > high) {
            break;
        }
        if (v >= low) {
            ans.push(v);
        }
        const x = v % 10;
        if (x > 0) {
            q.push(v * 10 + x - 1);
        }
        if (x < 9) {
            q.push(v * 10 + x + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
