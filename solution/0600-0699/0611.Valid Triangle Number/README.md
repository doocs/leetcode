# [611. 有效三角形的个数](https://leetcode.cn/problems/valid-triangle-number)

[English Version](/solution/0600-0699/0611.Valid%20Triangle%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含非负整数的数组&nbsp;<code>nums</code> ，返回其中可以组成三角形三条边的三元组个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,2,3,4]
<strong>输出:</strong> 3
<strong>解释:</strong>有效的组合是: 
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,2,3,4]
<strong>输出:</strong> 4</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

一个有效三角形需要满足：**任意两边之和大于第三边**。即：`a + b > c`①, `a + c > b`②, `b + c > a`③。

如果我们将边按从小到大顺序排列，即 `a < b < c`，那么显然 ②③ 成立，我们只需要确保 ① 也成立，就可以形成一个有效三角形。

我们在 `[0, n - 3]` 范围内枚举 i，在 `[i + 1, n - 2]` 范围内枚举 j，在 `[j + 1, n - 1]` 范围内进行二分查找，找出第一个大于等于 `nums[i] + nums[j]` 的下标 left，那么在 `[j + 1, left - 1]` 范围内的 k 满足条件，将其累加到结果 ans。

时间复杂度：$O(n²logn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        nums.sort()
        ans, n = 0, len(nums)
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                k = bisect_left(nums, nums[i] + nums[j], lo=j + 1) - 1
                ans += k - j
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

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, n = nums.length; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int left = j + 1, right = n;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (nums[mid] >= nums[i] + nums[j]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                ans += left - j - 1;
            }
        }
        return ans;
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
	sort.Ints(nums)
	ans := 0
	for i, n := 0, len(nums); i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			left, right := j+1, n
			for left < right {
				mid := (left + right) >> 1
				if nums[mid] >= nums[i]+nums[j] {
					right = mid
				} else {
					left = mid + 1
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
        int ans = 0, n = nums.size();
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int k = lower_bound(nums.begin() + j + 1, nums.end(), nums[i] + nums[j]) - nums.begin() - 1;
                ans += k - j;
            }
        }
        return ans;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn triangle_number(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut res = 0;
        for i in (2..n).rev() {
            let mut left = 0;
            let mut right = i - 1;
            while left < right {
                if nums[left] + nums[right] > nums[i] {
                    res += right - left;
                    right -= 1;
                } else {
                    left += 1;
                }
            }
        }
        res as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
