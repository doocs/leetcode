---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0810.Chalkboard%20XOR%20Game/README_EN.md
tags:
    - Bit Manipulation
    - Brainteaser
    - Array
    - Math
    - Game Theory
---

<!-- problem:start -->

# [810. Chalkboard XOR Game](https://leetcode.com/problems/chalkboard-xor-game)

[中文文档](/solution/0800-0899/0810.Chalkboard%20XOR%20Game/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> represents the numbers written on a chalkboard.</p>

<p>Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first. If erasing a number causes the bitwise XOR of all the elements of the chalkboard to become <code>0</code>, then that player loses. The bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is <code>0</code>.</p>

<p>Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to <code>0</code>, then that player wins.</p>

<p>Return <code>true</code> <em>if and only if Alice wins the game, assuming both players play optimally</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> 
Alice has two choices: erase 1 or erase 2. 
If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3. Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose. 
If Alice erases 2 first, now nums become [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0. Alice will lose.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>16</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

According to the game rules, if the XOR result of all numbers on the blackboard is $0$ when it is a player's turn, that player wins. Since Alice goes first, if the XOR result of all numbers in $\textit{nums}$ is $0$, Alice can win.

When the XOR result of all numbers in $\textit{nums}$ is not $0$, let's analyze Alice's winning situation based on the parity of the length of the array $\textit{nums}$.

When the length of $\textit{nums}$ is even, if Alice is destined to lose, there is only one situation: no matter which number Alice erases, the XOR result of all remaining numbers equals $0$. Let's analyze whether this situation exists.

Assume the length of the array $\textit{nums}$ is $n$, and $n$ is even. Let the XOR result of all numbers be $S$, then:

$$
S = \textit{nums}[0] \oplus \textit{nums}[1] \oplus \cdots \oplus \textit{nums}[n-1] \neq 0
$$

Let $S_i$ be the XOR result after erasing the $i$-th number from the array $\textit{nums}$, then:

$$
S_i \oplus \textit{nums}[i] = S
$$

XOR both sides of the equation by $\textit{nums}[i]$, we get:

$$
S_i = S \oplus \textit{nums}[i]
$$

If no matter which number Alice erases, the XOR result of all remaining numbers equals $0$, then for all $i$, we have $S_i = 0$, i.e.,

$$
S_0 \oplus S_1 \oplus \cdots \oplus S_{n-1} = 0
$$

Substitute $S_i = S \oplus \textit{nums}[i]$ into the above equation, we get:

$$
S \oplus \textit{nums}[0] \oplus S \oplus \textit{nums}[1] \oplus \cdots \oplus S \oplus \textit{nums}[n-1] = 0
$$

There are $n$ (even) $S$ terms in the above equation, and $\textit{nums}[0] \oplus \textit{nums}[1] \oplus \cdots \oplus \textit{nums}[n-1]$ also equals $S$, so the equation is equivalent to $0 \oplus S = 0$. This contradicts $S \neq 0$, so this situation does not exist. Therefore, when the length of $\textit{nums}$ is even, Alice is guaranteed to win.

If the length is odd, then after Alice erases a number, the remaining numbers are even in length, leaving Bob with an even-length situation, which means Bob is guaranteed to win, and thus Alice is destined to lose.

In conclusion, Alice can win when the length of $\textit{nums}$ is even or the XOR result of all numbers in $\textit{nums}$ is $0$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def xorGame(self, nums: List[int]) -> bool:
        return len(nums) % 2 == 0 or reduce(xor, nums) == 0
```

#### Java

```java
class Solution {
    public boolean xorGame(int[] nums) {
        return nums.length % 2 == 0 || Arrays.stream(nums).reduce(0, (a, b) -> a ^ b) == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool xorGame(vector<int>& nums) {
        if (nums.size() % 2 == 0) return true;
        int x = 0;
        for (int& v : nums) x ^= v;
        return x == 0;
    }
};
```

#### Go

```go
func xorGame(nums []int) bool {
	if len(nums)%2 == 0 {
		return true
	}
	x := 0
	for _, v := range nums {
		x ^= v
	}
	return x == 0
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
