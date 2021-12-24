# [611. Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number)

[中文文档](/solution/0600-0699/0611.Valid%20Triangle%20Number/README.md)

## Description

Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [2,2,3,4]

<b>Output:</b> 3

<b>Explanation:</b>

Valid combinations are:

2,3,4 (using the first 2)

2,3,4 (using the second 2)

2,2,3

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The length of the given array won't exceed 1000.</li>

<li>The integers in the given array are in the range of [0, 1000].</li>

</ol>

</p>

## Solutions

First enumerate two edges, and then use binary search to locate the third edge.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        n = len(nums)
        nums.sort()
        ans = 0
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                left, right = j + 1, n
                while left < right:
                    mid = left + (right - left) // 2
                    if nums[mid] < nums[i] + nums[j]:
                        left = mid + 1
                    else:
                        right = mid
                ans += left - j - 1
        return ans
```

### **Java**

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = n - 1; i >= 2; --i) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    --r;
                } else {
                    ++l;
                }
            }
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function triangleNumber(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let n = nums.length;
    let ans = 0;
    for (let i = n - 1; i >= 2; i--) {
        let left = 0,
            right = i - 1;
        while (left < right) {
            if (nums[left] + nums[right] > nums[i]) {
                ans += right - left;
                right--;
            } else {
                left++;
            }
        }
    }
    return ans;
}
```

### **Go**

```go
func triangleNumber(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	ans := 0
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			left, right := j+1, n
			for left < right {
				mid := int(uint(left+right) >> 1)
				if nums[mid] < nums[i]+nums[j] {
					left = mid + 1
				} else {
					right = mid
				}
			}
			ans += left - j - 1
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int left = j + 1, right = n;
                while (left < right) {
                    int mid = left + right >> 1;
                    if (nums[mid] < nums[i] + nums[j]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                ans += left - j - 1;
            }
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
