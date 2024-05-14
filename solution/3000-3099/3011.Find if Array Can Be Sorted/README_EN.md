---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3011.Find%20if%20Array%20Can%20Be%20Sorted/README_EN.md
rating: 1496
tags:
    - Bit Manipulation
    - Array
    - Sorting
---

# [3011. Find if Array Can Be Sorted](https://leetcode.com/problems/find-if-array-can-be-sorted)

[中文文档](/solution/3000-3099/3011.Find%20if%20Array%20Can%20Be%20Sorted/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>In one <strong>operation</strong>, you can swap any two <strong>adjacent</strong> elements if they have the <strong>same</strong> number of <span data-keyword="set-bit">set bits</span>. You are allowed to do this operation <strong>any</strong> number of times (<strong>including zero</strong>).</p>

<p>Return <code>true</code> <em>if you can sort the array, else return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,4,2,30,15]
<strong>Output:</strong> true
<strong>Explanation:</strong> Let&#39;s look at the binary representation of every element. The numbers 2, 4, and 8 have one set bit each with binary representation &quot;10&quot;, &quot;100&quot;, and &quot;1000&quot; respectively. The numbers 15 and 30 have four set bits each with binary representation &quot;1111&quot; and &quot;11110&quot;.
We can sort the array using 4 operations:
- Swap nums[0] with nums[1]. This operation is valid because 8 and 4 have one set bit each. The array becomes [4,8,2,30,15].
- Swap nums[1] with nums[2]. This operation is valid because 8 and 2 have one set bit each. The array becomes [4,2,8,30,15].
- Swap nums[0] with nums[1]. This operation is valid because 4 and 2 have one set bit each. The array becomes [2,4,8,30,15].
- Swap nums[3] with nums[4]. This operation is valid because 30 and 15 have four set bits each. The array becomes [2,4,8,15,30].
The array has become sorted, hence we return true.
Note that there may be other sequences of operations which also sort the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array is already sorted, hence we return true.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,16,8,4,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be shown that it is not possible to sort the input array using any number of operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2<sup>8</sup></code></li>
</ul>

## Solutions

### Solution 1: Two Pointers

We can use two pointers to divide the array $nums$ into several subarrays, each with the same number of 1s in the binary representation of its elements. For each subarray, we only need to focus on its maximum and minimum values. If the minimum value is smaller than the maximum value of the previous subarray, then it is impossible to make the array sorted by swapping.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def canSortArray(self, nums: List[int]) -> bool:
        pre_mx = -inf
        i, n = 0, len(nums)
        while i < n:
            j = i + 1
            cnt = nums[i].bit_count()
            mi = mx = nums[i]
            while j < n and nums[j].bit_count() == cnt:
                mi = min(mi, nums[j])
                mx = max(mx, nums[j])
                j += 1
            if pre_mx > mi:
                return False
            pre_mx = mx
            i = j
        return True
```

```java
class Solution {
    public boolean canSortArray(int[] nums) {
        int preMx = -300;
        int i = 0, n = nums.length;
        while (i < n) {
            int j = i + 1;
            int cnt = Integer.bitCount(nums[i]);
            int mi = nums[i], mx = nums[i];
            while (j < n && Integer.bitCount(nums[j]) == cnt) {
                mi = Math.min(mi, nums[j]);
                mx = Math.max(mx, nums[j]);
                j++;
            }
            if (preMx > mi) {
                return false;
            }
            preMx = mx;
            i = j;
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool canSortArray(vector<int>& nums) {
        int preMx = -300;
        int i = 0, n = nums.size();
        while (i < n) {
            int j = i + 1;
            int cnt = __builtin_popcount(nums[i]);
            int mi = nums[i], mx = nums[i];
            while (j < n && __builtin_popcount(nums[j]) == cnt) {
                mi = min(mi, nums[j]);
                mx = max(mx, nums[j]);
                j++;
            }
            if (preMx > mi) {
                return false;
            }
            preMx = mx;
            i = j;
        }
        return true;
    }
};
```

```go
func canSortArray(nums []int) bool {
	preMx := -300
	i, n := 0, len(nums)
	for i < n {
		j := i + 1
		cnt := bits.OnesCount(uint(nums[i]))
		mi, mx := nums[i], nums[i]
		for j < n && bits.OnesCount(uint(nums[j])) == cnt {
			mi = min(mi, nums[j])
			mx = max(mx, nums[j])
			j++
		}
		if preMx > mi {
			return false
		}
		preMx = mx
		i = j
	}
	return true
}
```

```ts
function canSortArray(nums: number[]): boolean {
    let preMx = -300;
    const n = nums.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        const cnt = bitCount(nums[i]);
        let [mi, mx] = [nums[i], nums[i]];
        while (j < n && bitCount(nums[j]) === cnt) {
            mi = Math.min(mi, nums[j]);
            mx = Math.max(mx, nums[j]);
            j++;
        }
        if (preMx > mi) {
            return false;
        }
        preMx = mx;
        i = j;
    }
    return true;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- end -->
