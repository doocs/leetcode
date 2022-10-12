# [1342. Number of Steps to Reduce a Number to Zero](https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero)

[中文文档](/solution/1300-1399/1342.Number%20of%20Steps%20to%20Reduce%20a%20Number%20to%20Zero/README.md)

## Description

<p>Given an integer <code>num</code>, return <em>the number of steps to reduce it to zero</em>.</p>

<p>In one step, if the current number is even, you have to divide it by <code>2</code>, otherwise, you have to subtract <code>1</code> from it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 14
<strong>Output:</strong> 6
<strong>Explanation:</strong>&nbsp;
Step 1) 14 is even; divide by 2 and obtain 7.&nbsp;
Step 2) 7 is odd; subtract 1 and obtain 6.
Step 3) 6 is even; divide by 2 and obtain 3.&nbsp;
Step 4) 3 is odd; subtract 1 and obtain 2.&nbsp;
Step 5) 2 is even; divide by 2 and obtain 1.&nbsp;
Step 6) 1 is odd; subtract 1 and obtain 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 8
<strong>Output:</strong> 4
<strong>Explanation:</strong>&nbsp;
Step 1) 8 is even; divide by 2 and obtain 4.&nbsp;
Step 2) 4 is even; divide by 2 and obtain 2.&nbsp;
Step 3) 2 is even; divide by 2 and obtain 1.&nbsp;
Step 4) 1 is odd; subtract 1 and obtain 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = 123
<strong>Output:</strong> 12
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfSteps(self, num: int) -> int:
        ans = 0
        while num:
            if num & 1:
                num -= 1
            else:
                num >>= 1
            ans += 1
        return ans
```

```python
class Solution:
    def numberOfSteps(self, num: int) -> int:
        if num == 0:
            return 0
        return 1 + (self.numberOfSteps(num // 2) if num % 2 == 0 else self.numberOfSteps(num - 1))
```

### **Java**

```java
class Solution {

    public int numberOfSteps(int num) {
        int ans = 0;
        while (num != 0) {
            num = (num & 1) == 1 ? num - 1 : num >> 1;
            ++ans;
        }
        return ans;
    }
}

```

```java
class Solution {

    public int numberOfSteps(int num) {
        if (num == 0) {
            return 0;
        }
        return 1 + numberOfSteps((num & 1) == 0 ? num >> 1 : num - 1);
    }
}
```

### **TypeScript**

```ts
function numberOfSteps(num: number): number {
    let ans = 0;
    while (num) {
        num = num & 1 ? num - 1 : num >>> 1;
        ans++;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfSteps(int num) {
        int ans = 0;
        while (num) {
            num = num & 1 ? num - 1 : num >> 1;
            ++ans;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int numberOfSteps(int num) {
        if (num == 0) return 0;
        return 1 + (num & 1 ? numberOfSteps(num - 1) : numberOfSteps(num >> 1));
    }
};
```

### **Go**

```go
func numberOfSteps(num int) int {
	ans := 0
	for num != 0 {
		if (num & 1) == 1 {
			num--
		} else {
			num >>= 1
		}
		ans++
	}
	return ans
}
```

```go
func numberOfSteps(num int) int {
	if num == 0 {
		return 0
	}
	if (num & 1) == 0 {
		return 1 + numberOfSteps(num>>1)
	}
	return 1 + numberOfSteps(num-1)
}
```

### **Rust**

```rust
impl Solution {
    pub fn number_of_steps(mut num: i32) -> i32 {
        let mut count = 0;
        while num != 0 {
            if num % 2 == 0 {
                num >>= 1;
            } else {
                num -= 1;
            }
            count += 1;
        }
        count
    }
}
```

```rust
impl Solution {
    pub fn number_of_steps(mut num: i32) -> i32 {
        if num == 0 {
            0
        } else if num % 2 == 0 {
            1 + Solution::number_of_steps(num >> 1)
        } else {
            1 + Solution::number_of_steps(num - 1)
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
