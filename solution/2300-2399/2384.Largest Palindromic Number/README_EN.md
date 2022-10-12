# [2384. Largest Palindromic Number](https://leetcode.com/problems/largest-palindromic-number)

[中文文档](/solution/2300-2399/2384.Largest%20Palindromic%20Number/README.md)

## Description

<p>You are given a string <code>num</code> consisting of digits only.</p>

<p>Return <em>the <strong>largest palindromic</strong> integer (in the form of a string) that can be formed using digits taken from </em><code>num</code>. It should not contain <strong>leading zeroes</strong>.</p>

<p><strong>Notes:</strong></p>

<ul>
	<li>You do <strong>not</strong> need to use all the digits of <code>num</code>, but you must use <strong>at least</strong> one digit.</li>
	<li>The digits can be reordered.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;444947137&quot;
<strong>Output:</strong> &quot;7449447&quot;
<strong>Explanation:</strong> 
Use the digits &quot;4449477&quot; from &quot;<u><strong>44494</strong></u><u><strong>7</strong></u>13<u><strong>7</strong></u>&quot; to form the palindromic integer &quot;7449447&quot;.
It can be shown that &quot;7449447&quot; is the largest palindromic integer that can be formed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;00009&quot;
<strong>Output:</strong> &quot;9&quot;
<strong>Explanation:</strong> 
It can be shown that &quot;9&quot; is the largest palindromic integer that can be formed.
Note that the integer returned should not contain leading zeroes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> consists of digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestPalindromic(self, num: str) -> str:
        cnt = Counter(num)
        ans = ''
        for i in range(9, -1, -1):
            v = str(i)
            if cnt[v] % 2:
                ans = v
                cnt[v] -= 1
                break
        for i in range(10):
            v = str(i)
            if cnt[v]:
                cnt[v] //= 2
                s = cnt[v] * v
                ans = s + ans + s
        return ans.strip('0') or '0'
```

### **Java**

```java
class Solution {
    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for (char c : num.toCharArray()) {
            ++cnt[c - '0'];
        }
        String mid = "";
        for (int i = 9; i >= 0; --i) {
            if (cnt[i] % 2 == 1) {
                mid += i;
                --cnt[i];
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            if (cnt[i] > 0) {
                cnt[i] >>= 1;
                sb.append(("" + i).repeat(cnt[i]));
            }
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        String t = sb.toString();
        String ans = sb.reverse().toString() + mid + t;
        return "".equals(ans) ? "0" : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string largestPalindromic(string num) {
        vector<int> cnt(10);
        for (char c : num) ++cnt[c - '0'];
        string mid = "";
        for (int i = 9; ~i; --i) {
            if (cnt[i] % 2) {
                mid += (i + '0');
                --cnt[i];
                break;
            }
        }
        string t = "";
        for (int i = 0; i < 10; ++i) {
            if (cnt[i]) {
                cnt[i] >>= 1;
                while (cnt[i]--) {
                    t += (i + '0');
                }
            }
        }
        while (t.size() && t.back() == '0') {
            t.pop_back();
        }
        string ans = t;
        reverse(ans.begin(), ans.end());
        ans += mid + t;
        return ans == "" ? "0" : ans;
    }
};
```

### **Go**

```go
func largestPalindromic(num string) string {
	cnt := make([]int, 10)
	for _, c := range num {
		cnt[c-'0']++
	}
	ans := ""
	for i := 9; i >= 0; i-- {
		if cnt[i]%2 == 1 {
			ans = strconv.Itoa(i)
			cnt[i]--
			break
		}
	}
	for i := 0; i < 10; i++ {
		if cnt[i] > 0 {
			cnt[i] >>= 1
			s := strings.Repeat(strconv.Itoa(i), cnt[i])
			ans = s + ans + s
		}
	}
	ans = strings.Trim(ans, "0")
	if ans == "" {
		return "0"
	}
	return ans
}
```

### **TypeScript**

```ts
function largestPalindromic(num: string): string {
    const count = new Array(10).fill(0);
    for (const c of num) {
        count[c]++;
    }
    while (count.reduce((r, v) => (v % 2 === 1 ? r + 1 : r), 0) > 1) {
        for (let i = 0; i < 10; i++) {
            if (count[i] % 2 === 1) {
                count[i]--;
                break;
            }
        }
    }

    let res = [];
    let oddIndex = -1;
    for (let i = 9; i >= 0; i--) {
        if (count[i] % 2 == 1) {
            oddIndex = i;
            count[i] -= 1;
        }
        res.push(...new Array(count[i] >> 1).fill(i));
    }
    if (oddIndex !== -1) {
        res.push(oddIndex);
    }
    const n = res.length;
    for (let i = 0; i < n; i++) {
        if (res[i] !== 0) {
            res = res.slice(i);
            if (oddIndex !== -1) {
                res.push(...[...res.slice(0, res.length - 1)].reverse());
            } else {
                res.push(...[...res].reverse());
            }
            return res.join('');
        }
    }

    return '0';
}
```

### **...**

```


```

<!-- tabs:end -->
