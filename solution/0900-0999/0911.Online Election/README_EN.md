# [911. Online Election](https://leetcode.com/problems/online-election)

[中文文档](/solution/0900-0999/0911.Online%20Election/README.md)

## Description

<p>In an election, the <code>i</code>-th&nbsp;vote was cast for <code>persons[i]</code> at time <code>times[i]</code>.</p>

<p>Now, we would like to implement the following query function: <code>TopVotedCandidate.q(int t)</code> will return the number of the person that was leading the election at time <code>t</code>.&nbsp;&nbsp;</p>

<p>Votes cast at time <code>t</code> will count towards our query.&nbsp; In the case of a tie, the most recent vote (among tied candidates) wins.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;TopVotedCandidate&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;]</span>, <span id="example-input-1-2">[[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]</span>

<strong>Output: </strong><span id="example-output-1">[null,0,1,1,0,0,1]</span>

<strong>Explanation: </strong>

At time 3, the votes are [0], and 0 is leading.

At time 12, the votes are [0,1,1], and 1 is leading.

At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)

This continues for 3 more queries at time 15, 24, and 8.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= persons.length = times.length &lt;= 5000</code></li>
	<li><code>0 &lt;= persons[i] &lt;= persons.length</code></li>
	<li><code>times</code>&nbsp;is a strictly increasing array with all elements in <code>[0, 10^9]</code>.</li>
	<li><code>TopVotedCandidate.q</code> is called at most <code>10000</code> times per test case.</li>
	<li><code>TopVotedCandidate.q(int t)</code> is always called with <code>t &gt;= times[0]</code>.</li>
</ol>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        self.times = times
        mx, cur_win, n = -1, -1, len(persons)
        counter = [0] * (n + 1)
        self.win_persons = [0] * n
        for i, p in enumerate(persons):
            counter[p] += 1
            if counter[p] >= mx:
                mx = counter[p]
                cur_win = p
            self.win_persons[i] = cur_win

    def q(self, t: int) -> int:
        left, right = 0, len(self.win_persons) - 1
        while left < right:
            mid = (left + right + 1) >> 1
            if self.times[mid] <= t:
                left = mid
            else:
                right = mid - 1
        return self.win_persons[left]

# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)
```

### **Java**

```java
class TopVotedCandidate {
    private int[] times;
    private int[] winPersons;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int mx = -1, curWin = -1;
        int n = persons.length;
        int[] counter = new int[n + 1];
        winPersons = new int[n];
        for (int i = 0; i < n; ++i) {
            if (++counter[persons[i]] >= mx) {
                mx = counter[persons[i]];
                curWin = persons[i];
            }
            winPersons[i] = curWin;
        }
    }

    public int q(int t) {
        int left = 0, right = winPersons.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return winPersons[left];
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
    vector<int> winPersons;

    TopVotedCandidate(vector<int>& persons, vector<int>& times) {
        this->times = times;
        int mx = -1, curWin = -1;
        int n = persons.size();
        vector<int> counter(n + 1);
        winPersons.resize(n);
        for (int i = 0; i < n; ++i)
        {
            if (++counter[persons[i]] >= mx)
            {
                mx = counter[persons[i]];
                curWin = persons[i];
            }
            winPersons[i] = curWin;
        }

    }

    int q(int t) {
        int left = 0, right = winPersons.size() - 1;
        while (left < right)
        {
            int mid = (left + right + 1) >> 1;
            if (times[mid] <= t) left = mid;
            else right = mid - 1;
        }
        return winPersons[left];
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
	times      []int
	winPersons []int
}

func Constructor(persons []int, times []int) TopVotedCandidate {
	mx, curWin, n := -1, -1, len(persons)
	counter := make([]int, n+1)
	winPersons := make([]int, n)
	for i, p := range persons {
		counter[p]++
		if counter[p] >= mx {
			mx = counter[p]
			curWin = p
		}
		winPersons[i] = curWin
	}
	return TopVotedCandidate{
		times, winPersons,
	}
}

func (this *TopVotedCandidate) Q(t int) int {
	left, right := 0, len(this.winPersons)-1
	for left < right {
		mid := (left + right + 1) >> 1
		if this.times[mid] <= t {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return this.winPersons[left]
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
