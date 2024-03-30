# [911. 在线选举](https://leetcode.cn/problems/online-election)

[English Version](/solution/0900-0999/0911.Online%20Election/README_EN.md)

<!-- tags:设计,数组,哈希表,二分查找 -->

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

### 方法一：二分查找

我们可以在初始化时，记录每个时刻的胜者，然后在查询时，使用二分查找找到小于等于 $t$ 的最大时刻，返回该时刻的胜者。

初始化时，我们使用一个计数器 $cnt$ 记录每个候选人的票数，用一个变量 $cur$ 记录当前领先的候选人。然后遍历每个时刻，更新 $cnt$ 和 $cur$，并记录每个时刻的胜者。

查询时，我们使用二分查找找到小于等于 $t$ 的最大时刻，返回该时刻的胜者。

时间复杂度方面，初始化时，我们需要 $O(n)$ 的时间，查询时，我们需要 $O(\log n)$ 的时间。空间复杂度为 $O(n)$。

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
