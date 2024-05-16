---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2870.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20Empty/README_EN.md
rating: 1392
source: Biweekly Contest 114 Q2
tags:
    - Greedy
    - Array
    - Hash Table
    - Counting
---

# [2870. Minimum Number of Operations to Make Array Empty](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty)

[中文文档](/solution/2800-2899/2870.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20Empty/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of positive integers.</p>

<p>There are two types of operations that you can apply on the array <strong>any</strong> number of times:</p>

<ul>
	<li>Choose <strong>two</strong> elements with <strong>equal</strong> values and <strong>delete</strong> them from the array.</li>
	<li>Choose <strong>three</strong> elements with <strong>equal</strong> values and <strong>delete</strong> them from the array.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations required to make the array empty, or </em><code>-1</code><em> if it is not possible</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,3,2,2,4,2,3,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can apply the following operations to make the array empty:
- Apply the first operation on the elements at indices 0 and 3. The resulting array is nums = [3,3,2,4,2,3,4].
- Apply the first operation on the elements at indices 2 and 4. The resulting array is nums = [3,3,4,3,4].
- Apply the second operation on the elements at indices 0, 1, and 3. The resulting array is nums = [4,4].
- Apply the first operation on the elements at indices 0 and 1. The resulting array is nums = [].
It can be shown that we cannot make the array empty in less than 4 operations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,2,2,3,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to empty the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Greedy

We use a hash table $count$ to count the number of occurrences of each element in the array. Then we traverse the hash table. For each element $x$, if it appears $c$ times, we can perform $\lfloor \frac{c+2}{3} \rfloor$ operations to delete $x$. Finally, we return the sum of the number of operations for all elements.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(n)$.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        count = Counter(nums)
        ans = 0
        for c in count.values():
            if c == 1:
                return -1
            ans += (c + 2) // 3
        return ans
```

```java
class Solution {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            // count.put(num, count.getOrDefault(num, 0) + 1);
            count.merge(num, 1, Integer::sum);
        }
        int ans = 0;
        for (int c : count.values()) {
            if (c < 2) {
                return -1;
            }
            int r = c % 3;
            int d = c / 3;
            switch (r) {
                case (0) -> {
                    ans += d;
                }
                default -> {
                    ans += d + 1;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        unordered_map<int, int> count;
        for (int num : nums) {
            ++count[num];
        }
        int ans = 0;
        for (auto& [_, c] : count) {
            if (c < 2) {
                return -1;
            }
            ans += (c + 2) / 3;
        }
        return ans;
    }
};
```

```go
func minOperations(nums []int) (ans int) {
	count := map[int]int{}
	for _, num := range nums {
		count[num]++
	}
	for _, c := range count {
		if c < 2 {
			return -1
		}
		ans += (c + 2) / 3
	}
	return
}
```

```ts
function minOperations(nums: number[]): number {
    const count: Map<number, number> = new Map();
    for (const num of nums) {
        count.set(num, (count.get(num) ?? 0) + 1);
    }
    let ans = 0;
    for (const [_, c] of count) {
        if (c < 2) {
            return -1;
        }
        ans += ((c + 2) / 3) | 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
