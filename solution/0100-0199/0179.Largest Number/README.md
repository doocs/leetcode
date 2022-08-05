# [179. 最大数](https://leetcode.cn/problems/largest-number)

[English Version](/solution/0100-0199/0179.Largest%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一组非负整数 <code>nums</code>，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。</p>

<p><strong>注意：</strong>输出结果可能非常大，所以你需要返回一个字符串而不是整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入<code>：</code></strong><code>nums = [10,2]</code>
<strong>输出：</strong><code>"210"</code></pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入<code>：</code></strong><code>nums = [3,30,34,5,9]</code>
<strong>输出：</strong><code>"9534330"</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：自定义排序**

先转成字符串列表，再对字符串列表进行字典序降序排列。最后将列表所有字符串拼接即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        nums = [str(v) for v in nums]
        nums.sort(key=cmp_to_key(lambda a, b: 1 if a + b < b + a else -1))
        return "0" if nums[0] == "0" else "".join(nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String largestNumber(int[] nums) {
        List<String> vs = new ArrayList<>();
        for (int v : nums) {
            vs.add(v + "");
        }
        vs.sort((a, b) -> (b + a).compareTo(a + b));
        if ("0".equals(vs.get(0))) {
            return "0";
        }
        return String.join("", vs);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string largestNumber(vector<int>& nums) {
        vector<string> vs;
        for (int v : nums) vs.push_back(to_string(v));
        sort(vs.begin(), vs.end(), [](string& a, string& b) {
            return a + b > b + a;
        });
        if (vs[0] == "0") return "0";
        string ans;
        for (string v : vs) ans += v;
        return ans;
    }
};
```

### **Go**

```go
func largestNumber(nums []int) string {
	vs := make([]string, len(nums))
	for i, v := range nums {
		vs[i] = strconv.Itoa(v)
	}
	sort.Slice(vs, func(i, j int) bool {
		return vs[i]+vs[j] > vs[j]+vs[i]
	})
	if vs[0] == "0" {
		return "0"
	}
	return strings.Join(vs, "")
}
```

### **C#**

```cs
using System;
using System.Globalization;
using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Comparer: IComparer<string>
{
    public int Compare(string left, string right)
    {
        return Compare(left, right, 0, 0);
    }

    private int Compare(string left, string right, int lBegin, int rBegin)
    {
        var len = Math.Min(left.Length - lBegin, right.Length - rBegin);
        for (var i = 0; i < len; ++i)
        {
            if (left[lBegin + i] != right[rBegin + i])
            {
                return left[lBegin + i] < right[rBegin + i] ? -1 : 1;
            }
        }

        if (left.Length - lBegin == right.Length - rBegin)
        {
            return 0;
        }
        if (left.Length - lBegin > right.Length - rBegin)
        {
            return Compare(left, right, lBegin + len, rBegin);
        }
        else
        {
            return Compare(left, right, lBegin, rBegin + len);
        }
    }
}

public class Solution {
    public string LargestNumber(int[] nums) {
        var sb = new StringBuilder();
        var strs = nums.Select(n => n.ToString(CultureInfo.InvariantCulture)).OrderByDescending(s => s, new Comparer());

        var nonZeroOccurred = false;
        foreach (var str in strs)
        {
            if (!nonZeroOccurred && str == "0") continue;
            sb.Append(str);
            nonZeroOccurred = true;
        }
        return sb.Length == 0 ? "0" : sb.ToString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
