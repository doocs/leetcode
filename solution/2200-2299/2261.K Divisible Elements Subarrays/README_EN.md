---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2261.K%20Divisible%20Elements%20Subarrays/README_EN.md
rating: 1724
source: Weekly Contest 291 Q3
tags:
    - Trie
    - Array
    - Hash Table
    - Enumeration
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [2261. K Divisible Elements Subarrays](https://leetcode.com/problems/k-divisible-elements-subarrays)

[中文文档](/solution/2200-2299/2261.K%20Divisible%20Elements%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and two integers <code>k</code> and <code>p</code>, return <em>the number of <strong>distinct subarrays,</strong> which have <strong>at most</strong></em> <code>k</code> <em>elements </em>that are <em>divisible by</em> <code>p</code>.</p>

<p>Two arrays <code>nums1</code> and <code>nums2</code> are said to be <strong>distinct</strong> if:</p>

<ul>
	<li>They are of <strong>different</strong> lengths, or</li>
	<li>There exists <strong>at least</strong> one index <code>i</code> where <code>nums1[i] != nums2[i]</code>.</li>
</ul>

<p>A <strong>subarray</strong> is defined as a <strong>non-empty</strong> contiguous sequence of elements in an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [<u><strong>2</strong></u>,3,3,<u><strong>2</strong></u>,<u><strong>2</strong></u>], k = 2, p = 2
<strong>Output:</strong> 11
<strong>Explanation:</strong>
The elements at indices 0, 3, and 4 are divisible by p = 2.
The 11 distinct subarrays which have at most k = 2 elements divisible by 2 are:
[2], [2,3], [2,3,3], [2,3,3,2], [3], [3,3], [3,3,2], [3,3,2,2], [3,2], [3,2,2], and [2,2].
Note that the subarrays [2] and [3] occur more than once in nums, but they should each be counted only once.
The subarray [2,3,3,2,2] should not be counted because it has 3 elements that are divisible by 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 4, p = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong>
All element of nums are divisible by p = 1.
Also, every subarray of nums will have at most 4 elements that are divisible by 1.
Since all subarrays are distinct, the total number of subarrays satisfying all the constraints is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i], p &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<p>Can you solve this problem in O(n<sup>2</sup>) time complexity?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + String Hashing

We can enumerate the left endpoint $i$ of the subarray, and then enumerate the right endpoint $j$ in the range $[i, n)$. During the enumeration of the right endpoint, we use double hashing to store the hash value of the subarray into a set. Finally, we return the size of the set.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        s = set()
        n = len(nums)
        base1, base2 = 131, 13331
        mod1, mod2 = 10**9 + 7, 10**9 + 9
        for i in range(n):
            h1 = h2 = cnt = 0
            for j in range(i, n):
                cnt += nums[j] % p == 0
                if cnt > k:
                    break
                h1 = (h1 * base1 + nums[j]) % mod1
                h2 = (h2 * base2 + nums[j]) % mod2
                s.add(h1 << 32 | h2)
        return len(s)
```

#### Java

```java
class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        Set<Long> s = new HashSet<>();
        int n = nums.length;
        int base1 = 131, base2 = 13331;
        int mod1 = (int) 1e9 + 7, mod2 = (int) 1e9 + 9;
        for (int i = 0; i < n; ++i) {
            long h1 = 0, h2 = 0;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += nums[j] % p == 0 ? 1 : 0;
                if (cnt > k) {
                    break;
                }
                h1 = (h1 * base1 + nums[j]) % mod1;
                h2 = (h2 * base2 + nums[j]) % mod2;
                s.add(h1 << 32 | h2);
            }
        }
        return s.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countDistinct(vector<int>& nums, int k, int p) {
        unordered_set<long long> s;
        int n = nums.size();
        int base1 = 131, base2 = 13331;
        int mod1 = 1e9 + 7, mod2 = 1e9 + 9;
        for (int i = 0; i < n; ++i) {
            long long h1 = 0, h2 = 0;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += nums[j] % p == 0;
                if (cnt > k) {
                    break;
                }
                h1 = (h1 * base1 + nums[j]) % mod1;
                h2 = (h2 * base2 + nums[j]) % mod2;
                s.insert(h1 << 32 | h2);
            }
        }
        return s.size();
    }
};
```

#### Go

```go
func countDistinct(nums []int, k int, p int) int {
	s := map[int]bool{}
	base1, base2 := 131, 13331
	mod1, mod2 := 1000000007, 1000000009
	for i := range nums {
		h1, h2, cnt := 0, 0, 0
		for j := i; j < len(nums); j++ {
			if nums[j]%p == 0 {
				cnt++
				if cnt > k {
					break
				}
			}
			h1 = (h1*base1 + nums[j]) % mod1
			h2 = (h2*base2 + nums[j]) % mod2
			s[h1<<32|h2] = true
		}
	}
	return len(s)
}
```

#### TypeScript

```ts
function countDistinct(nums: number[], k: number, p: number): number {
    const s = new Set<bigint>();
    const [base1, base2] = [131, 13331];
    const [mod1, mod2] = [1000000007, 1000000009];
    for (let i = 0; i < nums.length; i++) {
        let [h1, h2, cnt] = [0, 0, 0];
        for (let j = i; j < nums.length; j++) {
            if (nums[j] % p === 0) {
                cnt++;
                if (cnt > k) {
                    break;
                }
            }
            h1 = (h1 * base1 + nums[j]) % mod1;
            h2 = (h2 * base2 + nums[j]) % mod2;
            s.add((BigInt(h1) << 32n) | BigInt(h2));
        }
    }
    return s.size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        n = len(nums)
        s = set()
        for i in range(n):
            cnt = 0
            t = ""
            for x in nums[i:]:
                cnt += x % p == 0
                if cnt > k:
                    break
                t += str(x) + ","
                s.add(t)
        return len(s)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
