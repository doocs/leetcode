# [1874. 两个数组的最小乘积和](https://leetcode.cn/problems/minimize-product-sum-of-two-arrays)

[English Version](/solution/1800-1899/1874.Minimize%20Product%20Sum%20of%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个<strong>长度相等</strong>的数组<code>a</code>和<code>b</code>，它们的<strong>乘积和</strong>为数组中所有的<code>a[i] * b[i]</code>之和，其中<code>0 &lt;= i &lt; a.length</code>。</p>

<ul>
	<li>比如<code>a = [1,2,3,4]</code>，<code>b = [5,2,3,1]</code>时，它们的<strong>乘积和</strong>为<code>1*5 + 2*2 + 3*3 + 4*1 = 22</code></li>
</ul>

<p>现有两个长度都为<code>n</code>的数组<code>nums1</code>和<code>nums2</code>，你可以以<strong>任意顺序排序</strong><code>nums1</code>，请返回它们的<strong>最小乘积和</strong>。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums1 = [5,3,4,2], nums2 = [4,2,2,5]
<strong>输出:</strong> 40
<strong>解释: </strong>将 num1 重新排列为 [3,5,4,2] 后，可由<b> </b>[3,5,4,2] 和 [4,2,2,5] 得到最小乘积和 3*4 + 5*2 + 4*2 + 2*5 = 40。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums1 = [2,1,4,5,7], nums2 = [3,2,4,8,6]
<strong>输出:</strong> 65
<strong>解释:</strong> 将 num1 重新排列为 [5,7,4,1,2] 后，可由<b> </b>[5,7,4,1,2] 和 [3,2,4,8,6] 得到最小乘积和 5*3 + 7*2 + 4*4 + 1*8 + 2*6 = 65。
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

对两数组排序，然后首尾相乘求和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minProductSum(self, nums1: List[int], nums2: List[int]) -> int:
        nums1.sort()
        nums2.sort()
        n, res = len(nums1), 0
        for i in range(n):
            res += nums1[i] * nums2[n - i - 1]
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minProductSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, res = 0;
        for (int i = 0; i < n; ++i) {
            res += nums1[i] * nums2[n - i - 1];
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minProductSum(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());
        int n = nums1.size(), res = 0;
        for (int i = 0; i < n; ++i) {
            res += nums1[i] * nums2[n - i - 1];
        }
        return res;
    }
};
```

### **Go**

```go
func minProductSum(nums1 []int, nums2 []int) int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	res, n := 0, len(nums1)
	for i, num := range nums1 {
		res += num * nums2[n-i-1]
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
