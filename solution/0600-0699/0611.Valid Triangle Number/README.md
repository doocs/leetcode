---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0611.Valid%20Triangle%20Number/README.md
tags:
    - 贪心
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [611. 有效三角形的个数](https://leetcode.cn/problems/valid-triangle-number)

[English Version](/solution/0600-0699/0611.Valid%20Triangle%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

一个有效三角形需要满足：**任意两边之和大于第三边**。即：

$$a + b \gt c \tag{1}$$

$$a + c \gt b \tag{2}$$

$$b + c \gt a \tag{3}$$

如果我们将边按从小到大顺序排列，即 $a \leq b \leq c$，那么显然 (2)(3) 成立，我们只需要确保 (1) 也成立，就可以形成一个有效三角形。

我们在 $[0, n - 3]$ 范围内枚举 i，在 $[i + 1, n - 2]$ 范围内枚举 j，在 $[j + 1, n - 1]$ 范围内进行二分查找，找出第一个大于等于 $nums[i] + nums[j]$ 的下标 left，那么在 $[j + 1, left - 1]$ 范围内的 k 满足条件，将其累加到结果 $\textit{ans}$。

时间复杂度 $O(n^2\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 0, n = nums.size();
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int sum = nums[i] + nums[j];
                auto it = ranges::lower_bound(nums.begin() + j + 1, nums.end(), sum);
                int k = int(it - nums.begin()) - 1;
                ans += max(0, k - j);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func triangleNumber(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	ans := 0
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			sum := nums[i] + nums[j]
			k := sort.SearchInts(nums[j+1:], sum) + j + 1 - 1
			if k > j {
				ans += k - j
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function triangleNumber(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; i++) {
        for (let j = i + 1; j < n - 1; j++) {
            const sum = nums[i] + nums[j];
            let k = _.sortedIndex(nums, sum, j + 1) - 1;
            if (k > j) {
                ans += k - j;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn triangle_number(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut ans = 0;
        for i in 0..n.saturating_sub(2) {
            for j in i + 1..n.saturating_sub(1) {
                let sum = nums[i] + nums[j];
                let mut left = j + 1;
                let mut right = n;
                while left < right {
                    let mid = (left + right) / 2;
                    if nums[mid] < sum {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                if left > j + 1 {
                    ans += (left - 1 - j) as i32;
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
