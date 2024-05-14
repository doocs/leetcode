---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0896.Monotonic%20Array/README.md
tags:
    - 数组
---

# [896. 单调数列](https://leetcode.cn/problems/monotonic-array)

[English Version](/solution/0800-0899/0896.Monotonic%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果数组是单调递增或单调递减的，那么它是&nbsp;<strong>单调 </strong><em>的</em>。</p>

<p>如果对于所有 <code>i &lt;= j</code>，<code>nums[i] &lt;= nums[j]</code>，那么数组 <code>nums</code> 是单调递增的。 如果对于所有 <code>i &lt;= j</code>，<code>nums[i]&gt; = nums[j]</code>，那么数组 <code>nums</code>&nbsp;是单调递减的。</p>

<p>当给定的数组 <code>nums</code>&nbsp;是单调数组时返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,5,4,4]
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,2]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：一次遍历

遍历数组，如果出现递增或递减的情况，记录下来。判断是否出现过递增和递减的情况，如果都出现过，说明不是单调数组，返回 `false`。

否则遍历结束，说明是单调数组，返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        asc = all(a <= b for a, b in pairwise(nums))
        desc = all(a >= b for a, b in pairwise(nums))
        return asc or desc
```

```java
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean asc = false, desc = false;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) {
                asc = true;
            } else if (nums[i - 1] > nums[i]) {
                desc = true;
            }
            if (asc && desc) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool asc = false, desc = false;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                asc = true;
            } else if (nums[i - 1] > nums[i]) {
                desc = true;
            }
            if (asc && desc) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func isMonotonic(nums []int) bool {
	asc, desc := false, false
	for i, x := range nums[1:] {
		if nums[i] < x {
			asc = true
		} else if nums[i] > x {
			desc = true
		}
		if asc && desc {
			return false
		}
	}
	return true
}
```

```ts
function isMonotonic(nums: number[]): boolean {
    let [asc, desc] = [false, false];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            asc = true;
        } else if (nums[i - 1] > nums[i]) {
            desc = true;
        }
        if (asc && desc) {
            return false;
        }
    }
    return true;
}
```

```rust
impl Solution {
    pub fn is_monotonic(nums: Vec<i32>) -> bool {
        let mut asc = false;
        let mut desc = false;
        for i in 1..nums.len() {
            if nums[i - 1] < nums[i] {
                asc = true;
            } else if nums[i - 1] > nums[i] {
                desc = true;
            }
            if asc && desc {
                return false;
            }
        }
        true
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let [asc, desc] = [false, false];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            asc = true;
        } else if (nums[i - 1] > nums[i]) {
            desc = true;
        }
        if (asc && desc) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- end -->
