# [1752. 检查数组是否经排序和轮转得到](https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated)

[English Version](/solution/1700-1799/1752.Check%20if%20Array%20Is%20Sorted%20and%20Rotated/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>nums</code> 。<code>nums</code> 的源数组中，所有元素与 <code>nums</code> 相同，但按非递减顺序排列。</p>

<p>如果&nbsp;<code>nums</code> 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>

<p>源数组中可能存在 <strong>重复项</strong> 。</p>

<p><strong>注意：</strong>我们称数组 <code>A</code> 在轮转 <code>x</code> 个位置后得到长度相同的数组 <code>B</code> ，当它们满足 <code>A[i] == B[(i+x) % A.length]</code> ，其中 <code>%</code> 为取余运算。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,1,2]
<strong>输出：</strong>true
<strong>解释：</strong>[1,2,3,4,5] 为有序的源数组。
可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,3,4]
<strong>输出：</strong>false
<strong>解释：</strong>源数组无法经轮转得到 nums 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>true
<strong>解释：</strong>[1,2,3] 为有序的源数组。
可以轮转 x = 0 个位置（即不轮转）得到 nums 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

要满足题目要求，那么数组 `nums` 中最多只能存在一个元素，其值大于下一个元素，即 $nums[i] \gt nums[i + 1]$。如果存在多个这样的元素，那么数组 `nums` 无法通过轮转得到。

注意，数组 `nums` 最后一个元素的下一个元素是数组 `nums` 的第一个元素。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def check(self, nums: List[int]) -> bool:
        return sum(nums[i - 1] > v for i, v in enumerate(nums)) <= 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean check(int[] nums) {
        int cnt = 0;
        for (int i = 0, n = nums.length; i < n; ++i) {
            if (nums[i] > nums[(i + 1) % n]) {
                ++cnt;
            }
        }
        return cnt <= 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool check(vector<int>& nums) {
        int cnt = 0;
        for (int i = 0, n = nums.size(); i < n; ++i) {
            cnt += nums[i] > (nums[(i + 1) % n]);
        }
        return cnt <= 1;
    }
};
```

### **Go**

```go
func check(nums []int) bool {
	cnt := 0
	for i, v := range nums {
		if v > nums[(i+1)%len(nums)] {
			cnt++
		}
	}
	return cnt <= 1
}
```

### **TypeScript**

```ts
function check(nums: number[]): boolean {
    const n = nums.length;
    return (
        nums.reduce((r, v, i) => r + (v > nums[(i + 1) % n] ? 1 : 0), 0) <= 1
    );
}
```

### **Rust**

```rust
impl Solution {
    pub fn check(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut count = 0;
        for i in 0..n {
            if nums[i] > nums[(i + 1) % n] {
                count += 1;
            }
        }
        count <= 1
    }
}
```

### **C**

```c
bool check(int *nums, int numsSize) {
    int count = 0;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] > nums[(i + 1) % numsSize]) {
            count++;
        }
    }
    return count <= 1;
}
```

### **...**

```

```

<!-- tabs:end -->
