# [2151. 基于陈述统计最多好人数](https://leetcode.cn/problems/maximum-good-people-based-on-statements)

[English Version](/solution/2100-2199/2151.Maximum%20Good%20People%20Based%20on%20Statements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>游戏中存在两种角色：</p>

<ul>
	<li><strong>好人</strong>：该角色只说真话。</li>
	<li><strong>坏人</strong>：该角色可能说真话，也可能说假话。</li>
</ul>

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>statements</code> ，大小为 <code>n x n</code> ，表示 <code>n</code> 个玩家对彼此角色的陈述。具体来说，<code>statements[i][j]</code> 可以是下述值之一：</p>

<ul>
	<li><code>0</code> 表示 <code>i</code> 的陈述认为 <code>j</code> 是 <strong>坏人</strong> 。</li>
	<li><code>1</code> 表示 <code>i</code> 的陈述认为 <code>j</code> 是 <strong>好人</strong> 。</li>
	<li><code>2</code> 表示 <code>i</code> 没有对 <code>j</code> 作出陈述。</li>
</ul>

<p>另外，玩家不会对自己进行陈述。形式上，对所有&nbsp;<code>0 &lt;= i &lt; n</code> ，都有 <code>statements[i][i] = 2</code> 。</p>

<p>根据这 <code>n</code> 个玩家的陈述，返回可以认为是 <strong>好人</strong> 的 <strong>最大</strong> 数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2151.Maximum%20Good%20People%20Based%20on%20Statements/images/logic1.jpg" style="width: 600px; height: 262px;">
<pre><strong>输入：</strong>statements = [[2,1,2],[1,2,2],[2,0,2]]
<strong>输出：</strong>2
<strong>解释：</strong>每个人都做一条陈述。
- 0 认为 1 是好人。
- 1 认为 0 是好人。
- 2 认为 1 是坏人。
以 2 为突破点。
- 假设 2 是一个好人：
    - 基于 2 的陈述，1 是坏人。
    - 那么可以确认 1 是坏人，2 是好人。
    - 基于 1 的陈述，由于 1 是坏人，那么他在陈述时可能：
        - 说真话。在这种情况下会出现矛盾，所以假设无效。
        - 说假话。在这种情况下，0 也是坏人并且在陈述时说假话。
    - <strong>在认为 2 是好人的情况下，这组玩家中只有一个好人。</strong>
- 假设 2 是一个坏人：
    - 基于 2 的陈述，由于 2 是坏人，那么他在陈述时可能：
        - 说真话。在这种情况下，0 和 1 都是坏人。
            - <strong>在认为 2 是坏人但说真话的情况下，这组玩家中没有一个好人。</strong>
        - 说假话。在这种情况下，1 是好人。
            - 由于 1 是好人，0 也是好人。
            - <strong>在认为 2 是坏人且说假话的情况下，这组玩家中有两个好人。</strong>
在最佳情况下，至多有两个好人，所以返回 2 。
注意，能得到此结论的方法不止一种。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2151.Maximum%20Good%20People%20Based%20on%20Statements/images/logic2.jpg" style="width: 600px; height: 262px;">
<pre><strong>输入：</strong>statements = [[2,0],[0,2]]
<strong>输出：</strong>1
<strong>解释：</strong>每个人都做一条陈述。
- 0 认为 1 是坏人。
- 1 认为 0 是坏人。
以 0 为突破点。
- 假设 0 是一个好人：
    - 基于与 0 的陈述，1 是坏人并说假话。
    - <strong>在认为 0 是好人的情况下，这组玩家中只有一个好人。</strong>
- 假设 0 是一个坏人：
    - 基于 0 的陈述，由于 0 是坏人，那么他在陈述时可能：
        - 说真话。在这种情况下，0 和 1 都是坏人。
            - <strong>在认为 0 是坏人但说真话的情况下，这组玩家中没有一个好人。</strong>
        - 说假话。在这种情况下，1 是好人。
            - <strong>在认为 0 是坏人且说假话的情况下，这组玩家中只有一个好人。</strong>
在最佳情况下，至多有一个好人，所以返回 1 。 
注意，能得到此结论的方法不止一种。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == statements.length == statements[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>statements[i][j]</code> 的值为 <code>0</code>、<code>1</code> 或 <code>2</code></li>
	<li><code>statements[i][i] == 2</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二进制枚举**

二进制枚举好人的状态 $mask$，由于“好人只说真话”，我们借此判断 $statements$ 与 $mask$ 是否存在矛盾，不存在则获取 $mask$ 中好人的数量 $cnt$。迭代获取最大的合法 $cnt$。

时间复杂度 $O(2^n*n^2)$，其中 $n$ 表示 $statements$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumGood(self, statements: List[List[int]]) -> int:
        def check(mask):
            cnt = 0
            for i, s in enumerate(statements):
                if (mask >> i) & 1:
                    for j, v in enumerate(s):
                        if v < 2 and ((mask >> j) & 1) != v:
                            return 0
                    cnt += 1
            return cnt

        return max(check(mask) for mask in range(1, 1 << len(statements)))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumGood(int[][] statements) {
        int ans = 0;
        for (int mask = 1; mask < 1 << statements.length; ++mask) {
            ans = Math.max(ans, check(mask, statements));
        }
        return ans;
    }

    private int check(int mask, int[][] statements) {
        int cnt = 0;
        int n = statements.length;
        for (int i = 0; i < n; ++i) {
            if (((mask >> i) & 1) == 1) {
                for (int j = 0; j < n; ++j) {
                    int v = statements[i][j];
                    if (v < 2 && ((mask >> j) & 1) != v) {
                        return 0;
                    }
                }
                ++cnt;
            }
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumGood(vector<vector<int>>& statements) {
        int ans = 0;
        for (int mask = 1; mask < 1 << statements.size(); ++mask) ans = max(ans, check(mask, statements));
        return ans;
    }

    int check(int mask, vector<vector<int>>& statements) {
        int cnt = 0;
        int n = statements.size();
        for (int i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                for (int j = 0; j < n; ++j) {
                    int v = statements[i][j];
                    if (v < 2 && ((mask >> j) & 1) != v) return 0;
                }
                ++cnt;
            }
        }
        return cnt;
    }
};
```

### **Go**

```go
func maximumGood(statements [][]int) int {
	n := len(statements)
	check := func(mask int) int {
		cnt := 0
		for i, s := range statements {
			if ((mask >> i) & 1) == 1 {
				for j, v := range s {
					if v < 2 && ((mask>>j)&1) != v {
						return 0
					}
				}
				cnt++
			}
		}
		return cnt
	}
	ans := 0
	for mask := 1; mask < 1<<n; mask++ {
		ans = max(ans, check(mask))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maximumGood(statements: number[][]): number {
    const n = statements.length;
    function check(mask) {
        let cnt = 0;
        for (let i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                for (let j = 0; j < n; ++j) {
                    const v = statements[i][j];
                    if (v < 2 && ((mask >> j) & 1) != v) {
                        return 0;
                    }
                }
                ++cnt;
            }
        }
        return cnt;
    }
    let ans = 0;
    for (let mask = 1; mask < 1 << n; ++mask) {
        ans = Math.max(ans, check(mask));
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
