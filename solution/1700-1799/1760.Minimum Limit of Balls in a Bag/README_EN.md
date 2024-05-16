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

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minimumSize(self, nums: List[int], maxOperations: int) -> int:
        def check(mx: int) -> bool:
            return sum((x - 1) // mx for x in nums) <= maxOperations

        return bisect_left(range(1, max(nums)), True, key=check) + 1
```

```java
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = 0;
        for (int x : nums) {
            right = Math.max(right, x);
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            long cnt = 0;
            for (int x : nums) {
                cnt += (x - 1) / mid;
            }
            if (cnt <= maxOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```cpp
class Solution {
public:
    int minimumSize(vector<int>& nums, int maxOperations) {
        int left = 1, right = *max_element(nums.begin(), nums.end());
        while (left < right) {
            int mid = (left + right) >> 1;
            long long cnt = 0;
            for (int x : nums) {
                cnt += (x - 1) / mid;
            }
            if (cnt <= maxOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

```go
func minimumSize(nums []int, maxOperations int) int {
	r := slices.Max(nums)
	return 1 + sort.Search(r, func(mx int) bool {
		mx++
		cnt := 0
		for _, x := range nums {
			cnt += (x - 1) / mx
		}
		return cnt <= maxOperations
	})
}
```

```ts
function minimumSize(nums: number[], maxOperations: number): number {
    let left = 1;
    let right = Math.max(...nums);
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (const x of nums) {
            cnt += ~~((x - 1) / mid);
        }
        if (cnt <= maxOperations) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

```js
/**
 * @param {number[]} nums
 * @param {number} maxOperations
 * @return {number}
 */
var minimumSize = function (nums, maxOperations) {
    let left = 1;
    let right = Math.max(...nums);
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (const x of nums) {
            cnt += ~~((x - 1) / mid);
        }
        if (cnt <= maxOperations) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
