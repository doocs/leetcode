# [2120. 执行所有后缀指令](https://leetcode.cn/problems/execution-of-all-suffix-instructions-staying-in-a-grid)

[English Version](/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个 <code>n x n</code> 大小的网格，左上角单元格坐标 <code>(0, 0)</code> ，右下角单元格坐标 <code>(n - 1, n - 1)</code> 。给你整数 <code>n</code> 和一个整数数组 <code>startPos</code> ，其中 <code>startPos = [start<sub>row</sub>, start<sub>col</sub>]</code> 表示机器人最开始在坐标为 <code>(start<sub>row</sub>, start<sub>col</sub>)</code> 的单元格上。</p>

<p>另给你一个长度为 <code>m</code> 、下标从 <strong>0</strong> 开始的字符串 <code>s</code> ，其中 <code>s[i]</code> 是对机器人的第 <code>i</code> 条指令：<code>'L'</code>（向左移动），<code>'R'</code>（向右移动），<code>'U'</code>（向上移动）和 <code>'D'</code>（向下移动）。</p>

<p>机器人可以从 <code>s</code> 中的任一第 <code>i</code> 条指令开始执行。它将会逐条执行指令直到 <code>s</code> 的末尾，但在满足下述条件之一时，机器人将会停止：</p>

<ul>
	<li>下一条指令将会导致机器人移动到网格外。</li>
	<li>没有指令可以执行。</li>
</ul>

<p>返回一个长度为 <code>m</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 是机器人从第 <code>i</code>&nbsp;条指令 <strong>开始</strong>&nbsp;，可以执行的 <strong>指令数目</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/images/1.png" style="width: 145px; height: 142px;" /></p>

<pre>
<strong>输入：</strong>n = 3, startPos = [0,1], s = "RRDDLU"
<strong>输出：</strong>[1,5,4,3,1,0]
<strong>解释：</strong>机器人从 startPos 出发，并从第 i 条指令开始执行：
- 0: "<em><strong>R</strong></em>RDDLU" 在移动到网格外之前，只能执行一条 "R" 指令。
- 1:  "<em><strong>RDDLU</strong></em>" 可以执行全部五条指令，机器人仍在网格内，最终到达 (0, 0) 。
- 2:   "<em><strong>DDLU</strong></em>" 可以执行全部四条指令，机器人仍在网格内，最终到达 (0, 0) 。
- 3:    "<em><strong>DLU</strong></em>" 可以执行全部三条指令，机器人仍在网格内，最终到达 (0, 0) 。
- 4:     "<em><strong>L</strong></em>U" 在移动到网格外之前，只能执行一条 "L" 指令。
- 5:      "U" 如果向上移动，将会移动到网格外。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/images/2.png" style="width: 106px; height: 103px;" /></p>

<pre>
<strong>输入：</strong>n = 2, startPos = [1,1], s = "LURD"
<strong>输出：</strong>[4,1,0,0]
<strong>解释：</strong>
- 0: "<em><strong>LURD</strong></em>"
- 1:  "<em><strong>U</strong></em>RD"
- 2:   "RD"
- 3:    "D"
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/images/3.png" style="width: 67px; height: 64px;" /></p>

<pre>
<strong>输入：</strong>n = 1, startPos = [0,0], s = "LRUD"
<strong>输出：</strong>[0,0,0,0]
<strong>解释：</strong>无论机器人从哪条指令开始执行，都会移动到网格外。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == s.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>startPos.length == 2</code></li>
	<li><code>0 &lt;= start<sub>row</sub>, start<sub>col</sub> &lt; n</code></li>
	<li><code>s</code> 由 <code>'L'</code>、<code>'R'</code>、<code>'U'</code> 和 <code>'D'</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

直接模拟。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def executeInstructions(self, n: int, startPos: List[int], s: str) -> List[int]:
        ans = []
        m = len(s)
        mp = {"L": [0, -1], "R": [0, 1], "U": [-1, 0], "D": [1, 0]}
        for i in range(m):
            x, y = startPos
            t = 0
            for j in range(i, m):
                a, b = mp[s[j]]
                if 0 <= x + a < n and 0 <= y + b < n:
                    x, y, t = x + a, y + b, t + 1
                else:
                    break
            ans.append(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] ans = new int[m];
        Map<Character, int[]> mp = new HashMap<>(4);
        mp.put('L', new int[] {0, -1});
        mp.put('R', new int[] {0, 1});
        mp.put('U', new int[] {-1, 0});
        mp.put('D', new int[] {1, 0});
        for (int i = 0; i < m; ++i) {
            int x = startPos[0], y = startPos[1];
            int t = 0;
            for (int j = i; j < m; ++j) {
                char c = s.charAt(j);
                int a = mp.get(c)[0], b = mp.get(c)[1];
                if (0 <= x + a && x + a < n && 0 <= y + b && y + b < n) {
                    x += a;
                    y += b;
                    ++t;
                } else {
                    break;
                }
            }
            ans[i] = t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> executeInstructions(int n, vector<int>& startPos, string s) {
        int m = s.size();
        vector<int> ans(m);
        unordered_map<char, vector<int>> mp;
        mp['L'] = {0, -1};
        mp['R'] = {0, 1};
        mp['U'] = {-1, 0};
        mp['D'] = {1, 0};
        for (int i = 0; i < m; ++i) {
            int x = startPos[0], y = startPos[1];
            int t = 0;
            for (int j = i; j < m; ++j) {
                int a = mp[s[j]][0], b = mp[s[j]][1];
                if (0 <= x + a && x + a < n && 0 <= y + b && y + b < n) {
                    x += a;
                    y += b;
                    ++t;
                } else
                    break;
            }
            ans[i] = t;
        }
        return ans;
    }
};
```

### **Go**

```go
func executeInstructions(n int, startPos []int, s string) []int {
	m := len(s)
	mp := make(map[byte][]int)
	mp['L'] = []int{0, -1}
	mp['R'] = []int{0, 1}
	mp['U'] = []int{-1, 0}
	mp['D'] = []int{1, 0}
	ans := make([]int, m)
	for i := 0; i < m; i++ {
		x, y := startPos[0], startPos[1]
		t := 0
		for j := i; j < m; j++ {
			a, b := mp[s[j]][0], mp[s[j]][1]
			if 0 <= x+a && x+a < n && 0 <= y+b && y+b < n {
				x += a
				y += b
				t++
			} else {
				break
			}
		}
		ans[i] = t
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function executeInstructions(
    n: number,
    startPos: number[],
    s: string,
): number[] {
    const m = s.length;
    const ans = new Array(m);
    for (let i = 0; i < m; i++) {
        let [y, x] = startPos;
        let j: number;
        for (j = i; j < m; j++) {
            const c = s[j];
            if (c === 'U') {
                y--;
            } else if (c === 'D') {
                y++;
            } else if (c === 'L') {
                x--;
            } else {
                x++;
            }
            if (y === -1 || y === n || x === -1 || x === n) {
                break;
            }
        }
        ans[i] = j - i;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn execute_instructions(n: i32, start_pos: Vec<i32>, s: String) -> Vec<i32> {
        let s = s.as_bytes();
        let m = s.len();
        let mut ans = vec![0; m];
        for i in 0..m {
            let mut y = start_pos[0];
            let mut x = start_pos[1];
            let mut j = i;
            while j < m {
                match s[j] {
                    b'U' => y -= 1,
                    b'D' => y += 1,
                    b'L' => x -= 1,
                    _ => x += 1,
                }
                if y == -1 || y == n || x == -1 || x == n {
                    break;
                }
                j += 1;
            }
            ans[i] = (j - i) as i32;
        }
        ans
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int *executeInstructions(int n, int *startPos, int startPosSize, char *s, int *returnSize) {
    int m = strlen(s);
    int *ans = malloc(sizeof(int) * m);
    for (int i = 0; i < m; i++) {
        int y = startPos[0];
        int x = startPos[1];
        int j = i;
        for (j = i; j < m; j++) {
            if (s[j] == 'U') {
                y--;
            } else if (s[j] == 'D') {
                y++;
            } else if (s[j] == 'L') {
                x--;
            } else {
                x++;
            }
            if (y == -1 || y == n || x == -1 || x == n) {
                break;
            }
        }
        ans[i] = j - i;
    }
    *returnSize = m;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
