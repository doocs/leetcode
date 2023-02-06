# [895. Maximum Frequency Stack](https://leetcode.com/problems/maximum-frequency-stack)

[中文文档](/solution/0800-0899/0895.Maximum%20Frequency%20Stack/README.md)

## Description

<p>Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.</p>

<p>Implement the <code>FreqStack</code> class:</p>

<ul>
	<li><code>FreqStack()</code> constructs an empty frequency stack.</li>
	<li><code>void push(int val)</code> pushes an integer <code>val</code> onto the top of the stack.</li>
	<li><code>int pop()</code> removes and returns the most frequent element in the stack.
	<ul>
		<li>If there is a tie for the most frequent element, the element closest to the stack&#39;s top is removed and returned.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FreqStack&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;, &quot;pop&quot;]
[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
<strong>Output</strong>
[null, null, null, null, null, null, null, 5, 7, 5, 4]

<strong>Explanation</strong>
FreqStack freqStack = new FreqStack();
freqStack.push(5); // The stack is [5]
freqStack.push(7); // The stack is [5,7]
freqStack.push(5); // The stack is [5,7,5]
freqStack.push(7); // The stack is [5,7,5,7]
freqStack.push(4); // The stack is [5,7,5,7,4]
freqStack.push(5); // The stack is [5,7,5,7,4,5]
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= val &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>2 * 10<sup>4</sup></code> calls will be made to <code>push</code> and <code>pop</code>.</li>
	<li>It is guaranteed that there will be at least one element in the stack before calling <code>pop</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
