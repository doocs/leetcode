# [2217. Find Palindrome With Fixed Length](https://leetcode.com/problems/find-palindrome-with-fixed-length)

[中文文档](/solution/2200-2299/2217.Find%20Palindrome%20With%20Fixed%20Length/README.md)

## Description

<p>Given an integer array <code>queries</code> and a <strong>positive</strong> integer <code>intLength</code>, return <em>an array</em> <code>answer</code> <em>where</em> <code>answer[i]</code> <em>is either the </em><code>queries[i]<sup>th</sup></code> <em>smallest <strong>positive palindrome</strong> of length</em> <code>intLength</code> <em>or</em> <code>-1</code><em> if no such palindrome exists</em>.</p>

<p>A <strong>palindrome</strong> is a number that reads the same backwards and forwards. Palindromes cannot have leading zeros.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> queries = [1,2,3,4,5,90], intLength = 3
<strong>Output:</strong> [101,111,121,131,141,999]
<strong>Explanation:</strong>
The first few palindromes of length 3 are:
101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 201, ...
The 90<sup>th</sup> palindrome of length 3 is 999.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> queries = [2,4,6], intLength = 4
<strong>Output:</strong> [1111,1331,1551]
<strong>Explanation:</strong>
The first six palindromes of length 4 are:
1001, 1111, 1221, 1331, 1441, and 1551.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= intLength&nbsp;&lt;= 15</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

<!-- tabs:end -->
<!-- tabs:end -->
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
