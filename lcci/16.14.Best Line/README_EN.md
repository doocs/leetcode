---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [16.14. Best Line](https://leetcode.cn/problems/best-line-lcci)

[中文文档](/lcci/16.14.Best%20Line/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brute Force

<!-- thinking:start -->

> **Thinking**
>
> Return the two smallest indices on a line that covers the most points. $n$ is small enough for $O(n^3)$.
>
> Two points fix a line; a third is collinear when $(y_2-y_1)(x_3-x_1)=(y_3-y_1)(x_2-x_1)$, avoiding division.
>
> Enumerate $i<j<k$, update the best count and pair $(i,j)$. Smaller indices first already match the tie-break.

<!-- thinking:end -->

We can enumerate any two points $(x_1, y_1), (x_2, y_2)$, connect these two points into a line, and the number of points on this line is 2. Then we enumerate other points $(x_3, y_3)$, and determine whether they are on the same line. If they are, the number of points on the line increases by 1; otherwise, the number of points on the line remains the same. Find the maximum number of points on a line, and the corresponding smallest two point indices are the answer.

The time complexity is $O(n^3)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array `points`.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### Swift

```swift
class Solution {
    func bestLine(_ points: [[Int]]) -> [Int] {
        let n = points.count
        var maxCount = 0
        var answer = [Int](repeating: 0, count: 2)

        for i in 0..<n {
            let x1 = points[i][0], y1 = points[i][1]
            for j in i + 1..<n {
                let x2 = points[j][0], y2 = points[j][1]
                var count = 2

                for k in j + 1..<n {
                    let x3 = points[k][0], y3 = points[k][1]
                    let a = (y2 - y1) * (x3 - x1)
                    let b = (y3 - y1) * (x2 - x1)
                    if a == b {
                        count += 1
                    }
                }

                if maxCount < count {
                    maxCount = count
                    answer = [i, j]
                }
            }
        }
        return answer
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Enumeration + Hash Table

<!-- thinking:start -->

> **Thinking**
>
> Hashing the reduced $(\mathrm{d}x,\mathrm{d}y)$ directly breaks when two points coincide, because the gcd is $0$, and it also splits one line into opposite directions.
>
> An anchor in the middle of a segment sees the two sides as negatives of each other, so each bucket undercounts and the chosen index pair is wrong.
>
> Copies of the anchor lie on every line through it, and opposite directions are the same line, so they must share one key.
>
> We therefore collect $(0,0)$ separately, canonicalize the reduced direction so that $\mathrm{d}x>0$ (or $\mathrm{d}y>0$ on a vertical line), and store indices under that key. The count is the anchor plus that bucket plus the copies, and the second index is the smallest among them.

<!-- thinking:end -->

We enumerate an anchor $(x_1, y_1)$ and hash the direction from it to every later point. A point that coincides with the anchor has no slope; we record it separately and add it to every line through the anchor. For the other points, reduce $(\mathrm{d}x, \mathrm{d}y)$ by their greatest common divisor, then fold opposite directions onto one key so that $\mathrm{d}x > 0$, or $\mathrm{d}y > 0$ when $\mathrm{d}x = 0$. Points that share a key are collinear with the anchor and with those copies. The number of points on the line is $1$ plus the size of that bucket plus the number of copies, and the second index is the smallest index among them. Anchors are scanned in increasing index order, and the answer is updated only when the count is strictly larger, or the count is equal and the index pair is smaller.

The time complexity is $O(n^2 \times \log m)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the length of the array `points` and the maximum difference between all horizontal and vertical coordinates in the array `points`, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestLine(self, points: List[List[int]]) -> List[int]:
        def gcd(a, b):
            return a if b == 0 else gcd(b, a % b)

        n = len(points)
        mx = 0
        x = y = 0
        for i in range(n):
            x1, y1 = points[i]
            cnt = defaultdict(list)
            dup = []
            for j in range(i + 1, n):
                dx, dy = points[j][0] - x1, points[j][1] - y1
                if dx == 0 and dy == 0:
                    dup.append(j)
                    continue
                g = gcd(dx, dy)
                dx //= g
                dy //= g
                if dx < 0 or (dx == 0 and dy < 0):
                    dx, dy = -dx, -dy
                cnt[(dx, dy)].append(j)
            groups = (
                [js + dup for js in cnt.values()] if cnt else ([dup] if dup else [])
            )
            for js in groups:
                c = len(js) + 1
                b = min(js)
                if c > mx or (c == mx and (i, b) < (x, y)):
                    mx = c
                    x, y = i, b
        return [x, y]
```

#### Java

```java
class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = new int[2];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            Map<String, List<Integer>> cnt = new HashMap<>();
            List<Integer> dup = new ArrayList<>();
            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - x1, dy = points[j][1] - y1;
                if (dx == 0 && dy == 0) {
                    dup.add(j);
                    continue;
                }
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                String key = dx + "." + dy;
                cnt.computeIfAbsent(key, k -> new ArrayList<>()).add(j);
            }
            if (cnt.isEmpty()) {
                if (!dup.isEmpty()) {
                    int c = dup.size() + 1;
                    if (better(mx, ans, c, i, dup.get(0))) {
                        mx = c;
                        ans[0] = i;
                        ans[1] = dup.get(0);
                    }
                }
                continue;
            }
            for (List<Integer> js : cnt.values()) {
                int b = js.get(0);
                if (!dup.isEmpty()) {
                    b = Math.min(b, dup.get(0));
                }
                int c = js.size() + dup.size() + 1;
                if (better(mx, ans, c, i, b)) {
                    mx = c;
                    ans[0] = i;
                    ans[1] = b;
                }
            }
        }
        return ans;
    }

    private boolean better(int mx, int[] ans, int c, int a, int b) {
        return c > mx || (c == mx && (a < ans[0] || (a == ans[0] && b < ans[1])));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        pair<int, int> ans = {0, 0};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            unordered_map<string, vector<int>> cnt;
            vector<int> dup;
            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - x1, dy = points[j][1] - y1;
                if (dx == 0 && dy == 0) {
                    dup.push_back(j);
                    continue;
                }
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                string k = to_string(dx) + "." + to_string(dy);
                cnt[k].push_back(j);
            }
            auto consider = [&](int b, int c) {
                if (c > mx || (c == mx && ans > pair<int, int>{i, b})) {
                    mx = c;
                    ans = {i, b};
                }
            };
            if (cnt.empty()) {
                if (!dup.empty()) {
                    consider(dup[0], (int) dup.size() + 1);
                }
                continue;
            }
            for (auto& e : cnt) {
                int b = e.second[0];
                if (!dup.empty()) {
                    b = min(b, dup[0]);
                }
                consider(b, (int) e.second.size() + (int) dup.size() + 1);
            }
        }
        return vector<int>{ans.first, ans.second};
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

#### Go

```go
func bestLine(points [][]int) []int {
	n := len(points)
	ans := []int{0, 0}
	type pair struct{ x, y int }
	mx := 0
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		cnt := map[pair][]int{}
		var dup []int
		for j := i + 1; j < n; j++ {
			dx, dy := points[j][0]-x1, points[j][1]-y1
			if dx == 0 && dy == 0 {
				dup = append(dup, j)
				continue
			}
			g := gcd(dx, dy)
			dx /= g
			dy /= g
			if dx < 0 || (dx == 0 && dy < 0) {
				dx, dy = -dx, -dy
			}
			k := pair{dx, dy}
			cnt[k] = append(cnt[k], j)
		}
		consider := func(b, c int) {
			if c > mx || (c == mx && (i < ans[0] || (i == ans[0] && b < ans[1]))) {
				mx = c
				ans[0], ans[1] = i, b
			}
		}
		if len(cnt) == 0 {
			if len(dup) > 0 {
				consider(dup[0], len(dup)+1)
			}
			continue
		}
		for _, js := range cnt {
			b := js[0]
			if len(dup) > 0 && dup[0] < b {
				b = dup[0]
			}
			consider(b, len(js)+len(dup)+1)
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
