# [1447. 最简分数](https://leetcode.cn/problems/simplified-fractions)

[English Version](/solution/1400-1499/1447.Simplified%20Fractions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于&nbsp;&nbsp;<code>n</code>&nbsp;的 <strong>最简&nbsp;</strong>分数&nbsp;。分数可以以 <strong>任意&nbsp;</strong>顺序返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>[&quot;1/2&quot;]
<strong>解释：</strong>&quot;1/2&quot; 是唯一一个分母小于等于 2 的最简分数。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>[&quot;1/2&quot;,&quot;1/3&quot;,&quot;2/3&quot;]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 4
<strong>输出：</strong>[&quot;1/2&quot;,&quot;1/3&quot;,&quot;1/4&quot;,&quot;2/3&quot;,&quot;3/4&quot;]
<strong>解释：</strong>&quot;2/4&quot; 不是最简分数，因为它可以化简为 &quot;1/2&quot; 。</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def simplifiedFractions(self, n: int) -> List[str]:
        return [
            f'{i}/{j}'
            for i in range(1, n)
            for j in range(i + 1, n + 1)
            if gcd(i, j) == 1
        ]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            for (int j = i + 1; j < n + 1; ++j) {
                if (gcd(i, j) == 1) {
                    ans.add(i + "/" + j);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
```

### **TypeScript**

```ts
function simplifiedFractions(n: number): string[] {
    let ans: Array<string> = [];
    for (let j = 2; j <= n; j++) {
        for (let i = 1; i < j; i++) {
            if (gcd(i, j) == 1) {
                ans.push(`${i}/${j}`);
            }
        }
    }
    return ans;
}

// a < b
function gcd(a: number, b: number): number {
    if (a > b) [a, b] = [b, a];
    while (a) {
        [a, b] = [b % a, a];
    }
    return b;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> simplifiedFractions(int n) {
        vector<string> ans;
        for (int i = 1; i < n; ++i)
            for (int j = i + 1; j < n + 1; ++j)
                if (gcd(i, j) == 1)
                    ans.push_back(to_string(i) + "/" + to_string(j));
        return ans;
    }
};
```

### **Go**

```go
func simplifiedFractions(n int) []string {
	var ans []string
	for i := 1; i < n; i++ {
		for j := i + 1; j < n+1; j++ {
			if gcd(i, j) == 1 {
				ans = append(ans, strconv.Itoa(i)+"/"+strconv.Itoa(j))
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b <= 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **Rust**

```rust
impl Solution {
    fn gcd(a: i32, b: i32) -> i32 {
        match b {
            0 => a,
            _ => Solution::gcd(b, a % b),
        }
    }

    pub fn simplified_fractions(n: i32) -> Vec<String> {
        let mut res = vec![];
        for i in 1..n {
            for j in i + 1..=n {
                if Solution::gcd(i, j) == 1 {
                    res.push(format!("{}/{}", i, j));
                }
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
