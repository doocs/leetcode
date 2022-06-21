# [264. 丑数 II](https://leetcode.cn/problems/ugly-number-ii)

[English Version](/solution/0200-0299/0264.Ugly%20Number%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，请你找出并返回第 <code>n</code> 个 <strong>丑数</strong> 。</p>

<p><strong>丑数 </strong>就是只包含质因数 <code>2</code>、<code>3</code> 和/或 <code>5</code> 的正整数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>12
<strong>解释：</strong>[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
<strong>解释：</strong>1 通常被视为丑数。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 1690</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（最小堆）**

初始时，将第一个丑数 $1$ 加入堆。每次取出堆顶元素 $x$，由于 $2x$, $3x$, $5x$ 也是丑数，因此将它们加入堆中。为了避免重复元素，可以用哈希表 $vis$ 去重。

时间复杂度 $O(nlogn)$，空间复杂度 $O(n)$。

**方法二：动态规划**

定义数组 $dp$，$dp[i-1]$ 表示第 $i$ 个丑数，那么第 $n$ 个丑数就是 $dp[n - 1]$。最小的丑数是 $1$，所以 $dp[0]=1$。

定义 $3$ 个指针 $p_2$，$p_3$，$p_5$，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都指向 $0$。

当 $i∈[1,n)$，$dp[i]=min(dp[p_2] \ * 2, dp[p_3] \ * 3, dp[p_5] \ * 5)$，然后分别比较 $dp[i]$ 与 $dp[p_2] \ * 2$、$dp[p_3] \ * 3$、$dp[p_5] \ * 5$ 是否相等，若是，则对应的指针加 $1$。

最后返回 $dp[n-1]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
