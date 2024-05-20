---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3151.Special%20Array%20I/README.md
---

<!-- problem:start -->

# [3151. 特殊数组 I](https://leetcode.cn/problems/special-array-i)

[English Version](/solution/3100-3199/3151.Special%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 <strong>特殊数组</strong> 。</p>

<p>Aging 有一个整数数组 <code>nums</code>。如果 <code>nums</code> 是一个 <strong>特殊数组</strong> ，返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1]</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>只有一个元素，所以答案为 <code>true</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,1,4]</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>只有两对相邻元素： <code>(2,1)</code> 和 <code>(1,4)</code>，它们都包含了奇偶性不同的数字，因此答案为 <code>true</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,3,1,6]</span></p>

<p><strong>输出：</strong><span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p><code>nums[1]</code> 和 <code>nums[2]</code> 都是奇数。因此答案为 <code>false</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们从左到右遍历数组，对于每一对相邻元素，如果它们的奇偶性相同，那么数组就不是特殊数组，返回 `false`；否则，数组是特殊数组，返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isArraySpecial(self, nums: List[int]) -> bool:
        return all(a % 2 != b % 2 for a, b in pairwise(nums))
```

#### Java

```java
class Solution {
    public boolean isArraySpecial(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isArraySpecial(vector<int>& nums) {
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isArraySpecial(nums []int) bool {
	for i, x := range nums[1:] {
		if x%2 == nums[i]%2 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isArraySpecial(nums: number[]): boolean {
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] % 2 === nums[i - 1] % 2) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
