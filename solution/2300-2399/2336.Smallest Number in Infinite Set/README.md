# [2336. 无限集中的最小数字](https://leetcode.cn/problems/smallest-number-in-infinite-set)

[English Version](/solution/2300-2399/2336.Smallest%20Number%20in%20Infinite%20Set/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个包含所有正整数的集合 <code>[1, 2, 3, 4, 5, ...]</code> 。</p>

<p>实现 <code>SmallestInfiniteSet</code> 类：</p>

<ul>
	<li><code>SmallestInfiniteSet()</code> 初始化 <strong>SmallestInfiniteSet</strong> 对象以包含 <strong>所有</strong> 正整数。</li>
	<li><code>int popSmallest()</code> <strong>移除</strong> 并返回该无限集中的最小整数。</li>
	<li><code>void addBack(int num)</code> 如果正整数 <code>num</code> <strong>不</strong> 存在于无限集中，则将一个 <code>num</code> <strong>添加</strong> 到该无限集中。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入</strong>
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
<strong>输出</strong>
[null, null, 1, 2, 3, null, 1, 4, 5]

<strong>解释</strong>
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
                                   // 且 1 是最小的整数，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
	<li>最多调用 <code>popSmallest</code> 和 <code>addBack</code> 方法 <strong>共计</strong> <code>1000</code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

**方法二：哈希表 + 优先队列（小根堆）**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class SmallestInfiniteSet:
    def __init__(self):
        self.black = set()

    def popSmallest(self) -> int:
        i = 1
        while i in self.black:
            i += 1
        self.black.add(i)
        return i

    def addBack(self, num: int) -> None:
        self.black.discard(num)


# Your SmallestInfiniteSet object will be instantiated and called as such:
# obj = SmallestInfiniteSet()
# param_1 = obj.popSmallest()
# obj.addBack(num)
```

```python
class SmallestInfiniteSet:

    def __init__(self):
        self.h = list(range(1, 1010))
        self.s = set(self.h)
        heapify(self.h)

    def popSmallest(self) -> int:
        ans = heappop(self.h)
        self.s.discard(ans)
        return ans

    def addBack(self, num: int) -> None:
        if num not in self.s:
            self.s.add(num)
            heappush(self.h, num)


# Your SmallestInfiniteSet object will be instantiated and called as such:
# obj = SmallestInfiniteSet()
# param_1 = obj.popSmallest()
# obj.addBack(num)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class SmallestInfiniteSet {
    private Set<Integer> black = new HashSet<>();

    public SmallestInfiniteSet() {

    }

    public int popSmallest() {
        int i = 1;
        for (; black.contains(i); ++i);
        black.add(i);
        return i;
    }

    public void addBack(int num) {
        black.remove(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
```

```java
class SmallestInfiniteSet {
    private PriorityQueue<Integer> pq = new PriorityQueue<>();
    private Set<Integer> s = new HashSet<>();

    public SmallestInfiniteSet() {
        for (int i = 1; i < 1010; ++i) {
            pq.offer(i);
            s.add(i);
        }
    }

    public int popSmallest() {
        int ans = pq.poll();
        s.remove(ans);
        return ans;
    }

    public void addBack(int num) {
        if (!s.contains(num)) {
            s.add(num);
            pq.offer(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
```

### **C++**

```cpp
class SmallestInfiniteSet {
public:
    unordered_set<int> black;

    SmallestInfiniteSet() {
    }

    int popSmallest() {
        int i = 1;
        for (; black.count(i); ++i)
            ;
        black.insert(i);
        return i;
    }

    void addBack(int num) {
        black.erase(num);
    }
};

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet* obj = new SmallestInfiniteSet();
 * int param_1 = obj->popSmallest();
 * obj->addBack(num);
 */
```

```cpp
class SmallestInfiniteSet {
public:
    priority_queue<int, vector<int>, greater<int>> pq;
    unordered_set<int> s;

    SmallestInfiniteSet() {
        for (int i = 1; i < 1010; ++i)
        {
            pq.push(i);
            s.insert(i);
        }
    }

    int popSmallest() {
        int ans = pq.top();
        pq.pop();
        s.erase(ans);
        return ans;
    }

    void addBack(int num) {
        if (!s.count(num))
        {
            s.insert(num);
            pq.push(num);
        }
    }
};

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet* obj = new SmallestInfiniteSet();
 * int param_1 = obj->popSmallest();
 * obj->addBack(num);
 */
```

### **Go**

```go
type SmallestInfiniteSet struct {
	black map[int]bool
}

func Constructor() SmallestInfiniteSet {
	s := map[int]bool{}
	return SmallestInfiniteSet{s}
}

func (this *SmallestInfiniteSet) PopSmallest() int {
	i := 1
	for ; this.black[i]; i++ {
	}
	this.black[i] = true
	return i
}

func (this *SmallestInfiniteSet) AddBack(num int) {
	this.black[num] = false
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.PopSmallest();
 * obj.AddBack(num);
 */
```

```go
type SmallestInfiniteSet struct {
	h *hp
	s map[int]bool
}

func Constructor() SmallestInfiniteSet {
	h := &hp{}
	s := map[int]bool{}
	for i := 1; i < 1010; i++ {
		s[i] = true
		h.push(i)
	}
	return SmallestInfiniteSet{h, s}
}

func (this *SmallestInfiniteSet) PopSmallest() int {
	ans := this.h.pop()
	this.s[ans] = false
	return ans
}

func (this *SmallestInfiniteSet) AddBack(num int) {
	if !this.s[num] {
		this.s[num] = true
		this.h.push(num)
	}
}

type hp []int

func (h hp) Len() int              { return len(h) }
func (h hp) Less(i, j int) bool    { return h[i] < h[j] }
func (h hp) Swap(i, j int)         { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{})   { *h = append(*h, v.(int)) }
func (h *hp) Pop() (v interface{}) { a := *h; *h, v = a[:len(a)-1], a[len(a)-1]; return }
func (h *hp) push(v int)           { heap.Push(h, v) }
func (h *hp) pop() int             { return heap.Pop(h).(int) }
func (h *hp) top() int             { a := *h; return a[0] }

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.PopSmallest();
 * obj.AddBack(num);
 */
```

### **TypeScript**

```ts
class SmallestInfiniteSet {
    private hashMap: boolean[];

    constructor() {
        this.hashMap = new Array(1001).fill(true);
    }

    popSmallest(): number {
        for (let i = 1; i <= 1001; i++) {
            if (this.hashMap[i]) {
                this.hashMap[i] = false;
                return i;
            }
        }
        return -1;
    }

    addBack(num: number): void {
        if (!this.hashMap[num]) {
            this.hashMap[num] = true;
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * var obj = new SmallestInfiniteSet()
 * var param_1 = obj.popSmallest()
 * obj.addBack(num)
 */
```

### **Rust**

```rust
struct SmallestInfiniteSet {
    counter: [bool; 1000]
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl SmallestInfiniteSet {

    fn new() -> Self {
        Self {
            counter: [true; 1000]
        }
    }

    fn pop_smallest(&mut self) -> i32 {
        for i in 0..1000 {
            if self.counter[i] {
                self.counter[i] = false;
                return i as i32 + 1;
            }
        }
        -1
    }

    fn add_back(&mut self, num: i32) {
        self.counter[num as usize - 1] = true;
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * let obj = SmallestInfiniteSet::new();
 * let ret_1: i32 = obj.pop_smallest();
 * obj.add_back(num);
 */
```

### **...**

```

```

<!-- tabs:end -->
