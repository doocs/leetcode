# [2575. 找出字符串的可整除数组](https://leetcode.cn/problems/find-the-divisibility-array-of-a-string)

[English Version](/solution/2500-2599/2575.Find%20the%20Divisibility%20Array%20of%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>word</code> ，长度为 <code>n</code> ，由从 <code>0</code> 到 <code>9</code> 的数字组成。另给你一个正整数 <code>m</code> 。</p>

<p><code>word</code> 的 <strong>可整除数组</strong> <code>div</code>&nbsp; 是一个长度为 <code>n</code> 的整数数组，并满足：</p>

<ul>
	<li>如果 <code>word[0,...,i]</code> 所表示的 <strong>数值</strong> 能被 <code>m</code> 整除，<code>div[i] = 1</code></li>
	<li>否则，<code>div[i] = 0</code></li>
</ul>

<p>返回<em> </em><code>word</code> 的可整除数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "998244353", m = 3
<strong>输出：</strong>[1,1,0,0,0,1,1,0,0]
<strong>解释：</strong>仅有 4 个前缀可以被 3 整除："9"、"99"、"998244" 和 "9982443" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "1010", m = 10
<strong>输出：</strong>[0,1,0,1]
<strong>解释：</strong>仅有 2 个前缀可以被 10 整除："10" 和 "1010" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>word.length == n</code></li>
	<li><code>word</code> 由数字 <code>0</code> 到 <code>9</code> 组成</li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历 + 取模**

我们遍历字符串 `word`，用变量 $x$ 记录当前前缀与 $m$ 的取模结果，如果 $x$ 为 $0$，则当前位置的可整除数组值为 $1$，否则为 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 `word` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def divisibilityArray(self, word: str, m: int) -> List[int]:
        ans = []
        x = 0
        for c in word:
            x = (x * 10 + int(c)) % m
            ans.append(1 if x == 0 else 0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ans = new int[n];
        long x = 0;
        for (int i = 0; i < n; ++i) {
            x = (x * 10 + word.charAt(i) - '0') % m;
            if (x == 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> divisibilityArray(string word, int m) {
        vector<int> ans;
        long long x = 0;
        for (char& c : word) {
            x = (x * 10 + c - '0') % m;
            ans.push_back(x == 0 ? 1 : 0);
        }
        return ans;
    }
};
```

### **Go**

```go
func divisibilityArray(word string, m int) (ans []int) {
	x := 0
	for _, c := range word {
		x = (x*10 + int(c-'0')) % m
		if x == 0 {
			ans = append(ans, 1)
		} else {
			ans = append(ans, 0)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function divisibilityArray(word: string, m: number): number[] {
    const ans: number[] = [];
    let x = 0;
    for (const c of word) {
        x = (x * 10 + Number(c)) % m;
        ans.push(x === 0 ? 1 : 0);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn divisibility_array(word: String, m: i32) -> Vec<i32> {
        let m = m as i64;
        let mut x = 0i64;
        word.as_bytes()
            .iter()
            .map(|&c| {
                x = (x * 10 + i64::from(c - b'0')) % m;
                if x == 0 {
                    1
                } else {
                    0
                }
            })
            .collect()
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *divisibilityArray(char *word, int m, int *returnSize) {
    int n = strlen(word);
    int *ans = malloc(sizeof(int) * n);
    long long x = 0;
    for (int i = 0; i < n; i++) {
        x = (x * 10 + word[i] - '0') % m;
        ans[i] = x == 0 ? 1 : 0;
    }
    *returnSize = n;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
