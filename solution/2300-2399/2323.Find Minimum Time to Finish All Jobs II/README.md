# [2323. Find Minimum Time to Finish All Jobs II](https://leetcode.cn/problems/find-minimum-time-to-finish-all-jobs-ii)

[English Version](/solution/2300-2399/2323.Find%20Minimum%20Time%20to%20Finish%20All%20Jobs%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given two <strong>0-indexed</strong> integer arrays <code>jobs</code> and <code>workers</code> of <strong>equal</strong> length, where <code>jobs[i]</code> is the amount of time needed to complete the <code>i<sup>th</sup></code> job, and <code>workers[j]</code> is the amount of time the <code>j<sup>th</sup></code> worker can work each day.</p>

<p>Each job should be assigned to <strong>exactly</strong> one worker, such that each worker completes <strong>exactly</strong> one job.</p>

<p>Return <em>the <strong>minimum</strong> number of days needed to complete all the jobs after assignment.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> jobs = [5,2,4], workers = [1,7,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- Assign the 2<sup>nd</sup> worker to the 0<sup>th</sup> job. It takes them 1 day to finish the job.
- Assign the 0<sup>th</sup> worker to the 1<sup>st</sup> job. It takes them 2 days to finish the job.
- Assign the 1<sup>st</sup> worker to the 2<sup>nd</sup> job. It takes them 1 day to finish the job.
It takes 2 days for all the jobs to be completed, so return 2.
It can be proven that 2 days is the minimum number of days needed.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> jobs = [3,18,15,9], workers = [6,5,1,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
- Assign the 2<sup>nd</sup> worker to the 0<sup>th</sup> job. It takes them 3 days to finish the job.
- Assign the 0<sup>th</sup> worker to the 1<sup>st</sup> job. It takes them 3 days to finish the job.
- Assign the 1<sup>st</sup> worker to the 2<sup>nd</sup> job. It takes them 3 days to finish the job.
- Assign the 3<sup>rd</sup> worker to the 3<sup>rd</sup> job. It takes them 3 days to finish the job.
It takes 3 days for all the jobs to be completed, so return 3.
It can be proven that 3 days is the minimum number of days needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == jobs.length == workers.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= jobs[i], workers[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumTime(self, jobs: List[int], workers: List[int]) -> int:
        jobs.sort()
        workers.sort()
        return max((a + b - 1) // b for a, b in zip(jobs, workers))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumTime(int[] jobs, int[] workers) {
        Arrays.sort(jobs);
        Arrays.sort(workers);
        int ans = 0;
        for (int i = 0; i < jobs.length; ++i) {
            ans = Math.max(ans, (jobs[i] + workers[i] - 1) / workers[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumTime(vector<int>& jobs, vector<int>& workers) {
        sort(jobs.begin(), jobs.end());
        sort(workers.begin(), workers.end());
        int ans = 0;
        for (int i = 0; i < jobs.size(); ++i) ans = max(ans, (jobs[i] + workers[i] - 1) / workers[i]);
        return ans;
    }
};
```

### **Go**

```go
func minimumTime(jobs []int, workers []int) int {
	sort.Ints(jobs)
	sort.Ints(workers)
	ans := 0
	for i, a := range jobs {
		b := workers[i]
		ans = max(ans, (a+b-1)/b)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
