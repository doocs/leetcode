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

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We iterate through each integer from 1 to $n$. For each integer, we check whether it is a multiple of both 3 and 5, or just a multiple of 3, or just a multiple of 5. Based on the check result, we add the corresponding string to the answer array.

The time complexity is $O(n)$, where $n$ is the integer given in the problem. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

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
