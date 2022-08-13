# [780. 到达终点](https://leetcode.cn/problems/reaching-points)

[English Version](/solution/0700-0799/0780.Reaching%20Points/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定四个整数&nbsp;<code>sx</code>&nbsp;,&nbsp;<code>sy</code>&nbsp;，<code>tx</code>&nbsp;和&nbsp;<code>ty</code>，如果通过一系列的<strong>转换</strong>可以从起点&nbsp;<code>(sx, sy)</code>&nbsp;到达终点&nbsp;<code>(tx, ty)</code>，则返回 <code>true</code>，否则返回&nbsp;<code>false</code>。</p>

<p>从点&nbsp;<code>(x, y)</code>&nbsp;可以<strong>转换</strong>到&nbsp;<code>(x, x+y)</code>&nbsp; 或者&nbsp;<code>(x+y, y)</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> sx = 1, sy = 1, tx = 3, ty = 5
<strong>输出:</strong> true
<strong>解释:
</strong>可以通过以下一系列<strong>转换</strong>从起点转换到终点：
(1, 1) -&gt; (1, 2)
(1, 2) -&gt; (3, 2)
(3, 2) -&gt; (3, 5)
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> sx = 1, sy = 1, tx = 2, ty = 2 
<strong>输出:</strong> false
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> sx = 1, sy = 1, tx = 1, ty = 1 
<strong>输出:</strong> true
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= sx, sy, tx, ty &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：逆向计算**

从 `(tx, ty)` 开始逆向计算，判断是否可以到达状态 `(sx, sy)`。由于逆向计算是将 tx, ty 中的较大值减少，因此当 `tx > ty` 时可以直接将 tx 的值更新为 `tx % ty`，当 `tx < ty` 时可以将 ty 的值更新为 `ty % tx`。逆向计算需要满足 `tx > sx && ty > sy && tx != ty`。

当条件不成立时，根据此时 tx 和 ty 判断是否可以从起点转换到终点。

-   如果 `tx == sx && ty == sy`，说明此时已经到达起点状态，返回 true；
-   如果 `tx == sx`，若 `ty > sy && (ty - sy) % tx == 0`，返回 true，否则返回 false；
-   如果 `ty == sy`，若 `tx > sx && (tx - sx) % ty == 0`，返回 true，否则返回 false；
-   如果 `tx ≠ sx && ty ≠ sy`，则不可以从起点转换到终点。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reachingPoints(self, sx: int, sy: int, tx: int, ty: int) -> bool:
        while tx > sx and ty > sy and tx != ty:
            if tx > ty:
                tx %= ty
            else:
                ty %= tx
        if tx == sx and ty == sy:
            return True
        if tx == sx:
            return ty > sy and (ty - sy) % tx == 0
        if ty == sy:
            return tx > sx and (tx - sx) % ty == 0
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (tx == sx && ty == sy) {
            return true;
        }
        if (tx == sx) {
            return ty > sy && (ty - sy) % tx == 0;
        }
        if (ty == sy) {
            return tx > sx && (tx - sx) % ty == 0;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty)
                tx %= ty;
            else
                ty %= tx;
        }
        if (tx == sx && ty == sy) return true;
        if (tx == sx) return ty > sy && (ty - sy) % tx == 0;
        if (ty == sy) return tx > sx && (tx - sx) % ty == 0;
        return false;
    }
};
```

### **Go**

```go
func reachingPoints(sx int, sy int, tx int, ty int) bool {
	for tx > sx && ty > sy && tx != ty {
		if tx > ty {
			tx %= ty
		} else {
			ty %= tx
		}
	}
	if tx == sx && ty == sy {
		return true
	}
	if tx == sx {
		return ty > sy && (ty-sy)%tx == 0
	}
	if ty == sy {
		return tx > sx && (tx-sx)%ty == 0
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
