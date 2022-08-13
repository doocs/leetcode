# [2180. Count Integers With Even Digit Sum](https://leetcode.com/problems/count-integers-with-even-digit-sum)

[中文文档](/solution/2100-2199/2180.Count%20Integers%20With%20Even%20Digit%20Sum/README.md)

## Description

<p>Given a positive integer <code>num</code>, return <em>the number of positive integers <strong>less than or equal to</strong></em> <code>num</code> <em>whose digit sums are <strong>even</strong></em>.</p>

<p>The <strong>digit sum</strong> of a positive integer is the sum of all its digits.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The only integers less than or equal to 4 whose digit sums are even are 2 and 4.    
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 30
<strong>Output:</strong> 14
<strong>Explanation:</strong>
The 14 integers less than or equal to 30 whose digit sums are even are
2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, and 28.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countEven(self, num: int) -> int:
        ans = 0
        for i in range(1, num + 1):
            t = 0
            while i:
                t += i % 10
                i //= 10
            if t % 2 == 0:
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int j = i, t = 0;
            while (j > 0) {
                t += j % 10;
                j /= 10;
            }
            if (t % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countEven(num: number): number {
    let ans = 0;
    for (let i = 2; i <= num; i++) {
        if ([...String(i)].reduce((a, c) => a + Number(c), 0) % 2 == 0) {
            ans++;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int t = 0;
            for (int j = i; j; j /= 10) t += j % 10;
            if (t % 2 == 0) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countEven(num int) int {
	ans := 0
	for i := 1; i <= num; i++ {
		t := 0
		for j := i; j > 0; j /= 10 {
			t += j % 10
		}
		if t%2 == 0 {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
