# [2379. 得到 K 个黑块的最少涂色次数](https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks)

[English Version](/solution/2300-2399/2379.Minimum%20Recolors%20to%20Get%20K%20Consecutive%20Black%20Blocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>blocks</code>&nbsp;，<code>blocks[i]</code>&nbsp;要么是&nbsp;<code>'W'</code>&nbsp;要么是&nbsp;<code>'B'</code>&nbsp;，表示第&nbsp;<code>i</code>&nbsp;块的颜色。字符&nbsp;<code>'W'</code> 和&nbsp;<code>'B'</code>&nbsp;分别表示白色和黑色。</p>

<p>给你一个整数&nbsp;<code>k</code>&nbsp;，表示想要&nbsp;<strong>连续</strong>&nbsp;黑色块的数目。</p>

<p>每一次操作中，你可以选择一个白色块将它 <strong>涂成</strong>&nbsp;黑色块。</p>

<p>请你返回至少出现 <strong>一次</strong>&nbsp;连续 <code>k</code>&nbsp;个黑色块的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>blocks = "WBBWWBBWBW", k = 7
<b>输出：</b>3
<strong>解释：</strong>
一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
得到 blocks = "BBBBBBBWBW" 。
可以证明无法用少于 3 次操作得到 7 个连续的黑块。
所以我们返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>blocks = "WBWBBBW", k = 2
<b>输出：</b>0
<strong>解释：</strong>
不需要任何操作，因为已经有 2 个连续的黑块。
所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == blocks.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>blocks[i]</code>&nbsp;要么是&nbsp;<code>'W'</code>&nbsp;，要么是&nbsp;<code>'B'</code> 。</li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：滑动窗口**

我们观察发现，题目实际上求的是一个 $k$ 大小的滑动窗口中白色块的最小数量。

因此，我们只需要遍历字符串 $blocks$，用一个变量 $cnt$ 统计当前窗口中白色块的数量，然后用一个变量 $ans$ 维护最小值即可。

遍历结束后即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $blocks$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumRecolors(self, blocks: str, k: int) -> int:
        ans = cnt = blocks[:k].count('W')
        for i in range(k, len(blocks)):
            cnt += blocks[i] == 'W'
            cnt -= blocks[i - k] == 'W'
            ans = min(ans, cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
        }
        int ans = cnt;
        for (int i = k; i < blocks.length(); ++i) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
            cnt -= blocks.charAt(i - k) == 'W' ? 1 : 0;
            ans = Math.min(ans, cnt);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumRecolors(string blocks, int k) {
        int cnt = count(blocks.begin(), blocks.begin() + k, 'W');
        int ans = cnt;
        for (int i = k; i < blocks.size(); ++i) {
            cnt += blocks[i] == 'W';
            cnt -= blocks[i - k] == 'W';
            ans = min(ans, cnt);
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumRecolors(blocks string, k int) int {
	cnt := strings.Count(blocks[:k], "W")
	ans := cnt
	for i := k; i < len(blocks); i++ {
		if blocks[i] == 'W' {
			cnt++
		}
		if blocks[i-k] == 'W' {
			cnt--
		}
		if ans > cnt {
			ans = cnt
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumRecolors(blocks: string, k: number): number {
    let cnt = 0;
    for (let i = 0; i < k; ++i) {
        cnt += blocks[i] === 'W' ? 1 : 0;
    }
    let ans = cnt;
    for (let i = k; i < blocks.length; ++i) {
        cnt += blocks[i] === 'W' ? 1 : 0;
        cnt -= blocks[i - k] === 'W' ? 1 : 0;
        ans = Math.min(ans, cnt);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimum_recolors(blocks: String, k: i32) -> i32 {
        let k = k as usize;
        let s = blocks.as_bytes();
        let n = s.len();
        let mut count = 0;
        for i in 0..k {
            if s[i] == b'B' {
                count += 1;
            }
        }
        let mut ans = k - count;
        for i in k..n {
            if s[i - k] == b'B' {
                count -= 1;
            }
            if s[i] == b'B' {
                count += 1;
            }
            ans = ans.min(k - count);
        }
        ans as i32
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

int minimumRecolors(char *blocks, int k) {
    int n = strlen(blocks);
    int count = 0;
    for (int i = 0; i < k; i++) {
        count += blocks[i] == 'B';
    }
    int ans = k - count;
    for (int i = k; i < n; i++) {
        count -= blocks[i - k] == 'B';
        count += blocks[i] == 'B';
        ans = min(ans, k - count);
    }
    return ans;
}
```

### **...**

```


```

<!-- tabs:end -->
