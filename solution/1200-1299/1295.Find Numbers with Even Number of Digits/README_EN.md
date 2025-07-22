---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1295.Find%20Numbers%20with%20Even%20Number%20of%20Digits/README_EN.md
rating: 1139
source: Weekly Contest 168 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits)

[中文文档](/solution/1200-1299/1295.Find%20Numbers%20with%20Even%20Number%20of%20Digits/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code> of integers, return how many of them contain an <strong>even number</strong> of digits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,345,2,6,7896]
<strong>Output:</strong> 2
<strong>Explanation: 
</strong>12 contains 2 digits (even number of digits).&nbsp;
345 contains 3 digits (odd number of digits).&nbsp;
2 contains 1 digit (odd number of digits).&nbsp;
6 contains 1 digit (odd number of digits).&nbsp;
7896 contains 4 digits (even number of digits).&nbsp;
Therefore only 12 and 7896 contain an even number of digits.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [555,901,482,1771]
<strong>Output:</strong> 1 
<strong>Explanation: </strong>
Only 1771 contains an even number of digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We traverse each element $x$ in the array $\textit{nums}$. For the current element $x$, we directly convert it to a string and then check if its length is even. If it is, we increment the answer by one.

After the traversal is complete, we return the answer.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(\log M)$. Here, $n$ is the length of the array $\textit{nums}$, and $M$ is the maximum value of the elements in the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        return sum(len(str(x)) % 2 == 0 for x in nums)
```

#### Java

```java
class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            if (String.valueOf(x).length() % 2 == 0) {
                ++ans;
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
    int findNumbers(vector<int>& nums) {
        int ans = 0;
        for (int& x : nums) {
            ans += to_string(x).size() % 2 == 0;
        }
        return ans;
    }
};
```

#### Go

```go
func findNumbers(nums []int) (ans int) {
	for _, x := range nums {
		if len(strconv.Itoa(x))%2 == 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function findNumbers(nums: number[]): number {
    return nums.filter(x => x.toString().length % 2 === 0).length;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_numbers(nums: Vec<i32>) -> i32 {
        nums.iter().filter(|&x| x.to_string().len() % 2 == 0).count() as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findNumbers = function (nums) {
    return nums.filter(x => x.toString().length % 2 === 0).length;
};
```

#### C#

```cs
public class Solution {
    public int FindNumbers(int[] nums) {
        return nums.Count(x => x.ToString().Length % 2 == 0);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
