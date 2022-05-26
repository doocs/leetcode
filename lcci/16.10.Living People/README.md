# [面试题 16.10. 生存人数](https://leetcode-cn.com/problems/living-people-lcci)

[English Version](/lcci/16.10.Living%20People/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定N个人的出生年份和死亡年份，第<code>i</code>个人的出生年份为<code>birth[i]</code>，死亡年份为<code>death[i]</code>，实现一个方法以计算生存人数最多的年份。</p>
<p>你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。</p>
<p>如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
birth = {1900, 1901, 1950}
death = {1948, 1951, 2000}
<strong>输出：</strong> 1901
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 < birth.length == death.length <= 10000</code></li>
<li><code>birth[i] <= death[i]</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxAliveYear(self, birth: List[int], death: List[int]) -> int:
        years = [0] * 101
        for i in range(len(birth)):
            start = birth[i] - 1900
            end = death[i] - 1900
            for j in range(start, end + 1):
                years[j] += 1
        max_v = years[0]
        res = 0
        for i in range(1, 101):
            if years[i] > max_v:
                max_v = years[i]
                res = i
        return 1900 + res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxAliveYear(int[] birth, int[] death) {
        int[] years = new int[101];
        int n = birth.length;
        for (int i = 0; i < n; ++i) {
            int start = birth[i] - 1900;
            int end = death[i] - 1900;
            for (int j = start; j <= end; ++j) {
                ++years[j];
            }
        }
        int max = years[0];
        int res = 0;
        for (int i = 1; i < 101; ++i) {
            if (years[i] > max) {
                max = years[i];
                res = i;
            }
        }
        return 1900 + res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
