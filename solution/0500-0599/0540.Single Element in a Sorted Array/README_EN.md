# [540. Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array)

[中文文档](/solution/0500-0599/0540.Single%20Element%20in%20a%20Sorted%20Array/README.md)

## Description

<p>You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly&nbsp;once. Find this single element that appears only once.</p>

<p><b>Follow up:</b> Your solution should run in O(log n) time and O(1) space.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [1,1,2,3,3,4,4,8,8]

<strong>Output:</strong> 2

</pre><p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [3,3,7,7,10,11,11]

<strong>Output:</strong> 10

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10^5</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if (mid & 1) == 1:
                mid -= 1
            if nums[mid] == nums[mid + 1]:
                left = mid + 2
            else:
                right = mid
        return nums[left]
```

```python
class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if (mid % 2 == 0 and nums[mid] != nums[mid + 1]) or (mid % 2 != 0 and nums[mid] != nums[mid - 1]):
                right = mid
            else:
                left = mid + 1
        return nums[left]
```

### **Java**

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if ((mid & 1) == 1) {
                --mid;
            }
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
```

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if ((mid % 2 == 0 && nums[mid] != nums[mid + 1]) || (mid % 2 != 0 && nums[mid] != nums[mid - 1])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
```

### **TypeScript**

```ts
function singleNonDuplicate(nums: number[]): number {
    let left = 0,
        right = nums.length - 1;
    while (left < right) {
        let mid = (left + right) >> 1;
        if ((mid & 1) == 1) --mid;
        if (nums[mid] == nums[mid + 1]) {
            left = mid + 2;
        } else {
            right = mid;
        }
    }
    return nums[left];
}
```

### **C++**

```cpp
class Solution {
public:
    int singleNonDuplicate(vector<int> &nums) {
        int left = 0, right = nums.size() - 1;
        while (left < right)
        {
            int mid = left + right >> 1;
            if ((mid % 2 == 0 && nums[mid] != nums[mid + 1]) || (mid % 2 != 0 && nums[mid] != nums[mid - 1]))
                right = mid;
            else
                left = mid + 1;
        }
        return nums[left];
    }
};
```

### **Go**

```go
func singleNonDuplicate(nums []int) int {
	left, right := 0, len(nums)-1
	for left < right {
		mid := (left + right) >> 1
		if (mid & 1) == 1 {
			mid--
		}
		if nums[mid] == nums[mid+1] {
			left = mid + 2
		} else {
			right = mid
		}
	}
	return nums[left]
}
```

### **...**

```

```

<!-- tabs:end -->
