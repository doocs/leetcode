# [911. 在线选举](https://leetcode-cn.com/problems/online-election)

[English Version](/solution/0900-0999/0911.Online%20Election/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在选举中，第&nbsp;<code>i</code>&nbsp;张票是在时间为&nbsp;<code>times[i]</code>&nbsp;时投给&nbsp;<code>persons[i]</code>&nbsp;的。</p>

<p>现在，我们想要实现下面的查询函数： <code>TopVotedCandidate.q(int t)</code> 将返回在&nbsp;<code>t</code> 时刻主导选举的候选人的编号。</p>

<p>在&nbsp;<code>t</code> 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[&quot;TopVotedCandidate&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;,&quot;q&quot;], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
<strong>输出：</strong>[null,0,1,1,0,0,1]
<strong>解释：</strong>
时间为 3，票数分布情况是 [0]，编号为 0 的候选人领先。
时间为 12，票数分布情况是 [0,1,1]，编号为 1 的候选人领先。
时间为 25，票数分布情况是 [0,1,1,0,0,1]，编号为 1 的候选人领先（因为最近的投票结果是平局）。
在时间 15、24 和 8 处继续执行 3 个查询。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= persons.length = times.length &lt;= 5000</code></li>
	<li><code>0 &lt;= persons[i] &lt;= persons.length</code></li>
	<li><code>times</code>&nbsp;是严格递增的数组，所有元素都在&nbsp;<code>[0, 10^9]</code>&nbsp;范围中。</li>
	<li>每个测试用例最多调用&nbsp;<code>10000</code>&nbsp;次&nbsp;<code>TopVotedCandidate.q</code>。</li>
	<li><code>TopVotedCandidate.q(int t)</code>&nbsp;被调用时总是满足&nbsp;<code>t &gt;= times[0]</code>。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
