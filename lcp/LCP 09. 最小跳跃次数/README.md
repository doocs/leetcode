# [LCP 09. 最小跳跃次数](https://leetcode.cn/problems/zui-xiao-tiao-yue-ci-shu)

## 题目描述

<!-- 这里写题目描述 -->

<p>为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 <code>N</code> 个特殊弹簧排成一排，编号为 <code>0</code> 到 <code>N-1</code>。初始有一个小球在编号 <code>0</code> 的弹簧处。若小球在编号为 <code>i</code> 的弹簧处，通过按动弹簧，可以选择把小球向右弹射&nbsp;<code>jump[i]</code> 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 <code>i</code> 弹簧处按动弹簧，小球可以弹向 <code>0</code> 到 <code>i-1</code> 中任意弹簧或者 <code>i+jump[i]</code> 的弹簧（若 <code>i+jump[i]&gt;=N</code> ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不能再向左弹。</p>

<p>为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 <code>0</code> 弹簧弹出整个机器，即向右越过编号 <code>N-1</code> 的弹簧。</p>

<p><strong>示例 1：</strong></p>

<blockquote>
<p>输入：<code>jump = [2, 5, 1, 1, 1, 1]</code></p>

<p>输出：<code>3</code></p>

<p>解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -&gt; 2 -&gt; 1 -&gt; 6，最终小球弹出了机器。</p>
</blockquote>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= jump.length &lt;= 10^6</code></li>
	<li><code>1 &lt;= jump[i] &lt;= 10000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minJump(self, jump: List[int]) -> int:
        n = len(jump)
        vis = [False] * (n + 1)
        q = deque([0])
        ans = 0
        vis[0] = True
        mx = 1
        while q:
            for _ in range(len(q)):
                i = q.popleft()
                if i + jump[i] >= n:
                    return ans + 1
                for j in list(range(mx, i)) + [i + jump[i]]:
                    if not vis[j]:
                        q.append(j)
                        vis[j] = True
                mx = max(mx, i + 1)
            ans += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minJump(int[] jump) {
        int n = jump.length;
        boolean[] vis = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;
        int ans = 0;
        int mx = 1;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int i = q.poll();
                int j = i + jump[i];
                if (j >= n) {
                    return ans + 1;
                }
                if (!vis[j]) {
                    q.offer(j);
                    vis[j] = true;
                }
                for (j = mx; j < i; ++j) {
                    if (!vis[j]) {
                        q.offer(j);
                        vis[j] = true;
                    }
                }
                mx = Math.max(mx, i + 1);
            }
            ++ans;
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minJump(vector<int>& jump) {
        int n = jump.size();
        vector<bool> vis(n + 1);
        queue<int> q {{0}};
        vis[0] = true;
        int ans = 0, mx = 1;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                int i = q.front();
                int j = i + jump[i];
                if (j >= n) return ans + 1;
                q.pop();
                if (!vis[j]) {
                    vis[j] = true;
                    q.push(j);
                }
                for (j = mx; j < i; ++j) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
                mx = max(mx, i + 1);
            }
            ++ans;
        }
        return -1;
    }
};
```

### **Go**

```go
func minJump(jump []int) int {
    n := len(jump)
    vis := make([]bool, n + 1)
    q := []int{0}
    vis[0] = true
    ans, mx := 0, 1
    for len(q) > 0 {
        for t := len(q); t > 0; t-- {
            i := q[0]
            q = q[1:]
            j := i + jump[i]
            if j >= n {
                return ans + 1
            }
            if !vis[j] {
                vis[j] = true
                q = append(q, j)
            }
            for j = mx; j < i; j++ {
                if !vis[j] {
                    vis[j] = true
                    q = append(q, j)
                }
            }
            if mx < i + 1 {
                mx = i + 1
            }
        }
        ans++
    }
    return -1
}
```

### **...**

```

```

<!-- tabs:end -->
