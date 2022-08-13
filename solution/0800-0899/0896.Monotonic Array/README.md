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

<!-- 这里可写通用的实现逻辑 -->

遍历数组：

-   出现递增，将 `isIncr` 置为 `true`；
-   出现递减，将 `isDecr` 置为 `true`；
-   既是递增也是递减，提前返回 `false`；
-   正常遍历结束，返回 `true`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        isIncr = isDecr = False
        for i, v in enumerate(nums[1:]):
            if v < nums[i]:
                isIncr = True
            elif v > nums[i]:
                isDecr = True
            if isIncr and isDecr:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isIncr = false, isDecr = false;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                isIncr = true;
            } else if (nums[i] > nums[i - 1]) {
                isDecr = true;
            }
            if (isIncr && isDecr) {
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
    bool isMonotonic(vector<int>& nums) {
        bool isIncr = false;
        bool isDecr = false;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] < nums[i - 1]) isIncr = true;
            if (nums[i] > nums[i - 1]) isDecr = true;
            if (isIncr && isDecr) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func isMonotonic(nums []int) bool {
	isIncr, isDecr := false, false
	for i, v := range nums[1:] {
		if v < nums[i] {
			isIncr = true
		} else if v > nums[i] {
			isDecr = true
		}
		if isIncr && isDecr {
			return false
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let isIncr = false;
    let isDecr = false;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] < nums[i - 1]) {
            isIncr = true;
        }
        if (nums[i] > nums[i - 1]) {
            isDecr = true;
        }
        if (isIncr && isDecr) {
            return false;
        }
    }
    return true;
};
```

### **TypeScript**

```ts
function isMonotonic(nums: number[]): boolean {
    const n = nums.length;
    let isOrder = false;
    let isDecs = false;
    for (let i = 1; i < n; i++) {
        const pre = nums[i - 1];
        const cur = nums[i];
        if (pre < cur) {
            isOrder = true;
        } else if (pre > cur) {
            isDecs = true;
        }
        if (isOrder && isDecs) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_monotonic(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut is_order = false;
        let mut is_decs = false;
        for i in 1..n {
            let pre = nums[i - 1];
            let cur = nums[i];
            if pre < cur {
                is_order = true;
            } else if pre > cur {
                is_decs = true;
            }
            if is_order && is_decs {
                return false;
            }
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->
