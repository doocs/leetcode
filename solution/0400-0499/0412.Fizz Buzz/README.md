# [412. Fizz Buzz](https://leetcode.cn/problems/fizz-buzz)

[English Version](/solution/0400-0499/0412.Fizz%20Buzz/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，找出从 <code>1</code> 到 <code>n</code> 各个整数的 Fizz Buzz 表示，并用字符串数组 <code>answer</code>（<strong>下标从 1 开始</strong>）返回结果，其中：</p>

<ul>
	<li><code>answer[i] == "FizzBuzz"</code> 如果 <code>i</code> 同时是 <code>3</code> 和 <code>5</code> 的倍数。</li>
	<li><code>answer[i] == "Fizz"</code> 如果 <code>i</code> 是 <code>3</code> 的倍数。</li>
	<li><code>answer[i] == "Buzz"</code> 如果 <code>i</code> 是 <code>5</code> 的倍数。</li>
	<li><code>answer[i] == i</code> （以字符串形式）如果上述条件全不满足。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>["1","2","Fizz"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>["1","2","Fizz","4","Buzz"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 15
<strong>输出：</strong>["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fizzBuzz(self, n: int) -> List[str]:
        ans = []
        for i in range(1, n + 1):
            if i % 15 == 0:
                ans.append('FizzBuzz')
            elif i % 3 == 0:
                ans.append('Fizz')
            elif i % 5 == 0:
                ans.append('Buzz')
            else:
                ans.append(str(i))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            String s = "";
            if (i % 3 == 0) {
                s += "Fizz";
            }
            if (i % 5 == 0) {
                s += "Buzz";
            }
            if (s.length() == 0) {
                s += i;
            }
            ans.add(s);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> ans;
        for (int i = 1; i <= n; ++i) {
            string s = "";
            if (i % 3 == 0) s += "Fizz";
            if (i % 5 == 0) s += "Buzz";
            if (s.size() == 0) s = to_string(i);
            ans.push_back(s);
        }
        return ans;
    }
};
```

### **Go**

```go
func fizzBuzz(n int) []string {
	var ans []string
	for i := 1; i <= n; i++ {
		s := &strings.Builder{}
		if i%3 == 0 {
			s.WriteString("Fizz")
		}
		if i%5 == 0 {
			s.WriteString("Buzz")
		}
		if s.Len() == 0 {
			s.WriteString(strconv.Itoa(i))
		}
		ans = append(ans, s.String())
	}
	return ans
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer $n
     * @return String[]
     */
    function fizzBuzz($n) {
        $rs = [];
        for ($i = 1; $i <= $n; $i++) {
            if ($i % 3 != 0 && $i % 5 != 0) {
                array_push($rs, strval($i));
            } else if ($i % 3 == 0 && $i % 5 != 0) {
                array_push($rs, "Fizz");
            } else if ($i % 3 != 0 && $i % 5 == 0) {
                array_push($rs, "Buzz");
            } else {
                array_push($rs, "FizzBuzz");
            }
        }
        return $rs;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
