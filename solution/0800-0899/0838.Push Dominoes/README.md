# [838. 推多米诺](https://leetcode-cn.com/problems/push-dominoes)

[English Version](/solution/0800-0899/0838.Push%20Dominoes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一行中有 <code>N</code> 张多米诺骨牌，我们将每张多米诺骨牌垂直竖立。</p>

<p>在开始时，我们同时把一些多米诺骨牌向左或向右推。</p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0838.Push%20Dominoes/images/domino.png" style="height: 160px; width: 418px;"></p>

<p>每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。</p>

<p>同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。</p>

<p>如果同时有多米诺骨牌落在一张垂直竖立的多米诺骨牌的两边，由于受力平衡， 该骨牌仍然保持不变。</p>

<p>就这个问题而言，我们会认为正在下降的多米诺骨牌不会对其它正在下降或已经下降的多米诺骨牌施加额外的力。</p>

<p>给定表示初始状态的字符串 &quot;S&quot; 。如果第 i 张多米诺骨牌被推向左边，则 <code>S[i] = &#39;L&#39;</code>；如果第 i 张多米诺骨牌被推向右边，则 <code>S[i] = &#39;R&#39;</code>；如果第 i 张多米诺骨牌没有被推动，则 <code>S[i] = &#39;.&#39;</code>。</p>

<p>返回表示最终状态的字符串。</p>

<p><strong>示例 </strong><strong>1</strong><strong>：</strong></p>

<pre><strong>输入：</strong>&quot;.L.R...LR..L..&quot;
<strong>输出：</strong>&quot;LL.RR.LLRRLL..&quot;</pre>

<p><strong>示例 </strong><strong>2</strong><strong>：</strong></p>

<pre><strong>输入：</strong>&quot;RR.L&quot;
<strong>输出：</strong>&quot;RR.L&quot;
<strong>说明：</strong>第一张多米诺骨牌没有给第二张施加额外的力。</pre>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= N &lt;= 10^5</code></li>
	<li>表示多米诺骨牌状态的字符串只含有 <code>&#39;L&#39;</code>，<code>&#39;R&#39;</code>; 以及 <code>&#39;.&#39;</code>;</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS。设 time 记录骨牌翻倒或者确定不翻倒的时间，翻倒的骨牌不会对正在翻倒或者已经翻倒的骨牌施加力，force 记录骨牌受到的力，骨牌仅在受到单侧的力时会翻倒。

初始时，将所有受力点 i (`dominoes[i] != '.'`)入队，并设置 `time[i] = 0`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class Solution {
public:
    string pushDominoes(string dominoes) {
        int n = dominoes.size();
        queue<int> q;
        vector<int> time(n, - 1);
        vector<string> force(n);
        for (int i = 0; i < n; i++)
        {
            if (dominoes[i] == '.') continue;
            q.emplace(i);
            time[i] = 0;
            force[i].push_back(dominoes[i]);
        }

        string ans(n, '.');
        while (!q.empty())
        {
            int i = q.front();
            q.pop();
            if (force[i].size() == 1)
            {
                char f = force[i][0];
                ans[i] = f;
                int j = (f == 'L') ? (i - 1) : (i + 1);
                if (j >= 0 && j < n)
                {
                    int t = time[i];
                    if (time[j] == -1)
                    {
                        q.emplace(j);
                        time[j] = t + 1;
                        force[j].push_back(f);
                    }
                    else if(time[j] == t + 1) force[j].push_back(f);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
