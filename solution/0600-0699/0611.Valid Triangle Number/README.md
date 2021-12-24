# [611. 有效三角形的个数](https://leetcode-cn.com/problems/valid-triangle-number)

[English Version](/solution/0600-0699/0611.Valid%20Triangle%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [2,2,3,4]
<strong>输出:</strong> 3
<strong>解释:</strong>
有效的组合是:
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>数组长度不超过1000。</li>
	<li>数组里整数的范围为 [0, 1000]。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针加二分，先枚举两条边，然后利用二分查找定位第三条边。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
