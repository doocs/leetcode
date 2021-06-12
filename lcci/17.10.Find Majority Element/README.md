# [面试题 17.10. 主要元素](https://leetcode-cn.com/problems/find-majority-element-lcci)

[English Version](/lcci/17.10.Find%20Majority%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>如果数组中多一半的数都是同一个，则称之为主要元素。给定一个<strong>整数</strong>数组，找到它的主要元素。若没有，返回-1。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[1,2,5,9,5,9,5,5,5]
<strong>输出：</strong>5</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[3,2]
<strong>输出：</strong>-1</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[2,2,1,1,1,2,2]
<strong>输出：</strong>2</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong><br>
你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？</p>

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
var majorityElement = function(nums) {
    let cnt = 0;
    let major = 0;
    for (const num of nums) {
        if (cnt == 0) {
            major = num;
            cnt = 1;
        } else {
            cnt += (major == num ? 1 : -1);
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

### **...**

```

```

<!-- tabs:end -->
