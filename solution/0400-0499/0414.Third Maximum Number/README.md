# [414. 第三大的数](https://leetcode.cn/problems/third-maximum-number)

[English Version](/solution/0400-0499/0414.Third%20Maximum%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非空数组，返回此数组中 <strong>第三大的数</strong> 。如果不存在，则返回数组中最大的数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[3, 2, 1]
<strong>输出：</strong>1
<strong>解释：</strong>第三大的数是 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[1, 2]
<strong>输出：</strong>2
<strong>解释：</strong>第三大的数不存在, 所以返回最大的数 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>[2, 2, 3, 1]
<strong>输出：</strong>1
<strong>解释：</strong>注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能设计一个时间复杂度 <code>O(n)</code> 的解决方案吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

定义 m1, m2, m3 分别表示数组的第 1 大、第 2 大、第 3 大的数，初始化为一个足够小的数。

遍历数组每个元素 num：

-   若 num 与前三大数中的某一个相等，直接跳过，因为我们要找的是在所有不同数字中的第三大。
-   若 num 比 m1 大，说明找到了一个最大的数，此时我们要把 num 赋值给 m1，即 m1 = num，但在做赋值操作之前，我们要先把旧值赋给 m2，依次类推，即：`m3, m2, m1 = m2, m1, num`。
-   对于 num 比 m2、m3 大的情况，也按照上面的赋值方法进行处理。

遍历结束，判断 m3 这个值是否在初始化之后改变过，若是，说明找到了第 3 大数，返回 m3，否则返回 m1。

本方法时间复杂度 O(n)，空间复杂度 O(1)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def thirdMax(self, nums: List[int]) -> int:
        m1 = m2 = m3 = -inf
        for num in nums:
            if num in [m1, m2, m3]:
                continue
            if num > m1:
                m3, m2, m1 = m2, m1, num
            elif num > m2:
                m3, m2 = m2, num
            elif num > m3:
                m3 = num
        return m3 if m3 != -inf else m1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int thirdMax(int[] nums) {
        long m1 = Long.MIN_VALUE;
        long m2 = Long.MIN_VALUE;
        long m3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) {
                continue;
            }
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int) (m3 != Long.MIN_VALUE ? m3 : m1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int thirdMax(vector<int>& nums) {
        long m1 = LONG_MIN, m2 = LONG_MIN, m3 = LONG_MIN;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) continue;
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int)(m3 != LONG_MIN ? m3 : m1);
    }
};
```

### **Go**

```go
func thirdMax(nums []int) int {
	m1, m2, m3 := math.MinInt64, math.MinInt64, math.MinInt64
	for _, num := range nums {
		if num == m1 || num == m2 || num == m3 {
			continue
		}
		if num > m1 {
			m3, m2, m1 = m2, m1, num
		} else if num > m2 {
			m3, m2 = m2, num
		} else if num > m3 {
			m3 = num
		}
	}
	if m3 != math.MinInt64 {
		return m3
	}
	return m1
}
```

### **...**

```

```

<!-- tabs:end -->
