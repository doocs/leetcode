---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0911.Online%20Election/README_EN.md
tags:
    - Design
    - Array
    - Hash Table
    - Binary Search
---

# [911. Online Election](https://leetcode.com/problems/online-election)

[中文文档](/solution/0900-0999/0911.Online%20Election/README.md)

## Description

<p>You are given two integer arrays <code>persons</code> and <code>times</code>. In an election, the <code>i<sup>th</sup></code> vote was cast for <code>persons[i]</code> at time <code>times[i]</code>.</p>

<p>For each query at a time <code>t</code>, find the person that was leading the election at time <code>t</code>. Votes cast at time <code>t</code> will count towards our query. In the case of a tie, the most recent vote (among tied candidates) wins.</p>

<p>Implement the <code>TopVotedCandidate</code> class:</p>

<ul>
	<li><code>TopVotedCandidate(int[] persons, int[] times)</code> Initializes the object with the <code>persons</code> and <code>times</code> arrays.</li>
	<li><code>int q(int t)</code> Returns the number of the person that was leading the election at time <code>t</code> according to the mentioned rules.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TopVotedCandidate&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;, &quot;q&quot;]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
<strong>Output</strong>
[null, 0, 1, 1, 0, 0, 1]

<strong>Explanation</strong>
TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
topVotedCandidate.q(25); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
topVotedCandidate.q(15); // return 0
topVotedCandidate.q(24); // return 0
topVotedCandidate.q(8); // return 1

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= persons.length &lt;= 5000</code></li>
	<li><code>times.length == persons.length</code></li>
	<li><code>0 &lt;= persons[i] &lt; persons.length</code></li>
	<li><code>0 &lt;= times[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>times</code> is sorted in a strictly increasing order.</li>
	<li><code>times[0] &lt;= t &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>q</code>.</li>
</ul>

## Solutions

### Solution 1: Binary Search

We can record the winner at each moment during initialization, and then use binary search to find the largest moment less than or equal to $t$ during the query, and return the winner at that moment.

During initialization, we use a counter $cnt$ to record the votes of each candidate, and a variable $cur$ to record the current leading candidate. Then we traverse each moment, update $cnt$ and $cur$, and record the winner at each moment.

During the query, we use binary search to find the largest moment less than or equal to $t$, and return the winner at that moment.

In terms of time complexity, during initialization, we need $O(n)$ time, and during the query, we need $O(\log n)$ time. The space complexity is $O(n)$.

<!-- tabs:start -->

```python
class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        cnt = Counter()
        self.times = times
        self.wins = []
        cur = 0
        for p in persons:
            cnt[p] += 1
            if cnt[cur] <= cnt[p]:
                cur = p
            self.wins.append(cur)

    def q(self, t: int) -> int:
        i = bisect_right(self.times, t) - 1
        return self.wins[i]


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)
```

```java
class TopVotedCandidate {
    private int[] times;
    private int[] wins;

    public TopVotedCandidate(int[] persons, int[] times) {
        int n = persons.length;
        wins = new int[n];
        this.times = times;
        int[] cnt = new int[n];
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            int p = persons[i];
            ++cnt[p];
            if (cnt[cur] <= cnt[p]) {
                cur = p;
            }
            wins[i] = cur;
        }
    }

    public int q(int t) {
        int i = Arrays.binarySearch(times, t + 1);
        i = i < 0 ? -i - 2 : i - 1;
        return wins[i];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
```

```cpp
class TopVotedCandidate {
public:
    TopVotedCandidate(vector<int>& persons, vector<int>& times) {
        int n = persons.size();
        this->times = times;
        wins.resize(n);
        vector<int> cnt(n);
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            int p = persons[i];
            ++cnt[p];
            if (cnt[cur] <= cnt[p]) {
                cur = p;
            }
            wins[i] = cur;
        }
    }

    int q(int t) {
        int i = upper_bound(times.begin(), times.end(), t) - times.begin() - 1;
        return wins[i];
    }

private:
    vector<int> times;
    vector<int> wins;
};

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate* obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj->q(t);
 */
```

```go
type TopVotedCandidate struct {
	times []int
	wins  []int
}

func Constructor(persons []int, times []int) TopVotedCandidate {
	n := len(persons)
	wins := make([]int, n)
	cnt := make([]int, n)
	cur := 0
	for i, p := range persons {
		cnt[p]++
		if cnt[cur] <= cnt[p] {
			cur = p
		}
		wins[i] = cur
	}
	return TopVotedCandidate{times, wins}
}

func (this *TopVotedCandidate) Q(t int) int {
	i := sort.SearchInts(this.times, t+1) - 1
	return this.wins[i]
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * obj := Constructor(persons, times);
 * param_1 := obj.Q(t);
 */
```

```ts
class TopVotedCandidate {
    private times: number[];
    private wins: number[];

    constructor(persons: number[], times: number[]) {
        const n = persons.length;
        this.times = times;
        this.wins = new Array<number>(n).fill(0);
        const cnt: Array<number> = new Array<number>(n).fill(0);
        let cur = 0;
        for (let i = 0; i < n; ++i) {
            const p = persons[i];
            cnt[p]++;
            if (cnt[cur] <= cnt[p]) {
                cur = p;
            }
            this.wins[i] = cur;
        }
    }

    q(t: number): number {
        const search = (t: number): number => {
            let l = 0,
                r = this.times.length;
            while (l < r) {
                const mid = (l + r) >> 1;
                if (this.times[mid] > t) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        const i = search(t) - 1;
        return this.wins[i];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * var obj = new TopVotedCandidate(persons, times)
 * var param_1 = obj.q(t)
 */
```

<!-- tabs:end -->

<!-- end -->
