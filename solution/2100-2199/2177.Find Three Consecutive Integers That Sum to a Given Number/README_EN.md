# [2177. Find Three Consecutive Integers That Sum to a Given Number](https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number)

[中文文档](/solution/2100-2199/2177.Find%20Three%20Consecutive%20Integers%20That%20Sum%20to%20a%20Given%20Number/README.md)

## Description

<p>Given an integer <code>num</code>, return <em>three consecutive integers (as a sorted array)</em><em> that <strong>sum</strong> to </em><code>num</code>. If <code>num</code> cannot be expressed as the sum of three consecutive integers, return<em> an <strong>empty</strong> array.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 33
<strong>Output:</strong> [10,11,12]
<strong>Explanation:</strong> 33 can be expressed as 10 + 11 + 12 = 33.
10, 11, 12 are 3 consecutive integers, so we return [10, 11, 12].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 4
<strong>Output:</strong> []
<strong>Explanation:</strong> There is no way to express 4 as the sum of 3 consecutive integers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>15</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOfThree(self, num: int) -> List[int]:
        a, b = divmod(num, 3)
        return [] if b else [a - 1, a, a + 1]
```

### **Java**

```java
class Solution {
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[] {};
        }
        long x = num / 3;
        return new long[] {x - 1, x, x + 1};
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
