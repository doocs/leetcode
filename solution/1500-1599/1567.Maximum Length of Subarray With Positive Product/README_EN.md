# [1567. Maximum Length of Subarray With Positive Product](https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product)

[中文文档](/solution/1500-1599/1567.Maximum%20Length%20of%20Subarray%20With%20Positive%20Product/README.md)

## Description

<p>Given an array of integers <code>nums</code>, find the maximum length of a subarray where the product of all its elements is positive.</p>

<p>A subarray of an array is a consecutive sequence of zero or more values taken out of that array.</p>

<p>Return <em>the maximum length of a subarray with positive product</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-2,-3,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The array nums already has a positive product of 24.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,-2,-3,-4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that&#39;ll make the product 0 which is not positive.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3,0,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest subarray with positive product is [-1,-2] or [-2,-3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        f1 = 1 if nums[0] > 0 else 0
        f2 = 1 if nums[0] < 0 else 0
        res = f1
        for num in nums[1:]:
            pf1, pf2 = f1, f2
            if num > 0:
                f1 += 1
                if f2 > 0:
                    f2 += 1
                else:
                    f2 = 0
            elif num < 0:
                pf1, pf2 = f1, f2
                f2 = pf1 + 1
                if pf2 > 0:
                    f1 = pf2 + 1
                else:
                    f1 = 0
            else:
                f1 = 0
                f2 = 0
            res = max(res, f1)
        return res
```

### **Java**

```java
class Solution {
    public int getMaxLen(int[] nums) {
        int f1 = nums[0] > 0 ? 1 : 0;
        int f2 = nums[0] < 0 ? 1 : 0;
        int res = f1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > 0) {
                ++f1;
                f2 = f2 > 0 ? f2 + 1 : 0;
            } else if (nums[i] < 0) {
                int pf1 = f1, pf2 = f2;
                f2 = pf1 + 1;
                f1 = pf2 > 0 ? pf2 + 1 : 0;
            } else {
                f1 = 0;
                f2 = 0;
            }
            res = Math.max(res, f1);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function getMaxLen(nums: number[]): number {
    // 连续正数计数n1, 连续负数计数n2
    let n1 = nums[0] > 0 ? 1 : 0,
        n2 = nums[0] < 0 ? 1 : 0;
    let ans = n1;
    for (let i = 1; i < nums.length; ++i) {
        let cur = nums[i];
        if (cur == 0) {
            (n1 = 0), (n2 = 0);
        } else if (cur > 0) {
            ++n1;
            n2 = n2 > 0 ? n2 + 1 : 0;
        } else {
            let t1 = n1,
                t2 = n2;
            n1 = t2 > 0 ? t2 + 1 : 0;
            n2 = t1 + 1;
        }
        ans = Math.max(ans, n1);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int getMaxLen(vector<int>& nums) {
        int f1 = nums[0] > 0 ? 1 : 0;
        int f2 = nums[0] < 0 ? 1 : 0;
        int res = f1;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] > 0) {
                ++f1;
                f2 = f2 > 0 ? f2 + 1 : 0;
            } else if (nums[i] < 0) {
                int pf1 = f1, pf2 = f2;
                f2 = pf1 + 1;
                f1 = pf2 > 0 ? pf2 + 1 : 0;
            } else {
                f1 = 0;
                f2 = 0;
            }
            res = max(res, f1);
        }
        return res;
    }
};
```

### **Go**

```go
func getMaxLen(nums []int) int {
	f1, f2 := 0, 0
	if nums[0] > 0 {
		f1 = 1
	}
	if nums[0] < 0 {
		f2 = 1
	}
	res := f1
	for i := 1; i < len(nums); i++ {
		if nums[i] > 0 {
			f1++
			if f2 > 0 {
				f2++
			} else {
				f2 = 0
			}
		} else if nums[i] < 0 {
			pf1, pf2 := f1, f2
			f2 = pf1 + 1
			if pf2 > 0 {
				f1 = pf2 + 1
			} else {
				f1 = 0
			}
		} else {
			f1, f2 = 0, 0
		}
		res = max(res, f1)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
