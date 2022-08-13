# [LCP 17. 速算机器人](https://leetcode.cn/problems/nGK0Fy)

## 题目描述

<!-- 这里写题目描述 -->

小扣在秋日市集发现了一款速算机器人。店家对机器人说出两个数字（记作 `x` 和 `y`），请小扣说出计算指令：

-   `"A"` 运算：使 `x = 2 * x + y`；

-   `"B"` 运算：使 `y = 2 * y + x`。

在本次游戏中，店家说出的数字为 `x = 1` 和 `y = 0`，小扣说出的计算指令记作仅由大写字母 `A`、`B` 组成的字符串 `s`，字符串中字符的顺序表示计算顺序，请返回最终 `x` 与 `y` 的和为多少。

**示例 1：**

> 输入：`s = "AB"`

>

> 输出：`4`

>

> 解释：

> 经过一次 A 运算后，x = 2, y = 0。

> 再经过一次 B 运算，x = 2, y = 2。

> 最终 x 与 y 之和为 4。

**提示：**

-   `0 <= s.length <= 10`
-   `s` 由 `'A'` 和 `'B'` 组成

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def calculate(self, s: str) -> int:
        x, y = 1, 0
        for c in s:
            if c == 'A':
                x = x * 2 + y
            else:
                y = y * 2 + x
        return x + y
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int calculate(String s) {
        int x = 1, y = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                x = x * 2 + y;
            } else {
                y = y * 2 + x;
            }
        }
        return x + y;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int calculate(string s) {
        int x = 1, y = 0;
        for (char& c : s) {
            if (c == 'A')
                x = x * 2 + y;
            else
                y = y * 2 + x;
        }
        return x + y;
    }
};
```

### **Go**

```go
func calculate(s string) int {
	x, y := 1, 0
	for _, c := range s {
		if c == 'A' {
			x = x*2 + y
		} else {
			y = y*2 + x
		}
	}
	return x + y
}
```

### **...**

```

```

<!-- tabs:end -->
