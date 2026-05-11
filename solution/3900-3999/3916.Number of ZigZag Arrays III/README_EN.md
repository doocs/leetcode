---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3916.Number%20of%20ZigZag%20Arrays%20III/README_EN.md
---

<!-- problem:start -->

# [3916. Number of ZigZag Arrays III 🔒](https://leetcode.com/problems/number-of-zigzag-arrays-iii)

[中文文档](/solution/3900-3999/3916.Number%20of%20ZigZag%20Arrays%20III/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>l</code>, and <code>r</code>.</p>

<p>A <strong>ZigZag</strong> array of length <code>n</code> is defined as follows:</p>

<ul>
	<li>Each element lies in the range <code>[l, r]</code>.</li>
	<li>No <strong>two</strong> adjacent elements are equal.</li>
	<li>No <strong>three</strong> consecutive elements form a <strong>strictly increasing</strong> or <strong>strictly decreasing</strong> sequence.</li>
</ul>

<p>Return the total number of valid <strong>ZigZag</strong> arrays.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 4, r = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are only 2 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[4, 5]</code>:</p>

<ul>
	<li><code>[4, 5, 4]</code></li>
	<li><code>[5, 4, 5]</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 1, r = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 10 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[1, 3]</code>:</p>

<ul>
	<li><code>[1, 2, 1]</code>, <code>[1, 3, 1]</code>, <code>[1, 3, 2]</code></li>
	<li><code>[2, 1, 2]</code>, <code>[2, 1, 3]</code>, <code>[2, 3, 1]</code>, <code>[2, 3, 2]</code></li>
	<li><code>[3, 1, 2]</code>, <code>[3, 1, 3]</code>, <code>[3, 2, 3]</code></li>
</ul>

<p>All arrays meet the ZigZag conditions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 200</code></li>
	<li><code>1 &lt;= l &lt; r &lt;= 10<sup>​​​​​​​9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->
Traverse nums from right to left, maintaining the count of odd and even numbers that have been traversed. Based on the parity of nums[i], query the count of even or odd numbers to its right.
### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOppositeParity(self, nums: list[int]) -> list[int]:
        n = len(nums)
        ans = [0] * n
        cnt = [0] * 2
        for i in range(n - 1, -1, -1):
            x = nums[i] % 2 
            ans[i] = cnt[1 - x] 
            cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] cnt = new int[2];
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i] & 1;
            ans[i] = cnt[x ^ 1]; 
            cnt[x]++;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        int cnt[2]{};
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i] & 1;
            ans[i] = cnt[x ^ 1];
            cnt[x]++;
        }
        return ans;
    }
};
```

#### Go

```go
func countOppositeParity(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	cnt := [2]int{}
	for i := n - 1; i >= 0; i-- {
		x := nums[i] & 1 
		ans[i] = cnt[x^1]
		cnt[x]++
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
