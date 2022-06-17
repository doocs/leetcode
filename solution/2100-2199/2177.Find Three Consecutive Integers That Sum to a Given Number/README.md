# [2177. 找到和为给定整数的三个连续整数](https://leetcode.cn/problems/find-three-consecutive-integers-that-sum-to-a-given-number)

[English Version](/solution/2100-2199/2177.Find%20Three%20Consecutive%20Integers%20That%20Sum%20to%20a%20Given%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>num</code>&nbsp;，请你返回三个连续的整数，它们的&nbsp;<strong>和</strong>&nbsp;为<em>&nbsp;</em><code>num</code>&nbsp;。如果&nbsp;<code>num</code>&nbsp;无法被表示成三个连续整数的和，请你返回一个 <strong>空</strong>&nbsp;数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>num = 33
<b>输出：</b>[10,11,12]
<b>解释：</b>33 可以表示为 10 + 11 + 12 = 33 。
10, 11, 12 是 3 个连续整数，所以返回 [10, 11, 12] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>num = 4
<b>输出：</b>[]
<b>解释：</b>没有办法将 4 表示成 3 个连续整数的和。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>15</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOfThree(self, num: int) -> List[int]:
        a, b = divmod(num, 3)
        return [] if b else [a - 1, a, a + 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[]{};
        }
        long x = num / 3;
        return new long[]{x - 1, x, x + 1};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<long long> sumOfThree(long long num) {
        if (num % 3) return {};
        long long x = num / 3;
        return {x - 1, x, x + 1};
    }
};
```

### **Go**

```go
func sumOfThree(num int64) []int64 {
	if num%3 != 0 {
		return []int64{}
	}
	x := num / 3
	return []int64{x - 1, x, x + 1}
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
