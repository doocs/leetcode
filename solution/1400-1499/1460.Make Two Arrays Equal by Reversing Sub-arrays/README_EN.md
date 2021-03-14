# [1460. Make Two Arrays Equal by Reversing Sub-arrays](https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays)

[中文文档](/solution/1400-1499/1460.Make%20Two%20Arrays%20Equal%20by%20Reversing%20Sub-arrays/README.md)

## Description

<p>Given two integer arrays of equal length <code>target</code> and <code>arr</code>.</p>

<p>In one step, you can select any <strong>non-empty sub-array</strong> of <code>arr</code> and reverse it. You are allowed to make any number of steps.</p>

<p>Return <em>True</em> if you can make <code>arr</code> equal to <code>target</code>, or <em>False</em> otherwise.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2,3,4], arr = [2,4,1,3]
<strong>Output:</strong> true
<strong>Explanation:</strong> You can follow the next steps to convert arr to target:
1- Reverse sub-array [2,4,1], arr becomes [1,4,2,3]
2- Reverse sub-array [4,2], arr becomes [1,2,4,3]
3- Reverse sub-array [4,3], arr becomes [1,2,3,4]
There are multiple ways to convert arr to target, this is not the only way to do so.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [7], arr = [7]
<strong>Output:</strong> true
<strong>Explanation:</strong> arr is equal to target without any reverses.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [1,12], arr = [12,1]
<strong>Output:</strong> true
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> target = [3,7,9], arr = [3,7,11]
<strong>Output:</strong> false
<strong>Explanation:</strong> arr doesn&#39;t have value 9 and it can never be converted to target.
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> target = [1,1,1,1,1], arr = [1,1,1,1,1]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>target.length == arr.length</code></li>
	<li><code>1 &lt;= target.length &lt;= 1000</code></li>
	<li><code>1 &lt;= target[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        return sorted(target) == sorted(arr)
```

### **Java**

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
