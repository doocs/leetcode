# [1323. Maximum 69 Number](https://leetcode.com/problems/maximum-69-number)

[中文文档](/solution/1300-1399/1323.Maximum%2069%20Number/README.md)

## Description

<p>You are given a positive integer <code>num</code> consisting only of digits <code>6</code> and <code>9</code>.</p>

<p>Return <em>the maximum number you can get by changing <strong>at most</strong> one digit (</em><code>6</code><em> becomes </em><code>9</code><em>, and </em><code>9</code><em> becomes </em><code>6</code><em>)</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 9669
<strong>Output:</strong> 9969
<strong>Explanation:</strong> 
Changing the first digit results in 6669.
Changing the second digit results in 9969.
Changing the third digit results in 9699.
Changing the fourth digit results in 9666.
The maximum number is 9969.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 9996
<strong>Output:</strong> 9999
<strong>Explanation:</strong> Changing the last digit 6 to 9 results in the maximum number.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = 9999
<strong>Output:</strong> 9999
<strong>Explanation:</strong> It is better not to apply any change.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10<sup>4</sup></code></li>
	<li><code>num</code>&nbsp;consists of only <code>6</code> and <code>9</code> digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximum69Number(self, num: int) -> int:
        return int(str(num).replace("6", "9", 1))
```

### **Java**

```java
class Solution {
    public int maximum69Number(int num) {
        return Integer.valueOf(String.valueOf(num).replaceFirst("6", "9"));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximum69Number(int num) {
        string s = to_string(num);
        for (char& ch : s) {
            if (ch == '6') {
                ch = '9';
                break;
            }
        }
        return stoi(s);
    }
};
```

### **Go**

```go
func maximum69Number(num int) int {
	s := strconv.Itoa(num)
	nums := []byte(s)
	for i, ch := range nums {
		if ch == '6' {
			nums[i] = '9'
			break
		}
	}
	ans, _ := strconv.Atoi(string(nums))
	return ans
}
```

### **TypeScript**

```ts
function maximum69Number(num: number): number {
    return Number((num + '').replace('6', '9'));
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum69_number(num: i32) -> i32 {
        num.to_string().replacen('6', "9", 1).parse().unwrap()
    }
}
```

### **C**

```c
int maximum69Number(int num) {
    int n = 0;
    int i = 0;
    int t = num;
    while (t) {
        n++;
        if (t % 10 == 6) {
            i = n;
        }
        t /= 10;
    }
    return num + 3 * pow(10, i - 1);
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer $num
     * @return Integer
     */
    function maximum69Number($num) {
        $num = strval($num);
        $n = strpos($num, "6");
        $num[$n] = 9;
        return intval($num);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
