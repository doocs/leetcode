# [1323. 6 和 9 组成的最大数字](https://leetcode.cn/problems/maximum-69-number)

[English Version](/solution/1300-1399/1323.Maximum%2069%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由数字 6 和 9 组成的正整数&nbsp;<code>num</code>。</p>

<p>你最多只能翻转一位数字，将 6 变成&nbsp;9，或者把&nbsp;9 变成&nbsp;6 。</p>

<p>请返回你可以得到的最大数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 9669
<strong>输出：</strong>9969
<strong>解释：</strong>
改变第一位数字可以得到 6669 。
改变第二位数字可以得到 9969 。
改变第三位数字可以得到 9699 。
改变第四位数字可以得到 9666 。
其中最大的数字是 9969 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 9996
<strong>输出：</strong>9999
<strong>解释：</strong>将最后一位从 6 变到 9，其结果 9999 是最大的数。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = 9999
<strong>输出：</strong>9999
<strong>解释：</strong>无需改变就已经是最大的数字了。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10^4</code></li>
	<li><code>num</code>&nbsp;每一位上的数字都是 6 或者&nbsp;9 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们将数组转换为字符串，然后从左到右遍历字符串，找到第一个出现的 $6$，将其替换为 $9$，然后返回转换后的字符串对应的整数即可。

时间复杂度 $O(\log num)$，空间复杂度 $O(\log num)$。其中 $num$ 为给定的整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximum69Number(self, num: int) -> int:
        return int(str(num).replace("6", "9", 1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
