---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2102.Sequentially%20Ordinal%20Rank%20Tracker/README.md
rating: 2158
source: 第 67 场双周赛 Q4
tags:
    - 设计
    - 数据流
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [2102. 序列顺序查询](https://leetcode.cn/problems/sequentially-ordinal-rank-tracker)

[English Version](/solution/2100-2199/2102.Sequentially%20Ordinal%20Rank%20Tracker/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个观光景点由它的名字&nbsp;<code>name</code> 和景点评分&nbsp;<code>score</code>&nbsp;组成，其中&nbsp;<code>name</code>&nbsp;是所有观光景点中&nbsp;<strong>唯一</strong>&nbsp;的字符串，<code>score</code>&nbsp;是一个整数。景点按照最好到最坏排序。景点评分 <strong>越高</strong>&nbsp;，这个景点越好。如果有两个景点的评分一样，那么 <strong>字典序较小</strong>&nbsp;的景点更好。</p>

<p>你需要搭建一个系统，查询景点的排名。初始时系统里没有任何景点。这个系统支持：</p>

<ul>
	<li><strong>添加</strong> 景点，每次添加 <strong>一个</strong> 景点。</li>
	<li><strong>查询 </strong>已经添加景点中第&nbsp;<code>i</code>&nbsp;<strong>好</strong>&nbsp;的景点，其中&nbsp;<code>i</code>&nbsp;是系统目前位置查询的次数（包括当前这一次）。
	<ul>
		<li>比方说，如果系统正在进行第 <code>4</code>&nbsp;次查询，那么需要返回所有已经添加景点中第 <code>4</code>&nbsp;好的。</li>
	</ul>
	</li>
</ul>

<p>注意，测试数据保证&nbsp;<strong>任意查询时刻</strong>&nbsp;，查询次数都 <strong>不超过</strong>&nbsp;系统中景点的数目。</p>

<p>请你实现&nbsp;<code>SORTracker</code>&nbsp;类：</p>

<ul>
	<li><code>SORTracker()</code>&nbsp;初始化系统。</li>
	<li><code>void add(string name, int score)</code>&nbsp;向系统中添加一个名为&nbsp;<code>name</code> 评分为&nbsp;<code>score</code>&nbsp;的景点。</li>
	<li><code>string get()</code>&nbsp;查询第 <code>i</code>&nbsp;好的景点，其中 <code>i</code>&nbsp;是目前系统查询的次数（包括当前这次查询）。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["SORTracker", "add", "add", "get", "add", "get", "add", "get", "add", "get", "add", "get", "get"]
[[], ["bradford", 2], ["branford", 3], [], ["alps", 2], [], ["orland", 2], [], ["orlando", 3], [], ["alpine", 2], [], []]
<strong>输出：</strong>
[null, null, null, "branford", null, "alps", null, "bradford", null, "bradford", null, "bradford", "orland"]

<strong>解释：</strong>
SORTracker tracker = new SORTracker(); // 初始化系统
tracker.add("bradford", 2); // 添加 name="bradford" 且 score=2 的景点。
tracker.add("branford", 3); // 添加 name="branford" 且 score=3 的景点。
tracker.get();              // 从好到坏的景点为：branford ，bradford 。
                            // 注意到 branford 比 bradford 好，因为它的 <strong>评分更高</strong> (3 &gt; 2) 。
                            // 这是第 1 次调用 get() ，所以返回最好的景点："branford" 。
tracker.add("alps", 2);     // 添加 name="alps" 且 score=2 的景点。
tracker.get();              // 从好到坏的景点为：branford, alps, bradford 。
                            // 注意 alps 比 bradford 好，虽然它们评分相同，都为 2 。
                            // 这是因为 "alps" <strong>字典序</strong>&nbsp;比 "bradford" 小。
                            // 返回第 2 好的地点 "alps" ，因为当前为第 2 次调用 get() 。
tracker.add("orland", 2);   // 添加 name="orland" 且 score=2 的景点。
tracker.get();              // 从好到坏的景点为：branford, alps, bradford, orland 。
                            // 返回 "bradford" ，因为当前为第 3 次调用 get() 。
tracker.add("orlando", 3);  // 添加 name="orlando" 且 score=3 的景点。
tracker.get();              // 从好到坏的景点为：branford, orlando, alps, bradford, orland 。
                            // 返回 "bradford".
tracker.add("alpine", 2);   // 添加 name="alpine" 且 score=2 的景点。
tracker.get();              // 从好到坏的景点为：branford, orlando, alpine, alps, bradford, orland 。
                            // 返回 "bradford" 。
tracker.get();              // 从好到坏的景点为：branford, orlando, alpine, alps, bradford, orland 。
                            // 返回 "orland" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>name</code>&nbsp;只包含小写英文字母，且每个景点名字互不相同。</li>
	<li><code>1 &lt;= name.length &lt;= 10</code></li>
	<li><code>1 &lt;= score &lt;= 10<sup>5</sup></code></li>
	<li>任意时刻，调用&nbsp;<code>get</code>&nbsp;的次数都不超过调用&nbsp;<code>add</code>&nbsp;的次数。</li>
	<li><strong>总共</strong>&nbsp;调用&nbsp;<code>add</code> 和&nbsp;<code>get</code>&nbsp;不超过&nbsp;<code>4 * 10<sup>4</sup></code>&nbsp;</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序集合

我们可以使用有序集合来存储景点，用一个变量 $i$ 来记录当前查询的次数，初始时 $i = -1$。

调用 `add` 方法时，我们将景点的评分取负数，这样就可以使用有序集合按照评分从大到小排序，如果评分相同，按照景点名字的字典序从小到大排序。

调用 `get` 方法时，我们将 $i$ 加一，然后返回有序集合中第 $i$ 个景点的名字。

每次操作的时间复杂度为 $O(\log n)$，其中 $n$ 为已添加的景点数。空间复杂度为 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
from sortedcontainers import SortedList


class SORTracker:

    def __init__(self):
        self.sl = SortedList()
        self.i = -1

    def add(self, name: str, score: int) -> None:
        self.sl.add((-score, name))

    def get(self) -> str:
        self.i += 1
        return self.sl[self.i][1]


# Your SORTracker object will be instantiated and called as such:
# obj = SORTracker()
# obj.add(name,score)
# param_2 = obj.get()
```

#### C++

```cpp
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/hash_policy.hpp>
using namespace __gnu_pbds;

template <class T>
using ordered_set = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;

class SORTracker {
public:
    SORTracker() {
    }

    void add(string name, int score) {
        st.insert({-score, name});
    }

    string get() {
        return st.find_by_order(++i)->second;
    }

private:
    ordered_set<pair<int, string>> st;
    int i = -1;
};

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker* obj = new SORTracker();
 * obj->add(name,score);
 * string param_2 = obj->get();
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：双优先队列（大小根堆）

我们注意到，由于本题中的查询操作是按照严格递增的顺序进行的，因此我们可以使用类似于数据流中的中位数的方法，定义两个优先队列 `good` 和 `bad`，其中 `good` 是一个小根堆，存储当前最好的景点，`bad` 是一个大根堆，存储当前第 $i$ 好的景点。

每次调用 `add` 方法时，我们将景点的评分和名字加入 `good` 中，然后将 `good` 中的最差的景点加入 `bad` 中。

每次调用 `get` 方法时，我们将 `bad` 中的最好的景点加入 `good` 中，然后返回 `good` 中的最差的景点。

每次操作的时间复杂度为 $O(\log n)$，其中 $n$ 为已添加的景点数。空间复杂度为 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Node:
    def __init__(self, s: str):
        self.s = s

    def __lt__(self, other):
        return self.s > other.s


class SORTracker:

    def __init__(self):
        self.good = []
        self.bad = []

    def add(self, name: str, score: int) -> None:
        score, node = heappushpop(self.good, (score, Node(name)))
        heappush(self.bad, (-score, node.s))

    def get(self) -> str:
        score, name = heappop(self.bad)
        heappush(self.good, (-score, Node(name)))
        return self.good[0][1].s


# Your SORTracker object will be instantiated and called as such:
# obj = SORTracker()
# obj.add(name,score)
# param_2 = obj.get()
```

#### Java

```java
class SORTracker {
    private PriorityQueue<Map.Entry<Integer, String>> good = new PriorityQueue<>(
        (a, b)
            -> a.getKey().equals(b.getKey()) ? b.getValue().compareTo(a.getValue())
                                             : a.getKey() - b.getKey());
    private PriorityQueue<Map.Entry<Integer, String>> bad = new PriorityQueue<>(
        (a, b)
            -> a.getKey().equals(b.getKey()) ? a.getValue().compareTo(b.getValue())
                                             : b.getKey() - a.getKey());

    public SORTracker() {
    }

    public void add(String name, int score) {
        good.offer(Map.entry(score, name));
        bad.offer(good.poll());
    }

    public String get() {
        good.offer(bad.poll());
        return good.peek().getValue();
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */
```

#### C++

```cpp
using pis = pair<int, string>;

class SORTracker {
public:
    SORTracker() {
    }

    void add(string name, int score) {
        good.push({-score, name});
        bad.push(good.top());
        good.pop();
    }

    string get() {
        good.push(bad.top());
        bad.pop();
        return good.top().second;
    }

private:
    priority_queue<pis, vector<pis>, less<pis>> good;
    priority_queue<pis, vector<pis>, greater<pis>> bad;
};

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker* obj = new SORTracker();
 * obj->add(name,score);
 * string param_2 = obj->get();
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
