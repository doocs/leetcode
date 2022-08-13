# [17.10. Find Majority Element](https://leetcode.cn/problems/find-majority-element-lcci)

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
        cnt = m = 0
        for v in nums:
            if cnt == 0:
                m, cnt = v, 1
            else:
                cnt += 1 if m == v else -1
        return m if nums.count(m) > len(nums) // 2 else -1
```

### **Java**

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, m = 0;
        for (int v : nums) {
            if (cnt == 0) {
                m = v;
                cnt = 1;
            } else {
                cnt += (m == v ? 1 : -1);
            }
        }
        cnt = 0;
        for (int v : nums) {
            if (m == v) {
                ++cnt;
            }
        }
        return cnt > nums.length / 2 ? m : -1;
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
    let cnt = 0,
        m = 0;
    for (const v of nums) {
        if (cnt == 0) {
            m = v;
            cnt = 1;
        } else {
            cnt += m == v ? 1 : -1;
        }
    }
    cnt = 0;
    for (const v of nums) {
        if (m == v) {
            ++cnt;
        }
    }
    return cnt > nums.length / 2 ? m : -1;
};
```

### **C++**

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, m = 0;
        for (int& v : nums) {
            if (cnt == 0) {
                m = v;
                cnt = 1;
            } else
                cnt += (m == v ? 1 : -1);
        }
        cnt = count(nums.begin(), nums.end(), m);
        return cnt > nums.size() / 2 ? m : -1;
    }
};
```

### **Go**

```go
func majorityElement(nums []int) int {
	cnt, m := 0, 0
	for _, v := range nums {
		if cnt == 0 {
			m, cnt = v, 1
		} else {
			if m == v {
				cnt++
			} else {
				cnt--
			}
		}
	}
	cnt = 0
	for _, v := range nums {
		if m == v {
			cnt++
		}
	}
	if cnt > len(nums)/2 {
		return m
	}
	return -1
}
```

### **C#**

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, m = 0;
        foreach (int v in nums)
        {
            if (cnt == 0)
            {
                m = v;
                cnt = 1;
            }
            else
            {
                cnt += m == v ? 1 : -1;
            }
        }
        cnt = 0;
        foreach (int v in nums)
        {
            if (m == v)
            {
                ++cnt;
            }
        }
        return cnt > nums.Length / 2 ? m : -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
