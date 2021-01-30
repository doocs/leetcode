# [349. 两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays)

[English Version](/solution/0300-0399/0349.Intersection%20of%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个数组，编写一个函数来计算它们的交集。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>nums1 = [1,2,2,1], nums2 = [2,2]
<strong>输出: </strong>[2]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>输出: </strong>[9,4]</pre>

<p><strong>说明:</strong></p>

<ul>
	<li>输出结果中的每个元素一定是唯一的。</li>
	<li>我们可以不考虑输出结果的顺序。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        s1, s2 = set(nums1), set(nums2)
        return list(s1 & s2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = transfer(nums1);
        Set<Integer> s2 = transfer(nums2);
        s1.retainAll(s2);
        int[] output = new int[s1.size()];
        int i = 0;
        for (Integer e : s1) {
            output[i++] = e;
        }
        return output;
    }

    private Set<Integer> transfer(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int e : nums) {
            s.add(e);
        }
        return s;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
