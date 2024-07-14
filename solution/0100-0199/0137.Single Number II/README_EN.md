---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0137.Single%20Number%20II/README_EN.md
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [137. Single Number II](https://leetcode.com/problems/single-number-ii)

[中文文档](/solution/0100-0199/0137.Single%20Number%20II/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> where&nbsp;every element appears <strong>three times</strong> except for one, which appears <strong>exactly once</strong>. <em>Find the single element and return it</em>.</p>

<p>You must&nbsp;implement a solution with a linear runtime complexity and use&nbsp;only constant&nbsp;extra space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,3,2]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0,1,0,1,0,1,99]
<strong>Output:</strong> 99
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Each element in <code>nums</code> appears exactly <strong>three times</strong> except for one element which appears <strong>once</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bitwise Operation

We can enumerate each binary bit $i$, and for each binary bit, we calculate the sum of all numbers on that bit. If the sum of the numbers on that bit can be divided by 3, then the number that only appears once on that bit is 0, otherwise it is 1.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length of the array and the range of elements in the array, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        ans = 0
        for i in range(32):
            cnt = sum(num >> i & 1 for num in nums)
            if cnt % 3:
                if i == 31:
                    ans -= 1 << i
                else:
                    ans |= 1 << i
        return ans
```

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += num >> i & 1;
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int num : nums) {
                cnt += ((num >> i) & 1);
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
};
```

#### Go

```go
func singleNumber(nums []int) int {
	ans := int32(0)
	for i := 0; i < 32; i++ {
		cnt := int32(0)
		for _, num := range nums {
			cnt += int32(num) >> i & 1
		}
		cnt %= 3
		ans |= cnt << i
	}
	return int(ans)
}
```

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < 32; i++) {
        const count = nums.reduce((r, v) => r + ((v >> i) & 1), 0);
        ans |= count % 3 << i;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..32 {
            let count = nums.iter().map(|v| (v >> i) & 1).sum::<i32>();
            ans |= count % 3 << i;
        }
        ans
    }
}
```

#### C

```c
int singleNumber(int* nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < 32; i++) {
        int count = 0;
        for (int j = 0; j < numsSize; j++) {
            if (nums[j] >> i & 1) {
                count++;
            }
        }
        ans |= (uint) (count % 3) << i;
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        var a = nums.sorted()
        var n = a.count
        for i in stride(from: 0, through: n - 2, by: 3) {
            if a[i] != a[i + 1] {
                return a[i]
            }
        }
        return a[n - 1]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Digital Circuit

We can use a more efficient method that uses digital circuits to simulate the above bitwise operation.

Each binary bit of an integer can only represent 2 states, 0 or 1. However, we need to represent the sum of the $i$-th bit of all integers traversed so far modulo 3. Therefore, we can use two integers $a$ and $b$ to represent it. There are three possible cases:

1. The $i$-th bit of integer $a$ is 0 and the $i$-th bit of integer $b$ is 0, which means the modulo 3 result is 0;
2. The $i$-th bit of integer $a$ is 0 and the $i$-th bit of integer $b$ is 1, which means the modulo 3 result is 1;
3. The $i$-th bit of integer $a$ is 1 and the $i$-th bit of integer $b$ is 0, which means the modulo 3 result is 2.

We use integer $c$ to represent the number to be read in, and the truth table is as follows:

| $a_i$ | $b_i$ | $c_i$ | New $a_i$ | New $b_i$ |
| ----- | ----- | ----- | --------- | --------- |
| 0     | 0     | 0     | 0         | 0         |
| 0     | 0     | 1     | 0         | 1         |
| 0     | 1     | 0     | 0         | 1         |
| 0     | 1     | 1     | 1         | 0         |
| 1     | 0     | 0     | 1         | 0         |
| 1     | 0     | 1     | 0         | 0         |

Based on the truth table, we can write the logical expression:

$$
a_i = a_i' b_i c_i + a_i b_i' c_i'
$$

and:

$$
b_i = a_i' b_i' c_i + a_i' b_i c_i' = a_i' (b_i \oplus c_i)
$$

The final result is $b$, because when the binary bit of $b$ is 1, it means that the number appears only once.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        a = b = 0
        for c in nums:
            aa = (~a & b & c) | (a & ~b & ~c)
            bb = ~a & (b ^ c)
            a, b = aa, bb
        return b
```

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }
};
```

#### Go

```go
func singleNumber(nums []int) int {
	a, b := 0, 0
	for _, c := range nums {
		aa := (^a & b & c) | (a & ^b & ^c)
		bb := ^a & (b ^ c)
		a, b = aa, bb
	}
	return b
}
```

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    let a = 0;
    let b = 0;
    for (const c of nums) {
        const aa = (~a & b & c) | (a & ~b & ~c);
        const bb = ~a & (b ^ c);
        a = aa;
        b = bb;
    }
    return b;
}
```

#### Rust

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut b = 0;

        for c in nums {
            let aa = (!a & b & c) | (a & !b & !c);
            let bb = !a & (b ^ c);
            a = aa;
            b = bb;
        }

        return b;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
