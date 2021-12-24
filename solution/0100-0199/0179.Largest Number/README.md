# [179. 最大数](https://leetcode-cn.com/problems/largest-number)

[English Version](/solution/0100-0199/0179.Largest%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一组非负整数 <code>nums</code>，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。</p>

<p><strong>注意：</strong>输出结果可能非常大，所以你需要返回一个字符串而不是整数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入<code>：</code></strong><code>nums = [10,2]</code>
<strong>输出：</strong><code>"210"</code></pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入<code>：</code></strong><code>nums = [3,30,34,5,9]</code>
<strong>输出：</strong><code>"9534330"</code>
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入<code>：</code></strong>nums = [1]
<strong>输出：</strong>"1"
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入<code>：</code></strong>nums = [10]
<strong>输出：</strong>"10"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 100</code></li>
	<li><code>0 <= nums[i] <= 10<sup>9</sup></code></li>
</ul>

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
