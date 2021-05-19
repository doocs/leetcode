# [970. 强整数](https://leetcode-cn.com/problems/powerful-integers)

[English Version](/solution/0900-0999/0970.Powerful%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个正整数 <code>x</code> 和 <code>y</code>，如果某一整数等于 <code>x^i + y^j</code>，其中整数&nbsp;<code>i &gt;= 0</code> 且&nbsp;<code>j &gt;= 0</code>，那么我们认为该整数是一个<em>强整数</em>。</p>

<p>返回值小于或等于&nbsp;<code>bound</code>&nbsp;的所有<em>强整数</em>组成的列表。</p>

<p>你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>x = 2, y = 3, bound = 10
<strong>输出：</strong>[2,3,4,5,7,9,10]
<strong>解释： </strong>
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>x = 3, y = 5, bound = 15
<strong>输出：</strong>[2,4,6,8,10,14]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x &lt;= 100</code></li>
	<li><code>1 &lt;= y&nbsp;&lt;= 100</code></li>
	<li><code>0 &lt;= bound&nbsp;&lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def powerfulIntegers(self, x: int, y: int, bound: int) -> List[int]:
        s = set()
        i = 1
        while i < bound:
            j = 1
            while j < bound:
                if i + j <= bound:
                    s.add(i + j)
                if y == 1:
                    break
                j *= y
            if x == 1:
                break
            i *= x
        return list(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> s = new HashSet<>();
        for (int i = 1; i < bound; i *= x) {
            for (int j = 1; j < bound; j *= y) {
                if (i + j <= bound) {
                    s.add(i + j);
                }
                if (y == 1) {
                    break;
                }
            }
            if (x == 1) {
                break;
            }
        }
        return new ArrayList<>(s);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @param {number} y
 * @param {number} bound
 * @return {number[]}
 */
var powerfulIntegers = function(x, y, bound) {
    let res = new Set();
    for (let i = 1; i < bound; i *= x) {
        for (let j = 1; j < bound; j *= y) {
            if ((i + j) <= bound) {
                res.add(i + j);
            }
            if (y == 1) break;
        }
        if (x == 1) break;
    }
    return [...res];
};
```

### **...**

```

```

<!-- tabs:end -->
