# [896. 单调数列](https://leetcode-cn.com/problems/monotonic-array)

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

-   出现递减，将 `incr` 置为 `false`；
-   出现递增，将 `decr` 置为 `false`；
-   既非递增也非递减，提前返回 `false`；
-   遍历结束，若出现递增或递减，返回 `true`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        incr = decr = True
        for i, v in enumerate(nums[1:]):
            if not incr and not decr:
                return False
            if v < nums[i]:
                incr = False
            elif v > nums[i]:
                decr = False
        return incr or decr
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean incr = true, decr = true;
        for (int i = 1; i < nums.length; ++i) {
            if (!incr && !decr) {
                return false;
            }
            if (nums[i] < nums[i - 1]) {
                incr = false;
            } else if (nums[i] > nums[i - 1]) {
                decr = false;
            }
        }
        return incr || decr;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool incr = true;
        bool decr = true;
        for (int i = 1; i < nums.size(); ++i)
        {
            if (!incr && !decr) return false;
            if (nums[i] < nums[i - 1]) incr = false;
            if (nums[i] > nums[i - 1]) decr = false;
        }
        return incr || decr;
    }
};
```

### **Go**

```go
func isMonotonic(nums []int) bool {
	incr, decr := true, true
	for i, v := range nums[1:] {
		if !incr && !decr {
			return false
		}
		if v < nums[i] {
			incr = false
		} else if v > nums[i] {
			decr = false
		}
	}
	return incr || decr
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let incr = true;
    let decr = true;
    for (let i = 1; i < nums.length; ++i) {
        if (!incr && !decr) {
            return false;
        }
        if (nums[i] < nums[i - 1]) {
            incr = false;
        }
        if (nums[i] > nums[i - 1]) {
            decr = false;
        }
    }
    return incr || decr;
};
```

### **...**

```

```

<!-- tabs:end -->
