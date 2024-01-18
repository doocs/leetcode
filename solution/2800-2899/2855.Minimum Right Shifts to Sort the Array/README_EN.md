# [2855. Minimum Right Shifts to Sort the Array](https://leetcode.com/problems/minimum-right-shifts-to-sort-the-array)

[中文文档](/solution/2800-2899/2855.Minimum%20Right%20Shifts%20to%20Sort%20the%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of length <code>n</code> containing <strong>distinct</strong> positive integers. Return <em>the <strong>minimum</strong> number of <strong>right shifts</strong> required to sort </em><code>nums</code><em> and </em><code>-1</code><em> if this is not possible.</em></p>

<p>A <strong>right shift</strong> is defined as shifting the element at index <code>i</code> to index <code>(i + 1) % n</code>, for all indices.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
After the first right shift, nums = [2,3,4,5,1].
After the second right shift, nums = [1,2,3,4,5].
Now nums is sorted; therefore the answer is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already sorted therefore, the answer is 0.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It&#39;s impossible to sort the array using right shifts.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> contains distinct integers.</li>
</ul>

## Solutions

### Solution 1: Direct Traversal

First, we use a pointer $i$ to traverse the array $nums$ from left to right, finding a continuous increasing sequence until $i$ reaches the end of the array or $nums[i - 1] > nums[i]$. Next, we use another pointer $k$ to traverse the array $nums$ from $i + 1$, finding a continuous increasing sequence until $k$ reaches the end of the array or $nums[k - 1] > nums[k]$ and $nums[k] > nums[0]$. If $k$ reaches the end of the array, it means the array is already increasing, so we return $n - i$; otherwise, we return $-1$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def minimumRightShifts(self, nums: List[int]) -> int:
        n = len(nums)
        i = 1
        while i < n and nums[i - 1] < nums[i]:
            i += 1
        k = i + 1
        while k < n and nums[k - 1] < nums[k] < nums[0]:
            k += 1
        return -1 if k < n else n - i
```

```java
class Solution {
    public int minimumRightShifts(List<Integer> nums) {
        int n = nums.size();
        int i = 1;
        while (i < n && nums.get(i - 1) < nums.get(i)) {
            ++i;
        }
        int k = i + 1;
        while (k < n && nums.get(k - 1) < nums.get(k) && nums.get(k) < nums.get(0)) {
            ++k;
        }
        return k < n ? -1 : n - i;
    }
}
```

```cpp
class Solution {
public:
    int minimumRightShifts(vector<int>& nums) {
        int n = nums.size();
        int i = 1;
        while (i < n && nums[i - 1] < nums[i]) {
            ++i;
        }
        int k = i + 1;
        while (k < n && nums[k - 1] < nums[k] && nums[k] < nums[0]) {
            ++k;
        }
        return k < n ? -1 : n - i;
    }
};
```

```go
func minimumRightShifts(nums []int) int {
	n := len(nums)
	i := 1
	for i < n && nums[i-1] < nums[i] {
		i++
	}
	k := i + 1
	for k < n && nums[k-1] < nums[k] && nums[k] < nums[0] {
		k++
	}
	if k < n {
		return -1
	}
	return n - i
}
```

```ts
function minimumRightShifts(nums: number[]): number {
    const n = nums.length;
    let i = 1;
    while (i < n && nums[i - 1] < nums[i]) {
        ++i;
    }
    let k = i + 1;
    while (k < n && nums[k - 1] < nums[k] && nums[k] < nums[0]) {
        ++k;
    }
    return k < n ? -1 : n - i;
}
```

<!-- tabs:end -->

<!-- end -->
