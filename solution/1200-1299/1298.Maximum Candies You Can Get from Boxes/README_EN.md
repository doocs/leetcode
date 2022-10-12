# [1298. Maximum Candies You Can Get from Boxes](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes)

[中文文档](/solution/1200-1299/1298.Maximum%20Candies%20You%20Can%20Get%20from%20Boxes/README.md)

## Description

<p>You have <code>n</code> boxes labeled from <code>0</code> to <code>n - 1</code>. You are given four arrays: <code>status</code>, <code>candies</code>, <code>keys</code>, and <code>containedBoxes</code> where:</p>

<ul>
	<li><code>status[i]</code> is <code>1</code> if the <code>i<sup>th</sup></code> box is open and <code>0</code> if the <code>i<sup>th</sup></code> box is closed,</li>
	<li><code>candies[i]</code> is the number of candies in the <code>i<sup>th</sup></code> box,</li>
	<li><code>keys[i]</code> is a list of the labels of the boxes you can open after opening the <code>i<sup>th</sup></code> box.</li>
	<li><code>containedBoxes[i]</code> is a list of the boxes you found inside the <code>i<sup>th</sup></code> box.</li>
</ul>

<p>You are given an integer array <code>initialBoxes</code> that contains the labels of the boxes you initially have. You can take all the candies in <strong>any open box</strong> and you can use the keys in it to open new boxes and you also can use the boxes you find in it.</p>

<p>Return <em>the maximum number of candies you can get following the rules above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
<strong>Output:</strong> 16
<strong>Explanation:</strong> You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2.
Box 1 is closed and you do not have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.
In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.
Total number of candies collected = 7 + 4 + 5 = 16 candy.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
<strong>Output:</strong> 6
<strong>Explanation:</strong> You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys.
The total number of candies will be 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == status.length == candies.length == keys.length == containedBoxes.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>status[i]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= candies[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= keys[i].length &lt;= n</code></li>
	<li><code>0 &lt;= keys[i][j] &lt; n</code></li>
	<li>All values of <code>keys[i]</code> are <strong>unique</strong>.</li>
	<li><code>0 &lt;= containedBoxes[i].length &lt;= n</code></li>
	<li><code>0 &lt;= containedBoxes[i][j] &lt; n</code></li>
	<li>All values of <code>containedBoxes[i]</code> are unique.</li>
	<li>Each box is contained in one box at most.</li>
	<li><code>0 &lt;= initialBoxes.length &lt;= n</code></li>
	<li><code>0 &lt;= initialBoxes[i] &lt; n</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

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

```java
class Solution {
    public int maxCandies(
        int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
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
