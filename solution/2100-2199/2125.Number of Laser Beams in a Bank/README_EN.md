---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2125.Number%20of%20Laser%20Beams%20in%20a%20Bank/README_EN.md
rating: 1280
source: Weekly Contest 274 Q2
tags:
    - Array
    - Math
    - String
    - Matrix
---

<!-- problem:start -->

# [2125. Number of Laser Beams in a Bank](https://leetcode.com/problems/number-of-laser-beams-in-a-bank)

[中文文档](/solution/2100-2199/2125.Number%20of%20Laser%20Beams%20in%20a%20Bank/README.md)

## Description

<!-- description:start -->

<p>Anti-theft security devices are activated inside a bank. You are given a <strong>0-indexed</strong> binary string array <code>bank</code> representing the floor plan of the bank, which is an <code>m x n</code> 2D matrix. <code>bank[i]</code> represents the <code>i<sup>th</sup></code> row, consisting of <code>&#39;0&#39;</code>s and <code>&#39;1&#39;</code>s. <code>&#39;0&#39;</code> means the cell is empty, while<code>&#39;1&#39;</code> means the cell has a security device.</p>

<p>There is <strong>one</strong> laser beam between any <strong>two</strong> security devices <strong>if both</strong> conditions are met:</p>

<ul>
	<li>The two devices are located on two <strong>different rows</strong>: <code>r<sub>1</sub></code> and <code>r<sub>2</sub></code>, where <code>r<sub>1</sub> &lt; r<sub>2</sub></code>.</li>
	<li>For <strong>each</strong> row <code>i</code> where <code>r<sub>1</sub> &lt; i &lt; r<sub>2</sub></code>, there are <strong>no security devices</strong> in the <code>i<sup>th</sup></code> row.</li>
</ul>

<p>Laser beams are independent, i.e., one beam does not interfere nor join with another.</p>

<p>Return <em>the total number of laser beams in the bank</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2125.Number%20of%20Laser%20Beams%20in%20a%20Bank/images/laser1.jpg" style="width: 400px; height: 368px;" />
<pre>
<strong>Input:</strong> bank = [&quot;011001&quot;,&quot;000000&quot;,&quot;010100&quot;,&quot;001000&quot;]
<strong>Output:</strong> 8
<strong>Explanation:</strong> Between each of the following device pairs, there is one beam. In total, there are 8 beams:
 * bank[0][1] -- bank[2][1]
 * bank[0][1] -- bank[2][3]
 * bank[0][2] -- bank[2][1]
 * bank[0][2] -- bank[2][3]
 * bank[0][5] -- bank[2][1]
 * bank[0][5] -- bank[2][3]
 * bank[2][1] -- bank[3][2]
 * bank[2][3] -- bank[3][2]
Note that there is no beam between any device on the 0<sup>th</sup> row with any on the 3<sup>rd</sup> row.
This is because the 2<sup>nd</sup> row contains security devices, which breaks the second condition.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2125.Number%20of%20Laser%20Beams%20in%20a%20Bank/images/laser2.jpg" style="width: 244px; height: 325px;" />
<pre>
<strong>Input:</strong> bank = [&quot;000&quot;,&quot;111&quot;,&quot;000&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There does not exist two devices located on two different rows.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == bank.length</code></li>
	<li><code>n == bank[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>bank[i][j]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Row by Row Counting

We can count the number of safety devices row by row. If the current row does not have any safety devices, we skip it. Otherwise, we multiply the number of safety devices in the current row by the number of safety devices in the previous row, and add it to the answer. Then we update the number of safety devices in the previous row to be the number of safety devices in the current row.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfBeams(self, bank: List[str]) -> int:
        ans = pre = 0
        for row in bank:
            if (cur := row.count("1")) > 0:
                ans += pre * cur
                pre = cur
        return ans
```

```java
class Solution {
    public int numberOfBeams(String[] bank) {
        int ans = 0, pre = 0;
        for (String row : bank) {
            int cur = 0;
            for (int i = 0; i < row.length(); ++i) {
                cur += row.charAt(i) - '0';
            }
            if (cur > 0) {
                ans += pre * cur;
                pre = cur;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfBeams(vector<string>& bank) {
        int ans = 0, pre = 0;
        for (auto& row : bank) {
            int cur = count(row.begin(), row.end(), '1');
            if (cur) {
                ans += pre * cur;
                pre = cur;
            }
        }
        return ans;
    }
};
```

```go
func numberOfBeams(bank []string) (ans int) {
	pre := 0
	for _, row := range bank {
		if cur := strings.Count(row, "1"); cur > 0 {
			ans += pre * cur
			pre = cur
		}
	}
	return
}
```

```ts
function numberOfBeams(bank: string[]): number {
    let [ans, pre] = [0, 0];
    for (const row of bank) {
        const cur = row.split('1').length - 1;
        if (cur) {
            ans += pre * cur;
            pre = cur;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn number_of_beams(bank: Vec<String>) -> i32 {
        let mut ans = 0;
        let mut pre = 0;
        for row in bank {
            let cur = row
                .chars()
                .filter(|&c| c == '1')
                .count() as i32;
            if cur > 0 {
                ans += pre * cur;
                pre = cur;
            }
        }
        ans
    }
}
```

```c
int numberOfBeams(char** bank, int bankSize) {
    int ans = 0, pre = 0;
    for (int i = 0; i < bankSize; ++i) {
        int cur = 0;
        for (int j = 0; bank[i][j] != '\0'; ++j) {
            if (bank[i][j] == '1') {
                cur++;
            }
        }
        if (cur) {
            ans += pre * cur;
            pre = cur;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
