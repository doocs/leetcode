# [390. 消除游戏](https://leetcode-cn.com/problems/elimination-game)

[English Version](/solution/0300-0399/0390.Elimination%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个从1 到 n 排序的整数列表。<br />
首先，从左到右，从第一个数字开始，每隔一个数字进行删除，直到列表的末尾。<br />
第二步，在剩下的数字中，从右到左，从倒数第一个数字开始，每隔一个数字进行删除，直到列表开头。<br />
我们不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。<br />
返回长度为 n 的列表中，最后剩下的数字。</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入:</strong>
n = 9,
<u>1</u> 2 <u>3</u> 4 <u>5</u> 6 <u>7</u> 8 <u>9</u>
2 <u>4</u> 6 <u>8</u>
<u>2</u> 6
6

<strong>输出:</strong>
6</pre>

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
        for (int i = 0, cnt = n; cnt > 1; cnt >>= 1, step <<= 1, ++i)
        {
            if (i % 2)
            {
                an -= step;
                if (cnt % 2) a1 += step;
            }
            else
            {
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
