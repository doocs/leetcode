# [826. Most Profit Assigning Work](https://leetcode.com/problems/most-profit-assigning-work)

[中文文档](/solution/0800-0899/0826.Most%20Profit%20Assigning%20Work/README.md)

## Description

<p>We have jobs: <code>difficulty[i]</code>&nbsp;is the difficulty of the&nbsp;<code>i</code>th job, and&nbsp;<code>profit[i]</code>&nbsp;is the profit of the&nbsp;<code>i</code>th job.&nbsp;</p>



<p>Now we have some workers.&nbsp;<code>worker[i]</code>&nbsp;is the ability of the&nbsp;<code>i</code>th worker, which means that this worker can only complete a job with difficulty at most&nbsp;<code>worker[i]</code>.&nbsp;</p>



<p>Every worker can be assigned at most one job, but one job&nbsp;can be completed multiple times.</p>



<p>For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.&nbsp; If a worker cannot complete any job, his profit is $0.</p>



<p>What is the most profit we can make?</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]

<strong>Output: </strong>100 

<strong>Explanation: W</strong>orkers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.</pre>



<p><strong>Notes:</strong></p>



<ul>
	<li><code>1 &lt;= difficulty.length = profit.length &lt;= 10000</code></li>
	<li><code>1 &lt;= worker.length &lt;= 10000</code></li>
	<li><code>difficulty[i], profit[i], worker[i]</code>&nbsp; are in range&nbsp;<code>[1, 10^5]</code></li>
</ul>



## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfitAssignment(self, difficulty: List[int], profit: List[int], worker: List[int]) -> int:
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
            job.add(new int[]{difficulty[i], profit[i]});
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
    int maxProfitAssignment(vector<int> &difficulty, vector<int> &profit, vector<int> &worker) {
        int n = difficulty.size();
        vector<pair<int, int>> job;
        for (int i = 0; i < n; ++i)
        {
            job.push_back({difficulty[i], profit[i]});
        }
        sort(job.begin(), job.end());
        sort(worker.begin(), worker.end());
        int i = 0, t = 0;
        int res = 0;
        for (auto w : worker)
        {
            while (i < n && job[i].first <= w)
            {
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
