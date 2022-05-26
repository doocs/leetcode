# [970. 强整数](https://leetcode.cn/problems/powerful-integers)

[English Version](/solution/0900-0999/0970.Powerful%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定三个整数 <code>x</code>&nbsp;、&nbsp;<code>y</code>&nbsp;和<em>&nbsp;</em><code>bound</code><em>&nbsp;</em>，返回 <em>值小于或等于&nbsp;<code>bound</code>&nbsp;的所有&nbsp;<strong>强整数</strong>&nbsp;组成的列表</em>&nbsp;。</p>

<p>如果某一整数可以表示为&nbsp;<code>x<sup>i</sup>&nbsp;+ y<sup>j</sup></code>&nbsp;，其中整数&nbsp;<code>i &gt;= 0</code> 且&nbsp;<code>j &gt;= 0</code>，那么我们认为该整数是一个&nbsp;<strong>强整数</strong>&nbsp;。</p>

<p>你可以按 <strong>任何顺序</strong> 返回答案。在你的回答中，每个值 <strong>最多</strong> 出现一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2, y = 3, bound = 10
<strong>输出：</strong>[2,3,4,5,7,9,10]
<strong>解释： </strong>
2 = 2<sup>0</sup> + 3<sup>0</sup>
3 = 2<sup>1</sup> + 3<sup>0</sup>
4 = 2<sup>0</sup> + 3<sup>1</sup>
5 = 2<sup>1</sup> + 3<sup>1</sup>
7 = 2<sup>2</sup> + 3<sup>1</sup>
9 = 2<sup>3</sup> + 3<sup>0</sup>
10 = 2<sup>0</sup> + 3<sup>2</sup></pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>x = 3, y = 5, bound = 15
<strong>输出：</strong>[2,4,6,8,10,14]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= x, y &lt;= 100</code></li>
	<li><code>0 &lt;= bound &lt;= 10<sup>6</sup></code></li>
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
var powerfulIntegers = function (x, y, bound) {
    let res = new Set();
    for (let i = 1; i < bound; i *= x) {
        for (let j = 1; j < bound; j *= y) {
            if (i + j <= bound) {
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
