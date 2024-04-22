# [351. Android Unlock Patterns 🔒](https://leetcode.com/problems/android-unlock-patterns)

[中文文档](/solution/0300-0399/0351.Android%20Unlock%20Patterns/README.md)

<!-- tags:Bit Manipulation,Dynamic Programming,Backtracking,Bitmask -->

## Description

<p>Android devices have a special lock screen with a <code>3 x 3</code> grid of dots. Users can set an &quot;unlock pattern&quot; by connecting the dots in a specific sequence, forming a series of joined line segments where each segment&#39;s endpoints are two consecutive dots in the sequence. A sequence of <code>k</code> dots is a <strong>valid</strong> unlock pattern if both of the following are true:</p>

<ul>
	<li>All the dots in the sequence are <strong>distinct</strong>.</li>
	<li>If the line segment connecting two consecutive dots in the sequence passes through the <strong>center</strong> of any other dot, the other dot <strong>must have previously appeared</strong> in the sequence. No jumps through the center non-selected dots are allowed.
	<ul>
		<li>For example, connecting dots <code>2</code> and <code>9</code> without dots <code>5</code> or <code>6</code> appearing beforehand is valid because the line from dot <code>2</code> to dot <code>9</code> does not pass through the center of either dot <code>5</code> or <code>6</code>.</li>
		<li>However, connecting dots <code>1</code> and <code>3</code> without dot <code>2</code> appearing beforehand is invalid because the line from dot <code>1</code> to dot <code>3</code> passes through the center of dot <code>2</code>.</li>
	</ul>
	</li>
</ul>

<p>Here are some example valid and invalid unlock patterns:</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0351.Android%20Unlock%20Patterns/images/android-unlock.png" style="width: 418px; height: 128px;" /></p>

<ul>
	<li>The 1st pattern <code>[4,1,3,6]</code> is invalid because the line connecting dots <code>1</code> and <code>3</code> pass through dot <code>2</code>, but dot <code>2</code> did not previously appear in the sequence.</li>
	<li>The 2nd pattern <code>[4,1,9,2]</code> is invalid because the line connecting dots <code>1</code> and <code>9</code> pass through dot <code>5</code>, but dot <code>5</code> did not previously appear in the sequence.</li>
	<li>The 3rd pattern <code>[2,4,1,3,6]</code> is valid because it follows the conditions. The line connecting dots <code>1</code> and <code>3</code> meets the condition because dot <code>2</code> previously appeared in the sequence.</li>
	<li>The 4th pattern <code>[6,5,4,1,9,2]</code> is valid because it follows the conditions. The line connecting dots <code>1</code> and <code>9</code> meets the condition because dot <code>5</code> previously appeared in the sequence.</li>
</ul>

<p>Given two integers <code>m</code> and <code>n</code>, return <em>the <strong>number of unique and valid unlock patterns</strong> of the Android grid lock screen that consist of <strong>at least</strong> </em><code>m</code><em> keys and <strong>at most</strong> </em><code>n</code><em> keys.</em></p>

<p>Two unlock patterns are considered <strong>unique</strong> if there is a dot in one sequence that is not in the other, or the order of the dots is different.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> m = 1, n = 1
<strong>Output:</strong> 9
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 1, n = 2
<strong>Output:</strong> 65
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 9</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numberOfPatterns(self, m: int, n: int) -> int:
        def dfs(i: int, cnt: int = 1) -> int:
            if cnt > n:
                return 0
            vis[i] = True
            ans = int(cnt >= m)
            for j in range(1, 10):
                x = cross[i][j]
                if not vis[j] and (x == 0 or vis[x]):
                    ans += dfs(j, cnt + 1)
            vis[i] = False
            return ans

        cross = [[0] * 10 for _ in range(10)]
        cross[1][3] = cross[3][1] = 2
        cross[1][7] = cross[7][1] = 4
        cross[1][9] = cross[9][1] = 5
        cross[2][8] = cross[8][2] = 5
        cross[3][7] = cross[7][3] = 5
        cross[3][9] = cross[9][3] = 6
        cross[4][6] = cross[6][4] = 5
        cross[7][9] = cross[9][7] = 8
        vis = [False] * 10
        return dfs(1) * 4 + dfs(2) * 4 + dfs(5)
```

```java
class Solution {
    private int m;
    private int n;
    private int[][] cross = new int[10][10];
    private boolean[] vis = new boolean[10];

    public int numberOfPatterns(int m, int n) {
        this.m = m;
        this.n = n;
        cross[1][3] = cross[3][1] = 2;
        cross[1][7] = cross[7][1] = 4;
        cross[1][9] = cross[9][1] = 5;
        cross[2][8] = cross[8][2] = 5;
        cross[3][7] = cross[7][3] = 5;
        cross[3][9] = cross[9][3] = 6;
        cross[4][6] = cross[6][4] = 5;
        cross[7][9] = cross[9][7] = 8;
        return dfs(1, 1) * 4 + dfs(2, 1) * 4 + dfs(5, 1);
    }

    private int dfs(int i, int cnt) {
        if (cnt > n) {
            return 0;
        }
        vis[i] = true;
        int ans = cnt >= m ? 1 : 0;
        for (int j = 1; j < 10; ++j) {
            int x = cross[i][j];
            if (!vis[j] && (x == 0 || vis[x])) {
                ans += dfs(j, cnt + 1);
            }
        }
        vis[i] = false;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfPatterns(int m, int n) {
        int cross[10][10];
        memset(cross, 0, sizeof(cross));
        bool vis[10];
        memset(vis, false, sizeof(vis));
        cross[1][3] = cross[3][1] = 2;
        cross[1][7] = cross[7][1] = 4;
        cross[1][9] = cross[9][1] = 5;
        cross[2][8] = cross[8][2] = 5;
        cross[3][7] = cross[7][3] = 5;
        cross[3][9] = cross[9][3] = 6;
        cross[4][6] = cross[6][4] = 5;
        cross[7][9] = cross[9][7] = 8;

        function<int(int, int)> dfs = [&](int i, int cnt) {
            if (cnt > n) {
                return 0;
            }
            vis[i] = true;
            int ans = cnt >= m ? 1 : 0;
            for (int j = 1; j < 10; ++j) {
                int x = cross[i][j];
                if (!vis[j] && (x == 0 || vis[x])) {
                    ans += dfs(j, cnt + 1);
                }
            }
            vis[i] = false;
            return ans;
        };

        return dfs(1, 1) * 4 + dfs(2, 1) * 4 + dfs(5, 1);
    }
};
```

```go
func numberOfPatterns(m int, n int) int {
	cross := [10][10]int{}
	vis := [10]bool{}
	cross[1][3] = 2
	cross[1][7] = 4
	cross[1][9] = 5
	cross[2][8] = 5
	cross[3][7] = 5
	cross[3][9] = 6
	cross[4][6] = 5
	cross[7][9] = 8
	cross[3][1] = 2
	cross[7][1] = 4
	cross[9][1] = 5
	cross[8][2] = 5
	cross[7][3] = 5
	cross[9][3] = 6
	cross[6][4] = 5
	cross[9][7] = 8
	var dfs func(int, int) int
	dfs = func(i, cnt int) int {
		if cnt > n {
			return 0
		}
		vis[i] = true
		ans := 0
		if cnt >= m {
			ans++
		}
		for j := 1; j < 10; j++ {
			x := cross[i][j]
			if !vis[j] && (x == 0 || vis[x]) {
				ans += dfs(j, cnt+1)
			}
		}
		vis[i] = false
		return ans
	}
	return dfs(1, 1)*4 + dfs(2, 1)*4 + dfs(5, 1)
}
```

```ts
function numberOfPatterns(m: number, n: number): number {
    const cross: number[][] = Array(10)
        .fill(0)
        .map(() => Array(10).fill(0));
    const vis: boolean[] = Array(10).fill(false);
    cross[1][3] = cross[3][1] = 2;
    cross[1][7] = cross[7][1] = 4;
    cross[1][9] = cross[9][1] = 5;
    cross[2][8] = cross[8][2] = 5;
    cross[3][7] = cross[7][3] = 5;
    cross[3][9] = cross[9][3] = 6;
    cross[4][6] = cross[6][4] = 5;
    cross[7][9] = cross[9][7] = 8;
    const dfs = (i: number, cnt: number): number => {
        if (cnt > n) {
            return 0;
        }
        vis[i] = true;
        let ans = 0;
        if (cnt >= m) {
            ++ans;
        }
        for (let j = 1; j < 10; ++j) {
            const x = cross[i][j];
            if (!vis[j] && (x === 0 || vis[x])) {
                ans += dfs(j, cnt + 1);
            }
        }
        vis[i] = false;
        return ans;
    };
    return dfs(1, 1) * 4 + dfs(2, 1) * 4 + dfs(5, 1);
}
```

<!-- tabs:end -->

<!-- end -->
