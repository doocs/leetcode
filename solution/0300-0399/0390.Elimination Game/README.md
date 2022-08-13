# [390. 消除游戏](https://leetcode.cn/problems/elimination-game)

[English Version](/solution/0300-0399/0390.Elimination%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>列表 <code>arr</code> 由在范围 <code>[1, n]</code> 中的所有整数组成，并按严格递增排序。请你对 <code>arr</code> 应用下述算法：</p>

<div class="original__bRMd">
<div>
<ul>
	<li>从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。</li>
	<li>重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。</li>
	<li>不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。</li>
</ul>

<p>给你整数 <code>n</code> ，返回 <code>arr</code> 最后剩下的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 9
<strong>输出：</strong>6
<strong>解释：</strong>
arr = [<strong><em>1</em></strong>, 2, <em><strong>3</strong></em>, 4, <em><strong>5</strong></em>, 6, <em><strong>7</strong></em>, 8, <em><strong>9</strong></em>]
arr = [2, <em><strong>4</strong></em>, 6, <em><strong>8</strong></em>]
arr = [<em><strong>2</strong></em>, 6]
arr = [6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用 `i` 记录该从左边还是右边进行删除。由于经过每轮删除过后都是一个等差数列，因此我们用 `a1`, `an` 记录首尾元素，`cnt` 记录数列元素个数，`step` 记录元素间的间隔，`step` 初始为 1。

-   若从左边删除：
    -   `a1` 变为 `a1 + step`
    -   若 `cnt` 为奇数个，`an` 变为 `an - step`，否则 `an` 不变
-   若从右边删除：
    -   `an` 变为 `an - step`
    -   若 `cnt` 为奇数个，`a1` 变为 `a1 + step`，否则 `a1` 不变

每次经过一轮删除，数列元素个数 `cnt` 变为 `cnt >> 1`，元素间隔 `step` 变为 `step << 1`，`i` 自增 1。

当元素个数剩下 1 个时，退出循环，返回 `a1` 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lastRemaining(self, n: int) -> int:
        a1, an = 1, n
        i, step, cnt = 0, 1, n
        while cnt > 1:
            if i % 2:
                an -= step
                if cnt % 2:
                    a1 += step
            else:
                a1 += step
                if cnt % 2:
                    an -= step
            cnt >>= 1
            step <<= 1
            i += 1
        return a1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lastRemaining(int n) {
        int a1 = 1, an = n, step = 1;
        for (int i = 0, cnt = n; cnt > 1; cnt >>= 1, step <<= 1, ++i) {
            if (i % 2 == 1) {
                an -= step;
                if (cnt % 2 == 1) {
                    a1 += step;
                }
            } else {
                a1 += step;
                if (cnt % 2 == 1) {
                    an -= step;
                }
            }
        }
        return a1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int lastRemaining(int n) {
        int a1 = 1, an = n, step = 1;
        for (int i = 0, cnt = n; cnt > 1; cnt >>= 1, step <<= 1, ++i) {
            if (i % 2) {
                an -= step;
                if (cnt % 2) a1 += step;
            } else {
                a1 += step;
                if (cnt % 2) an -= step;
            }
        }
        return a1;
    }
};
```

### **Go**

```go
func lastRemaining(n int) int {
	a1, an, step := 1, n, 1
	for i, cnt := 0, n; cnt > 1; cnt, step, i = cnt>>1, step<<1, i+1 {
		if i%2 == 1 {
			an -= step
			if cnt%2 == 1 {
				a1 += step
			}
		} else {
			a1 += step
			if cnt%2 == 1 {
				an -= step
			}
		}
	}
	return a1
}
```

### **...**

```

```

<!-- tabs:end -->
