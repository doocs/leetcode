---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3508.Implement%20Router/README.md
rating: 1851
source: 第 444 场周赛 Q2
tags:
    - 设计
    - 队列
    - 数组
    - 哈希表
    - 二分查找
    - 有序集合
---

<!-- problem:start -->

# [3508. 设计路由器](https://leetcode.cn/problems/implement-router)

[English Version](/solution/3500-3599/3508.Implement%20Router/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你设计一个数据结构来高效管理网络路由器中的数据包。每个数据包包含以下属性：</p>

<ul>
	<li><code>source</code>：生成该数据包的机器的唯一标识符。</li>
	<li><code>destination</code>：目标机器的唯一标识符。</li>
	<li><code>timestamp</code>：该数据包到达路由器的时间戳。</li>
</ul>

<p>实现 <code>Router</code> 类：</p>

<p><code>Router(int memoryLimit)</code>：初始化路由器对象，并设置固定的内存限制。</p>

<ul>
	<li><code>memoryLimit</code> 是路由器在任意时间点可以存储的 <strong>最大</strong> 数据包数量。</li>
	<li>如果添加一个新数据包会超过这个限制，则必须移除 <strong>最旧的</strong> 数据包以腾出空间。</li>
</ul>

<p><code>bool addPacket(int source, int destination, int timestamp)</code>：将具有给定属性的数据包添加到路由器。</p>

<ul>
	<li>如果路由器中已经存在一个具有相同 <code>source</code>、<code>destination</code> 和 <code>timestamp</code> 的数据包，则视为重复数据包。</li>
	<li>如果数据包成功添加（即不是重复数据包），返回 <code>true</code>；否则返回 <code>false</code>。</li>
</ul>

<p><code>int[] forwardPacket()</code>：以 FIFO（先进先出）顺序转发下一个数据包。</p>

<ul>
	<li>从存储中移除该数据包。</li>
	<li>以数组 <code>[source, destination, timestamp]</code> 的形式返回该数据包。</li>
	<li>如果没有数据包可以转发，则返回空数组。</li>
</ul>

<p><code>int getCount(int destination, int startTime, int endTime)</code>：</p>

<ul>
	<li>返回当前存储在路由器中（即尚未转发）的，且目标地址为指定 <code>destination</code> 且时间戳在范围 <code>[startTime, endTime]</code>（包括两端）内的数据包数量。</li>
</ul>

<p><strong>注意</strong>：对于 <code>addPacket</code> 的查询会按照 <code>timestamp</code> 的递增顺序进行。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["Router", "addPacket", "addPacket", "addPacket", "addPacket", "addPacket", "forwardPacket", "addPacket", "getCount"]<br />
[[3], [1, 4, 90], [2, 5, 90], [1, 4, 90], [3, 5, 95], [4, 5, 105], [], [5, 2, 110], [5, 100, 110]]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, true, true, false, true, true, [2, 5, 90], true, 1] </span></p>

<p><strong>解释：</strong></p>
<code>Router router = new Router(3);</code> // 初始化路由器，内存限制为 3。<br />
<code>router.addPacket(1, 4, 90);</code> // 数据包被添加，返回 True。<br />
<code>router.addPacket(2, 5, 90);</code> // 数据包被添加，返回 True。<br />
<code>router.addPacket(1, 4, 90);</code> // 这是一个重复数据包，返回 False。<br />
<code>router.addPacket(3, 5, 95);</code> // 数据包被添加，返回 True。<br />
<code>router.addPacket(4, 5, 105);</code> // 数据包被添加，<code>[1, 4, 90]</code> 被移除，因为数据包数量超过限制，返回 True。<br />
<code>router.forwardPacket();</code> // 转发数据包 <code>[2, 5, 90]</code> 并将其从路由器中移除。<br />
<code>router.addPacket(5, 2, 110);</code> // 数据包被添加，返回 True。<br />
<code>router.getCount(5, 100, 110);</code> // 唯一目标地址为 5 且时间在 <code>[100, 110]</code>&nbsp;范围内的数据包是 <code>[4, 5, 105]</code>，返回 1。</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["Router", "addPacket", "forwardPacket", "forwardPacket"]<br />
[[2], [7, 4, 90], [], []]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, true, [7, 4, 90], []] </span></p>

<p><strong>解释：</strong></p>
<code>Router router = new Router(2);</code> // 初始化路由器，内存限制为 2。<br />
<code>router.addPacket(7, 4, 90);</code> // 返回 True。<br />
<code>router.forwardPacket();</code> // 返回 <code>[7, 4, 90]</code>。<br />
<code>router.forwardPacket();</code> // 没有数据包可以转发，返回 <code>[]</code>。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= memoryLimit &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= source, destination &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= timestamp &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= startTime &lt;= endTime &lt;= 10<sup>9</sup></code></li>
	<li><code>addPacket</code>、<code>forwardPacket</code> 和 <code>getCount</code> 方法的总调用次数最多为 <code>10<sup>5</sup></code>。</li>
	<li>对于 <code>addPacket</code> 的查询，<code>timestamp</code> 按递增顺序给出。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一： 哈希表 + 队列 + 二分查找

我们用一个哈希表 $\textit{vis}$ 来存储已经添加过的数据包的哈希值，用一个队列 $\textit{q}$ 来存储当前路由器中的数据包，用一个哈希表 $\textit{idx}$ 来记录每个目标地址已经转发的数据包数量，用一个哈希表 $\textit{d}$ 来存储每个目标地址对应的时间戳列表。

对于 $\textit{addPacket}$ 方法，我们计算数据包的哈希值，如果已经存在于 $\textit{vis}$ 中，则返回 $\text{false}$；否则将其添加到 $\textit{vis}$ 中，并检查当前队列的大小是否超过内存限制，如果超过则调用 $\textit{forwardPacket}$ 方法移除最旧的数据包，然后将新数据包添加到队列中，并将时间戳添加到对应目标地址的时间戳列表中，最后返回 $\text{true}$。时间复杂度为 $O(1)$。

对于 $\textit{forwardPacket}$ 方法，如果队列为空则返回空数组；否则移除队列头部的数据包，并从 $\textit{vis}$ 中删除其哈希值，更新对应目标地址的已转发数据包数量，最后返回该数据包。时间复杂度为 $O(1)$。

对于 $\textit{getCount}$ 方法，我们获取对应目标地址的时间戳列表和已转发数据包数量，然后使用二分查找找到时间戳在指定范围内的数量，最后返回该数量。时间复杂度为 $O(\log n)$，其中 $n$ 是时间戳列表的长度。

空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Router:

    def __init__(self, memoryLimit: int):
        self.lim = memoryLimit
        self.vis = set()
        self.q = deque()
        self.idx = defaultdict(int)
        self.d = defaultdict(list)

    def addPacket(self, source: int, destination: int, timestamp: int) -> bool:
        x = self.f(source, destination, timestamp)
        if x in self.vis:
            return False
        self.vis.add(x)
        if len(self.q) >= self.lim:
            self.forwardPacket()
        self.q.append((source, destination, timestamp))
        self.d[destination].append(timestamp)
        return True

    def forwardPacket(self) -> List[int]:
        if not self.q:
            return []
        s, d, t = self.q.popleft()
        self.vis.remove(self.f(s, d, t))
        self.idx[d] += 1
        return [s, d, t]

    def f(self, a: int, b: int, c: int) -> int:
        return a << 46 | b << 29 | c

    def getCount(self, destination: int, startTime: int, endTime: int) -> int:
        ls = self.d[destination]
        k = self.idx[destination]
        i = bisect_left(ls, startTime, k)
        j = bisect_left(ls, endTime + 1, k)
        return j - i


# Your Router object will be instantiated and called as such:
# obj = Router(memoryLimit)
# param_1 = obj.addPacket(source,destination,timestamp)
# param_2 = obj.forwardPacket()
# param_3 = obj.getCount(destination,startTime,endTime)
```

#### Java

```java
class Router {
    private int lim;
    private Set<Long> vis = new HashSet<>();
    private Deque<int[]> q = new ArrayDeque<>();
    private Map<Integer, Integer> idx = new HashMap<>();
    private Map<Integer, List<Integer>> d = new HashMap<>();

    public Router(int memoryLimit) {
        this.lim = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        long x = f(source, destination, timestamp);
        if (vis.contains(x)) {
            return false;
        }
        vis.add(x);
        if (q.size() >= lim) {
            forwardPacket();
        }
        q.offer(new int[] {source, destination, timestamp});
        d.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (q.isEmpty()) {
            return new int[] {};
        }
        int[] packet = q.poll();
        int s = packet[0], d_ = packet[1], t = packet[2];
        vis.remove(f(s, d_, t));
        idx.merge(d_, 1, Integer::sum);
        return new int[] {s, d_, t};
    }

    private long f(int a, int b, int c) {
        return ((long) a << 46) | ((long) b << 29) | (long) c;
    }

    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> ls = d.getOrDefault(destination, List.of());
        int k = idx.getOrDefault(destination, 0);
        int i = lowerBound(ls, startTime, k);
        int j = lowerBound(ls, endTime + 1, k);
        return j - i;
    }

    private int lowerBound(List<Integer> list, int target, int fromIndex) {
        int l = fromIndex, r = list.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (list.get(m) < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */
```

#### C++

```cpp
class Router {
private:
    int lim;
    unordered_set<long long> vis;
    deque<array<int, 3>> q;
    unordered_map<int, int> idx;
    unordered_map<int, vector<int>> d;

    long long f(int a, int b, int c) {
        return ((long long) a << 46) | ((long long) b << 29) | (long long) c;
    }

public:
    Router(int memoryLimit) {
        lim = memoryLimit;
    }

    bool addPacket(int source, int destination, int timestamp) {
        long long x = f(source, destination, timestamp);
        if (vis.count(x)) {
            return false;
        }
        vis.insert(x);
        if ((int) q.size() >= lim) {
            forwardPacket();
        }
        q.push_back({source, destination, timestamp});
        d[destination].push_back(timestamp);
        return true;
    }

    vector<int> forwardPacket() {
        if (q.empty()) {
            return {};
        }
        auto packet = q.front();
        q.pop_front();
        int s = packet[0], d_ = packet[1], t = packet[2];
        vis.erase(f(s, d_, t));
        idx[d_]++;
        return {s, d_, t};
    }

    int getCount(int destination, int startTime, int endTime) {
        auto& ls = d[destination];
        int k = idx[destination];
        auto i = lower_bound(ls.begin() + k, ls.end(), startTime);
        auto j = lower_bound(ls.begin() + k, ls.end(), endTime + 1);
        return j - i;
    }
};

/**
 * Your Router object will be instantiated and called as such:
 * Router* obj = new Router(memoryLimit);
 * bool param_1 = obj->addPacket(source,destination,timestamp);
 * vector<int> param_2 = obj->forwardPacket();
 * int param_3 = obj->getCount(destination,startTime,endTime);
 */
```

#### Go

```go
type Router struct {
	lim int
	vis map[int64]struct{}
	q   [][3]int
	idx map[int]int
	d   map[int][]int
}

func Constructor(memoryLimit int) Router {
	return Router{
		lim: memoryLimit,
		vis: make(map[int64]struct{}),
		q:   make([][3]int, 0),
		idx: make(map[int]int),
		d:   make(map[int][]int),
	}
}

func (this *Router) f(a, b, c int) int64 {
	return int64(a)<<46 | int64(b)<<29 | int64(c)
}

func (this *Router) AddPacket(source int, destination int, timestamp int) bool {
	x := this.f(source, destination, timestamp)
	if _, ok := this.vis[x]; ok {
		return false
	}
	this.vis[x] = struct{}{}
	if len(this.q) >= this.lim {
		this.ForwardPacket()
	}
	this.q = append(this.q, [3]int{source, destination, timestamp})
	this.d[destination] = append(this.d[destination], timestamp)
	return true
}

func (this *Router) ForwardPacket() []int {
	if len(this.q) == 0 {
		return []int{}
	}
	packet := this.q[0]
	this.q = this.q[1:]
	s, d, t := packet[0], packet[1], packet[2]
	delete(this.vis, this.f(s, d, t))
	this.idx[d]++
	return []int{s, d, t}
}

func (this *Router) GetCount(destination int, startTime int, endTime int) int {
	ls := this.d[destination]
	k := this.idx[destination]
	i := sort.Search(len(ls)-k, func(i int) bool { return ls[i+k] >= startTime }) + k
	j := sort.Search(len(ls)-k, func(i int) bool { return ls[i+k] >= endTime+1 }) + k
	return j - i
}

/**
 * Your Router object will be instantiated and called as such:
 * obj := Constructor(memoryLimit)
 * param_1 := obj.AddPacket(source,destination,timestamp)
 * param_2 := obj.ForwardPacket()
 * param_3 := obj.GetCount(destination,startTime,endTime)
 */
```

#### TypeScript

```ts
class Router {
    private lim: number;
    private vis: Set<number>;
    private q: [number, number, number][];
    private idx: Map<number, number>;
    private d: Map<number, number[]>;

    constructor(memoryLimit: number) {
        this.lim = memoryLimit;
        this.vis = new Set();
        this.q = [];
        this.idx = new Map();
        this.d = new Map();
    }

    private f(a: number, b: number, c: number): number {
        return ((BigInt(a) << 46n) | (BigInt(b) << 29n) | BigInt(c)) as unknown as number;
    }

    addPacket(source: number, destination: number, timestamp: number): boolean {
        const x = this.f(source, destination, timestamp);
        if (this.vis.has(x)) {
            return false;
        }
        this.vis.add(x);
        if (this.q.length >= this.lim) {
            this.forwardPacket();
        }
        this.q.push([source, destination, timestamp]);
        if (!this.d.has(destination)) {
            this.d.set(destination, []);
        }
        this.d.get(destination)!.push(timestamp);
        return true;
    }

    forwardPacket(): number[] {
        if (this.q.length === 0) {
            return [];
        }
        const [s, d, t] = this.q.shift()!;
        this.vis.delete(this.f(s, d, t));
        this.idx.set(d, (this.idx.get(d) ?? 0) + 1);
        return [s, d, t];
    }

    getCount(destination: number, startTime: number, endTime: number): number {
        const ls = this.d.get(destination) ?? [];
        const k = this.idx.get(destination) ?? 0;
        const i = this.lowerBound(ls, startTime, k);
        const j = this.lowerBound(ls, endTime + 1, k);
        return j - i;
    }

    private lowerBound(arr: number[], target: number, from: number): number {
        let l = from,
            r = arr.length;
        while (l < r) {
            const m = Math.floor((l + r) / 2);
            if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * var obj = new Router(memoryLimit)
 * var param_1 = obj.addPacket(source,destination,timestamp)
 * var param_2 = obj.forwardPacket()
 * var param_3 = obj.getCount(destination,startTime,endTime)
 */
```

#### Rust

```rust
use std::collections::{HashSet, HashMap, VecDeque};

struct Router {
    lim: usize,
    vis: HashSet<i64>,
    q: VecDeque<(i32, i32, i32)>,
    idx: HashMap<i32, usize>,
    d: HashMap<i32, Vec<i32>>,
}

impl Router {
    fn new(memory_limit: i32) -> Self {
        Router {
            lim: memory_limit as usize,
            vis: HashSet::new(),
            q: VecDeque::new(),
            idx: HashMap::new(),
            d: HashMap::new(),
        }
    }

    fn f(a: i32, b: i32, c: i32) -> i64 {
        ((a as i64) << 46) | ((b as i64) << 29) | (c as i64)
    }

    fn add_packet(&mut self, source: i32, destination: i32, timestamp: i32) -> bool {
        let x = Self::f(source, destination, timestamp);
        if self.vis.contains(&x) {
            return false;
        }
        self.vis.insert(x);
        if self.q.len() >= self.lim {
            self.forward_packet();
        }
        self.q.push_back((source, destination, timestamp));
        self.d.entry(destination).or_default().push(timestamp);
        true
    }

    fn forward_packet(&mut self) -> Vec<i32> {
        if let Some((s, d, t)) = self.q.pop_front() {
            self.vis.remove(&Self::f(s, d, t));
            *self.idx.entry(d).or_insert(0) += 1;
            vec![s, d, t]
        } else {
            vec![]
        }
    }

    fn get_count(&self, destination: i32, start_time: i32, end_time: i32) -> i32 {
        let ls = self.d.get(&destination);
        if ls.is_none() {
            return 0;
        }
        let ls = ls.unwrap();
        let k = *self.idx.get(&destination).unwrap_or(&0);
        let i = k + ls[k..].partition_point(|&x| x < start_time);
        let j = k + ls[k..].partition_point(|&x| x < end_time + 1);
        (j - i) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
