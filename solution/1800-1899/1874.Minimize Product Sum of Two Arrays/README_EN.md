# [1874. Minimize Product Sum of Two Arrays](https://leetcode.com/problems/minimize-product-sum-of-two-arrays)

[中文文档](/solution/1800-1899/1874.Minimize%20Product%20Sum%20of%20Two%20Arrays/README.md)

## Description

<p>The <b>product sum </b>of two equal-length arrays <code>a</code> and <code>b</code> is equal to the sum of <code>a[i] * b[i]</code> for all <code>0 &lt;= i &lt; a.length</code> (<strong>0-indexed</strong>).</p>

<ul>

    <li>For example, if <code>a = [1,2,3,4]</code> and <code>b = [5,2,3,1]</code>, the <strong>product sum</strong> would be <code>1*5 + 2*2 + 3*3 + 4*1 = 22</code>.</li>

</ul>

<p>Given two arrays <code>nums1</code> and <code>nums2</code> of length <code>n</code>, return <em>the <strong>minimum product sum</strong> if you are allowed to <strong>rearrange</strong> the <strong>order</strong> of the elements in </em><code>nums1</code>.&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums1 = [5,3,4,2], nums2 = [4,2,2,5]

<strong>Output:</strong> 40

<strong>Explanation:</strong>&nbsp;We can rearrange nums1 to become [3,5,4,2]. The product sum of [3,5,4,2] and [4,2,2,5] is 3*4 + 5*2 + 4*2 + 2*5 = 40.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums1 = [2,1,4,5,7], nums2 = [3,2,4,8,6]

<strong>Output:</strong> 65

<strong>Explanation: </strong>We can rearrange nums1 to become [5,7,4,1,2]. The product sum of [5,7,4,1,2] and [3,2,4,8,6] is 5*3 + 7*2 + 4*4 + 1*8 + 2*6 = 65.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>n == nums1.length == nums2.length</code></li>

    <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>

    <li><code>1 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
