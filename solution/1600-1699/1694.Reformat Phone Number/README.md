# [1694. 重新格式化电话号码](https://leetcode.cn/problems/reformat-phone-number)

[English Version](/solution/1600-1699/1694.Reformat%20Phone%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串形式的电话号码 <code>number</code> 。<code>number</code> 由数字、空格 <code>' '</code>、和破折号 <code>'-'</code> 组成。</p>

<p>请你按下述方式重新格式化电话号码。</p>

<ul>
	<li>首先，<strong>删除</strong> 所有的空格和破折号。</li>
	<li>其次，将数组从左到右 <strong>每 3 个一组</strong> 分块，<strong>直到 </strong>剩下 4 个或更少数字。剩下的数字将按下述规定再分块：
	<ul>
		<li>2 个数字：单个含 2 个数字的块。</li>
		<li>3 个数字：单个含 3 个数字的块。</li>
		<li>4 个数字：两个分别含 2 个数字的块。</li>
	</ul>
	</li>
</ul>

<p>最后用破折号将这些块连接起来。注意，重新格式化过程中 <strong>不应该</strong> 生成仅含 1 个数字的块，并且 <strong>最多</strong> 生成两个含 2 个数字的块。</p>

<p>返回格式化后的电话号码。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>number = "1-23-45 6"
<strong>输出：</strong>"123-456"
<strong>解释：</strong>数字是 "123456"
步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
步骤 2：剩下 3 个数字，将它们放入单个含 3 个数字的块。第 2 个块是 "456" 。
连接这些块后得到 "123-456" 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>number = "123 4-567"
<strong>输出：</strong>"123-45-67"
<strong>解释：</strong>数字是 "1234567".
步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
步骤 2：剩下 4 个数字，所以将它们分成两个含 2 个数字的块。这 2 块分别是 "45" 和 "67" 。
连接这些块后得到 "123-45-67" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>number = "123 4-5678"
<strong>输出：</strong>"123-456-78"
<strong>解释：</strong>数字是 "12345678" 。
步骤 1：第 1 个块 "123" 。
步骤 2：第 2 个块 "456" 。
步骤 3：剩下 2 个数字，将它们放入单个含 2 个数字的块。第 3 个块是 "78" 。
连接这些块后得到 "123-456-78" 。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>number = "12"
<strong>输出：</strong>"12"
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>number = "--17-5 229 35-39475 "
<strong>输出：</strong>"175-229-353-94-75"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= number.length <= 100</code></li>
	<li><code>number</code> 由数字和字符 <code>'-'</code> 及 <code>' '</code> 组成。</li>
	<li><code>number</code> 中至少含 <strong>2</strong> 个数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：简单模拟**

先按照题意，去除字符串中的所有空格和破折号。

记当前字符串长度为 $n$，然后从前往后遍历字符串，每 $3$ 个字符为一组，将其加入结果字符串中，共取 $n / 3$ 组。

若最后剩余 $1$ 个字符，则将前面的最后一组的最后一个字符与该字符组成一个新的两个字符的组，加入结果字符串中；若最后剩余 $2$ 个字符，直接将这连个字符组成一个新的组，加入结果字符串中。

最后将所有组之间加上破折号，返回结果字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
