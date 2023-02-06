# [1687. 从仓库到码头运输箱子](https://leetcode.cn/problems/delivering-boxes-from-storage-to-ports)

[English Version](/solution/1600-1699/1687.Delivering%20Boxes%20from%20Storage%20to%20Ports/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有&nbsp;<strong>箱子数目的限制</strong>&nbsp;和 <strong>总重量的限制</strong>&nbsp;。</p>

<p>给你一个箱子数组&nbsp;<code>boxes</code>&nbsp;和三个整数 <code>portsCount</code>, <code>maxBoxes</code>&nbsp;和&nbsp;<code>maxWeight</code>&nbsp;，其中&nbsp;<code>boxes[i] = [ports<sub>​​i</sub>​, weight<sub>i</sub>]</code>&nbsp;。</p>

<ul>
	<li><code>ports<sub>​​i</sub></code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个箱子需要送达的码头，&nbsp;<code>weights<sub>i</sub></code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个箱子的重量。</li>
	<li><code>portsCount</code>&nbsp;是码头的数目。</li>
	<li><code>maxBoxes</code> 和&nbsp;<code>maxWeight</code>&nbsp;分别是卡车每趟运输箱子数目和重量的限制。</li>
</ul>

<p>箱子需要按照 <strong>数组顺序</strong>&nbsp;运输，同时每次运输需要遵循以下步骤：</p>

<ul>
	<li>卡车从&nbsp;<code>boxes</code>&nbsp;队列中按顺序取出若干个箱子，但不能违反&nbsp;<code>maxBoxes</code> 和&nbsp;<code>maxWeight</code>&nbsp;限制。</li>
	<li>对于在卡车上的箱子，我们需要 <strong>按顺序</strong>&nbsp;处理它们，卡车会通过 <strong>一趟行程</strong>&nbsp;将最前面的箱子送到目的地码头并卸货。如果卡车已经在对应的码头，那么不需要 <strong>额外行程</strong>&nbsp;，箱子也会立马被卸货。</li>
	<li>卡车上所有箱子都被卸货后，卡车需要 <strong>一趟行程</strong>&nbsp;回到仓库，从箱子队列里再取出一些箱子。</li>
</ul>

<p>卡车在将所有箱子运输并卸货后，最后必须回到仓库。</p>

<p>请你返回将所有箱子送到相应码头的&nbsp;<b>最少行程</b>&nbsp;次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3
<b>输出：</b>4
<b>解释：</b>最优策略如下：
- 卡车将所有箱子装上车，到达码头 1 ，然后去码头 2 ，然后再回到码头 1 ，最后回到仓库，总共需要 4 趟行程。
所以总行程数为 4 。
注意到第一个和第三个箱子不能同时被卸货，因为箱子需要按顺序处理（也就是第二个箱子需要先被送到码头 2 ，然后才能处理第三个箱子）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6
<b>输出：</b>6
<b>解释：</b>最优策略如下：
- 卡车首先运输第一个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第二、第三、第四个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第五个箱子，到达码头 2 ，回到仓库，总共 2 趟行程。
总行程数为 2 + 2 + 2 = 6 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7
<b>输出：</b>6
<b>解释：</b>最优策略如下：
- 卡车运输第一和第二个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第三和第四个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第五和第六个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
总行程数为 2 + 2 + 2 = 6 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>boxes = [[2,4],[2,5],[3,1],[3,2],[3,7],[3,1],[4,4],[1,3],[5,2]], portsCount = 5, maxBoxes = 5, maxWeight = 7
<b>输出：</b>14
<b>解释：</b>最优策略如下：
- 卡车运输第一个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第二个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第三和第四个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第五个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第六和第七个箱子，到达码头 3 ，然后去码头 4 ，然后回到仓库，总共 3 趟行程。
- 卡车运输第八和第九个箱子，到达码头 1 ，然后去码头 5 ，然后回到仓库，总共 3 趟行程。
总行程数为 2 + 2 + 2 + 2 + 3 + 3 = 14 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= boxes.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= portsCount, maxBoxes, maxWeight &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= ports<sub>​​i</sub> &lt;= portsCount</code></li>
	<li><code>1 &lt;= weights<sub>i</sub> &lt;= maxWeight</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划 + 单调队列优化**

我们定义 $f[i]$ 表示把前 $i$ 个箱子从仓库运送到相应码头的最少行程数，那么答案就是 $f[n]$。

箱子需要按数组顺序运输，每一次运输，卡车会按顺序取出连续的几个箱子，然后依次送往对应的码头，全部送达之后，又回到了仓库。

因此，我们可以枚举上一次运输的最后一个箱子的编号 $j$，那么 $f[i]$ 就可以从 $f[j]$ 转移而来，转移的时候，我们需要考虑以下几个问题：

-   从 $f[j]$ 转移过来的时候，卡车上的箱子数量不能超过 $maxBoxes$
-   从 $f[j]$ 转移过来的时候，卡车上的箱子总重量不能超过 $maxWeight$

状态转移方程为：

$$
f[i] = \min_{j \in [i - maxBoxes, i - 1]} \left(f[j] + \sum_{k = j + 1}^i \text{cost}(k)\right)
$$

其中 $\sum_{k = j + 1}^i \text{cost}(k)$ 表示通过一次运输，把 $[j+1,..i]$ 这些箱子送往对应的码头所需要的行程数。这部分行程数可以通过前缀和快速计算出来。

简单举个例子，假设我们取出了 $1, 2, 3$ 这三个箱子，需要送往 $4, 4, 5$ 这三个码头，那么我们首先要从仓库到 $4$ 号码头，然后再从 $4$ 号码头到 $5$ 号码头，最后再从 $5$ 号码头回到仓库。可以发现，从仓库到码头，以及从码头到仓库，需要花费 $2$ 趟行程，而从码头到码头的行程数，取决于相邻两个码头是否相同，如果不相同，那么行程数会增加 $1$，否则不变。因此，我们可以通过前缀和，计算出码头之间的行程数，再加上首尾两趟行程，就能把 $[j+1,..i]$ 这些箱子送往对应的码头所需要的行程数计算出来。

代码实现如下：

```python
# 33/39 个通过测试用例，超出时间限制
class Solution:
    def boxDelivering(self, boxes: List[List[int]], portsCount: int, maxBoxes: int, maxWeight: int) -> int:
        n = len(boxes)
        ws = list(accumulate((box[1] for box in boxes), initial=0))
        c = [int(a != b) for a, b in pairwise(box[0] for box in boxes)]
        cs = list(accumulate(c, initial=0))
        f = [inf] * (n + 1)
        f[0] = 0
        for i in range(1, n + 1):
            for j in range(max(0, i - maxBoxes), i):
                if ws[i] - ws[j] <= maxWeight:
                    f[i] = min(f[i], f[j] + cs[i - 1] - cs[j] + 2)
        return f[n]
```

```java
// 35/39 个通过测试用例，超出时间限制
class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        long[] ws = new long[n + 1];
        int[] cs = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) {
                cs[i + 1] = cs[i] + (p != boxes[i + 1][0] ? 1 : 0);
            }
        }
        int[] f = new int[n + 1];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = Math.max(0, i - maxBoxes); j < i; ++j) {
                if (ws[i] - ws[j] <= maxWeight) {
                    f[i] = Math.min(f[i], f[j] + cs[i - 1] - cs[j] + 2);
                }
            }
        }
        return f[n];
    }
}
```

```cpp
// 35/39 个通过测试用例，超出时间限制
class Solution {
public:
    int boxDelivering(vector<vector<int>>& boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.size();
        long ws[n + 1];
        int cs[n];
        ws[0] = cs[0] = 0;
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) cs[i + 1] = cs[i] + (p != boxes[i + 1][0]);
        }
        int f[n + 1];
        memset(f, 0x3f, sizeof f);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = max(0, i - maxBoxes); j < i; ++j) {
                if (ws[i] - ws[j] <= maxWeight) {
                    f[i] = min(f[i], f[j] + cs[i - 1] - cs[j] + 2);
                }
            }
        }
        return f[n];
    }
};
```

```go
// 35/39 个通过测试用例，超出时间限制
func boxDelivering(boxes [][]int, portsCount int, maxBoxes int, maxWeight int) int {
	n := len(boxes)
	ws := make([]int, n+1)
	cs := make([]int, n)
	for i, box := range boxes {
		p, w := box[0], box[1]
		ws[i+1] = ws[i] + w
		if i < n-1 {
			t := 0
			if p != boxes[i+1][0] {
				t++
			}
			cs[i+1] = cs[i] + t
		}
	}
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = 1 << 30
		for j := max(0, i-maxBoxes); j < i; j++ {
			if ws[i]-ws[j] <= maxWeight {
				f[i] = min(f[i], f[j]+cs[i-1]-cs[j]+2)
			}
		}
	}
	return f[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

本题数据规模达到 $10^5$，而以上代码的时间复杂度为 $O(n^2)$，会超出时间限制。我们仔细观察：

$$
f[i] = min(f[i], f[j] + cs[i - 1] - cs[j] + 2)
$$

实际上我们是要在 $[i-maxBoxes,..i-1]$ 这个窗口内找到一个 $j$，使得 $f[j] - cs[j]$ 的值最小，求滑动窗口的最小值，一种常用的做法是使用单调队列，可以在 $O(1)$ 时间内获取到满足条件的最小值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是题目中箱子的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def boxDelivering(
        self, boxes: List[List[int]], portsCount: int, maxBoxes: int, maxWeight: int
    ) -> int:
        n = len(boxes)
        ws = list(accumulate((box[1] for box in boxes), initial=0))
        c = [int(a != b) for a, b in pairwise(box[0] for box in boxes)]
        cs = list(accumulate(c, initial=0))
        f = [0] * (n + 1)
        q = deque([0])
        for i in range(1, n + 1):
            while q and (i - q[0] > maxBoxes or ws[i] - ws[q[0]] > maxWeight):
                q.popleft()
            if q:
                f[i] = cs[i - 1] + f[q[0]] - cs[q[0]] + 2
            if i < n:
                while q and f[q[-1]] - cs[q[-1]] >= f[i] - cs[i]:
                    q.pop()
                q.append(i)
        return f[n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        long[] ws = new long[n + 1];
        int[] cs = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) {
                cs[i + 1] = cs[i] + (p != boxes[i + 1][0] ? 1 : 0);
            }
        }
        int[] f = new int[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        for (int i = 1; i <= n; ++i) {
            while (!q.isEmpty()
                && (i - q.peekFirst() > maxBoxes || ws[i] - ws[q.peekFirst()] > maxWeight)) {
                q.pollFirst();
            }
            if (!q.isEmpty()) {
                f[i] = cs[i - 1] + f[q.peekFirst()] - cs[q.peekFirst()] + 2;
            }
            if (i < n) {
                while (!q.isEmpty() && f[q.peekLast()] - cs[q.peekLast()] >= f[i] - cs[i]) {
                    q.pollLast();
                }
                q.offer(i);
            }
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int boxDelivering(vector<vector<int>>& boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.size();
        long ws[n + 1];
        int f[n + 1];
        int cs[n];
        ws[0] = cs[0] = f[0] = 0;
        for (int i = 0; i < n; ++i) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) cs[i + 1] = cs[i] + (p != boxes[i + 1][0]);
        }
        deque<int> q{{0}};
        for (int i = 1; i <= n; ++i) {
            while (!q.empty() && (i - q.front() > maxBoxes || ws[i] - ws[q.front()] > maxWeight)) q.pop_front();
            if (!q.empty()) f[i] = cs[i - 1] + f[q.front()] - cs[q.front()] + 2;
            if (i < n) {
                while (!q.empty() && f[q.back()] - cs[q.back()] >= f[i] - cs[i]) q.pop_back();
                q.push_back(i);
            }
        }
        return f[n];
    }
};
```

### **Go**

```go
func boxDelivering(boxes [][]int, portsCount int, maxBoxes int, maxWeight int) int {
	n := len(boxes)
	ws := make([]int, n+1)
	cs := make([]int, n)
	for i, box := range boxes {
		p, w := box[0], box[1]
		ws[i+1] = ws[i] + w
		if i < n-1 {
			t := 0
			if p != boxes[i+1][0] {
				t++
			}
			cs[i+1] = cs[i] + t
		}
	}
	f := make([]int, n+1)
	q := []int{0}
	for i := 1; i <= n; i++ {
		for len(q) > 0 && (i-q[0] > maxBoxes || ws[i]-ws[q[0]] > maxWeight) {
			q = q[1:]
		}
		if len(q) > 0 {
			f[i] = cs[i-1] + f[q[0]] - cs[q[0]] + 2
		}
		if i < n {
			for len(q) > 0 && f[q[len(q)-1]]-cs[q[len(q)-1]] >= f[i]-cs[i] {
				q = q[:len(q)-1]
			}
			q = append(q, i)
		}
	}
	return f[n]
}
```

### **...**

```

```

<!-- tabs:end -->
