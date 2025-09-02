---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0412.Fizz%20Buzz/README.md
tags:
    - 数学
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [412. Fizz Buzz](https://leetcode.cn/problems/fizz-buzz)

[English Version](/solution/0400-0499/0412.Fizz%20Buzz/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，返回一个字符串数组 <code>answer</code>（<strong>下标从 1 开始</strong>），其中：</p>

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历从 1 到 n 的每个整数，对于每个整数，我们检查它是否是 3 和 5 的倍数，或者只是 3 的倍数，或者只是 5 的倍数。根据检查的结果，我们将相应的字符串添加到答案数组中。

时间复杂度 $O(n)$，其中 $n$ 是题目给定的整数。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> ans;
        for (int i = 1; i <= n; ++i) {
            string s = "";
            if (i % 3 == 0) {
                s += "Fizz";
            }
            if (i % 5 == 0) {
                s += "Buzz";
            }
            if (s.empty()) {
                s = to_string(i);
            }
            ans.push_back(s);
        }
        return ans;
    }
};
```

#### Go

```go
func fizzBuzz(n int) []string {
	ans := make([]string, 0, n)
	for i := 1; i < n+1; i++ {
		switch {
		case i%15 == 0:
			ans = append(ans, "FizzBuzz")
		case i%3 == 0:
			ans = append(ans, "Fizz")
		case i%5 == 0:
			ans = append(ans, "Buzz")
		default:
			ans = append(ans, strconv.Itoa(i))
		}
	}
	return ans
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {string[]}
 */
var fizzBuzz = function (n) {
    const ans = [];
    for (let i = 1; i <= n; ++i) {
        if (i % 15 === 0) {
            ans.push('FizzBuzz');
        } else if (i % 3 === 0) {
            ans.push('Fizz');
        } else if (i % 5 === 0) {
            ans.push('Buzz');
        } else {
            ans.push(`${i}`);
        }
    }
    return ans;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $n
     * @return String[]
     */
    function fizzBuzz($n) {
        $ans = [];
        for ($i = 1; $i <= $n; ++$i) {
            $s = '';
            if ($i % 3 == 0) {
                $s .= 'Fizz';
            }
            if ($i % 5 == 0) {
                $s .= 'Buzz';
            }
            if (strlen($s) == 0) {
                $s .= $i;
            }
            $ans[] = $s;
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
