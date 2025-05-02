---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0838.Push%20Dominoes/README.md
tags:
    - 双指针
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [838. 推多米诺](https://leetcode.cn/problems/push-dominoes)

[English Version](/solution/0800-0899/0838.Push%20Dominoes/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>n</code> 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。</p>

<p>每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。</p>

<p>如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。</p>

<p>就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。</p>

<p>给你一个字符串 <code>dominoes</code> 表示这一行多米诺骨牌的初始状态，其中：</p>

<ul>
 <li><code>dominoes[i] = 'L'</code>，表示第 <code>i</code> 张多米诺骨牌被推向左侧，</li>
 <li><code>dominoes[i] = 'R'</code>，表示第 <code>i</code> 张多米诺骨牌被推向右侧，</li>
 <li><code>dominoes[i] = '.'</code>，表示没有推动第 <code>i</code> 张多米诺骨牌。</li>
</ul>

<p>返回表示最终状态的字符串。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dominoes = "RR.L"
<strong>输出：</strong>"RR.L"
<strong>解释：</strong>第一张多米诺骨牌没有给第二张施加额外的力。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0838.Push%20Dominoes/images/domino.png" style="height: 196px; width: 512px;" />
<pre>
<strong>输入：</strong>dominoes = ".L.R...LR..L.."
<strong>输出：</strong>"LL.RR.LLRRLL.."
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
 <li><code>n == dominoes.length</code></li>
 <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
 <li><code>dominoes[i]</code> 为 <code>'L'</code>、<code>'R'</code> 或 <code>'.'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：多源 BFS

把所有初始受到推力的骨牌（`L` 或 `R`）视作 **源点**，它们会同时向外扩散各自的力。用队列按时间层级（0, 1, 2 …）进行 BFS：

我们定义 $\text{time[i]}$ 记录第 *i* 张骨牌第一次受力的时刻，`-1` 表示尚未受力，定义 $\text{force[i]}$ 是一个长度可变的列表，存放该骨牌在同一时刻收到的方向（`'L'`、`'R'`）。初始时把所有 `L/R` 的下标压入队列，并将它们的时间置 0。

当弹出下标 *i* 时，若 $\text{force[i]}$ 只有一个方向，骨牌就会倒向该方向 $f$。设下一张骨牌下标为

$$
j =
\begin{cases}
i - 1, & f = L,\\
i + 1, & f = R.
\end{cases}
$$

若 $0 \leq j < n$：

-   若 $\text{time[j]}=-1$，说明 *j* 从未受力，记录 $\text{time[j]}=\text{time[i]}+1$ 并入队，同时把 $f$ 写入 $\text{force[j]}$。
-   若 $\text{time[j]}=\text{time[i]}+1$，说明它在同一“下一刻”已受过另一股力，此时只把 $f$ 追加到 $\text{force[j]}$，形成对冲；后续因 `len(force[j])==2`，它将保持竖直。

队列清空后，所有 $\text{force[i]}$ 长度为 1 的位置倒向对应方向；长度为 2 的位置保持 `.`。最终将字符数组拼接为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是骨牌的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pushDominoes(self, dominoes: str) -> str:
        n = len(dominoes)
        q = deque()
        time = [-1] * n
        force = defaultdict(list)
        for i, f in enumerate(dominoes):
            if f != '.':
                q.append(i)
                time[i] = 0
                force[i].append(f)
        ans = ['.'] * n
        while q:
            i = q.popleft()
            if len(force[i]) == 1:
                ans[i] = f = force[i][0]
                j = i - 1 if f == 'L' else i + 1
                if 0 <= j < n:
                    t = time[i]
                    if time[j] == -1:
                        q.append(j)
                        time[j] = t + 1
                        force[j].append(f)
                    elif time[j] == t + 1:
                        force[j].append(f)
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        Deque<Integer> q = new ArrayDeque<>();
        int[] time = new int[n];
        Arrays.fill(time, -1);
        List<Character>[] force = new List[n];
        for (int i = 0; i < n; ++i) {
            force[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            char f = dominoes.charAt(i);
            if (f != '.') {
                q.offer(i);
                time[i] = 0;
                force[i].add(f);
            }
        }
        char[] ans = new char[n];
        Arrays.fill(ans, '.');
        while (!q.isEmpty()) {
            int i = q.poll();
            if (force[i].size() == 1) {
                ans[i] = force[i].get(0);
                char f = ans[i];
                int j = f == 'L' ? i - 1 : i + 1;
                if (j >= 0 && j < n) {
                    int t = time[i];
                    if (time[j] == -1) {
                        q.offer(j);
                        time[j] = t + 1;
                        force[j].add(f);
                    } else if (time[j] == t + 1) {
                        force[j].add(f);
                    }
                }
            }
        }
        return new String(ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string pushDominoes(string dominoes) {
        int n = dominoes.size();
        queue<int> q;
        vector<int> time(n, -1);
        vector<string> force(n);
        for (int i = 0; i < n; i++) {
            if (dominoes[i] == '.') continue;
            q.emplace(i);
            time[i] = 0;
            force[i].push_back(dominoes[i]);
        }

        string ans(n, '.');
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            if (force[i].size() == 1) {
                char f = force[i][0];
                ans[i] = f;
                int j = (f == 'L') ? (i - 1) : (i + 1);
                if (j >= 0 && j < n) {
                    int t = time[i];
                    if (time[j] == -1) {
                        q.emplace(j);
                        time[j] = t + 1;
                        force[j].push_back(f);
                    } else if (time[j] == t + 1)
                        force[j].push_back(f);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func pushDominoes(dominoes string) string {
	n := len(dominoes)
	q := []int{}
	time := make([]int, n)
	for i := range time {
		time[i] = -1
	}
	force := make([][]byte, n)
	for i, c := range dominoes {
		if c != '.' {
			q = append(q, i)
			time[i] = 0
			force[i] = append(force[i], byte(c))
		}
	}

	ans := bytes.Repeat([]byte{'.'}, n)
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if len(force[i]) > 1 {
			continue
		}
		f := force[i][0]
		ans[i] = f
		j := i - 1
		if f == 'R' {
			j = i + 1
		}
		if 0 <= j && j < n {
			t := time[i]
			if time[j] == -1 {
				q = append(q, j)
				time[j] = t + 1
				force[j] = append(force[j], f)
			} else if time[j] == t+1 {
				force[j] = append(force[j], f)
			}
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function pushDominoes(dominoes: string): string {
    const n = dominoes.length;
    const q: number[] = [];
    const time: number[] = Array(n).fill(-1);
    const force: string[][] = Array.from({ length: n }, () => []);

    for (let i = 0; i < n; i++) {
        const f = dominoes[i];
        if (f !== '.') {
            q.push(i);
            time[i] = 0;
            force[i].push(f);
        }
    }

    const ans: string[] = Array(n).fill('.');
    let head = 0;
    while (head < q.length) {
        const i = q[head++];
        if (force[i].length === 1) {
            const f = force[i][0];
            ans[i] = f;
            const j = f === 'L' ? i - 1 : i + 1;
            if (j >= 0 && j < n) {
                const t = time[i];
                if (time[j] === -1) {
                    q.push(j);
                    time[j] = t + 1;
                    force[j].push(f);
                } else if (time[j] === t + 1) {
                    force[j].push(f);
                }
            }
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
