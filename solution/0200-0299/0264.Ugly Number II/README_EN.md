# [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii)

[中文文档](/solution/0200-0299/0264.Ugly%20Number%20II/README.md)

## Description

<p>An <strong>ugly number</strong> is a positive integer whose prime factors are limited to <code>2</code>, <code>3</code>, and <code>5</code>.</p>

<p>Given an integer <code>n</code>, return <em>the</em> <code>n<sup>th</sup></code> <em><strong>ugly number</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 12
<strong>Explanation:</strong> [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1690</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        h = [1]
        vis = {1}
        ans = 1
        for _ in range(n):
            ans = heappop(h)
            for v in [2, 3, 5]:
                nxt = ans * v
                if nxt not in vis:
                    vis.add(nxt)
                    heappush(h, nxt)
        return ans
```

```python
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        dp = [1] * n
        p2 = p3 = p5 = 0
        for i in range(1, n):
            next2, next3, next5 = dp[p2] * 2, dp[p3] * 3, dp[p5] * 5
            dp[i] = min(next2, next3, next5)
            if dp[i] == next2:
                p2 += 1
            if dp[i] == next3:
                p3 += 1
            if dp[i] == next5:
                p5 += 1
        return dp[-1]
```

### **Java**

```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) ++p2;
            if (dp[i] == next3) ++p3;
            if (dp[i] == next5) ++p5;
        }
        return dp[n - 1];
    }
}
```

```java
class Solution {
    public int nthUglyNumber(int n) {
        Set<Long> vis = new HashSet<>();
        PriorityQueue<Long> q = new PriorityQueue<>();
        int[] f = new int[]{2, 3, 5};
        q.offer(1L);
        vis.add(1L);
        long ans = 0;
        while (n-- > 0) {
            ans = q.poll();
            for (int v : f) {
                long next = ans * v;
                if (vis.add(next)) {
                    q.offer(next);
                }
            }
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int nthUglyNumber(int n) {
        vector<int> dp(n);
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = min(next2, min(next3, next5));
            if (dp[i] == next2) ++p2;
            if (dp[i] == next3) ++p3;
            if (dp[i] == next5) ++p5;
        }
        return dp[n - 1];
    }
};
```

```cpp
class Solution {
public:
    int nthUglyNumber(int n) {
        priority_queue<long, vector<long>, greater<long>> q;
        q.push(1l);
        unordered_set<long> vis{{1l}};
        long ans = 1;
        vector<int> f = {2, 3, 5};
        while (n--)
        {
            ans = q.top();
            q.pop();
            for (int& v : f)
            {
                long nxt = ans * v;
                if (!vis.count(nxt))
                {
                    vis.insert(nxt);
                    q.push(nxt);
                }
            }
        }
        return (int) ans;
    }
};
```

### **Go**

```go
func nthUglyNumber(n int) int {
    dp := make([]int, n)
    dp[0] = 1
    p2, p3, p5 := 0, 0, 0
    for i := 1; i < n; i++ {
        next2, next3, next5 := dp[p2]*2, dp[p3]*3, dp[p5]*5
        dp[i] = min(next2, min(next3, next5))
        if dp[i] == next2 {
            p2++
        }
        if dp[i] == next3 {
            p3++
        }
        if dp[i] == next5 {
            p5++
        }
    }
    return dp[n-1]
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}
```

```go
func nthUglyNumber(n int) int {
	h := IntHeap([]int{1})
	heap.Init(&h)
	ans := 1
	vis := map[int]bool{1: true}
	for n > 0 {
		ans = heap.Pop(&h).(int)
		for _, v := range []int{2, 3, 5} {
			nxt := ans * v
			if !vis[nxt] {
				vis[nxt] = true
				heap.Push(&h, nxt)
			}
		}
		n--
	}
	return ans
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}
func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var nthUglyNumber = function (n) {
    let dp = [1];
    let p2 = 0,
        p3 = 0,
        p5 = 0;
    for (let i = 1; i < n; ++i) {
        const next2 = dp[p2] * 2,
            next3 = dp[p3] * 3,
            next5 = dp[p5] * 5;
        dp[i] = Math.min(next2, Math.min(next3, next5));
        if (dp[i] == next2) ++p2;
        if (dp[i] == next3) ++p3;
        if (dp[i] == next5) ++p5;
        dp.push(dp[i]);
    }
    return dp[n - 1];
};
```

### **C#**

```cs
public class Solution {
    public int NthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i)
        {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.Min(next2, Math.Min(next3, next5));
            if (dp[i] == next2)
            {
                ++p2;
            }
            if (dp[i] == next3)
            {
                ++p3;
            }
            if (dp[i] == next5)
            {
                ++p5;
            }
        }
        return dp[n - 1];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
