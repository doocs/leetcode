# [1539. 第 k 个缺失的正整数](https://leetcode-cn.com/problems/kth-missing-positive-number)

[English Version](/solution/1500-1599/1539.Kth%20Missing%20Positive%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>严格升序排列</strong>&nbsp;的正整数数组 <code>arr</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>请你找到这个数组里第&nbsp;<code>k</code>&nbsp;个缺失的正整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,3,4,7,11], k = 5
<strong>输出：</strong>9
<strong>解释：</strong>缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,4], k = 2
<strong>输出：</strong>6
<strong>解释：</strong>缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li>对于所有&nbsp;<code>1 &lt;= i &lt; j &lt;= arr.length</code>&nbsp;的 <code>i</code>&nbsp;和 <code>j</code> 满足&nbsp;<code>arr[i] &lt; arr[j]</code>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
