---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2963.Count%20the%20Number%20of%20Good%20Partitions/README_EN.md
rating: 1984
source: Weekly Contest 375 Q4
tags:
    - Array
    - Hash Table
    - Math
    - Combinatorics
---

<!-- problem:start -->

# [2963. Count the Number of Good Partitions](https://leetcode.com/problems/count-the-number-of-good-partitions)

[中文文档](/solution/2900-2999/2963.Count%20the%20Number%20of%20Good%20Partitions/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>A partition of an array into one or more <strong>contiguous</strong> subarrays is called <strong>good</strong> if no two subarrays contain the same number.</p>

<p>Return <em>the <strong>total number</strong> of good partitions of </em><code>nums</code>.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 8
<strong>Explanation:</strong> The 8 possible good partitions are: ([1], [2], [3], [4]), ([1], [2], [3,4]), ([1], [2,3], [4]), ([1], [2,3,4]), ([1,2], [3], [4]), ([1,2], [3,4]), ([1,2,3], [4]), and ([1,2,3,4]).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only possible good partition is: ([1,1,1,1]).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The 2 possible good partitions are: ([1,2,1], [3]) and ([1,2,1,3]).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Grouping + Fast Power

According to the problem description, we know that the same number must be in the same subarray. Therefore, we use a hash table $last$ to record the index of the last occurrence of each number.

Next, we use an index $j$ to mark the index of the last element that has appeared among the elements, and use a variable $k$ to record the number of subarrays that can currently be divided.

Then, we traverse the array $nums$ from left to right. For the current number $nums[i]$ we are traversing, we get its last occurrence index and update $j = \max(j, last[nums[i]])$. If $i = j$, it means that the current position can be the end of a subarray, and we increase $k$ by $1$. Continue to traverse until the entire array is traversed.

Finally, we consider the number of division schemes for $k$ subarrays. The number of subarrays is $k$, and there are $k-1$ positions that can be divided (concatenated), so the number of schemes is $2^{k-1}$. Since the answer may be very large, we need to take the modulo of $10^9 + 7$. Here we can use fast power to speed up the calculation.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfGoodPartitions(self, nums: List[int]) -> int:
        last = {x: i for i, x in enumerate(nums)}
        mod = 10**9 + 7
        j, k = -1, 0
        for i, x in enumerate(nums):
            j = max(j, last[x])
            k += i == j
        return pow(2, k - 1, mod)
```

```java
class Solution {
    public int numberOfGoodPartitions(int[] nums) {
        Map<Integer, Integer> last = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            last.put(nums[i], i);
        }
        final int mod = (int) 1e9 + 7;
        int j = -1;
        int k = 0;
        for (int i = 0; i < n; ++i) {
            j = Math.max(j, last.get(nums[i]));
            k += i == j ? 1 : 0;
        }
        return qpow(2, k - 1, mod);
    }

    private int qpow(long a, int n, int mod) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfGoodPartitions(vector<int>& nums) {
        unordered_map<int, int> last;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            last[nums[i]] = i;
        }
        const int mod = 1e9 + 7;
        int j = -1, k = 0;
        for (int i = 0; i < n; ++i) {
            j = max(j, last[nums[i]]);
            k += i == j;
        }
        auto qpow = [&](long long a, int n, int mod) {
            long long ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return (int) ans;
        };
        return qpow(2, k - 1, mod);
    }
};
```

```go
func numberOfGoodPartitions(nums []int) int {
	qpow := func(a, n, mod int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	last := map[int]int{}
	for i, x := range nums {
		last[x] = i
	}
	const mod int = 1e9 + 7
	j, k := -1, 0
	for i, x := range nums {
		j = max(j, last[x])
		if i == j {
			k++
		}
	}
	return qpow(2, k-1, mod)
}
```

```ts
function numberOfGoodPartitions(nums: number[]): number {
    const qpow = (a: number, n: number, mod: number) => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    const last: Map<number, number> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        last.set(nums[i], i);
    }
    const mod = 1e9 + 7;
    let [j, k] = [-1, 0];
    for (let i = 0; i < n; ++i) {
        j = Math.max(j, last.get(nums[i])!);
        if (i === j) {
            ++k;
        }
    }
    return qpow(2, k - 1, mod);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
