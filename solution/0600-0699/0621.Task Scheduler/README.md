---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0621.Task%20Scheduler/README.md
tags:
    - 贪心
    - 数组
    - 哈希表
    - 计数
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [621. 任务调度器](https://leetcode.cn/problems/task-scheduler)

[English Version](/solution/0600-0699/0621.Task%20Scheduler/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个用字符数组&nbsp;<code>tasks</code> 表示的 CPU 需要执行的任务列表，用字母 A 到 Z 表示，以及一个冷却时间 <code>n</code>。每个周期或时间间隔允许完成一项任务。任务可以按任何顺序完成，但有一个限制：两个<strong> 相同种类</strong> 的任务之间必须有长度为<strong>&nbsp;</strong><code>n</code><strong> </strong>的冷却时间。</p>

<p>返回完成所有任务所需要的<strong> 最短时间间隔</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>tasks = ["A","A","A","B","B","B"], n = 2
<strong>输出：</strong>8
<strong>解释：</strong>A -&gt; B -&gt; (待命) -&gt; A -&gt; B -&gt; (待命) -&gt; A -&gt; B
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 </pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>tasks = ["A","A","A","B","B","B"], n = 0
<strong>输出：</strong>6
<strong>解释：</strong>在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
诸如此类
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
<strong>输出：</strong>16
<strong>解释：</strong>一种可能的解决方案是：
     A -&gt; B -&gt; C -&gt; A -&gt; D -&gt; E -&gt; A -&gt; F -&gt; G -&gt; A -&gt; (待命) -&gt; (待命) -&gt; A -&gt; (待命) -&gt; (待命) -&gt; A
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>4</sup></code></li>
	<li><code>tasks[i]</code> 是大写英文字母</li>
	<li><code>0 &lt;= n &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 构造

不妨设 $m$ 是任务的个数，统计每种任务出现的次数，记录在数组 `cnt` 中。

假设出现次数最多的任务为 `A`，出现次数为 $x$，则至少需要 $(x-1)\times(n+1) + 1$ 个时间单位才能安排完所有任务。如果出现次数最多的任务有 $s$ 个，则需要再加上出现次数最多的任务的个数。

答案是 $\max ((x-1) \times(n+1)+s, m)$。

时间复杂度 $O(m+|\Sigma|)$。其中 $|\Sigma|$ 是任务的种类数。

<!-- tabs:start -->

```python
class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        cnt = Counter(tasks)
        x = max(cnt.values())
        s = sum(v == x for v in cnt.values())
        return max(len(tasks), (x - 1) * (n + 1) + s)
```

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int x = 0;
        for (char c : tasks) {
            c -= 'A';
            ++cnt[c];
            x = Math.max(x, cnt[c]);
        }
        int s = 0;
        for (int v : cnt) {
            if (v == x) {
                ++s;
            }
        }
        return Math.max(tasks.length, (x - 1) * (n + 1) + s);
    }
}
```

```cpp
class Solution {
public:
    int leastInterval(vector<char>& tasks, int n) {
        vector<int> cnt(26);
        int x = 0;
        for (char c : tasks) {
            c -= 'A';
            ++cnt[c];
            x = max(x, cnt[c]);
        }
        int s = 0;
        for (int v : cnt) {
            s += v == x;
        }
        return max((int) tasks.size(), (x - 1) * (n + 1) + s);
    }
};
```

```go
func leastInterval(tasks []byte, n int) int {
	cnt := make([]int, 26)
	x := 0
	for _, c := range tasks {
		c -= 'A'
		cnt[c]++
		x = max(x, cnt[c])
	}
	s := 0
	for _, v := range cnt {
		if v == x {
			s++
		}
	}
	return max(len(tasks), (x-1)*(n+1)+s)
}
```

```cs
public class Solution {
    public int LeastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int x = 0;
        foreach (char c in tasks) {
            cnt[c - 'A']++;
            x = Math.Max(x, cnt[c - 'A']);
        }
        int s = 0;
        foreach (int v in cnt) {
            s = v == x ? s + 1 : s;
        }
        return Math.Max(tasks.Length, (x - 1) * (n + 1) + s);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
