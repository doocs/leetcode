---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3514.Number%20of%20Unique%20XOR%20Triplets%20II/README_EN.md
rating: 1883
source: Biweekly Contest 154 Q3
tags:
    - Bit Manipulation
    - Array
    - Math
    - Enumeration
---

<!-- problem:start -->

# [3514. Number of Unique XOR Triplets II](https://leetcode.com/problems/number-of-unique-xor-triplets-ii)

[中文文档](/solution/3500-3599/3514.Number%20of%20Unique%20XOR%20Triplets%20II/README.md)

## Description

<!-- description:start -->

<p data-end="261" data-start="147">You are given an integer array <code>nums</code>.</p>

<p>A <strong>XOR triplet</strong> is defined as the XOR of three elements <code>nums[i] XOR nums[j] XOR nums[k]</code> where <code>i &lt;= j &lt;= k</code>.</p>

<p>Return the number of <strong>unique</strong> XOR triplet values from all possible triplets <code>(i, j, k)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="158" data-start="101">The possible XOR triplet values are:</p>

<ul data-end="280" data-start="159">
	<li data-end="188" data-start="159"><code>(0, 0, 0) &rarr; 1 XOR 1 XOR 1 = 1</code></li>
	<li data-end="218" data-start="189"><code>(0, 0, 1) &rarr; 1 XOR 1 XOR 3 = 3</code></li>
	<li data-end="248" data-start="219"><code>(0, 1, 1) &rarr; 1 XOR 3 XOR 3 = 1</code></li>
	<li data-end="280" data-start="249"><code>(1, 1, 1) &rarr; 3 XOR 3 XOR 3 = 3</code></li>
</ul>

<p data-end="343" data-start="282">The unique XOR values are <code data-end="316" data-start="308">{1, 3}</code>. Thus, the output is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,7,8,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible XOR triplet values are <code data-end="275" data-start="267">{6, 7, 8, 9}</code>. Thus, the output is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1500</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

With indices satisfying $i \le j \le k$, the same index may be chosen more than once, and XOR is commutative. Therefore, the answer equals the number of distinct XOR values obtainable by picking any three elements from the array (with replacement).

Let $M = \max(\textit{nums})$. The XOR of any two non-negative integers at most $M$ is less than $2M$, so a boolean array of length $2M$ can be used for marking.

First enumerate all pairs $(a, b)$ and mark $a \oplus b$ in array $\textit{st}$. Then enumerate every appeared pairwise XOR value $\textit{ab}$ and each third element $c$, and mark $\textit{ab} \oplus c$ in array $s$. Finally count the number of non-zero entries in $s$.

The time complexity is $O(n^2 + M \cdot n)$, and the space complexity is $O(M)$, where $n$ is the length of the array and $M$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniqueXorTriplets(self, nums: List[int]) -> int:
        mx = max(nums) << 1
        st = [False] * mx
        for a in nums:
            for b in nums:
                st[a ^ b] = True
        s = [0] * mx
        for ab in range(mx):
            if st[ab]:
                for c in nums:
                    s[ab ^ c] = 1
        return sum(s)
```

#### Java

```java
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        mx <<= 1;

        boolean[] st = new boolean[mx];
        for (int a : nums) {
            for (int b : nums) {
                st[a ^ b] = true;
            }
        }

        int[] s = new int[mx];
        for (int ab = 0; ab < mx; ab++) {
            if (st[ab]) {
                for (int c : nums) {
                    s[ab ^ c] = 1;
                }
            }
        }

        int ans = 0;
        for (int v : s) {
            ans += v;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        int mx = ranges::max(nums) << 1;

        vector<bool> st(mx, false);
        for (int a : nums) {
            for (int b : nums) {
                st[a ^ b] = true;
            }
        }

        vector<int> s(mx, 0);
        for (int ab = 0; ab < mx; ab++) {
            if (st[ab]) {
                for (int c : nums) {
                    s[ab ^ c] = 1;
                }
            }
        }

        return accumulate(s.begin(), s.end(), 0);
    }
};
```

#### Go

```go
func uniqueXorTriplets(nums []int) int {
	mx := slices.Max(nums) << 1

	st := make([]bool, mx)
	for _, a := range nums {
		for _, b := range nums {
			st[a^b] = true
		}
	}

	s := make([]int, mx)
	for ab := 0; ab < mx; ab++ {
		if st[ab] {
			for _, c := range nums {
				s[ab^c] = 1
			}
		}
	}

	ans := 0
	for _, v := range s {
		ans += v
	}
	return ans
}
```

#### TypeScript

```ts
function uniqueXorTriplets(nums: number[]): number {
    const mx = Math.max(...nums) << 1;

    const st = new Array<boolean>(mx).fill(false);
    for (const a of nums) {
        for (const b of nums) {
            st[a ^ b] = true;
        }
    }

    const s = new Array<number>(mx).fill(0);
    for (let ab = 0; ab < mx; ab++) {
        if (st[ab]) {
            for (const c of nums) {
                s[ab ^ c] = 1;
            }
        }
    }

    let ans = 0;
    for (const v of s) {
        ans += v;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
