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
        self.persons = persons
        n = len(persons)
        win_person = [0] * n
        count = [0] * n
        cur_max = -1
        cur_win = -1
        for i in range(n):
            count[persons[i]] += 1
            if count[persons[i]] >= cur_max:
                cur_win = persons[i]
                cur_max = count[persons[i]]
            win_person[i] = cur_win
        self.win_person = win_person

    def q(self, t: int) -> int:
        times = self.times
        win_person = self.win_person
        left, right = 0, len(times) - 1
        while left < right:
            mid = (left + right + 1) // 2
            if times[mid] <= t:
                left = mid
            else:
                right = mid - 1
        return win_person[left]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class TopVotedCandidate {
    int[] persons;
    int[] times;
    int[] winPerson;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        int len = times.length;
        winPerson = new int[len];
        int max = 0;
        int curWin = -1;
        int[] count = new int[len];
        for (int i = 0; i < len; i++) {
            if (++count[persons[i]] >= max) {
                max = count[persons[i]];
                curWin = persons[i];
            }
            winPerson[i] = curWin;
        }
    }

    public int q(int t) {
        int left = 0, right = times.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return winPerson[left];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
