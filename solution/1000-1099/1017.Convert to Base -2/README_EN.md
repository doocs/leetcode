# [1017. Convert to Base -2](https://leetcode.com/problems/convert-to-base-2)

[中文文档](/solution/1000-1099/1017.Convert%20to%20Base%20-2/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>a binary string representing its representation in base</em> <code>-2</code>.</p>

<p><strong>Note</strong> that the returned string should not have leading zeros unless the string is <code>&quot;0&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> &quot;110&quot;
<strong>Explantion:</strong> (-2)<sup>2</sup> + (-2)<sup>1</sup> = 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> &quot;111&quot;
<strong>Explantion:</strong> (-2)<sup>2</sup> + (-2)<sup>1</sup> + (-2)<sup>0</sup> = 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> &quot;100&quot;
<strong>Explantion:</strong> (-2)<sup>2</sup> = 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def baseNeg2(self, n: int) -> str:
        k = 1
        ans = []
        while n:
            if n % 2:
                ans.append('1')
                n -= k
            else:
                ans.append('0')
            n //= 2
            k *= -1
        return ''.join(ans[::-1]) or '0'
```

### **Java**

```java
class Solution {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        int k = 1;
        StringBuilder ans = new StringBuilder();
        while (n != 0) {
            if (n % 2 != 0) {
                ans.append(1);
                n -= k;
            } else {
                ans.append(0);
            }
            k *= -1;
            n /= 2;
        }
        return ans.reverse().toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        int k = 1;
        string ans;
        while (n) {
            if (n % 2) {
                ans.push_back('1');
                n -= k;
            } else {
                ans.push_back('0');
            }
            k *= -1;
            n /= 2;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

### **Go**

```go
func baseNeg2(n int) string {
	if n == 0 {
		return "0"
	}
	ans := []byte{}
	k := 1
	for n != 0 {
		if n%2 != 0 {
			ans = append(ans, '1')
			n -= k
		} else {
			ans = append(ans, '0')
		}
		k *= -1
		n /= 2
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function baseNeg2(n: number): string {
    if (n === 0) {
        return '0';
    }
    let k = 1;
    const ans: string[] = [];
    while (n) {
        if (n % 2) {
            ans.push('1');
            n -= k;
        } else {
            ans.push('0');
        }
        k *= -1;
        n /= 2;
    }
    return ans.reverse().join('');
}
```

### **...**

```

```

<!-- tabs:end -->
