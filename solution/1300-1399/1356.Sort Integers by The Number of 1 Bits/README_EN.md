# [1356. Sort Integers by The Number of 1 Bits](https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits)

[中文文档](/solution/1300-1399/1356.Sort%20Integers%20by%20The%20Number%20of%201%20Bits/README.md)

## Description

<p>Given an integer array <code>arr</code>. You have to sort the integers in the array&nbsp;in ascending order by the number of <strong>1&#39;s</strong>&nbsp;in their binary representation and in case of two or more integers have the same number of <strong>1&#39;s</strong> you have to sort them in ascending order.</p>

<p>Return <em>the sorted array</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [0,1,2,3,4,5,6,7,8]
<strong>Output:</strong> [0,1,2,4,8,3,5,6,7]
<strong>Explantion:</strong> [0] is the only integer with 0 bits.
[1,2,4,8] all have 1 bit.
[3,5,6] have 2 bits.
[7] has 3 bits.
The sorted array by bits is [0,1,2,4,8,3,5,6,7]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1024,512,256,128,64,32,16,8,4,2,1]
<strong>Output:</strong> [1,2,4,8,16,32,64,128,256,512,1024]
<strong>Explantion:</strong> All integers have 1 bit in the binary representation, you should just sort them in ascending order.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [10000,10000]
<strong>Output:</strong> [10000,10000]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,3,5,7,11,13,17,19]
<strong>Output:</strong> [2,3,5,17,7,11,13,19]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,100,1000,10000]
<strong>Output:</strong> [10,100,10000,1000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10^4</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sortByBits(self, arr: List[int]) -> List[int]:
        arr.sort(key=lambda x: (bin(x).count("1"), x))
        return arr
```

### **Java**

```java
class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] += Integer.bitCount(arr[i]) * 100000;
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            arr[i] %= 100000;
        }
        return arr;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
