# [面试题 45. 把数组排成最小的数](https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>[10,2]</code>
<strong>输出:</strong> &quot;<code>102&quot;</code></pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> <code>[3,30,34,5,9]</code>
<strong>输出:</strong> &quot;<code>3033459&quot;</code></pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt; nums.length &lt;= 100</code></li>
</ul>

<p><strong>说明: </strong></p>

<ul>
	<li>输出结果可能非常大，所以你需要返回一个字符串而不是整数</li>
	<li>拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

自定义排序比较器。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
import functools


class Solution:
    def minNumber(self, nums: List[int]) -> str:
        if not nums:
            return ''

        def compare(s1, s2):
            if s1 + s2 < s2 + s1:
                return -1
            if s1 + s2 > s2 + s1:
                return 1
            return 0

        return ''.join(
            sorted([str(x) for x in nums], key=functools.cmp_to_key(compare))
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        return Arrays.stream(nums).mapToObj(String::valueOf).sorted((s1, s2) -> (s1 + s2).compareTo(s2 + s1)).reduce((s1, s2) -> s1 + s2).get();
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {string}
 */
var minNumber = function (nums) {
    nums.sort((a, b) => {
        let s1 = a + '' + b;
        let s2 = b + '' + a;
        if (s1 < s2) {
            return -1;
        } else return 1;
    });
    return nums.join('');
};
```

### **C++**

```cpp
class Solution {
public:
    string minNumber(vector<int>& nums) {
        int n = nums.size();
        vector<string> strs(n);
        for (int i = 0; i < n; ++i) {
            strs[i] = to_string(nums[i]);
        }
        sort(strs.begin(), strs.end(), [](const string& s1, const string& s2) {
            return s1 + s2 < s2 + s1;
        });
        string ans;
        for (int i = 0; i < n; ++i) {
            ans += strs[i];
        }
        return ans;
    }
};
```

### **TypeScript**

```ts
function minNumber(nums: number[]): string {
    return nums
        .sort((a, b) => Number(`${a}${b}`) - Number(`${b}${a}`))
        .join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_number(mut nums: Vec<i32>) -> String {
        nums.sort_by(|a, b| format!("{}{}", a, b).cmp(&format!("{}{}", b, a)));
        nums.iter().map(|num| num.to_string()).collect()
    }
}
```

### **C#**

```cs
public class Solution {
    public string MinNumber(int[] nums) {
        List<string> ans = new List<string>();
        foreach (int temp in nums) {
            ans.Add(temp.ToString());
        }
        ans.Sort((a, b) => (a + b).CompareTo(b + a));
        return string.Join("", ans);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
