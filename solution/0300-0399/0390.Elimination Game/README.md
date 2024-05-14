# [390. 消除游戏](https://leetcode.cn/problems/elimination-game)

[English Version](/solution/0300-0399/0390.Elimination%20Game/README_EN.md)

<!-- tags:递归,数学 -->

<!-- difficulty:中等 -->

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

### 方法一

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
