# [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array)

[中文文档](/solution/0000-0099/0033.Search%20in%20Rotated%20Sorted%20Array/README.md)

## Description

<p>There is an integer array <code>nums</code> sorted in ascending order (with <strong>distinct</strong> values).</p>

<p>Prior to being passed to your function, <code>nums</code> is <strong>rotated</strong> at an unknown pivot index <code>k</code> (<code>0 &lt;= k &lt; nums.length</code>) such that the resulting array is <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>0-indexed</strong>). For example, <code>[0,1,2,4,5,6,7]</code> might be rotated at pivot index <code>3</code> and become <code>[4,5,6,7,0,1,2]</code>.</p>

<p>Given the array <code>nums</code> <strong>after</strong> the rotation and an integer <code>target</code>, return <em>the index of </em><code>target</code><em> if it is in </em><code>nums</code><em>, or </em><code>-1</code><em> if it is not in </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 0
<strong>Output:</strong> 4
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 3
<strong>Output:</strong> -1
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1], target = 0
<strong>Output:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>All values of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is guaranteed to be rotated at some pivot.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you achieve this in <code>O(log n)</code> time complexity?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums) - 1
        while l <= r:
            mid = (l + r) >> 1
            if nums[mid] == target:
                return mid
            if nums[mid] > target:
                if nums[mid] >= nums[r] and target < nums[l]:
                    l = mid + 1
                else:
                    r = mid - 1
            else:
                if nums[mid] <= nums[l] and target > nums[r]:
                    r = mid - 1
                else:
                    l = mid + 1
        return -1
```

### **Java**

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                if (nums[mid] >= nums[r] && target < nums[l]) l = mid + 1;
                else r = mid - 1;
            } else {
                if (nums[mid] <= nums[l] && target > nums[r]) r = mid - 1;
                else l = mid + 1;
            }
        }
        return -1;
    }
}
```

### **C++**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```cpp
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                if (nums[mid] >= nums[r] && target < nums[l]) l = mid + 1;
                else r = mid - 1;
            } else {
                if (nums[mid] <= nums[l] && target > nums[r]) r = mid - 1;
                else l = mid + 1;
            }
        }
        return -1;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    let l = 0,
        r = nums.length - 1;
    if (l > r) return -1;
    while (l <= r) {
        let mid = l + Math.floor((r - l) / 2);
        if (nums[mid] === target) return mid;
        else if (
            nums[mid] <= nums[r] &&
            target <= nums[r] &&
            target >= nums[mid]
        )
            l = mid + 1;
        else if (
            nums[mid] >= nums[l] &&
            target <= nums[mid] &&
            target >= nums[l]
        )
            r = mid - 1;
        else if (nums[mid] >= nums[r]) l = mid + 1;
        else if (nums[mid] <= nums[l]) r = mid - 1;
        else return -1;
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->
