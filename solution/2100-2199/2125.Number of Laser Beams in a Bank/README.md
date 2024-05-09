# [2125. 银行中的激光束数量](https://leetcode.cn/problems/number-of-laser-beams-in-a-bank)

[English Version](/solution/2100-2199/2125.Number%20of%20Laser%20Beams%20in%20a%20Bank/README_EN.md)

<!-- tags:数组,数学,字符串,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>银行内部的防盗安全装置已经激活。给你一个下标从 <strong>0</strong> 开始的二进制字符串数组 <code>bank</code> ，表示银行的平面图，这是一个大小为 <code>m x n</code> 的二维矩阵。 <code>bank[i]</code> 表示第 <code>i</code> 行的设备分布，由若干 <code>'0'</code> 和若干 <code>'1'</code> 组成。<code>'0'</code> 表示单元格是空的，而 <code>'1'</code> 表示单元格有一个安全设备。</p>

<p>对任意两个安全设备而言，<strong>如果</strong><strong>同时</strong> 满足下面两个条件，则二者之间存在 <strong>一个</strong> 激光束：</p>

<ul>
	<li>两个设备位于两个 <strong>不同行</strong> ：<code>r<sub>1</sub></code> 和 <code>r<sub>2</sub></code> ，其中 <code>r<sub>1</sub> &lt; r<sub>2</sub></code> 。</li>
	<li>满足&nbsp;<code>r<sub>1</sub> &lt; i &lt; r<sub>2</sub></code>&nbsp;的 <strong>所有&nbsp;</strong>行&nbsp;<code>i</code>&nbsp;，都&nbsp;<strong>没有安全设备</strong> 。</li>
</ul>

<p>激光束是独立的，也就是说，一个激光束既不会干扰另一个激光束，也不会与另一个激光束合并成一束。</p>

<p>返回银行中激光束的总数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2125.Number%20of%20Laser%20Beams%20in%20a%20Bank/images/laser1.jpg" style="width: 400px; height: 368px;" /></p>

<pre>
<strong>输入：</strong>bank = ["011001","000000","010100","001000"]
<strong>输出：</strong>8
<strong>解释：</strong>在下面每组设备对之间，存在一条激光束。总共是 8 条激光束：
 * bank[0][1] -- bank[2][1]
 * bank[0][1] -- bank[2][3]
 * bank[0][2] -- bank[2][1]
 * bank[0][2] -- bank[2][3]
 * bank[0][5] -- bank[2][1]
 * bank[0][5] -- bank[2][3]
 * bank[2][1] -- bank[3][2]
 * bank[2][3] -- bank[3][2]
注意，第 0 行和第 3 行上的设备之间不存在激光束。
这是因为第 2 行存在安全设备，这不满足第 2 个条件。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2125.Number%20of%20Laser%20Beams%20in%20a%20Bank/images/laser2.jpg" style="width: 244px; height: 325px;" /></p>

<pre>
<strong>输入：</strong>bank = ["000","111","000"]
<strong>输出：</strong>0
<strong>解释：</strong>不存在两个位于不同行的设备
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == bank.length</code></li>
	<li><code>n == bank[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>bank[i][j]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

### 方法一：逐行统计

我们可以逐行统计每行的安全设备数量，如果当前行没有安全设备，直接跳过，否则我们将当前行的安全设备数量乘以前一行的安全设备数量，累加到答案中。然后更新前一行的安全设备数量为当前行的安全设备数量。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别为行数和列数。空间复杂度 $O(1)$。

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

<!-- end -->
