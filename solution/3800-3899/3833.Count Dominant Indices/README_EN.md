---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3833.Count%20Dominant%20Indices/README_EN.md
rating: 1171
source: Weekly Contest 488 Q1
---

<!-- problem:start -->

# [3833. Count Dominant Indices](https://leetcode.com/problems/count-dominant-indices)

[中文文档](/solution/3800-3899/3833.Count%20Dominant%20Indices/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>An element at index <code>i</code> is called <strong>dominant</strong> if: <code>nums[i] &gt; average(nums[i + 1], nums[i + 2], ..., nums[n - 1])</code></p>

<p>Your task is to count the number of indices <code>i</code> that are <strong>dominant</strong>.</p>

<p>The <strong>average</strong> of a set of numbers is the value obtained by adding all the numbers together and dividing the sum by the total number of numbers.</p>

<p><strong>Note</strong>: The <strong>rightmost</strong> element of any array is <strong>not</strong> <strong>dominant</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At index <code>i = 0</code>, the value 5 is dominant as <code>5 &gt; average(4, 3) = 3.5</code>.</li>
	<li>At index <code>i = 1</code>, the value 4 is dominant over the subarray <code>[3]</code>.</li>
	<li>Index <code>i = 2</code> is not dominant as there are no elements to its right. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At index <code>i = 0</code>, the value 4 is dominant over the subarray <code>[1, 2]</code>.</li>
	<li>At index <code>i = 1</code>, the value 1 is not dominant.</li>
	<li>Index <code>i = 2</code> is not dominant as there are no elements to its right. Thus, the answer is 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Traversal

We can traverse the array from back to front, maintaining a suffix sum $\text{suf}$, which represents the sum of all elements to the right of the current element. For each element, we check if it is greater than the average value of the elements to its right $\frac{\text{suf}}{n - i - 1}$. If so, we increment the answer by one. Finally, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\text{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dominantIndices(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        suf = nums[-1]
        for i in range(n - 2, -1, -1):
            if nums[i] > suf / (n - i - 1):
                ans += 1
            suf += nums[i]
        return ans
```

#### Java

```java
class Solution {
    public int dominantIndices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int suf = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] * (n - i - 1) > suf) {
                ans++;
            }
            suf += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dominantIndices(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        int suf = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] * (n - i - 1) > suf) {
                ans++;
            }
            suf += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func dominantIndices(nums []int) int {
	n := len(nums)
	ans := 0
	suf := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i]*(n-i-1) > suf {
			ans++
		}
		suf += nums[i]
	}
	return ans
}
```

#### TypeScript

```ts
function dominantIndices(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    let suf = nums[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] * (n - i - 1) > suf) {
            ans++;
        }
        suf += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
