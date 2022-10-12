# [826. Most Profit Assigning Work](https://leetcode.com/problems/most-profit-assigning-work)

[中文文档](/solution/0800-0899/0826.Most%20Profit%20Assigning%20Work/README.md)

## Description

<p>You have <code>n</code> jobs and <code>m</code> workers. You are given three arrays: <code>difficulty</code>, <code>profit</code>, and <code>worker</code> where:</p>

<ul>
	<li><code>difficulty[i]</code> and <code>profit[i]</code> are the difficulty and the profit of the <code>i<sup>th</sup></code> job, and</li>
	<li><code>worker[j]</code> is the ability of <code>j<sup>th</sup></code> worker (i.e., the <code>j<sup>th</sup></code> worker can only complete a job with difficulty at most <code>worker[j]</code>).</li>
</ul>

<p>Every worker can be assigned <strong>at most one job</strong>, but one job can be <strong>completed multiple times</strong>.</p>

<ul>
	<li>For example, if three workers attempt the same job that pays <code>$1</code>, then the total profit will be <code>$3</code>. If a worker cannot complete any job, their profit is <code>$0</code>.</li>
</ul>

<p>Return the maximum profit we can achieve after assigning the workers to the jobs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
<strong>Output:</strong> 100
<strong>Explanation:</strong> Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == difficulty.length</code></li>
	<li><code>n == profit.length</code></li>
	<li><code>m == worker.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= difficulty[i], profit[i], worker[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfitAssignment(
        self, difficulty: List[int], profit: List[int], worker: List[int]
    ) -> int:
        n = len(difficulty)
        job = [(difficulty[i], profit[i]) for i in range(n)]
        job.sort(key=lambda x: x[0])
        worker.sort()
        i = t = res = 0
        for w in worker:
            while i < n and job[i][0] <= w:
                t = max(t, job[i][1])
                i += 1
            res += t
        return res
```

### **Java**

```java
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        List<int[]> job = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            job.add(new int[] {difficulty[i], profit[i]});
        }
        job.sort(Comparator.comparing(a -> a[0]));
        Arrays.sort(worker);
        int res = 0;
        int i = 0, t = 0;
        for (int w : worker) {
            while (i < n && job.get(i)[0] <= w) {
                t = Math.max(t, job.get(i++)[1]);
            }
            res += t;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProfitAssignment(vector<int>& difficulty, vector<int>& profit, vector<int>& worker) {
        int n = difficulty.size();
        vector<pair<int, int>> job;
        for (int i = 0; i < n; ++i) {
            job.push_back({difficulty[i], profit[i]});
        }
        sort(job.begin(), job.end());
        sort(worker.begin(), worker.end());
        int i = 0, t = 0;
        int res = 0;
        for (auto w : worker) {
            while (i < n && job[i].first <= w) {
                t = max(t, job[i++].second);
            }
            res += t;
        }
        return res;
    }
};
```

### **Go**

```go
func maxProfitAssignment(difficulty []int, profit []int, worker []int) int {
	var job [][2]int
	for i := range difficulty {
		job = append(job, [2]int{difficulty[i], profit[i]})
	}

	sort.SliceStable(job, func(i, j int) bool { return job[i][0] <= job[j][0] })
	sort.Ints(worker)
	i, t, n, res := 0, 0, len(difficulty), 0
	for _, w := range worker {
		for i < n && job[i][0] <= w {
			t = max(t, job[i][1])
			i++
		}
		res += t
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
