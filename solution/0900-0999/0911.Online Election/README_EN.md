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
<p><strong>Example 1:</strong></p>

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

Binary search.

<!-- tabs:start -->

### **Python3**

```python
class TopVotedCandidate:
    def __init__(self, persons: List[int], times: List[int]):
        mx = cur = 0
        counter = Counter()
        self.times = times
        self.wins = []
        for i, p in enumerate(persons):
            counter[p] += 1
            if counter[p] >= mx:
                mx, cur = counter[p], p
            self.wins.append(cur)

    def q(self, t: int) -> int:
        left, right = 0, len(self.wins) - 1
        while left < right:
            mid = (left + right + 1) >> 1
            if self.times[mid] <= t:
                left = mid
            else:
                right = mid - 1
        return self.wins[left]


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)
```

### **Java**

```java
class TopVotedCandidate {
    private int[] times;
    private int[] wins;

    public TopVotedCandidate(int[] persons, int[] times) {
        int n = persons.length;
        int mx = 0, cur = 0;
        this.times = times;
        wins = new int[n];
        int[] counter = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = persons[i];
            if (++counter[p] >= mx) {
                mx = counter[p];
                cur = p;
            }
            wins[i] = cur;
        }
    }

    public int q(int t) {
        int left = 0, right = wins.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return wins[left];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
```

### **C++**

```cpp
class TopVotedCandidate {
public:
    vector<int> times;
    vector<int> wins;

    TopVotedCandidate(vector<int>& persons, vector<int>& times) {
        int n = persons.size();
        wins.resize(n);
        int mx = 0, cur = 0;
        this->times = times;
        vector<int> counter(n);
        for (int i = 0; i < n; ++i) {
            int p = persons[i];
            if (++counter[p] >= mx) {
                mx = counter[p];
                cur = p;
            }
            wins[i] = cur;
        }
    }

    int q(int t) {
        int left = 0, right = wins.size() - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (times[mid] <= t)
                left = mid;
            else
                right = mid - 1;
        }
        return wins[left];
    }
};

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate* obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj->q(t);
 */
```

### **Go**

```go
type TopVotedCandidate struct {
	times []int
	wins  []int
}

func Constructor(persons []int, times []int) TopVotedCandidate {
	mx, cur, n := 0, 0, len(persons)
	counter := make([]int, n)
	wins := make([]int, n)
	for i, p := range persons {
		counter[p]++
		if counter[p] >= mx {
			mx = counter[p]
			cur = p
		}
		wins[i] = cur
	}
	return TopVotedCandidate{times, wins}
}

func (this *TopVotedCandidate) Q(t int) int {
	left, right := 0, len(this.wins)-1
	for left < right {
		mid := (left + right + 1) >> 1
		if this.times[mid] <= t {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return this.wins[left]
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * obj := Constructor(persons, times);
 * param_1 := obj.Q(t);
 */
```

### **...**

```

```

<!-- tabs:end -->
