# [1723. Find Minimum Time to Finish All Jobs](https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs)

[中文文档](/solution/1700-1799/1723.Find%20Minimum%20Time%20to%20Finish%20All%20Jobs/README.md)

## Description

<p>You are given an integer array <code>jobs</code>, where <code>jobs[i]</code> is the amount of time it takes to complete the <code>i<sup>th</sup></code> job.</p>

<p>There are <code>k</code> workers that you can assign jobs to. Each job should be assigned to <strong>exactly</strong> one worker. The <strong>working time</strong> of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the <strong>maximum working time</strong> of any worker is <strong>minimized</strong>.</p>

<p><em>Return the <strong>minimum</strong> possible <strong>maximum working time</strong> of any assignment. </em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> jobs = [3,2,3], k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> By assigning each person one job, the maximum time is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> jobs = [1,2,4,7,8], k = 2
<strong>Output:</strong> 11
<strong>Explanation:</strong> Assign the jobs the following way:
Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
Worker 2: 4, 7 (working time = 4 + 7 = 11)
The maximum working time is 11.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= jobs.length &lt;= 12</code></li>
	<li><code>1 &lt;= jobs[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumTimeRequired(self, jobs: List[int], k: int) -> int:
        def dfs(i):
            nonlocal ans
            if i == len(jobs):
                ans = min(ans, max(cnt))
                return
            for j in range(k):
                if cnt[j] + jobs[i] >= ans:
                    continue
                cnt[j] += jobs[i]
                dfs(i + 1)
                cnt[j] -= jobs[i]
                if cnt[j] == 0:
                    break

        cnt = [0] * k
        jobs.sort(reverse=True)
        ans = inf
        dfs(0)
        return ans
```

### **Java**

```java
class Solution {
    private int[] cnt;
    private int ans;
    private int[] jobs;
    private int k;

    public int minimumTimeRequired(int[] jobs, int k) {
        this.k = k;
        Arrays.sort(jobs);
        for (int i = 0, j = jobs.length - 1; i < j; ++i, --j) {
            int t = jobs[i];
            jobs[i] = jobs[j];
            jobs[j] = t;
        }
        this.jobs = jobs;
        cnt = new int[k];
        ans = 0x3f3f3f3f;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == jobs.length) {
            int mx = 0;
            for (int v : cnt) {
                mx = Math.max(mx, v);
            }
            ans = Math.min(ans, mx);
            return;
        }
        for (int j = 0; j < k; ++j) {
            if (cnt[j] + jobs[i] >= ans) {
                continue;
            }
            cnt[j] += jobs[i];
            dfs(i + 1);
            cnt[j] -= jobs[i];
            if (cnt[j] == 0) {
                break;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int ans;

    int minimumTimeRequired(vector<int>& jobs, int k) {
        vector<int> cnt(k);
        ans = 0x3f3f3f3f;
        sort(jobs.begin(), jobs.end(), greater<int>());
        dfs(0, k, jobs, cnt);
        return ans;
    }

    void dfs(int i, int k, vector<int>& jobs, vector<int>& cnt) {
        if (i == jobs.size()) {
            ans = min(ans, *max_element(cnt.begin(), cnt.end()));
            return;
        }
        for (int j = 0; j < k; ++j) {
            if (cnt[j] + jobs[i] >= ans) continue;
            cnt[j] += jobs[i];
            dfs(i + 1, k, jobs, cnt);
            cnt[j] -= jobs[i];
            if (cnt[j] == 0) break;
        }
    }
};
```

### **Go**

```go
func minimumTimeRequired(jobs []int, k int) int {
	cnt := make([]int, k)
	ans := 0x3f3f3f3f
	sort.Slice(jobs, func(i, j int) bool {
		return jobs[i] > jobs[j]
	})
	var dfs func(int)
	dfs = func(i int) {
		if i == len(jobs) {
			mx := 0
			for _, v := range cnt {
				mx = max(mx, v)
			}
			ans = min(ans, mx)
			return
		}
		for j := 0; j < k; j++ {
			if cnt[j]+jobs[i] >= ans {
				continue
			}
			cnt[j] += jobs[i]
			dfs(i + 1)
			cnt[j] -= jobs[i]
			if cnt[j] == 0 {
				break
			}
		}
	}
	dfs(0)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
