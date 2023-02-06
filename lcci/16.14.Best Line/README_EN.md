# [16.14. Best Line](https://leetcode.cn/problems/best-line-lcci)

[中文文档](/lcci/16.14.Best%20Line/README.md)

## Description

<p>Given a two-dimensional graph with points on it, find a line which passes the most number of points.</p>
<p>Assume all the points that passed by the line are stored in list <code>S</code>&nbsp;sorted by their number. You need to return <code>[S[0], S[1]]</code>, that is , two points that have smallest number. If there are more than one line that passes the most number of points, choose the one that has the smallest <code>S[0].</code>&nbsp;If there are more that one line that has the same <code>S[0]</code>, choose the one that has smallest <code>S[1]</code>.</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong> [[0,0],[1,1],[1,0],[2,0]]

<strong>Output: </strong> [0,2]

<strong>Explanation: </strong> The numbers of points passed by the line are [0,2,3].

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>2 &lt;= len(Points) &lt;= 300</code></li>
	<li><code>len(Points[i]) = 2</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        n = len(points)
        mx = 0
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                cnt = 2
                for k in range(j + 1, n):
                    x3, y3 = points[k]
                    a = (y2 - y1) * (x3 - x1)
                    b = (y3 - y1) * (x2 - x1)
                    cnt += a == b
                if mx < cnt:
                    mx = cnt
                    x, y = i, j
        return [x, y]
```

```python
class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        def gcd(a, b):
            return a if b == 0 else gcd(b, a % b)

        n = len(points)
        mx = 0
        for i in range(n):
            x1, y1 = points[i]
            cnt = defaultdict(list)
            for j in range(i + 1, n):
                x2, y2 = points[j]
                dx, dy = x2 - x1, y2 - y1
                g = gcd(dx, dy)
                k = (dx // g, dy // g)
                cnt[k].append((i, j))
                if mx < len(cnt[k]) or (mx == len(cnt[k]) and (x, y) > cnt[k][0]):
                    mx = len(cnt[k])
                    x, y = cnt[k][0]
        return [x, y]
```

### **Java**

```java
class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = new int[2];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int cnt = 2;
                for (int k = j + 1; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    int a = (y2 - y1) * (x3 - x1);
                    int b = (y3 - y1) * (x2 - x1);
                    if (a == b) {
                        ++cnt;
                    }
                }
                if (mx < cnt) {
                    mx = cnt;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = new int[2];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            Map<String, List<int[]>> cnt = new HashMap<>();
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                String key = (dx / g) + "." + (dy / g);
                cnt.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[] {i, j});
                if (mx < cnt.get(key).size()
                    || (mx == cnt.get(key).size()
                        && (ans[0] > cnt.get(key).get(0)[0]
                            || (ans[0] == cnt.get(key).get(0)[0]
                                && ans[1] > cnt.get(key).get(0)[1])))) {
                    mx = cnt.get(key).size();
                    ans = cnt.get(key).get(0);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        vector<int> ans(2);
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int cnt = 2;
                for (int k = j + 1; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    long a = (long) (y2 - y1) * (x3 - x1);
                    long b = (long) (y3 - y1) * (x2 - x1);
                    cnt += a == b;
                }
                if (mx < cnt) {
                    mx = cnt;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        pair<int, int> ans = {0, 0};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            unordered_map<string, vector<pair<int, int>>> cnt;
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                string k = to_string(dx / g) + "." + to_string(dy / g);
                cnt[k].push_back({i, j});
                if (mx < cnt[k].size() || (mx == cnt[k].size() && ans > cnt[k][0])) {
                    mx = cnt[k].size();
                    ans = cnt[k][0];
                }
            }
        }
        return vector<int>{ans.first, ans.second};
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

### **Go**

```go
func bestLine(points [][]int) []int {
	n := len(points)
	ans := make([]int, 2)
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			cnt := 2
			for k := j + 1; k < n; k++ {
				x3, y3 := points[k][0], points[k][1]
				a := (y2 - y1) * (x3 - x1)
				b := (y3 - y1) * (x2 - x1)
				if a == b {
					cnt++
				}
			}
			if mx < cnt {
				mx = cnt
				ans[0], ans[1] = i, j
			}
		}
	}
	return ans
}
```

```go
func bestLine(points [][]int) []int {
	n := len(points)
	ans := make([]int, 2)
	type pair struct{ i, j int }
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		cnt := map[pair][]pair{}
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			dx, dy := x2-x1, y2-y1
			g := gcd(dx, dy)
			k := pair{dx / g, dy / g}
			cnt[k] = append(cnt[k], pair{i, j})
			if mx < len(cnt[k]) || (mx == len(cnt[k]) && (ans[0] > cnt[k][0].i || (ans[0] == cnt[k][0].i && ans[1] > cnt[k][0].j))) {
				mx = len(cnt[k])
				ans[0], ans[1] = cnt[k][0].i, cnt[k][0].j
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
