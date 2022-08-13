# [1298. 你能从盒子里获得的最大糖果数](https://leetcode.cn/problems/maximum-candies-you-can-get-from-boxes)

[English Version](/solution/1200-1299/1298.Maximum%20Candies%20You%20Can%20Get%20from%20Boxes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你&nbsp;<code>n</code>&nbsp;个盒子，每个盒子的格式为&nbsp;<code>[status, candies, keys, containedBoxes]</code>&nbsp;，其中：</p>

<ul>
	<li>状态字&nbsp;<code>status[i]</code>：整数，如果&nbsp;<code>box[i]</code>&nbsp;是开的，那么是 <strong>1&nbsp;</strong>，否则是 <strong>0&nbsp;</strong>。</li>
	<li>糖果数&nbsp;<code>candies[i]</code>: 整数，表示&nbsp;<code>box[i]</code> 中糖果的数目。</li>
	<li>钥匙&nbsp;<code>keys[i]</code>：数组，表示你打开&nbsp;<code>box[i]</code>&nbsp;后，可以得到一些盒子的钥匙，每个元素分别为该钥匙对应盒子的下标。</li>
	<li>内含的盒子&nbsp;<code>containedBoxes[i]</code>：整数，表示放在&nbsp;<code>box[i]</code>&nbsp;里的盒子所对应的下标。</li>
</ul>

<p>给你一个&nbsp;<code>initialBoxes</code> 数组，表示你现在得到的盒子，你可以获得里面的糖果，也可以用盒子里的钥匙打开新的盒子，还可以继续探索从这个盒子里找到的其他盒子。</p>

<p>请你按照上述规则，返回可以获得糖果的 <strong>最大数目&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
<strong>输出：</strong>16
<strong>解释：
</strong>一开始你有盒子 0 。你将获得它里面的 7 个糖果和盒子 1 和 2。
盒子 1 目前状态是关闭的，而且你还没有对应它的钥匙。所以你将会打开盒子 2 ，并得到里面的 4 个糖果和盒子 1 的钥匙。
在盒子 1 中，你会获得 5 个糖果和盒子 3 ，但是你没法获得盒子 3 的钥匙所以盒子 3 会保持关闭状态。
你总共可以获得的糖果数目 = 7 + 4 + 5 = 16 个。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
<strong>输出：</strong>6
<strong>解释：
</strong>你一开始拥有盒子 0 。打开它你可以找到盒子 1,2,3,4,5 和它们对应的钥匙。
打开这些盒子，你将获得所有盒子的糖果，所以总糖果数为 6 个。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>status = [1,1,1], candies = [100,1,100], keys = [[],[0,2],[]], containedBoxes = [[],[],[]], initialBoxes = [1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>status = [1], candies = [100], keys = [[]], containedBoxes = [[]], initialBoxes = []
<strong>输出：</strong>0
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>status = [1,1,1], candies = [2,3,2], keys = [[],[],[]], containedBoxes = [[],[],[]], initialBoxes = [2,1,0]
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= status.length &lt;= 1000</code></li>
	<li><code>status.length == candies.length == keys.length == containedBoxes.length == n</code></li>
	<li><code>status[i]</code> 要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code> 。</li>
	<li><code>1 &lt;= candies[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= keys[i].length &lt;= status.length</code></li>
	<li><code>0 &lt;= keys[i][j] &lt; status.length</code></li>
	<li><code>keys[i]</code>&nbsp;中的值都是互不相同的。</li>
	<li><code>0 &lt;= containedBoxes[i].length &lt;= status.length</code></li>
	<li><code>0 &lt;= containedBoxes[i][j] &lt; status.length</code></li>
	<li><code>containedBoxes[i]</code>&nbsp;中的值都是互不相同的。</li>
	<li>每个盒子最多被一个盒子包含。</li>
	<li><code>0 &lt;= initialBoxes.length&nbsp;&lt;= status.length</code></li>
	<li><code>0 &lt;= initialBoxes[i] &lt; status.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxCandies(
        self,
        status: List[int],
        candies: List[int],
        keys: List[List[int]],
        containedBoxes: List[List[int]],
        initialBoxes: List[int],
    ) -> int:
        q = deque([i for i in initialBoxes if status[i] == 1])
        ans = sum(candies[i] for i in initialBoxes if status[i] == 1)
        has = set(initialBoxes)
        took = {i for i in initialBoxes if status[i] == 1}

        while q:
            i = q.popleft()
            for k in keys[i]:
                status[k] = 1
                if k in has and k not in took:
                    ans += candies[k]
                    took.add(k)
                    q.append(k)
            for j in containedBoxes[i]:
                has.add(j)
                if status[j] and j not in took:
                    ans += candies[j]
                    took.add(j)
                    q.append(j)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int ans = 0;
        int n = status.length;
        boolean[] has = new boolean[n];
        boolean[] took = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i : initialBoxes) {
            has[i] = true;
            if (status[i] == 1) {
                ans += candies[i];
                took[i] = true;
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int k : keys[i]) {
                status[k] = 1;
                if (has[k] && !took[k]) {
                    ans += candies[k];
                    took[k] = true;
                    q.offer(k);
                }
            }
            for (int j : containedBoxes[i]) {
                has[j] = true;
                if (status[j] == 1 && !took[j]) {
                    ans += candies[j];
                    took[j] = true;
                    q.offer(j);
                }
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
    int maxCandies(vector<int>& status, vector<int>& candies, vector<vector<int>>& keys, vector<vector<int>>& containedBoxes, vector<int>& initialBoxes) {
        int ans = 0;
        int n = status.size();
        vector<bool> has(n);
        vector<bool> took(n);
        queue<int> q;
        for (int& i : initialBoxes) {
            has[i] = true;
            if (status[i]) {
                ans += candies[i];
                took[i] = true;
                q.push(i);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int k : keys[i]) {
                status[k] = 1;
                if (has[k] && !took[k]) {
                    ans += candies[k];
                    took[k] = true;
                    q.push(k);
                }
            }
            for (int j : containedBoxes[i]) {
                has[j] = true;
                if (status[j] && !took[j]) {
                    ans += candies[j];
                    took[j] = true;
                    q.push(j);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxCandies(status []int, candies []int, keys [][]int, containedBoxes [][]int, initialBoxes []int) int {
	ans := 0
	n := len(status)
	has := make([]bool, n)
	took := make([]bool, n)
	var q []int
	for _, i := range initialBoxes {
		has[i] = true
		if status[i] == 1 {
			ans += candies[i]
			took[i] = true
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for _, k := range keys[i] {
			status[k] = 1
			if has[k] && !took[k] {
				ans += candies[k]
				took[k] = true
				q = append(q, k)
			}
		}
		for _, j := range containedBoxes[i] {
			has[j] = true
			if status[j] == 1 && !took[j] {
				ans += candies[j]
				took[j] = true
				q = append(q, j)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
