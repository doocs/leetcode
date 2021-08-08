# [1342. 将数字变成 0 的操作次数](https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero)

[English Version](/solution/1300-1399/1342.Number%20of%20Steps%20to%20Reduce%20a%20Number%20to%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非负整数&nbsp;<code>num</code>&nbsp;，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 14
<strong>输出：</strong>6
<strong>解释：
</strong>步骤 1) 14 是偶数，除以 2 得到 7 。
步骤 2） 7 是奇数，减 1 得到 6 。
步骤 3） 6 是偶数，除以 2 得到 3 。
步骤 4） 3 是奇数，减 1 得到 2 。
步骤 5） 2 是偶数，除以 2 得到 1 。
步骤 6） 1 是奇数，减 1 得到 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 8
<strong>输出：</strong>4
<strong>解释：</strong>
步骤 1） 8 是偶数，除以 2 得到 4 。
步骤 2） 4 是偶数，除以 2 得到 2 。
步骤 3） 2 是偶数，除以 2 得到 1 。
步骤 4） 1 是奇数，减 1 得到 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = 123
<strong>输出：</strong>12
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfSteps(self, num: int) -> int:
        res = 0
        while num:
            if (num & 1) == 0:
                num >>= 1
            else:
                num -= 1
            res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfSteps(int num) {
        int res = 0;
        while (num != 0) {
            if ((num & 1) == 0) {
                num >>= 1;
            } else {
                --num;
            }
            ++res;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfSteps(int num) {
        int res = 0;
        while (num)
        {
            if ((num & 1) == 0) num >>= 1;
            else --num;
            ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func numberOfSteps(num int) int {
	res := 0
	for num != 0 {
		if (num & 1) == 0 {
			num >>= 1
		} else {
			num--
		}
		res++
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
