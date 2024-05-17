---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2422.Merge%20Operations%20to%20Turn%20Array%20Into%20a%20Palindrome/README_EN.md
tags:
    - Greedy
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [2422. Merge Operations to Turn Array Into a Palindrome 🔒](https://leetcode.com/problems/merge-operations-to-turn-array-into-a-palindrome)

[中文文档](/solution/2400-2499/2422.Merge%20Operations%20to%20Turn%20Array%20Into%20a%20Palindrome/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>You can perform the following operation on the array <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any two <strong>adjacent</strong> elements and <strong>replace</strong> them with their <strong>sum</strong>.

    <ul>
    	<li>For example, if <code>nums = [1,<u>2,3</u>,1]</code>, you can apply one operation to make it <code>[1,5,1]</code>.</li>
    </ul>
    </li>

</ul>

<p>Return <em>the <strong>minimum</strong> number of operations needed to turn the array into a <strong>palindrome</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,1,2,3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can turn the array into a palindrome in 2 operations as follows:
- Apply the operation on the fourth and fifth element of the array, nums becomes equal to [4,3,2,<strong><u>3</u></strong>,3,1].
- Apply the operation on the fifth and sixth element of the array, nums becomes equal to [4,3,2,3,<strong><u>4</u></strong>].
The array [4,3,2,3,4] is a palindrome.
It can be shown that 2 is the minimum number of operations needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We do the operation 3 times in any position, we obtain the array [10] at the end which is a palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Two Pointers

Define two pointers $i$ and $j$, pointing to the beginning and end of the array respectively, use variables $a$ and $b$ to represent the values of the first and last elements, and variable $ans$ to represent the number of operations.

If $a < b$, we move the pointer $i$ one step to the right, i.e., $i \leftarrow i + 1$, then add the value of the element pointed to by $i$ to $a$, i.e., $a \leftarrow a + nums[i]$, and increment the operation count by one, i.e., $ans \leftarrow ans + 1$.

If $a > b$, we move the pointer $j$ one step to the left, i.e., $j \leftarrow j - 1$, then add the value of the element pointed to by $j$ to $b$, i.e., $b \leftarrow b + nums[j]$, and increment the operation count by one, i.e., $ans \leftarrow ans + 1$.

Otherwise, it means $a = b$, at this time we move the pointer $i$ one step to the right, i.e., $i \leftarrow i + 1$, move the pointer $j$ one step to the left, i.e., $j \leftarrow j - 1$, and update the values of $a$ and $b$, i.e., $a \leftarrow nums[i]$ and $b \leftarrow nums[j]$.

Repeat the above process until $i \ge j$, return the operation count $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        i, j = 0, len(nums) - 1
        a, b = nums[i], nums[j]
        ans = 0
        while i < j:
            if a < b:
                i += 1
                a += nums[i]
                ans += 1
            elif b < a:
                j -= 1
                b += nums[j]
                ans += 1
            else:
                i, j = i + 1, j - 1
                a, b = nums[i], nums[j]
        return ans
```

```java
class Solution {
    public int minimumOperations(int[] nums) {
        int i = 0, j = nums.length - 1;
        long a = nums[i], b = nums[j];
        int ans = 0;
        while (i < j) {
            if (a < b) {
                a += nums[++i];
                ++ans;
            } else if (b < a) {
                b += nums[--j];
                ++ans;
            } else {
                a = nums[++i];
                b = nums[--j];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        int i = 0, j = nums.size() - 1;
        long a = nums[i], b = nums[j];
        int ans = 0;
        while (i < j) {
            if (a < b) {
                a += nums[++i];
                ++ans;
            } else if (b < a) {
                b += nums[--j];
                ++ans;
            } else {
                a = nums[++i];
                b = nums[--j];
            }
        }
        return ans;
    }
};
```

```go
func minimumOperations(nums []int) int {
	i, j := 0, len(nums)-1
	a, b := nums[i], nums[j]
	ans := 0
	for i < j {
		if a < b {
			i++
			a += nums[i]
			ans++
		} else if b < a {
			j--
			b += nums[j]
			ans++
		} else {
			i, j = i+1, j-1
			a, b = nums[i], nums[j]
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
