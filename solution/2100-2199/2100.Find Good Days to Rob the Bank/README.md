# [2100. 适合打劫银行的日子](https://leetcode.cn/problems/find-good-days-to-rob-the-bank)

[English Version](/solution/2100-2199/2100.Find%20Good%20Days%20to%20Rob%20the%20Bank/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你和一群强盗准备打劫银行。给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>security</code>&nbsp;，其中&nbsp;<code>security[i]</code>&nbsp;是第 <code>i</code>&nbsp;天执勤警卫的数量。日子从 <code>0</code>&nbsp;开始编号。同时给你一个整数&nbsp;<code>time</code>&nbsp;。</p>

<p>如果第 <code>i</code>&nbsp;天满足以下所有条件，我们称它为一个适合打劫银行的日子：</p>

<ul>
	<li>第 <code>i</code>&nbsp;天前和后都分别至少有 <code>time</code>&nbsp;天。</li>
	<li>第 <code>i</code>&nbsp;天前连续 <code>time</code>&nbsp;天警卫数目都是非递增的。</li>
	<li>第 <code>i</code>&nbsp;天后连续 <code>time</code>&nbsp;天警卫数目都是非递减的。</li>
</ul>

<p>更正式的，第 <code>i</code> 天是一个合适打劫银行的日子当且仅当：<code>security[i - time] &gt;= security[i - time + 1] &gt;= ... &gt;= security[i] &lt;= ... &lt;= security[i + time - 1] &lt;= security[i + time]</code>.</p>

<p>请你返回一个数组，包含 <strong>所有</strong> 适合打劫银行的日子（下标从 <strong>0</strong>&nbsp;开始）。返回的日子可以 <strong>任意</strong>&nbsp;顺序排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>security = [5,3,3,3,5,6,2], time = 2
<b>输出：</b>[2,3]
<strong>解释：</strong>
第 2 天，我们有 security[0] &gt;= security[1] &gt;= security[2] &lt;= security[3] &lt;= security[4] 。
第 3 天，我们有 security[1] &gt;= security[2] &gt;= security[3] &lt;= security[4] &lt;= security[5] 。
没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>security = [1,1,1,1,1], time = 0
<b>输出：</b>[0,1,2,3,4]
<strong>解释：</strong>
因为 time 等于 0 ，所以每一天都是适合打劫银行的日子，所以返回每一天。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>security = [1,2,3,4,5,6], time = 2
<b>输出：</b>[]
<strong>解释：</strong>
没有任何一天的前 2 天警卫数目是非递增的。
所以没有适合打劫银行的日子，返回空数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= security.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= security[i], time &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

left, right 分别记录左右符合要求的天数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def goodDaysToRobBank(self, security: List[int], time: int) -> List[int]:
        n = len(security)
        if n <= time * 2:
            return []
        left, right = [0] * n, [0] * n
        for i in range(1, n):
            if security[i] <= security[i - 1]:
                left[i] = left[i - 1] + 1
        for i in range(n - 2, -1, -1):
            if security[i] <= security[i + 1]:
                right[i] = right[i + 1] + 1
        return [i for i in range(n) if time <= min(left[i], right[i])]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        if (n <= time * 2) {
            return Collections.emptyList();
        }
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; ++i) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (security[i] <= security[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = time; i < n - time; ++i) {
            if (time <= Math.min(left[i], right[i])) {
                ans.add(i);
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
    vector<int> goodDaysToRobBank(vector<int>& security, int time) {
        int n = security.size();
        if (n <= time * 2) return {};
        vector<int> left(n);
        vector<int> right(n);
        for (int i = 1; i < n; ++i)
            if (security[i] <= security[i - 1])
                left[i] = left[i - 1] + 1;
        for (int i = n - 2; i >= 0; --i)
            if (security[i] <= security[i + 1])
                right[i] = right[i + 1] + 1;
        vector<int> ans;
        for (int i = time; i < n - time; ++i)
            if (time <= min(left[i], right[i]))
                ans.push_back(i);
        return ans;
    }
};
```

### **Go**

```go
func goodDaysToRobBank(security []int, time int) []int {
	n := len(security)
	if n <= time*2 {
		return []int{}
	}
	left := make([]int, n)
	right := make([]int, n)
	for i := 1; i < n; i++ {
		if security[i] <= security[i-1] {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 2; i >= 0; i-- {
		if security[i] <= security[i+1] {
			right[i] = right[i+1] + 1
		}
	}
	var ans []int
	for i := time; i < n - time; i++ {
		if time <= left[i] && time <= right[i] {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function goodDaysToRobBank(security: number[], time: number): number[] {
    const n = security.length;
    if (n <= time * 2) {
        return [];
    }
    const l = new Array(n).fill(0);
    const r = new Array(n).fill(0);
    for (let i = 1; i < n; i++) {
        if (security[i] <= security[i - 1]) {
            l[i] = l[i - 1] + 1;
        }
        if (security[n - i - 1] <= security[n - i]) {
            r[n - i - 1] = r[n - i] + 1;
        }
    }
    const res = [];
    for (let i = time; i < n - time; i++) {
        if (time <= Math.min(l[i], r[i])) {
            res.push(i);
        }
    }
    return res;
}
```

### **Rust**

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn good_days_to_rob_bank(security: Vec<i32>, time: i32) -> Vec<i32> {
        let time = time as usize;
        let n = security.len();
        if time * 2 >= n {
            return vec![];
        }
        let mut g = vec![0; n];
        for i in 1..n {
            g[i] = match security[i].cmp(&security[i - 1]) {
                Ordering::Less => -1,
                Ordering::Greater => 1,
                Ordering::Equal => 0,
            }
        }
        let (mut a, mut b) = (vec![0; n + 1], vec![0; n + 1]);
        for i in 1..=n {
            a[i] = a[i - 1] + if g[i - 1] == 1 { 1 } else { 0 };
            b[i] = b[i - 1] + if g[i - 1] == -1 { 1 } else { 0 };
        }
        let mut res = vec![];
        for i in time..n - time {
            if a[i + 1] - a[i + 1 - time] == 0 && b[i + 1 + time] - b[i + 1] == 0 {
                res.push((i) as i32);
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
