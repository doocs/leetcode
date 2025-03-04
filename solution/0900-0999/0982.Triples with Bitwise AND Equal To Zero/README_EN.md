---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0982.Triples%20with%20Bitwise%20AND%20Equal%20To%20Zero/README_EN.md
tags:
    - Bit Manipulation
    - Array
    - Hash Table
---

<!-- problem:start -->

# [982. Triples with Bitwise AND Equal To Zero](https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero)

[中文文档](/solution/0900-0999/0982.Triples%20with%20Bitwise%20AND%20Equal%20To%20Zero/README.md)

## Description

<!-- description:start -->

<p>Given an integer array nums, return <em>the number of <strong>AND triples</strong></em>.</p>

<p>An <strong>AND triple</strong> is a triple of indices <code>(i, j, k)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i &lt; nums.length</code></li>
	<li><code>0 &lt;= j &lt; nums.length</code></li>
	<li><code>0 &lt;= k &lt; nums.length</code></li>
	<li><code>nums[i] &amp; nums[j] &amp; nums[k] == 0</code>, where <code>&amp;</code> represents the bitwise-AND operator.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3]
<strong>Output:</strong> 12
<strong>Explanation:</strong> We could choose the following i, j, k triples:
(i=0, j=0, k=1) : 2 &amp; 2 &amp; 1
(i=0, j=1, k=0) : 2 &amp; 1 &amp; 2
(i=0, j=1, k=1) : 2 &amp; 1 &amp; 1
(i=0, j=1, k=2) : 2 &amp; 1 &amp; 3
(i=0, j=2, k=1) : 2 &amp; 3 &amp; 1
(i=1, j=0, k=0) : 1 &amp; 2 &amp; 2
(i=1, j=0, k=1) : 1 &amp; 2 &amp; 1
(i=1, j=0, k=2) : 1 &amp; 2 &amp; 3
(i=1, j=1, k=0) : 1 &amp; 1 &amp; 2
(i=1, j=2, k=0) : 1 &amp; 3 &amp; 2
(i=2, j=0, k=1) : 3 &amp; 2 &amp; 1
(i=2, j=1, k=0) : 3 &amp; 1 &amp; 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0]
<strong>Output:</strong> 27
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

### Solution 1: Enumeration + Counting

First, we enumerate any two numbers $x$ and $y$, and use a hash table or array $cnt$ to count the occurrences of their bitwise AND result $x \& y$.

Then, we enumerate the bitwise AND result $xy$, and enumerate $z$. If $xy \& z = 0$, then we add the value of $cnt[xy]$ to the answer.

Finally, we return the answer.

The time complexity is $O(n^2 + n \times M)$, and the space complexity is $O(M)$, where $n$ is the length of the array $nums$; and $M$ is the maximum value in the array $nums$, with $M \leq 2^{16}$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countTriplets(self, nums: List[int]) -> int:
        cnt = Counter(x & y for x in nums for y in nums)
        return sum(v for xy, v in cnt.items() for z in nums if xy & z == 0)
```

#### Java

```java
class Solution {
    public int countTriplets(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            for (int y : nums) {
                cnt[x & y]++;
            }
        }
        int ans = 0;
        for (int xy = 0; xy <= mx; ++xy) {
            for (int z : nums) {
                if ((xy & z) == 0) {
                    ans += cnt[xy];
                }
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
    int countTriplets(vector<int>& nums) {
        int mx = ranges::max(nums);
        int cnt[mx + 1];
        memset(cnt, 0, sizeof cnt);
        for (int& x : nums) {
            for (int& y : nums) {
                cnt[x & y]++;
            }
        }
        int ans = 0;
        for (int xy = 0; xy <= mx; ++xy) {
            for (int& z : nums) {
                if ((xy & z) == 0) {
                    ans += cnt[xy];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countTriplets(nums []int) (ans int) {
	mx := slices.Max(nums)
	cnt := make([]int, mx+1)
	for _, x := range nums {
		for _, y := range nums {
			cnt[x&y]++
		}
	}
	for xy := 0; xy <= mx; xy++ {
		for _, z := range nums {
			if xy&z == 0 {
				ans += cnt[xy]
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countTriplets(nums: number[]): number {
    const mx = Math.max(...nums);
    const cnt: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        for (const y of nums) {
            cnt[x & y]++;
        }
    }
    let ans = 0;
    for (let xy = 0; xy <= mx; ++xy) {
        for (const z of nums) {
            if ((xy & z) === 0) {
                ans += cnt[xy];
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
