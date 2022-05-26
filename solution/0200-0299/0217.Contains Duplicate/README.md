# [217. 存在重复元素](https://leetcode-cn.com/problems/contains-duplicate)

[English Version](/solution/0200-0299/0217.Contains%20Duplicate/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个整数数组，判断是否存在重复元素。</p>

<p>如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [1,2,3,1]
<strong>输出:</strong> true</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>[1,2,3,4]
<strong>输出:</strong> false</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入: </strong>[1,1,1,3,3,4,3,2,4,2]
<strong>输出:</strong> true</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        return len(nums) != len(set(nums))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int e : nums) {
            if (set.contains(e)) return true;
            set.add(e);
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
