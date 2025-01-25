---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2323.Find%20Minimum%20Time%20to%20Finish%20All%20Jobs%20II/README.md
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [2323. 完成所有工作的最短时间 II 🔒](https://leetcode.cn/problems/find-minimum-time-to-finish-all-jobs-ii)

[English Version](/solution/2300-2399/2323.Find%20Minimum%20Time%20to%20Finish%20All%20Jobs%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个 <b>下标从 0 开始&nbsp;</b>的整数数组 <code>jobs</code> 和&nbsp;<strong>相等&nbsp;</strong>长度的 <code>workers</code> ，其中&nbsp;<code>jobs[i]</code>是完成第 <code>i</code> 个工作所需的时间，<code>workers[j]</code>&nbsp;是第 <code>j</code> 个工人每天可以工作的时间。</p>

<p>每项工作都应该 <strong>正好</strong> 分配给一个工人，这样每个工人就&nbsp;<strong>只能&nbsp;</strong>完成一项工作。</p>

<p>返回<em>分配后完成所有作业所需的最少天数。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> jobs = [5,2,4], workers = [1,7,5]
<strong>输出:</strong> 2
<strong>解释:</strong>
- 把第 2 个工人分配到第 0 个工作。他们花了 1 天时间完成这项工作。
- 把第 0 个工人分配到第 1 个工作。他们花了 2 天时间完成这项工作。
- 把第 1 个工人分配到第 2 个工作。他们花了 1 天时间完成这项工作。
所有工作完成需要 2 天，因此返回 2。
可以证明 2 天是最少需要的天数。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> jobs = [3,18,15,9], workers = [6,5,1,3]
<strong>输出:</strong> 3
<strong>解释:</strong>
- 把第 2 个工人分配到第 0 个工作。他花了 3 天时间完成这项工作。
- 把第 0 个工人分配到第 1 个工作。他花了 3 天时间完成这项工作。
- 把第 1 个工人分配到第 2 个工作。他花了 3 天时间完成这项工作。
- 把第 3 个工人分配到第 3 个工作。他花了 3 天时间完成这项工作。
完成所有工作需要 3 天，因此返回 3。
可以证明，3 天是最少需要的天数。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == jobs.length == workers.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= jobs[i], workers[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

为了使得完成所有工作所需的最少天数尽可能小，我们可以尽量让工作时间较长的工人去完成工作时间较长的工作。

因此，我们可以先对 $\textit{jobs}$ 和 $\textit{workers}$ 进行排序，然后依次将工作分配给对应下标的工人，求最大的工作时间和工人时间的比值即可。

时间复杂度 $O(n \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为工作数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTime(self, jobs: List[int], workers: List[int]) -> int:
        jobs.sort()
        workers.sort()
        return max((a + b - 1) // b for a, b in zip(jobs, workers))
```

#### Java

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

#### C++

```cpp
class Solution {
public:
    int minimumTime(vector<int>& jobs, vector<int>& workers) {
        ranges::sort(jobs);
        ranges::sort(workers);
        int ans = 0;
        int n = jobs.size();
        for (int i = 0; i < n; ++i) {
            ans = max(ans, (jobs[i] + workers[i] - 1) / workers[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func minimumTime(jobs []int, workers []int) (ans int) {
	sort.Ints(jobs)
	sort.Ints(workers)
	for i, a := range jobs {
		b := workers[i]
		ans = max(ans, (a+b-1)/b)
	}
	return
}
```

#### TypeScript

```ts
function minimumTime(jobs: number[], workers: number[]): number {
    jobs.sort((a, b) => a - b);
    workers.sort((a, b) => a - b);
    let ans = 0;
    const n = jobs.length;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.ceil(jobs[i] / workers[i]));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_time(mut jobs: Vec<i32>, mut workers: Vec<i32>) -> i32 {
        jobs.sort();
        workers.sort();
        jobs.iter()
            .zip(workers.iter())
            .map(|(a, b)| (a + b - 1) / b)
            .max()
            .unwrap()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} jobs
 * @param {number[]} workers
 * @return {number}
 */
var minimumTime = function (jobs, workers) {
    jobs.sort((a, b) => a - b);
    workers.sort((a, b) => a - b);
    let ans = 0;
    const n = jobs.length;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.ceil(jobs[i] / workers[i]));
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
