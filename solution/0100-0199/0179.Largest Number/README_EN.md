# [179. Largest Number](https://leetcode.com/problems/largest-number)

[中文文档](/solution/0100-0199/0179.Largest%20Number/README.md)

## Description

<p>Given a list of non-negative integers <code>nums</code>, arrange them such that they form the largest number.</p>

<p><strong>Note:</strong> The result may be very large, so you need to return a string instead of an integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,2]
<strong>Output:</strong> &quot;210&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,30,34,5,9]
<strong>Output:</strong> &quot;9534330&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> &quot;1&quot;
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> nums = [10]
<strong>Output:</strong> &quot;10&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from functools import cmp_to_key

class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        num_list = list(map(str, nums))
        num_list.sort(key=cmp_to_key(lambda x, y: int(y + x) - int(x + y)))
        return '0' if num_list[0] == '0' else ''.join(num_list)
```

### **Java**

```java
class Solution {
    public String largestNumber(int[] nums) {
        List<String> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(String.valueOf(num));
        }
        numList.sort((a, b) -> (b + a).compareTo(a + b));
        if ("0".equals(numList.get(0))) return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : numList) {
            sb.append(s);
        }
        return sb.toString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
