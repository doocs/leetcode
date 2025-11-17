---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0169.Majority%20Element/README_EN.md
tags:
    - Array
    - Hash Table
    - Divide and Conquer
    - Counting
    - Sorting
---

<!-- problem:start -->

# [169. Majority Element](https://leetcode.com/problems/majority-element)

[中文文档](/solution/0100-0199/0169.Majority%20Element/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code> of size <code>n</code>, return <em>the majority element</em>.</p>

<p>The majority element is the element that appears more than <code>&lfloor;n / 2&rfloor;</code> times. You may assume that the majority element always exists in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,1,1,1,2,2]
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that a majority element will exist in the array.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Moore Voting Algorithm

The basic steps of the Moore voting algorithm are as follows:

Initialize the element $m$ and initialize the counter $cnt = 0$. Then, for each element $x$ in the input list:

1. If $cnt = 0$, then $m = x$ and $cnt = 1$;
1. Otherwise, if $m = x$, then $cnt = cnt + 1$, otherwise $cnt = cnt - 1$.

In general, the Moore voting algorithm requires **two passes** over the input list. In the first pass, we generate the candidate value $m$, and if there is a majority, the candidate value is the majority value. In the second pass, we simply compute the frequency of the candidate value to confirm whether it is the majority value. Since this problem has clearly stated that there is a majority value, we can directly return $m$ after the first pass, without the need for a second pass to confirm whether it is the majority value.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = m = 0
        for x in nums:
            if cnt == 0:
                m, cnt = x, 1
            else:
                cnt += 1 if m == x else -1
        return m
```

#### Java

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, m = 0;
        for (int x : nums) {
            if (cnt == 0) {
                m = x;
                cnt = 1;
            } else {
                cnt += m == x ? 1 : -1;
            }
        }
        return m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, m = 0;
        for (int& x : nums) {
            if (cnt == 0) {
                m = x;
                cnt = 1;
            } else {
                cnt += m == x ? 1 : -1;
            }
        }
        return m;
    }
};
```

#### Go

```go
func majorityElement(nums []int) int {
	var cnt, m int
	for _, x := range nums {
		if cnt == 0 {
			m, cnt = x, 1
		} else {
			if m == x {
				cnt++
			} else {
				cnt--
			}
		}
	}
	return m
}
```

#### TypeScript

```ts
function majorityElement(nums: number[]): number {
    let cnt: number = 0;
    let m: number = 0;
    for (const x of nums) {
        if (cnt === 0) {
            m = x;
            cnt = 1;
        } else {
            cnt += m === x ? 1 : -1;
        }
    }
    return m;
}
```

#### Rust

```rust
impl Solution {
    pub fn majority_element(nums: Vec<i32>) -> i32 {
        let mut m = 0;
        let mut cnt = 0;
        for &x in nums.iter() {
            if cnt == 0 {
                m = x;
                cnt = 1;
            } else {
                cnt += if m == x { 1 } else { -1 };
            }
        }
        m
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0;
    let m = 0;
    for (const x of nums) {
        if (cnt === 0) {
            m = x;
            cnt = 1;
        } else {
            cnt += m === x ? 1 : -1;
        }
    }
    return m;
};
```

#### C#

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, m = 0;
        foreach (int x in nums) {
            if (cnt == 0) {
                m = x;
                cnt = 1;
            } else {
                cnt += m == x ? 1 : -1;
            }
        }
        return m;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function majorityElement($nums) {
        $m = 0;
        $cnt = 0;
        foreach ($nums as $x) {
            if ($cnt == 0) {
                $m = $x;
            }
            if ($m == $x) {
                $cnt++;
            } else {
                $cnt--;
            }
        }
        return $m;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
