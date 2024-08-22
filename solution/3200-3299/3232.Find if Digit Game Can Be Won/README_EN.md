---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3232.Find%20if%20Digit%20Game%20Can%20Be%20Won/README_EN.md
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [3232. Find if Digit Game Can Be Won](https://leetcode.com/problems/find-if-digit-game-can-be-won)

[中文文档](/solution/3200-3299/3232.Find%20if%20Digit%20Game%20Can%20Be%20Won/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>Alice and Bob are playing a game. In the game, Alice can choose <strong>either</strong> all single-digit numbers or all double-digit numbers from <code>nums</code>, and the rest of the numbers are given to Bob. Alice wins if the sum of her numbers is <strong>strictly greater</strong> than the sum of Bob&#39;s numbers.</p>

<p>Return <code>true</code> if Alice can win this game, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>Alice cannot win by choosing either single-digit or double-digit numbers.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,14]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Alice can win by choosing single-digit numbers which have a sum equal to 15.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5,25]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Alice can win by choosing double-digit numbers which have a sum equal to 25.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 99</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Summation

According to the problem description, as long as the sum of the units digits is not equal to the sum of the tens digits, Xiaohong can always choose a larger sum to win.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canAliceWin(self, nums: List[int]) -> bool:
        a = sum(x for x in nums if x < 10)
        b = sum(x for x in nums if x > 9)
        return a != b
```

#### Java

```java
class Solution {
    public boolean canAliceWin(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x < 10) {
                a += x;
            } else {
                b += x;
            }
        }
        return a != b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canAliceWin(vector<int>& nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            if (x < 10) {
                a += x;
            } else {
                b += x;
            }
        }
        return a != b;
    }
};
```

#### Go

```go
func canAliceWin(nums []int) bool {
	a, b := 0, 0
	for _, x := range nums {
		if x < 10 {
			a += x
		} else {
			b += x
		}
	}
	return a != b
}
```

#### TypeScript

```ts
function canAliceWin(nums: number[]): boolean {
    let [a, b] = [0, 0];
    for (const x of nums) {
        if (x < 10) {
            a += x;
        } else {
            b += x;
        }
    }
    return a !== b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
