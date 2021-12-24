# [17. 电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number)

[English Version](/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个仅包含数字 <code>2-9</code> 的字符串，返回所有它能表示的字母组合。答案可以按 <strong>任意顺序</strong> 返回。</p>

<p>给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/images/17_telephone_keypad.png" style="width: 200px;" /></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = "23"
<strong>输出：</strong>["ad","ae","af","bd","be","bf","cd","ce","cf"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = ""
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>digits = "2"
<strong>输出：</strong>["a","b","c"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= digits.length <= 4</code></li>
	<li><code>digits[i]</code> 是范围 <code>['2', '9']</code> 的一个数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        n = len(digits)
        if n == 0:
            return []
        chars = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz']
        strs = [chars[int(d) - 2] for d in digits]
        res = []
        for s in strs:
            if not res:
                res = list(s)
            else:
                cache = []
                for item in res:
                    for letter in s:
                        cache.append(item + letter)
                res = cache
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        int n;
        if ((n = digits.length()) == 0) return Collections.emptyList();
        List<String> chars = Arrays.asList("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");

        List<String> strs = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            strs.add(chars.get(c - '0' - 2));
        }
        List<String> res = new ArrayList<>();
        for (String str : strs) {
            if (res.size() == 0) {
                for (char c : str.toCharArray()) {
                    res.add(String.valueOf(c));
                }
            } else {
                List<String> cache = new ArrayList<>();
                for (String item : res) {
                    for (char c : str.toCharArray()) {
                        cache.add(item + String.valueOf(c));
                    }
                }
                res = cache;
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
