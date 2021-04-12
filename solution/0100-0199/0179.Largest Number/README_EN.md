# [179. Largest Number](https://leetcode.com/problems/largest-number)

[中文文档](/solution/0100-0199/0179.Largest%20Number/README.md)

## Description

<p>Given a list of non negative integers, arrange them such that they form the largest number.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> <code>[10,2]</code>

<strong>Output:</strong> &quot;<code>210&quot;</code></pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> <code>[3,30,34,5,9]</code>

<strong>Output:</strong> &quot;<code>9534330&quot;</code>

</pre>

<p><strong>Note:</strong> The result may be very large, so you need to return a string instead of an integer.</p>

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
