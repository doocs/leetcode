# [169. 多数元素](https://leetcode-cn.com/problems/majority-element)

[English Version](/solution/0100-0199/0169.Majority%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个大小为 <em>n </em>的数组，找到其中的多数元素。多数元素是指在数组中出现次数 <strong>大于</strong> <code>⌊ n/2 ⌋</code> 的元素。</p>

<p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[3,2,3]
<strong>输出：</strong>3</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[2,2,1,1,1,2,2]
<strong>输出：</strong>2
</pre>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

摩尔投票法。时间复杂度 O(n)，空间复杂度 O(1)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = major = 0
        for num in nums:
            if cnt == 0:
                major = num
                cnt = 1
            else:
                cnt += (1 if major == num else -1)
        return major
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0, major = 0;
        for (int num : nums) {
            if (cnt == 0) {
                major = num;
                cnt = 1;
            } else {
                cnt += (major == num ? 1 : -1);
            }
        }
        return major;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function (nums) {
    let cnt = 0;
    let major = 0;
    for (const num of nums) {
        if (cnt == 0) {
            major = num;
            cnt = 1;
        } else {
            cnt += major == num ? 1 : -1;
        }
    }
    return major;
};
```

### **C++**

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int cnt = 0, major = 0;
        for (int num : nums) {
            if (cnt == 0) {
                major = num;
                cnt = 1;
            } else {
                cnt += (major == num ? 1 : -1);
            }
        }
        return major;
    }
};
```

### **C#**

```cs
public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, major = 0;
        foreach (int num in nums)
        {
            if (cnt == 0)
            {
                major = num;
                cnt = 1;
            }
            else
            {
                cnt += (major == num ? 1 : -1);
            }
        }
        return major;
    }
}
```

### **Go**

```go
func majorityElement(nums []int) int {
    var cnt, major int
    for _, num := range nums {
        if cnt == 0 {
            major = num
            cnt = 1
        } else {
            if major == num {
                cnt++
            } else {
                cnt--
            }
        }
    }
    return major
}
```

### **...**

```

```

<!-- tabs:end -->
