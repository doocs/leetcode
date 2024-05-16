---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2239.Find%20Closest%20Number%20to%20Zero/README_EN.md
rating: 1256
source: Biweekly Contest 76 Q1
tags:
    - Array
---

<!-- problem:start -->

# [2239. Find Closest Number to Zero](https://leetcode.com/problems/find-closest-number-to-zero)

[中文文档](/solution/2200-2299/2239.Find%20Closest%20Number%20to%20Zero/README.md)

## Description

<p>Given an integer array <code>nums</code> of size <code>n</code>, return <em>the number with the value <strong>closest</strong> to </em><code>0</code><em> in </em><code>nums</code>. If there are multiple answers, return <em>the number with the <strong>largest</strong> value</em>.</p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-4,-2,1,4,8]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The distance from -4 to 0 is |-4| = 4.
The distance from -2 to 0 is |-2| = 2.
The distance from 1 to 0 is |1| = 1.
The distance from 4 to 0 is |4| = 4.
The distance from 8 to 0 is |8| = 8.
Thus, the closest number to 0 in the array is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,-1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 1 and -1 are both the closest numbers to 0, so 1 being larger is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def findClosestNumber(self, nums: List[int]) -> int:
        ans, d = 0, inf
        for x in nums:
            if (y := abs(x)) < d or (y == d and x > ans):
                ans, d = x, y
        return ans
```

```java
class Solution {
    public int findClosestNumber(int[] nums) {
        int ans = 0, d = 1 << 30;
        for (int x : nums) {
            int y = Math.abs(x);
            if (y < d || (y == d && x > ans)) {
                ans = x;
                d = y;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findClosestNumber(vector<int>& nums) {
        int ans = 0, d = 1 << 30;
        for (int x : nums) {
            int y = abs(x);
            if (y < d || (y == d && x > ans)) {
                ans = x;
                d = y;
            }
        }
        return ans;
    }
};
```

```go
func findClosestNumber(nums []int) int {
	ans, d := 0, 1<<30
	for _, x := range nums {
		if y := abs(x); y < d || (y == d && x > ans) {
			ans, d = x, y
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function findClosestNumber(nums: number[]): number {
    let [ans, d] = [0, 1 << 30];
    for (const x of nums) {
        const y = Math.abs(x);
        if (y < d || (y == d && x > ans)) {
            [ans, d] = [x, y];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
