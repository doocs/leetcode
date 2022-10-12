# [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number)

[中文文档](/solution/0200-0299/0287.Find%20the%20Duplicate%20Number/README.md)

## Description

<p>Given an array of integers <code>nums</code> containing&nbsp;<code>n + 1</code> integers where each integer is in the range <code>[1, n]</code> inclusive.</p>

<p>There is only <strong>one repeated number</strong> in <code>nums</code>, return <em>this&nbsp;repeated&nbsp;number</em>.</p>

<p>You must solve the problem <strong>without</strong> modifying the array <code>nums</code>&nbsp;and uses only constant extra space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,4,2,2]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,3,4,2]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums.length == n + 1</code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li>All the integers in <code>nums</code> appear only <strong>once</strong> except for <strong>precisely one integer</strong> which appears <strong>two or more</strong> times.</li>
</ul>

<p>&nbsp;</p>
<p><b>Follow up:</b></p>

<ul>
	<li>How can we prove that at least one duplicate number must exist in <code>nums</code>?</li>
	<li>Can you solve the problem in linear runtime complexity?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        left, right = 1, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            cnt = sum(v <= mid for v in nums)
            if cnt > mid:
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int v : nums) {
                if (v <= mid) {
                    ++cnt;
                }
            }
            if (cnt > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int left = 1, right = nums.size() - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int& v : nums)
                if (v <= mid)
                    ++cnt;
            if (cnt > mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

### **Go**

```go
func findDuplicate(nums []int) int {
	left, right := 1, len(nums)-1
	for left < right {
		mid := (left + right) >> 1
		cnt := 0
		for _, v := range nums {
			if v <= mid {
				cnt++
			}
		}
		if cnt > mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findDuplicate = function (nums) {
    let left = 1,
        right = nums.length - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (let v of nums) {
            if (v <= mid) {
                ++cnt;
            }
        }
        if (cnt > mid) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
```

### **TypeScript**

```ts
function findDuplicate(nums: number[]): number {
    let left = 1,
        right = nums.length - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (let v of nums) {
            if (v <= mid) {
                ++cnt;
            }
        }
        if (cnt > mid) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
