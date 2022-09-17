# [1753. 移除石子的最大得分](https://leetcode.cn/problems/maximum-score-from-removing-stones)

[English Version](/solution/1700-1799/1753.Maximum%20Score%20From%20Removing%20Stones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在玩一个单人游戏，面前放置着大小分别为 <code>a</code>​​​​​​、<code>b</code> 和 <code>c</code>​​​​​​ 的 <strong>三堆</strong> 石子。</p>

<p>每回合你都要从两个 <strong>不同的非空堆</strong> 中取出一颗石子，并在得分上加 <code>1</code> 分。当存在 <strong>两个或更多</strong> 的空堆时，游戏停止。</p>

<p>给你三个整数 <code>a</code> 、<code>b</code> 和 <code>c</code> ，返回可以得到的 <strong>最大分数</strong> 。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = 4, c = 6
<strong>输出：</strong>6
<strong>解释：</strong>石子起始状态是 (2, 4, 6) ，最优的一组操作是：
- 从第一和第三堆取，石子状态现在是 (1, 4, 5)
- 从第一和第三堆取，石子状态现在是 (0, 4, 4)
- 从第二和第三堆取，石子状态现在是 (0, 3, 3)
- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
总分：6 分 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 4, b = 4, c = 6
<strong>输出：</strong>7
<strong>解释：</strong>石子起始状态是 (4, 4, 6) ，最优的一组操作是：
- 从第一和第二堆取，石子状态现在是 (3, 3, 6)
- 从第一和第三堆取，石子状态现在是 (2, 3, 5)
- 从第一和第三堆取，石子状态现在是 (1, 3, 4)
- 从第一和第三堆取，石子状态现在是 (0, 3, 3)
- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
总分：7 分 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 8, c = 8
<strong>输出：</strong>8
<strong>解释：</strong>最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= a, b, c <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列（大根堆）**

每次贪心地拿出最大的两堆，直到至少有两堆石子为空。

时间复杂度 $O(m\times \log n)$。其中 $m$, $n$ 分别为石子总数以及石子堆数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        h = [-a, -b, -c]
        heapify(h)
        ans = 0
        while 1:
            a, b = heappop(h), heappop(h)
            if b == 0:
                break
            heappush(h, a + 1)
            heappush(h, b + 1)
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        pq.offer(a);
        pq.offer(b);
        pq.offer(c);
        int ans = 0;
        while (true) {
            int x = pq.poll(), y = pq.poll();
            if (y == 0) {
                break;
            }
            pq.offer(x - 1);
            pq.offer(y - 1);
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumScore(int a, int b, int c) {
        priority_queue<int> pq;
        pq.push(a);
        pq.push(b);
        pq.push(c);
        int ans = 0;
        while (1) {
            a = pq.top(), pq.pop();
            b = pq.top(), pq.pop();
            if (b == 0) {
                break;
            }
            pq.push(a - 1);
            pq.push(b - 1);
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumScore(a int, b int, c int) int {
	q := hp{[]int{a, b, c}}
	ans := 0
	for {
		a = q.IntSlice[0]
		heap.Pop(&q)
		b = q.IntSlice[0]
		heap.Pop(&q)
		if b == 0 {
			break
		}
		heap.Push(&q, a-1)
		heap.Push(&q, b-1)
		ans++
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
```

### **...**

```

```

<!-- tabs:end -->
