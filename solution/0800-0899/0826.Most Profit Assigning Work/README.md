# [826. 安排工作以达到最大收益](https://leetcode.cn/problems/most-profit-assigning-work)

[English Version](/solution/0800-0899/0826.Most%20Profit%20Assigning%20Work/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>n</code>&nbsp;个工作和 <code>m</code> 个工人。给定三个数组：&nbsp;<code>difficulty</code>,&nbsp;<code>profit</code>&nbsp;和&nbsp;<code>worker</code>&nbsp;，其中:</p>

<ul>
	<li><code>difficulty[i]</code>&nbsp;表示第 <code>i</code> 个工作的难度，<code>profit[i]</code> 表示第 <code>i</code> 个工作的收益。</li>
	<li><code>worker[i]</code> 是第 <code>i</code> 个工人的能力，即该工人只能完成难度小于等于 <code>worker[i]</code> 的工作。</li>
</ul>

<p>每个工人&nbsp;<strong>最多</strong> 只能安排 <strong>一个</strong> 工作，但是一个工作可以 <strong>完成多次</strong> 。</p>

<ul>
	<li>举个例子，如果 3 个工人都尝试完成一份报酬为 <code>$1</code> 的同样工作，那么总收益为 <code>$3</code>&nbsp;。如果一个工人不能完成任何工作，他的收益为 <code>$0</code> 。</li>
</ul>

<p>返回 <em>在把工人分配到工作岗位后，我们所能获得的最大利润&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
<strong>输出: </strong>100 
<strong>解释: </strong>工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
<strong>输出:</strong> 0</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == difficulty.length</code></li>
	<li><code>n == profit.length</code></li>
	<li><code>m == worker.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= difficulty[i], profit[i], worker[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“排序 + 双指针”。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
