# [2217. 找到指定长度的回文数](https://leetcode.cn/problems/find-palindrome-with-fixed-length)

[English Version](/solution/2200-2299/2217.Find%20Palindrome%20With%20Fixed%20Length/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>queries</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>intLength</code>&nbsp;，请你返回一个数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code> 是长度为&nbsp;<code>intLength</code>&nbsp;的&nbsp;<strong>正回文数</strong> 中第<em>&nbsp;</em><code>queries[i]</code>&nbsp;小的数字，如果不存在这样的回文数，则为 <code>-1</code>&nbsp;。</p>

<p><strong>回文数</strong> 指的是从前往后和从后往前读一模一样的数字。回文数不能有前导 0 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>queries = [1,2,3,4,5,90], intLength = 3
<b>输出：</b>[101,111,121,131,141,999]
<strong>解释：</strong>
长度为 3 的最小回文数依次是：
101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
第 90 个长度为 3 的回文数是 999 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>queries = [2,4,6], intLength = 4
<b>输出：</b>[1111,1331,1551]
<strong>解释：</strong>
长度为 4 的前 6 个回文数是：
1001, 1111, 1221, 1331, 1441 和 1551 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= intLength&nbsp;&lt;= 15</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kthPalindrome(self, queries: List[int], intLength: int) -> List[int]:
        l = (intLength + 1) >> 1
        start, end = 10 ** (l - 1), 10**l - 1
        ans = []
        for q in queries:
            v = start + q - 1
            if v > end:
                ans.append(-1)
                continue
            s = str(v)
            s += s[::-1][intLength % 2 :]
            ans.append(int(s))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long[] kthPalindrome(int[] queries, int intLength) {
        int n = queries.length;
        long[] ans = new long[n];
        int l = (intLength + 1) >> 1;
        long start = (long) Math.pow(10, l - 1);
        long end = (long) Math.pow(10, l) - 1;
        for (int i = 0; i < n; ++i) {
            long v = start + queries[i] - 1;
            if (v > end) {
                ans[i] = -1;
                continue;
            }
            String s = "" + v;
            s += new StringBuilder(s).reverse().substring(intLength % 2);
            ans[i] = Long.parseLong(s);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<long long> kthPalindrome(vector<int>& queries, int intLength) {
        int l = (intLength + 1) >> 1;
        long long start = pow(10, l - 1), end = pow(10, l) - 1;
        vector<long long> ans;
        for (int& q : queries) {
            long long v = start + q - 1;
            if (v > end) {
                ans.push_back(-1);
                continue;
            }
            string s = to_string(v);
            string s1 = s;
            reverse(s1.begin(), s1.end());
            s += s1.substr(intLength % 2);
            ans.push_back(stoll(s));
        }
        return ans;
    }
};
```

### **Go**

```go
func kthPalindrome(queries []int, intLength int) []int64 {
	l := (intLength + 1) >> 1
	start, end := int(math.Pow10(l-1)), int(math.Pow10(l))-1
	var ans []int64
	for _, q := range queries {
		v := start + q - 1
		if v > end {
			ans = append(ans, -1)
			continue
		}
		t := v
		if intLength%2 == 1 {
			t /= 10
		}
		for t > 0 {
			v = v*10 + t%10
			t /= 10
		}
		ans = append(ans, int64(v))
	}
	return ans
}
```

### **TypeScript**

```ts
function kthPalindrome(queries: number[], intLength: number): number[] {
    const isOdd = intLength % 2 === 1;
    const bestNum = 10 ** ((intLength >> 1) + (isOdd ? 1 : 0) - 1);
    const max = bestNum * 9;
    return queries.map(v => {
        if (v > max) {
            return -1;
        }
        const num = bestNum + v - 1;
        return Number(
            num +
                (num + '')
                    .split('')
                    .reverse()
                    .slice(isOdd ? 1 : 0)
                    .join(''),
        );
    });
}
```

### **Rust**

```rust
impl Solution {
    pub fn kth_palindrome(queries: Vec<i32>, int_length: i32) -> Vec<i64> {
        let is_odd = int_length & 1 == 1;
        let best_num = i32::pow(10, (int_length / 2 + if is_odd { 0 } else { -1 }) as u32);
        let max = best_num * 9;
        queries
            .iter()
            .map(|&num| {
                if num > max {
                    return -1;
                }
                let num = best_num + num - 1;
                format!(
                    "{}{}",
                    num,
                    num.to_string()
                        .chars()
                        .rev()
                        .skip(if is_odd { 1 } else { 0 })
                        .collect::<String>()
                )
                .parse()
                .unwrap()
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
