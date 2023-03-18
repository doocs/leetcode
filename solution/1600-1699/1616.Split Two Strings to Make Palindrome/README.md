# [1616. 分割两个字符串得到回文串](https://leetcode.cn/problems/split-two-strings-to-make-palindrome)

[English Version](/solution/1600-1699/1616.Split%20Two%20Strings%20to%20Make%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>a</code> 和&nbsp;<code>b</code>&nbsp;，它们长度相同。请你选择一个下标，将两个字符串都在&nbsp;<strong>相同的下标 </strong>分割开。由&nbsp;<code>a</code>&nbsp;可以得到两个字符串：&nbsp;<code>a<sub>prefix</sub></code>&nbsp;和&nbsp;<code>a<sub>suffix</sub></code>&nbsp;，满足&nbsp;<code>a = a<sub>prefix</sub> + a<sub>suffix</sub></code><sub>&nbsp;</sub>，同理，由&nbsp;<code>b</code> 可以得到两个字符串&nbsp;<code>b<sub>prefix</sub></code> 和&nbsp;<code>b<sub>suffix</sub></code>&nbsp;，满足&nbsp;<code>b = b<sub>prefix</sub> + b<sub>suffix</sub></code>&nbsp;。请你判断&nbsp;<code>a<sub>prefix</sub> + b<sub>suffix</sub></code> 或者&nbsp;<code>b<sub>prefix</sub> + a<sub>suffix</sub></code>&nbsp;能否构成回文串。</p>

<p>当你将一个字符串&nbsp;<code>s</code>&nbsp;分割成&nbsp;<code>s<sub>prefix</sub></code> 和&nbsp;<code>s<sub>suffix</sub></code>&nbsp;时，&nbsp;<code>s<sub>suffix</sub></code> 或者&nbsp;<code>s<sub>prefix</sub></code> 可以为空。比方说，&nbsp;<code>s = "abc"</code>&nbsp;那么&nbsp;<code>"" + "abc"</code>&nbsp;，&nbsp;<code>"a" + "bc"&nbsp;</code>，&nbsp;<code>"ab" + "c"</code>&nbsp;和&nbsp;<code>"abc" + ""</code>&nbsp;都是合法分割。</p>

<p>如果 <strong>能构成回文字符串</strong> ，那么请返回&nbsp;<code>true</code>，否则返回<em>&nbsp;</em><code>false</code>&nbsp;。</p>

<p><strong>注意</strong>，&nbsp;<code>x + y</code>&nbsp;表示连接字符串&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>a = "x", b = "y"
<b>输出：</b>true
<b>解释：</b>如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
a<sub>prefix</sub> = "", a<sub>suffix</sub> = "x"
b<sub>prefix</sub> = "", b<sub>suffix</sub> = "y"
那么 a<sub>prefix</sub> + b<sub>suffix</sub> = "" + "y" = "y" 是回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = "abdef", b = "fecab"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>a = "ulacfd", b = "jizalu"
<b>输出：</b>true
<b>解释：</b>在下标为 3 处分割：
a<sub>prefix</sub> = "ula", a<sub>suffix</sub> = "cfd"
b<sub>prefix</sub> = "jiz", b<sub>suffix</sub> = "alu"
那么 a<sub>prefix</sub> + b<sub>suffix</sub> = "ula" + "alu" = "ulaalu" 是回文串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a.length == b.length</code></li>
	<li><code>a</code> 和&nbsp;<code>b</code>&nbsp;都只包含小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们可以使用双指针，其中一个指针 $i$ 从字符串 $a$ 的头部开始，另一个指针 $j$ 从字符串 $b$ 的尾部开始，如果两个指针指向的字符相等，那么两个指针同时往中间移动，直到遇到不同的字符或两指针交叉。

如果两指针交叉，即 $i \geq j$，说明 $prefix$ 和 $suffix$ 已经可以得到回文串，返回 `true`；否则，我们还需要判断 $a[i,...j]$ 或者 $b[i,...j]$ 是否是回文串，若是，返回 `true`。

否则，我们尝试交换两个字符串 $a$ 和 $b$，重复上述同样的过程。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是字符串 $a$ 或 $b$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkPalindromeFormation(self, a: str, b: str) -> bool:
        def check1(a: str, b: str) -> bool:
            i, j = 0, len(b) - 1
            while i < j and a[i] == b[j]:
                i, j = i + 1, j - 1
            return i >= j or check2(a, i, j) or check2(b, i, j)

        def check2(a: str, i: int, j: int) -> bool:
            return a[i: j + 1] == a[i: j + 1][::-1]

        return check1(a, b) or check1(b, a)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
        return check1(a, b) || check1(b, a);
    }

    private boolean check1(String a, String b) {
        int i = 0;
        int j = b.length() - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    }

    private boolean check2(String a, int i, int j) {
        while (i < j && a.charAt(i) == a.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkPalindromeFormation(string a, string b) {
        return check1(a, b) || check1(b, a);
    }

private:
    bool check1(string &a, string &b) {
        int i = 0, j = b.size() - 1;
        while (i < j && a[i] == b[j]) {
            ++i;
            --j;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    }

    bool check2(string &a, int i, int j) {
        while (i <= j && a[i] == a[j]) {
            ++i;
            --j;
        }
        return i >= j;
    }
};
```

### **Go**

```go
func checkPalindromeFormation(a string, b string) bool {
	return check1(a, b) || check1(b, a)
}

func check1(a, b string) bool {
	i, j := 0, len(b)-1
	for i < j && a[i] == b[j] {
		i++
		j--
	}
	return i >= j || check2(a, i, j) || check2(b, i, j)
}

func check2(a string, i, j int) bool {
	for i < j && a[i] == a[j] {
		i++
		j--
	}
	return i >= j
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_palindrome_formation(a: String, b: String) -> bool {
        fn check1(a: &[u8], b: &[u8]) -> bool {
            let (mut i, mut j) = (0, b.len() - 1);
            while i < j && a[i] == b[j] {
                i += 1;
                j -= 1;
            }
            if i >= j {
                return true;
            }
            check2(a, i, j) || check2(b, i, j)
        }

        fn check2(a: &[u8], mut i: usize, mut j: usize) -> bool {
            while i < j && a[i] == a[j] {
                i += 1;
                j -= 1;
            }
            i >= j
        }

        let a = a.as_bytes();
        let b = b.as_bytes();
        check1(a, b) || check1(b, a)
    }
}
```

### **TypeScript**

```ts
function checkPalindromeFormation(a: string, b: string): boolean {
    const check1 = (a: string, b: string) => {
        let i = 0;
        let j = b.length - 1;
        while (i < j && a.charAt(i) === b.charAt(j)) {
            i++;
            j--;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    };

    const check2 = (a: string, i: number, j: number) => {
        while (i < j && a.charAt(i) === a.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    };
    return check1(a, b) || check1(b, a);
}
```

### **...**

```

```

<!-- tabs:end -->
