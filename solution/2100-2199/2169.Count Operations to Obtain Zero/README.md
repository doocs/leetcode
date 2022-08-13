# [2169. 得到 0 的操作数](https://leetcode.cn/problems/count-operations-to-obtain-zero)

[English Version](/solution/2100-2199/2169.Count%20Operations%20to%20Obtain%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>非负</strong> 整数 <code>num1</code> 和 <code>num2</code> 。</p>

<p>每一步 <strong>操作</strong>&nbsp;中，如果 <code>num1 &gt;= num2</code> ，你必须用 <code>num1</code> 减 <code>num2</code> ；否则，你必须用 <code>num2</code> 减 <code>num1</code> 。</p>

<ul>
	<li>例如，<code>num1 = 5</code> 且 <code>num2 = 4</code> ，应该用&nbsp;<code>num1</code> 减 <code>num2</code> ，因此，得到 <code>num1 = 1</code> 和 <code>num2 = 4</code> 。然而，如果 <code>num1 = 4</code>且 <code>num2 = 5</code> ，一步操作后，得到 <code>num1 = 4</code> 和 <code>num2 = 1</code> 。</li>
</ul>

<p>返回使 <code>num1 = 0</code> 或 <code>num2 = 0</code> 的 <strong>操作数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num1 = 2, num2 = 3
<strong>输出：</strong>3
<strong>解释：</strong>
- 操作 1 ：num1 = 2 ，num2 = 3 。由于 num1 &lt; num2 ，num2 减 num1 得到 num1 = 2 ，num2 = 3 - 2 = 1 。
- 操作 2 ：num1 = 2 ，num2 = 1 。由于 num1 &gt; num2 ，num1 减 num2 。
- 操作 3 ：num1 = 1 ，num2 = 1 。由于 num1 == num2 ，num1 减 num2 。
此时 num1 = 0 ，num2 = 1 。由于 num1 == 0 ，不需要再执行任何操作。
所以总操作数是 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num1 = 10, num2 = 10
<strong>输出：</strong>1
<strong>解释：</strong>
- 操作 1 ：num1 = 10 ，num2 = 10 。由于 num1 == num2 ，num1 减 num2 得到 num1 = 10 - 10 = 0 。
此时 num1 = 0 ，num2 = 10 。由于 num1 == 0 ，不需要再执行任何操作。
所以总操作数是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num1, num2 &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

模拟相减。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countOperations(self, num1: int, num2: int) -> int:
        ans = 0
        while num1 and num2:
            if num1 >= num2:
                num1, num2 = num2, num1
            num2 -= num1
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
            ++ans;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countOperations(num1: number, num2: number): number {
    let ans = 0;
    while (num1 && num2) {
        [num1, num2] = [Math.min(num1, num2), Math.abs(num1 - num2)];
        ans++;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 && num2) {
            if (num1 > num2) swap(num1, num2);
            num2 -= num1;
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countOperations(num1 int, num2 int) int {
	ans := 0
	for num1 != 0 && num2 != 0 {
		if num1 > num2 {
			num1, num2 = num2, num1
		}
		num2 -= num1
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
