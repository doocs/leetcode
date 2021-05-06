# [438. 找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string)

[English Version](/solution/0400-0499/0438.Find%20All%20Anagrams%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串&nbsp;<strong>s&nbsp;</strong>和一个非空字符串&nbsp;<strong>p</strong>，找到&nbsp;<strong>s&nbsp;</strong>中所有是&nbsp;<strong>p&nbsp;</strong>的字母异位词的子串，返回这些子串的起始索引。</p>

<p>字符串只包含小写英文字母，并且字符串&nbsp;<strong>s&nbsp;</strong>和 <strong>p&nbsp;</strong>的长度都不超过 20100。</p>

<p><strong>说明：</strong></p>

<ul>
	<li>字母异位词指字母相同，但排列不同的字符串。</li>
	<li>不考虑答案输出的顺序。</li>
</ul>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong>
s: &quot;cbaebabacd&quot; p: &quot;abc&quot;

<strong>输出:</strong>
[0, 6]

<strong>解释:</strong>
起始索引等于 0 的子串是 &quot;cba&quot;, 它是 &quot;abc&quot; 的字母异位词。
起始索引等于 6 的子串是 &quot;bac&quot;, 它是 &quot;abc&quot; 的字母异位词。
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<strong>输入:</strong>
s: &quot;abab&quot; p: &quot;ab&quot;

<strong>输出:</strong>
[0, 1, 2]

<strong>解释:</strong>
起始索引等于 0 的子串是 &quot;ab&quot;, 它是 &quot;ab&quot; 的字母异位词。
起始索引等于 1 的子串是 &quot;ba&quot;, 它是 &quot;ab&quot; 的字母异位词。
起始索引等于 2 的子串是 &quot;ab&quot;, 它是 &quot;ab&quot; 的字母异位词。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“双指针 + 滑动窗口”求解。定义滑动窗口的左右两个指针 left、right，right 一步步往右走遍历 s 字符串，当 right 指针遍历到的字符加入 t 后超过 counter 的字符数量时，将滑动窗口左侧字符不断弹出，也就是 left 指针不断右移，直到符合要求为止。

若滑动窗口长度等于字符串 p 的长度时，这时的 s 子字符串就是 p 的异位词。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        counter = collections.Counter(p)
        res = []
        left = right = 0
        t = collections.Counter()
        while right < len(s):
            t[s[right]] += 1
            while t[s[right]] > counter[s[right]]:
                t[s[left]] -= 1
                left += 1
            if right - left == len(p) - 1:
                res.append(left)
            right += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

“暴力”求解。利用哈希表 counter 统计字符串 p 中每个字符出现的次数。然后遍历字符串 s，判断子串 `s[i, i + p)` 中每个字符出现的次数是否与 counter 相同。若是，则将当前下标 i 添加到结果列表中。时间复杂度 `O(s.length * p.length)`。

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] counter = new int[26];
        for (int i = 0; i < p.length(); ++i) {
            ++counter[p.charAt(i) - 'a'];
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= s.length() - p.length(); ++i) {
            int[] t = Arrays.copyOf(counter, counter.length);
            boolean find = true;
            for (int j = i; j < i + p.length(); ++j) {
                if (--t[s.charAt(j) - 'a'] < 0) {
                    find = false;
                    break;
                }
            }
            if (find) {
                res.add(i);
            }
        }
        return res;
    }
}
```

“双指针 + 滑动窗口”求解。

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] counter = new int[26];
        for (int i = 0; i < p.length(); ++i) {
            ++counter[p.charAt(i) - 'a'];
        }
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;
        int[] t = new int[26];
        while (right < s.length()) {
            int i = s.charAt(right) - 'a';
            ++t[i];
            while (t[i] > counter[i]) {
                --t[s.charAt(left) - 'a'];
                ++left;
            }
            if (right - left == p.length() - 1) {
                res.add(left);
            }
            ++right;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
