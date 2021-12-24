# [17.10. Find Majority Element](https://leetcode-cn.com/problems/find-majority-element-lcci)

[中文文档](/lcci/17.10.Find%20Majority%20Element/README.md)

## Description

<p>A majority element is an element that makes up more than half of the items in an array. Given a positive integers array, find the majority element. If there is no majority element, return -1. Do this in O(N) time and O(1) space.</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>[1,2,5,9,5,9,5,5,5]

<strong>Output: </strong>5</pre>

<p>&nbsp;</p>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>[3,2]

<strong>Output: </strong>-1</pre>

<p>&nbsp;</p>

<p><strong>Example 3: </strong></p>

<pre>

<strong>Input: </strong>[2,2,1,1,1,2,2]

<strong>Output: </strong>2

</pre>

## Solutions

Boyer–Moore majority vote algorithm

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = candidate = 0
        for num in nums:
            if cnt == 0:
                candidate = num
            cnt += (1 if candidate == num else -1)
        return candidate if nums.count(candidate) > len(nums) / 2 else -1
```

### **Java**

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, candidate = 0;
        for (int num : nums) {
            if (cnt == 0) {
                candidate = num;
            }
            cnt += (num == candidate ? 1 : -1);
        }
        cnt = 0;
        for (int num : nums) {
            if (num == candidate) {
                ++cnt;
            }
        }
        return cnt > nums.length / 2 ? candidate : -1;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0;
    let candidate = 0;
    for (const num of nums) {
        if (cnt == 0) {
            candidate = num;
        }
        cnt += candidate == num ? 1 : -1;
    }
    cnt = 0;
    for (const num of nums) {
        if (candidate == num) {
            ++cnt;
        }
    }
    return cnt > nums.length / 2 ? candidate : -1;
};
```

### **C++**

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, candidate = 0;
        for (int num : nums)
        {
            if (cnt == 0) candidate = num;
            cnt += (candidate == num ? 1 : -1);
        }
        cnt = count(nums.begin(), nums.end(), candidate);
        return cnt > nums.size() / 2 ? candidate : -1;
    }
};
```

### **Go**

```go
func majorityElement(nums []int) int {
	var cnt, candidate int
	for _, num := range nums {
		if cnt == 0 {
			candidate = num
		}
		if candidate == num {
			cnt++
		} else {
			cnt--
		}
	}
	cnt = 0
	for _, num := range nums {
		if candidate == num {
			cnt++
		}
	}
	if cnt > len(nums)/2 {
		return candidate
	}
	return -1
}
```

### **C#**

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, candidate = 0;
        foreach (int num in nums)
        {
            if (cnt == 0)
            {
                candidate = num;
            }
            cnt += (candidate == num ? 1 : -1);
        }
        cnt = 0;
        foreach (int num in nums)
        {
            if (candidate == num)
            {
                ++cnt;
            }
        }
        return cnt > nums.Length / 2 ? candidate : -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
