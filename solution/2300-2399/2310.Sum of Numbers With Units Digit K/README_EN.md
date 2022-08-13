# [2310. Sum of Numbers With Units Digit K](https://leetcode.com/problems/sum-of-numbers-with-units-digit-k)

[中文文档](/solution/2300-2399/2310.Sum%20of%20Numbers%20With%20Units%20Digit%20K/README.md)

## Description

<p>Given two integers <code>num</code> and <code>k</code>, consider a set of positive integers with the following properties:</p>

<ul>
	<li>The units digit of each integer is <code>k</code>.</li>
	<li>The sum of the integers is <code>num</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> possible size of such a set, or </em><code>-1</code><em> if no such set exists.</em></p>

<p>Note:</p>

<ul>
	<li>The set can contain multiple instances of the same integer, and the sum of an empty set is considered <code>0</code>.</li>
	<li>The <strong>units digit</strong> of a number is the rightmost digit of the number.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 58, k = 9
<strong>Output:</strong> 2
<strong>Explanation:</strong>
One valid set is [9,49], as the sum is 58 and each integer has a units digit of 9.
Another valid set is [19,39].
It can be shown that 2 is the minimum possible size of a valid set.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 37, k = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to obtain a sum of 37 using only integers that have a units digit of 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = 0, k = 7
<strong>Output:</strong> 0
<strong>Explanation:</strong> The sum of an empty set is considered 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 3000</code></li>
	<li><code>0 &lt;= k &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        if num == 0:
            return 0
        for i in range(1, num + 1):
            if (t := num - k * i) >= 0 and t % 10 == 0:
                return i
        return -1
```

```python
class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        if num == 0:
            return 0
        for i in range(1, 11):
            if (k * i) % 10 == num % 10 and k * i <= num:
                return i
        return -1
```

```python
class Solution:
    def minimumNumbers(self, num: int, k: int) -> int:
        @cache
        def dfs(v):
            if v == 0:
                return 0
            if v < 10 and v % k:
                return inf
            i = 0
            t = inf
            while (x := i * 10 + k) <= v:
                t = min(t, dfs(v - x))
                i += 1
            return t + 1

        if num == 0:
            return 0
        if k == 0:
            return -1 if num % 10 else 1
        ans = dfs(num)
        return -1 if ans >= inf else ans
```

### **Java**

```java
class Solution {
    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        for (int i = 1; i <= num; ++i) {
            int t = num - k * i;
            if (t >= 0 && t % 10 == 0) {
                return i;
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        for (int i = 1; i <= 10; ++i) {
            if ((k * i) % 10 ==  num % 10 && k * i <= num) {
                return i;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        for (int i = 1; i <= num; ++i) {
            int t = num - k * i;
            if (t >= 0 && t % 10 == 0) return i;
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int minimumNumbers(int num, int k) {
        if (!num) return 0;
        for (int i = 1; i <= 10; ++i)
            if ((k * i) % 10 == num % 10 && k * i <= num)
                return i;
        return -1;
    }
};
```

### **Go**

```go
func minimumNumbers(num int, k int) int {
	if num == 0 {
		return 0
	}
	for i := 1; i <= num; i++ {
		t := num - k*i
		if t >= 0 && t%10 == 0 {
			return i
		}
	}
	return -1
}
```

```go
func minimumNumbers(num int, k int) int {
	if num == 0 {
		return 0
	}
	for i := 1; i <= 10; i++ {
		if (k*i)%10 == num%10 && k*i <= num {
			return i
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function minimumNumbers(num: number, k: number): number {
    if (!num) return 0;
    let digit = num % 10;
    for (let i = 1; i < 11; i++) {
        let target = i * k;
        if (target <= num && target % 10 == digit) return i;
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
