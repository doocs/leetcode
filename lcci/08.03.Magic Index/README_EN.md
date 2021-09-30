# [08.03. Magic Index](https://leetcode-cn.com/problems/magic-index-lcci)

[中文文档](/lcci/08.03.Magic%20Index/README.md)

## Description

<p>A magic index in an array <code>A[0...n-1]</code> is defined to be an index such that <code>A[i] = i</code>. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A. If not, return -1. If there are more than one magic index, return the smallest one.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: nums = [0, 2, 3, 4, 5]

<strong> Output</strong>: 0

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: nums = [1, 1, 1]

<strong> Output</strong>: 1

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= nums.length &lt;= 1000000</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMagicIndex(self, nums: List[int]) -> int:
        def find(nums, left, right):
            if left > right:
                return -1
            mid = (left + right) >> 1
            left_index = find(nums, left, mid - 1)
            if left_index != -1:
                return left_index
            if nums[mid] == mid:
                return mid
            return find(nums, mid + 1, right)

        return find(nums, 0, len(nums) - 1)
```

### **Java**

```java
class Solution {
    public int findMagicIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        return find(nums, left, right);
    }

    private int find(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMagicIndex = function(nums) {
    return helper(nums, 0, nums.length - 1);
};

function helper(nums, left, right) {
    if (left > right) return -1;
    let mid = Math.floor((left + right) / 2);
    let leftIndex = helper(nums, left, mid - 1);
    if (leftIndex != -1) return leftIndex;
    if (nums[mid] == mid) return mid;
    return helper(nums, mid + 1, right);
}
```

### **C++**

```cpp
class Solution {
public:
    int findMagicIndex(vector<int>& nums) {
        return find(nums, 0, nums.size() - 1);
    }

    int find(vector<int>& nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + right >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
};
```

### **Go**

```go
func findMagicIndex(nums []int) int {
	return find(nums, 0, len(nums)-1)
}

func find(nums []int, left, right int) int {
	if left > right {
		return -1
	}
	mid := (left + right) >> 1
	leftIndex := find(nums, left, mid-1)
	if leftIndex != -1 {
		return leftIndex
	}
	if nums[mid] == mid {
		return mid
	}
	return find(nums, mid+1, right)
}
```

### **...**

```

```

<!-- tabs:end -->
