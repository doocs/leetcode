---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4120. Count Dominant Elements](https://leetcode.com/problems/count-dominant-elements)

[中文文档](/solution/4100-4199/4120.Count%20Dominant%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>An element in <code>nums</code> is called <strong>dominant</strong> if it is <strong>greater than or equal to</strong> the average of its immediate neighbors.</p>

<p>The immediate neighbors of <code>nums[i]</code> are <code>nums[i - 1]</code> and <code>nums[i + 1]</code>, when those indices exist.</p>

<p>A single element with no neighbors is considered <strong>dominant</strong>.</p>

<p>Return the number of <strong>dominant</strong> elements in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,10,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>nums[0] = 1</code>, its only neighbor is 10. Since <code>1 &lt; 10</code>, it is not dominant.</li>
	<li>For <code>nums[1] = 10</code>, its neighbors are 1 and 2. Their average is <code>(1 + 2) / 2 = 1.5</code>. Since <code>10 &gt; 1.5</code>, it is dominant.</li>
	<li>For <code>nums[2] = 2</code>, its only neighbor is 10. Since <code>2 &lt; 10</code>, it is not dominant.</li>
</ul>

<p>Therefore, the answer is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>nums[0] = 6</code>, its only neighbor is 4. Since <code>6 &gt; 4</code>, it is dominant.</li>
	<li>For <code>nums[1] = 4</code>, its neighbors are 6 and 2. Their average is <code>(6 + 2) / 2 = 4</code>. Since <code>4 = 4</code>, it is dominant.</li>
	<li>For <code>nums[2] = 2</code>, its only neighbor is 4. Since <code>2 &lt; 4</code>, it is not dominant.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>nums[0] = 7</code>, it has no neighbors, so it is dominant.</li>
</ul>

<p>Therefore, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

<!-- thinking:start -->

> **Thinking**
>
> $n$ is at most $100$, so each element can be compared with its neighbors directly.
>
> An element is dominant when it is at least the average of its neighbors. A single element has no neighbor, and the statement counts it as dominant.
>
> If $\textit{cnt}$ neighbors sum to $s$, being at least the average is the same as the current value times $\textit{cnt}$ being at least $s$. Whether the left or right neighbor exists depends only on whether the index is on the border.
>
> One pass therefore adds the neighbors that exist, and counts the index when there is no neighbor or the inequality holds.

<!-- thinking:end -->

Scan index $i$ from left to right. Add the existing neighbors into $s$, and let $\textit{cnt}$ be how many there are. If $i>0$, add $\textit{nums}[i-1]$. If $i<n-1$, add $\textit{nums}[i+1]$. The index counts when $\textit{cnt}=0$, since the element has no neighbor. Otherwise it counts when $\textit{nums}[i]\ge s/\textit{cnt}$. Python compares the quotient. The other languages compare $\textit{nums}[i]\times\textit{cnt}$ with $s$, which is the same test.

The time complexity is $O(n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDominantElements(self, nums: list[int]) -> int:
        ans = 0
        for i, x in enumerate(nums):
            s = cnt = 0
            if i > 0:
                s += nums[i - 1]
                cnt += 1
            if i < len(nums) - 1:
                s += nums[i + 1]
                cnt += 1
            if cnt == 0 or x >= s / cnt:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countDominantElements(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int s = 0, cnt = 0;
            if (i > 0) {
                s += nums[i - 1];
                cnt++;
            }
            if (i < nums.length - 1) {
                s += nums[i + 1];
                cnt++;
            }
            if (cnt == 0 || nums[i] * cnt >= s) {
                ans++;
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
    int countDominantElements(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            int s = 0, cnt = 0;
            if (i > 0) {
                s += nums[i - 1];
                cnt++;
            }
            if (i < nums.size() - 1) {
                s += nums[i + 1];
                cnt++;
            }
            if (cnt == 0 || nums[i] * cnt >= s) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countDominantElements(nums []int) int {
	ans := 0
	for i, x := range nums {
		s, cnt := 0, 0
		if i > 0 {
			s += nums[i-1]
			cnt++
		}
		if i < len(nums)-1 {
			s += nums[i+1]
			cnt++
		}
		if cnt == 0 || x*cnt >= s {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countDominantElements(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < nums.length; i++) {
        let s = 0,
            cnt = 0;
        if (i > 0) {
            s += nums[i - 1];
            cnt++;
        }
        if (i < nums.length - 1) {
            s += nums[i + 1];
            cnt++;
        }
        if (cnt === 0 || nums[i] * cnt >= s) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
