# [2802. Find The K-th Lucky Number](https://leetcode.com/problems/find-the-k-th-lucky-number)

[中文文档](/solution/2800-2899/2802.Find%20The%20K-th%20Lucky%20Number/README.md)

## Description

<p>We know that <code>4</code> and <code>7</code> are <strong>lucky</strong> digits. Also, a number is called <strong>lucky</strong>&nbsp;if it contains <strong>only</strong> lucky digits.</p>

<p>You are given an integer <code>k</code>, return<em> the </em><code>k<sup>th</sup></code><em>&nbsp;lucky number represented as a <strong>string</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 4
<strong>Output:</strong> &quot;47&quot;
<strong>Explanation:</strong> The first lucky number is 4, the second one is 7, the third one is 44 and the fourth one is 47.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 10
<strong>Output:</strong> &quot;477&quot;
<strong>Explanation:</strong> Here are lucky numbers sorted in increasing order:
4, 7, 44, 47, 74, 77, 444, 447, 474, 477. So the 10<sup>th</sup> lucky number is 477.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 1000
<strong>Output:</strong> &quot;777747447&quot;
<strong>Explanation:</strong> It can be shown that the 1000<sup>th</sup> lucky number is 777747447.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

**Solution 1: Mathematics**

According to the problem description, a lucky number only contains the digits $4$ and $7$, so the number of $n$-digit lucky numbers is $2^n$.

We initialize $n=1$, then loop to check whether $k$ is greater than $2^n$. If it is, we subtract $2^n$ from $k$ and increment $n$, until $k$ is less than or equal to $2^n$. At this point, we just need to find the $k$-th lucky number among the $n$-digit lucky numbers.

If $k$ is less than or equal to $2^{n-1}$, then the first digit of the $k$-th lucky number is $4$, otherwise the first digit is $7$. Then we subtract $2^{n-1}$ from $k$ and continue to determine the second digit, until all digits of the $n$-digit lucky number are determined.

The time complexity is $O(\log k)$, and the space complexity is $O(\log k)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kthLuckyNumber(self, k: int) -> str:
        n = 1
        while k > 1 << n:
            k -= 1 << n
            n += 1
        ans = []
        while n:
            n -= 1
            if k <= 1 << n:
                ans.append("4")
            else:
                ans.append("7")
                k -= 1 << n
        return "".join(ans)
```

### **Java**

```java
class Solution {
    public String kthLuckyNumber(int k) {
        int n = 1;
        while (k > 1 << n) {
            k -= 1 << n;
            ++n;
        }
        StringBuilder ans = new StringBuilder();
        while (n-- > 0) {
            if (k <= 1 << n) {
                ans.append('4');
            } else {
                ans.append('7');
                k -= 1 << n;
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string kthLuckyNumber(int k) {
        int n = 1;
        while (k > 1 << n) {
            k -= 1 << n;
            ++n;
        }
        string ans;
        while (n--) {
            if (k <= 1 << n) {
                ans.push_back('4');
            } else {
                ans.push_back('7');
                k -= 1 << n;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func kthLuckyNumber(k int) string {
	n := 1
	for k > 1<<n {
		k -= 1 << n
		n++
	}
	ans := []byte{}
	for n > 0 {
		n--
		if k <= 1<<n {
			ans = append(ans, '4')
		} else {
			ans = append(ans, '7')
			k -= 1 << n
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function kthLuckyNumber(k: number): string {
    let n = 1;
    while (k > 1 << n) {
        k -= 1 << n;
        ++n;
    }
    const ans: string[] = [];
    while (n-- > 0) {
        if (k <= 1 << n) {
            ans.push('4');
        } else {
            ans.push('7');
            k -= 1 << n;
        }
    }
    return ans.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
