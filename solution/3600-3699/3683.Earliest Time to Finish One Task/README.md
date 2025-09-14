---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3683.Earliest%20Time%20to%20Finish%20One%20Task/README.md
---

<!-- problem:start -->

# [3683. 完成一个任务的最早时间](https://leetcode.cn/problems/earliest-time-to-finish-one-task)

[English Version](/solution/3600-3699/3683.Earliest%20Time%20to%20Finish%20One%20Task/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>tasks</code>，其中 <code>tasks[i] = [s<sub>i</sub>, t<sub>i</sub>]</code>。</p>

<p>数组中的每个 <code>[s<sub>i</sub>, t<sub>i</sub>]</code> 表示一个任务，该任务的开始时间为 <code>s<sub>i</sub></code>，完成该任务需要 <code>t<sub>i</sub></code> 个时间单位。</p>

<p>返回至少完成一个任务的最早时间。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">tasks = [[1,6],[2,3]]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>第一个任务从时间 <code>t = 1</code> 开始，并在 <code>1 + 6 = 7</code> 时完成。第二个任务在时间 <code>t = 2</code> 开始，并在 <code>2 + 3 = 5</code> 时完成。因此，最早完成的任务在时间 5。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">tasks = [[100,100],[100,100],[100,100]]</span></p>

<p><strong>输出：</strong> <span class="example-io">200</span></p>

<p><strong>解释：</strong></p>

<p>三个任务都在时间 <code>100 + 100 = 200</code> 时完成。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 100</code></li>
	<li><code>tasks[i] = [s<sub>i</sub>, t<sub>i</sub>]</code></li>
	<li><code>1 &lt;= s<sub>i</sub>, t<sub>i</sub> &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们遍历 $\textit{tasks}$ 数组，对于每个任务，计算其完成时间 $s_i + t_i$，求出所有任务完成时间的最小值，即为至少完成一个任务的最早时间。

时间复杂度 $O(n)$，其中 $n$ 为 $\textit{tasks}$ 数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def earliestTime(self, tasks: List[List[int]]) -> int:
        return min(s + t for s, t in tasks)
```

#### Java

```java
class Solution {
    public int earliestTime(int[][] tasks) {
        int ans = 200;
        for (var task : tasks) {
            ans = Math.min(ans, task[0] + task[1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int earliestTime(vector<vector<int>>& tasks) {
        int ans = 200;
        for (const auto& task : tasks) {
            ans = min(ans, task[0] + task[1]);
        }
        return ans;
    }
};
```

#### Go

```go
func earliestTime(tasks [][]int) int {
	ans := 200
	for _, task := range tasks {
		ans = min(ans, task[0]+task[1])
	}
	return ans
}
```

#### TypeScript

```ts
function earliestTime(tasks: number[][]): number {
    return Math.min(...tasks.map(task => task[0] + task[1]));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
