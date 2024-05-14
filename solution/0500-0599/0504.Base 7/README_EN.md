---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0504.Base%207/README_EN.md
tags:
    - Math
---

# [504. Base 7](https://leetcode.com/problems/base-7)

[中文文档](/solution/0500-0599/0504.Base%207/README.md)

## Description

<p>Given an integer <code>num</code>, return <em>a string of its <strong>base 7</strong> representation</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> num = 100
<strong>Output:</strong> "202"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> num = -7
<strong>Output:</strong> "-10"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>7</sup> &lt;= num &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def convertToBase7(self, num: int) -> str:
        if num == 0:
            return '0'
        if num < 0:
            return '-' + self.convertToBase7(-num)
        ans = []
        while num:
            ans.append(str(num % 7))
            num //= 7
        return ''.join(ans[::-1])
```

```java
class Solution {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        return sb.reverse().toString();
    }
}
```

```cpp
class Solution {
public:
    string convertToBase7(int num) {
        if (num == 0) return "0";
        if (num < 0) return "-" + convertToBase7(-num);
        string ans = "";
        while (num) {
            ans = to_string(num % 7) + ans;
            num /= 7;
        }
        return ans;
    }
};
```

```go
func convertToBase7(num int) string {
	if num == 0 {
		return "0"
	}
	if num < 0 {
		return "-" + convertToBase7(-num)
	}
	ans := []byte{}
	for num != 0 {
		ans = append([]byte{'0' + byte(num%7)}, ans...)
		num /= 7
	}
	return string(ans)
}
```

```ts
function convertToBase7(num: number): string {
    if (num == 0) {
        return '0';
    }
    let res = '';
    const isMinus = num < 0;
    if (isMinus) {
        num = -num;
    }
    while (num != 0) {
        const r = num % 7;
        res = r + res;
        num = (num - r) / 7;
    }
    return isMinus ? '-' + res : res;
}
```

```rust
impl Solution {
    pub fn convert_to_base7(mut num: i32) -> String {
        if num == 0 {
            return String::from("0");
        }
        let mut res = String::new();
        let is_minus = num < 0;
        if is_minus {
            num = -num;
        }
        while num != 0 {
            res.push_str((num % 7).to_string().as_str());
            num /= 7;
        }
        if is_minus {
            res.push('-');
        }
        res.chars().rev().collect()
    }
}
```

<!-- tabs:end -->

<!-- end -->
