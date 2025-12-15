---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3767.Maximize%20Points%20After%20Choosing%20K%20Tasks/README_EN.md
rating: 1703
source: Biweekly Contest 171 Q3
tags:
    - Greedy
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3767. Maximize Points After Choosing K Tasks](https://leetcode.com/problems/maximize-points-after-choosing-k-tasks)

[中文文档](/solution/3700-3799/3767.Maximize%20Points%20After%20Choosing%20K%20Tasks/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>technique1</code> and <code>technique2</code>, each of length <code>n</code>, where <code>n</code> represents the number of tasks to complete.</p>

<ul>
	<li>If the <code>i<sup>th</sup></code> task is completed using technique 1, you earn <code>technique1[i]</code> points.</li>
	<li>If it is completed using technique 2, you earn <code>technique2[i]</code> points.</li>
</ul>

<p>You are also given an integer <code>k</code>, representing the <strong>minimum</strong> number of tasks that <strong>must</strong> be completed using technique 1.</p>

<p>You <strong>must</strong> complete <strong>at least</strong> <code>k</code> tasks using technique 1 (they do not need to be the first <code>k</code> tasks).</p>

<p>The remaining tasks may be completed using <strong>either</strong> technique.</p>

<p>Return an integer denoting the <strong>maximum total points</strong> you can earn.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">technique1 = [5,2,10], technique2 = [10,3,8], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<p>We must complete at least <code>k = 2</code> tasks using <code>technique1</code>.</p>

<p>Choosing <code>technique1[1]</code> and <code>technique1[2]</code> (completed using technique 1), and <code>technique2[0]</code> (completed using technique 2), yields the maximum points: <code>2 + 10 + 10 = 22</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">technique1 = [10,20,30], technique2 = [5,15,25], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">60</span></p>

<p><strong>Explanation:</strong></p>

<p>We must complete at least <code>k = 2</code> tasks using <code>technique1</code>.</p>

<p>Choosing all tasks using technique 1 yields the maximum points: <code>10 + 20 + 30 = 60</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">technique1 = [1,2,3], technique2 = [4,5,6], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>k = 0</code>, we are not required to choose any task using <code>technique1</code>.</p>

<p>Choosing all tasks using technique 2 yields the maximum points: <code>4 + 5 + 6 = 15</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == technique1.length == technique2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= technique1[i], technique2​​​​​​​[i] &lt;= 10<sup>​​​​​​​5</sup></code></li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

We can first assign all tasks to technique 2, so the initial total score is $\sum_{i=0}^{n-1} technique2[i]$.

Then, we calculate the score increase for each task if it were completed using technique 1 instead, denoted as $\text{diff}[i] = technique1[i] - technique2[i]$. We sort this in descending order to obtain a sorted array of task indices $\text{idx}$.

Next, we select the first $k$ tasks to be completed using technique 1 and add their score differences to the total score. For the remaining tasks, if a task can increase the score by using technique 1 (i.e., $\text{diff}[i] \geq 0$), we also choose to complete it using technique 1.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the number of tasks.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPoints(self, technique1: List[int], technique2: List[int], k: int) -> int:
        n = len(technique1)
        idx = sorted(range(n), key=lambda i: -(technique1[i] - technique2[i]))
        ans = sum(technique2)
        for i in idx[:k]:
            ans -= technique2[i]
            ans += technique1[i]
        for i in idx[k:]:
            if technique1[i] >= technique2[i]:
                ans -= technique2[i]
                ans += technique1[i]
        return ans
```

#### Java

```java
class Solution {
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        int n = technique1.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (i, j) -> technique1[j] - technique2[j] - (technique1[i] - technique2[i]));
        long ans = 0;
        for (int x : technique2) {
            ans += x;
        }
        for (int i = 0; i < k; i++) {
            int index = idx[i];
            ans -= technique2[index];
            ans += technique1[index];
        }
        for (int i = k; i < n; i++) {
            int index = idx[i];
            if (technique1[index] >= technique2[index]) {
                ans -= technique2[index];
                ans += technique1[index];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxPoints(vector<int>& technique1, vector<int>& technique2, int k) {
        int n = technique1.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);

        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return (technique1[j] - technique2[j]) < (technique1[i] - technique2[i]);
        });

        long long ans = 0;
        for (int x : technique2) {
            ans += x;
        }

        for (int i = 0; i < k; i++) {
            int index = idx[i];
            ans -= technique2[index];
            ans += technique1[index];
        }

        for (int i = k; i < n; i++) {
            int index = idx[i];
            if (technique1[index] >= technique2[index]) {
                ans -= technique2[index];
                ans += technique1[index];
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxPoints(technique1 []int, technique2 []int, k int) int64 {
	n := len(technique1)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		idx[i] = i
	}

	sort.Slice(idx, func(i, j int) bool {
		return technique1[idx[j]]-technique2[idx[j]] < technique1[idx[i]]-technique2[idx[i]]
	})

	var ans int64
	for _, x := range technique2 {
		ans += int64(x)
	}

	for i := 0; i < k; i++ {
		index := idx[i]
		ans -= int64(technique2[index])
		ans += int64(technique1[index])
	}

	for i := k; i < n; i++ {
		index := idx[i]
		if technique1[index] >= technique2[index] {
			ans -= int64(technique2[index])
			ans += int64(technique1[index])
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maxPoints(technique1: number[], technique2: number[], k: number): number {
    const n = technique1.length;
    const idx = Array.from({ length: n }, (_, i) => i);

    idx.sort((i, j) => technique1[j] - technique2[j] - (technique1[i] - technique2[i]));

    let ans = technique2.reduce((sum, x) => sum + x, 0);

    for (let i = 0; i < k; i++) {
        const index = idx[i];
        ans -= technique2[index];
        ans += technique1[index];
    }

    for (let i = k; i < n; i++) {
        const index = idx[i];
        if (technique1[index] >= technique2[index]) {
            ans -= technique2[index];
            ans += technique1[index];
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
