---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3282.Reach%20End%20of%20Array%20With%20Max%20Score/README_EN.md
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [3282. Reach End of Array With Max Score](https://leetcode.com/problems/reach-end-of-array-with-max-score)

[中文文档](/solution/3200-3299/3282.Reach%20End%20of%20Array%20With%20Max%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>Your goal is to start at index <code>0</code> and reach index <code>n - 1</code>. You can only jump to indices <strong>greater</strong> than your current index.</p>

<p>The score for a jump from index <code>i</code> to index <code>j</code> is calculated as <code>(j - i) * nums[i]</code>.</p>

<p>Return the <strong>maximum</strong> possible <b>total score</b> by the time you reach the last index.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,1,5]</span></p>

<p><strong>Output:</strong> 7</p>

<p><strong>Explanation:</strong></p>

<p>First, jump to index 1 and then jump to the last index. The final score is <code>1 * 1 + 2 * 3 = 7</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,1,3,2]</span></p>

<p><strong>Output:</strong> 16</p>

<p><strong>Explanation:</strong></p>

<p>Jump directly to the last index. The final score is <code>4 * 4 = 16</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

Suppose we jump from index $i$ to index $j$, then the score is $(j - i) \times \text{nums}[i]$. This is equivalent to taking $j - i$ steps, and each step earns a score of $\text{nums}[i]$. Then we continue to jump from $j$ to the next index $k$, and the score is $(k - j) \times \text{nums}[j]$, and so on. If $\text{nums}[i] \gt \text{nums}[j]$, then we should not jump from $i$ to $j$, because the score obtained this way is definitely less than the score obtained by jumping directly from $i$ to $k$. Therefore, each time we should jump to the next index with a value greater than the current index.

We can maintain a variable $mx$ to represent the maximum value of $\text{nums}[i]$ encountered so far. Then we traverse the array from left to right until the second-to-last element, updating $mx$ each time and accumulating the score.

After the traversal, the result is the maximum total score.

The time complexity is $O(n)$, where $n$ is the length of the array $\text{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaximumScore(self, nums: List[int]) -> int:
        ans = mx = 0
        for x in nums[:-1]:
            mx = max(mx, x)
            ans += mx
        return ans
```

#### Java

```java
class Solution {
    public long findMaximumScore(List<Integer> nums) {
        long ans = 0;
        int mx = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            mx = Math.max(mx, nums.get(i));
            ans += mx;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long findMaximumScore(vector<int>& nums) {
        long long ans = 0;
        int mx = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            mx = max(mx, nums[i]);
            ans += mx;
        }
        return ans;
    }
};
```

#### Go

```go
func findMaximumScore(nums []int) (ans int64) {
	mx := 0
	for _, x := range nums[:len(nums)-1] {
		mx = max(mx, x)
		ans += int64(mx)
	}
	return
}
```

#### TypeScript

```ts
function findMaximumScore(nums: number[]): number {
    let [ans, mx]: [number, number] = [0, 0];
    for (const x of nums.slice(0, -1)) {
        mx = Math.max(mx, x);
        ans += mx;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
