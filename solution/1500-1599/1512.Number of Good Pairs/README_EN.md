---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1512.Number%20of%20Good%20Pairs/README_EN.md
rating: 1160
source: Weekly Contest 197 Q1
tags:
    - Array
    - Hash Table
    - Math
    - Counting
---

<!-- problem:start -->

# [1512. Number of Good Pairs](https://leetcode.com/problems/number-of-good-pairs)

[中文文档](/solution/1500-1599/1512.Number%20of%20Good%20Pairs/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code>, return <em>the number of <strong>good pairs</strong></em>.</p>

<p>A pair <code>(i, j)</code> is called <em>good</em> if <code>nums[i] == nums[j]</code> and <code>i</code> &lt; <code>j</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1,1,3]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Each pair in the array are <em>good</em>.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

Traverse the array, and for each element $x$, count how many elements before it are equal to $x$. This count represents the number of good pairs formed by $x$ and the previous elements. After traversing the entire array, we obtain the answer.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the array, and $C$ is the range of values in the array. In this problem, $C = 101$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numIdenticalPairs(self, nums: List[int]) -> int:
        ans = 0
        cnt = Counter()
        for x in nums:
            ans += cnt[x]
            cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int ans = 0;
        int[] cnt = new int[101];
        for (int x : nums) {
            ans += cnt[x]++;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numIdenticalPairs(vector<int>& nums) {
        int ans = 0;
        int cnt[101]{};
        for (int& x : nums) {
            ans += cnt[x]++;
        }
        return ans;
    }
};
```

#### Go

```go
func numIdenticalPairs(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		ans += cnt[x]
		cnt[x]++
	}
	return
}
```

#### TypeScript

```ts
function numIdenticalPairs(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    let ans = 0;
    for (const x of nums) {
        ans += cnt[x]++;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_identical_pairs(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut cnt = [0; 101];
        for &x in nums.iter() {
            ans += cnt[x as usize];
            cnt[x as usize] += 1;
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var numIdenticalPairs = function (nums) {
    const cnt = Array(101).fill(0);
    let ans = 0;
    for (const x of nums) {
        ans += cnt[x]++;
    }
    return ans;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function numIdenticalPairs($nums) {
        $ans = 0;
        $cnt = array_fill(0, 101, 0);
        foreach ($nums as $x) {
            $ans += $cnt[$x]++;
        }
        return $ans;
    }
}
```

#### C

```c
int numIdenticalPairs(int* nums, int numsSize) {
    int cnt[101] = {0};
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        ans += cnt[nums[i]]++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
