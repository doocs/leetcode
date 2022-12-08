# [1812. 判断国际象棋棋盘中一个格子的颜色](https://leetcode.cn/problems/determine-color-of-a-chessboard-square)

[English Version](/solution/1800-1899/1812.Determine%20Color%20of%20a%20Chessboard%20Square/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个坐标 <code>coordinates</code> ，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。下图是国际象棋棋盘示意图。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1812.Determine%20Color%20of%20a%20Chessboard%20Square/images/chessboard.png" style="width: 400px; height: 396px;" /></p>

<p>如果所给格子的颜色是白色，请你返回 <code>true</code>，如果是黑色，请返回 <code>false</code> 。</p>

<p>给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>coordinates = "a1"
<b>输出：</b>false
<b>解释：</b>如上图棋盘所示，"a1" 坐标的格子是黑色的，所以返回 false 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>coordinates = "h3"
<b>输出：</b>true
<b>解释：</b>如上图棋盘所示，"h3" 坐标的格子是白色的，所以返回 true 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>coordinates = "c7"
<b>输出：</b>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>coordinates.length == 2</code></li>
	<li><code>'a' <= coordinates[0] <= 'h'</code></li>
	<li><code>'1' <= coordinates[1] <= '8'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：找规律**

观察棋盘我们发现，颜色相同的两个格子 $(x_1, y_1)$ 和 $(x_2, y_2)$ 满足 $x_1 + y_1$ 和 $x_2 + y_2$ 均为奇数或偶数。

因此，我们可以根据 `coordinates` 获取对应的坐标 $(x, y)$，如果 $x + y$ 为奇数，则格子为白色，返回 `true`，否则返回 `false`。

时间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def squareIsWhite(self, coordinates: str) -> bool:
        return (ord(coordinates[0]) + ord(coordinates[1])) % 2 == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean squareIsWhite(String coordinates) {
        return (coordinates.charAt(0) + coordinates.charAt(1)) % 2 == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool squareIsWhite(string coordinates) {
        return (coordinates[0] + coordinates[1]) % 2;
    }
};
```

### **Go**

```go
func squareIsWhite(coordinates string) bool {
	return (coordinates[0]+coordinates[1])%2 == 1
}
```

### **JavaScript**

```js
/**
 * @param {string} coordinates
 * @return {boolean}
 */
var squareIsWhite = function (coordinates) {
    const x = coordinates.charAt(0).charCodeAt();
    const y = coordinates.charAt(1).charCodeAt();
    return (x + y) % 2 == 1;
};
```

### **TypeScript**

```ts
function squareIsWhite(coordinates: string): boolean {
    return ((coordinates.charCodeAt(0) + coordinates.charCodeAt(1)) & 1) === 1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn square_is_white(coordinates: String) -> bool {
        let s = coordinates.as_bytes();
        s[0] + s[1] & 1 == 1
    }
}
```

### **C**

```c
bool squareIsWhite(char *coordinates) {
    return (coordinates[0] + coordinates[1]) & 1;
}
```

### **...**

```

```

<!-- tabs:end -->
