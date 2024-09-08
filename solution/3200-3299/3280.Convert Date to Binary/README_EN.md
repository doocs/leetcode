---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3280.Convert%20Date%20to%20Binary/README_EN.md
---

<!-- problem:start -->

# [3280. Convert Date to Binary](https://leetcode.com/problems/convert-date-to-binary)

[中文文档](/solution/3200-3299/3280.Convert%20Date%20to%20Binary/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>date</code> representing a Gregorian calendar date in the <code>yyyy-mm-dd</code> format.</p>

<p><code>date</code> can be written in its binary representation obtained by converting year, month, and day to their binary representations without any leading zeroes and writing them down in <code>year-month-day</code> format.</p>

<p>Return the <strong>binary</strong> representation of <code>date</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">date = &quot;2080-02-29&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;100000100000-10-11101&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><span class="example-io">100000100000, 10, and 11101 are the binary representations of 2080, 02, and 29 respectively.</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">date = &quot;1900-01-01&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;11101101100-1-1&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><span class="example-io">11101101100, 1, and 1 are the binary representations of 1900, 1, and 1 respectively.</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>date.length == 10</code></li>
	<li><code>date[4] == date[7] == &#39;-&#39;</code>, and all other <code>date[i]</code>&#39;s are digits.</li>
	<li>The input is generated such that <code>date</code> represents a valid Gregorian calendar date between Jan 1<sup>st</sup>, 1900 and Dec 31<sup>st</sup>, 2100 (both inclusive).</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first split the string $\textit{date}$ by `-`, then convert each part to its binary representation, and finally join these three parts with `-`.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $\textit{date}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def convertDateToBinary(self, date: str) -> str:
        return "-".join(f"{int(s):b}" for s in date.split("-"))
```

#### Java

```java
class Solution {
    public String convertDateToBinary(String date) {
        List<String> ans = new ArrayList<>();
        for (var s : date.split("-")) {
            int x = Integer.parseInt(s);
            ans.add(Integer.toBinaryString(x));
        }
        return String.join("-", ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string convertDateToBinary(string date) {
        auto bin = [](string s) -> string {
            string t = bitset<32>(stoi(s)).to_string();
            return t.substr(t.find('1'));
        };
        return bin(date.substr(0, 4)) + "-" + bin(date.substr(5, 2)) + "-" + bin(date.substr(8, 2));
    }
};
```

#### Go

```go
func convertDateToBinary(date string) string {
	ans := []string{}
	for _, s := range strings.Split(date, "-") {
		x, _ := strconv.Atoi(s)
		ans = append(ans, strconv.FormatUint(uint64(x), 2))
	}
	return strings.Join(ans, "-")
}
```

#### TypeScript

```ts
function convertDateToBinary(date: string): string {
    return date
        .split('-')
        .map(s => (+s).toString(2))
        .join('-');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
