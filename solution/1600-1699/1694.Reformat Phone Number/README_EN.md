# [1694. Reformat Phone Number](https://leetcode.com/problems/reformat-phone-number)

[中文文档](/solution/1600-1699/1694.Reformat%20Phone%20Number/README.md)

## Description

<p>You are given a phone number as a string <code>number</code>. <code>number</code> consists of digits, spaces <code>&#39; &#39;</code>, and/or dashes <code>&#39;-&#39;</code>.</p>

<p>You would like to reformat the phone number in a certain manner. Firstly, <strong>remove</strong> all spaces and dashes. Then, <strong>group</strong> the digits from left to right into blocks of length 3 <strong>until</strong> there are 4 or fewer digits. The final digits are then grouped as follows:</p>

<ul>
	<li>2 digits: A single block of length 2.</li>
	<li>3 digits: A single block of length 3.</li>
	<li>4 digits: Two blocks of length 2 each.</li>
</ul>

<p>The blocks are then joined by dashes. Notice that the reformatting process should <strong>never</strong> produce any blocks of length 1 and produce <strong>at most</strong> two blocks of length 2.</p>

<p>Return <em>the phone number after formatting.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;1-23-45 6&quot;
<strong>Output:</strong> &quot;123-456&quot;
<strong>Explanation:</strong> The digits are &quot;123456&quot;.
Step 1: There are more than 4 digits, so group the next 3 digits. The 1st block is &quot;123&quot;.
Step 2: There are 3 digits remaining, so put them in a single block of length 3. The 2nd block is &quot;456&quot;.
Joining the blocks gives &quot;123-456&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;123 4-567&quot;
<strong>Output:</strong> &quot;123-45-67&quot;
<strong>Explanation: </strong>The digits are &quot;1234567&quot;.
Step 1: There are more than 4 digits, so group the next 3 digits. The 1st block is &quot;123&quot;.
Step 2: There are 4 digits left, so split them into two blocks of length 2. The blocks are &quot;45&quot; and &quot;67&quot;.
Joining the blocks gives &quot;123-45-67&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;123 4-5678&quot;
<strong>Output:</strong> &quot;123-456-78&quot;
<strong>Explanation:</strong> The digits are &quot;12345678&quot;.
Step 1: The 1st block is &quot;123&quot;.
Step 2: The 2nd block is &quot;456&quot;.
Step 3: There are 2 digits left, so put them in a single block of length 2. The 3rd block is &quot;78&quot;.
Joining the blocks gives &quot;123-456-78&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= number.length &lt;= 100</code></li>
	<li><code>number</code> consists of digits and the characters <code>&#39;-&#39;</code> and <code>&#39; &#39;</code>.</li>
	<li>There are at least <strong>two</strong> digits in <code>number</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reformatNumber(self, number: str) -> str:
        number = number.replace("-", "").replace(" ", "")
        n = len(number)
        ans = [number[i * 3 : i * 3 + 3] for i in range(n // 3)]
        if n % 3 == 1:
            ans[-1] = ans[-1][:2]
            ans.append(number[-2:])
        elif n % 3 == 2:
            ans.append(number[-2:])
        return "-".join(ans)
```

### **Java**

```java
class Solution {
    public String reformatNumber(String number) {
        number = number.replace("-", "").replace(" ", "");
        int n = number.length();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n / 3; ++i) {
            ans.add(number.substring(i * 3, i * 3 + 3));
        }
        if (n % 3 == 1) {
            ans.set(ans.size() - 1, ans.get(ans.size() - 1).substring(0, 2));
            ans.add(number.substring(n - 2));
        } else if (n % 3 == 2) {
            ans.add(number.substring(n - 2));
        }
        return String.join("-", ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reformatNumber(string number) {
        string s;
        for (char c : number) {
            if (c != ' ' && c != '-') {
                s.push_back(c);
            }
        }
        int n = s.size();
        vector<string> res;
        for (int i = 0; i < n / 3; ++i) {
            res.push_back(s.substr(i * 3, 3));
        }
        if (n % 3 == 1) {
            res.back() = res.back().substr(0, 2);
            res.push_back(s.substr(n - 2));
        } else if (n % 3 == 2) {
            res.push_back(s.substr(n - 2));
        }
        string ans;
        for (auto& v : res) {
            ans += v;
            ans += "-";
        }
        ans.pop_back();
        return ans;
    }
};
```

### **Go**

```go
func reformatNumber(number string) string {
	number = strings.ReplaceAll(number, " ", "")
	number = strings.ReplaceAll(number, "-", "")
	n := len(number)
	ans := []string{}
	for i := 0; i < n/3; i++ {
		ans = append(ans, number[i*3:i*3+3])
	}
	if n%3 == 1 {
		ans[len(ans)-1] = ans[len(ans)-1][:2]
		ans = append(ans, number[n-2:])
	} else if n%3 == 2 {
		ans = append(ans, number[n-2:])
	}
	return strings.Join(ans, "-")
}
```

### **TypeScript**

```ts
function reformatNumber(number: string): string {
    const cs = [...number].filter(c => c !== ' ' && c !== '-');
    const n = cs.length;
    return cs
        .map((v, i) => {
            if (
                ((i + 1) % 3 === 0 && i < n - 2) ||
                (n % 3 === 1 && n - 3 === i)
            ) {
                return v + '-';
            }
            return v;
        })
        .join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn reformat_number(number: String) -> String {
        let cs: Vec<char> = number.chars().filter(|&c| c != ' ' && c != '-').collect();
        let n = cs.len();
        cs.iter()
            .enumerate()
            .map(|(i, c)| {
                if (i + 1) % 3 == 0 && i < n - 2 || n % 3 == 1 && i == n - 3 {
                    return c.to_string() + &"-";
                }
                c.to_string()
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
