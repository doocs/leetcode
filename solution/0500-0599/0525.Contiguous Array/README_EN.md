---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0525.Contiguous%20Array/README_EN.md
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [525. Contiguous Array](https://leetcode.com/problems/contiguous-array)

[中文文档](/solution/0500-0599/0525.Contiguous%20Array/README.md)

## Description

<!-- description:start -->

<p>Given a binary array <code>nums</code>, return <em>the maximum length of a contiguous subarray with an equal number of </em><code>0</code><em> and </em><code>1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong> [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
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

### Solution 1: Prefix Sum + Hash Table

According to the problem description, we can treat $0$s in the array as $-1$. In this way, when encountering a $0$, the prefix sum $s$ will decrease by one, and when encountering a $1$, the prefix sum $s$ will increase by one. Therefore, suppose the prefix sum $s$ is equal at indices $j$ and $i$, where $j < i$, then the subarray from index $j + 1$ to $i$ has an equal number of $0$s and $1$s.

We use a hash table to store all prefix sums and their first occurrence indices. Initially, we map the prefix sum of $0$ to $-1$.

As we iterate through the array, we calculate the prefix sum $s$. If $s$ is already in the hash table, then we have found a subarray with a sum of $0$, and its length is $i - d[s]$, where $d[s]$ is the index where $s$ first appeared in the hash table. If $s$ is not in the hash table, we store $s$ and its index $i$ in the hash table.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, x in enumerate(nums):
            s += 1 if x else -1
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
```

#### Java

```java
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int ans = 0, s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i] == 1 ? 1 : -1;
            if (d.containsKey(s)) {
                ans = Math.max(ans, i - d.get(s));
            } else {
                d.put(s, i);
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
    int findMaxLength(vector<int>& nums) {
        unordered_map<int, int> d{{0, -1}};
        int ans = 0, s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i] ? 1 : -1;
            if (d.contains(s)) {
                ans = max(ans, i - d[s]);
            } else {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findMaxLength(nums []int) int {
	d := map[int]int{0: -1}
	ans, s := 0, 0
	for i, x := range nums {
		if x == 0 {
			x = -1
		}
		s += x
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findMaxLength(nums: number[]): number {
    const d: Record<number, number> = { 0: -1 };
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i] ? 1 : -1;
        if (d.hasOwnProperty(s)) {
            ans = Math.max(ans, i - d[s]);
        } else {
            d[s] = i;
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxLength = function (nums) {
    const d = { 0: -1 };
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i] ? 1 : -1;
        if (d.hasOwnProperty(s)) {
            ans = Math.max(ans, i - d[s]);
        } else {
            d[s] = i;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
