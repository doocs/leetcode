---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0412.Fizz%20Buzz/README_EN.md
tags:
    - Math
    - String
    - Simulation
---

<!-- problem:start -->

# [412. Fizz Buzz](https://leetcode.com/problems/fizz-buzz)

[中文文档](/solution/0400-0499/0412.Fizz%20Buzz/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>a string array </em><code>answer</code><em> (<strong>1-indexed</strong>) where</em>:</p>

<ul>
	<li><code>answer[i] == &quot;FizzBuzz&quot;</code> if <code>i</code> is divisible by <code>3</code> and <code>5</code>.</li>
	<li><code>answer[i] == &quot;Fizz&quot;</code> if <code>i</code> is divisible by <code>3</code>.</li>
	<li><code>answer[i] == &quot;Buzz&quot;</code> if <code>i</code> is divisible by <code>5</code>.</li>
	<li><code>answer[i] == i</code> (as a string) if none of the above conditions are true.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 3
<strong>Output:</strong> ["1","2","Fizz"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 5
<strong>Output:</strong> ["1","2","Fizz","4","Buzz"]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> n = 15
<strong>Output:</strong> ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

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

```js
const fizzBuzz = function (n) {
    let arr = [];
    for (let i = 1; i <= n; i++) {
        if (i % 15 === 0) arr.push('FizzBuzz');
        else if (i % 3 === 0) arr.push('Fizz');
        else if (i % 5 === 0) arr.push('Buzz');
        else arr.push(`${i}`);
    }
    return arr;
};
```

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
            } elseif ($i % 3 == 0 && $i % 5 != 0) {
                array_push($rs, 'Fizz');
            } elseif ($i % 3 != 0 && $i % 5 == 0) {
                array_push($rs, 'Buzz');
            } else {
                array_push($rs, 'FizzBuzz');
            }
        }
        return $rs;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
