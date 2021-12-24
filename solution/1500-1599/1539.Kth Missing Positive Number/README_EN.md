# [1539. Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number)

[中文文档](/solution/1500-1599/1539.Kth%20Missing%20Positive%20Number/README.md)

## Description

<p>Given an array <code>arr</code>&nbsp;of positive integers&nbsp;sorted in a <strong>strictly increasing order</strong>, and an integer <code><font face="monospace">k</font></code>.</p>

<p><em>Find the </em><font face="monospace"><code>k<sup>th</sup></code></font><em>&nbsp;positive integer that is missing from this array.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,3,4,7,11], k = 5
<strong>Output:</strong> 9
<strong>Explanation: </strong>The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5<sup>th</sup>&nbsp;missing positive integer is 9.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3,4], k = 2
<strong>Output:</strong> 6
<strong>Explanation: </strong>The missing positive integers are [5,6,7,...]. The 2<sup>nd</sup> missing positive integer is 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>arr[i] &lt; arr[j]</code> for <code>1 &lt;= i &lt; j &lt;= arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:
        if arr[0] > k:
            return k
        left, right = 0, len(arr)
        while left < right:
            mid = (left + right) // 2
            if arr[mid] - mid - 1 < k:
                left = mid + 1
            else:
                right = mid
        return k - (arr[left - 1] - (left - 1) - 1) + arr[left - 1]
```

### **Java**

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        if (arr[0] > k) {
            return k;
        }
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            int cur = mid == arr.length ? Integer.MAX_VALUE : arr[mid];
            if (cur - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return k - (arr[left - 1] - (left - 1) - 1) + arr[left - 1];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
