---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2102.Sequentially%20Ordinal%20Rank%20Tracker/README_EN.md
rating: 2158
source: Biweekly Contest 67 Q4
tags:
    - Design
    - Data Stream
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2102. Sequentially Ordinal Rank Tracker](https://leetcode.com/problems/sequentially-ordinal-rank-tracker)

[中文文档](/solution/2100-2199/2102.Sequentially%20Ordinal%20Rank%20Tracker/README.md)

## Description

<!-- description:start -->

<p>A scenic location is represented by its <code>name</code> and attractiveness <code>score</code>, where <code>name</code> is a <strong>unique</strong> string among all locations and <code>score</code> is an integer. Locations can be ranked from the best to the worst. The <strong>higher</strong> the score, the better the location. If the scores of two locations are equal, then the location with the <strong>lexicographically smaller</strong> name is better.</p>

<p>You are building a system that tracks the ranking of locations with the system initially starting with no locations. It supports:</p>

<ul>
	<li><strong>Adding</strong> scenic locations, <strong>one at a time</strong>.</li>
	<li><strong>Querying</strong> the <code>i<sup>th</sup></code> <strong>best</strong> location of <strong>all locations already added</strong>, where <code>i</code> is the number of times the system has been queried (including the current query).
	<ul>
		<li>For example, when the system is queried for the <code>4<sup>th</sup></code> time, it returns the <code>4<sup>th</sup></code> best location of all locations already added.</li>
	</ul>
	</li>
</ul>

<p>Note that the test data are generated so that <strong>at any time</strong>, the number of queries <strong>does not exceed</strong> the number of locations added to the system.</p>

<p>Implement the <code>SORTracker</code> class:</p>

<ul>
	<li><code>SORTracker()</code> Initializes the tracker system.</li>
	<li><code>void add(string name, int score)</code> Adds a scenic location with <code>name</code> and <code>score</code> to the system.</li>
	<li><code>string get()</code> Queries and returns the <code>i<sup>th</sup></code> best location, where <code>i</code> is the number of times this method has been invoked (including this invocation).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SORTracker&quot;, &quot;add&quot;, &quot;add&quot;, &quot;get&quot;, &quot;add&quot;, &quot;get&quot;, &quot;add&quot;, &quot;get&quot;, &quot;add&quot;, &quot;get&quot;, &quot;add&quot;, &quot;get&quot;, &quot;get&quot;]
[[], [&quot;bradford&quot;, 2], [&quot;branford&quot;, 3], [], [&quot;alps&quot;, 2], [], [&quot;orland&quot;, 2], [], [&quot;orlando&quot;, 3], [], [&quot;alpine&quot;, 2], [], []]
<strong>Output</strong>
[null, null, null, &quot;branford&quot;, null, &quot;alps&quot;, null, &quot;bradford&quot;, null, &quot;bradford&quot;, null, &quot;bradford&quot;, &quot;orland&quot;]

<strong>Explanation</strong>
SORTracker tracker = new SORTracker(); // Initialize the tracker system.
tracker.add(&quot;bradford&quot;, 2); // Add location with name=&quot;bradford&quot; and score=2 to the system.
tracker.add(&quot;branford&quot;, 3); // Add location with name=&quot;branford&quot; and score=3 to the system.
tracker.get();              // The sorted locations, from best to worst, are: branford, bradford.
                            // Note that branford precedes bradford due to its <strong>higher score</strong> (3 &gt; 2).
                            // This is the 1<sup>st</sup> time get() is called, so return the best location: &quot;branford&quot;.
tracker.add(&quot;alps&quot;, 2);     // Add location with name=&quot;alps&quot; and score=2 to the system.
tracker.get();              // Sorted locations: branford, alps, bradford.
                            // Note that alps precedes bradford even though they have the same score (2).
                            // This is because &quot;alps&quot; is <strong>lexicographically smaller</strong> than &quot;bradford&quot;.
                            // Return the 2<sup>nd</sup> best location &quot;alps&quot;, as it is the 2<sup>nd</sup> time get() is called.
tracker.add(&quot;orland&quot;, 2);   // Add location with name=&quot;orland&quot; and score=2 to the system.
tracker.get();              // Sorted locations: branford, alps, bradford, orland.
                            // Return &quot;bradford&quot;, as it is the 3<sup>rd</sup> time get() is called.
tracker.add(&quot;orlando&quot;, 3);  // Add location with name=&quot;orlando&quot; and score=3 to the system.
tracker.get();              // Sorted locations: branford, orlando, alps, bradford, orland.
                            // Return &quot;bradford&quot;.
tracker.add(&quot;alpine&quot;, 2);   // Add location with name=&quot;alpine&quot; and score=2 to the system.
tracker.get();              // Sorted locations: branford, orlando, alpine, alps, bradford, orland.
                            // Return &quot;bradford&quot;.
tracker.get();              // Sorted locations: branford, orlando, alpine, alps, bradford, orland.
                            // Return &quot;orland&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>name</code> consists of lowercase English letters, and is unique among all locations.</li>
	<li><code>1 &lt;= name.length &lt;= 10</code></li>
	<li><code>1 &lt;= score &lt;= 10<sup>5</sup></code></li>
	<li>At any time, the number of calls to <code>get</code> does not exceed the number of calls to <code>add</code>.</li>
	<li>At most <code>4 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>add</code> and <code>get</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Ordered Set

We can use an ordered set to store the attractions, and a variable $i$ to record the current number of queries, initially $i = -1$.

When calling the `add` method, we take the negative of the attraction's rating, so that we can use the ordered set to sort by rating in descending order. If the ratings are the same, sort by the dictionary order of the attraction names in ascending order.

When calling the `get` method, we increment $i$ by one, and then return the name of the $i$-th attraction in the ordered set.

The time complexity of each operation is $O(\log n)$, where $n$ is the number of added attractions. The space complexity is $O(n)$.

<!-- tabs:start -->

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

### Solution 2: Double Priority Queue (Min-Max Heap)

We notice that the query operations in this problem are performed in strictly increasing order. Therefore, we can use a method similar to the median in the data stream. We define two priority queues `good` and `bad`. `good` is a min-heap, storing the current best attractions, and `bad` is a max-heap, storing the current $i$-th best attraction.

Each time the `add` method is called, we add the attraction's rating and name to `good`, and then add the worst attraction in `good` to `bad`.

Each time the `get` method is called, we add the best attraction in `bad` to `good`, and then return the worst attraction in `good`.

The time complexity of each operation is $O(\log n)$, where $n$ is the number of added attractions. The space complexity is $O(n)$.

<!-- tabs:start -->

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
