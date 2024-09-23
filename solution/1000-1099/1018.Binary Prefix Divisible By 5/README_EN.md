---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1018.Binary%20Prefix%20Divisible%20By%205/README_EN.md
rating: 1376
source: Weekly Contest 130 Q1
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [1018. Binary Prefix Divisible By 5](https://leetcode.com/problems/binary-prefix-divisible-by-5)

[中文文档](/solution/1000-1099/1018.Binary%20Prefix%20Divisible%20By%205/README.md)

## Description

<!-- description:start -->

<p>You are given a binary array <code>nums</code> (<strong>0-indexed</strong>).</p>

<p>We define <code>x<sub>i</sub></code> as the number whose binary representation is the subarray <code>nums[0..i]</code> (from most-significant-bit to least-significant-bit).</p>

<ul>
	<li>For example, if <code>nums = [1,0,1]</code>, then <code>x<sub>0</sub> = 1</code>, <code>x<sub>1</sub> = 2</code>, and <code>x<sub>2</sub> = 5</code>.</li>
</ul>

<p>Return <em>an array of booleans </em><code>answer</code><em> where </em><code>answer[i]</code><em> is </em><code>true</code><em> if </em><code>x<sub>i</sub></code><em> is divisible by </em><code>5</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1]
<strong>Output:</strong> [true,false,false]
<strong>Explanation:</strong> The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.
Only the first number is divisible by 5, so answer[0] is true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> [false,false,false]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a variable $x$ to represent the current binary prefix, then traverse the array $nums$. For each element $v$, we left shift $x$ by one bit, then add $v$, and take the result modulo $5$. If the result equals $0$, it means the current binary prefix is divisible by $5$, and we add $\textit{true}$ to the answer array; otherwise, we add $\textit{false}$ to the answer array.

The time complexity is $O(n)$, and ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def prefixesDivBy5(self, nums: List[int]) -> List[bool]:
        ans = []
        x = 0
        for v in nums:
            x = (x << 1 | v) % 5
            ans.append(x == 0)
        return ans
```

#### Java

```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        int x = 0;
        for (int v : nums) {
            x = (x << 1 | v) % 5;
            ans.add(x == 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> prefixesDivBy5(vector<int>& nums) {
        vector<bool> ans;
        int x = 0;
        for (int v : nums) {
            x = (x << 1 | v) % 5;
            ans.push_back(x == 0);
        }
        return ans;
    }
};
```

#### Go

```go
func prefixesDivBy5(nums []int) (ans []bool) {
	x := 0
	for _, v := range nums {
		x = (x<<1 | v) % 5
		ans = append(ans, x == 0)
	}
	return
}
```

#### TypeScript

```ts
function prefixesDivBy5(nums: number[]): boolean[] {
    const ans: boolean[] = [];
    let x = 0;
    for (const v of nums) {
        x = ((x << 1) | v) % 5;
        ans.push(x === 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
