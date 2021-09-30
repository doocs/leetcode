# [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements)

[中文文档](/solution/0600-0699/0658.Find%20K%20Closest%20Elements/README.md)

## Description

<p>Given a <strong>sorted</strong> integer array <code>arr</code>, two integers <code>k</code> and <code>x</code>, return the <code>k</code> closest integers to <code>x</code> in the array. The result should also be sorted in ascending order.</p>

<p>An integer <code>a</code> is closer to <code>x</code> than an integer <code>b</code> if:</p>

<ul>
	<li><code>|a - x| &lt; |b - x|</code>, or</li>
	<li><code>|a - x| == |b - x|</code> and <code>a &lt; b</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = 3
<strong>Output:</strong> [1,2,3,4]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = -1
<strong>Output:</strong> [1,2,3,4]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length</code></li>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>arr</code> is sorted in <strong>ascending</strong> order.</li>
	<li><code>-10<sup>4</sup> &lt;= arr[i], x &lt;= 10<sup>4</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (arr.length < k) {
            for (int item : arr) {
                res.add(item);
            }
            return res;
        }
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (arr[mid] > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        int left1 = 0;
        int right1 = arr.length - 1;
        if (left >= k) {
            left1 = left - k;
        }
        if (arr.length - 1 - left >= k) {
            right1 = left + k;
        }
        while (right1 - left1 >= k) {
            if (Math.abs(arr[left1] - x) > Math.abs(arr[right1] -x)) {
                left1++;
            } else {
                right1--;
            }
        }
        while (left1 <= right1) {
            res.add(arr[left1]);
            left1++;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
