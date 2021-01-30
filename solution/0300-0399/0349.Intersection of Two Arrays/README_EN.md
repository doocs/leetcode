# [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays)

[中文文档](/solution/0300-0399/0349.Intersection%20of%20Two%20Arrays/README.md)

## Description

<p>Given two arrays, write a function to compute their intersection.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>nums1 = <span id="example-input-1-1">[1,2,2,1]</span>, nums2 = <span id="example-input-1-2">[2,2]</span>

<strong>Output: </strong><span id="example-output-1">[2]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>nums1 = <span id="example-input-2-1">[4,9,5]</span>, nums2 = <span id="example-input-2-2">[9,4,9,8,4]</span>

<strong>Output: </strong><span id="example-output-2">[9,4]</span></pre>

</div>

<p><b>Note:</b></p>

<ul>
    <li>Each element in the result must be unique.</li>
    <li>The result can be in any order.</li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        s1, s2 = set(nums1), set(nums2)
        return list(s1 & s2)
```

### **Java**

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
