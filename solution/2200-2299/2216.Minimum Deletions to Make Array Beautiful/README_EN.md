# [2216. Minimum Deletions to Make Array Beautiful](https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful)

[中文文档](/solution/2200-2299/2216.Minimum%20Deletions%20to%20Make%20Array%20Beautiful/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. The array <code>nums</code> is <strong>beautiful</strong> if:</p>

<ul>
	<li><code>nums.length</code> is even.</li>
	<li><code>nums[i] != nums[i + 1]</code> for all <code>i % 2 == 0</code>.</li>
</ul>

<p>Note that an empty array is considered beautiful.</p>

<p>You can delete any number of elements from <code>nums</code>. When you delete an element, all the elements to the right of the deleted element will be <strong>shifted one unit to the left</strong> to fill the gap created and all the elements to the left of the deleted element will remain <strong>unchanged</strong>.</p>

<p>Return <em>the <strong>minimum</strong> number of elements to delete from </em><code>nums</code><em> to make it </em><em>beautiful.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,3,5]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can delete either <code>nums[0]</code> or <code>nums[1]</code> to make <code>nums</code> = [1,2,3,5] which is beautiful. It can be proven you need at least 1 deletion to make <code>nums</code> beautiful.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,2,3,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can delete <code>nums[0]</code> and <code>nums[5]</code> to make nums = [1,2,2,3] which is beautiful. It can be proven you need at least 2 deletions to make nums beautiful.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy

According to the problem description, we know that a beautiful array has an even number of elements, and if we divide every two adjacent elements in this array into a group, then the two elements in each group are not equal. This means that the elements within a group cannot be repeated, but the elements between groups can be repeated.

Therefore, we consider traversing the array from left to right. As long as we encounter two adjacent elements that are equal, we delete one of them, that is, the deletion count increases by one; otherwise, we can keep these two elements.

Finally, we check whether the length of the array after deletion is even. If not, it means that we need to delete one more element to make the final array length even.

The time complexity is $O(n)$, where $n$ is the length of the array. We only need to traverse the array once. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minDeletion(self, nums: List[int]) -> int:
        n = len(nums)
        i = ans = 0
        while i < n - 1:
            if nums[i] == nums[i + 1]:
                ans += 1
                i += 1
            else:
                i += 2
        ans += (n - ans) % 2
        return ans
```

```java
class Solution {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                ++ans;
            } else {
                ++i;
            }
        }
        ans += (n - ans) % 2;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minDeletion(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                ++ans;
            } else {
                ++i;
            }
        }
        ans += (n - ans) % 2;
        return ans;
    }
};
```

```go
func minDeletion(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n-1; i++ {
		if nums[i] == nums[i+1] {
			ans++
		} else {
			i++
		}
	}
	ans += (n - ans) % 2
	return
}
```

```ts
function minDeletion(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 1; ++i) {
        if (nums[i] === nums[i + 1]) {
            ++ans;
        } else {
            ++i;
        }
    }
    ans += (n - ans) % 2;
    return ans;
}
```

```rust
impl Solution {
    pub fn min_deletion(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n - 1 {
            if nums[i] == nums[i + 1] {
                ans += 1;
                i += 1;
            } else {
                i += 2;
            }
        }
        ans += (n - ans) % 2;
        ans as i32
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def minDeletion(self, nums: List[int]) -> int:
        n = len(nums)
        ans = i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j] == nums[i]:
                j += 1
                ans += 1
            i = j + 1
        ans += (n - ans) % 2
        return ans
```

```java
class Solution {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) {
                ++j;
                ++ans;
            }
            i = j + 1;
        }
        ans += (n - ans) % 2;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minDeletion(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) {
                ++j;
                ++ans;
            }
            i = j + 1;
        }
        ans += (n - ans) % 2;
        return ans;
    }
};
```

```go
func minDeletion(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n; {
		j := i + 1
		for ; j < n && nums[j] == nums[i]; j++ {
			ans++
		}
		i = j + 1
	}
	ans += (n - ans) % 2
	return
}
```

```ts
function minDeletion(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        for (; j < n && nums[j] === nums[i]; ++j) {
            ++ans;
        }
        i = j + 1;
    }
    ans += (n - ans) % 2;
    return ans;
}
```

```rust
impl Solution {
    pub fn min_deletion(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            while j < n && nums[j] == nums[i] {
                ans += 1;
                j += 1;
            }
            i = j + 1;
        }
        ans += (n - ans) % 2;
        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
