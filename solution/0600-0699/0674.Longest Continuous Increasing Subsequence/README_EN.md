---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0674.Longest%20Continuous%20Increasing%20Subsequence/README_EN.md
tags:
    - Array
---

<!-- problem:start -->

# [674. Longest Continuous Increasing Subsequence](https://leetcode.com/problems/longest-continuous-increasing-subsequence)

[中文文档](/solution/0600-0699/0674.Longest%20Continuous%20Increasing%20Subsequence/README.md)

## Description

<!-- description:start -->

<p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest <strong>continuous increasing subsequence</strong> (i.e. subarray)</em>. The subsequence must be <strong>strictly</strong> increasing.</p>

<p>A <strong>continuous increasing subsequence</strong> is defined by two indices <code>l</code> and <code>r</code> (<code>l &lt; r</code>) such that it is <code>[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]</code> and for each <code>l &lt;= i &lt; r</code>, <code>nums[i] &lt; nums[i + 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,4,7]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
increasing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: One-pass Scan

We can traverse the array $nums$, using a variable $cnt$ to record the length of the current consecutive increasing sequence. Initially, $cnt = 1$.

Then, we start from index $i = 1$ and traverse the array $nums$ to the right. Each time we traverse, if $nums[i - 1] < nums[i]$, it means that the current element can be added to the consecutive increasing sequence, so we set $cnt = cnt + 1$, and then update the answer to $ans = \max(ans, cnt)$. Otherwise, it means that the current element cannot be added to the consecutive increasing sequence, so we set $cnt = 1$.

After the traversal ends, we return the answer $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        ans = cnt = 1
        for i, x in enumerate(nums[1:]):
            if nums[i] < x:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 1
        return ans
```

#### Java

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        for (int i = 1, cnt = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
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
    int findLengthOfLCIS(vector<int>& nums) {
        int ans = 1;
        for (int i = 1, cnt = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                ans = max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLengthOfLCIS(nums []int) int {
	ans, cnt := 1, 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findLengthOfLCIS(nums: number[]): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_length_of_lcis(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        let mut cnt = 1;
        for i in 1..nums.len() {
            if nums[i - 1] < nums[i] {
                ans = ans.max(cnt + 1);
                cnt += 1;
            } else {
                cnt = 1;
            }
        }
        ans
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
    function findLengthOfLCIS($nums) {
        $ans = 1;
        $cnt = 1;
        for ($i = 1; $i < count($nums); ++$i) {
            if ($nums[$i - 1] < $nums[$i]) {
                $ans = max($ans, ++$cnt);
            } else {
                $cnt = 1;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Two Pointers

We can also use two pointers $i$ and $j$ to find each consecutive increasing sequence, and find the length of the longest consecutive increasing sequence as the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        ans, n = 1, len(nums)
        i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j - 1] < nums[j]:
                j += 1
            ans = max(ans, j - i)
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        int n = nums.length;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j - 1] < nums[j]) {
                ++j;
            }
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int ans = 1;
        int n = nums.size();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j - 1] < nums[j]) {
                ++j;
            }
            ans = max(ans, j - i);
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func findLengthOfLCIS(nums []int) int {
	ans := 1
	n := len(nums)
	for i := 0; i < n; {
		j := i + 1
		for j < n && nums[j-1] < nums[j] {
			j++
		}
		ans = max(ans, j-i)
		i = j
	}
	return ans
}
```

#### TypeScript

```ts
function findLengthOfLCIS(nums: number[]): number {
    let ans = 1;
    const n = nums.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && nums[j - 1] < nums[j]) {
            ++j;
        }
        ans = Math.max(ans, j - i);
        i = j;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_length_of_lcis(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            while j < n && nums[j - 1] < nums[j] {
                j += 1;
            }
            ans = ans.max(j - i);
            i = j;
        }
        ans as i32
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
    function findLengthOfLCIS($nums) {
        $ans = 1;
        $n = count($nums);
        $i = 0;
        while ($i < $n) {
            $j = $i + 1;
            while ($j < $n && $nums[$j - 1] < $nums[$j]) {
                $j++;
            }
            $ans = max($ans, $j - $i);
            $i = $j;
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
