---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3709.Design%20Exam%20Scores%20Tracker/README_EN.md
rating: 1647
source: Biweekly Contest 167 Q3
tags:
    - Design
    - Array
    - Binary Search
    - Prefix Sum
---

<!-- problem:start -->

# [3709. Design Exam Scores Tracker](https://leetcode.com/problems/design-exam-scores-tracker)

[中文文档](/solution/3700-3799/3709.Design%20Exam%20Scores%20Tracker/README.md)

## Description

<!-- description:start -->

<p>Alice frequently takes exams and wants to track her scores and calculate the total scores over specific time periods.</p>

<p>Implement the <code>ExamTracker</code> class:</p>

<ul>
	<li><code>ExamTracker()</code>: Initializes the <code>ExamTracker</code> object.</li>
	<li><code>void record(int time, int score)</code>: Alice takes a new exam at time <code>time</code> and achieves the score <code>score</code>.</li>
	<li><code>long long totalScore(int startTime, int endTime)</code>: Returns an integer that represents the <strong>total</strong> score of all exams taken by Alice between <code>startTime</code> and <code>endTime</code> (inclusive). If there are no recorded exams taken by Alice within the specified time interval, return 0.</li>
</ul>

<p>It is guaranteed that the function calls are made in chronological order. That is,</p>

<ul>
	<li>Calls to <code>record()</code> will be made with <strong>strictly increasing</strong> <code>time</code>.</li>
	<li>Alice will never ask for total scores that require information from the future. That is, if the latest <code>record()</code> is called with <code>time = t</code>, then <code>totalScore()</code> will always be called with <code>startTime &lt;= endTime &lt;= t</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;ExamTracker&quot;, &quot;record&quot;, &quot;totalScore&quot;, &quot;record&quot;, &quot;totalScore&quot;, &quot;totalScore&quot;, &quot;totalScore&quot;, &quot;totalScore&quot;]<br />
[[], [1, 98], [1, 1], [5, 99], [1, 3], [1, 5], [3, 4], [2, 5]]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, 98, null, 98, 197, 0, 99] </span></p>

<p><strong>Explanation</strong></p>
ExamTracker examTracker = new ExamTracker();<br />
examTracker.record(1, 98); // Alice takes a new exam at time 1, scoring 98.<br />
examTracker.totalScore(1, 1); // Between time 1 and time 1, Alice took 1 exam at time 1, scoring 98. The total score is 98.<br />
examTracker.record(5, 99); // Alice takes a new exam at time 5, scoring 99.<br />
examTracker.totalScore(1, 3); // Between time 1 and time 3, Alice took 1 exam at time 1, scoring 98. The total score is 98.<br />
examTracker.totalScore(1, 5); // Between time 1 and time 5, Alice took 2 exams at time 1 and 5, scoring 98 and 99. The total score is <code>98 + 99 = 197</code>.<br />
examTracker.totalScore(3, 4); // Alice did not take any exam between time 3 and time 4. Therefore, the answer is 0.<br />
examTracker.totalScore(2, 5); // Between time 2 and time 5, Alice took 1 exam at time 5, scoring 99. The total score is 99.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= time &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= score &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= startTime &lt;= endTime &lt;= t</code>, where <code>t</code> is the value of <code>time</code> from the most recent call of <code>record()</code>.</li>
	<li>Calls of <code>record()</code> will be made with <strong>strictly increasing</strong> <code>time</code>.</li>
	<li>After <code>ExamTracker()</code>, the first function call will always be <code>record()</code>.</li>
	<li>At most <code>10<sup>5</sup></code> calls will be made in total to <code>record()</code> and <code>totalScore()</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Binary Search

We use an array $\textit{times}$ to store the time points of each exam, and another array $\textit{pre}$ to store the prefix sums, where $\textit{pre}[i]$ represents the total score of the first $i$ exams. For each call to $\texttt{record}(time, score)$, we add $time$ to $\textit{times}$ and add the last element of $\textit{pre}$ plus $score$ to $\textit{pre}$.

For each call to $\texttt{totalScore}(startTime, endTime)$, we use binary search to find the first position $l$ in $\textit{times}$ that is greater than or equal to $startTime$ and the first position $r$ that is greater than $endTime$, then return $\textit{pre}[r-1] - \textit{pre}[l-1]$.

The time complexity is $O(\log n)$, where $n$ is the number of exams. The space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class ExamTracker:

    def __init__(self):
        self.times = [0]
        self.pre = [0]

    def record(self, time: int, score: int) -> None:
        self.times.append(time)
        self.pre.append(self.pre[-1] + score)

    def totalScore(self, startTime: int, endTime: int) -> int:
        l = bisect_left(self.times, startTime) - 1
        r = bisect_left(self.times, endTime + 1) - 1
        return self.pre[r] - self.pre[l]


# Your ExamTracker object will be instantiated and called as such:
# obj = ExamTracker()
# obj.record(time,score)
# param_2 = obj.totalScore(startTime,endTime)
```

#### Java

```java
class ExamTracker {
    private List<Integer> times = new ArrayList<>();
    private List<Long> pre = new ArrayList<>();

    public ExamTracker() {
        times.add(0);
        pre.add(0L);
    }

    public void record(int time, int score) {
        times.add(time);
        pre.add(pre.getLast() + score);
    }

    public long totalScore(int startTime, int endTime) {
        int l = binarySearch(startTime) - 1;
        int r = binarySearch(endTime + 1) - 1;
        return pre.get(r) - pre.get(l);
    }

    private int binarySearch(int x) {
        int l = 0, r = times.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (times.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */
```

#### C++

```cpp
class ExamTracker {
public:
    ExamTracker() {
        times.push_back(0);
        pre.push_back(0LL);
    }

    void record(int time, int score) {
        times.push_back(time);
        pre.push_back(pre.back() + score);
    }

    long long totalScore(int startTime, int endTime) {
        int l = lower_bound(times.begin(), times.end(), startTime) - times.begin() - 1;
        int r = lower_bound(times.begin(), times.end(), endTime + 1) - times.begin() - 1;
        return pre[r] - pre[l];
    }

private:
    vector<int> times;
    vector<long long> pre;
};

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker* obj = new ExamTracker();
 * obj->record(time,score);
 * long long param_2 = obj->totalScore(startTime,endTime);
 */
```

#### Go

```go
type ExamTracker struct {
	times []int
	pre   []int64
}

func Constructor() ExamTracker {
	return ExamTracker{[]int{0}, []int64{int64(0)}}
}

func (this *ExamTracker) Record(time int, score int) {
	this.times = append(this.times, time)
	this.pre = append(this.pre, this.pre[len(this.pre)-1]+int64(score))
}

func (this *ExamTracker) TotalScore(startTime int, endTime int) int64 {
	l := sort.SearchInts(this.times, startTime) - 1
	r := sort.SearchInts(this.times, endTime+1) - 1
	return this.pre[r] - this.pre[l]
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Record(time,score);
 * param_2 := obj.TotalScore(startTime,endTime);
 */
```

#### TypeScript

```ts
class ExamTracker {
    private times: number[] = [0];
    private pre: number[] = [0];
    constructor() {}

    record(time: number, score: number): void {
        this.times.push(time);
        this.pre.push(this.pre.at(-1)! + score);
    }

    totalScore(startTime: number, endTime: number): number {
        const l = _.sortedIndex(this.times, startTime) - 1;
        const r = _.sortedIndex(this.times, endTime + 1) - 1;
        return this.pre[r] - this.pre[l];
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * var obj = new ExamTracker()
 * obj.record(time,score)
 * var param_2 = obj.totalScore(startTime,endTime)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
