---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3502.Minimum%20Cost%20to%20Reach%20Every%20Position/README_EN.md
---

<!-- problem:start -->

# [3502. Minimum Cost to Reach Every Position](https://leetcode.com/problems/minimum-cost-to-reach-every-position)

[中文文档](/solution/3500-3599/3502.Minimum%20Cost%20to%20Reach%20Every%20Position/README.md)

## Description

<!-- description:start -->

<p data-end="438" data-start="104">You are given an integer array <code data-end="119" data-start="113">cost</code> of size <code data-end="131" data-start="128">n</code>. You are currently at position <code data-end="166" data-start="163">n</code> (at the end of the line) in a line of <code data-end="187" data-start="180">n + 1</code> people (numbered from 0 to <code data-end="218" data-start="215">n</code>).</p>

<p data-end="438" data-start="104">You wish to move forward in the line, but each person in front of you charges a specific amount to <strong>swap</strong> places. The cost to swap with person <code data-end="375" data-start="372">i</code> is given by <code data-end="397" data-start="388">cost[i]</code>.</p>

<p data-end="487" data-start="440">You are allowed to swap places with people as follows:</p>

<ul data-end="632" data-start="488">
	<li data-end="572" data-start="488">If they are in front of you, you <strong>must</strong> pay them <code data-end="546" data-start="537">cost[i]</code> to swap with them.</li>
	<li data-end="632" data-start="573">If they are behind you, they can swap with you for free.</li>
</ul>

<p data-end="755" data-start="634">Return an array <code>answer</code> of size <code>n</code>, where <code>answer[i]</code> is the <strong data-end="680" data-start="664">minimum</strong> total cost to reach each position <code>i</code> in the line<font face="monospace">.</font></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cost = [5,3,4,1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,3,3,1,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>We can get to each position in the following way:</p>

<ul>
	<li><code>i = 0</code>. We can swap with person 0 for a cost of 5.</li>
	<li><span class="example-io"><code><font face="monospace">i = </font>1</code>. We can swap with person 1 for a cost of 3.</span></li>
	<li><span class="example-io"><code>i = 2</code>. We can swap with person 1 for a cost of 3, then swap with person 2 for free.</span></li>
	<li><span class="example-io"><code>i = 3</code>. We can swap with person 3 for a cost of 1.</span></li>
	<li><span class="example-io"><code>i = 4</code>. We can swap with person 3 for a cost of 1, then swap with person 4 for free.</span></li>
	<li><span class="example-io"><code>i = 5</code>. We can swap with person 3 for a cost of 1, then swap with person 5 for free.</span></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cost = [1,2,4,6,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,1,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>We can swap with person 0 for a cost of <span class="example-io">1, then we will be able to reach any position <code>i</code> for free.</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == cost.length &lt;= 100</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

According to the problem description, the minimum cost for each position $i$ is the minimum cost from $0$ to $i$. We can use a variable $\textit{mi}$ to record the minimum cost from $0$ to $i$.

Starting from $0$, we iterate through each position $i$, updating $\textit{mi}$ as $\text{min}(\textit{mi}, \text{cost}[i])$ at each step, and assign $\textit{mi}$ to the $i$-th position of the answer array.

Finally, return the answer array.

Time complexity is $O(n)$, where $n$ is the length of the array $\textit{cost}$. Ignoring the space used by the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCosts(self, cost: List[int]) -> List[int]:
        n = len(cost)
        ans = [0] * n
        mi = cost[0]
        for i, c in enumerate(cost):
            mi = min(mi, c)
            ans[i] = mi
        return ans
```

#### Java

```java
class Solution {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] ans = new int[n];
        int mi = cost[0];
        for (int i = 0; i < n; ++i) {
            mi = Math.min(mi, cost[i]);
            ans[i] = mi;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minCosts(vector<int>& cost) {
        int n = cost.size();
        vector<int> ans(n);
        int mi = cost[0];
        for (int i = 0; i < n; ++i) {
            mi = min(mi, cost[i]);
            ans[i] = mi;
        }
        return ans;
    }
};
```

#### Go

```go
func minCosts(cost []int) []int {
	n := len(cost)
	ans := make([]int, n)
	mi := cost[0]
	for i, c := range cost {
		mi = min(mi, c)
		ans[i] = mi
	}
	return ans
}
```

#### TypeScript

```ts
function minCosts(cost: number[]): number[] {
    const n = cost.length;
    const ans: number[] = Array(n).fill(0);
    let mi = cost[0];
    for (let i = 0; i < n; ++i) {
        mi = Math.min(mi, cost[i]);
        ans[i] = mi;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
