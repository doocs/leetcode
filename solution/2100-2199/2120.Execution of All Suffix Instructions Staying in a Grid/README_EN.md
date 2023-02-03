# [2120. Execution of All Suffix Instructions Staying in a Grid](https://leetcode.com/problems/execution-of-all-suffix-instructions-staying-in-a-grid)

[中文文档](/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/README.md)

## Description

<p>There is an <code>n x n</code> grid, with the top-left cell at <code>(0, 0)</code> and the bottom-right cell at <code>(n - 1, n - 1)</code>. You are given the integer <code>n</code> and an integer array <code>startPos</code> where <code>startPos = [start<sub>row</sub>, start<sub>col</sub>]</code> indicates that a robot is initially at cell <code>(start<sub>row</sub>, start<sub>col</sub>)</code>.</p>

<p>You are also given a <strong>0-indexed</strong> string <code>s</code> of length <code>m</code> where <code>s[i]</code> is the <code>i<sup>th</sup></code> instruction for the robot: <code>&#39;L&#39;</code> (move left), <code>&#39;R&#39;</code> (move right), <code>&#39;U&#39;</code> (move up), and <code>&#39;D&#39;</code> (move down).</p>

<p>The robot can begin executing from any <code>i<sup>th</sup></code> instruction in <code>s</code>. It executes the instructions one by one towards the end of <code>s</code> but it stops if either of these conditions is met:</p>

<ul>
	<li>The next instruction will move the robot off the grid.</li>
	<li>There are no more instructions left to execute.</li>
</ul>

<p>Return <em>an array</em> <code>answer</code> <em>of length</em> <code>m</code> <em>where</em> <code>answer[i]</code> <em>is <strong>the number of instructions</strong> the robot can execute if the robot <strong>begins executing from</strong> the</em> <code>i<sup>th</sup></code> <em>instruction in</em> <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/images/1.png" style="width: 145px; height: 142px;" />
<pre>
<strong>Input:</strong> n = 3, startPos = [0,1], s = &quot;RRDDLU&quot;
<strong>Output:</strong> [1,5,4,3,1,0]
<strong>Explanation:</strong> Starting from startPos and beginning execution from the i<sup>th</sup> instruction:
- 0<sup>th</sup>: &quot;<u><strong>R</strong></u>RDDLU&quot;. Only one instruction &quot;R&quot; can be executed before it moves off the grid.
- 1<sup>st</sup>:  &quot;<u><strong>RDDLU</strong></u>&quot;. All five instructions can be executed while it stays in the grid and ends at (1, 1).
- 2<sup>nd</sup>:   &quot;<u><strong>DDLU</strong></u>&quot;. All four instructions can be executed while it stays in the grid and ends at (1, 0).
- 3<sup>rd</sup>:    &quot;<u><strong>DLU</strong></u>&quot;. All three instructions can be executed while it stays in the grid and ends at (0, 0).
- 4<sup>th</sup>:     &quot;<u><strong>L</strong></u>U&quot;. Only one instruction &quot;L&quot; can be executed before it moves off the grid.
- 5<sup>th</sup>:      &quot;U&quot;. If moving up, it would move off the grid.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/images/2.png" style="width: 106px; height: 103px;" />
<pre>
<strong>Input:</strong> n = 2, startPos = [1,1], s = &quot;LURD&quot;
<strong>Output:</strong> [4,1,0,0]
<strong>Explanation:</strong>
- 0<sup>th</sup>: &quot;<u><strong>LURD</strong></u>&quot;.
- 1<sup>st</sup>:  &quot;<u><strong>U</strong></u>RD&quot;.
- 2<sup>nd</sup>:   &quot;RD&quot;.
- 3<sup>rd</sup>:    &quot;D&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2120.Execution%20of%20All%20Suffix%20Instructions%20Staying%20in%20a%20Grid/images/3.png" style="width: 67px; height: 64px;" />
<pre>
<strong>Input:</strong> n = 1, startPos = [0,0], s = &quot;LRUD&quot;
<strong>Output:</strong> [0,0,0,0]
<strong>Explanation:</strong> No matter which instruction the robot begins execution from, it would move off the grid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == s.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>startPos.length == 2</code></li>
	<li><code>0 &lt;= start<sub>row</sub>, start<sub>col</sub> &lt; n</code></li>
	<li><code>s</code> consists of <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, <code>&#39;U&#39;</code>, and <code>&#39;D&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
