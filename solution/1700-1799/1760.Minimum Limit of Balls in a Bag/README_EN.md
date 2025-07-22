---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1760.Minimum%20Limit%20of%20Balls%20in%20a%20Bag/README_EN.md
rating: 1939
source: Weekly Contest 228 Q3
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [1760. Minimum Limit of Balls in a Bag](https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag)

[中文文档](/solution/1700-1799/1760.Minimum%20Limit%20of%20Balls%20in%20a%20Bag/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> where the <code>i<sup>th</sup></code> bag contains <code>nums[i]</code> balls. You are also given an integer <code>maxOperations</code>.</p>

<p>You can perform the following operation at most <code>maxOperations</code> times:</p>

<ul>
	<li>Take any bag of balls and divide it into two new bags with a <strong>positive </strong>number of balls.

    <ul>
    	<li>For example, a bag of <code>5</code> balls can become two new bags of <code>1</code> and <code>4</code> balls, or two new bags of <code>2</code> and <code>3</code> balls.</li>
    </ul>
    </li>

</ul>

<p>Your penalty is the <strong>maximum</strong> number of balls in a bag. You want to <strong>minimize</strong> your penalty after the operations.</p>

<p>Return <em>the minimum possible penalty after performing the operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [9], maxOperations = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
- Divide the bag with 9 balls into two bags of sizes 6 and 3. [<strong><u>9</u></strong>] -&gt; [6,3].
- Divide the bag with 6 balls into two bags of sizes 3 and 3. [<strong><u>6</u></strong>,3] -&gt; [3,3,3].
The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,8,2], maxOperations = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,<strong><u>8</u></strong>,2] -&gt; [2,4,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,<strong><u>4</u></strong>,4,4,2] -&gt; [2,2,2,4,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,<strong><u>4</u></strong>,4,2] -&gt; [2,2,2,2,2,4,2].
- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,<strong><u>4</u></strong>,2] -&gt; [2,2,2,2,2,2,2,2].
The bag with the most number of balls has 2 balls, so your penalty is 2, and you should return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= maxOperations, nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

This problem requires us to minimize the cost, which is the maximum number of balls in a single bag. As the maximum value increases, the number of operations decreases, making it easier to meet the condition.

Therefore, we can use binary search to enumerate the maximum number of balls in a single bag and determine if it can be achieved within $\textit{maxOperations}$ operations.

Specifically, we define the left boundary of the binary search as $l = 1$ and the right boundary as $r = \max(\textit{nums})$. Then we continuously perform binary search on the middle value $\textit{mid} = \frac{l + r}{2}$. For each $\textit{mid}$, we calculate the number of operations needed. If the number of operations is less than or equal to $\textit{maxOperations}$, it means $\textit{mid}$ meets the condition, and we update the right boundary $r$ to $\textit{mid}$. Otherwise, we update the left boundary $l$ to $\textit{mid} + 1$.

Finally, we return the left boundary $l$.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length and the maximum value of the array $\textit{nums}$, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSize(self, nums: List[int], maxOperations: int) -> int:
        def check(mx: int) -> bool:
            return sum((x - 1) // mx for x in nums) <= maxOperations

        return bisect_left(range(1, max(nums) + 1), True, key=check) + 1
```

#### Java

```java
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int l = 1, r = Arrays.stream(nums).max().getAsInt();
        while (l < r) {
            int mid = (l + r) >> 1;
            long s = 0;
            for (int x : nums) {
                s += (x - 1) / mid;
            }
            if (s <= maxOperations) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSize(vector<int>& nums, int maxOperations) {
        int l = 1, r = ranges::max(nums);
        while (l < r) {
            int mid = (l + r) >> 1;
            long long s = 0;
            for (int x : nums) {
                s += (x - 1) / mid;
            }
            if (s <= maxOperations) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func minimumSize(nums []int, maxOperations int) int {
	r := slices.Max(nums)
	return 1 + sort.Search(r, func(mx int) bool {
		mx++
		s := 0
		for _, x := range nums {
			s += (x - 1) / mx
		}
		return s <= maxOperations
	})
}
```

#### TypeScript

```ts
function minimumSize(nums: number[], maxOperations: number): number {
    let [l, r] = [1, Math.max(...nums)];
    while (l < r) {
        const mid = (l + r) >> 1;
        const s = nums.map(x => ((x - 1) / mid) | 0).reduce((a, b) => a + b);
        if (s <= maxOperations) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_size(nums: Vec<i32>, max_operations: i32) -> i32 {
        let mut l = 1;
        let mut r = *nums.iter().max().unwrap();

        while l < r {
            let mid = (l + r) / 2;
            let mut s: i64 = 0;

            for &x in &nums {
                s += ((x - 1) / mid) as i64;
            }

            if s <= max_operations as i64 {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        l
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} maxOperations
 * @return {number}
 */
var minimumSize = function (nums, maxOperations) {
    let [l, r] = [1, Math.max(...nums)];
    while (l < r) {
        const mid = (l + r) >> 1;
        const s = nums.map(x => ((x - 1) / mid) | 0).reduce((a, b) => a + b);
        if (s <= maxOperations) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
};
```

#### C#

```cs
public class Solution {
    public int MinimumSize(int[] nums, int maxOperations) {
        int l = 1, r = nums.Max();
        while (l < r) {
            int mid = (l + r) >> 1;
            long s = 0;
            foreach (int x in nums) {
                s += (x - 1) / mid;
            }
            if (s <= maxOperations) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
