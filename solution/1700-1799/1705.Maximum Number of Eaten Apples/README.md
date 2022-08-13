# [1705. 吃苹果的最大数目](https://leetcode.cn/problems/maximum-number-of-eaten-apples)

[English Version](/solution/1700-1799/1705.Maximum%20Number%20of%20Eaten%20Apples/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一棵特殊的苹果树，一连 <code>n</code> 天，每天都可以长出若干个苹果。在第 <code>i</code> 天，树上会长出 <code>apples[i]</code> 个苹果，这些苹果将会在 <code>days[i]</code> 天后（也就是说，第 <code>i + days[i]</code> 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 <code>apples[i] == 0</code> 且 <code>days[i] == 0</code> 表示。</p>

<p>你打算每天 <strong>最多</strong> 吃一个苹果来保证营养均衡。注意，你可以在这 <code>n</code> 天之后继续吃苹果。</p>

<p>给你两个长度为 <code>n</code> 的整数数组 <code>days</code> 和 <code>apples</code> ，返回你可以吃掉的苹果的最大数目<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>apples = [1,2,3,5,2], days = [3,2,1,4,2]
<strong>输出：</strong>7
<strong>解释：</strong>你可以吃掉 7 个苹果：
- 第一天，你吃掉第一天长出来的苹果。
- 第二天，你吃掉一个第二天长出来的苹果。
- 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
- 第四天到第七天，你吃的都是第四天长出来的苹果。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
<strong>输出：</strong>5
<strong>解释：</strong>你可以吃掉 5 个苹果：
- 第一天到第三天，你吃的都是第一天长出来的苹果。
- 第四天和第五天不吃苹果。
- 第六天和第七天，你吃的都是第六天长出来的苹果。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>apples.length == n</code></li>
	<li><code>days.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= apples[i], days[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li>只有在 <code>apples[i] = 0</code> 时，<code>days[i] = 0</code> 才成立</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

优先队列。先吃掉最容易腐烂的苹果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def eatenApples(self, apples: List[int], days: List[int]) -> int:
        q = []
        n = len(apples)
        i = ans = 0
        while i < n or q:
            if i < n and apples[i] > 0:
                heappush(q, [i + days[i] - 1, apples[i]])
            while q and q[0][0] < i:
                heappop(q)
            if q:
                end, num = heappop(q)
                num -= 1
                ans += 1
                if num > 0 and end > i:
                    heappush(q, [end, num])
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int ans = 0, i = 0, n = apples.length;
        while (i < n || !q.isEmpty()) {
            if (i < n && apples[i] > 0) {
                q.offer(new int[]{i + days[i] - 1, apples[i]});
            }
            while (!q.isEmpty() && q.peek()[0] < i) {
                q.poll();
            }
            if (!q.isEmpty()) {
                int[] t = q.poll();
                if (--t[1] > 0 && t[0] > i) {
                    q.offer(t);
                }
                ++ans;
            }
            ++i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
typedef pair<int, int> PII;

class Solution {
public:
    int eatenApples(vector<int>& apples, vector<int>& days) {
        priority_queue<PII, vector<PII>, greater<PII>> q;
        int i = 0, n = apples.size(), ans = 0;
        while (i < n || !q.empty()) {
            if (i < n && apples[i] > 0) q.emplace(i + days[i] - 1, apples[i]);
            while (!q.empty() && q.top().first < i) q.pop();
            if (!q.empty()) {
                PII t = q.top();
                q.pop();
                --t.second;
                ++ans;
                if (t.second > 0 && t.first > i) q.emplace(t);
            }
            ++i;
        }
        return ans;
    }
};
```

### **Go**

```go
func eatenApples(apples []int, days []int) int {
	var h hp
	ans, n := 0, len(apples)
	for i := 0; i < n || len(h) > 0; i++ {
		if i < n && apples[i] > 0 {
			heap.Push(&h, pair{i + days[i] - 1, apples[i]})
		}
		for len(h) > 0 && h[0].first < i {
			heap.Pop(&h)
		}
		if len(h) > 0 {
			h[0].second--
			if h[0].first == i || h[0].second == 0 {
				heap.Pop(&h)
			}
			ans++
		}
	}
	return ans
}

type pair struct {
	first  int
	second int
}

type hp []pair

func (a hp) Len() int            { return len(a) }
func (a hp) Swap(i, j int)       { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool  { return a[i].first < a[j].first }
func (a *hp) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *hp) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }
```

### **...**

```

```

<!-- tabs:end -->
