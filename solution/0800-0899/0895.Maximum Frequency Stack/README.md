# [895. 最大频率栈](https://leetcode.cn/problems/maximum-frequency-stack)

[English Version](/solution/0800-0899/0895.Maximum%20Frequency%20Stack/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出<strong>出现频率</strong>最高的元素。</p>

<p>实现 <code>FreqStack</code>&nbsp;类:</p>

<ul>
	<li><meta charset="UTF-8" /><code>FreqStack()</code>&nbsp;构造一个空的堆栈。</li>
	<li><meta charset="UTF-8" /><code>void push(int val)</code>&nbsp;将一个整数&nbsp;<code>val</code>&nbsp;压入栈顶。</li>
	<li><meta charset="UTF-8" /><code>int pop()</code>&nbsp;删除并返回堆栈中出现频率最高的元素。
	<ul>
		<li>如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
<strong>输出：</strong>[null,null,null,null,null,null,null,5,7,5,4]
<strong>解释：</strong>
FreqStack = new FreqStack();
freqStack.push (5);//堆栈为 [5]
freqStack.push (7);//堆栈是 [5,7]
freqStack.push (5);//堆栈是 [5,7,5]
freqStack.push (7);//堆栈是 [5,7,5,7]
freqStack.push (4);//堆栈是 [5,7,5,7,4]
freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= val &lt;= 10<sup>9</sup></code></li>
	<li><code>push</code>&nbsp;和 <code>pop</code>&nbsp;的操作数不大于 <code>2 * 10<sup>4</sup></code>。</li>
	<li>输入保证在调用&nbsp;<code>pop</code>&nbsp;之前堆栈中至少有一个元素。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 优先队列（大根堆）**

根据题目描述，我们需要设计一个支持弹出“出现频率最高”的元素的数据结构。如果存在多个元素出现频率相同，那么弹出最接近栈顶的元素。

我们可以使用哈希表 $cnt$ 记录每个元素出现的频率，用一个优先队列（大根堆） $q$ 维护元素频率以及对应的压栈时间戳。

执行压栈操作时，我们先将当前时间戳加一，即 $ts \gets ts + 1$；然后将元素 $val$ 的频率加一，即 $cnt[val] \gets cnt[val] + 1$，最后将三元组 $(cnt[val], ts, val)$ 加入优先队列 $q$ 中。压栈操作的时间复杂度为 $O(\log n)$。

执行弹栈操作时，我们直接从优先队列 $q$ 中弹出一个元素即可。由于优先队列 $q$ 中的元素按照频率降序排序，因此弹出的元素一定是出现频率最高的元素。如果存在多个元素出现频率相同，那么弹出最接近栈顶的元素，即弹出时间戳最大的元素。弹出后，我们将弹出元素的频率减一，即 $cnt[val] \gets cnt[val] - 1$。弹栈操作的时间复杂度为 $O(\log n)$。

**方法二：双哈希表**

在方法一中，为了能弹出符合要求的元素，我们维护了一个优先队列，每次都需要对优先队列进行操作，时间复杂度为 $O(\log n)$。如果我们能够在 $O(1)$ 的时间内找到符合要求的元素，那么整个数据结构每次操作的时间复杂度就可以降低到 $O(1)$。

实际上，我们可以用一个变量 $mx$ 记录当前出现频率的最大值，用一个哈希表 $d$ 记录每个出现频率对应的元素列表，与方法一相同，用一个哈希表 $cnt$ 记录每个元素出现的频率。

执行压栈操作时，我们将元素的频率加一，即 $cnt[val] \gets cnt[val] + 1$，然后将元素 $val$ 加入哈希表 $d$ 中对应的频率列表中，即 $d[cnt[val]].push(val)$。如果当前元素的频率大于 $mx$，则更新 $mx$，即 $mx \gets cnt[val]$。压栈操作的时间复杂度为 $O(1)$。

执行弹栈操作时，我们从哈希表 $d$ 中取出频率为 $mx$ 的元素列表，弹出列表中的最后一个元素 $val$，然后将 $val$ 从哈希表 $d$ 中移除，即 $d[mx].pop()$。最后将 $val$ 的频率减一，即 $cnt[val] \gets cnt[val] - 1$。如果 $d[mx]$ 列表为空，说明当前出现频率最大的元素已经全部弹出，我们需要将 $mx$ 减一，即 $mx \gets mx - 1$。弹栈操作的时间复杂度为 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class FreqStack:

    def __init__(self):
        self.cnt = defaultdict(int)
        self.q = []
        self.ts = 0

    def push(self, val: int) -> None:
        self.ts += 1
        self.cnt[val] += 1
        heappush(self.q, (-self.cnt[val], -self.ts, val))

    def pop(self) -> int:
        val = heappop(self.q)[2]
        self.cnt[val] -= 1
        return val


# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(val)
# param_2 = obj.pop()
```

```python
class FreqStack:

    def __init__(self):
        self.cnt = defaultdict(int)
        self.d = defaultdict(list)
        self.mx = 0

    def push(self, val: int) -> None:
        self.cnt[val] += 1
        self.d[self.cnt[val]].append(val)
        self.mx = max(self.mx, self.cnt[val])

    def pop(self) -> int:
        val = self.d[self.mx].pop()
        self.cnt[val] -= 1
        if not self.d[self.mx]:
            self.mx -= 1
        return val


# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(val)
# param_2 = obj.pop()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class FreqStack {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
    private int ts;

    public FreqStack() {

    }

    public void push(int val) {
        cnt.put(val, cnt.getOrDefault(val, 0) + 1);
        q.offer(new int[] {cnt.get(val), ++ts, val});
    }

    public int pop() {
        int val = q.poll()[2];
        cnt.put(val, cnt.get(val) - 1);
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
```

```java
class FreqStack {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private Map<Integer, Deque<Integer>> d = new HashMap<>();
    private int mx;

    public FreqStack() {
    }

    public void push(int val) {
        cnt.put(val, cnt.getOrDefault(val, 0) + 1);
        int t = cnt.get(val);
        d.computeIfAbsent(t, k -> new ArrayDeque<>()).push(val);
        mx = Math.max(mx, t);
    }

    public int pop() {
        int val = d.get(mx).pop();
        cnt.put(val, cnt.get(val) - 1);
        if (d.get(mx).isEmpty()) {
            --mx;
        }
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
```

### **C++**

```cpp
class FreqStack {
public:
    FreqStack() {

    }

    void push(int val) {
        ++cnt[val];
        q.emplace(cnt[val], ++ts, val);
    }

    int pop() {
        auto [a, b, val] = q.top();
        q.pop();
        --cnt[val];
        return val;
    }

private:
    unordered_map<int, int> cnt;
    priority_queue<tuple<int, int, int>> q;
    int ts = 0;
};

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack* obj = new FreqStack();
 * obj->push(val);
 * int param_2 = obj->pop();
 */
```

```cpp
class FreqStack {
public:
    FreqStack() {

    }

    void push(int val) {
        ++cnt[val];
        d[cnt[val]].push(val);
        mx = max(mx, cnt[val]);
    }

    int pop() {
        int val = d[mx].top();
        --cnt[val];
        d[mx].pop();
        if (d[mx].empty()) --mx;
        return val;
    }

private:
    unordered_map<int, int> cnt;
    unordered_map<int, stack<int>> d;
    int mx = 0;
};

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack* obj = new FreqStack();
 * obj->push(val);
 * int param_2 = obj->pop();
 */
```

### **Go**

```go
type FreqStack struct {
	cnt map[int]int
	q   hp
	ts  int
}

func Constructor() FreqStack {
	return FreqStack{map[int]int{}, hp{}, 0}
}

func (this *FreqStack) Push(val int) {
	this.cnt[val]++
	this.ts++
	heap.Push(&this.q, tuple{this.cnt[val], this.ts, val})
}

func (this *FreqStack) Pop() int {
	val := heap.Pop(&this.q).(tuple).val
	this.cnt[val]--
	return val
}

type tuple struct{ cnt, ts, val int }
type hp []tuple

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	return h[i].cnt > h[j].cnt || h[i].cnt == h[j].cnt && h[i].ts > h[j].ts
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

/**
 * Your FreqStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * param_2 := obj.Pop();
 */
```

```go
type FreqStack struct {
	cnt map[int]int
	d   map[int][]int
	mx  int
}

func Constructor() FreqStack {
	return FreqStack{map[int]int{}, map[int][]int{}, 0}
}

func (this *FreqStack) Push(val int) {
	this.cnt[val]++
	this.d[this.cnt[val]] = append(this.d[this.cnt[val]], val)
	this.mx = max(this.mx, this.cnt[val])
}

func (this *FreqStack) Pop() int {
	val := this.d[this.mx][len(this.d[this.mx])-1]
	this.d[this.mx] = this.d[this.mx][:len(this.d[this.mx])-1]
	this.cnt[val]--
	if len(this.d[this.mx]) == 0 {
		this.mx--
	}
	return val
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * param_2 := obj.Pop();
 */
```

### **...**

```

```

<!-- tabs:end -->
