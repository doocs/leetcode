---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3678.Smallest%20Absent%20Positive%20Greater%20Than%20Average/README_EN.md
---

<!-- problem:start -->

# [3678. Smallest Absent Positive Greater Than Average](https://leetcode.com/problems/smallest-absent-positive-greater-than-average)

[中文文档](/solution/3600-3699/3678.Smallest%20Absent%20Positive%20Greater%20Than%20Average/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Return the <strong>smallest absent positive</strong> integer in <code>nums</code> such that it is <strong>strictly greater</strong> than the <strong>average</strong> of all elements in <code>nums</code>.</p>
The <strong>average</strong> of an array is defined as the sum of all its elements divided by the number of elements.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The average of <code>nums</code> is <code>(3 + 5) / 2 = 8 / 2 = 4</code>.</li>
	<li>The smallest absent positive integer greater than 4 is 6.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>​​​​​​​The average of <code>nums</code> is <code>(-1 + 1 + 2) / 3 = 2 / 3 = 0.667</code>.</li>
	<li>The smallest absent positive integer greater than 0.667 is 3.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The average of <code>nums</code> is <code>(4 + (-1)) / 2 = 3 / 2 = 1.50</code>.</li>
	<li>The smallest absent positive integer greater than 1.50 is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map

We use a hash map $\textit{s}$ to record the elements that appear in the array $\textit{nums}$.

Then, we calculate the average value $\textit{avg}$ of the array $\textit{nums}$, and initialize the answer $\textit{ans}$ as $\max(1, \lfloor \textit{avg} \rfloor + 1)$.

If $\textit{ans}$ appears in $\textit{s}$, we increment $\textit{ans}$ until it no longer appears in $\textit{s}$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestAbsent(self, nums: List[int]) -> int:
        s = set(nums)
        ans = max(1, sum(nums) // len(nums) + 1)
        while ans in s:
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int smallestAbsent(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int sum = 0;
        for (int x : nums) {
            s.add(x);
            sum += x;
        }
        int ans = Math.max(1, sum / nums.length + 1);
        while (s.contains(ans)) {
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestAbsent(vector<int>& nums) {
        unordered_set<int> s;
        int sum = 0;
        for (int x : nums) {
            s.insert(x);
            sum += x;
        }
        int ans = max(1, sum / (int) nums.size() + 1);
        while (s.contains(ans)) {
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func smallestAbsent(nums []int) int {
	s := map[int]bool{}
	sum := 0
	for _, x := range nums {
		s[x] = true
		sum += x
	}
	ans := max(1, sum/len(nums)+1)
	for s[ans] {
		ans++
	}
	return ans
}
```

#### TypeScript

```ts
function smallestAbsent(nums: number[]): number {
    const s = new Set<number>(nums);
    const sum = nums.reduce((a, b) => a + b, 0);
    let ans = Math.max(1, Math.floor(sum / nums.length) + 1);
    while (s.has(ans)) {
        ans++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
