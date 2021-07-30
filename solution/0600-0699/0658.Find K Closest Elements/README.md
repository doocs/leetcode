# [658. 找到 K 个最接近的元素](https://leetcode-cn.com/problems/find-k-closest-elements)

[English Version](/solution/0600-0699/0658.Find%20K%20Closest%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个排序好的数组 <code>arr</code> ，两个整数 <code>k</code> 和 <code>x</code> ，从数组中找到最靠近 <code>x</code>（两数之差最小）的 <code>k</code> 个数。返回的结果必须要是按升序排好的。</p>

<p>整数 <code>a</code> 比整数 <code>b</code> 更接近 <code>x</code> 需要满足：</p>

<ul>
	<li><code>|a - x| < |b - x|</code> 或者</li>
	<li><code>|a - x| == |b - x|</code> 且 <code>a < b</code></li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,4,5], k = 4, x = 3
<strong>输出：</strong>[1,2,3,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,4,5], k = 4, x = -1
<strong>输出：</strong>[1,2,3,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= arr.length</code></li>
	<li><code>1 <= arr.length <= 10<sup>4</sup></code></li>
	<li>数组里的每个元素与 <code>x</code> 的绝对值不超过 <code>10<sup>4</sup></code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
