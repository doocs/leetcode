# [3084. 统计以给定字符开头和结尾的子字符串总数](https://leetcode.cn/problems/count-substrings-starting-and-ending-with-given-character)

[English Version](/solution/3000-3099/3084.Count%20Substrings%20Starting%20and%20Ending%20with%20Given%20Character/README_EN.md)

<!-- tags:数学,字符串,计数 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个字符 <code>c </code>。返回在字符串 <code>s</code> 中并且以 <code>c</code> 字符开头和结尾的<span data-keyword="substring-nonempty">非空子字符串</span>的总数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "abada", c = "a"</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">6</span></p>

<p><strong>解释：</strong>以 <code>"a"</code> 开头和结尾的子字符串有： <code>"<strong><u>a</u></strong>bada"</code>、<code>"<u><strong>aba</strong></u>da"</code>、<code>"<u><strong>abada</strong></u>"</code>、<code>"ab<u><strong>a</strong></u>da"</code>、<code>"ab<u><strong>ada</strong></u>"</code>、<code>"abad<u><strong>a</strong></u>"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "zzz", c = "z"</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">6</span></p>

<p><strong>解释：</strong>字符串 <code>s</code> 中总共有 <code>6</code> 个子字符串，并且它们都以 <code>"z"</code> 开头和结尾。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>c</code> 均由小写英文字母组成。</li>
</ul>

## 解法

### 方法一：数学

我们可以先统计字符串 $s$ 中字符 $c$ 的个数，记为 $cnt$。

每个 $c$ 字符可以单独作为一个子字符串，所以有 $cnt$ 个子字符串满足条件。每个 $c$ 字符可以和其他 $c$ 字符组成一个满足条件的子字符串，所以有 $\frac{cnt \times (cnt - 1)}{2}$ 个子字符串满足条件。

所以答案为 $cnt + \frac{cnt \times (cnt - 1)}{2}$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countSubstrings(self, s: str, c: str) -> int:
        cnt = s.count(c)
        return cnt + cnt * (cnt - 1) // 2
```

```java
class Solution {
    public long countSubstrings(String s, char c) {
        long cnt = s.chars().filter(ch -> ch == c).count();
        return cnt + cnt * (cnt - 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    long long countSubstrings(string s, char c) {
        long long cnt = ranges::count(s, c);
        return cnt + cnt * (cnt - 1) / 2;
    }
};
```

```go
func countSubstrings(s string, c byte) int64 {
	cnt := int64(strings.Count(s, string(c)))
	return cnt + cnt*(cnt-1)/2
}
```

```ts
function countSubstrings(s: string, c: string): number {
    const cnt = s.split('').filter(ch => ch === c).length;
    return cnt + Math.floor((cnt * (cnt - 1)) / 2);
}
```

<!-- tabs:end -->

<!-- end -->
