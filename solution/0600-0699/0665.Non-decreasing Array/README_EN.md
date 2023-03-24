# [665. Non-decreasing Array](https://leetcode.com/problems/non-decreasing-array)

[中文文档](/solution/0600-0699/0665.Non-decreasing%20Array/README.md)

## Description

<p>Given an array <code>nums</code> with <code>n</code> integers, your task is to check if it could become non-decreasing by modifying <strong>at most one element</strong>.</p>

<p>We define an array is non-decreasing if <code>nums[i] &lt;= nums[i + 1]</code> holds for every <code>i</code> (<strong>0-based</strong>) such that (<code>0 &lt;= i &lt;= n - 2</code>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,3]
<strong>Output:</strong> true
<strong>Explanation:</strong> You could modify the first 4 to 1 to get a non-decreasing array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,1]
<strong>Output:</strong> false
<strong>Explanation:</strong> You cannot get a non-decreasing array by modifying at most one element.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkPossibility(self, nums: List[int]) -> bool:
        def is_sorted(nums: List[int]) -> bool:
            return all(a <= b for a, b in pairwise(nums))

        n = len(nums)
        for i in range(n - 1):
            a, b = nums[i], nums[i + 1]
            if a > b:
                nums[i] = b
                if is_sorted(nums):
                    return True
                nums[i] = nums[i + 1] = a
                return is_sorted(nums)
        return True
```

### **Java**

```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            int a = nums[i], b = nums[i + 1];
            if (a > b) {
                nums[i] = b;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = a;
                nums[i + 1] = a;
                return isSorted(nums);
            }
        }
        return true;
    }

    private boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkPossibility(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n - 1; ++i) {
            int a = nums[i], b = nums[i + 1];
            if (a > b) {
                nums[i] = b;
                if (is_sorted(nums.begin(), nums.end())) {
                    return true;
                }
                nums[i] = a;
                nums[i + 1] = a;
                return is_sorted(nums.begin(), nums.end());
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkPossibility(nums []int) bool {
	isSorted := func(nums []int) bool {
		for i, b := range nums[1:] {
			if nums[i] > b {
				return false
			}
		}
		return true
	}
	for i := 0; i < len(nums)-1; i++ {
		a, b := nums[i], nums[i+1]
		if a > b {
			nums[i] = b
			if isSorted(nums) {
				return true
			}
			nums[i] = a
			nums[i+1] = a
			return isSorted(nums)
		}
	}
	return true
}
```

### **TypeScript**

```ts
function checkPossibility(nums: number[]): boolean {
    const isSorted = (nums: number[]) => {
        for (let i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0; i < nums.length - 1; ++i) {
        const a = nums[i],
            b = nums[i + 1];
        if (a > b) {
            nums[i] = b;
            if (isSorted(nums)) {
                return true;
            }
            nums[i] = a;
            nums[i + 1] = a;
            return isSorted(nums);
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
