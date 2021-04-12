# [179. 最大数](https://leetcode-cn.com/problems/largest-number)

[English Version](/solution/0100-0199/0179.Largest%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>[10,2]</code>
<strong>输出:</strong> <code>210</code></pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> <code>[3,30,34,5,9]</code>
<strong>输出:</strong> <code>9534330</code></pre>

<p><strong>说明: </strong>输出结果可能非常大，所以你需要返回一个字符串而不是整数。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先转成字符串列表，再对字符串列表进行字典序降序排列。最后将列表所有字符串拼接即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from functools import cmp_to_key

class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        num_list = list(map(str, nums))
        num_list.sort(key=cmp_to_key(lambda x, y: int(y + x) - int(x + y)))
        return '0' if num_list[0] == '0' else ''.join(num_list)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
