# [1345. Jump Game IV](https://leetcode.com/problems/jump-game-iv)

[中文文档](/solution/1300-1399/1345.Jump%20Game%20IV/README.md)

## Description

<p>Given an array of&nbsp;integers <code>arr</code>, you are initially positioned at the first index of the array.</p>

<p>In one step you can jump from index <code>i</code> to index:</p>

<ul>
	<li><code>i + 1</code> where:&nbsp;<code>i + 1 &lt; arr.length</code>.</li>
	<li><code>i - 1</code> where:&nbsp;<code>i - 1 &gt;= 0</code>.</li>
	<li><code>j</code> where: <code>arr[i] == arr[j]</code> and <code>i != j</code>.</li>
</ul>

<p>Return <em>the minimum number of steps</em> to reach the <strong>last index</strong> of the array.</p>

<p>Notice that you can not jump outside of the array at any time.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [100,-23,-23,404,100,23,23,23,3,404]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You need three jumps from index 0 --&gt; 4 --&gt; 3 --&gt; 9. Note that index 9 is the last index of the array.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [7]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Start index is the last index. You do not need to jump.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,6,9,6,9,6,9,7]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can jump directly from index 0 to index 7 which is last index of the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>8</sup> &lt;= arr[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minJumps(self, arr: List[int]) -> int:
        idx = defaultdict(list)
        for i, v in enumerate(arr):
            idx[v].append(i)
        q = deque([(0, 0)])
        vis = {0}
        while q:
            i, step = q.popleft()
            if i == len(arr) - 1:
                return step
            v = arr[i]
            step += 1
            for j in idx[v]:
                if j not in vis:
                    vis.add(j)
                    q.append((j, step))
            del idx[v]
            if i + 1 < len(arr) and (i + 1) not in vis:
                vis.add(i + 1)
                q.append((i + 1, step))
            if i - 1 >= 0 and (i - 1) not in vis:
                vis.add(i - 1)
                q.append((i - 1, step))
```

### **Java**

```java
class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> idx = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            idx.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        Deque<int[]> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] e = q.pollFirst();
            int i = e[0], step = e[1];
            if (i == n - 1) {
                return step;
            }
            int v = arr[i];
            ++step;
            for (int j : idx.getOrDefault(v, new ArrayList<>())) {
                if (!vis.contains(j)) {
                    vis.add(j);
                    q.offer(new int[]{j, step});
                }
            }
            idx.remove(v);
            if (i + 1 < n && !vis.contains(i + 1)) {
                vis.add(i + 1);
                q.offer(new int[]{i + 1, step});
            }
            if (i - 1 >= 0 && !vis.contains(i - 1)) {
                vis.add(i - 1);
                q.offer(new int[]{i - 1, step});
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minJumps(vector<int>& arr) {
        unordered_map<int, vector<int>> idx;
        int n = arr.size();
        for (int i = 0; i < n; ++i) idx[arr[i]].push_back(i);
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        unordered_set<int> vis;
        vis.insert(0);
        while (!q.empty()) {
            auto e = q.front();
            q.pop();
            int i = e.first, step = e.second;
            if (i == n - 1) return step;
            int v = arr[i];
            ++step;
            if (idx.count(v)) {
                for (int j : idx[v]) {
                    if (!vis.count(j)) {
                        vis.insert(j);
                        q.emplace(j, step);
                    }
                }
                idx.erase(v);
            }
            if (i + 1 < n && !vis.count(i + 1)) {
                vis.insert(i + 1);
                q.emplace(i + 1, step);
            }
            if (i - 1 >= 0 && !vis.count(i - 1)) {
                vis.insert(i - 1);
                q.emplace(i - 1, step);
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func minJumps(arr []int) int {
	idx := map[int][]int{}
	for i, v := range arr {
		idx[v] = append(idx[v], i)
	}
	vis := map[int]bool{0: true}
	type pair struct{ idx, step int }
	q := []pair{{0, 0}}
	for len(q) > 0 {
		e := q[0]
		q = q[1:]
		i, step := e.idx, e.step
		if i == len(arr)-1 {
			return step
		}
		step++
		for _, j := range idx[arr[i]] {
			if !vis[j] {
				vis[j] = true
				q = append(q, pair{j, step})
			}
		}
		delete(idx, arr[i])
		if i+1 < len(arr) && !vis[i+1] {
			vis[i+1] = true
			q = append(q, pair{i + 1, step})
		}
		if i-1 >= 0 && !vis[i-1] {
			vis[i-1] = true
			q = append(q, pair{i - 1, step})
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
