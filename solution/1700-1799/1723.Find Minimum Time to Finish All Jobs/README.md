# [1723. 完成所有工作的最短时间](https://leetcode.cn/problems/find-minimum-time-to-finish-all-jobs)

[English Version](/solution/1700-1799/1723.Find%20Minimum%20Time%20to%20Finish%20All%20Jobs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>jobs</code> ，其中 <code>jobs[i]</code> 是完成第 <code>i</code> 项工作要花费的时间。</p>

<p>请你将这些工作分配给 <code>k</code> 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 <strong>工作时间</strong> 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 <strong>最大工作时间</strong> 得以 <strong>最小化</strong> 。</p>

<p>返回分配方案中尽可能 <strong>最小</strong> 的 <strong>最大工作时间</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>jobs = [3,2,3], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>给每位工人分配一项工作，最大工作时间是 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>jobs = [1,2,4,7,8], k = 2
<strong>输出：</strong>11
<strong>解释：</strong>按下述方式分配工作：
1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
2 号工人：4、7（工作时间 = 4 + 7 = 11）
最大工作时间是 11 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= jobs.length <= 12</code></li>
	<li><code>1 <= jobs[i] <= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS + 剪枝**

本题与 [2305. 公平分发饼干](/solution/2300-2399/2305.Fair%20Distribution%20of%20Cookies/README.md) 基本一致，不同的地方仅在于 $k$ 值的大小。

剪枝优化：优化分配花费时间较大的工作，因此可以先对 $jobs$ 按照降序排列。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
