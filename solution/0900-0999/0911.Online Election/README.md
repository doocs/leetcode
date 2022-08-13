# [911. 在线选举](https://leetcode.cn/problems/online-election)

[English Version](/solution/0900-0999/0911.Online%20Election/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组 <code>persons</code> 和 <code>times</code> 。在选举中，第&nbsp;<code>i</code>&nbsp;张票是在时刻为&nbsp;<code>times[i]</code>&nbsp;时投给候选人 <code>persons[i]</code>&nbsp;的。</p>

<p>对于发生在时刻 <code>t</code> 的每个查询，需要找出在&nbsp;<code>t</code> 时刻在选举中领先的候选人的编号。</p>

<p>在&nbsp;<code>t</code> 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。</p>

<p>实现 <code>TopVotedCandidate</code> 类：</p>

<ul>
	<li><code>TopVotedCandidate(int[] persons, int[] times)</code> 使用&nbsp;<code>persons</code> 和 <code>times</code> 数组初始化对象。</li>
	<li><code>int q(int t)</code> 根据前面描述的规则，返回在时刻 <code>t</code> 在选举中领先的候选人的编号。</li>
</ul>
&nbsp;

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
<strong>输出：</strong>
[null, 0, 1, 1, 0, 0, 1]

<strong>解释：</strong>
TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
topVotedCandidate.q(15); // 返回 0
topVotedCandidate.q(24); // 返回 0
topVotedCandidate.q(8); // 返回 1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= persons.length &lt;= 5000</code></li>
	<li><code>times.length == persons.length</code></li>
	<li><code>0 &lt;= persons[i] &lt; persons.length</code></li>
	<li><code>0 &lt;= times[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>times</code> 是一个严格递增的有序数组</li>
	<li><code>times[0] &lt;= t &lt;= 10<sup>9</sup></code></li>
	<li>每个测试用例最多调用 <code>10<sup>4</sup></code> 次 <code>q</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

先预处理得到每个时刻的领先的候选人编号 `wins[i]`。

然后对于每次查询 q，二分查找得到小于等于 t 时刻的最大时刻 left，返回 `wins[left]` 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
