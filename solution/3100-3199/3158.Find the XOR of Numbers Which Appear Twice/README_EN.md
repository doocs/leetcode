---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3158.Find%20the%20XOR%20of%20Numbers%20Which%20Appear%20Twice/README_EN.md
rating: 1172
source: Biweekly Contest 131 Q1
tags:
    - Bit Manipulation
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3158. Find the XOR of Numbers Which Appear Twice](https://leetcode.com/problems/find-the-xor-of-numbers-which-appear-twice)

[中文文档](/solution/3100-3199/3158.Find%20the%20XOR%20of%20Numbers%20Which%20Appear%20Twice/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code>, where each number in the array appears <strong>either</strong><em> </em>once<em> </em>or<em> </em>twice.</p>

<p>Return the bitwise<em> </em><code>XOR</code> of all the numbers that appear twice in the array, or 0 if no number appears twice.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only number that appears twice in&nbsp;<code>nums</code>&nbsp;is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No number appears twice in&nbsp;<code>nums</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Numbers 1 and 2 appeared twice. <code>1 XOR 2 == 3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li>Each number in <code>nums</code> appears either once or twice.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We define an array or hash table `cnt` to record the occurrence of each number.

Next, we traverse the array `nums`. When a number appears twice, we perform an XOR operation with the answer.

Finally, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(M)$. Where $n$ is the length of the array `nums`, and $M$ is the maximum value in the array `nums` or the number of distinct numbers in the array `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def duplicateNumbersXOR(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return reduce(xor, [x for x, v in cnt.items() if v == 2], 0)
```

#### Java

```java
class Solution {
    public int duplicateNumbersXOR(int[] nums) {
        int[] cnt = new int[51];
        int ans = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans ^= x;
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
    int duplicateNumbersXOR(vector<int>& nums) {
        int cnt[51]{};
        int ans = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans ^= x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func duplicateNumbersXOR(nums []int) (ans int) {
	cnt := [51]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] == 2 {
			ans ^= x
		}
	}
	return
}
```

#### TypeScript

```ts
function duplicateNumbersXOR(nums: number[]): number {
    const cnt: number[] = Array(51).fill(0);
    let ans: number = 0;
    for (const x of nums) {
        if (++cnt[x] === 2) {
            ans ^= x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Bit Manipulation

Since the given number range in the problem is $1 \leq \textit{nums}[i] \leq 50$, we can use a $64$-bit integer to store the occurrence of each number.

We define an integer $\textit{mask}$ to record whether each number has appeared.

Next, we traverse the array $\textit{nums}$. When a number appears twice, i.e., the $x$-th bit in the binary representation of $\textit{mask}$ is $1$, we perform an XOR operation with the answer. Otherwise, we set the $x$-th bit of $\textit{mask}$ to $1$.

Finally, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def duplicateNumbersXOR(self, nums: List[int]) -> int:
        ans = mask = 0
        for x in nums:
            if mask >> x & 1:
                ans ^= x
            else:
                mask |= 1 << x
        return ans
```

#### Java

```java
class Solution {
    public int duplicateNumbersXOR(int[] nums) {
        int ans = 0;
        long mask = 0;
        for (int x : nums) {
            if ((mask >> x & 1) == 1) {
                ans ^= x;
            } else {
                mask |= 1L << x;
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
    int duplicateNumbersXOR(vector<int>& nums) {
        int ans = 0;
        long long mask = 0;
        for (int x : nums) {
            if (mask >> x & 1) {
                ans ^= x;
            } else {
                mask |= 1LL << x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func duplicateNumbersXOR(nums []int) (ans int) {
	mask := 0
	for _, x := range nums {
		if mask>>x&1 == 1 {
			ans ^= x
		} else {
			mask |= 1 << x
		}
	}
	return
}
```

#### TypeScript

```ts
function duplicateNumbersXOR(nums: number[]): number {
    let ans = 0;
    let mask = 0n;
    for (const x of nums) {
        if ((mask >> BigInt(x)) & 1n) {
            ans ^= x;
        } else {
            mask |= 1n << BigInt(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
